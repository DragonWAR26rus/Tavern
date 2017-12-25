/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tavern.model.entities;

import ru.sfedu.tavern.model.Constants;

/**
 *
 * @author entropy
 */
public enum ClassType {
    
    /**
     *
     */
    OURUSER      (Constants.HEADERS_OURUSER,      OurUser.class,      Constants.OURUSER_FILENAME,       Constants.OURUSER_TABLE),

    /**
     *
     */
    PLATFORM     (Constants.HEADERS_PLATFORM,     Platform.class,     Constants.PLATFORM_FILENAME,      Constants.PLATFORM_TABLE),

    /**
     *
     */
    PLATFORMUSER (Constants.HEADERS_PLATFORMUSER, PlatformUser.class, Constants.PLATFORMUSER_FILENAME,  Constants.PLATFORMUSER_TABLE),

    /**
     *
     */
    MESSAGE      (Constants.HEADERS_MESSAGE,      Message.class,      Constants.MESSAGE_FILENAME,       Constants.MESSAGE_TABLE);
    
    private Class _class;
    private String[] headers;
    private String fileName;
    private String tableName;
    private ClassType(){};
    
    private ClassType(String[] headers, Class _class, String fileName, String tableName) {
        this.headers = headers;
        this._class = _class;
        this.fileName = fileName;
        this.tableName = tableName;
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    /**
     *
     * @return
     */
    public String getHeaderString() {
        return String.join(", ", headers);
    }
    
    /**
     *
     * @return
     */
    public Class getCl() {
        return _class;
    }
    
    /**
     *
     * @return
     */
    public String[] getHeaders() {
        return headers;
    }

    /**
     *
     * @return
     */
    public String getFileName() {
        return fileName;
    }
    
    /**
     *
     * @return
     */
    public String getTableName() {
        return tableName;
    }

}
