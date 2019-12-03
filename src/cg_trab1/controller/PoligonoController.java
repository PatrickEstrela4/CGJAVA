/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cg_trab1.controller;

import cg_trab1.model.Poligono;
import cg_trab1.model.Ponto;
import cg_trab1.model.RGB;
import java.util.LinkedList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @authors Patrick, Rafael
 * @version 2.1
 * @since 2017
 */
public class PoligonoController implements Cloneable {

    private LinkedList<Poligono> poligonos;
    private Canvas canvas;
    private Poligono pol;
    private Ponto luzIncidente;
    private Ponto pPers;
    private Ponto vrpPers;
    private double dpPers;

    public PoligonoController(Canvas canvas) {
        this.canvas = canvas;
        this.poligonos = new LinkedList<>();
        this.pPers = new Ponto();
        this.vrpPers = new Ponto();
        this.luzIncidente = new Ponto();
        this.dpPers = 0;
    }

    public void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(-(canvas.getWidth()) * 2, -(canvas.getHeight()) * 2, canvas.getWidth() * 2, canvas.getHeight() * 2);
        for (Poligono poligono : poligonos) {
            poligono.drawXY(gc);
        }
        if (pol != null) {
            pol.drawXY(gc);

        }
    }

    public void setPol(Poligono pol) {
        this.pol = pol;
    }

    public void criarRegularXY(Ponto centro, int lado, double raio, Color c1) {
        this.poligonos.add(new Poligono(lado, centro, raio, c1));
        this.poligonos.getLast().GerarpoligonoXY();
        calcPerpectiva(this.poligonos.getLast());
        //this.draw();
    }

    public void criarRegularXZ(Ponto centro, int lado, double raio, Color c1) {
        this.poligonos.add(new Poligono(lado, centro, raio, c1));
        this.poligonos.getLast().GerarpoligonoXZ();
        calcPerpectiva(this.poligonos.getLast());
        //this.draw();
    }

    public void criarRegularYZ(Ponto centro, int lado, double raio, Color c1) {
        this.poligonos.add(new Poligono(lado, centro, raio, c1));
        this.poligonos.getLast().GerarpoligonoYZ();
        calcPerpectiva(this.poligonos.getLast());
        //this.draw();
    }

    public void setPoligonos(LinkedList<Poligono> poligonos) {
        this.poligonos = poligonos;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Ponto getpPers() {
        return pPers;
    }

    public void setpPers(Ponto pPers) {
        this.pPers = pPers;
    }

    public Ponto getVrpPers() {
        return vrpPers;
    }

    public void setVrpPers(Ponto vrpPers) {
        this.vrpPers = vrpPers;
    }

    public double getDpPers() {
        return dpPers;
    }

    public void setDpPers(double dpPers) {
        this.dpPers = dpPers;
    }

    public LinkedList<Poligono> getPoligonos() {
        return poligonos;
    }

    public void createIrregular(Color C) {
        this.pol = new Poligono();
        this.pol.setFechado(false);
        this.pol.setCorBorda(C);
    }

    public void finishIrregular() {
        pol.fecharPoligono();
        this.poligonos.add(pol);
        pol.calcCentro();
        this.pol = null;

        this.draw();
    }

    public void addPonto(Ponto p) {
        if (pol != null) {
            pol.addPonto(p);
            pol.incNLados();
            this.draw();
        }
    }

//    public boolean equaçaoPonto(Ponto a, Ponto b, Ponto c) {
//
//        double x, y, t, t2;
//
//        if ((b.getY() == a.getY()) && (c.getY() == a.getY())) {
//            if ((Math.max(b.getX(), a.getX()) > c.getX()) && (Math.min(b.getX(), a.getX()) < c.getX())) {
//                return true;
//            }
//        }
//
//        if ((b.getX() == a.getX()) && (c.getX() == a.getX())) {
//            if ((Math.max(b.getY(), a.getY()) > c.getY()) && (Math.min(b.getY(), a.getY()) < c.getY())) {
//                return true;
//            }
//        }
//        t = (c.getX() - a.getX()) / (b.getX() - a.getX());
//        t2 = (c.getY() - a.getY()) / (b.getY() - a.getY());
//
//        return (t >= 0 && t <= 1) && (t2 >= 0 && t2 <= 1);
//    }
    public double distanciaRetaXY(Ponto a, Ponto b) {
        double d;
        d = Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()));

        return d;
    }

    public double distanciaRetaXZ(Ponto a, Ponto b) {
        double d;
        d = Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getZ() - b.getZ()) * (a.getZ() - b.getZ()));

        return d;
    }

    public double distanciaRetaYZ(Ponto a, Ponto b) {
        double d;
        d = Math.sqrt((a.getZ() - b.getZ()) * (a.getZ() - b.getZ()) + (a.getY() - b.getY()) * (a.getY() - b.getY()));

        return d;
    }

    public boolean equaçaoPontoXZ(Ponto a, Ponto b, Ponto c) {

        double x, Z, t, t2;

        if ((b.getZ() == a.getZ()) && (c.getZ() == a.getZ())) {
            if ((Math.max(b.getX(), a.getX()) > c.getX()) && (Math.min(b.getX(), a.getX()) < c.getX())) {
                return true;
            }
        }

        if ((b.getX() == a.getX()) && (c.getX() == a.getX())) {
            if ((Math.max(b.getZ(), a.getZ()) > c.getZ()) && (Math.min(b.getZ(), a.getZ()) < c.getZ())) {
                return true;
            }
        }
        t = (c.getX() - a.getX()) / (b.getX() - a.getX());
        t2 = (c.getZ() - a.getZ()) / (b.getZ() - a.getZ());

        return (t >= 0 && t <= 1) && (t2 >= 0 && t2 <= 1);
    }

    public boolean equaçaoPontoXY(Ponto a, Ponto b, Ponto c) {

        double x, y, t, t2;

        if ((b.getY() == a.getY()) && (c.getY() == a.getY())) {
            if ((Math.max(b.getX(), a.getX()) > c.getX()) && (Math.min(b.getX(), a.getX()) < c.getX())) {
                return true;
            }
        }

        if ((b.getX() == a.getX()) && (c.getX() == a.getX())) {
            if ((Math.max(b.getY(), a.getY()) > c.getY()) && (Math.min(b.getY(), a.getY()) < c.getY())) {
                return true;
            }
        }
        t = (c.getX() - a.getX()) / (b.getX() - a.getX());
        t2 = (c.getY() - a.getY()) / (b.getY() - a.getY());

        return (t >= 0 && t <= 1) && (t2 >= 0 && t2 <= 1);
    }

    public boolean equaçaoPontoYZ(Ponto a, Ponto b, Ponto c) {

        double Z, y, t, t2;

        if ((b.getY() == a.getY()) && (c.getY() == a.getY())) {
            if ((Math.max(b.getZ(), a.getZ()) > c.getZ()) && (Math.min(b.getZ(), a.getZ()) < c.getZ())) {
                return true;
            }
        }

        if ((b.getZ() == a.getZ()) && (c.getZ() == a.getZ())) {
            if ((Math.max(b.getY(), a.getY()) > c.getY()) && (Math.min(b.getY(), a.getY()) < c.getY())) {
                return true;
            }
        }
        t = (c.getZ() - a.getZ()) / (b.getZ() - a.getZ());
        t2 = (c.getY() - a.getY()) / (b.getY() - a.getY());

        return (t >= 0 && t <= 1) && (t2 >= 0 && t2 <= 1);
    }

    public double anguloVetoresXY(Ponto x, Ponto y, Ponto z) {
        double ret;
        Ponto a = new Ponto();
        Ponto b = new Ponto();

        a.setXY((y.getX() - x.getX()), (y.getY() - x.getY()));
        b.setXY((z.getX() - x.getX()), (z.getY() - x.getY()));

        ret = ((a.getX() * b.getX()) + (b.getY() * a.getY())) / (Math.sqrt(a.X2() + a.Y2()) * Math.sqrt(b.X2() + b.Y2()));

        if (calcdireçao(a, b)) {
            return -Math.toDegrees(Math.acos(ret));
        } else {
            return Math.toDegrees(Math.acos(ret));
        }
    }

    public double anguloVetoresXZ(Ponto x, Ponto y, Ponto z) {
        double ret;
        Ponto a = new Ponto();
        Ponto b = new Ponto();

        a.setXY((y.getX() - x.getX()), (y.getY() - x.getZ()));
        b.setXY((z.getX() - x.getX()), (z.getY() - x.getZ()));

        calcdireçao(a, b);

        ret = ((a.getX() * b.getX()) + (b.getY() * a.getY())) / (Math.sqrt(a.X2() + a.Y2()) * Math.sqrt(b.X2() + b.Y2()));
        if (calcdireçao(a, b)) {
            return -Math.toDegrees(Math.acos(ret));
        } else {
            return Math.toDegrees(Math.acos(ret));
        }

    }

    public double anguloVetoresYZ(Ponto x, Ponto y, Ponto z) {
        double ret;
        Ponto a = new Ponto();
        Ponto b = new Ponto();

        a.setXY((y.getX() - x.getZ()), (y.getY() - x.getY()));
        b.setXY((z.getX() - x.getZ()), (z.getY() - x.getY()));

        ret = ((a.getX() * b.getX()) + (b.getY() * a.getY())) / (Math.sqrt(a.X2() + a.Y2()) * Math.sqrt(b.X2() + b.Y2()));

        if (calcdireçao(a, b)) {
            return -Math.toDegrees(Math.acos(ret));
        } else {
            return Math.toDegrees(Math.acos(ret));
        }
    }

    public boolean calcdireçao(Ponto a, Ponto b) {
        double x = (b.getY() * a.getX()) - (a.getY() * b.getX());
        if (x < 0) {
            return false;
        } else {
            return true;
        }
    }

    public Poligono selecaoXY(Ponto p) {
        Poligono ret = null;

        double menor = 100000000, d1;
        int l = 0;
        for (Poligono poligono : poligonos) {

            int t = poligono.getLados().size();

            for (int i = 1; i < t; i++) {
                d1 = dist1XY(poligono.getLados().get(i), poligono.getLados().get(i - 1), p);
                if (d1 < menor) {
                    if (d1 < 10) {
                        ret = poligono;
                        menor = d1;
                    }
                }
            }
            d1 = dist1XY(poligono.getLados().get(0), poligono.getLados().get(t - 1), p);
            if (d1 < menor) {
                if (d1 < 10) {
                    ret = poligono;
                    menor = d1;
                }
            }

        }
        return ret;
    }

    public Poligono selecaoYZ(Ponto p) {
        Poligono ret = null;

        double menor = 100000000, d1;
        int l = 0;
        for (Poligono poligono : poligonos) {

            int t = poligono.getLados().size();

            for (int i = 1; i < t; i++) {
                d1 = dist1YZ(poligono.getLados().get(i), poligono.getLados().get(i - 1), p);
                if (d1 < menor) {
                    if (d1 < 10) {
                        ret = poligono;
                        menor = d1;
                    }
                }
            }
            d1 = dist1YZ(poligono.getLados().get(0), poligono.getLados().get(t - 1), p);
            if (d1 < menor) {
                if (d1 < 10) {
                    ret = poligono;
                    menor = d1;
                }
            }

        }
        return ret;
    }

    public Poligono selecaoXZ(Ponto p) {
        Poligono ret = null;

        double menor = 100000000, d1;
        int l = 0;
        for (Poligono poligono : poligonos) {

            int t = poligono.getLados().size();

            for (int i = 1; i < t; i++) {
                d1 = dist1XZ(poligono.getLados().get(i), poligono.getLados().get(i - 1), p);
                if (d1 < menor) {
                    if (d1 < 10) {
                        ret = poligono;
                        menor = d1;
                    }
                }
            }
            d1 = dist1XZ(poligono.getLados().get(0), poligono.getLados().get(t - 1), p);
            if (d1 < menor) {
                if (d1 < 10) {
                    ret = poligono;
                    menor = d1;
                }
            }

        }
        return ret;
    }

    public double dist1XZ(Ponto A, Ponto B, Ponto C) {
        double r, s, t, m, b, b2, m2, dist = 100, x, y = 0, z = 0;
        Ponto p;

        r = (B.getZ() - A.getZ());
        s = -(B.getX() - A.getX());
        t = B.getX() * A.getZ() - B.getZ() * A.getX();

        m = -r / s;
        b = -t / s;

        m2 = s / r;
        b2 = (C.getZ() - ((s / r) * C.getX()));

        x = (b2 - b) / (m - m2);
        z = ((b2 * m) - (b * m2)) / (m - m2);

        p = new Ponto(x, y, z);

        if (Math.round(s) == 0) {
            p = new Ponto(B.getX(), 0, C.getZ());

        }

        if (Math.round(r) == 0) {
            p = new Ponto(C.getX(), 0, B.getZ());

        }

        if (equaçaoPontoXZ(A, B, p)) {
            dist = distanciaRetaXZ(p, C);

        }

        return dist;
    }

    public double dist1XY(Ponto A, Ponto B, Ponto C) {
        double r, s, t, m, b, b2, m2, dist = 100, x = 0, y = 0, z = 0;
        Ponto p;

        r = (B.getY() - A.getY());
        s = -(B.getX() - A.getX());
        t = B.getX() * A.getY() - B.getY() * A.getX();

        m = -r / s;
        b = -t / s;

        m2 = s / r;
        b2 = (C.getY() - ((s / r) * C.getX()));

        x = (b2 - b) / (m - m2);
        y = ((b2 * m) - (b * m2)) / (m - m2);

        p = new Ponto(x, y, z);

        if (Math.round(s) == 0) {
            p = new Ponto(B.getX(), C.getY(), 0);

        }

        if (Math.round(r) == 0) {
            p = new Ponto(C.getX(), B.getY(), 0);

        }

        if (equaçaoPontoXY(A, B, p)) {
            dist = distanciaRetaXY(p, C);

        }

        return dist;
    }

    public double dist1YZ(Ponto A, Ponto B, Ponto C) {
        double r, s, t, m, b, b2, m2, dist = 100, x = 0, y = 0, z = 0;
        Ponto p;

        r = (B.getY() - A.getY());
        s = -(B.getZ() - A.getZ());
        t = B.getZ() * A.getY() - B.getY() * A.getZ();

        m = -r / s;
        b = -t / s;

        m2 = s / r;
        b2 = (C.getY() - ((s / r) * C.getZ()));

        z = (b2 - b) / (m - m2);
        y = ((b2 * m) - (b * m2)) / (m - m2);

        p = new Ponto(x, y, z);

        if (Math.round(s) == 0) {
            p = new Ponto(0, C.getY(), B.getZ());
        }

        if (Math.round(r) == 0) {
            p = new Ponto(0, B.getY(), C.getZ());

        }

        if (equaçaoPontoYZ(A, B, p)) {
            dist = distanciaRetaYZ(p, C);

        }

        return dist;
    }

    public Poligono transladarXY(Poligono pol, Ponto a, Ponto b) {
        Poligono ret = null;
        double x, y;

        x = b.getX() - a.getX();
        y = b.getY() - a.getY();

        for (Ponto lado : pol.getLados()) {
            lado.setXY((lado.getX() + x), (lado.getY() + y));
        }

        pol.getCentro().setXY(pol.getCentro().getX() + x, pol.getCentro().getY() + y);
        ret = pol;
        ret.CalcPerspectiva(pPers, vrpPers, dpPers);
        return ret;
    }

    public Poligono transladarXZ(Poligono pol, Ponto a, Ponto b) {
        Poligono ret = null;
        double x, y, z;

        x = b.getX() - a.getX();
        z = b.getZ() - a.getZ();

        for (Ponto lado : pol.getLados()) {
            lado.setXZ((lado.getX() + x), (lado.getZ() + z));
        }

        pol.getCentro().setXZ(pol.getCentro().getX() + x, pol.getCentro().getZ() + z);
        ret = pol;
        ret.calcCentro();
        ret.CalcPerspectiva(pPers, vrpPers, dpPers);
        return ret;
    }

    public Poligono transladarYZ(Poligono pol, Ponto a, Ponto b) {
        Poligono ret = null;
        double x, y, z;

        y = b.getY() - a.getY();
        z = b.getZ() - a.getZ();

        for (Ponto lado : pol.getLados()) {
            lado.setYZ((lado.getY() + y), (lado.getZ() + z));
        }

        pol.getCentro().setYZ(pol.getCentro().getY() + y, pol.getCentro().getZ() + z);
        ret = pol;
        ret.calcCentro();
        ret.CalcPerspectiva(pPers, vrpPers, dpPers);
        return ret;
    }

    public void rotacionarXY(Poligono select, Ponto anterior, Ponto novo) {
        double angulo, xc, yc;

        xc = select.getCentro().getX();
        yc = select.getCentro().getY();

        angulo = anguloVetoresXY(select.getCentro(), anterior, novo);

        select.setAngulo(Math.toRadians(angulo));

        for (Ponto lado : select.getLados()) {
            lado.setX(lado.getX() - xc);
            lado.setY(lado.getY() - yc);
        }

        for (Ponto lado : select.getLados()) {
            select.multiMatriz2XY(lado);
        }

        for (Ponto lado : select.getLados()) {
            lado.setX(lado.getX() + xc);
            lado.setY(lado.getY() + yc);
        }
        select.CalcPerspectiva(pPers, vrpPers, dpPers);
    }

    public void rotacionarXZ(Poligono select, Ponto anterior, Ponto novo) {
        double angulo, xc, zc;

        xc = select.getCentro().getX();
        zc = select.getCentro().getZ();

        angulo = anguloVetoresXZ(select.getCentro(), anterior, novo);

        select.setAngulo(Math.toRadians(angulo));

        for (Ponto lado : select.getLados()) {
            lado.setX(lado.getX() - xc);
            lado.setZ(lado.getZ() - zc);
        }

        for (Ponto lado : select.getLados()) {
            select.multiMatriz2XZ(lado);
        }

        for (Ponto lado : select.getLados()) {
            lado.setX(lado.getX() + xc);
            lado.setZ(lado.getZ() + zc);
        }
        select.CalcPerspectiva(pPers, vrpPers, dpPers);
    }

    public void rotacionarYZ(Poligono select, Ponto anterior, Ponto novo) {
        double angulo, zc, yc;

        zc = select.getCentro().getZ();
        yc = select.getCentro().getY();

        angulo = anguloVetoresYZ(select.getCentro(), anterior, novo);

        select.setAngulo(Math.toRadians(angulo));

        for (Ponto lado : select.getLados()) {
            lado.setZ(lado.getZ() - zc);
            lado.setY(lado.getY() - yc);
        }

        for (Ponto lado : select.getLados()) {
            select.multiMatriz2YZ(lado);
        }

        for (Ponto lado : select.getLados()) {
            lado.setZ(lado.getZ() + zc);
            lado.setY(lado.getY() + yc);
        }
        select.CalcPerspectiva(pPers, vrpPers, dpPers);
    }

    public void escalaXY(Poligono original, Poligono select, Ponto inicio, Ponto fim) {
        double x, y, prop;

        prop = distanciaRetaXY(select.getCentro(), fim) / distanciaRetaXY(select.getCentro(), inicio);

        double xc, yc;

        xc = select.getCentro().getX();
        yc = select.getCentro().getY();

        for (Ponto lado : original.getLados()) {
            lado.setX(lado.getX() - xc);
            lado.setY(lado.getY() - yc);
        }

        for (Ponto lado : select.getLados()) {
            lado.setX(lado.getX() - xc);
            lado.setY(lado.getY() - yc);
        }

        int i = 0;

        for (Ponto lado : select.getLados()) {

            lado.setXY(original.getLados().get(i).getX() * prop, original.getLados().get(i).getY() * prop);

            i++;
        }

        for (Ponto lado : select.getLados()) {
            lado.setX(lado.getX() + xc);
            lado.setY(lado.getY() + yc);
        }
        for (Ponto lado : original.getLados()) {

            lado.setX(lado.getX() + xc);
            lado.setY(lado.getY() + yc);
        }
        select.calcCentro();
        select.CalcPerspectiva(pPers, vrpPers, dpPers);
        //}
    }

    public void escalaXZ(Poligono original, Poligono select, Ponto inicio, Ponto fim) {
        double x, z, prop;

        prop = distanciaRetaXZ(select.getCentro(), fim) / distanciaRetaXZ(select.getCentro(), inicio);

        double xc, zc;

        xc = select.getCentro().getX();
        zc = select.getCentro().getZ();

        for (Ponto lado : original.getLados()) {
            lado.setX(lado.getX() - xc);
            lado.setZ(lado.getZ() - zc);
        }

        for (Ponto lado : select.getLados()) {
            lado.setX(lado.getX() - xc);
            lado.setZ(lado.getZ() - zc);
        }

        int i = 0;

        for (Ponto lado : select.getLados()) {

            lado.setXZ(original.getLados().get(i).getX() * prop, original.getLados().get(i).getZ() * prop);

            i++;
        }

        for (Ponto lado : select.getLados()) {
            lado.setX(lado.getX() + xc);
            lado.setZ(lado.getZ() + zc);
        }
        for (Ponto lado : original.getLados()) {

            lado.setX(lado.getX() + xc);
            lado.setZ(lado.getZ() + zc);
        }
        select.calcCentro();
        select.CalcPerspectiva(pPers, vrpPers, dpPers);

    }

    public void escalaYZ(Poligono original, Poligono select, Ponto inicio, Ponto fim) {
        double z, y, prop;

        prop = distanciaRetaYZ(select.getCentro(), fim) / distanciaRetaYZ(select.getCentro(), inicio);

        double zc, yc;

        zc = select.getCentro().getZ();
        yc = select.getCentro().getY();

        for (Ponto lado : original.getLados()) {
            lado.setZ(lado.getZ() - zc);
            lado.setY(lado.getY() - yc);
        }

        for (Ponto lado : select.getLados()) {
            lado.setZ(lado.getZ() - zc);
            lado.setY(lado.getY() - yc);
        }

        int i = 0;

        for (Ponto lado : select.getLados()) {

            lado.setYZ(original.getLados().get(i).getZ() * prop, original.getLados().get(i).getY() * prop);

            i++;
        }

        for (Ponto lado : select.getLados()) {
            lado.setZ(lado.getZ() + zc);
            lado.setY(lado.getY() + yc);
        }
        for (Ponto lado : original.getLados()) {

            lado.setZ(lado.getZ() + zc);
            lado.setY(lado.getY() + yc);
        }
        select.calcCentro();
        select.CalcPerspectiva(pPers, vrpPers, dpPers);

    }

    public void cisalharXY(Poligono original, Poligono select, Ponto anterior, Ponto novo) {
        double x, y;
        double xc, yc;

        xc = select.getCentro().getX();
        yc = select.getCentro().getY();

        x = (anterior.getX() - novo.getX()) * 0.005;
        y = (anterior.getY() - novo.getY()) * 0.005;

        for (Ponto lado : select.getLados()) {

            lado.setX(lado.getX() - xc);
            lado.setY(lado.getY() - yc);
        }
        for (Ponto lado : original.getLados()) {

            lado.setX(lado.getX() - xc);
            lado.setY(lado.getY() - yc);
        }

        int i = 0;

        for (Ponto lado : select.getLados()) {
            lado.setX(original.getLados().get(i).getX() + original.getLados().get(i).getY() * x);
            lado.setY(original.getLados().get(i).getY() + original.getLados().get(i).getX() * y);

            i++;
        }

        for (Ponto lado : select.getLados()) {
            lado.setX(lado.getX() + xc);
            lado.setY(lado.getY() + yc);
        }

        for (Ponto lado : original.getLados()) {
            lado.setX(lado.getX() + xc);
            lado.setY(lado.getY() + yc);
        }
        select.calcCentro();
        select.CalcPerspectiva(pPers, vrpPers, dpPers);
    }

    public void cisalharXZ(Poligono original, Poligono select, Ponto anterior, Ponto novo) {
        double x, z;
        double angulo, xc, zc;

        xc = select.getCentro().getX();
        zc = select.getCentro().getZ();

        x = (anterior.getX() - novo.getX()) * 0.005;
        z = (anterior.getZ() - novo.getZ()) * 0.005;

        for (Ponto lado : select.getLados()) {

            lado.setX(lado.getX() - xc);
            lado.setZ(lado.getZ() - zc);
        }
        for (Ponto lado : original.getLados()) {

            lado.setX(lado.getX() - xc);
            lado.setZ(lado.getZ() - zc);
        }

        int i = 0;

        for (Ponto lado : select.getLados()) {
            lado.setX(original.getLados().get(i).getX() + original.getLados().get(i).getZ() * x);
            lado.setZ(original.getLados().get(i).getZ() + original.getLados().get(i).getX() * z);

            i++;
        }

        for (Ponto lado : select.getLados()) {
            lado.setX(lado.getX() + xc);
            lado.setZ(lado.getZ() + zc);
        }

        for (Ponto lado : original.getLados()) {
            lado.setX(lado.getX() + xc);
            lado.setZ(lado.getZ() + zc);
        }
        select.calcCentro();
        select.CalcPerspectiva(pPers, vrpPers, dpPers);
    }

    public void cisalharYZ(Poligono original, Poligono select, Ponto anterior, Ponto novo) {
        double z, y;
        double angulo, zc, yc;

        zc = select.getCentro().getZ();
        yc = select.getCentro().getY();

        z = (anterior.getZ() - novo.getZ()) * 0.005;
        y = (anterior.getY() - novo.getY()) * 0.005;

        for (Ponto lado : select.getLados()) {

            lado.setZ(lado.getZ() - zc);
            lado.setY(lado.getY() - yc);
        }
        for (Ponto lado : original.getLados()) {

            lado.setZ(lado.getZ() - zc);
            lado.setY(lado.getY() - yc);
        }

        int i = 0;

        for (Ponto lado : select.getLados()) {
            lado.setZ(original.getLados().get(i).getZ() + original.getLados().get(i).getY() * z);
            lado.setY(original.getLados().get(i).getY() + original.getLados().get(i).getZ() * y);

            i++;
        }

        for (Ponto lado : select.getLados()) {
            lado.setZ(lado.getZ() + zc);
            lado.setY(lado.getY() + yc);
        }

        for (Ponto lado : original.getLados()) {
            lado.setZ(lado.getZ() + zc);
            lado.setY(lado.getY() + yc);
        }
        select.calcCentro();

        select.CalcPerspectiva(pPers, vrpPers, dpPers);
    }
    // fazer o calculo do vetor do teste de visiilidade, add um ponto na classe poligono q seria o vetor normal da face

    public void calcVetorNormal(Poligono p) {
        p.setVetorNormal(produtoVetorial(p.getArestas().get(0), p.getArestas().get(1), p.getArestas().get(2)));
    }

    public void testeDeVisibilidade(Poligono p) {

        p.setVetorNormal(produtoVetorial(p.getArestas().get(0), p.getArestas().get(1), p.getArestas().get(2)));

        Ponto n = new Ponto(vrpPers.getX() - pPers.getX(), vrpPers.getY() - pPers.getY(), vrpPers.getZ() - pPers.getZ());
        Ponto aun = p.getVetorNormal();
        double perp = n.getX() * aun.getX() + n.getY() * aun.getY() + n.getZ() * aun.getZ();

        
        if (p.getVetorNormal().getZ() < 0) {
            p.setVisivelXY(true);
        } else {
            p.setVisivelXY(false);
        }
        if (p.getVetorNormal().getY() < 0) {
            p.setVisivelXZ(true);
        } else {
            p.setVisivelXZ(false);
        }
        if (p.getVetorNormal().getX() < 0) {
            p.setVisivelYZ(true);
        } else {
            p.setVisivelYZ(false);
        }

        if (perp < 0) {
            p.setVisivelPers(true);
        } else {
            p.setVisivelPers(false);
        }

    }

    public Ponto produtoVetorial(Ponto zero, Ponto um, Ponto dois) {
        Ponto ret = new Ponto();
        Ponto a = new Ponto();
        Ponto b = new Ponto();

        a.setXYZ((zero.getX() - um.getX()), (zero.getY() - um.getY()), (zero.getZ() - um.getZ()));
        b.setXYZ((dois.getX() - um.getX()), (dois.getY() - um.getY()), (dois.getZ() - um.getZ()));

        ret.setXYZ((b.getZ() * a.getY() - a.getZ() * b.getY()), (a.getZ() * b.getX() - a.getX() * b.getZ()), (a.getX() * b.getY() - a.getY() * b.getX()));

        return ret;
    }

    public void sombreamentoConstante(GraphicsContext gc, RGB Ila, RGB Il, Ponto luzIncidente, Poligono select) {
        select.sombreamentoConstante(gc, Ila, Il,luzIncidente );
    }

    public void calcPerpectiva(Poligono p) {
        p.CalcPerspectiva(pPers, vrpPers, dpPers);
    }

}
