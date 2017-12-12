
package ru.sfedu.tavern.model.entities;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import java.io.Serializable;

/**
 *
 * @author entropy
 */
public abstract class Entity implements Serializable{

//    @CsvBindByPosition(position = 1)
//    protected long id = 1l;
//    @CsvBindByPosition
    private ClassType classType;
    
    public Entity(){}

    public Entity(ClassType classType) {
        this.classType = classType;
    }
    
    public abstract long getId();

    public abstract void setId( long id );
    
    public ClassType getClassType() {
        return classType;
    }
    
    public void setClassType( ClassType classType ) {
        this.classType = classType;
    }
    
    public abstract String toString(boolean comma);
    
}
