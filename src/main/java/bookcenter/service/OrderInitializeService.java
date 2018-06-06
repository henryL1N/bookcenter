package bookcenter.service;

import bookcenter.domain.*;
import bookcenter.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.*;

/**
 * @author Henry Lin badcop@163.com
 */
@Service
public class OrderInitializeService {

    private static final Random RANDOM = new Random(Instant.now().toEpochMilli());

    private final PurchaseOrderRepository purchaseOrderRepository;

    private final SalesOrderRepository salesOrderRepository;

    private final OrderItemRepository orderItemRepository;

    private final WarehouseRepository warehouseRepository;

    private final EmployeeRepository employeeRepository;

    private final BookRepository bookRepository;

    private final StockItemRepository stockItemRepository;

    private List<StockItem> stockItems;

    private Long lastSum;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderInitializeService.class);

    OrderInitializeService(PurchaseOrderRepository purchaseOrderRepository,
                           SalesOrderRepository salesOrderRepository,
                           OrderItemRepository orderItemRepository,
                           WarehouseRepository warehouseRepository,
                           EmployeeRepository employeeRepository,
                           BookRepository bookRepository,
                           StockItemRepository stockItemRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.salesOrderRepository = salesOrderRepository;
        this.orderItemRepository = orderItemRepository;
        this.warehouseRepository = warehouseRepository;
        this.employeeRepository = employeeRepository;
        this.bookRepository = bookRepository;
        this.stockItemRepository = stockItemRepository;
        initialize();
    }

    public void initialize() {
        if (purchaseOrderRepository.count() == 0 &&
            salesOrderRepository.count() == 0 &&
            orderItemRepository.count() == 0) {
            this.stockItems = new ArrayList<>();
            initialize(
                Instant.parse("2016-01-01T00:00:00.00Z"),
                Instant.now(),
                Period.ofDays(5),
                Period.ofDays(3),
                30,
                9,
                100L,
                50L,
                Duration.ofMinutes(600),
                Duration.ofMinutes(30)
            );
            stockItemRepository.save(this.stockItems);
            this.stockItems.clear();
        }
    }

    public void initialize(Instant from,
                           Instant to,
                           TemporalAmount purchaseCycle,
                           TemporalAmount purchaseCycleTolerance,
                           Integer purchaseItemsAmount,
                           Integer purchaseItemsAmountTolerance,
                           Long purchaseItemQuantity,
                           Long purchaseItemQuantityTolerance,
                           TemporalAmount saleCycle,
                           TemporalAmount saleCycleTolerance) {
        //from, to validation
        if (from.equals(to) || from.isAfter(to)) {
            return;
        }
        //purchase parameters validation
        if (Instant.ofEpochSecond(0).plus(purchaseCycle)
            .getEpochSecond() < 1) {
            purchaseCycle = Duration.ofSeconds(1);
        }
        if (Instant.ofEpochSecond(0).plus(purchaseCycleTolerance)
            .getEpochSecond() < 0) {
            purchaseCycleTolerance = Duration.ofSeconds(0);
        }
        if (Instant.ofEpochSecond(0).plus(purchaseCycle).
            minus(purchaseCycleTolerance).getEpochSecond() < 1) {
            purchaseCycleTolerance = Duration.ofSeconds(
                Instant.ofEpochSecond(0).plus(purchaseCycle).getEpochSecond() - 1
            );
        }
        if (purchaseItemsAmount < 1) {
            purchaseItemsAmount = 1;
        }
        if (purchaseItemsAmountTolerance < 0) {
            purchaseItemsAmountTolerance = 0;
        }
        if (purchaseItemsAmount - purchaseItemsAmountTolerance < 1) {
            purchaseItemsAmountTolerance = purchaseItemsAmount - 1;
        }
        if (purchaseItemQuantity < 1L) {
            purchaseItemQuantity = 1L;
        }
        if (purchaseItemQuantityTolerance < 0L) {
            purchaseItemQuantityTolerance = 0L;
        }
        if (purchaseItemQuantity - purchaseItemQuantityTolerance < 1L) {
            purchaseItemQuantityTolerance = purchaseItemQuantity - 1L;
        }
        //sale parameters validation
        if (Instant.ofEpochSecond(0).plus(saleCycle)
            .getEpochSecond() < 1) {
            saleCycle = Duration.ofSeconds(1);
        }
        if (Instant.ofEpochSecond(0).plus(saleCycleTolerance)
            .getEpochSecond() < 0) {
            saleCycleTolerance = Duration.ofSeconds(0);
        }
        if (Instant.ofEpochSecond(0).plus(saleCycle).
            minus(saleCycleTolerance).getEpochSecond() < 1) {
            saleCycleTolerance = Duration.ofSeconds(
                Instant.ofEpochSecond(0).plus(saleCycle).getEpochSecond() - 1
            );
        }
        Integer saleItemsAmount;
        Integer saleItemsAmountTolerance;
        Long saleItemQuantity;
        Long saleItemQuantityTolerance;
        Double purchaseSaleCycleRate =
            1.0 * Instant.ofEpochSecond(0).plus(purchaseCycle).getEpochSecond()
                / Instant.ofEpochSecond(0).plus(saleCycle).getEpochSecond();
        saleItemQuantity = 50L;
        saleItemQuantityTolerance = saleItemQuantity - 1L;
        saleItemsAmount = (int) (purchaseItemsAmount * purchaseItemQuantity / purchaseSaleCycleRate / saleItemQuantity);
        if (saleItemsAmount < 1) {
            saleItemsAmount = 1;
        }
        saleCycle = Duration.ofSeconds(
            (long) (
                1.0 * Instant.ofEpochSecond(0).plus(purchaseCycle).getEpochSecond()
                    / (purchaseItemsAmount * purchaseItemQuantity)
                    * saleItemQuantity * saleItemsAmount
            )
        );
        saleItemsAmountTolerance = saleItemsAmount - 1;
        LOGGER.debug("Order initialize from: " + from.toString()
            + " to: " + to.toString()
            + " purchaseCycle: " + purchaseCycle.toString()
            + " purchaseCycleTolerance: " + purchaseCycleTolerance.toString()
            + " purchaseItemsAmount: " + purchaseItemsAmount.toString()
            + " purchaseItemsAmountTolerance: " + purchaseItemsAmountTolerance.toString()
            + " purchaseItemQuantity: " + purchaseItemQuantity.toString()
            + " purchaseItemQuantityTolerance: " + purchaseItemQuantityTolerance.toString()
            + " saleCycle: " + saleCycle.toString()
            + " saleCycleTolerance: " + saleCycleTolerance.toString()
            + " saleItemsAmount: " + saleItemsAmount.toString()
            + " saleItemsAmountTolerance: " + saleItemsAmountTolerance.toString()
            + " saleItemQuantity: " + saleItemQuantity.toString()
            + " saleItemQuantityTolerance: " + saleItemQuantityTolerance.toString()
        );
        initialize(
            from,
            to,
            purchaseCycle,
            purchaseCycleTolerance,
            purchaseItemsAmount,
            purchaseItemsAmountTolerance,
            purchaseItemQuantity,
            purchaseItemQuantityTolerance,
            saleCycle,
            saleCycleTolerance,
            saleItemsAmount,
            saleItemsAmountTolerance,
            saleItemQuantity,
            saleItemQuantityTolerance);
    }

    public void initialize(Instant from,
                           Instant to,
                           TemporalAmount purchaseCycle,
                           TemporalAmount purchaseCycleTolerance,
                           Integer purchaseItemsAmount,
                           Integer purchaseItemsAmountTolerance,
                           Long purchaseItemQuantity,
                           Long purchaseItemQuantityTolerance,
                           TemporalAmount saleCycle,
                           TemporalAmount saleCycleTolerance,
                           Integer saleItemsAmount,
                           Integer saleItemsAmountTolerance,
                           Long saleItemQuantity,
                           Long saleItemQuantityTolerance) {
        //from, to validation
        if (from.equals(to) || from.isAfter(to)) {
            return;
        }
        //purchase parameters validation
        if (Instant.ofEpochSecond(0).plus(purchaseCycle)
            .getEpochSecond() < 1) {
            purchaseCycle = Duration.ofSeconds(1);
        }
        if (Instant.ofEpochSecond(0).plus(purchaseCycleTolerance)
            .getEpochSecond() < 0) {
            purchaseCycleTolerance = Duration.ofSeconds(0);
        }
        if (Instant.ofEpochSecond(0).plus(purchaseCycle).
            minus(purchaseCycleTolerance).getEpochSecond() < 1) {
            purchaseCycleTolerance = Duration.ofSeconds(
                Instant.ofEpochSecond(0).plus(purchaseCycle).getEpochSecond() - 1
            );
        }
        if (purchaseItemsAmount < 1) {
            purchaseItemsAmount = 1;
        }
        if (purchaseItemsAmountTolerance < 0) {
            purchaseItemsAmountTolerance = 0;
        }
        if (purchaseItemsAmount - purchaseItemsAmountTolerance < 1) {
            purchaseItemsAmountTolerance = purchaseItemsAmount - 1;
        }
        if (purchaseItemQuantity < 1L) {
            purchaseItemQuantity = 1L;
        }
        if (purchaseItemQuantityTolerance < 0L) {
            purchaseItemQuantityTolerance = 0L;
        }
        if (purchaseItemQuantity - purchaseItemQuantityTolerance < 1L) {
            purchaseItemQuantityTolerance = purchaseItemQuantity - 1L;
        }
        //sale parameters validation
        if (Instant.ofEpochSecond(0).plus(saleCycle)
            .getEpochSecond() < 1) {
            saleCycle = Duration.ofSeconds(1);
        }
        if (Instant.ofEpochSecond(0).plus(saleCycleTolerance)
            .getEpochSecond() < 0) {
            saleCycleTolerance = Duration.ofSeconds(0);
        }
        if (Instant.ofEpochSecond(0).plus(saleCycle).
            minus(saleCycleTolerance).getEpochSecond() < 1) {
            saleCycleTolerance = Duration.ofSeconds(
                Instant.ofEpochSecond(0).plus(saleCycle).getEpochSecond() - 1
            );
        }
        if (saleItemsAmount < 1) {
            saleItemsAmount = 1;
        }
        if (saleItemsAmountTolerance < 0) {
            saleItemsAmountTolerance = 0;
        }
        if (saleItemsAmount - saleItemsAmountTolerance < 1) {
            saleItemsAmountTolerance = saleItemsAmount - 1;
        }
        if (saleItemQuantity < 1L) {
            saleItemQuantity = 1L;
        }
        if (saleItemQuantityTolerance < 0L) {
            saleItemQuantityTolerance = 0L;
        }
        if (saleItemQuantity - saleItemQuantityTolerance < 1L) {
            saleItemQuantityTolerance = saleItemQuantity - 1L;
        }

        Double purchaseSaleCycleRate =
            1.0 * Instant.ofEpochSecond(0).plus(purchaseCycle).getEpochSecond()
                / Instant.ofEpochSecond(0).plus(saleCycle).getEpochSecond();

        Instant virtualTime = from;
        Instant nextPurchaseTime = from;
        Instant nextSaleTime = from.plus(saleCycle);

        while (!virtualTime.isAfter(to)) {
            if (virtualTime.equals(nextPurchaseTime) || virtualTime.isAfter(nextPurchaseTime)) {
                purchase(
                    nextPurchaseTime,
                    purchaseItemsAmount +
                        (
                            purchaseItemsAmountTolerance != 0
                                ? RANDOM.nextInt()
                                % (purchaseItemsAmountTolerance + 1)
                                : 0
                        ),
                    purchaseItemQuantity,
                    purchaseItemQuantityTolerance
                );
                nextPurchaseTime = nextPurchaseTime.plus(purchaseCycle).plus(
                    Duration.ofSeconds(
                        Instant.ofEpochSecond(0).plus(purchaseCycleTolerance)
                            .getEpochSecond() != 0
                            ? RANDOM.nextLong()
                            % Instant.ofEpochSecond(0)
                            .plus(purchaseCycleTolerance)
                            .getEpochSecond()
                            : 0
                    )
                );
            } else if (virtualTime.equals(nextSaleTime) || virtualTime.isAfter(nextSaleTime)) {
                sale(
                    nextSaleTime,
                    saleItemsAmount +
                        (
                            saleItemsAmountTolerance != 0
                                ? RANDOM.nextInt()
                                % (saleItemsAmountTolerance + 1)
                                : 0
                        ),
                    saleItemQuantity,
                    saleItemQuantityTolerance
                );
                nextSaleTime = nextSaleTime.plus(saleCycle).plus(
                    Duration.ofSeconds(
                        Instant.ofEpochSecond(0).plus(saleCycleTolerance)
                            .getEpochSecond() != 0
                            ? RANDOM.nextLong()
                            % Instant.ofEpochSecond(0)
                            .plus(saleCycleTolerance)
                            .getEpochSecond()
                            : 0
                    )
                );
            } else if (nextPurchaseTime.isAfter(nextSaleTime)) {
                virtualTime = nextSaleTime;
            } else {
                virtualTime = nextPurchaseTime;
            }
        }
    }

    private void purchase(Instant purchaseTime,
                          Integer purchaseItemsAmount,
                          Long purchaseItemQuantity,
                          Long purchaseItemQuantityTolerance) {
        if (purchaseItemsAmount < 1) {
            purchaseItemsAmount = 1;
        }
        if (purchaseItemQuantity < 1L) {
            purchaseItemQuantity = 1L;
        }
        if (purchaseItemQuantityTolerance < 0L) {
            purchaseItemQuantityTolerance = 0L;
        }
        if (purchaseItemQuantity - purchaseItemQuantityTolerance < 1L) {
            purchaseItemQuantityTolerance = purchaseItemQuantity - 1L;
        }

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setDate(purchaseTime);
        purchaseOrder.setSupplier("initializer");
        List<Warehouse> warehouses = warehouseRepository.findAll();
        purchaseOrder.setWarehouse(
            warehouses.get(
                RANDOM.nextInt(warehouses.size())
            )
        );
        List<Employee> employees = employeeRepository.findAllByPosition("销售经理");
        purchaseOrder.setBuyer(
            employees.get(
                RANDOM.nextInt(employees.size())
            )
        );

        purchaseOrder.setTotalAmount(new BigDecimal(0));

        HashSet<OrderItem> orderItems = new HashSet<OrderItem>();
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            return;
        }
        while (orderItems.size() < purchaseItemsAmount) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(
                books.get(
                    RANDOM.nextInt(books.size())
                )
            );
            books.remove(orderItem.getBook());
            orderItem.setPrice(orderItem.getBook().getCost());
            orderItem.setQuantity(
                purchaseItemQuantity +
                    (
                        purchaseItemQuantityTolerance != 0
                            ? RANDOM.nextLong()
                            % (purchaseItemQuantityTolerance + 1)
                            : 0
                    )
            );
            orderItems.add(orderItem);
            purchaseOrder.setTotalAmount(
                purchaseOrder.getTotalAmount().add(
                    orderItem.getPrice().multiply(
                        BigDecimal.valueOf(
                            orderItem.getQuantity()
                        )
                    )
                )
            );
        }
        purchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        for (OrderItem orderItem : orderItems) {
            orderItem.setPurchaseOrder(purchaseOrder);
        }
        orderItemRepository.save(orderItems);
        for (OrderItem orderItem : orderItems) {
            StockItem stockItem = this.
                findFirstByBookAndWarehouse(
                    orderItem.getBook(), purchaseOrder.getWarehouse()
                );
            if (null == stockItem) {
                stockItem = new StockItem();
                stockItem.setBook(orderItem.getBook());
                stockItem.setQuantity(orderItem.getQuantity());
                stockItem.setWarehouse(purchaseOrder.getWarehouse());
                stockItems.add(stockItem);
            } else {
                stockItem.setQuantity(
                    stockItem.getQuantity() + orderItem.getQuantity()
                );
            }
        }
