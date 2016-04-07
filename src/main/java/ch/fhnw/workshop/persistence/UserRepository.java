package ch.fhnw.workshop.persistence;

import ch.fhnw.workshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by roman on 10.03.16.
 */
public interface UserRepository extends JpaRepository<User,String> {
}
