package in.lingayat.we.payload;

import in.lingayat.we.models.enums.Complexion;
import in.lingayat.we.models.enums.Gender;
import in.lingayat.we.models.enums.MaritalStatus;

import java.sql.Date;

public interface UserMinimumProjection {
    Long getId();

    public String getFirstName() ;

    String getMiddleName() ;

    String getLastName() ;

    Gender getGender();

    MaritalStatus getMaritalStatus() ;

    Complexion getComplexion() ;

    String getImageUrl() ;

    Date getDob() ;

    Integer getHeightInCms();

    Integer getWeightInKgs();

    String getCurrentCity() ;

    Long getMonthlyIncome();

    String getJobRole() ;

    String getQualification() ;
}
