package ex1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ex1.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUsername(String username);

    User findFirstByFullNameContaining(String name);

    @Query("SELECT u from User u where u.username = :username AND u.userType = :type")
    List<User> filterByType(String username, String type);
}
