package in.lingayat.we.specifications;

import in.lingayat.we.models.User;
import in.lingayat.we.models.UserPersonalDetails;
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

                Subquery<User> sq = criteriaQuery.subquery(User.class);

                Root<UserPersonalDetails> personal = sq.from(UserPersonalDetails.class);

                Join<UserPersonalDetails, User> sqUser = personal.join("user");

                sq.select(sqUser).where(criteriaBuilder.between(
                        personal.get("heightInCms"), min, max));

                return criteriaBuilder.in(root).value(sq);
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

                LocalDate today = LocalDate.now();

                Subquery<User> sq = criteriaQuery.subquery(User.class);

                Root<UserPersonalDetails> personal = sq.from(UserPersonalDetails.class);

                Join<UserPersonalDetails, User> sqUser = personal.join("user");

                System.out.println(minDate);
                ParameterExpression<Date> param = criteriaBuilder.parameter(Date.class, "minDate");
                sq.select(sqUser).where(criteriaBuilder.lessThanOrEqualTo(personal.get("dob"), 786285360000L));
                return criteriaBuilder.in(root).value(sq);
            }
        };
    }
}
