package india.lingayat.we.repositories;

import india.lingayat.we.models.User;
import india.lingayat.we.models.UserPersonalDetails;
import india.lingayat.we.models.enums.Gender;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserPersonalDetailsRepository extends CrudRepository<UserPersonalDetails, Long> {

    public UserPersonalDetails findByUser(User user);

    @Cacheable(value="users")
    Long countByGender(Gender gender);
}
