
package ru.sfedu.tavern.model;

import java.util.ArrayList;
import java.util.Objects;
import ru.sfedu.tavern.model.entities.Entity;

/**
 *
 * @author entropy
 */
public class Result {

    private StatusType status;
    private String msg;

    public Result() {}
    public Result(StatusType status) {
        this.status = status;
    }
    public Result(StatusType status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    
    public StatusType getStatus() {
        return status;
    }
    
    public void setStatus(StatusType status) {
        this.status = status;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Result other = (Result) obj;
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.msg, other.msg)) {
            return false;
        }
        return true;
    }
    
    
    
}
