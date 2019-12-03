/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cg_trab1.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author patri
 */
public class Poligono implements Cloneable, Serializable {

    private LinkedList<Ponto> arestas;
    private LinkedList<Ponto> arestasPerspectiva;

    private int nLados;
    private Ponto centro;
    private double raio;
    private double angulo;
    private boolean fechado;
    //private Color corBorda;
    //private Color corFundo;
    private boolean visivelXY;
    private boolean visivelXZ;
    private boolean visivelYZ;
    private boolean visivelPers;
    private Ponto vetorNormal;


    private RGB ka;
    private RGB kd;
    private RGB ks;
    private double n;
    
    private String corBorda;
    private String corFundo;
    private String corLuz;

    public Poligono(int nLados, Ponto centro, double raio, Color cor1) {
        arestasPerspectiva = new LinkedList<>();
        this.nLados = nLados;
        this.centro = centro;
        this.raio = raio;
        this.angulo = Math.toRadians(Double.valueOf(360.0) / this.nLados);
        this.arestas = new LinkedList<>();
        this.fechado = true;
        this.corBorda = cor1.toString();
        
        this.visivelXY = true;
        this.visivelXZ = true;
        this.visivelYZ = true;
        this.visivelPers = true;
        this.ka = new RGB();
        this.ks = new RGB();
        this.kd = new RGB();
        //this.corFundo = Color.BLACK;
    }

    public Poligono() {
        arestasPerspectiva = new LinkedList<>();
        this.arestas = new LinkedList<>();
        this.nLados = 0;
        this.fechado = true;
        this.corBorda = Color.BLACK.toString();
        this.visivelXY = true;
        this.visivelXZ = true;
        this.visivelYZ = true;
        this.visivelPers = true;
        this.ka = new RGB();
        this.ks = new RGB();
        this.kd = new RGB();

    }

    public void setFechado(boolean fechado) {
        this.fechado = fechado;
    }

    public void addPonto(Ponto p) {
        this.arestas.add(p);
    }

    public void remPonto(Ponto p) {
        this.arestas.remove(p);
    }

    public int getnLados() {
        return nLados;
    }

    public double getAngulo() {
        return angulo;
    }

    public void incNLados() {
        this.nLados++;
    }

    public LinkedList<Ponto> getLados() {
        return arestas;
    }

    public Ponto getCentro() {
        return centro;
    }

    public double getRaio() {
        return raio;
    }

    public boolean isVisivelXY() {
        return visivelXY;
    }

    public void setVisivelXY(boolean visivelXY) {
        this.visivelXY = visivelXY;
    }

    public boolean isVisivelXZ() {
        return visivelXZ;
    }

    public void setVisivelXZ(boolean visivelXZ) {
        this.visivelXZ = visivelXZ;
    }

    public boolean isVisivelYZ() {
        return visivelYZ;
    }

    public void setVisivelYZ(boolean visivelYZ) {
        this.visivelYZ = visivelYZ;
    }

    public LinkedList<Ponto> getArestasPerspectiva() {
        return arestasPerspectiva;
    }

    public void setArestasPerspectiva(LinkedList<Ponto> arestasPerspectiva) {
        this.arestasPerspectiva = arestasPerspectiva;
    }

    public boolean isVisivelPers() {
        return visivelPers;
    }

    public void setVisivelPers(boolean visivelPers) {
        this.visivelPers = visivelPers;
    }

    

    public RGB getKa() {
        return ka;
    }

    public void setKa(RGB ka) {
        this.ka = ka;
    }

    public RGB getKd() {
        return kd;
    }

    public void setKd(RGB kd) {
        this.kd = kd;
    }

    public RGB getKs() {
        return ks;
    }

    public void setKs(RGB ks) {
        this.ks = ks;
    }

    public String getCorLuz() {
        return corLuz;
    }

    public void setCorLuz(String corLuz) {
        this.corLuz = corLuz;
    }

    public String getCorBorda() {
        return corBorda;
    }

    public void setCorBorda(Color corBorda) {
        this.corBorda = corBorda.toString();
    }

    public String getCorFundo() {
        return corFundo;
    }

    public void setCorFundo(Color corFundo) {
        this.corFundo = corFundo.toString();
    }

    public Ponto getVetorNormal() {
        return vetorNormal;
    }

    public void setVetorNormal(Ponto vetorNormal) {
        this.vetorNormal = vetorNormal;
    }

    public Ponto multiMatrizXY(Ponto p) {
        Ponto ret = new Ponto();

        double x = (p.getX() * Math.cos(this.angulo)) + (p.getY() * Math.sin(this.angulo));
        double y = (p.getX() * -Math.sin(this.angulo)) + (p.getY() * Math.cos(this.angulo));

        ret.setXY(x, y);

        return ret;
    }

