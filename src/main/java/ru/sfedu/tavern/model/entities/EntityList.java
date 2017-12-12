package ru.sfedu.tavern.model.entities;

import java.util.List;
import org.simpleframework.xml.ElementList;

/**
 *
 * @author entropy
 */
public class EntityList<T> {
    @ElementList(inline = true, entry = "Entity")
    private List<T> list;
    public EntityList() {

    }
    public List<T> getList() {
        return list;
    }
    public void setList(List<T> list) {
        this.list = list;
    }
}
