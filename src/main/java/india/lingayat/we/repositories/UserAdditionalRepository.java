package india.lingayat.we.repositories;

import india.lingayat.we.models.User;
import india.lingayat.we.models.UserAdditionalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserAdditionalRepository extends JpaRepository<UserAdditionalDetails, Long> {

    UserAdditionalDetails findByUser(User user);
}
