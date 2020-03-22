package india.lingayat.we.repositories;

import india.lingayat.we.models.User;
import india.lingayat.we.models.UserPersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPersonalDetailsRepository extends JpaRepository<UserPersonalDetails, Long> {

    public UserPersonalDetails findByUser(User user);

}
