package in.lingayat.we.controllers;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import in.lingayat.we.models.*;
import in.lingayat.we.models.QUser;
import in.lingayat.we.models.QUserPersonalDetails;
import in.lingayat.we.models.UserPersonalDetails_;
import in.lingayat.we.models.User_;
import in.lingayat.we.models.enums.Qualification;
import in.lingayat.we.payload.FilterRequest;
import in.lingayat.we.payload.UserMinimumProjection;
import in.lingayat.we.payload.UserMinimumView;
import in.lingayat.we.repositories.*;
import in.lingayat.we.specifications.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.ws.rs.PathParam;
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
    public List<UserMinimumProjection> getAllUsers(Pageable pageable, @CurrentUser UserPrincipal currentUser, @RequestBody FilterRequest filterRequest) {


        Specification<User> specification = null;

        if (filterRequest.getMaxHeight() != 0) {

            if(specification==null){
                specification =  UserSpecification.hasHeightBetween(filterRequest.getMinHeight(), filterRequest.getMaxHeight());
            }
            else
            {
                specification = specification.and(
                        UserSpecification.hasHeightBetween(filterRequest.getMinHeight(), filterRequest.getMaxHeight())
                );
            }


        }

        if (filterRequest.getMaxAge() != 0) {
            specification = specification.and(
                    UserSpecification.hasAgeBetween(filterRequest.getMinAge(), filterRequest.getMaxAge())
            );
        }

//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//
//        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
//        Root<User> userRoot = query.from(User.class);
//
//        Subquery<User> sq = query.subquery(User.class);
//
//        Root<UserPersonalDetails> personal = sq.from(UserPersonalDetails.class);
//
//        Join<UserPersonalDetails, User> sqUser = personal.join("user");
//
//        sq.select(sqUser).where(criteriaBuilder.greaterThanOrEqualTo(personal.get("dob"), criteriaBuilder.currentDate()));
//
//        query.select(userRoot).where(criteriaBuilder.in(userRoot.get("id")).value(sq));
//
//        List<User> users = em.createQuery(query).getResultList();

        Page<User> result = userRepository.findAll(
                pageable);
        List<Long> userIds = new ArrayList<>();
        for (User u : result.getContent()) {
            userIds.add(u.getId());
        }


        return userRepository.findAllUserMinimumView(userIds);

    }


    @PostMapping("/getAllUsers1")
    @PreAuthorize("hasRole('USER')")
    public List<UserMinimumProjection> getAllUsers2(Pageable pageable, @RequestBody FilterRequest filterRequest){

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery(User.class);

        Root<UserPersonalDetails> personal = query.from(UserPersonalDetails.class);
        Join<UserPersonalDetails, User> user = personal.join("user");

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.greaterThanOrEqualTo(personal.get("heightInCms"),filterRequest.getMinHeight()));

        query.multiselect(user.get(User_.firstName),
                personal.get("dob"));
        query.where(predicates.stream().toArray(Predicate[]::new));

        TypedQuery<UserMinimumProjection> typedQuery = em.createQuery(query);

        List<UserMinimumProjection> resultList = typedQuery.getResultList();

        return null;

    }

}
