package india.lingayat.we.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import india.lingayat.we.models.enums.Qualification;

import javax.persistence.*;

@Entity
@Table(name="user_educational")
public class UserEducationalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Qualification qualification;

    private String institute;

    private String other_qualification;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userEducationalDetails")
    private User user;

    public UserEducationalDetails() {
    }

    public UserEducationalDetails(Long id, Qualification qualification, String institute, String other_qualification, User user) {
        this.id = id;
        this.qualification = qualification;
        this.institute = institute;
        this.other_qualification = other_qualification;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getOther_qualification() {
        return other_qualification;
    }

    public void setOther_qualification(String other_qualification) {
        this.other_qualification = other_qualification;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
