package bookcenter.service.mapper;

import bookcenter.domain.*;
import bookcenter.service.dto.SalesOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SalesOrder and its DTO SalesOrderDTO.
 */
@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface SalesOrderMapper extends EntityMapper<SalesOrderDTO, SalesOrder> {

    @Mapping(source = "seller.id", target = "sellerId")
    SalesOrderDTO toDto(SalesOrder salesOrder);

    @Mapping(source = "sellerId", target = "seller")
    @Mapping(target = "orderItems", ignore = true)
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
