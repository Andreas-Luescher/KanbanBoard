package ch.fhnw.workshop;

import ch.fhnw.workshop.domain.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by roman on 21.03.16.
 */
public class UserTests {


    @Test
    public void comparePW(){

    }

    @Test
    public void compareTwoEqualPW(){
        User user1 = new User();
        user1.setPasswordhash("hashFromFrontend@*#");
        User user2 = new User();
        user2.setPasswordhash("hashFromFrontend@*#");
        Assert.assertNotEquals(user1.getPasswordhash(), user2.getPasswordhash(), "Identical pw saved");
    }


}
