package ch.fhnw.workshop.persistence;

import ch.fhnw.workshop.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by roman on 10.03.16.
 */
public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByCreator_email(String email);
    //Gets all tasks from Project with ProjectID, if email is Member of Project
    List<Task> findByProject_idAndProject_Members_email(long Projectid, String email);
    List<Task> findByResponsibleUser_email(String email);

}
