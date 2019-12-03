/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cg_trab1.model;

import java.io.Serializable;
import java.util.LinkedList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javax.swing.text.StyledEditorKit;

/**
 *
 * @author Mateus
 */
public class Poliedro implements Cloneable, Serializable {

    private LinkedList<Poligono> faces;
    private boolean ocultarFaces;
    private boolean sobrear;
    private int nParticoes;
    private double angulo;
    private Ponto centro;

    private String corBorda;
    private String corFundo;

    public Poliedro(LinkedList<Poligono> faces, boolean ocultarFaces) {
        this.faces = faces;
        this.ocultarFaces = ocultarFaces;
        this.corBorda = Color.BLACK.toString();
    }

    public Poliedro() {
        this.ocultarFaces = false;
        this.faces = new LinkedList<>();
        this.ocultarFaces = false;
        this.corBorda = Color.BLACK.toString();
    }

    public LinkedList<Poligono> getFaces() {
        return faces;
    }

    public void setFaces(LinkedList<Poligono> faces) {
        this.faces = faces;
    }

    public boolean isOcultarFaces() {
        return ocultarFaces;
    }

    public void setOcultarFaces(boolean ocultarFaces) {
        if (!ocultarFaces) {
            for (Poligono face : faces) {
                face.setVisivelXY(ocultarFaces);
                face.setVisivelXZ(ocultarFaces);
                face.setVisivelYZ(ocultarFaces);
            }
        }
        this.ocultarFaces = ocultarFaces;
    }

    public int getnParticoes() {
        return nParticoes;
    }

