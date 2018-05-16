package bookcenter.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the StockItem entity.
 */
public class StockItemDTO implements Serializable {

    private Long id;

    @NotNull
    @Min(value = 0L)
    private Long quantity;

    private Long bookId;

    private Long warehouseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StockItemDTO stockItemDTO = (StockItemDTO) o;
        if(stockItemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stockItemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StockItemDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            "}";
    }
}
