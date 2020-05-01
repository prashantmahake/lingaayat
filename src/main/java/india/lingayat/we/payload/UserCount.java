package india.lingayat.we.payload;

import india.lingayat.we.models.enums.Gender;

public class UserCount {

    private Gender gender;
    private long count;

    public UserCount(Gender gender, long count) {
        this.gender = gender;
        this.count = count;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