    public Ponto multiMatrizXZ(Ponto p) {
        Ponto ret = new Ponto();

        double x = (p.getX() * Math.cos(this.angulo)) + (p.getZ() * Math.sin(this.angulo));
        double z = (p.getX() * -Math.sin(this.angulo)) + (p.getZ() * Math.cos(this.angulo));

        ret.setXZ(x, z);

        return ret;
    }

    public Ponto multiMatrizYZ(Ponto p) {
        Ponto ret = new Ponto();

        double z = (p.getZ() * Math.cos(this.angulo)) + (p.getY() * Math.sin(this.angulo));
        double y = (p.getZ() * -Math.sin(this.angulo)) + (p.getY() * Math.cos(this.angulo));

        ret.setYZ(y, z);

        return ret;
    }

    public void multiMatriz2XY(Ponto p) {
        double x = (p.getX() * Math.cos(this.angulo)) + (p.getY() * Math.sin(this.angulo));
        double y = (p.getX() * -Math.sin(this.angulo)) + (p.getY() * Math.cos(this.angulo));

        p.setXY(x, y);

    }

    public void multiMatriz2XZ(Ponto p) {
        double x = (p.getX() * Math.cos(this.angulo)) + (p.getZ() * Math.sin(this.angulo));
        double z = (p.getX() * -Math.sin(this.angulo)) + (p.getZ() * Math.cos(this.angulo));

        p.setXZ(x, z);

    }

    public void multiMatriz2YZ(Ponto p) {
        double z = (p.getZ() * Math.cos(this.angulo)) + (p.getY() * Math.sin(this.angulo));
        double y = (p.getZ() * -Math.sin(this.angulo)) + (p.getY() * Math.cos(this.angulo));

        p.setYZ(y, z);

    }

    public void GerarpoligonoXY() {

        Ponto p = new Ponto(0, -this.raio, 0);
        this.arestas.add(p);

        for (int n = this.nLados; n > 1; n--) {
            p = multiMatrizXY(this.arestas.getLast());
            this.arestas.add(p);
        }

        for (Ponto aresta : arestas) {
            aresta.setX(this.centro.getX() + aresta.getX());
            aresta.setY(this.centro.getY() + aresta.getY());
        }
    }

    public void GerarpoligonoXZ() {

        Ponto p = new Ponto(0, 0, -this.raio);
        this.arestas.add(p);

        for (int n = this.nLados; n > 1; n--) {
            p = multiMatrizXZ(this.arestas.getLast());
            this.arestas.add(p);
        }

        for (Ponto aresta : arestas) {
            aresta.setX(this.centro.getX() + aresta.getX());
            aresta.setZ(this.centro.getZ() + aresta.getZ());
        }

    }

    public void GerarpoligonoYZ() {

        Ponto p = new Ponto(0, -this.raio, 0);
        this.arestas.add(p);

        for (int n = this.nLados; n > 1; n--) {
            p = multiMatrizYZ(this.arestas.getLast());
            this.arestas.add(p);
        }

        for (Ponto aresta : arestas) {
            aresta.setZ(this.centro.getZ() + aresta.getZ());
            aresta.setY(this.centro.getY() + aresta.getY());
        }
    }

    public void drawXY(GraphicsContext c) {

        if (visivelXY) {
            if (this.corLuz != null) {
                sombreamentoConstanteFillXY(c);
            } else {
                if (this.corFundo != null) {
                    autoFillXY(c);
                }
            }

            Ponto p = null;

            c.setStroke(Color.web(corBorda));
            for (Ponto ponto : arestas) {

                if (p == null) {
                    p = ponto;
                    continue;
                }

                c.strokeLine(p.getX(), p.getY(), ponto.getX(), ponto.getY());

                p = ponto;
            }
            if (fechado) {
                c.strokeLine(p.getX(), p.getY(), this.arestas.getFirst().getX(), this.arestas.getFirst().getY());
            }
        }

    }

    public void drawYZ(GraphicsContext c) {

        if (visivelYZ) {
            if (this.corLuz != null) {
                sombreamentoConstanteFillXY(c);
            } else {
                if (this.corFundo != null) {
                    autoFillYZ(c);
                }
            }

            Ponto p = null;

            c.setStroke(Color.web(corBorda));
            for (Ponto ponto : arestas) {

                if (p == null) {
                    p = ponto;
                    continue;
                }

                c.strokeLine(p.getZ(), p.getY(), ponto.getZ(), ponto.getY());

                p = ponto;
            }
            if (fechado) {
                c.strokeLine(p.getZ(), p.getY(), this.arestas.getFirst().getZ(), this.arestas.getFirst().getY());
            }
        }

    }

    public void drawXZ(GraphicsContext c) {

        if (visivelXZ) {
            if (this.corLuz != null) {
                sombreamentoConstanteFillXY(c);
            } else {
                if (this.corFundo != null) {
                    autoFillXZ(c);
                }
            }

            Ponto p = null;

            c.setStroke(Color.web(corBorda));
            for (Ponto ponto : arestas) {

                if (p == null) {
                    p = ponto;
                    continue;
                }

                c.strokeLine(p.getX(), p.getZ(), ponto.getX(), ponto.getZ());

                p = ponto;
            }
            if (fechado) {
                c.strokeLine(p.getX(), p.getZ(), this.arestas.getFirst().getX(), this.arestas.getFirst().getZ());
            }
        }

    }

