package ru.sfedu.tavern.model.entities;

import java.util.List;
import org.simpleframework.xml.ElementList;

/**
 *
 * @author entropy
 * @param <T>
 */
public class EntityList<T> {
    @ElementList
    private List<T> list;

    /**
     *
     */
    public EntityList() {

    }

    /**
     *
     * @param list
     */
    public EntityList(List<T> list) {
        this.list = list;
    }

    /**
     *
     * @return
     */
    public List<T> getList() {
        return list;
    }

    /**
     *
     * @param list
     */
    public void setList(List<T> list) {
        this.list = list;
    }
}
