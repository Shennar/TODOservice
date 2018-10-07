package TODOservice.web.dto;

public class TODOPostDTO {


    private Long id;

    private String datum;

    private String whatTODO;

    private String doneStatus;

    public TODOPostDTO(){}
    public TODOPostDTO(Long id, String datum, String whatTODO, String doneStatus){
        this.id=id;
        this.datum=datum;
        this.whatTODO=whatTODO;
        this.doneStatus=doneStatus;
    }

    public String getDoneStatus() {  return doneStatus;   }

    public void setDoneStatus(String doneStatus) { this.doneStatus = doneStatus;  }

    public String getDatum() { return datum; }

    public void setDatum(String datum) {  this.datum = datum;    }

    public String getWhatTODO() {  return whatTODO;    }

    public void setWhatTODO(String whatTODO) {   this.whatTODO = whatTODO;    }

    public Long getId() {   return id;     }

    public void setId(Long id) {
        this.id = id;
    }
}
