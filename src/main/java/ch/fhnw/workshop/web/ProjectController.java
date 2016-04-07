package ch.fhnw.workshop.web;

import ch.fhnw.workshop.domain.Project;
import ch.fhnw.workshop.persistence.ProjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roman on 10.03.16.
 */
@RestController
@RequestMapping("/api/projects")
public class ProjectController extends BaseController<Project, ProjectRepository> {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Project>> findProjectWhereUserIsMember() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Project> list = new ArrayList<>();
        if(user != null){
           list = baseRepository.findByMembers_email(user.getUsername());
            log.debug("Found " + list.size() + " projects where the User '" + user.getUsername() + "' is member");
            return new ResponseEntity<List<Project>>(list, HttpStatus.OK);
        }
        return new ResponseEntity<List<Project>>(list, HttpStatus.OK);
    }


}
