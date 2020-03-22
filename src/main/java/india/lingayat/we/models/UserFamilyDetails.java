package in.lingayat.we.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.lingayat.we.models.enums.JobType;
import in.lingayat.we.models.enums.Relation;

import javax.persistence.*;

@Entity
@Table(name="user_family")
public class UserFamilyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String firstName;

    private String middleName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Relation relation;

    private boolean isMarried;

    @Enumerated(EnumType.STRING)
    private JobType profession;

    private String additionalDescription;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public UserFamilyDetails() {
    }

    public UserFamilyDetails(Long id, String title, String firstName, String middleName, String lastName, Relation relation, boolean isMarried, JobType profession, String additionalDescription, User user) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.relation = relation;
        this.isMarried = isMarried;
        this.profession = profession;
        this.additionalDescription = additionalDescription;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public JobType getProfession() {
        return profession;
    }

    public void setProfession(JobType profession) {
        this.profession = profession;
    }

    public String getAdditionalDescription() {
        return additionalDescription;
    }

    public void setAdditionalDescription(String additionalDescription) {
        this.additionalDescription = additionalDescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
