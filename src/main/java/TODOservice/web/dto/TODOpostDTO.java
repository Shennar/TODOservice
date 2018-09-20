package TODOservice.web.dto;

import org.joda.time.DateTime;

public class TODOpostDTO {

    private Long id;

    private DateTime dateTime;

    private String whatTODO;

    private boolean doneStatus;

    public TODOpostDTO(){}
    public TODOpostDTO(Long id, DateTime dateTime, String whatTODO, boolean doneStatus){
        this.id=id;
        this.dateTime=dateTime;
        this.whatTODO=whatTODO;
        this.doneStatus=doneStatus;
    }

    public boolean isDoneStatus() {  return doneStatus;   }

    public void setDoneStatus(boolean doneStatus) { this.doneStatus = doneStatus;  }

    public Long getId() { return id;  }

    public void setId(Long id) { this.id = id; }

    public DateTime getDateTime() { return dateTime;    }

    public void setDateTime(DateTime dateTime) {  this.dateTime = dateTime;    }

    public String getWhatTODO() {  return whatTODO;    }

    public void setWhatTODO(String whatTODO) {   this.whatTODO = whatTODO;    }
}
