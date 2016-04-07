package ch.fhnw.workshop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Created by roman on 10.03.16.
 */
@Entity
@Table(name="ACCOUNTS")
public class User implements BaseModel {

    @Column
    @Id
    private String email;

    @Column
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column
    private String passwordhash;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column
    protected String salt;
    @Column
    private Boolean isActive;
    @Column
    private Boolean isEnabeled;

    @ManyToMany(mappedBy = "members")
    @JsonBackReference
    private List<Project> projects;

    @ManyToMany(mappedBy = "invitedMembers")
    @JsonBackReference
    private List<Project> invitedProjects;

    @OneToMany(mappedBy = "owner")
    @JsonBackReference
    private List<Project> owningProject;

    @OneToMany(mappedBy = "responsibleUser")
    @JsonBackReference
    private List<Task> tasks;

    public User(){
        this.salt = UUID.randomUUID().toString();
    }
    public User(String email){
        this();
        this.email = email;
    }
    public User(String email, String username, String passwordhash){
        this(email);
        this.username = username;
        this.setPasswordhash(passwordhash);
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(String passwordhash) {
        MessageDigestPasswordEncoder mspe = new MessageDigestPasswordEncoder("MD5");
        this.passwordhash = mspe.encodePassword(passwordhash, this.salt);
    }

    public String getSalt() {return salt;}
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getEnabeled() {
        return isEnabeled;
    }

    public void setEnabeled(Boolean enabeled) {
        isEnabeled = enabeled;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Project> getInvitedProjects() {
        return invitedProjects;
    }

    public void setInvitedProjects(List<Project> invitedProjects) {
        this.invitedProjects = invitedProjects;
    }

    public List<Project> getOwningProject() {
        return owningProject;
    }

    public void setOwningProject(List<Project> owningProject) {
        this.owningProject = owningProject;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
