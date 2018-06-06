package bookcenter.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Henry Lin badcop@163.com
 */
public class ExpenseReportDTO implements Serializable {

    private Long id;

    private Long departmentId;

    private String departmentName;

    private BigDecimal expense;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExpenseReportDTO expenseReportDTO = (ExpenseReportDTO) o;
        if (expenseReportDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), expenseReportDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ExpenseReportDTO{" +
            "id=" + getId() +
            ", departmentId=" + getDepartmentId() +
            ", expense=" + getExpense() +
            "}";
    }
}