    public void drawSelectPerspectiva(GraphicsContext c) {
        if (visivelPers) {
            Ponto p = null;

            if (this.corFundo != null) {
                autoFillPerspec(c);
            }

            c.setStroke(Color.RED);
            for (Ponto ponto : arestasPerspectiva) {

                if (p == null) {
                    p = ponto;
                    continue;
                }

                c.strokeLine(p.getX(), p.getY(), ponto.getX(), ponto.getY());

                p = ponto;
            }
            if (fechado) {
                c.strokeLine(p.getX(), p.getY(), this.arestasPerspectiva.getFirst().getX(), this.arestasPerspectiva.getFirst().getY());
            }
        }

    }

    public void drawPerspectiva(GraphicsContext c) {
        if (visivelPers) {
            if (this.corFundo != null) {
                autoFillPerspec(c);
            }
            Ponto p = null;

            c.setStroke(Color.web(corBorda));
            for (Ponto ponto : arestasPerspectiva) {

                if (p == null) {
                    p = ponto;
                    continue;
                }

                c.strokeLine(p.getX(), p.getY(), ponto.getX(), ponto.getY());

                p = ponto;
            }
            if (fechado) {
                c.strokeLine(p.getX(), p.getY(), this.arestasPerspectiva.getFirst().getX(), this.arestasPerspectiva.getFirst().getY());
            }
        }

    }

    public void CalcPerspectiva(Ponto p, Ponto vrp, double dp) {
        
        arestasPerspectiva = new LinkedList<>();
        //public void CalcPerspectiva() {

        //pontos devem ser mutados no canvas
        /* p = new Ponto(0, 0, 0);
        vrp = new Ponto(0, 0, 500);
        dp = 350;*/
        double aux;
        Ponto n = new Ponto(vrp.getX() - p.getX(), vrp.getY() - p.getY(), vrp.getZ() - p.getZ());
        n.normalizarVetor();
        Ponto y = new Ponto(0, 1, 0);

        Ponto v = new Ponto();

        v.setX(y.getX() - (n.getY()) * n.getX());
        v.setY(y.getY() - (n.getY()) * n.getY());
        v.setZ(y.getZ() - (n.getY()) * n.getZ());
        v.normalizarVetor();

        Ponto u = new Ponto();
        u.setXYZ((n.getZ() * v.getY() - v.getZ() * n.getY()), (v.getZ() * n.getX() - v.getX() * n.getZ()), (v.getX() * n.getY() - v.getY() * n.getX()));

        for (Ponto aresta : arestas) {
            arestasPerspectiva.add(aresta.getClone());
        }

        for (Ponto aresta : arestasPerspectiva) {
            SRUtoSRC(vrp, u, v, n, aresta);
            aux = (-1 / dp) * aresta.getZ();

            aresta.setX(aresta.getX() / aux);
            aresta.setY(aresta.getY() / aux);
            aresta.setZ(aresta.getZ() / aux);
        }
    }

    public void drawSelectXY(GraphicsContext c) {
        if (visivelXY) {
            Ponto p = null;

            if (this.corFundo != null) {
                autoFillXY(c);
            }

            c.setStroke(Color.RED);
            for (Ponto ponto : arestas) {

                if (p == null) {
                    p = ponto;
                    continue;
                }

                c.strokeLine(p.getX(), p.getY(), ponto.getX(), ponto.getY());

                p = ponto;
            }
            if (fechado) {
                c.strokeLine(p.getX(), p.getY(), this.arestas.getFirst().getX(), this.arestas.getFirst().getY());
            }
        }

    }

    public void drawSelectXZ(GraphicsContext c) {
        if (visivelXZ) {
            Ponto p = null;

            if (this.corFundo != null) {
                autoFillXZ(c);
            }

            c.setStroke(Color.RED);
            for (Ponto ponto : arestas) {

                if (p == null) {
                    p = ponto;
                    continue;
                }

                c.strokeLine(p.getX(), p.getZ(), ponto.getX(), ponto.getZ());

                p = ponto;
            }
            if (fechado) {
                c.strokeLine(p.getX(), p.getZ(), this.arestas.getFirst().getX(), this.arestas.getFirst().getZ());
            }
        }
    }

    public void drawSelectYZ(GraphicsContext c) {
        if (visivelYZ) {

            Ponto p = null;

            if (this.corFundo != null) {
                autoFillYZ(c);
            }

            c.setStroke(Color.RED);
            for (Ponto ponto : arestas) {

                if (p == null) {
                    p = ponto;
                    continue;
                }

                c.strokeLine(p.getZ(), p.getY(), ponto.getZ(), ponto.getY());

                p = ponto;
            }
            if (fechado) {
                c.strokeLine(p.getZ(), p.getY(), this.arestas.getFirst().getZ(), this.arestas.getFirst().getY());
            }
        }
    }

