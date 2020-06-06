package india.lingayat.we.repositories;

import india.lingayat.we.models.User;
import india.lingayat.we.models.UserProfessionalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserProfessionalDetailsRepository extends JpaRepository<UserProfessionalDetails, Long> {
    UserProfessionalDetails findByUser(User user);
}
