package bookcenter.web.rest;

import com.codahale.metrics.annotation.Timed;
import bookcenter.domain.StockItem;
import bookcenter.service.StockItemService;
import bookcenter.web.rest.errors.BadRequestAlertException;
import bookcenter.web.rest.util.HeaderUtil;
import bookcenter.web.rest.util.PaginationUtil;
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
     * @param stockItem the stockItem to create
     * @return the ResponseEntity with status 201 (Created) and with body the new stockItem, or with status 400 (Bad Request) if the stockItem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/stock-items")
    @Timed
    public ResponseEntity<StockItem> createStockItem(@Valid @RequestBody StockItem stockItem) throws URISyntaxException {
        log.debug("REST request to save StockItem : {}", stockItem);
        if (stockItem.getId() != null) {
            throw new BadRequestAlertException("A new stockItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StockItem result = stockItemService.save(stockItem);
        return ResponseEntity.created(new URI("/api/stock-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /stock-items : Updates an existing stockItem.
     *
     * @param stockItem the stockItem to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated stockItem,
     * or with status 400 (Bad Request) if the stockItem is not valid,
     * or with status 500 (Internal Server Error) if the stockItem couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/stock-items")
    @Timed
    public ResponseEntity<StockItem> updateStockItem(@Valid @RequestBody StockItem stockItem) throws URISyntaxException {
        log.debug("REST request to update StockItem : {}", stockItem);
        if (stockItem.getId() == null) {
            return createStockItem(stockItem);
        }
        StockItem result = stockItemService.save(stockItem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stockItem.getId().toString()))
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
    public ResponseEntity<List<StockItem>> getAllStockItems(Pageable pageable) {
        log.debug("REST request to get a page of StockItems");
        Page<StockItem> page = stockItemService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stock-items");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /stock-items/:id : get the "id" stockItem.
     *
     * @param id the id of the stockItem to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stockItem, or with status 404 (Not Found)
     */
    @GetMapping("/stock-items/{id}")
    @Timed
    public ResponseEntity<StockItem> getStockItem(@PathVariable Long id) {
        log.debug("REST request to get StockItem : {}", id);
        StockItem stockItem = stockItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(stockItem));
    }

    /**
     * DELETE  /stock-items/:id : delete the "id" stockItem.
     *
     * @param id the id of the stockItem to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/stock-items/{id}")
    @Timed
    public ResponseEntity<Void> deleteStockItem(@PathVariable Long id) {
        log.debug("REST request to delete StockItem : {}", id);
        stockItemService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
