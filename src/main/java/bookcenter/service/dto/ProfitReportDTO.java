package bookcenter.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Henry Lin badcop@163.com
 */
public class ProfitReportDTO implements Serializable {

    private Long id;

    private Long bookCenterId;

    private String bookCenterName;

    private Integer year;

    private Integer month;

    private BigDecimal profit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookCenterId() {
        return bookCenterId;
    }

    public void setBookCenterId(Long bookCenterId) {
        this.bookCenterId = bookCenterId;
    }

    public String getBookCenterName() {
        return bookCenterName;
    }

    public void setBookCenterName(String bookCenterName) {
        this.bookCenterName = bookCenterName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProfitReportDTO profitReportDTO = (ProfitReportDTO) o;
        if (profitReportDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), profitReportDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProfitReportDTO{" +
            "id=" + getId() +
            ", bookCenterId=" + getBookCenterId() +
            ", year=" + getYear() +
            ", month=" + getMonth() +
            ", profit=" + getProfit() +
            "}";
    }
}
