package india.lingayat.we.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import india.lingayat.we.models.enums.Complexion;
import india.lingayat.we.models.enums.FamilyType;
import india.lingayat.we.models.enums.Gender;
import india.lingayat.we.models.enums.MaritalStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Entity
@Table(name="user_personal")
public class UserPersonalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dob;

    @NotBlank
    private String placeOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer heightInCms;

    private Integer weightInKgs;

    @Enumerated(EnumType.STRING)
    private Complexion complexion;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    @Enumerated(EnumType.STRING)
    private FamilyType familyType;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userPersonalDetails")
    private User user;

    public UserPersonalDetails() {
    }


    public UserPersonalDetails(Long id, Date dob, @NotBlank String placeOfBirth, Gender gender, Integer heightInCms, Integer weightInKgs, Complexion complexion, MaritalStatus maritalStatus, FamilyType familyType, User user) {
        this.id = id;
        this.dob = dob;
        this.placeOfBirth = placeOfBirth;
        this.gender = gender;
        this.heightInCms = heightInCms;
        this.weightInKgs = weightInKgs;
        this.complexion = complexion;
        this.maritalStatus = maritalStatus;
        this.familyType = familyType;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getHeightInCms() {
        return heightInCms;
    }

    public void setHeightInCms(int heightInCms) {
        this.heightInCms = heightInCms;
    }

    public int getWeightInKgs() {
        return weightInKgs;
    }

    public void setWeightInKgs(int weightInKgs) {
        this.weightInKgs = weightInKgs;
    }

    public Complexion getComplexion() {
        return complexion;
    }

    public void setComplexion(Complexion complexion) {
        this.complexion = complexion;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setHeightInCms(Integer heightInCms) {
        this.heightInCms = heightInCms;
    }

    public void setWeightInKgs(Integer weightInKgs) {
        this.weightInKgs = weightInKgs;
    }

    public FamilyType getFamilyType() {
        return familyType;
    }

    public void setFamilyType(FamilyType familyType) {
        this.familyType = familyType;
    }
}