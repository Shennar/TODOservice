package todoservice.services;

import todoservice.web.dto.TodoPostDto;

public class TodoPostResponse {
    public TodoPostResponse(TodoPostDto post, String errors) {
        id = post.getId();
        datum = post.getDatum();
        whatTODO = post.getWhatTODO();
        doneStatus = post.getDoneStatus();
        this.errors = errors;
    }

    private Long id;
    private String datum;
    private String whatTODO;
    private String doneStatus;
    private String errors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getWhatTODO() {
        return whatTODO;
    }

    public void setWhatTODO(String whatTODO) {
        this.whatTODO = whatTODO;
    }

    public String getDoneStatus() {
        return doneStatus;
    }

    public void setDoneStatus(String doneStatus) {
        this.doneStatus = doneStatus;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public String toString(){
        return "ID: " + getId() + ", Task: " + getWhatTODO() + ", Date: " + getDatum() + ", Status " + getDoneStatus() +
                ", Errors: " + getErrors();
    }
}