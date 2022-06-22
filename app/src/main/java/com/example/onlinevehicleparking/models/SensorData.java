package com.example.onlinevehicleparking.models;
import java.util.Date;
public class SensorData {
    private int id;
    private String p1, p2, p3, p4, p5, p6, available;
    private Date date;

    public SensorData(int id, String p1, String p2, String p3, String p4, String p5, String p6, String available, Date date) {
        this.id = id;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
        this.p6 = p6;
        this.available = available;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getP1() {
        return p1;
    }

    public String getP2() {
        return p2;
    }

    public String getP3() {
        return p3;
    }

    public String getP4() {
        return p4;
    }

    public String getP5() {
        return p5;
    }

    public String getP6() {
        return p6;
    }

    public String getAvailable() {
        return available;
    }

    public Date getDate() {
        return date;
    }
}

