
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
    
    /**
     *
     */
    public Entity(){}

    /**
     *
     * @param classType
     */
    public Entity(ClassType classType) {
        this.classType = classType;
    }
    
    /**
     *
     * @return
     */
    public abstract long getId();

    /**
     *
     * @param id
     */
    public abstract void setId(long id);
    
    /**
     *
     * @return
     */
    public ClassType getClassType() {
        return classType;
    }
    
    /**
     *
     * @param classType
     */
    public void setClassType( ClassType classType ) {
        this.classType = classType;
    }
    
    /**
     *
     * @param comma
     * @return
     */
    public abstract String toString(boolean comma);
    
}
