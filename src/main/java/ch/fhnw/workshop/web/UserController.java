package ch.fhnw.workshop.web;


import ch.fhnw.workshop.domain.dto.UserDTO;
import ch.fhnw.workshop.domain.User;
import ch.fhnw.workshop.persistence.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by roman on 10.03.16.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserRepository userRepository;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<User> getUser() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user != null){
            User entity = userRepository.findOne(user.getUsername());
            if(entity != null){
                log.debug("Found user with EMAIL " + entity.getEmail());
                return new ResponseEntity<User>(entity, HttpStatus.OK);
            }
        }
        log.debug("User not found");
        return new ResponseEntity<User>(new User(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<User> update(@Valid @RequestBody UserDTO updateUser) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user != null && updateUser.getEmail() != null && updateUser.getEmail().equals(user.getUsername())){
            User entity = userRepository.findOne(user.getUsername());
            if(entity != null){
                entity.setUsername(updateUser.getUsername());
                entity.setPasswordhash(updateUser.getUsername());
                entity = userRepository.saveAndFlush(entity);
                log.debug("User updated: " + entity.getEmail());
                return new ResponseEntity<User>(entity, HttpStatus.OK);
            }
        }
        return new ResponseEntity<User>(new User(), HttpStatus.CONFLICT);
    }


}
