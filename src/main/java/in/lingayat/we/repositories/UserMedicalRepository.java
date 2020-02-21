package in.lingayat.we.repositories;

import in.lingayat.we.models.User;
import in.lingayat.we.models.UserMedicalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMedicalRepository extends JpaRepository<UserMedicalDetails, Long> {

    UserMedicalDetails findByUser(User user);
}
