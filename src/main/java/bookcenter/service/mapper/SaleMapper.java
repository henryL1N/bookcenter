package bookcenter.service.mapper;

import bookcenter.domain.SalesOrder;
import bookcenter.service.dto.SaleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Henry Lin badcop@163.com
 */
@Mapper(componentModel = "spring", uses = {WarehouseMapper.class, EmployeeMapper.class, OrderItemMapper.class})
public interface SaleMapper extends EntityMapper<SaleDTO, SalesOrder> {

    @Mapping(source = "warehouse.id", target = "warehouseId")
    @Mapping(source = "warehouse.name", target = "warehouseName")
    @Mapping(source = "seller.id", target = "sellerId")
    @Mapping(source = "seller.name", target = "sellerName")
    SaleDTO toDto(SalesOrder salesOrder);

    @Mapping(source = "warehouseId", target = "warehouse")
    @Mapping(source = "sellerId", target = "seller")
    SalesOrder toEntity(SaleDTO saleDTO);

    default SalesOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setId(id);
        return salesOrder;
    }
}
