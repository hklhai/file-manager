package com.hxqh.filemanager.model.assist;

public class IconDto {

    private String icon;
    private Integer fileid;

    public IconDto() {
    }

    public IconDto(String icon, Integer fileid) {
        this.icon = icon;
        this.fileid = fileid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }
}
