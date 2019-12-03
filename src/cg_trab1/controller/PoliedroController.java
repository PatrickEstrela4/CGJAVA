/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cg_trab1.controller;

import cg_trab1.model.Poliedro;
import cg_trab1.model.Poligono;
import cg_trab1.model.Ponto;
import cg_trab1.model.RGB;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Mateus
 */
public class PoliedroController {

    private LinkedList<Poliedro> poliedros;
    private Canvas canvas;
    private Poliedro pole;
    private Poligono pol;
    private Ponto pPers;
    private Ponto vrpPers;
    private double dpPers;

    public PoliedroController(Canvas canvas) {
        this.poliedros = new LinkedList<>();
        this.canvas = canvas;
        this.pPers = new Ponto();
        this.vrpPers = new Ponto();
        this.dpPers = 0;
    }

    public LinkedList<Poliedro> getPoliedros() {
        return poliedros;
    }

    public void setPoliedros(LinkedList<Poliedro> poliedros) {
        this.poliedros = poliedros;
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

    public Poliedro getPole() {
        return pole;
    }

    public void setPole(Poliedro pole) {
        this.pole = pole;
    }

    public Poligono getPol() {
        return pol;
    }

    public void setPol(Poligono pol) {
        this.pol = pol;
    }

    public void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Poliedro poliedro : poliedros) {
            poliedro.drawXY(gc);
        }

