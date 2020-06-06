package india.lingayat.we.repositories;

import india.lingayat.we.models.User;
import india.lingayat.we.models.UserMedicalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMedicalRepository extends JpaRepository<UserMedicalDetails, Long> {

    UserMedicalDetails findByUser(User user);
}