    public void fecharPoligono() {
        this.fechado = true;

    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }

    public void setArestas(LinkedList<Ponto> arestas) {
        this.arestas = arestas;
    }

    public void setnLados(int nLados) {
        this.nLados = nLados;
    }

    public void setCentro(Ponto centro) {
        this.centro = centro;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }

    public LinkedList<Ponto> getArestas() {
        return arestas;
    }

    public boolean isFechado() {
        return fechado;
    }

    private void criarLista(LinkedList<Lista> l1, double max, double min) {
        for (int i = (int) min; i < (int) max; i++) {
            l1.add(new Lista(i));
        }
    }

    private void addIndex(LinkedList<Lista> l1, int index, double x) {
        l1.get(index).add(x);
    }

    public void autoFillXY(GraphicsContext c) {

        LinkedList<Lista> lista = new LinkedList<Lista>();
        double maior = -1, menor = 10000000, max, min, ca;

        for (Ponto aresta : arestas) {
            if (aresta.getY() < menor) {
                menor = aresta.getY();
            }
            if (aresta.getY() > maior) {
                maior = aresta.getY();
            }
        }

        menor = Math.round(menor);
        maior = Math.round(maior);
        criarLista(lista, maior, menor);

        double r, s;

        Ponto p1, p2;

        for (int i = 1, j = 0; j < nLados; i++, j++) {

            if (i == nLados) {
                i = 0;
            }
            p1 = arestas.get(j);
            p2 = arestas.get(i);

            ca = 1 / ((p2.getY() - p1.getY()) / (p2.getX() - p1.getX()));

            max = Math.max(p1.getY(), p2.getY());
            min = Math.min(p1.getY(), p2.getY());

            if (p1.getY() == p2.getY()) {
                continue;
            } else {
                if (min == arestas.get(i).getY()) {
                    p2 = arestas.get(j);
                    p1 = arestas.get(i);
                } else {
                    if (min == arestas.get(j).getY()) {
                        p1 = arestas.get(j);
                        p2 = arestas.get(i);
                    }

                }
            }

            min = Math.round(min);
            max = Math.round(max);

            double xmca = p1.getX();
            //addIndex(lista, (int) (min - min), p1.getX());
            for (int y = (int) min; y < (int) max; y++, xmca += ca) {

                addIndex(lista, (int) (y - menor), xmca);
            }
        }

        c.setStroke(Color.web(corFundo));
        for (Lista lista1 : lista) {
            Collections.sort(lista1.getLista());
        }

        for (Lista pontos : lista) {
            for (int i = 0; i < pontos.getLista().size(); i += 2) {
                double x1, x2;

                x1 = pontos.getLista().get(i);
                x2 = pontos.getLista().get(i + 1);
                c.strokeLine(x1, pontos.getY(), x2, pontos.getY());

            }
        }
        lista.clear();
    }

    public void autoFillPerspec(GraphicsContext c) {

        LinkedList<Lista> lista = new LinkedList<Lista>();
        double maior = -1, menor = 10000000, max, min, ca;

        for (Ponto aresta : arestasPerspectiva) {
            if (aresta.getY() < menor) {
                menor = aresta.getY();
            }
            if (aresta.getY() > maior) {
                maior = aresta.getY();
            }
        }

        menor = Math.round(menor);
        maior = Math.round(maior);
        criarLista(lista, maior, menor);

        double r, s;

        Ponto p1, p2;

        for (int i = 1, j = 0; j < nLados; i++, j++) {

            if (i == nLados) {
                i = 0;
            }
            p1 = arestasPerspectiva.get(j);
            p2 = arestasPerspectiva.get(i);

            ca = 1 / ((p2.getY() - p1.getY()) / (p2.getX() - p1.getX()));

            max = Math.max(p1.getY(), p2.getY());
            min = Math.min(p1.getY(), p2.getY());

            if (p1.getY() == p2.getY()) {
                continue;
            } else {
                if (min == arestasPerspectiva.get(i).getY()) {
                    p2 = arestasPerspectiva.get(j);
                    p1 = arestasPerspectiva.get(i);
                } else {
                    if (min == arestasPerspectiva.get(j).getY()) {
                        p1 = arestasPerspectiva.get(j);
                        p2 = arestasPerspectiva.get(i);
                    }

                }
            }

            min = Math.round(min);
            max = Math.round(max);

            double xmca = p1.getX();
            //addIndex(lista, (int) (min - min), p1.getX());
            for (int y = (int) min; y < (int) max; y++, xmca += ca) {

                addIndex(lista, (int) (y - menor), xmca);
            }
        }

        c.setStroke(Color.web(corFundo));
        for (Lista lista1 : lista) {
            Collections.sort(lista1.getLista());
        }

        for (Lista pontos : lista) {
            for (int i = 0; i < pontos.getLista().size(); i += 2) {
                double x1, x2;

                x1 = pontos.getLista().get(i);
                x2 = pontos.getLista().get(i + 1);
                c.strokeLine(x1, pontos.getY(), x2, pontos.getY());

            }
        }
        lista.clear();
    }

