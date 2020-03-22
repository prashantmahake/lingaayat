package in.lingayat.we.repositories;

import in.lingayat.we.models.User;
import in.lingayat.we.models.UserFamilyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserFamilyRepository extends JpaRepository<UserFamilyDetails, Long> {

    List<UserFamilyDetails> findByUser(User user);

    UserFamilyDetails findByUserAndFirstNameAndLastName(User user, String firstName, String lastName);
}
