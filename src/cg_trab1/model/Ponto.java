/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cg_trab1.model;

import java.io.Serializable;

/**
 *
 * @author patri
 */
public class Ponto implements Comparable<Ponto>, Cloneable, Serializable {

    private double x;
    private double y;
    private double z;
    private double n;

    public Ponto(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Ponto(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    public Ponto() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setXZ(double x, double z) {
        this.x = x;
        this.z = z;
    }

    public void setYZ(double y, double z) {
        this.y = y;
        this.z = z;
    }

    public void setXYZ(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double X2() {
        return this.x * this.x;
    }

    public double Y2() {
        return this.y * this.y;
    }

    public double Z2() {
        return this.z * this.z;
    }

    public void somaPixelX(double x) {
        this.x = this.x + x;
    }

    public void somaPixelY(double y) {
        this.y = this.y + y;
    }

    public void somaPixelZ(double z) {
        this.z = this.z + z;
    }

    public void subPonto(Ponto p) {
        this.x -= p.getX();
        this.y -= p.getY();
        this.z -= p.getZ();
    }

    public void somPonto(Ponto p) {
        this.x += p.getX();
        this.y += p.getY();
        this.z += p.getZ();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ponto other = (Ponto) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", z=" + z;
    }

    @Override
    public int compareTo(Ponto t) {
        if (this.x == t.getX() && this.y == t.getY()) {
            return 1;
        }
        return 0;
    }

    public Ponto getClone() {
        try {
            Ponto p = (Ponto) super.clone();

            p.setX(getX());
            p.setY(getY());
            return p;

        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public double getNorma() {
        return Math.sqrt(X2() + Y2() + Z2());

    }

    public void normalizarVetor() {
        this.n = Math.sqrt(X2() + Y2() + Z2());
        this.x = this.x / n;
        this.y = this.y / n;
        this.z = this.z / n;
    }

    public void desnormalizarVetor() {
        this.x = this.x * n;
        this.y = this.y * n;
        this.z = this.z * n;

    }

}
