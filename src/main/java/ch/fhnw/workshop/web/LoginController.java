package ch.fhnw.workshop.web;

import ch.fhnw.workshop.authentication.LoginService;
import ch.fhnw.workshop.authentication.LoginStatus;
import ch.fhnw.workshop.domain.dto.UserDTO;
import ch.fhnw.workshop.domain.User;
import ch.fhnw.workshop.persistence.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by roman on 29.03.16.
 */
@RestController

public class LoginController {
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<LoginStatus> getStatus() {
        return new ResponseEntity<LoginStatus>( loginService.getStatus(), HttpStatus.OK);

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<LoginStatus> login(@RequestParam("username") String username,
                                   @RequestParam("password") String password) {
        LoginStatus loginStatus = loginService.login(username, password);
        if(loginStatus.isLoggedIn())
            return new ResponseEntity<LoginStatus>(loginStatus, HttpStatus.OK);
        return new ResponseEntity<LoginStatus>( loginStatus, HttpStatus.UNAUTHORIZED);

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<User> create(@Valid @RequestBody UserDTO registerUser) {
        if(!loginService.getStatus().isLoggedIn()){
            User tmpUser = userRepository.findOne(registerUser.getEmail());
            if(tmpUser == null){
                User entity = new User(registerUser.getEmail(), registerUser.getUsername(), registerUser.getPassword());
                entity = userRepository.save(entity);
                User checkUser = userRepository.findOne(entity.getEmail());
                if(checkUser != null){
                    loginService.login(entity.getEmail(), registerUser.getPassword());
                    log.debug("Created new User");
                    return new ResponseEntity<User>(entity, HttpStatus.CREATED);
                }
            }
        }
        log.debug("User already exists or a user i logedin!");
        return new ResponseEntity<User>(new User(), HttpStatus.CONFLICT);
    }


}
