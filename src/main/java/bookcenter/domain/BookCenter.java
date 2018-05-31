package bookcenter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A BookCenter.
 */
@Entity
@Table(name = "book_center")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BookCenter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private Employee generalManager;

    @OneToMany(mappedBy = "bookCenter")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Department> departments = new HashSet<>();

    @OneToMany(mappedBy = "bookCenter")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Employee> employees = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public BookCenter name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public BookCenter address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Employee getGeneralManager() {
        return generalManager;
    }

    public BookCenter generalManager(Employee employee) {
        this.generalManager = employee;
        return this;
    }

    public void setGeneralManager(Employee employee) {
        this.generalManager = employee;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public BookCenter departments(Set<Department> departments) {
        this.departments = departments;
        return this;
    }

    public BookCenter addDepartment(Department department) {
        this.departments.add(department);
        department.setBookCenter(this);
        return this;
    }

    public BookCenter removeDepartment(Department department) {
        this.departments.remove(department);
        department.setBookCenter(null);
        return this;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public BookCenter employees(Set<Employee> employees) {
        this.employees = employees;
        return this;
    }

    public BookCenter addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.setBookCenter(this);
        return this;
    }

    public BookCenter removeEmployee(Employee employee) {
        this.employees.remove(employee);
        employee.setBookCenter(null);
        return this;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BookCenter bookCenter = (BookCenter) o;
        if (bookCenter.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bookCenter.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BookCenter{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
