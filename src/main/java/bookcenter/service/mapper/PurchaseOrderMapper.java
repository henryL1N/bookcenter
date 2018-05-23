package bookcenter.service.mapper;

import bookcenter.domain.*;
import bookcenter.service.dto.PurchaseOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PurchaseOrder and its DTO PurchaseOrderDTO.
 */
@Mapper(componentModel = "spring", uses = {WarehouseMapper.class, EmployeeMapper.class})
public interface PurchaseOrderMapper extends EntityMapper<PurchaseOrderDTO, PurchaseOrder> {

    @Mapping(source = "warehouse.id", target = "warehouseId")
    @Mapping(source = "warehouse.name", target = "warehouseName")
    @Mapping(source = "buyer.id", target = "buyerId")
    @Mapping(source = "buyer.name", target = "buyerName")
    PurchaseOrderDTO toDto(PurchaseOrder purchaseOrder);

    @Mapping(target = "orderItems", ignore = true)
    @Mapping(source = "warehouseId", target = "warehouse")
    @Mapping(source = "buyerId", target = "buyer")
    PurchaseOrder toEntity(PurchaseOrderDTO purchaseOrderDTO);

    default PurchaseOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(id);
        return purchaseOrder;
    }
}
