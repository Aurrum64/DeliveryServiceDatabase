package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Courier_ID")
    private Integer courierId;

    @Column(name = "Department_ID")
    private Integer departmentId;

    @Column(name = "First_Name")
    private String firstName;

    @Column(name = "Last_Name")
    private String lastName;

    @Column(name = "E-Mail")
    private String email;

    @Column(name = "Phone_number")
    private Integer phoneNumber;

    @Column(name = "Rating")
    private Integer rating;

    @Column(name = "Salary")
    private Integer salary;

    @Temporal(TemporalType.DATE)
    @Column(name = "Hire_Date")
    private Date hireDate;

    @Column(name = "Premium")
    private Double premium;

    public Integer getCourierId() {
        return courierId;
    }

    public void setCourierId(Integer couriersId) {
        this.courierId = couriersId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String secondName) {
        this.lastName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Double getPremium() {
        return premium;
    }

    public void setPremium(Double premium) {
        this.premium = premium;
    }
}