    public void autoFillXZ(GraphicsContext c) {

        LinkedList<Lista> lista = new LinkedList<Lista>();
        double maior = -1, menor = 10000000, max, min, ca;

        for (Ponto aresta : arestas) {
            if (aresta.getZ() < menor) {
                menor = aresta.getZ();
            }
            if (aresta.getZ() > maior) {
                maior = aresta.getZ();
            }
        }

        menor = Math.round(menor);
        maior = Math.round(maior);
        criarLista(lista, maior, menor);

        double r, s;

        Ponto p1, p2;

        for (int i = 1, j = 0; j < nLados; i++, j++) {

            if (i == nLados) {
                i = 0;
            }
            p1 = arestas.get(j);
            p2 = arestas.get(i);

            ca = 1 / ((p2.getZ() - p1.getZ()) / (p2.getX() - p1.getX()));

            max = Math.max(p1.getZ(), p2.getZ());
            min = Math.min(p1.getZ(), p2.getZ());

            if (p1.getZ() == p2.getZ()) {
                continue;
            } else {
                if (min == arestas.get(i).getZ()) {
                    p2 = arestas.get(j);
                    p1 = arestas.get(i);
                } else {
                    if (min == arestas.get(j).getZ()) {
                        p1 = arestas.get(j);
                        p2 = arestas.get(i);
                    }

                }
            }

            min = Math.round(min);
            max = Math.round(max);

            double xmca = p1.getX();
            //addIndex(lista, (int) (min - min), p1.getX());
            for (int z = (int) min; z < (int) max; z++, xmca += ca) {

                addIndex(lista, (int) (z - menor), xmca);
            }
        }

        c.setStroke(Color.web(corFundo));
        for (Lista lista1 : lista) {
            Collections.sort(lista1.getLista());
        }

        for (Lista pontos : lista) {
            for (int i = 0; i < pontos.getLista().size(); i += 2) {
                double x1, x2;

                x1 = pontos.getLista().get(i);
                x2 = pontos.getLista().get(i + 1);
                c.strokeLine(x1, pontos.getY(), x2, pontos.getY()); //gerar getter e setter??

            }
        }
        lista.clear();
    }

    public void autoFillYZ(GraphicsContext c) {

        LinkedList<Lista> lista = new LinkedList<Lista>();
        double maior = -1, menor = 10000000, max, min, ca;

        for (Ponto aresta : arestas) {
            if (aresta.getY() < menor) {
                menor = aresta.getY();
            }
            if (aresta.getY() > maior) {
                maior = aresta.getY();
            }
        }

        menor = Math.round(menor);
        maior = Math.round(maior);
        criarLista(lista, maior, menor);

        double r, s;

        Ponto p1, p2;

        for (int i = 1, j = 0; j < nLados; i++, j++) {

            if (i == nLados) {
                i = 0;
            }
            p1 = arestas.get(j);
            p2 = arestas.get(i);

            ca = 1 / ((p2.getY() - p1.getY()) / (p2.getZ() - p1.getZ()));

            max = Math.max(p1.getY(), p2.getY());
            min = Math.min(p1.getY(), p2.getY());

            if (p1.getY() == p2.getY()) {
                continue;
            } else {
                if (min == arestas.get(i).getY()) {
                    p2 = arestas.get(j);
                    p1 = arestas.get(i);
                } else {
                    if (min == arestas.get(j).getY()) {
                        p1 = arestas.get(j);
                        p2 = arestas.get(i);
                    }

                }
            }

            min = Math.round(min);
            max = Math.round(max);

            double xmca = p1.getZ();
            //addIndex(lista, (int) (min - min), p1.getX());
            for (int y = (int) min; y < (int) max; y++, xmca += ca) {

                addIndex(lista, (int) (y - menor), xmca);
            }
        }

        c.setStroke(Color.web(corFundo));
        for (Lista lista1 : lista) {
            Collections.sort(lista1.getLista());
        }

        for (Lista pontos : lista) {
            for (int i = 0; i < pontos.getLista().size(); i += 2) {
                double x1, x2;

                x1 = pontos.getLista().get(i);
                x2 = pontos.getLista().get(i + 1);
                c.strokeLine(x1, pontos.getY(), x2, pontos.getY());

            }
        }
        lista.clear();
    }

