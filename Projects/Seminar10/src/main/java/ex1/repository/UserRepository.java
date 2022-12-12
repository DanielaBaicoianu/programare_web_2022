package ex1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ex1.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByFullNameContains(String fullName);

    @Query("SELECT u FROM User u where u.username = :username and u.userType = :type")
    List<User> findUserByUsernameAndType(String username, String type);

    List<User> findByUsername(String username);
}
