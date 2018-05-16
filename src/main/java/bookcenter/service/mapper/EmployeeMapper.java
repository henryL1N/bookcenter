package bookcenter.service.mapper;

import bookcenter.domain.*;
import bookcenter.service.dto.EmployeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Employee and its DTO EmployeeDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, BookCenterMapper.class, DepartmentMapper.class})
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "bookCenter.id", target = "bookCenterId")
    @Mapping(source = "department.id", target = "departmentId")
    EmployeeDTO toDto(Employee employee);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "bookCenterId", target = "bookCenter")
    @Mapping(source = "departmentId", target = "department")
    Employee toEntity(EmployeeDTO employeeDTO);

    default Employee fromId(Long id) {
        if (id == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(id);
        return employee;
    }
}
