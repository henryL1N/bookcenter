package bookcenter.service.mapper;

import bookcenter.domain.PurchaseOrder;
import bookcenter.service.dto.PurchaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Henry Lin badcop@163.com
 */
@Mapper(componentModel = "spring", uses = {EmployeeMapper.class, WarehouseMapper.class, OrderItemMapper.class})
public interface PurchaseMapper extends EntityMapper<PurchaseDTO, PurchaseOrder> {

    @Mapping(source = "buyer.id", target = "buyerId")
    @Mapping(source = "buyer.name", target = "buyerName")
    @Mapping(source = "warehouse.id", target = "warehouseId")
    @Mapping(source = "warehouse.name", target = "warehouseName")
    PurchaseDTO toDto(PurchaseOrder purchaseOrder);

    @Mapping(source = "buyerId", target = "buyer")
    @Mapping(source = "warehouseId", target = "warehouse")
    PurchaseOrder toEntity(PurchaseDTO purchaseDTO);

    default PurchaseOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setId(id);
        return purchaseOrder;
    }
}