    public void sombreamentoConstanteFillXY(GraphicsContext c) {
        if (visivelXY) {
            LinkedList<Lista> lista = new LinkedList<Lista>();
            double maior = -1, menor = 10000000, max, min, ca;

            for (Ponto aresta : arestas) {
                if (aresta.getY() < menor) {
                    menor = aresta.getY();
                }
                if (aresta.getY() > maior) {
                    maior = aresta.getY();
                }
            }

            menor = Math.round(menor);
            maior = Math.round(maior);
            criarLista(lista, maior, menor);

            double r, s;

            Ponto p1, p2;

            for (int i = 1, j = 0; j < nLados; i++, j++) {

                if (i == nLados) {
                    i = 0;
                }
                p1 = arestas.get(j);
                p2 = arestas.get(i);

                ca = 1 / ((p2.getY() - p1.getY()) / (p2.getX() - p1.getX()));

                max = Math.max(p1.getY(), p2.getY());
                min = Math.min(p1.getY(), p2.getY());

                if (p1.getY() == p2.getY()) {
                    continue;
                } else {
                    if (min == arestas.get(i).getY()) {
                        p2 = arestas.get(j);
                        p1 = arestas.get(i);
                    } else {
                        if (min == arestas.get(j).getY()) {
                            p1 = arestas.get(j);
                            p2 = arestas.get(i);
                        }

                    }
                }

                min = Math.round(min);
                max = Math.round(max);

                double xmca = p1.getX();
                //addIndex(lista, (int) (min - min), p1.getX());
                for (int y = (int) min; y < (int) max; y++, xmca += ca) {

                    addIndex(lista, (int) (y - menor), xmca);
                }
            }

            c.setStroke(Color.web(corLuz));
            for (Lista lista1 : lista) {
                Collections.sort(lista1.getLista());
            }

            for (Lista pontos : lista) {
                for (int i = 0; i < pontos.getLista().size(); i += 2) {
                    double x1, x2;

                    x1 = pontos.getLista().get(i);
                    x2 = pontos.getLista().get(i + 1);
                    c.strokeLine(x1, pontos.getY(), x2, pontos.getY());

                }
            }
            lista.clear();
        }
    }

    public void sombreamentoConstanteFillPerspec(GraphicsContext c) {
        if (visivelPers) {
            LinkedList<Lista> lista = new LinkedList<Lista>();
            double maior = -1, menor = 10000000, max, min, ca;

            for (Ponto aresta : arestasPerspectiva) {
                if (aresta.getY() < menor) {
                    menor = aresta.getY();
                }
                if (aresta.getY() > maior) {
                    maior = aresta.getY();
                }
            }

            menor = Math.round(menor);
            maior = Math.round(maior);
            criarLista(lista, maior, menor);

            double r, s;

            Ponto p1, p2;

            for (int i = 1, j = 0; j < nLados; i++, j++) {

                if (i == nLados) {
                    i = 0;
                }
                p1 = arestasPerspectiva.get(j);
                p2 = arestasPerspectiva.get(i);

                ca = 1 / ((p2.getY() - p1.getY()) / (p2.getX() - p1.getX()));

                max = Math.max(p1.getY(), p2.getY());
                min = Math.min(p1.getY(), p2.getY());

                if (p1.getY() == p2.getY()) {
                    continue;
                } else {
                    if (min == arestasPerspectiva.get(i).getY()) {
                        p2 = arestasPerspectiva.get(j);
                        p1 = arestasPerspectiva.get(i);
                    } else {
                        if (min == arestasPerspectiva.get(j).getY()) {
                            p1 = arestasPerspectiva.get(j);
                            p2 = arestasPerspectiva.get(i);
                        }

                    }
                }

                min = Math.round(min);
                max = Math.round(max);

                double xmca = p1.getX();
                //addIndex(lista, (int) (min - min), p1.getX());
                for (int y = (int) min; y < (int) max; y++, xmca += ca) {

                    addIndex(lista, (int) (y - menor), xmca);
                }
            }

            c.setStroke(Color.web(corLuz));
            for (Lista lista1 : lista) {
                Collections.sort(lista1.getLista());
            }

            for (Lista pontos : lista) {
                for (int i = 0; i < pontos.getLista().size(); i += 2) {
                    double x1, x2;

                    x1 = pontos.getLista().get(i);
                    x2 = pontos.getLista().get(i + 1);
                    c.strokeLine(x1, pontos.getY(), x2, pontos.getY());

                }
            }
            lista.clear();
        }
    }

    public double getN() {
        return n;
    }

    public void setN(double n) {
        this.n = n;
    }
    
    

