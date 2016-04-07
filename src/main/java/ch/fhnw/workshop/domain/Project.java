package ch.fhnw.workshop.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by roman on 10.03.16.
 */
@Entity
@Table(name="PROJECTS")
public class Project implements BaseModel {
    @Id
    @GeneratedValue
    @Column(name="projectid")
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    @ManyToMany
    @JsonManagedReference
    private List<User> members;

    @ManyToOne
    @JsonManagedReference
    private User owner;

    @Column
    @OneToMany(mappedBy = "project")
    @JsonManagedReference
    private List<Task> tasks;

    @Column
    @ManyToMany
    @JsonManagedReference
    private List<User> invitedMembers;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<User> getInvitedMembers() {
        return invitedMembers;
    }

    public void setInvitedMembers(List<User> invitedMembers) {
        this.invitedMembers = invitedMembers;
    }
}
