/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cg_trab1.model;

/**
 *
 * @author patri
 */
public class RGB {
    private double r;
    private double g;
    private double b;

    public RGB(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public RGB() {
        this.r = 0;
        this.g = 0;
        this.b = 0;
    }
    
    

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }
    
    public void setRGB(double r,double g,double b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public String toString() {
        return Integer.toHexString((int) r) +""+ Integer.toHexString((int)g) + "" + Integer.toHexString((int)b) ;
    }
    
    
    
    
}