    public void sombreamentoConstanteFillXZ(GraphicsContext c) {
        if (visivelXZ) {
            LinkedList<Lista> lista = new LinkedList<Lista>();
            double maior = -1, menor = 10000000, max, min, ca;

            for (Ponto aresta : arestas) {
                if (aresta.getZ() < menor) {
                    menor = aresta.getZ();
                }
                if (aresta.getZ() > maior) {
                    maior = aresta.getZ();
                }
            }

            menor = Math.round(menor);
            maior = Math.round(maior);
            criarLista(lista, maior, menor);

            double r, s;

            Ponto p1, p2;

            for (int i = 1, j = 0; j < nLados; i++, j++) {

                if (i == nLados) {
                    i = 0;
                }
                p1 = arestas.get(j);
                p2 = arestas.get(i);

                ca = 1 / ((p2.getZ() - p1.getZ()) / (p2.getX() - p1.getX()));

                max = Math.max(p1.getZ(), p2.getZ());
                min = Math.min(p1.getZ(), p2.getZ());

                if (p1.getZ() == p2.getZ()) {
                    continue;
                } else {
                    if (min == arestas.get(i).getZ()) {
                        p2 = arestas.get(j);
                        p1 = arestas.get(i);
                    } else {
                        if (min == arestas.get(j).getZ()) {
                            p1 = arestas.get(j);
                            p2 = arestas.get(i);
                        }

                    }
                }

                min = Math.round(min);
                max = Math.round(max);

                double xmca = p1.getX();
                //addIndex(lista, (int) (min - min), p1.getX());
                for (int z = (int) min; z < (int) max; z++, xmca += ca) {

                    addIndex(lista, (int) (z - menor), xmca);
                }
            }

            c.setStroke(Color.web(corLuz));
            for (Lista lista1 : lista) {
                Collections.sort(lista1.getLista());
            }

            for (Lista pontos : lista) {
                for (int i = 0; i < pontos.getLista().size(); i += 2) {
                    double x1, x2;

                    x1 = pontos.getLista().get(i);
                    x2 = pontos.getLista().get(i + 1);
                    c.strokeLine(x1, pontos.getY(), x2, pontos.getY()); //gerar getter e setter??

                }
            }
            lista.clear();
        }
    }

    public void sombreamentoConstanteFillYZ(GraphicsContext c) {
        if (visivelYZ) {
            LinkedList<Lista> lista = new LinkedList<Lista>();
            double maior = -1, menor = 10000000, max, min, ca;

            for (Ponto aresta : arestas) {
                if (aresta.getY() < menor) {
                    menor = aresta.getY();
                }
                if (aresta.getY() > maior) {
                    maior = aresta.getY();
                }
            }

            menor = Math.round(menor);
            maior = Math.round(maior);
            criarLista(lista, maior, menor);

            double r, s;

            Ponto p1, p2;

            for (int i = 1, j = 0; j < nLados; i++, j++) {

                if (i == nLados) {
                    i = 0;
                }
                p1 = arestas.get(j);
                p2 = arestas.get(i);

                ca = 1 / ((p2.getY() - p1.getY()) / (p2.getZ() - p1.getZ()));

                max = Math.max(p1.getY(), p2.getY());
                min = Math.min(p1.getY(), p2.getY());

                if (p1.getY() == p2.getY()) {
                    continue;
                } else {
                    if (min == arestas.get(i).getY()) {
                        p2 = arestas.get(j);
                        p1 = arestas.get(i);
                    } else {
                        if (min == arestas.get(j).getY()) {
                            p1 = arestas.get(j);
                            p2 = arestas.get(i);
                        }

                    }
                }

                min = Math.round(min);
                max = Math.round(max);

                double xmca = p1.getZ();
                //addIndex(lista, (int) (min - min), p1.getX());
                for (int y = (int) min; y < (int) max; y++, xmca += ca) {

                    addIndex(lista, (int) (y - menor), xmca);
                }
            }

            c.setStroke(Color.web(corLuz));
            for (Lista lista1 : lista) {
                Collections.sort(lista1.getLista());
            }

            for (Lista pontos : lista) {
                for (int i = 0; i < pontos.getLista().size(); i += 2) {
                    double x1, x2;

                    x1 = pontos.getLista().get(i);
                    x2 = pontos.getLista().get(i + 1);
                    c.strokeLine(x1, pontos.getY(), x2, pontos.getY());

                }
            }
            lista.clear();
        }
    }

