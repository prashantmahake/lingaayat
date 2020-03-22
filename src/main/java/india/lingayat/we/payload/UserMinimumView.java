package india.lingayat.we.payload;

import india.lingayat.we.models.enums.Complexion;
import india.lingayat.we.models.enums.Gender;
import india.lingayat.we.models.enums.MaritalStatus;

import java.sql.Date;

public class UserMinimumView {

    private Long id;

    private String firstName;

    private String middleName;

    private String lastName;

    private Gender gender;

    private MaritalStatus maritalStatus;

    private Complexion complexion;

    private String imageUrl;

    private Date dob;

    private int heightInCms;

    private int weightInKgs;

    private String currentCity;

    private long monthlyIncome;

    private String jobRole;

    private String qualification;

    public UserMinimumView(Long id, String firstName, String middleName, String lastName, Gender gender, MaritalStatus maritalStatus, Complexion complexion, String imageUrl, Date dob, int heightInCms, int weightInKgs, String currentCity, long monthlyIncome, String jobRole, String qualification) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.complexion = complexion;
        this.imageUrl = imageUrl;
        this.dob = dob;
        this.heightInCms = heightInCms;
        this.weightInKgs = weightInKgs;
        this.currentCity = currentCity;
        this.monthlyIncome = monthlyIncome;
        this.jobRole = jobRole;
        this.qualification = qualification;
    }

    public UserMinimumView() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Complexion getComplexion() {
        return complexion;
    }

    public void setComplexion(Complexion complexion) {
        this.complexion = complexion;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
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

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public Long getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(long monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
