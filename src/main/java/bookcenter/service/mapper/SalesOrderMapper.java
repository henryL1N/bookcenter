package bookcenter.service.mapper;

import bookcenter.domain.*;
import bookcenter.service.dto.SalesOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SalesOrder and its DTO SalesOrderDTO.
 */
@Mapper(componentModel = "spring", uses = {WarehouseMapper.class, EmployeeMapper.class})
public interface SalesOrderMapper extends EntityMapper<SalesOrderDTO, SalesOrder> {

    @Mapping(source = "warehouse.id", target = "warehouseId")
    @Mapping(source = "warehouse.name", target = "warehouseName")
    @Mapping(source = "seller.id", target = "sellerId")
    @Mapping(source = "seller.name", target = "sellerName")
    SalesOrderDTO toDto(SalesOrder salesOrder);

    @Mapping(target = "orderItems", ignore = true)
    @Mapping(source = "warehouseId", target = "warehouse")
    @Mapping(source = "sellerId", target = "seller")
    SalesOrder toEntity(SalesOrderDTO salesOrderDTO);

    default SalesOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setId(id);
        return salesOrder;
    }
}
