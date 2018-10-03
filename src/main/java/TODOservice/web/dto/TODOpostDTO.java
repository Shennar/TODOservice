package TODOservice.web.dto;

import org.joda.time.DateTime;

public class TODOpostDTO {

    private Long id;

    private String datum;

    private String whatTODO;

    private String doneStatus;

    public TODOpostDTO(){}
    public TODOpostDTO(Long id, String datum, String whatTODO, String doneStatus){
        this.id=id;
        this.datum=datum;
        this.whatTODO=whatTODO;
        this.doneStatus=doneStatus;
    }

    public String isDoneStatus() {  return doneStatus;   }

    public void setDoneStatus(String doneStatus) { this.doneStatus = doneStatus;  }

    public Long getId() { return id;  }

    public void setId(Long id) { this.id = id; }

    public String getDatum() { return datum; }

    public void setDatum(String dateTime) {  this.datum = dateTime;    }

    public String getWhatTODO() {  return whatTODO;    }

    public void setWhatTODO(String whatTODO) {   this.whatTODO = whatTODO;    }
}
