package india.lingayat.we.payload;

import india.lingayat.we.models.enums.Qualification;

import java.util.List;

public class FilterRequest {

    private int minHeight;
    private int maxHeight;
    private Long minAge;
    private Long maxAge;
    private int minSalary;
    private List<Qualification> qualification;

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
}
