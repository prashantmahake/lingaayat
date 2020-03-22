package in.lingayat.we.repositories;

import in.lingayat.we.models.User;
import in.lingayat.we.models.UserProfessionalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserProfessionalDetailsRepository extends JpaRepository<UserProfessionalDetails, Long> {
    UserProfessionalDetails findByUser(User user);
}
