/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cg_trab1.controller;

import cg_trab1.model.Poligono;
import cg_trab1.model.Ponto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javax.swing.JFileChooser;

/**
 *
 * @author Patrick
 */
public class ArquivoController {

    public ArquivoController() {

    }

    public void salvar(LinkedList<Poligono> polis) throws IOException {

        File salvar = null;
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (file.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

            salvar = new File(file.getSelectedFile().getAbsolutePath() + ".txt");

            //salvar = file.getSelectedFile(); //new File(file.getSelectedFile().getAbsolutePath())
            // if (salvar.isFile()) {
            FileWriter f1 = new FileWriter(salvar);

            //f1.write("teste");
            //f1.flush();
            //f1.close();
            int i = 1;
            String ponto;
            String pol;
            String cores;
            for (Poligono poli : polis) {
                pol = "P" + String.valueOf(i) + "\r\n";

                f1.write(pol);
                cores = "CB:" + poli.getCorBorda().toString() + " \r\n";
                f1.write(cores);

                if (poli.getCorFundo() == null) {
                    cores = "CF:null \r\n";
                } else {
                    cores = "CF:" + poli.getCorFundo().toString() + " \r\n";
                }
                f1.write(cores);
                i++;
                for (Ponto arestas : poli.getLados()) {
                    ponto = "x:" + arestas.getX() + " " + "y:" + arestas.getY() + " \r\n";
                    f1.write(ponto);
                }
            }

            f1.flush();
            f1.close();
            // }
        }
    }

    public void save(LinkedList<Poligono> polis) {
        File salvar = null;
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (file.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

            salvar = new File(file.getSelectedFile().getAbsolutePath() + ".txt");
            FileOutputStream out;
            try {
                out = new FileOutputStream(file.getSelectedFile().getAbsolutePath() + ".gigio");
                ObjectOutputStream oos = new ObjectOutputStream(out);
                oos.writeObject(polis);
            } catch (IOException e) {
            }
        }
    }

    public void loade(PoligonoController polCtrl) {
        String ret = null;
        JFileChooser fc = new JFileChooser();

        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                FileInputStream in = new FileInputStream(fc.getSelectedFile().getAbsolutePath());
                ObjectInputStream ois = new ObjectInputStream(in);
                polCtrl.setPoligonos((LinkedList<Poligono>) (ois.readObject()));
            } catch (Exception e) {
                System.out.println("Problem serializing: " + e);
            }
        }
    }

    public void carregar(PoligonoController polCtrl) throws FileNotFoundException, IOException {

        String ret = null;
        JFileChooser fc = new JFileChooser();

        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            Scanner elem = new Scanner(fc.getSelectedFile());

            ret = Files.readAllLines(fc.getSelectedFile().toPath()).stream().collect(Collectors.joining("\n"));

            int i = 0;
            boolean add = false;
            while (i < ret.length()) {

                if (ret.charAt(i) == 'P') {
                    i++;
                    Poligono pol = new Poligono();
                    pol.setFechado(true);
                    String x = null;
                    String y = null;
                    String cf = null;
                    String cb = null;
                    while (ret.charAt(i) != 'P') {

                        if (ret.charAt(i) == 'C') {
                            i++;

                            if (ret.charAt(i) == 'B') {
                                cb = "";
                                i++;
                                i++;

                                while (ret.charAt(i) != ' ') {
                                    cb = cb + ret.charAt(i);
                                    i++;

                                }
                                pol.setCorBorda(Color.web(cb));
                            }

                            while (ret.charAt(i) != 'F') {
                                i++;
                            }

                            if (ret.charAt(i) == 'F') {
                                cf = "";
                                i++;
                                i++;

                                while (ret.charAt(i) != ' ') {
                                    cf = cf + ret.charAt(i);
                                    i++;
                                }

                            }

                            if (cf.charAt(0) == 'n') {
                            } else {
                                pol.setCorFundo(Color.web(cf));

                            }
                        }

                        if (ret.charAt(i) == 'x') {
                            x = "";
                            i++;
                            i++;

                            while (ret.charAt(i) != ' ') {

                                x = x + ret.charAt(i);
                                i++;

                            }
                        }

                        if (i == ret.length()) {
                            polCtrl.getPoligonos().add(pol);
                            return;
                        }

                        if (ret.charAt(i) == 'y') {

                            y = "";

                            i++;

                            while (ret.charAt(i) != ' ') {
                                i++;
                                y = y + ret.charAt(i);
                                add = true;
                            }

                        }

                        if (x != null && y != null && add) {
                            pol.addPonto(new Ponto(Double.parseDouble(x), Double.parseDouble(y)));
                            add = false;
                        }

                    }
                    polCtrl.getPoligonos().add(pol);

                }

            }
        }

    }
}
