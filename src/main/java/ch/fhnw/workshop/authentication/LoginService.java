package ch.fhnw.workshop.authentication;

/**
 * Created by roman on 29.03.16.
 */
public interface LoginService {
    LoginStatus getStatus();
    LoginStatus login(String username, String password);
    LoginStatus logout();
}
