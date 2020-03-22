package in.lingayat.we.payload;

public class FilterRequest {

    private int minHeight;
    private int maxHeight;
    private Long minAge;
    private Long maxAge;

    public FilterRequest() {
    }

    public FilterRequest(int minHeight, int maxHeight, Long minAge, Long maxAge) {
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public Long getMinAge() {
        return minAge;
    }

    public void setMinAge(Long minAge) {
        this.minAge = minAge;
    }

    public Long getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Long maxAge) {
        this.maxAge = maxAge;
    }
}
