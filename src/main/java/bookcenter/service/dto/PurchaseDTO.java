package bookcenter.service.dto;


import bookcenter.domain.OrderItem;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @author Henry Lin badcop@163.com
 */
public class PurchaseDTO extends PurchaseOrderDTO implements Serializable{

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

        PurchaseDTO purchaseDTO = (PurchaseDTO) o;
        if(purchaseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), purchaseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PurchaseDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", supplier='" + getSupplier() + "'" +
            ", totalAmount=" + getTotalAmount() +
            "}";
    }
}
