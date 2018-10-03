package TODOservice.domain;

//import org.joda.time.DateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "todobase")
public class TODOPost {

    @Id
    @Column (name = "id")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "Date", nullable = false)
    private String datum;

    @Column (name = "WhatTODO", nullable = false)
    private String whatTODO;

    @Column (name = "Status", nullable = false)
    private boolean doneStatus;

    public boolean isDoneStatus() {  return doneStatus;   }

    public void setDoneStatus(boolean doneStatus) { this.doneStatus = doneStatus;  }

    public Long getId() { return id;  }

    public void setId(Long id) { this.id = id; }

    public String getDatum() { return datum;    }

    public void setDatum(String datum) {  this.datum = datum;    }

    public String getWhatTODO() {  return whatTODO;    }

    public void setWhatTODO(String whatTODO) {   this.whatTODO = whatTODO;    }

    @Override
    public String toString() {
        return "TODO: " +id+"\n" +
                "When: " + datum +"\n" +
                "What: " + whatTODO + "\n" +
                "Status: "+doneStatus+"\n" ;
//(doneStatus?"Done\n":"Not done\n");
    }

}
