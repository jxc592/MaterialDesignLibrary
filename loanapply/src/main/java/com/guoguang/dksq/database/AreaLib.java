package com.guoguang.dksq.database;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table AREA_LIB.
 */
public class AreaLib {

    private Long id;
    private String Code;
    private String ID;
    private String Name;

    public AreaLib() {
    }

    public AreaLib(Long id) {
        this.id = id;
    }

    public AreaLib(Long id, String Code, String ID, String Name) {
        this.id = id;
        this.Code = Code;
        this.ID = ID;
        this.Name = Name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

}
