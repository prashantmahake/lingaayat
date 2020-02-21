package in.lingayat.we.repositories;

import in.lingayat.we.models.User;
import in.lingayat.we.models.UserEducationalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserEducationalRepository extends JpaRepository<UserEducationalDetails, Long> {
    UserEducationalDetails findByUser(User user);
}
