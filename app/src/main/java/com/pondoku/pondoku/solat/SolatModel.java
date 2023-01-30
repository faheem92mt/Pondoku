package com.pondoku.pondoku.solat;

public class SolatModel {

    private String imsak;
    private String subuh;
    private String syuruk;
    private String zuhur;
    private String asar;
    private String maghrib;
    private String isyak;
    private String date;

    public SolatModel(String imsak, String subuh, String syuruk, String zuhur, String asar, String maghrib, String isyak, String date) {
        this.imsak = imsak;
        this.subuh = subuh;
        this.syuruk = syuruk;
        this.zuhur = zuhur;
        this.asar = asar;
        this.maghrib = maghrib;
        this.isyak = isyak;
        this.date = date;
    }

    public String getImsak() {
        return imsak;
    }

    public void setImsak(String imsak) {
        this.imsak = imsak;
    }

    public String getSubuh() {
        return subuh;
    }

    public void setSubuh(String subuh) {
        this.subuh = subuh;
    }

    public String getSyuruk() {
        return syuruk;
    }

    public void setSyuruk(String syuruk) {
        this.syuruk = syuruk;
    }

    public String getZuhur() {
        return zuhur;
    }

    public void setZuhur(String zuhur) {
        this.zuhur = zuhur;
    }

    public String getAsar() {
        return asar;
    }

    public void setAsar(String asar) {
        this.asar = asar;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public void setMaghrib(String maghrib) {
        this.maghrib = maghrib;
    }

    public String getIsyak() {
        return isyak;
    }

    public void setIsyak(String isyak) {
        this.isyak = isyak;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
