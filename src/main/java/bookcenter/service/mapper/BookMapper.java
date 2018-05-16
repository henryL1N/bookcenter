package bookcenter.service.mapper;

import bookcenter.domain.*;
import bookcenter.service.dto.BookDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Book and its DTO BookDTO.
 */
@Mapper(componentModel = "spring", uses = {PublisherMapper.class, CategoryMapper.class})
public interface BookMapper extends EntityMapper<BookDTO, Book> {

    @Mapping(source = "publisher.id", target = "publisherId")
    @Mapping(source = "publisher.name", target = "publisherName")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    BookDTO toDto(Book book);

    @Mapping(source = "publisherId", target = "publisher")
    @Mapping(source = "categoryId", target = "category")
    Book toEntity(BookDTO bookDTO);

    default Book fromId(Long id) {
        if (id == null) {
            return null;
        }
        Book book = new Book();
        book.setId(id);
        return book;
    }
}
