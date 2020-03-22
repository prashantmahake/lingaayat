package india.lingayat.we.repositories;

import india.lingayat.we.models.User;
import india.lingayat.we.models.UserEducationalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserEducationalRepository extends JpaRepository<UserEducationalDetails, Long> {
    UserEducationalDetails findByUser(User user);
}
