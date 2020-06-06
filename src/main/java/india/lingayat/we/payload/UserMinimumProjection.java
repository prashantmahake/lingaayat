package india.lingayat.we.payload;

import india.lingayat.we.models.enums.Complexion;
import india.lingayat.we.models.enums.Gender;
import india.lingayat.we.models.enums.MaritalStatus;

import java.sql.Date;

public interface UserMinimumProjection {
    Long getId();

    public String getFirstName() ;

    String getMiddleName() ;

    String getLastName() ;

    PersonalDetails getUserPersonalDetails();

    ProfessionalDetails getUserProfessionalDetails();

    EducationalDetails getUserEducationalDetails();

    AdditionalDetails getUserAdditionalDetails();

    UserImagesDetails getUserImages();


    interface PersonalDetails{
        Date getDob() ;

        Integer getHeightInCms();

        Integer getWeightInKgs();

        Complexion getComplexion() ;

        Gender getGender();

        MaritalStatus getMaritalStatus() ;

    }

    interface ProfessionalDetails{
        Long getMonthlyIncome();

        String getJobRole() ;
    }

    interface EducationalDetails{
        String getQualification() ;
    }

    interface AdditionalDetails{
        String getCurrentCity() ;
    }

    interface UserImagesDetails{
        String getImageUrl() ;
    }
}
