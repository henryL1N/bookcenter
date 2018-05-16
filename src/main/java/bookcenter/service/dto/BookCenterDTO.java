package bookcenter.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the BookCenter entity.
 */
public class BookCenterDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String address;

    private Long generalManagerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getGeneralManagerId() {
        return generalManagerId;
    }

    public void setGeneralManagerId(Long employeeId) {
        this.generalManagerId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BookCenterDTO bookCenterDTO = (BookCenterDTO) o;
        if(bookCenterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bookCenterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BookCenterDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
