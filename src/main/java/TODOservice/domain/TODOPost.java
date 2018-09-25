package TODOservice.domain;

import org.joda.time.DateTime;
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
    private String dateTime;

    @Column (name = "WhatTODO", nullable = false)
    private String whatTODO;

    @Column (name = "Status", nullable = false)
    private boolean doneStatus;

    public boolean isDoneStatus() {  return doneStatus;   }

    public void setDoneStatus(boolean doneStatus) { this.doneStatus = doneStatus;  }

    public Long getId() { return id;  }

    public void setId(Long id) { this.id = id; }

    public String getDateTime() { return dateTime;    }

    public void setDateTime(String dateTime) {  this.dateTime = dateTime;    }

    public String getWhatTODO() {  return whatTODO;    }

    public void setWhatTODO(String whatTODO) {   this.whatTODO = whatTODO;    }

    @Override
    public String toString() {
        return "TODO: " +
                "When: " + dateTime +"\n" +
                "What: " + whatTODO + "\n" +
                "Status: "+(doneStatus?"Done":"Not done");
    }
}
