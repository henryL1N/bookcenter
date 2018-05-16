package bookcenter.service.mapper;

import bookcenter.domain.*;
import bookcenter.service.dto.StockItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StockItem and its DTO StockItemDTO.
 */
@Mapper(componentModel = "spring", uses = {BookMapper.class, WarehouseMapper.class})
public interface StockItemMapper extends EntityMapper<StockItemDTO, StockItem> {

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "warehouse.id", target = "warehouseId")
    StockItemDTO toDto(StockItem stockItem);

    @Mapping(source = "bookId", target = "book")
    @Mapping(source = "warehouseId", target = "warehouse")
    StockItem toEntity(StockItemDTO stockItemDTO);

    default StockItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        StockItem stockItem = new StockItem();
        stockItem.setId(id);
        return stockItem;
    }
}
