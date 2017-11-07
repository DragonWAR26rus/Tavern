
package ru.sfedu.tavern.model.entities;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import java.io.Serializable;

/**
 *
 * @author entropy
 */
public abstract class Entity implements Serializable{

    @CsvBindByName
    private long id = 1l;
    @CsvBindByName
    private ClassType classType;
    
    public Entity(){}

    public Entity(ClassType classType, long id) {
        this.classType = classType;
        this.id = id;
    }
    
    public long getId() {
        return id;
    }

    public void setId( long id ){
        this.id = id;
    }
    
    public ClassType getClassType() {
        return classType;
    }
    
    public void setClassType( ClassType classType ) {
        this.classType = classType;
    }
    
}
