package bookcenter.service.mapper;

import bookcenter.domain.*;
import bookcenter.service.dto.OrderItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity OrderItem and its DTO OrderItemDTO.
 */
@Mapper(componentModel = "spring", uses = {PurchaseOrderMapper.class, SalesOrderMapper.class, BookMapper.class})
public interface OrderItemMapper extends EntityMapper<OrderItemDTO, OrderItem> {

    @Mapping(source = "purchaseOrder.id", target = "purchaseOrderId")
    @Mapping(source = "salesOrder.id", target = "salesOrderId")
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.name", target = "bookName")
    OrderItemDTO toDto(OrderItem orderItem);

    @Mapping(source = "purchaseOrderId", target = "purchaseOrder")
    @Mapping(source = "salesOrderId", target = "salesOrder")
    @Mapping(source = "bookId", target = "book")
    OrderItem toEntity(OrderItemDTO orderItemDTO);

    default OrderItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setId(id);
        return orderItem;
    }
}
