package india.lingayat.we.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import india.lingayat.we.models.enums.BloodGroup;

import javax.persistence.*;

@Entity
@Table(name="user_medical")
public class UserMedicalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    private boolean isDisabled;

    private String typeOfDisability;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userMedicalDetails")
    private User user;

    public UserMedicalDetails() {
    }

    public UserMedicalDetails(Long id, BloodGroup bloodGroup, boolean isDisabled, String typeOfDisability, User user) {
        this.id = id;
        this.bloodGroup = bloodGroup;
        this.isDisabled = isDisabled;
        this.typeOfDisability = typeOfDisability;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public String getTypeOfDisability() {
        return typeOfDisability;
    }

    public void setTypeOfDisability(String typeOfDisability) {
        this.typeOfDisability = typeOfDisability;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
