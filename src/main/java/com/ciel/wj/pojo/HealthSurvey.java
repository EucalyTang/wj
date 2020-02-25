package com.ciel.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "health")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class HealthSurvey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String flight;
    Date flightDate;
    String departureCity;
    String arrivalCity;
    Date inputDate;
    String name;
    String passportType;
    String IdNumber;
    String sex;
    Date birthDate;
    String seatNumber;
    String address1;
    String address2;
    String mobilePhone;
    boolean question1;
    boolean question2;
    boolean question3;

    public void setId(int id) {
        this.id = id;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassportType(String passportType) {
        this.passportType = passportType;
    }

    public void setIdNumber(String IdNumber) {
        this.IdNumber = IdNumber;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void setQuestion1(boolean question1) {
        this.question1 = question1;
    }

    public void setQuestion2(boolean question2) {
        this.question2 = question2;
    }

    public void setQuestion3(boolean question3) {
        this.question3 = question3;
    }

    public int getId() {
        return id;
    }

    public String getFlight() {
        return flight;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public String getName() {
        return name;
    }

    public String getPassportType() {
        return passportType;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public String getSex() {
        return sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public boolean isQuestion1() {
        return question1;
    }

    public boolean isQuestion2() {
        return question2;
    }

    public boolean isQuestion3() {
        return question3;
    }
}
