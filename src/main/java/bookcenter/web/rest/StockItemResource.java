package bookcenter.web.rest;

import bookcenter.domain.StockItem;
import bookcenter.service.StockItemService;
import bookcenter.service.dto.StockItemDTO;
import bookcenter.web.rest.errors.BadRequestAlertException;
import bookcenter.web.rest.util.HeaderUtil;
import bookcenter.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing StockItem.
 */
@RestController
@RequestMapping("/api")
public class StockItemResource {

    private final Logger log = LoggerFactory.getLogger(StockItemResource.class);

    private static final String ENTITY_NAME = "stockItem";

    private final StockItemService stockItemService;

    public StockItemResource(StockItemService stockItemService) {
        this.stockItemService = stockItemService;
    }

    /**
     * POST  /stock-items : Create a new stockItem.
     *
     * @param stockItemDTO the stockItemDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new stockItemDTO, or with status 400 (Bad Request) if the stockItem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/stock-items")
    @Timed
    public ResponseEntity<StockItemDTO> createStockItem(@Valid @RequestBody StockItemDTO stockItemDTO) throws URISyntaxException {
        log.debug("REST request to save StockItem : {}", stockItemDTO);
        if (stockItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new stockItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StockItemDTO result = stockItemService.save(stockItemDTO);
        return ResponseEntity.created(new URI("/api/stock-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /stock-items : Updates an existing stockItem.
     *
     * @param stockItemDTO the stockItemDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated stockItemDTO,
     * or with status 400 (Bad Request) if the stockItemDTO is not valid,
     * or with status 500 (Internal Server Error) if the stockItemDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/stock-items")
    @Timed
    public ResponseEntity<StockItemDTO> updateStockItem(@Valid @RequestBody StockItemDTO stockItemDTO) throws URISyntaxException {
        log.debug("REST request to update StockItem : {}", stockItemDTO);
        if (stockItemDTO.getId() == null) {
            return createStockItem(stockItemDTO);
        }
        StockItemDTO result = stockItemService.save(stockItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stockItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /stock-items : get all the stockItems.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of stockItems in body
     */
    @GetMapping("/stock-items")
    @Timed
    public ResponseEntity<List<StockItemDTO>> getAllStockItems(Pageable pageable) {
        log.debug("REST request to get a page of StockItems");
        Page<StockItemDTO> page = stockItemService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stock-items");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /stock-items/:id : get the "id" stockItem.
     *
     * @param id the id of the stockItemDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stockItemDTO, or with status 404 (Not Found)
     */
    @GetMapping("/stock-items/{id}")
    @Timed
    public ResponseEntity<StockItemDTO> getStockItem(@PathVariable Long id) {
        log.debug("REST request to get StockItem : {}", id);
        StockItemDTO stockItemDTO = stockItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(stockItemDTO));
    }

    /**
     * DELETE  /stock-items/:id : delete the "id" stockItem.
     *
     * @param id the id of the stockItemDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/stock-items/{id}")
    @Timed
    public ResponseEntity<Void> deleteStockItem(@PathVariable Long id) {
        log.debug("REST request to delete StockItem : {}", id);
        stockItemService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/stock-items/warehouseId/{id}")
    @Timed
    public ResponseEntity<List<StockItemDTO>> getStockItemsByWarehouseId(@PathVariable Long id, Pageable pageable) {
        log.debug("REST request to get StockItem by Warehouse id : {}", id);
        Page<StockItemDTO> page = stockItemService.findAllByWarehouseId(id, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stock-items");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
