package com.hxqh.filemanager.model.base;

/**
 * Created by Ocean lin on 2019/2/20.
 *
 * @author Ocean lin
 */
public class MessageInfo extends Message {

    private String icon;
    private Integer fileid;

    public MessageInfo(String icon) {
        this.icon = icon;
    }

    public MessageInfo(int code, String message, String icon, Integer fileid) {
        super(code, message);
        this.icon = icon;
        this.fileid = fileid;
    }

    public MessageInfo(int code, String message, boolean success, String icon) {
        super(code, message, success);
        this.icon = icon;
    }


    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
