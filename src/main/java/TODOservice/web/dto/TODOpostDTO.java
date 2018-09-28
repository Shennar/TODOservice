package TODOservice.web.dto;

import org.joda.time.DateTime;

public class TODOpostDTO {

    private Long id;

    private String datum;

    private String whatTODO;

    private boolean doneStatus;

    public TODOpostDTO(){}
    public TODOpostDTO(Long id, String datum, String whatTODO, boolean doneStatus){
        this.id=id;
        this.datum=datum;
        this.whatTODO=whatTODO;
        this.doneStatus=doneStatus;
    }

    public boolean isDoneStatus() {  return doneStatus;   }

    public void setDoneStatus(boolean doneStatus) { this.doneStatus = doneStatus;  }

    public Long getId() { return id;  }

    public void setId(Long id) { this.id = id; }

    public String getDatum() { return datum;    }

    public void setDatum(String dateTime) {  this.datum = dateTime;    }

    public String getWhatTODO() {  return whatTODO;    }

    public void setWhatTODO(String whatTODO) {   this.whatTODO = whatTODO;    }
}
