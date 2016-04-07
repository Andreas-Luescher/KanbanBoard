package ch.fhnw.workshop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by roman on 10.03.16.
 */
@Entity
@Table(name="TASKS")
public class Task implements BaseModel{
    @Id
    @GeneratedValue
    @Column(name="taskid")
    private Long id;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name="projectid")
    @JsonBackReference
    private Project project;


    @ManyToOne
    @JoinColumn(name="email")
    private User creator;

    @Column
    private String description;

    @ManyToOne
    @JsonManagedReference
    private User responsibleUser;

    @Column
    @ElementCollection
    private List<String> attachements;

    @Column
    private Date createdAt;
    @Column
    private Date deadline;
    @Column
    private Date finishedDate;
    @Column
    private TaskState state;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getResponsibleUser() {
        return responsibleUser;
    }

    public void setResponsibleUser(User responsibleUser) {
        this.responsibleUser = responsibleUser;
    }

    public List<String> getAttachements() {
        return attachements;
    }

    public void setAttachements(List<String> attachements) {
        this.attachements = attachements;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
