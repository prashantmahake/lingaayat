package india.lingayat.we.specifications;

import india.lingayat.we.models.User;
import india.lingayat.we.models.UserPersonalDetails;
import india.lingayat.we.models.UserPersonalDetails_;
import india.lingayat.we.models.User_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.sql.Date;
import java.time.LocalDate;

public class UserSpecification {

    public static Specification<User> alwaysTrue() {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.in(root);
            }
        };
    }

    public static Specification<User> hasHeightBetween(int min, int max) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Join<User, UserPersonalDetails> personalJoin = root.join(User_.USER_PERSONAL_DETAILS);
                Predicate heightBetweenPredicate = criteriaBuilder.between(personalJoin.get(UserPersonalDetails_.HEIGHT_IN_CMS),min, max);

                return heightBetweenPredicate;
            }
        };
    }

    public static Specification<User> hasAgeBetween(Long min, Long max) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                java.util.Date maxDateInMillis = new java.util.Date(System.currentTimeMillis() - (max * 1000L * 60L * 60L
                        * 24L * 365L));
                java.util.Date minDateInMillis = new java.util.Date(System.currentTimeMillis() - (min * 1000L * 60L * 60L
                        * 24L * 365L));

                Date minDate = new Date(minDateInMillis.getTime());
                Date maxDate = new Date(maxDateInMillis.getTime());

                Join<User, UserPersonalDetails> personalJoin = root.join(User_.USER_PERSONAL_DETAILS);
                Predicate ageBetweenPredicate = criteriaBuilder.between(personalJoin.get(UserPersonalDetails_.DOB),maxDate, minDate);

                return ageBetweenPredicate;
            }
        };
    }
}
