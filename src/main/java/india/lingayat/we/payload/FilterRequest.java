package india.lingayat.we.payload;

import india.lingayat.we.models.enums.JobType;
import india.lingayat.we.models.enums.Qualification;

import java.util.List;

public class FilterRequest {

    private int minHeight;
    private int maxHeight;
    private Long minAge;
    private Long maxAge;
    private int minSalary;
    private List<Qualification> qualification;
    private String firstName;
    private String lastName;
    private List<JobType> jobType;
    private String cityNameOrPin;

    public FilterRequest() {
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public Long getMinAge() {
        return minAge;
    }

    public Long getMaxAge() {
        return maxAge;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public List<Qualification> getQualification() {
        return qualification;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<JobType> getJobType() {
        return jobType;
    }

    public String getCityNameOrPin() {
        return cityNameOrPin;
    }
}
