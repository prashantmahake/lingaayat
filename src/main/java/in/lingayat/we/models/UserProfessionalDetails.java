package in.lingayat.we.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import in.lingayat.we.models.enums.JobType;

import javax.persistence.*;

@Entity
@Table(name="user_professional")
public class UserProfessionalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    private String jobRole;

    private long monthlyIncome;

    private String jobLocation;

    private String jobIndustry;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public UserProfessionalDetails() {
    }

    public UserProfessionalDetails(Long id, JobType jobType, String jobRole, long monthlyIncome, String jobLocation, String jobIndustry, User user) {
        this.id = id;
        this.jobType = jobType;
        this.jobRole = jobRole;
        this.monthlyIncome = monthlyIncome;
        this.jobLocation = jobLocation;
        this.jobIndustry = jobIndustry;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public long getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(long monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobIndustry() {
        return jobIndustry;
    }

    public void setJobIndustry(String jobIndustry) {
        this.jobIndustry = jobIndustry;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
