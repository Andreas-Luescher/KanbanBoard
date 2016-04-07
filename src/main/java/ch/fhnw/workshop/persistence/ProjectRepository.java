package ch.fhnw.workshop.persistence;

import ch.fhnw.workshop.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by roman on 10.03.16.
 */
public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findByOwner_email(String email);
    List<Project> findByMembers_email(String email);
}
