package mu.mcb.juice.recruitment.repository;

import mu.mcb.juice.recruitment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Brume
 **/
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserNameOrEmail(String userName, String email);
}
