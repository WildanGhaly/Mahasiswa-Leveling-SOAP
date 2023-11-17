package org.example.model;

public class Currency {
    int point;
    int uang;
    
    
    public Currency(int point, int uang) {
        this.point = point;
        this.uang = uang;
    }

    public int getPoint() {
        return point;
    }
    public void setPoint(int point) {
        this.point = point;
    }
    public int getUang() {
        return uang;
    }
    public void setUang(int uang) {
        this.uang = uang;
    }
    
    
}

