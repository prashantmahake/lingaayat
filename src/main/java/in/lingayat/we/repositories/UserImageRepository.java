package in.lingayat.we.repositories;

import in.lingayat.we.models.User;
import in.lingayat.we.models.UserImages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserImageRepository extends JpaRepository<UserImages, Long> {

    UserImages findByUser(User user);
}
