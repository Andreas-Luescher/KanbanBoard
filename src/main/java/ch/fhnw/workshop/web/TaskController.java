package ch.fhnw.workshop.web;

import ch.fhnw.workshop.domain.Project;
import ch.fhnw.workshop.domain.Task;
import ch.fhnw.workshop.domain.dto.TaskDTO;
import ch.fhnw.workshop.persistence.ProjectRepository;
import ch.fhnw.workshop.persistence.TaskRepository;
import ch.fhnw.workshop.persistence.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by roman on 10.03.16.
 */
@RestController
@RequestMapping("/api/tasks")
@EnableGlobalAuthentication
public class TaskController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Task>> findCreator() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Task> list = taskRepository.findByCreator_email(user.getUsername());
        log.debug("Found " + list.size() + " tasks where user '" + user.getUsername() + "' is creator.");
        return new ResponseEntity<List<Task>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/responsible", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> findResponsible() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Task> list = taskRepository.findByResponsibleUser_email(user.getUsername());
        log.debug("Found " + list.size() + " tasks where user '" + user.getUsername() + "' is responsible for.");
        return new ResponseEntity<List<Task>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/{projectId}", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> findAll(@PathVariable Long projectId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Task> list = taskRepository.findByProject_idAndProject_Members_email(projectId, user.getUsername());
        log.debug("Found " + list.size() + " tasks in the Project '" + projectId + "' where the user '" + user.getUsername() + "' is member");
        return new ResponseEntity<List<Task>>(list, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Task> create(@Valid @RequestBody TaskDTO taskDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user != null) {
            Project project = projectRepository.findOne(taskDTO.getProjectId());
            ch.fhnw.workshop.domain.User creator = userRepository.getOne(user.getUsername());
            ch.fhnw.workshop.domain.User responsible = userRepository.getOne(taskDTO.getResponsibleUserEmail());
            if(project != null){
                boolean isUserMember = project.getMembers().stream().filter(o -> o.getEmail().equals(user.getUsername())).findFirst().isPresent();
                if (creator != null && !taskDTO.getTitle().equals("") && user != null && isUserMember){
                    Task task = new Task();

                    task.setTitle(taskDTO.getTitle());
                    task.setAttachements(taskDTO.getAttachements());
                    task.setCreatedAt(new Date());
                    task.setAttachements(taskDTO.getAttachements());
                    task.setDescription(taskDTO.getDescription());
                    task.setDeadline(taskDTO.getDeadline());
                    task.setCreator(creator);
                    task.setResponsibleUser(responsible);
                    task.setState(taskDTO.getState());
                    task.setProject(project);

                    task = taskRepository.save(task);
                    log.debug("Created new task with id " + task.getId());
                    return new ResponseEntity<Task>(task, HttpStatus.CREATED);
                }
            }
        }
        log.debug("Created entity");
        return new ResponseEntity<Task>(new Task(), HttpStatus.CONFLICT);
    }


}