        if (pole != null) {
            pole.drawXY(gc);
        }
    }

    public void revolucao(Poligono pol, int nlados, String eixo, double graus) {
        double angulo = graus / nlados;
        double rotaçao = angulo;
        Poliedro poli = new Poliedro();
        int n = pol.getnLados();
        poli.setnParticoes(nlados);

        PoligonoController polControl = new PoligonoController(canvas);
        polControl.setDpPers(dpPers);
        polControl.setVrpPers(vrpPers);
        polControl.setpPers(pPers);
        if ("x".equals(eixo.toLowerCase())) {
            Poligono novo, face, ant;

            if (graus != 360) {
                polControl.calcVetorNormal(pol);
                poli.addFace(pol);
            }

            ant = pol;//.getClone();
            face = new Poligono();

            novo = poli.rotacionarX(pol, Math.toRadians(rotaçao));

            for (int i = 0; i < nlados; i++) {
                for (int j = 0; j < n; j++) {
                    if (j == (n - 1)) {
                        face.addPonto(novo.getArestas().get(j).getClone());
                        face.addPonto(novo.getArestas().get(0).getClone());

                        face.addPonto(ant.getArestas().get(0).getClone());
                        face.addPonto(ant.getArestas().get(j).getClone());
                        face.calcCentro();
                        polControl.calcVetorNormal(face);
                        face.CalcPerspectiva(pPers, vrpPers, dpPers);
                        poli.addFace(face);

                        face = new Poligono();
                        break;
                    }
                    face.addPonto(novo.getArestas().get(j).getClone());
                    face.addPonto(novo.getArestas().get(j + 1).getClone());

                    face.addPonto(ant.getArestas().get(j + 1).getClone());
                    face.addPonto(ant.getArestas().get(j).getClone());

                    face.calcCentro();
                    polControl.calcVetorNormal(face);
                    face.CalcPerspectiva(pPers, vrpPers, dpPers);
                    poli.addFace(face);
                    face = new Poligono();
                }

                ant = novo;
                rotaçao += angulo;
                novo = poli.rotacionarX(pol, Math.toRadians(rotaçao));
            }

            if (graus != 360) {
                Collections.reverse(ant.getArestas());
                polControl.calcVetorNormal(ant);
                ant.CalcPerspectiva(pPers, vrpPers, dpPers);
                poli.addFace(ant);
            }

        }
        if ("y".equals(eixo.toLowerCase())) {
            Poligono novo, face, ant;

            if (graus != 360) {
                polControl.calcVetorNormal(pol);
                poli.addFace(pol);
            }

            ant = pol;//.getClone();
            face = new Poligono();

            novo = poli.rotacionarY(pol, Math.toRadians(rotaçao));

            for (int i = 0; i < nlados; i++) {
                for (int j = 0; j < n; j++) {
                    if (j == (n - 1)) {
                        face.addPonto(novo.getArestas().get(j).getClone());
                        face.addPonto(novo.getArestas().get(0).getClone());

                        face.addPonto(ant.getArestas().get(0).getClone());
                        face.addPonto(ant.getArestas().get(j).getClone());
                        face.calcCentro();
                        polControl.calcVetorNormal(face);
                        face.CalcPerspectiva(pPers, vrpPers, dpPers);
                        poli.addFace(face);

                        face = new Poligono();
                        break;
                    }
                    face.addPonto(novo.getArestas().get(j).getClone());
                    face.addPonto(novo.getArestas().get(j + 1).getClone());

                    face.addPonto(ant.getArestas().get(j + 1).getClone());
                    face.addPonto(ant.getArestas().get(j).getClone());

                    face.calcCentro();
                    polControl.calcVetorNormal(face);
                    face.CalcPerspectiva(pPers, vrpPers, dpPers);
                    poli.addFace(face);
                    face = new Poligono();
                }

                ant = novo;
                rotaçao += angulo;
                novo = poli.rotacionarY(pol, Math.toRadians(rotaçao));
            }

            if (graus != 360) {
                Collections.reverse(ant.getArestas());
                polControl.calcVetorNormal(ant);
                ant.CalcPerspectiva(pPers, vrpPers, dpPers);
                poli.addFace(ant);
            }

        }
        if ("z".equals(eixo.toLowerCase())) {
            Poligono novo, face, ant;

            if (graus != 360) {
                polControl.calcVetorNormal(pol);
                poli.addFace(pol);
            }

            ant = pol;//.getClone();
            face = new Poligono();

            novo = poli.rotacionarZ(pol, Math.toRadians(rotaçao));

            for (int i = 0; i < nlados; i++) {
                for (int j = 0; j < n; j++) {
                    if (j == (n - 1)) {
                        face.addPonto(novo.getArestas().get(j).getClone());
                        face.addPonto(novo.getArestas().get(0).getClone());

                        face.addPonto(ant.getArestas().get(0).getClone());
                        face.addPonto(ant.getArestas().get(j).getClone());
                        face.calcCentro();
                        polControl.calcVetorNormal(face);
                        ant.CalcPerspectiva(pPers, vrpPers, dpPers);
                        poli.addFace(face);

                        face = new Poligono();
                        break;
                    }
                    face.addPonto(novo.getArestas().get(j).getClone());
                    face.addPonto(novo.getArestas().get(j + 1).getClone());

                    face.addPonto(ant.getArestas().get(j + 1).getClone());
                    face.addPonto(ant.getArestas().get(j).getClone());

                    face.calcCentro();
                    polControl.calcVetorNormal(face);
                    ant.CalcPerspectiva(pPers, vrpPers, dpPers);
                    poli.addFace(face);
                    face = new Poligono();
                }

                ant = novo;
                rotaçao += angulo;
                novo = poli.rotacionarZ(pol, Math.toRadians(rotaçao));
            }

            if (graus != 360) {
                Collections.reverse(ant.getArestas());
                polControl.calcVetorNormal(ant);
                ant.CalcPerspectiva(pPers, vrpPers, dpPers);
                poli.addFace(ant);
            }

        }

        poli.calcCentro();
        poliedros.add(poli);

    }

    public void extrusao(Poligono pol, int nlados, String eixo, double npixel) {

        double dist = npixel / nlados;
        Poliedro poli = new Poliedro();
        int n = pol.getnLados();
        poli.setnParticoes(nlados);
        if ("x".equals(eixo.toLowerCase())) {
            Poligono novo, face, ant;
            ant = pol;//.getClone();
            face = new Poligono();

            novo = poli.somaPixelX(pol, dist).getClone();

            for (int i = 0; i < nlados; i++) {
                for (int j = 0; j < n; j++) {
                    if (j == (n - 1)) {
                        face.addPonto(novo.getArestas().get(j).getClone());
                        face.addPonto(novo.getArestas().get(0).getClone());

                        face.addPonto(ant.getArestas().get(0).getClone());
                        face.addPonto(ant.getArestas().get(j).getClone());
                        face.calcCentro();
                        poli.addFace(face);

                        face = new Poligono();
                        break;
                    }
                    face.addPonto(novo.getArestas().get(j).getClone());
                    face.addPonto(novo.getArestas().get(j + 1).getClone());

                    face.addPonto(ant.getArestas().get(j + 1).getClone());
                    face.addPonto(ant.getArestas().get(j).getClone());

                    face.calcCentro();
                    poli.addFace(face);
                    face = new Poligono();
                }

                ant = novo;
                novo = poli.somaPixelX(pol, dist).getClone();

            }

        }
        if ("y".equals(eixo.toLowerCase())) {
            Poligono novo, face, ant;
            ant = pol;//.getClone();
            face = new Poligono();

            novo = poli.somaPixelY(pol, dist);

            for (int i = 0; i < nlados + 2; i++) {
                for (int j = 0; j < n; j++) {
                    if (j == (n - 1)) {
                        face.addPonto(novo.getArestas().get(j).getClone());
                        face.addPonto(novo.getArestas().get(0).getClone());

                        face.addPonto(ant.getArestas().get(0).getClone());
                        face.addPonto(ant.getArestas().get(j).getClone());
                        face.calcCentro();
                        poli.addFace(face);

                        face = new Poligono();
                        break;
                    }
                    face.addPonto(novo.getArestas().get(j).getClone());
                    face.addPonto(novo.getArestas().get(j + 1).getClone());

                    face.addPonto(ant.getArestas().get(j + 1).getClone());
                    face.addPonto(ant.getArestas().get(j).getClone());

                    face.calcCentro();
                    poli.addFace(face);
                    face = new Poligono();
                }

                ant = novo;
                novo = poli.somaPixelY(pol, dist).getClone();
            }

        }
        if ("z".equals(eixo.toLowerCase())) {
            Poligono novo, face, ant;
            ant = pol;//.getClone();
            face = new Poligono();
            novo = poli.somaPixelZ(pol, dist);

            for (int i = 0; i < nlados + 2; i++) {
                for (int j = 0; j < n; j++) {
                    if (j == (n - 1)) {
                        face.addPonto(novo.getArestas().get(j).getClone());
                        face.addPonto(novo.getArestas().get(0).getClone());

                        face.addPonto(ant.getArestas().get(0).getClone());
                        face.addPonto(ant.getArestas().get(j).getClone());
                        face.calcCentro();
                        poli.addFace(face);

                        face = new Poligono();
                        break;
                    }
                    face.addPonto(novo.getArestas().get(j).getClone());
                    face.addPonto(novo.getArestas().get(j + 1).getClone());

                    face.addPonto(ant.getArestas().get(j + 1).getClone());
                    face.addPonto(ant.getArestas().get(j).getClone());

                    face.calcCentro();
                    poli.addFace(face);
                    face = new Poligono();
                }

                ant = novo;
                novo = poli.somaPixelZ(pol, dist).getClone();
            }

        }

        poli.calcCentro();
        poliedros.add(poli);
    }

    public Poliedro transladarXY(Poliedro poli, Ponto a, Ponto b) {
        Poliedro ret = null;

        PoligonoController polControl = new PoligonoController(canvas);
        polControl.setDpPers(dpPers);
        polControl.setVrpPers(vrpPers);
        polControl.setpPers(pPers);

        for (Poligono face : poli.getFaces()) {
            face = polControl.transladarXY(face, a, b);
        }

        ret = poli;

        ret.calcCentro();

        return ret;

    }

    public void testeDeVisibilidade(Poliedro pol) {

        PoligonoController polControl = new PoligonoController(canvas);
        polControl.setDpPers(dpPers);
        polControl.setVrpPers(vrpPers);
        polControl.setpPers(pPers);
        for (Poligono face : pol.getFaces()) {
            polControl.testeDeVisibilidade(face);
        }
    }

    public Poliedro transladarXZ(Poliedro poli, Ponto a, Ponto b) {
        Poliedro ret = null;

        PoligonoController polControl = new PoligonoController(canvas);
        polControl.setDpPers(dpPers);
        polControl.setVrpPers(vrpPers);
        polControl.setpPers(pPers);
        for (Poligono face : poli.getFaces()) {
            face = polControl.transladarXZ(face, a, b);
        }

        ret = poli;

        ret.calcCentro();

        return ret;

    }

    public Poliedro transladarYZ(Poliedro poli, Ponto a, Ponto b) {
        Poliedro ret = null;

        PoligonoController polControl = new PoligonoController(canvas);

        polControl.setDpPers(dpPers);
        polControl.setVrpPers(vrpPers);
        polControl.setpPers(pPers);
        for (Poligono face : poli.getFaces()) {
            face = polControl.transladarYZ(face, a, b);
        }

        ret = poli;

        ret.calcCentro();

        return ret;

    }

    public Poliedro selecaoPoliXY(Ponto p) {
        Poliedro ret = null;

        PoligonoController polControl = new PoligonoController(canvas);
        polControl.setDpPers(dpPers);
        polControl.setVrpPers(vrpPers);
        polControl.setpPers(pPers);
        for (Poliedro poliedro : poliedros) {
            polControl.setPoligonos(poliedro.getFaces());
            if (polControl.selecaoXY(p) != null) {
                ret = poliedro;
                return ret;
            }
        }

        return ret;
    }

    public Poliedro selecaoPoliXZ(Ponto p) {
        Poliedro ret = null;

        PoligonoController polControl = new PoligonoController(canvas);
        polControl.setDpPers(dpPers);
        polControl.setVrpPers(vrpPers);
        polControl.setpPers(pPers);
        for (Poliedro poliedro : poliedros) {
            polControl.setPoligonos(poliedro.getFaces());
            if (polControl.selecaoXZ(p) != null) {
                ret = poliedro;
                return ret;
            }
        }

        return ret;
    }

    public Poliedro selecaoPoliYZ(Ponto p) {
        Poliedro ret = null;

        PoligonoController polControl = new PoligonoController(canvas);
        polControl.setDpPers(dpPers);
        polControl.setVrpPers(vrpPers);
        polControl.setpPers(pPers);
        for (Poliedro poliedro : poliedros) {
            polControl.setPoligonos(poliedro.getFaces());
            if (polControl.selecaoYZ(p) != null) {
                ret = poliedro;
                return ret;
            }
        }

        return ret;
    }

    public void rotacionarXY(Poliedro poli, Ponto ant, Ponto novo) {

        double dx = novo.getX() - ant.getX();
        double dy = novo.getY() - ant.getY();

        poli.transOrigem();

        poli.rotacionarX2(Math.toDegrees(dy * 0.0001));
        poli.rotacionarY2(Math.toDegrees(dx * 0.0001));

        poli.transOriginal();
        for (Poligono face : poli.getFaces()) {
            face.CalcPerspectiva(pPers, vrpPers, dpPers);
        }

    }

    public void rotacionarXZ(Poliedro poli, Ponto ant, Ponto novo) {
        double dx = novo.getX() - ant.getX();
        double dz = novo.getZ() - ant.getZ();

        poli.transOrigem();

        poli.rotacionarX2(Math.toDegrees(dz * 0.0001));
        poli.rotacionarZ2(Math.toDegrees(dx * 0.0001));

        poli.transOriginal();
        
        for (Poligono face : poli.getFaces()) {
            face.CalcPerspectiva(pPers, vrpPers, dpPers);
        }

    }

    public void rotacionarYZ(Poliedro poli, Ponto ant, Ponto novo) {
        double dy = novo.getY() - ant.getY();
        double dz = novo.getZ() - ant.getZ();

        poli.transOrigem();

        poli.rotacionarZ2(Math.toDegrees(dz * 0.0001));
        poli.rotacionarY2(Math.toDegrees(dy * 0.0001));

        poli.transOriginal();
        
        for (Poligono face : poli.getFaces()) {
            face.CalcPerspectiva(pPers, vrpPers, dpPers);
        }
        

    }

    public double angulo(Ponto a, Ponto b) {
        double ret = 0;
        ret = (a.getX() * b.getX() + a.getY() * b.getY() + a.getZ() * b.getZ()) / (Math.sqrt(a.X2() + a.Y2() + a.Z2()) * Math.sqrt(b.X2() + b.Y2() + b.Z2()));

        //ret = Math.toRadians(Math.acos(ret));
        ret = Math.acos(ret);
        return ret;
    }

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

    public void escalaXY(Poliedro original, Poliedro select, Ponto inicio, Ponto fim) {
        double prop;

        prop = distanciaRetaXY(select.getCentro(), fim) / distanciaRetaXY(select.getCentro(), inicio);

        System.out.println(prop);
        
        original.transOrigem();
        select.transOrigem();

        int i = 0, j = 0;

        for (Poligono face : select.getFaces()) {
            for (Ponto aresta : face.getArestas()) {
                aresta.setXYZ(original.getFaces().get(i).getArestas().get(j).getX() * prop,
                        original.getFaces().get(i).getArestas().get(j).getY() * prop,
                        original.getFaces().get(i).getArestas().get(j).getZ());
                j++;
            }
            i++;
            j = 0;
        }

        original.transOriginal();
        select.transOriginal();

        select.calcCentro();
    }

    public void escalaXZ(Poliedro original, Poliedro select, Ponto inicio, Ponto fim) {
        double prop;

        prop = distanciaRetaXZ(select.getCentro(), fim) / distanciaRetaXY(select.getCentro(), inicio);

        original.transOrigem();
        select.transOrigem();

        int i = 0, j = 0;

        for (Poligono face : select.getFaces()) {
            for (Ponto aresta : face.getArestas()) {
                aresta.setXYZ(original.getFaces().get(i).getArestas().get(j).getX() * prop,
                        original.getFaces().get(i).getArestas().get(j).getY(),
                        original.getFaces().get(i).getArestas().get(j).getZ() * prop);
                j++;
            }
            i++;
            j = 0;
        }

        original.transOriginal();
        select.transOriginal();

        select.calcCentro();
    }

    public void escalaYZ(Poliedro original, Poliedro select, Ponto inicio, Ponto fim) {
        double prop;

        prop = distanciaRetaYZ(select.getCentro(), fim) / distanciaRetaXY(select.getCentro(), inicio);

        original.transOrigem();
        select.transOrigem();

        int i = 0, j = 0;

        for (Poligono face : select.getFaces()) {
            for (Ponto aresta : face.getArestas()) {
                aresta.setXYZ(original.getFaces().get(i).getArestas().get(j).getX(),
                        original.getFaces().get(i).getArestas().get(j).getY() * prop,
                        original.getFaces().get(i).getArestas().get(j).getZ() * prop);
                j++;
            }
            i++;
            j = 0;
        }

        original.transOriginal();
        select.transOriginal();

        select.calcCentro();
    }

    public void cisalharXY(Poliedro original, Poliedro select, Ponto anterior, Ponto novo) {
        double x, y;
        x = (anterior.getX() - novo.getX()) * 0.005;
        y = (anterior.getY() - novo.getY()) * 0.005;

        original.transOrigem();
        select.transOrigem();

        int i = 0, j = 0;

        for (Poligono face : select.getFaces()) {
            for (Ponto aresta : face.getArestas()) {
                aresta.setXYZ(original.getFaces().get(i).getArestas().get(j).getX() + original.getFaces().get(i).getArestas().get(j).getY() * x,
                        original.getFaces().get(i).getArestas().get(j).getY() + original.getFaces().get(i).getArestas().get(j).getX() * y,
                        original.getFaces().get(i).getArestas().get(j).getZ());
                j++;
            }
            i++;
            j = 0;
        }
        original.transOriginal();
        select.transOriginal();
        select.calcCentro();
    }

    public void cisalharXZ(Poliedro original, Poliedro select, Ponto anterior, Ponto novo) {
        double x, z;
        x = (anterior.getX() - novo.getX()) * 0.005;
        z = (anterior.getZ() - novo.getZ()) * 0.005;

        original.transOrigem();
        select.transOrigem();

        int i = 0, j = 0;

        for (Poligono face : select.getFaces()) {
            for (Ponto aresta : face.getArestas()) {
                aresta.setXYZ(original.getFaces().get(i).getArestas().get(j).getX() + original.getFaces().get(i).getArestas().get(j).getZ() * x,
                        original.getFaces().get(i).getArestas().get(j).getY(),
                        original.getFaces().get(i).getArestas().get(j).getZ() + original.getFaces().get(i).getArestas().get(j).getX() * z);
                j++;
            }
            i++;
            j = 0;
        }

        original.transOriginal();
        select.transOriginal();

        select.calcCentro();

    }

    public void cisalharYZ(Poliedro original, Poliedro select, Ponto anterior, Ponto novo) {
        double y, z;
        z = (anterior.getZ() - novo.getZ()) * 0.005;
        y = (anterior.getY() - novo.getY()) * 0.005;

        original.transOrigem();
        select.transOrigem();

        int i = 0, j = 0;

        for (Poligono face : select.getFaces()) {
            for (Ponto aresta : face.getArestas()) {
                aresta.setXYZ(original.getFaces().get(i).getArestas().get(j).getX(),
                        original.getFaces().get(i).getArestas().get(j).getY() + original.getFaces().get(i).getArestas().get(j).getZ() * y,
                        original.getFaces().get(i).getArestas().get(j).getZ() + original.getFaces().get(i).getArestas().get(j).getY() * z);
                j++;
            }
            i++;
            j = 0;
        }

        original.transOriginal();
        select.transOriginal();

        select.calcCentro();

    }
    
    

    public double anguloVetoresXY(Ponto x, Ponto y, Ponto z) {
        double ret;
        Ponto a = new Ponto();
        Ponto b = new Ponto();

        a.setXY((y.getX() - x.getX()), (y.getY() - x.getY()));
        b.setXY((z.getX() - x.getX()), (z.getY() - x.getY()));

        ret = ((a.getX() * b.getX()) + (b.getY() * a.getY())) / (Math.sqrt(a.X2() + a.Y2()) * Math.sqrt(b.X2() + b.Y2()));

        if (calcdireçao(a, b)) {
            return Math.acos(ret);
        } else {
            return Math.acos(ret);
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

    public void sombreamentoConstanteXY(GraphicsContext gc, RGB Ila, RGB Il, Ponto luzIncidente, Poliedro select) {
        select.sombreamentoConstanteXY(gc, Ila, Il,luzIncidente);
    }

    public void sombreamentoConstanteXZ(GraphicsContext gc, RGB Ila, RGB Il, Ponto luzIncidente, Poliedro select) {
        select.sombreamentoConstanteXZ(gc, Ila, Il,luzIncidente);
    }

    public void sombreamentoConstanteYZ(GraphicsContext gc, RGB Ila, RGB Il, Ponto luzIncidente, Poliedro select) {
        select.sombreamentoConstanteYZ(gc, Ila, Il,luzIncidente);
    }

    public void sombreamentoConstantePerspec(GraphicsContext gc, RGB Ila, RGB Il, Ponto luzIncidente, Poliedro select) {
        select.sombreamentoConstantePerspec(gc, Ila, Il,luzIncidente);
    }

    public void sombreamentoConstanteSelectXY(GraphicsContext gc, RGB Ila, RGB Il, Ponto luzIncidente, Poliedro select) {
        select.sombreamentoConstanteSelectXY(gc, Ila, Il,luzIncidente);
    }

    public void sombreamentoConstanteSelectXZ(GraphicsContext gc, RGB Ila, RGB Il, Ponto luzIncidente, Poliedro select) {
        select.sombreamentoConstanteSelectXZ(gc, Ila, Il,luzIncidente);
    }

    public void sombreamentoConstanteSelectYZ(GraphicsContext gc, RGB Ila, RGB Il, Ponto luzIncidente, Poliedro select) {
        select.sombreamentoConstanteSelectYZ(gc, Ila, Il,luzIncidente);
    }

    public void sombreamentoConstanteSelectPerspec(GraphicsContext gc, RGB Ila, RGB Il, Ponto luzIncidente, Poliedro select) {
        select.sombreamentoConstanteSelectPerspec(gc, Ila, Il,luzIncidente);
    }

}