    public void setnParticoes(int nParticoes) {
        this.nParticoes = nParticoes;
    }

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }

    public Ponto getCentro() {
        return centro;
    }

    public void setCentro(Ponto centro) {
        this.centro = centro;
    }

    public boolean isSobrear() {
        return sobrear;
    }

    public void setSobrear(boolean sobrear) {
        this.sobrear = sobrear;
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
        for (Poligono face : faces) {
            if (this.corFundo != null) {
                face.setCorFundo(Color.web(this.corFundo));
            }
        }
    }

    public Poligono somaPixelX(Poligono p, double dist) {
        Poligono ret = null;

        for (Ponto aresta : p.getArestas()) {
            aresta.somaPixelX(dist);
        }

        ret = p;

        return ret;
    }

    public Poligono somaPixelY(Poligono p, double dist) {
        Poligono ret = null;

        for (Ponto aresta : p.getArestas()) {
            aresta.somaPixelY(dist);
        }

        ret = p;
        return ret;
    }

    public Poligono somaPixelZ(Poligono p, double dist) {
        Poligono ret = null;

        for (Ponto aresta : p.getArestas()) {
            aresta.somaPixelZ(dist);
        }

        ret = p;
        return ret;
    }

    public Poligono rotacionarX(Poligono p, double ang) {
        double x1, y1, z1, sin, cos;
        cos = Math.cos(ang);
        sin = Math.sin(ang);
        Poligono novo = new Poligono();

        for (Ponto aresta : p.getArestas()) {
            x1 = aresta.getX();
            y1 = cos * aresta.getY() - sin * aresta.getZ();
            z1 = cos * aresta.getZ() + sin * aresta.getY();
            novo.addPonto(new Ponto(x1, y1, z1));
        }

        novo.calcCentro();
        return novo;
    }

    public Poligono rotacionarY(Poligono p, double ang) {
        double x1, y1, z1, sin, cos;
        cos = Math.cos(ang);
        sin = Math.sin(ang);
        Poligono novo = new Poligono();
        for (Ponto aresta : p.getArestas()) {
            x1 = cos * aresta.getX() + sin * aresta.getZ();
            y1 = aresta.getY();
            z1 = cos * aresta.getZ() - sin * aresta.getX();
            novo.addPonto(new Ponto(x1, y1, z1));
        }
        novo.calcCentro();
        return novo;
    }

    public Poligono rotacionarZ(Poligono p, double ang) {
        double x1, y1, z1, sin, cos;
        cos = Math.cos(ang);
        sin = Math.sin(ang);
        Poligono novo = new Poligono();
        for (Ponto aresta : p.getArestas()) {
            x1 = cos * aresta.getX() - sin * aresta.getY();
            y1 = cos * aresta.getY() + sin * aresta.getX();
            z1 = aresta.getZ();
            novo.addPonto(new Ponto(x1, y1, z1));
        }
        novo.calcCentro();

        return novo;
    }

    public void drawXY(GraphicsContext gc) {

        for (Poligono face : faces) {

            face.drawXY(gc);

        }
    }

    public void drawPespec(GraphicsContext gc) {

        for (Poligono face : faces) {

            face.drawPerspectiva(gc);

        }
    }

    public void calcCentro() {
        this.centro = new Ponto();
        double minx, miny, minz, maxx, maxy, maxz;
        minx = this.faces.getFirst().getArestas().getFirst().getX();
        miny = this.faces.getFirst().getArestas().getFirst().getY();
        minz = this.faces.getFirst().getArestas().getFirst().getZ();

        maxx = this.faces.getFirst().getArestas().getFirst().getX();
        maxy = this.faces.getFirst().getArestas().getFirst().getY();
        maxz = this.faces.getFirst().getArestas().getFirst().getZ();

        for (Poligono face : faces) {
            for (Ponto aresta : face.getArestas()) {
                if (minx > aresta.getX()) {
                    minx = aresta.getX();
                }
                if (miny > aresta.getY()) {
                    miny = aresta.getY();
                }
                if (minz > aresta.getZ()) {
                    minz = aresta.getZ();
                }
                if (maxx < aresta.getX()) {
                    maxx = aresta.getX();
                }
                if (maxy < aresta.getY()) {
                    maxy = aresta.getY();
                }
                if (maxz < aresta.getZ()) {
                    maxz = aresta.getZ();
                }

            }
        }

        centro.setXYZ((maxx + minx) / 2, (maxy + miny) / 2, (maxz + minz) / 2);

    }

    public void drawSelectXY(GraphicsContext gc) {

        for (Poligono face : faces) {
            if (this.corFundo != null) {
                face.setCorFundo(Color.web(corFundo));
            }
            face.drawSelectXY(gc);
        }
    }

    public void drawXZ(GraphicsContext gc) {

        for (Poligono face : faces) {
            face.drawXZ(gc);
        }
    }

    public void drawSelectXZ(GraphicsContext gc) {

        for (Poligono face : faces) {

            face.drawSelectXZ(gc);
        }
    }

    public void drawSelectPerspectiva(GraphicsContext gc) {

        for (Poligono face : faces) {

            face.drawSelectPerspectiva(gc);
        }
    }

    public void drawYZ(GraphicsContext gc) {

        for (Poligono face : faces) {

            face.drawYZ(gc);
        }
    }

    public void drawSelectYZ(GraphicsContext gc) {

        for (Poligono face : faces) {

            face.drawSelectYZ(gc);
        }
    }

    public void addFace(Poligono P) {
        faces.add(P);
    }

    public void rotacionarXZ(double angulo) {
        double x1, y1, z1, cos, sin;
        cos = Math.cos(angulo);
        sin = Math.sin(angulo);
        for (Poligono face : faces) {
            for (Ponto aresta : face.getArestas()) {
                x1 = cos * aresta.getX() - sin * aresta.getY();
                y1 = cos * sin * aresta.getX() + cos * cos * aresta.getY() + (-sin * aresta.getZ());
                z1 = sin * sin * aresta.getX() + sin * cos * aresta.getY() + cos * aresta.getZ();
                aresta.setXYZ(x1, y1, z1);
            }
        }
    }

    public void rotacionarXY(double angulo) {
        double x1, y1, z1, cos, sin;
        cos = Math.cos(angulo);
        sin = Math.sin(angulo);
        for (Poligono face : faces) {
            for (Ponto aresta : face.getArestas()) {
                x1 = cos * aresta.getX() + sin * aresta.getZ();
                y1 = sin * sin * aresta.getX() + cos * aresta.getY() + (-sin * cos * aresta.getZ());
                z1 = -sin * cos * aresta.getX() + sin * aresta.getY() + cos * cos * aresta.getZ();
                aresta.setXYZ(x1, y1, z1);
            }
        }
    }

    public void rotacionarYZ(double angulo) {
        double x1, y1, z1, cos, sin;
        cos = Math.cos(angulo);
        sin = Math.sin(angulo);
        for (Poligono face : faces) {
            for (Ponto aresta : face.getArestas()) {
                x1 = cos * cos * aresta.getX() + cos * sin * aresta.getY() + sin * aresta.getZ();
                y1 = sin * aresta.getX() + cos * aresta.getY();
                z1 = sin * cos * aresta.getX() + sin * sin * aresta.getY() + cos * aresta.getZ();
                aresta.setXYZ(x1, y1, z1);
            }
        }
    }

    public void rotacionarX2(double ang) {
        double x1, y1, z1, cos, sin;
        cos = Math.cos(ang);
        sin = Math.sin(ang);
        for (Poligono face : faces) {
            for (Ponto aresta : face.getArestas()) {
                x1 = aresta.getX();
                y1 = cos * aresta.getY() - sin * aresta.getZ();
                z1 = cos * aresta.getZ() + sin * aresta.getY();

                aresta.setXYZ(x1, y1, z1);

            }
        }
    }

    public void rotacionarY2(double ang) {
        double x1, y1, z1, cos, sin;
        cos = Math.cos(ang);
        sin = Math.sin(ang);
        for (Poligono face : faces) {
            for (Ponto aresta : face.getArestas()) {
                x1 = cos * aresta.getX() + sin * aresta.getZ();
                y1 = aresta.getY();
                z1 = cos * aresta.getZ() - sin * aresta.getX();

                aresta.setXYZ(x1, y1, z1);

            }
        }
    }

    public void rotacionarZ2(double ang) {
        double x1, y1, z1, cos, sin;
        cos = Math.cos(ang);
        sin = Math.sin(ang);
        for (Poligono face : faces) {
            for (Ponto aresta : face.getArestas()) {
                x1 = cos * aresta.getX() - sin * aresta.getY();
                y1 = cos * aresta.getY() + sin * aresta.getX();
                z1 = aresta.getZ();

                aresta.setXYZ(x1, y1, z1);

            }
        }
    }

    public void transOrigem() {
        for (Poligono face : faces) {
            for (Ponto aresta : face.getArestas()) {
                aresta.subPonto(centro);
            }
        }
    }

    public void transOriginal() {
        for (Poligono face : faces) {
            for (Ponto aresta : face.getArestas()) {
                aresta.somPonto(centro);
            }
        }
    }

    public Poliedro getClone() {
        try {
            Poliedro p = (Poliedro) super.clone();
            p.setFaces(new LinkedList<>());

            for (Poligono face : faces) {

                p.addFace(face.getClone());
            }
            p.setAngulo(getAngulo());
            p.setCentro(centro.getClone());
            /*p.setCorBorda(Color.web(corBorda));
            p.setCorFundo(Color.web(corFundo));*/
            p.setOcultarFaces(isOcultarFaces());
            p.setnParticoes(nParticoes);
            return p;

        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void autoFillXY(GraphicsContext gc) {
        for (Poligono face : faces) {
            if (this.corFundo != null) {
                face.setCorFundo(Color.web(corFundo));
            }
            if (face.isVisivelXY()) {
                face.autoFillXY(gc);
            }
        }

    }

    public void autoFillYZ(GraphicsContext gc) {
        for (Poligono face : faces) {
            if (this.corFundo != null) {
                face.setCorFundo(Color.web(corFundo));
            }

            if (face.isVisivelYZ()) {
                face.autoFillYZ(gc);
            }
        }

    }

    public void autoFillXZ(GraphicsContext gc) {
        for (Poligono face : faces) {
            if (this.corFundo != null) {
                face.setCorFundo(Color.web(corFundo));
            }

            if (face.isVisivelXZ()) {
                face.autoFillXZ(gc);
            }
        }
    }

    public void sombreamentoConstanteXY(GraphicsContext gc, RGB Ila, RGB Il, Ponto luzIncidente) {

        for (Poligono face : faces) {
            face.sombreamentoConstante(gc, Ila, Il,luzIncidente);
            face.sombreamentoConstanteFillXY(gc);

        }

    }

    public void sombreamentoConstanteXZ(GraphicsContext gc, RGB Ila, RGB Il, Ponto luzIncidente) {

        for (Poligono face : faces) {
            face.sombreamentoConstante(gc, Ila, Il,luzIncidente);
            face.sombreamentoConstanteFillXZ(gc);

        }

    }

    public void sombreamentoConstanteYZ(GraphicsContext gc, RGB Ila, RGB Il, Ponto luzIncidente) {

        for (Poligono face : faces) {
             face.sombreamentoConstante(gc, Ila, Il,luzIncidente);
            face.sombreamentoConstanteFillYZ(gc);
        }

    }

    public void sombreamentoConstantePerspec(GraphicsContext gc, RGB Ila, RGB Il, Ponto luzIncidente) {

        for (Poligono face : faces) {
             face.sombreamentoConstante(gc, Ila, Il,luzIncidente);
            face.sombreamentoConstanteFillPerspec(gc);
        }

    }

    public void sombreamentoConstanteSelectXY(GraphicsContext gc, RGB Ila, RGB Il, Ponto luzIncidente) {

        for (Poligono face : faces) {
             face.sombreamentoConstante(gc, Ila, Il,luzIncidente);
            face.sombreamentoConstanteFillXY(gc);
            face.drawSelectXY(gc);
        }

    }

    public void sombreamentoConstanteSelectXZ(GraphicsContext gc, RGB Ila, RGB Il, Ponto luzIncidente) {

        for (Poligono face : faces) {
             face.sombreamentoConstante(gc, Ila, Il,luzIncidente);
            face.sombreamentoConstanteFillXZ(gc);
            face.drawSelectXZ(gc);

        }

    }

    public void sombreamentoConstanteSelectYZ(GraphicsContext gc, RGB Ila, RGB Il, Ponto luzIncidente) {

        for (Poligono face : faces) {
             face.sombreamentoConstante(gc, Ila, Il,luzIncidente);
            face.sombreamentoConstanteFillYZ(gc);
            face.drawSelectYZ(gc);
        }

    }

    public void sombreamentoConstanteSelectPerspec(GraphicsContext gc, RGB Ila, RGB Il, Ponto luzIncidente) {

        for (Poligono face : faces) {
             face.sombreamentoConstante(gc, Ila, Il,luzIncidente);
            face.sombreamentoConstanteFillPerspec(gc);
            face.drawSelectPerspectiva(gc);
        }

    }
    
    
    public void setKa(double R,double G,double B){
        for (Poligono face : faces) {
            face.getKa().setRGB(R, G, B);
            
        }
    }
    public void setKd(double R,double G,double B){
        for (Poligono face : faces) {
            face.getKd().setRGB(R, G, B);
            
        }
    }
    public void setKs(double R,double G,double B){
        for (Poligono face : faces) {
            face.getKs().setRGB(R, G, B);
            
        }
    }

    public void setN(double n){
        for (Poligono face : faces) {
            face.setN(n);
            
        }
    }
    
    @Override
    public String toString() {
        for (Poligono face : faces) {
            System.out.println(face);
            System.out.println("");
        }
        return "";
    }

}
