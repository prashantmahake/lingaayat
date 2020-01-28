package in.lingayat.we.repositories;

import in.lingayat.we.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

    

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    <T> Optional<User> findByUsernameOrEmail(String usernameOrEmail, String usernameOrEmail1);
}
