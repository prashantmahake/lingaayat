package in.lingayat.we.repositories;

import in.lingayat.we.models.User;
import in.lingayat.we.models.UserPersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPersonalDetailsRepository extends JpaRepository<UserPersonalDetails, Long> {

    public UserPersonalDetails findByUser(User user);

}
