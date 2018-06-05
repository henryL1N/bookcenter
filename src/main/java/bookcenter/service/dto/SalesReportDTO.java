package bookcenter.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Henry Lin badcop@163.com
 */
public class SalesReportDTO implements Serializable {

    private Long id;

    private Long bookId;

    private String bookName;

    private Long quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SalesReportDTO salesReportDTO = (SalesReportDTO) o;
        if (salesReportDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), salesReportDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SalesReportDTO{" +
            "id=" + getId() +
            ", bookId=" + getBookId() +
            ", quantity=" + getQuantity() +
            "}";
    }
}
