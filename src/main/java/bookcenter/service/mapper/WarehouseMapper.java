package bookcenter.service.mapper;

import bookcenter.domain.*;
import bookcenter.service.dto.WarehouseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Warehouse and its DTO WarehouseDTO.
 */
@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface WarehouseMapper extends EntityMapper<WarehouseDTO, Warehouse> {

    @Mapping(source = "keeper.id", target = "keeperId")
    WarehouseDTO toDto(Warehouse warehouse);

    @Mapping(source = "keeperId", target = "keeper")
    @Mapping(target = "stockItems", ignore = true)
    Warehouse toEntity(WarehouseDTO warehouseDTO);

    default Warehouse fromId(Long id) {
        if (id == null) {
            return null;
        }
        Warehouse warehouse = new Warehouse();
        warehouse.setId(id);
        return warehouse;
    }
}
