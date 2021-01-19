package com.vakcinainfo.vakcina.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
public class VaccinationPersonalData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String city;
    private Integer postalcode;
    private String name;
    private String phoneNumber;
    private String email;
    private Integer socialInsuranceNumber;
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:sss")
    private Date createdAt = new Date();

    public VaccinationPersonalData() {

    }

    public VaccinationPersonalData(UUID id, String name, String city, Integer postalcode, String phoneNumber, String email, Integer socialInsuranceNumber) {
        this.id = id;
        this.city = city;
        this.postalcode = postalcode;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.socialInsuranceNumber = socialInsuranceNumber;
        this.createdAt = new Date();
    }

    public VaccinationPersonalData(String name, String city, Integer postalcode, String phoneNumber, String email, Integer socialInsuranceNumber) {
        this.id = UUID.randomUUID();
        this.city = city;
        this.postalcode = postalcode;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.socialInsuranceNumber = socialInsuranceNumber;
        this.createdAt = new Date();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(Integer postalcode) {
        this.postalcode = postalcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSocialInsuranceNumber() {
        return socialInsuranceNumber;
    }

    public void setSocialInsuranceNumber(Integer socialInsuranceNumber) {
        this.socialInsuranceNumber = socialInsuranceNumber;
    }

    @Override
    public String toString() {
        return "VaccinationPersonalData{" +
                ", name='" + name + '\'' +
                "id=" + id +
                ", city='" + city + '\'' +
                ", postalcode=" + postalcode +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", socialInsuranceNumber=" + socialInsuranceNumber +
                ", createdAt=" + createdAt +
                '}';
    }
}