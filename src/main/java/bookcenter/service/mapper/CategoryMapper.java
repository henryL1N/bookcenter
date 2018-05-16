package bookcenter.service.mapper;

import bookcenter.domain.*;
import bookcenter.service.dto.CategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Category and its DTO CategoryDTO.
 */
@Mapper(componentModel = "spring", uses = {DepartmentMapper.class})
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {

    @Mapping(source = "salesDepartment.id", target = "salesDepartmentId")
    CategoryDTO toDto(Category category);

    @Mapping(source = "salesDepartmentId", target = "salesDepartment")
    Category toEntity(CategoryDTO categoryDTO);

    default Category fromId(Long id) {
        if (id == null) {
            return null;
        }
        Category category = new Category();
        category.setId(id);
        return category;
    }
}
