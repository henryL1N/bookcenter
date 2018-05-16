package bookcenter.service.mapper;

import bookcenter.domain.*;
import bookcenter.service.dto.BookCenterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BookCenter and its DTO BookCenterDTO.
 */
@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface BookCenterMapper extends EntityMapper<BookCenterDTO, BookCenter> {

    @Mapping(source = "generalManager.id", target = "generalManagerId")
    BookCenterDTO toDto(BookCenter bookCenter);

    @Mapping(source = "generalManagerId", target = "generalManager")
    @Mapping(target = "departments", ignore = true)
    @Mapping(target = "employees", ignore = true)
    BookCenter toEntity(BookCenterDTO bookCenterDTO);

    default BookCenter fromId(Long id) {
        if (id == null) {
            return null;
        }
        BookCenter bookCenter = new BookCenter();
        bookCenter.setId(id);
        return bookCenter;
    }
}
