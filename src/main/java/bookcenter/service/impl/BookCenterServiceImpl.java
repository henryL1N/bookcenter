package bookcenter.service.impl;

import bookcenter.service.BookCenterService;
import bookcenter.domain.BookCenter;
import bookcenter.repository.BookCenterRepository;
import bookcenter.service.dto.BookCenterDTO;
import bookcenter.service.mapper.BookCenterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing BookCenter.
 */
@Service
@Transactional
public class BookCenterServiceImpl implements BookCenterService {

    private final Logger log = LoggerFactory.getLogger(BookCenterServiceImpl.class);

    private final BookCenterRepository bookCenterRepository;

    private final BookCenterMapper bookCenterMapper;

    public BookCenterServiceImpl(BookCenterRepository bookCenterRepository, BookCenterMapper bookCenterMapper) {
        this.bookCenterRepository = bookCenterRepository;
        this.bookCenterMapper = bookCenterMapper;
    }

    /**
     * Save a bookCenter.
     *
     * @param bookCenterDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BookCenterDTO save(BookCenterDTO bookCenterDTO) {
        log.debug("Request to save BookCenter : {}", bookCenterDTO);
        BookCenter bookCenter = bookCenterMapper.toEntity(bookCenterDTO);
        bookCenter = bookCenterRepository.save(bookCenter);
        return bookCenterMapper.toDto(bookCenter);
    }

    /**
     * Get all the bookCenters.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BookCenterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BookCenters");
        return bookCenterRepository.findAll(pageable)
            .map(bookCenterMapper::toDto);
    }

    /**
     * Get one bookCenter by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BookCenterDTO findOne(Long id) {
        log.debug("Request to get BookCenter : {}", id);
        BookCenter bookCenter = bookCenterRepository.findOne(id);
        return bookCenterMapper.toDto(bookCenter);
    }

    /**
     * Delete the bookCenter by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BookCenter : {}", id);
        bookCenterRepository.delete(id);
    }
}
