package india.lingayat.we.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="user_additional")
public class UserAdditionalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int currentPinCode;

    private int permanentPinCode;

    private String currentAddressLine1;

    private String currentAddressLine2;

    private String currentCity;

    private String permanentAddressLine1;

    private String permanentAddressLine2;

    private String permanentCity;

    private String additionalDetails1;

    private String additionalDetails2;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userAdditionalDetails")
    private User user;

    public UserAdditionalDetails() {
    }

    public UserAdditionalDetails(Long id, int currentPinCode, int permanentPinCode, String currentAddressLine1, String currentAddressLine2, String currentCity, String permanentAddressLine1, String permanentAddressLine2, String permanentCity, String additionalDetails1, String additionalDetails2, User user) {
        this.id = id;
        this.currentPinCode = currentPinCode;
        this.permanentPinCode = permanentPinCode;
        this.currentAddressLine1 = currentAddressLine1;
        this.currentAddressLine2 = currentAddressLine2;
        this.currentCity = currentCity;
        this.permanentAddressLine1 = permanentAddressLine1;
        this.permanentAddressLine2 = permanentAddressLine2;
        this.permanentCity = permanentCity;
        this.additionalDetails1 = additionalDetails1;
        this.additionalDetails2 = additionalDetails2;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCurrentPinCode() {
        return currentPinCode;
    }

    public void setCurrentPinCode(int currentPinCode) {
        this.currentPinCode = currentPinCode;
    }

    public int getPermanentPinCode() {
        return permanentPinCode;
    }

    public void setPermanentPinCode(int permanentPinCode) {
        this.permanentPinCode = permanentPinCode;
    }

    public String getCurrentAddressLine1() {
        return currentAddressLine1;
    }

    public void setCurrentAddressLine1(String currentAddressLine1) {
        this.currentAddressLine1 = currentAddressLine1;
    }

    public String getCurrentAddressLine2() {
        return currentAddressLine2;
    }

    public void setCurrentAddressLine2(String currentAddressLine2) {
        this.currentAddressLine2 = currentAddressLine2;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getPermanentAddressLine1() {
        return permanentAddressLine1;
    }

    public void setPermanentAddressLine1(String permanentAddressLine1) {
        this.permanentAddressLine1 = permanentAddressLine1;
    }

    public String getPermanentAddressLine2() {
        return permanentAddressLine2;
    }

    public void setPermanentAddressLine2(String permanentAddressLine2) {
        this.permanentAddressLine2 = permanentAddressLine2;
    }

    public String getPermanentCity() {
        return permanentCity;
    }

    public void setPermanentCity(String permanentCity) {
        this.permanentCity = permanentCity;
    }

    public String getAdditionalDetails1() {
        return additionalDetails1;
    }

    public void setAdditionalDetails1(String additionalDetails1) {
        this.additionalDetails1 = additionalDetails1;
    }

    public String getAdditionalDetails2() {
        return additionalDetails2;
    }

    public void setAdditionalDetails2(String additionalDetails2) {
        this.additionalDetails2 = additionalDetails2;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
