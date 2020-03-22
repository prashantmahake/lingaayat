package in.lingayat.we.repositories;

import in.lingayat.we.models.User;
import in.lingayat.we.payload.UserMinimumProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    <T> Optional<User> findByUsernameOrEmail(String usernameOrEmail, String usernameOrEmail1);

    @Query(value = "SELECT u.id, u.first_name as firstName, u.middle_name as middleName, u.last_name as lastName,\n" +
            "\tp.gender as gender, p.marital_status as maritalStatus, p.complexion as complexion, p.dob as dob, p.height_in_cms as heightInCms, p.weight_in_kgs as weightInKgs,\n" +
            "\ti.image_url as imageUrl,\n" +
            "\te.qualification as qualification, \n" +
            "\ta.current_city as currentCity,\n" +
            "\tpr.job_role as jobRole, pr.monthly_income as monthlyIncome\n" +
            "\tFROM users u LEFT JOIN user_personal p ON u.id = p.user_id\n" +
            "\tLEFT JOIN user_images i ON u.id = i.user_id\n" +
            "\tLEFT JOIN user_educational e ON u.id = e.user_id\n" +
            "\tLEFT JOIN user_additional a ON u.id = a.user_id\n" +
            "\tLEFT JOIN user_professional pr ON u.id = pr.user_id WHERE u.id in :userIds ORDER BY u.id",
             nativeQuery = true)
    List<UserMinimumProjection> findAllUserMinimumView(List<Long> userIds);


}
