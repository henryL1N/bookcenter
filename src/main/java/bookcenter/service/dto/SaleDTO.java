package bookcenter.service.dto;


import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @author Henry Lin badcop@163.com
 */
public class SaleDTO extends SalesOrderDTO implements Serializable{

    private Set<OrderItemDTO> orderItems;

    public Set<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItemDTO> orderItemDTOs) {
        this.orderItems = orderItemDTOs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SaleDTO saleDTO = (SaleDTO) o;
        if(saleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), saleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SaleDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", customer='" + getCustomer() + "'" +
            ", totalAmount=" + getTotalAmount() +
            "}";
    }
}