//        LOGGER.debug(purchaseOrder.toString());
    }

    private void sale(Instant saleTime,
                      Integer saleItemsAmount,
                      Long saleItemQuantity,
                      Long saleItemQuantityTolerance) {
        if (saleItemsAmount < 1) {
            saleItemsAmount = 1;
        }
        if (saleItemQuantity < 1L) {
            saleItemQuantity = 1L;
        }
        if (saleItemQuantityTolerance < 0L) {
            saleItemQuantityTolerance = 0L;
        }
        if (saleItemQuantity - saleItemQuantityTolerance < 1L) {
            saleItemQuantityTolerance = saleItemQuantity - 1L;
        }

        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setDate(saleTime);
        salesOrder.setCustomer("initializer");
        List<Warehouse> warehouses = warehouseRepository.findAll();
        List<StockItem> stockItems;
        Iterator<Warehouse> warehouseIterator = warehouses.iterator();
        while (warehouseIterator.hasNext()) {
            stockItems =
                this.findAllByWarehouseAndQuantityGreaterThan(
                    warehouseIterator.next(), 0L
                );
            if (stockItems.isEmpty()) {
                warehouseIterator.remove();
            }
        }
        if (warehouses.isEmpty()) {
            return;
        }
        salesOrder.setWarehouse(
            warehouses.get(
                RANDOM.nextInt(warehouses.size())
            )
        );
        List<Employee> employees = employeeRepository.findAllByPosition("销售员");
        salesOrder.setSeller(
            employees.get(
                RANDOM.nextInt(employees.size())
            )
        );

        salesOrder.setTotalAmount(new BigDecimal(0));

        HashSet<OrderItem> orderItems = new HashSet<OrderItem>();
        stockItems =
            this.findAllByWarehouseAndQuantityGreaterThan(
                salesOrder.getWarehouse(), 0L
            );
        ArrayList<StockItem> stockItemsToUpdate = new ArrayList<>();
        while (orderItems.size() < saleItemsAmount) {
            if (stockItems.isEmpty()) {
                break;
            }
            OrderItem orderItem = new OrderItem();
            StockItem stockItem = stockItems.get(
                RANDOM.nextInt(stockItems.size())
            );
            orderItem.setBook(stockItem.getBook());
            orderItem.setPrice(orderItem.getBook().getRetailPrice());
            orderItem.setQuantity(
                saleItemQuantity +
                    (
                        saleItemQuantityTolerance != 0
                            ? RANDOM.nextLong()
                            % (saleItemQuantityTolerance + 1)
                            : 0
                    )
            );
            if (orderItem.getQuantity() > stockItem.getQuantity()) {
                orderItem.setQuantity(stockItem.getQuantity());
            }
            stockItem.setQuantity(stockItem.getQuantity() - orderItem.getQuantity());
            stockItemsToUpdate.add(stockItem);
            stockItems.remove(stockItem);
            orderItems.add(orderItem);
            salesOrder.setTotalAmount(
                salesOrder.getTotalAmount().add(
                    orderItem.getPrice().multiply(
                        BigDecimal.valueOf(
                            orderItem.getQuantity()
                        )
                    )
                )
            );
        }
        salesOrder = salesOrderRepository.save(salesOrder);
        for (OrderItem orderItem : orderItems) {
            orderItem.setSalesOrder(salesOrder);
        }
        orderItemRepository.save(orderItems);
//        LOGGER.debug(salesOrder.toString());
    }

    private List<StockItem> findAllByWarehouseAndQuantityGreaterThan(Warehouse warehouse, Long greaterThan) {
        List<StockItem> stockItems = new ArrayList<>();
        for (StockItem stockItem : this.stockItems) {
            if (stockItem.getWarehouse().equals(warehouse) &&
                !stockItem.getQuantity().equals(0L)) {
                stockItems.add(stockItem);
            }
        }
        return stockItems;
    }

    private StockItem findFirstByBookAndWarehouse(Book book, Warehouse warehouse) {
        for (StockItem stockItem : this.stockItems) {
            if (stockItem.getBook().equals(book) &&
                stockItem.getWarehouse().equals(warehouse)) {
                return stockItem;
            }
        }
        return null;
    }
}
