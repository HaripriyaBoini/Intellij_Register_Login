package repository;

import model.User_Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User_Model,Integer> {
            Optional<User_Model> findByLoginAndPassword(String login, String password);
    Optional<User_Model> findFirstByLogin(String login);
}

