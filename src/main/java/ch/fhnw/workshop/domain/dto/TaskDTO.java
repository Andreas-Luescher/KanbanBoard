package ch.fhnw.workshop.domain.dto;

import ch.fhnw.workshop.domain.TaskState;

import java.util.Date;
import java.util.List;

/**
 * Created by roman on 30.03.16.
 */
public class TaskDTO {
    private String title;
    private Long projectId;
    private String description;
    private String responsibleUserEmail;
    private Date deadline;
    private TaskState state;
    private List<String> attachements;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponsibleUserEmail() {
        return responsibleUserEmail;
    }

    public void setResponsibleUserEmail(String responsibleUserEmail) {
        this.responsibleUserEmail = responsibleUserEmail;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public List<String> getAttachements() {
        return attachements;
    }

    public void setAttachements(List<String> attachements) {
        this.attachements = attachements;
    }
}
