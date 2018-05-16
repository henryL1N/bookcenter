package bookcenter.service.mapper;

import bookcenter.domain.*;
import bookcenter.service.dto.PurchaseOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PurchaseOrder and its DTO PurchaseOrderDTO.
 */
@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface PurchaseOrderMapper extends EntityMapper<PurchaseOrderDTO, PurchaseOrder> {

    @Mapping(source = "buyer.id", target = "buyerId")
    PurchaseOrderDTO toDto(PurchaseOrder purchaseOrder);

    @Mapping(source = "buyerId", target = "buyer")
    @Mapping(target = "orderItems", ignore = true)
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
