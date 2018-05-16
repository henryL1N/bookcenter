package bookcenter.service.mapper;

import bookcenter.domain.*;
import bookcenter.service.dto.DepartmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Department and its DTO DepartmentDTO.
 */
@Mapper(componentModel = "spring", uses = {BookCenterMapper.class, EmployeeMapper.class})
public interface DepartmentMapper extends EntityMapper<DepartmentDTO, Department> {

    @Mapping(source = "bookCenter.id", target = "bookCenterId")
    @Mapping(source = "manager.id", target = "managerId")
    DepartmentDTO toDto(Department department);

    @Mapping(source = "bookCenterId", target = "bookCenter")
    @Mapping(source = "managerId", target = "manager")
    @Mapping(target = "employees", ignore = true)
    Department toEntity(DepartmentDTO departmentDTO);

    default Department fromId(Long id) {
        if (id == null) {
            return null;
        }
        Department department = new Department();
        department.setId(id);
        return department;
    }
}
