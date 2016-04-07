package ch.fhnw.workshop;

import ch.fhnw.workshop.domain.User;
import ch.fhnw.workshop.persistence.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = KanbanboardApplication.class)
@WebAppConfiguration
public class KanbanboardApplicationTests {
    private MockMvc mockMvc;
    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Resource
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    UserRepository userRepository;

    @Before
    public void beforeTests(){
        this.mockMvc = webAppContextSetup(webApplicationContext).addFilter(springSecurityFilterChain).build();
        User user = new User("testing@kanbanboard.ch", "JUnit Test User", "myTestPassword");
        userRepository.save(user);
    }

    @Test
    public void LoginLogoutTest() throws Exception {
        mockMvc.perform(post("/login")
                .param("username", "testing@kanbanboard.ch")
                .param("password", "myTestPassword"))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.loggedIn", is(true))))
                .andExpect((jsonPath("$.username", is("testing@kanbanboard.ch"))));
        mockMvc.perform(get("/logout")).andExpect(redirectedUrl("/login"));
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.loggedIn", is(false))));

    }

    @Test
    public void RegisterTest() throws Exception {
        List<User> oldList = userRepository.findAll();
        String email = "testing2@kanbanboard.ch";
        mockMvc.perform(post("/register").contentType(APPLICATION_JSON_UTF8)
                .content("{\"email\":\""+ email +"\", \"username\":\"TestUser\",\"password\": \"test1234\"}"))
                .andExpect(status().isCreated())
                .andExpect((jsonPath("$.email", is(email))));
        List<User> newlist = userRepository.findAll();
        Assert.assertEquals(oldList.size() + 1, newlist.size());
    }


    @Test
	public void contextLoads() {
	}

}
