package com.hxqh.filemanager.model.assist;

public class Refer {

    private String refertab;

    private Integer referid;

    private String savePath;

    public Refer() {
    }

    public Refer(String refertab, Integer referid, String savePath) {
        this.refertab = refertab;
        this.referid = referid;
        this.savePath = savePath;
    }

    public String getRefertab() {
        return refertab;
    }

    public void setRefertab(String refertab) {
        this.refertab = refertab;
    }

    public Integer getReferid() {
        return referid;
    }

    public void setReferid(Integer referid) {
        this.referid = referid;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
}
