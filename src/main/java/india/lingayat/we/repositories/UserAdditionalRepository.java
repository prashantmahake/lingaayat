package in.lingayat.we.repositories;

import in.lingayat.we.models.User;
import in.lingayat.we.models.UserAdditionalDetails;
import in.lingayat.we.models.UserEducationalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserAdditionalRepository extends JpaRepository<UserAdditionalDetails, Long> {

    UserAdditionalDetails findByUser(User user);
}