    public void sombreamentoConstante(GraphicsContext gc, RGB Ila, RGB Il, Ponto luzIncidente) {

        ka.setRGB(0.2, 0.5, 0.9);
        kd.setRGB(0.7, 0.9, 0.2);
        ks.setRGB(0.3, 0.9, 0.1);
        double n = 2.15;

        RGB Ia = new RGB(), Id = new RGB(), Is = new RGB(), It = new RGB();;
        Ia.setR(Ila.getR() * ka.getR());
        Ia.setB(Ila.getB() * ka.getB());
        Ia.setG(Ila.getG() * ka.getG());

        double tmp;
        Ponto l = new Ponto(luzIncidente.getX() - centro.getX(), luzIncidente.getY() - centro.getY(), luzIncidente.getZ() - centro.getZ());
        l.normalizarVetor();
        vetorNormal.normalizarVetor();
        tmp = l.getX() * vetorNormal.getX() + l.getY() * vetorNormal.getY() + l.getZ() * vetorNormal.getZ();
        if (tmp > 0) {

            Id.setR(Il.getR() * kd.getR() * tmp);
            Id.setB(Il.getB() * kd.getB() * tmp);
            Id.setG(Il.getG() * kd.getG() * tmp);
            Ponto r = new Ponto();
            //perguntar se o 2 multiplica um vetor ou o resultado final
            tmp = 2 * (vetorNormal.getX() * l.getX() + vetorNormal.getY() * l.getY() + vetorNormal.getZ() * l.getZ());

            r.setXYZ(r.getX() * tmp, r.getY() * tmp, r.getZ() * tmp);

            //perguntar se o vrp e o meso da projeçao
            //perguntar qual e o valor do vrp;
            Ponto vrp = new Ponto();

            Ponto s = new Ponto();

            s.setXYZ(vrp.getX() - centro.getX(), vrp.getY() - centro.getY(), vrp.getZ() - centro.getZ());

            s.normalizarVetor();

            //precisa fazer a verificaçao se eh maior q zero ????
            //IS ID tbm sao RGB ????
            tmp = (r.getX() * s.getX() + r.getY() * s.getY() + r.getZ() * s.getZ());

            if (tmp > 0) {

                Is.setR(Il.getR() * ks.getR() * Math.pow(tmp, this.n));
                Is.setB(Il.getB() * ks.getB() * Math.pow(tmp, this.n));
                Is.setG(Il.getG() * ks.getG() * Math.pow(tmp, this.n));

                //trar valores maior q 2455
            }
        }

        It.setR(Math.round(Ia.getR() + Id.getR() + Is.getR()));
        It.setB(Math.round(Ia.getB() + Id.getB() + Is.getB()));        
        It.setG(Math.round(Ia.getG() + Id.getG() + Is.getG()));

        this.corLuz = It.toString() + "ff";


    }

    @Override
    public String toString() {

        for (Ponto aresta : arestas) {
            System.out.println(aresta);
        }

        return "Poligonoz\n";
    }

    public Poligono getClone() {
        try {
            Poligono p = (Poligono) super.clone();
            p.setArestas(new LinkedList<>());

            for (Ponto aresta : this.arestas) {
                p.addPonto(aresta.getClone());
            }
            p.setAngulo(getAngulo());
            p.setCentro(centro.getClone());
            p.setFechado(isFechado());
            p.setRaio(getRaio());
            p.setnLados(getnLados());

            return p;

        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void tranladarOrigem() {
        for (Ponto aresta : arestas) {
            aresta.setX(aresta.getX() - centro.getX());
            aresta.setY(aresta.getY() - centro.getY());
            aresta.setZ(aresta.getZ() - centro.getZ());

        }
    }

    public void tranladarOriginal() {
        for (Ponto aresta : arestas) {
            aresta.setX(aresta.getX() + centro.getX());
            aresta.setY(aresta.getY() + centro.getY());
            aresta.setZ(aresta.getZ() + centro.getZ());

        }
    }

    public void calcCentro() {
        this.nLados = arestas.size();
        double maix = 0;
        double maiy = 0;
        double menx = 0;
        double meny = 0;
        double maiz = 0;
        double menz = 0;

        maix = arestas.get(0).getX();
        maiz = arestas.get(0).getZ();
        maiy = arestas.get(0).getY();

        meny = maiy;
        menx = maix;
        menz = maiz;

        for (Ponto aresta : arestas) {
            if (aresta.getX() < menx) {
                menx = aresta.getX();
            }
            if (aresta.getX() > maix) {
                maix = aresta.getX();
            }
            if (aresta.getY() < meny) {
                meny = aresta.getY();
            }
            if (aresta.getY() > maiy) {
                maiy = aresta.getY();
            }
            if (aresta.getZ() < menz) {
                menz = aresta.getZ();
            }
            if (aresta.getZ() > maiz) {
                maiz = aresta.getZ();
            }
        }
        this.centro = new Ponto((maix + menx) / 2, (maiy + meny) / 2, (maiz + menz) / 2);
    }

    private void SRUtoSRC(Ponto vrp, Ponto u, Ponto v, Ponto n, Ponto aresta) {
        Ponto tmp = aresta.getClone();

        aresta.setX(tmp.getX() * u.getX() + tmp.getY() * u.getY() + tmp.getZ() * u.getZ()
                - (vrp.getX() * u.getX() + vrp.getY() * u.getY() + vrp.getZ() * u.getZ()));
        aresta.setY(tmp.getX() * v.getX() + tmp.getY() * v.getY() + tmp.getZ() * v.getZ()
                - (vrp.getX() * v.getX() + vrp.getY() * v.getY() + vrp.getZ() * v.getZ()));
        aresta.setZ(tmp.getX() * n.getX() + tmp.getY() * n.getY() + tmp.getZ() * n.getZ()
                - (vrp.getX() * n.getX() + vrp.getY() * n.getY() + vrp.getZ() * n.getZ()));
    }
}
