package india.lingayat.we.controllers;

import com.querydsl.core.BooleanBuilder;
import india.lingayat.we.models.*;
import india.lingayat.we.models.UserPersonalDetails_;
import india.lingayat.we.models.User_;
import india.lingayat.we.payload.FilterRequest;
import india.lingayat.we.payload.UserMinimumProjection;
import india.lingayat.we.repositories.*;
import india.lingayat.we.repositories.UserRepository;
import india.lingayat.we.specifications.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    EntityManager em;


    @PostMapping("/getAllUsers")
    @PreAuthorize("hasRole('USER')")
    public Page<User> getAllUsers(Pageable pageable, @CurrentUser UserPrincipal currentUser, @RequestBody FilterRequest filterRequest) {

        Specification<User> specification = Specification.where(UserSpecification.alwaysTrue());

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.and(QUser.user.id.ne(currentUser.getId()));

        if (filterRequest.getMaxHeight() > 0) {

            booleanBuilder.and(
                    QUser.user.userPersonalDetails.heightInCms.between(
                            filterRequest.getMinHeight(), filterRequest.getMaxHeight()
                    )
            );
        }

        if (filterRequest.getMaxAge() > 0) {

            java.util.Date maxDateInMillis = new java.util.Date(System.currentTimeMillis() - (filterRequest.getMaxAge() * 1000L * 60L * 60L
                    * 24L * 365L));
            java.util.Date minDateInMillis = new java.util.Date(System.currentTimeMillis() - (filterRequest.getMinAge() * 1000L * 60L * 60L
                    * 24L * 365L));

            Date minDate = new Date(minDateInMillis.getTime());
            Date maxDate = new Date(maxDateInMillis.getTime());

            booleanBuilder.and(
                    QUser.user.userPersonalDetails.dob.between(
                            maxDate, minDate)
                    );

        }

        if(filterRequest.getMinSalary() > 0){
            booleanBuilder.and(
              QUser.user.userProfessionalDetails.monthlyIncome.goe(filterRequest.getMinSalary())
            );
        }

        if(filterRequest.getQualification() != null && filterRequest.getQualification().size() !=0){
            booleanBuilder.and(
                    QUser.user.userEducationalDetails.qualification.in(filterRequest.getQualification())
            );
        }

        Page<User> resultList = userRepository.findAll(booleanBuilder.getValue(), pageable);

        return resultList;

    }
}
