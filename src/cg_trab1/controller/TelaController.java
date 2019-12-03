/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cg_trab1.controller;

import cg_trab1.Cg_trab1;
import cg_trab1.model.Poliedro;
import cg_trab1.model.Poligono;
import cg_trab1.model.Ponto;
import cg_trab1.model.RGB;
import java.io.IOException;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.AnchorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @authors Patrick, Rafael
 * @version 1.0
 * @since 2017
 */
public class TelaController implements Initializable {

    private PoligonoController polControl;
    private PoliedroController poliControl;
    private ArquivoController arquiControl;
    GraphicsContext gcXY;
    GraphicsContext gcXZ;
    GraphicsContext gcYZ;
    GraphicsContext gcPer;
    @FXML
    private Canvas canvasXY;
    @FXML
    private Canvas canvasYZ;
    @FXML
    private Canvas canvasXZ;
    @FXML
    private Canvas canvasPers;
    @FXML
    private TextField raioTxt;
    @FXML
    private CheckBox poligonoCheck;
    @FXML
    private ComboBox selecioneLados;
    @FXML
    private TextArea mousePosition;
    @FXML
    private ColorPicker ColorPikerPoligono;
    @FXML
    private ColorPicker ColorPikerPoliedro;
    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private ToggleButton cisalhar;
    @FXML
    private ToggleButton transladar;
    @FXML
    private ToggleButton rotacionar;
    @FXML
    private ToggleButton escala;
    @FXML
    private ToggleButton triangulo;
    @FXML
    private ToggleButton quadrado;
    @FXML
    private ToggleButton pentagono;
    @FXML
    private ToggleButton hexagono;
    @FXML
    private ToggleButton selecionar;
    @FXML
    private ToggleButton desenharIrregular;
    @FXML
    private AnchorPane ancorCanvas;

    private RGB Ila;
    private RGB Il;

    //public static final Antialiasing DISABLED;
    private Poligono selectPol;
    private Poliedro selectPoli;
    private Ponto anterior;
    private Ponto luzIncidente;
    private int opcao;
    private int indexPol;
    private int indexPoli;
    double desX, desY;
    private Ponto pPers;
    private Ponto vrpPers;
    private double dpPers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        System.out.println("ehnoix");
        this.polControl = new PoligonoController(this.canvasXY);
        this.poliControl = new PoliedroController(this.canvasXY);
        this.arquiControl = new ArquivoController();

        desX = (canvasXY.getWidth() / 2);
        desY = (canvasXY.getHeight() / 2);
        gcXY = this.canvasXY.getGraphicsContext2D();
        gcXZ = this.canvasXZ.getGraphicsContext2D();
        gcYZ = this.canvasYZ.getGraphicsContext2D();
        gcPer = this.canvasPers.getGraphicsContext2D();
        gcXY.translate(desX, desY);
        gcXZ.translate(desX, desY);
        gcYZ.translate(desX, desY);
        gcPer.translate(desX, desY);

        gcXY.setStroke(Color.BLACK);
        gcXZ.setStroke(Color.BLACK);
        gcYZ.setStroke(Color.BLACK);
        gcPer.setStroke(Color.BLACK);
        IniciarCombo();
        ColorPikerPoligono.setValue(Color.BLACK);

        pPers = new Ponto(0, 0, 0);
        vrpPers = new Ponto(0, 0, 500);
        dpPers = 350;
        luzIncidente = new Ponto(70, 20, 35);
        poliControl.setDpPers(dpPers);
        poliControl.setVrpPers(vrpPers);
        poliControl.setpPers(pPers);

        polControl.setDpPers(dpPers);
        polControl.setVrpPers(vrpPers);
        polControl.setpPers(pPers);

        Ila = new RGB();
        Il = new RGB();
        toggleGroup = new ToggleGroup();

        cisalhar.setToggleGroup(toggleGroup);
        transladar.setToggleGroup(toggleGroup);
        rotacionar.setToggleGroup(toggleGroup);
        escala.setToggleGroup(toggleGroup);
        triangulo.setToggleGroup(toggleGroup);
        quadrado.setToggleGroup(toggleGroup);
        pentagono.setToggleGroup(toggleGroup);
        hexagono.setToggleGroup(toggleGroup);
        selecionar.setToggleGroup(toggleGroup);
        desenharIrregular.setToggleGroup(toggleGroup);
        //selecionarPoliedro.setToggleGroup(toggleGroup);
        desenharMarcas();
    }

    @FXML
    public void btnAbrirClick(ActionEvent e) {

    }

    @FXML
    public void btnSalvarClick(ActionEvent e) {

    }

    @FXML
    public void btnOpenNewWindow(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Ajuda.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
        }
    }

    public void setColor() {
        gcXY.setStroke(ColorPikerPoligono.getValue());
    }

    @FXML
    public void handleButtonAction(ActionEvent e) {
        System.exit(0);
    }

    public Color getColor() {
        return ColorPikerPoligono.getValue();
    }

    @FXML
    public void polCenter(MouseEvent e) {
        this.polControl.criarRegularXY(new Ponto((e.getX() - desX), (e.getY() - desY)), 3, 20, getColor());
        this.canvasXY.setOnMouseClicked(null);

    }

    @FXML
    public void novoPontoPoligono(MouseEvent e) {
        if (opcao == 7) {

            if (e.getButton() == MouseButton.PRIMARY) {
                this.polControl.addPonto(new Ponto((e.getX() - desX), (e.getY() - desY)));

            } else if (e.getButton() == MouseButton.SECONDARY) {
                this.polControl.finishIrregular();
                this.polControl.createIrregular(ColorPikerPoligono.getValue());
            }
        }
    }

    @FXML
    public void desenhaTriangulo(ActionEvent e) {

        this.canvasXY.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getSource() == canvasXY) {
                desenhaPoligonoXY(new Ponto((mouseEvent.getX() - desX), (mouseEvent.getY() - desY), 0), 3);
            }
        });
        this.canvasXZ.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getSource() == canvasXZ) {
                desenhaPoligonoXZ(new Ponto((mouseEvent.getX() - desX), 0, (mouseEvent.getY() - desY)), 3);
            }
        });
        this.canvasYZ.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getSource() == canvasYZ) {
                desenhaPoligonoYZ(new Ponto(0, (mouseEvent.getY() - desY), (mouseEvent.getX() - desX)), 3);
            }
        });

    }

    @FXML
    public void desenhaQuadrado(ActionEvent e) {
        this.canvasXY.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getSource() == canvasXY) {
                desenhaPoligonoXY(new Ponto((mouseEvent.getX() - desX), (mouseEvent.getY() - desY), 0), 4);
            }
        });
        this.canvasXZ.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getSource() == canvasXZ) {
                desenhaPoligonoXZ(new Ponto((mouseEvent.getX() - desX), 0, (mouseEvent.getY() - desY)), 4);
            }
        });
        this.canvasYZ.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getSource() == canvasYZ) {
                desenhaPoligonoYZ(new Ponto(0, (mouseEvent.getY() - desY), (mouseEvent.getX() - desX)), 4);
            }
        });
    }

    @FXML
    public void desenhaPentagono(ActionEvent e) {
        this.canvasXY.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getSource() == canvasXY) {
                desenhaPoligonoXY(new Ponto((mouseEvent.getX() - desX), (mouseEvent.getY() - desY), 0), 5);
            }
        });
        this.canvasXZ.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getSource() == canvasXZ) {
                desenhaPoligonoXZ(new Ponto((mouseEvent.getX() - desX), 0, (mouseEvent.getY() - desY)), 5);
            }
        });
        this.canvasYZ.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getSource() == canvasYZ) {
                desenhaPoligonoYZ(new Ponto(0, (mouseEvent.getY() - desY), (mouseEvent.getX() - desX)), 5);
            }
        });
    }

    @FXML
    public void desenhaHexagono(ActionEvent e) {
        this.canvasXY.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getSource() == canvasXY) {
                desenhaPoligonoXY(new Ponto((mouseEvent.getX() - desX), (mouseEvent.getY() - desY), 0), 6);
            }
        });
        this.canvasXZ.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getSource() == canvasXZ) {
                desenhaPoligonoXZ(new Ponto((mouseEvent.getX() - desX), 0, (mouseEvent.getY() - desY)), 6);
            }
        });
        this.canvasYZ.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getSource() == canvasYZ) {
                desenhaPoligonoYZ(new Ponto(0, (mouseEvent.getY() - desY), (mouseEvent.getX() - desX)), 6);
            }
        });
    }

    @FXML
    public void salvarTela() throws IOException {
        this.arquiControl.save(polControl.getPoligonos());
    }

    @FXML
    public void carregarTela() throws IOException {
        clearAll();
        this.arquiControl.loade(polControl);
        desenharTela();

    }

    public void desenhaPoligonoXY(Ponto centro, int lados) {
        setColor();
        this.polControl.criarRegularXY(centro, lados, getRaio(), getColor());
        zerarTela();
        desenharTela();
    }

    public void desenhaPoligonoXZ(Ponto centro, int lados) {
        setColor();
        this.polControl.criarRegularXZ(centro, lados, getRaio(), getColor());
        zerarTela();
        desenharTela();
    }

    public void desenhaPoligonoYZ(Ponto centro, int lados) {
        setColor();
        this.polControl.criarRegularYZ(centro, lados, getRaio(), getColor());
        zerarTela();
        desenharTela();
    }

    public double getRaio() {
        if (raioTxt.getText() != null && !raioTxt.getText().equals("")) {
            return Double.parseDouble(raioTxt.getText());
        }

        return 50;
    }

    @FXML
    public void desenharIrregular(ActionEvent e) {
        selopcao(7);
        this.polControl.createIrregular(ColorPikerPoligono.getValue());
        this.canvasXY.setOnMouseClicked(this::novoPontoPoligono);
    }

    public void IniciarCombo() {
        for (int i = 7; i < 21; i++) {
            selecioneLados.getItems().add(i);
        }
        selecioneLados.getItems().add(100);
    }

    public void desenharMarcas() {

        gcXY.setLineWidth(2.0);
        gcXY.setLineDashOffset(5.0);
        gcXY.setLineDashes(10);
        gcXY.setStroke(Color.RED);

        gcXZ.setLineWidth(2.0);
        gcXZ.setLineDashOffset(5.0);
        gcXZ.setLineDashes(10);
        gcXZ.setStroke(Color.RED);

        gcYZ.setLineWidth(2.0);
        gcYZ.setLineDashOffset(5.0);
        gcYZ.setLineDashes(10);
        gcYZ.setStroke(Color.RED);

        gcXY.setStroke(Color.GREY);
        gcXZ.setStroke(Color.GREY);
        gcYZ.setStroke(Color.GREY);
        gcXY.strokeLine(0, 1000, 0, -1000);
        gcXZ.strokeLine(0, 1000, 0, -1000);
        gcYZ.strokeLine(0, 1000, 0, -1000);
        gcXY.strokeLine(1000, 0, -1000, 0);
        gcXZ.strokeLine(1000, 0, -1000, 0);
        gcYZ.strokeLine(1000, 0, -1000, 0);
        gcXY.setStroke(Color.BLACK);
        gcXZ.setStroke(Color.BLACK);
        gcYZ.setStroke(Color.BLACK);

        gcXY.setLineWidth(0);
        gcXY.setLineDashOffset(0);
        gcXY.setLineDashes(0);
        gcXY.setStroke(Color.RED);

        gcXZ.setLineWidth(0);
        gcXZ.setLineDashOffset(0);
        gcXZ.setLineDashes(0);
        gcXZ.setStroke(Color.RED);

        gcYZ.setLineWidth(0);
        gcYZ.setLineDashOffset(0);
        gcYZ.setLineDashes(0);
        gcYZ.setStroke(Color.RED);
    }

    public void desenharTela() {
        desenharMarcas();
        if (selectPol == null) {
            for (Poligono poligono : polControl.getPoligonos()) {
                poligono.drawXY(gcXY);
                poligono.drawYZ(gcYZ);
                poligono.drawXZ(gcXZ);
                poligono.drawPerspectiva(gcPer);

            }
        } else {
            for (int i = 0; i < polControl.getPoligonos().size(); i++) {
                if (i != indexPol) {
                    polControl.getPoligonos().get(i).drawXY(gcXY);
                    polControl.getPoligonos().get(i).drawYZ(gcYZ);
                    polControl.getPoligonos().get(i).drawXZ(gcXZ);
                    polControl.getPoligonos().get(i).drawPerspectiva(gcPer);
                } else {
                    selectPol.drawSelectXY(gcXY);
                    selectPol.drawSelectXZ(gcXZ);
                    selectPol.drawSelectYZ(gcYZ);
                    selectPol.drawSelectPerspectiva(gcPer);
                }
            }
        }

        if (selectPoli == null) {

            for (Poliedro poliedro : poliControl.getPoliedros()) {
                poliedro.drawXY(gcXY);
                poliedro.drawXZ(gcXZ);
                poliedro.drawYZ(gcYZ);
                poliedro.drawPespec(gcPer);
                if (poliedro.isSobrear()) {
                    this.poliControl.sombreamentoConstanteXY(gcXY, Ila, Il, luzIncidente, poliedro);
                    this.poliControl.sombreamentoConstanteXZ(gcXZ, Ila, Il, luzIncidente, poliedro);
                    this.poliControl.sombreamentoConstanteYZ(gcYZ, Ila, Il, luzIncidente, poliedro);
                    this.poliControl.sombreamentoConstantePerspec(gcPer, Ila, Il, luzIncidente, poliedro);
                }
            }
        } else {
            for (int i = 0; i < poliControl.getPoliedros().size(); i++) {
                if (i != indexPoli) {
                    poliControl.getPoliedros().get(i).drawXY(gcXY);
                    poliControl.getPoliedros().get(i).drawYZ(gcYZ);
                    poliControl.getPoliedros().get(i).drawXZ(gcXZ);
                    poliControl.getPoliedros().get(i).drawPespec(gcPer);
                    if (poliControl.getPoliedros().get(i).isSobrear()) {
                        this.poliControl.sombreamentoConstanteXY(gcXY, Ila, Il, luzIncidente, poliControl.getPoliedros().get(i));
                        this.poliControl.sombreamentoConstanteXZ(gcXZ, Ila, Il, luzIncidente, poliControl.getPoliedros().get(i));
                        this.poliControl.sombreamentoConstanteYZ(gcYZ, Ila, Il, luzIncidente, poliControl.getPoliedros().get(i));
                        this.poliControl.sombreamentoConstantePerspec(gcPer, Ila, Il, luzIncidente, poliControl.getPoliedros().get(i));
                    }
                } else {
                    selectPoli.drawSelectXY(gcXY);
                    selectPoli.drawSelectYZ(gcYZ);
                    selectPoli.drawSelectXZ(gcXZ);
                    selectPoli.drawSelectPerspectiva(gcPer);
                    if (poliControl.getPoliedros().get(i).isSobrear()) {
                        this.poliControl.sombreamentoConstanteSelectXY(gcXY, Ila, Il, luzIncidente, poliControl.getPoliedros().get(i));
                        this.poliControl.sombreamentoConstanteSelectXZ(gcXZ, Ila, Il, luzIncidente, poliControl.getPoliedros().get(i));
                        this.poliControl.sombreamentoConstanteSelectYZ(gcYZ, Ila, Il, luzIncidente, poliControl.getPoliedros().get(i));
                        this.poliControl.sombreamentoConstanteSelectPerspec(gcPer, Ila, Il, luzIncidente, poliControl.getPoliedros().get(i));
                    }
                }
            }
        }
    }

    @FXML
    public void limparTela() {
        clearAll();
    }

    public void clearAll() {

        zerarTela();
        this.polControl.setPol(new Poligono());
        this.poliControl.setPole(new Poliedro());
        this.poliControl.setPol(new Poligono());
        this.polControl.getPoligonos().clear();
        this.poliControl.getPoliedros().clear();
    }

    @FXML
    public void getMousePosition(ActionEvent e) {
        this.canvasXY.setOnMouseMoved(this::printMousePosition);
    }

    public void printMousePosition(MouseEvent e) {
        this.mousePosition.setText(("x: " + String.valueOf((e.getX() - desX) + " " + "y: " + String.valueOf((e.getY() - desY)))));
    }

    @FXML
    public void desenharNlados(ActionEvent e) {
        this.canvasXY.setOnMouseClicked(mouseEvent -> {
            desenhaPoligonoXY(new Ponto((mouseEvent.getX() - desX), (mouseEvent.getY() - desY)), Integer.parseInt(selecioneLados.getValue().toString()));
        });
    }

    public void selectPolXY(MouseEvent e) {
        if (this.opcao == 1) {

            if (e.getSource() == canvasXY) {

                //alterarKs();

                this.selectPol = new Poligono();
                this.selectPol = this.polControl.selecaoXY(new Ponto((e.getX() - desX), (e.getY() - desY), 0));
                indexPol = polControl.getPoligonos().indexOf(selectPol);
                zerarTela();
                gcXY.setStroke(ColorPikerPoligono.getValue());
                selectPoli = null;
                desenharTela();
            }
        }
    }

    public void selectPolXZ(MouseEvent e) {
        if (this.opcao == 1) {

            if (e.getSource() == canvasXZ) {
                this.selectPol = new Poligono();
                this.selectPol = this.polControl.selecaoXZ(new Ponto((e.getX() - desX), 0, (e.getY() - desY)));
                indexPol = polControl.getPoligonos().indexOf(selectPol);
                zerarTela();
                gcXZ.setStroke(ColorPikerPoligono.getValue());
                selectPoli = null;
                desenharTela();
            }

        }
    }

    public void selectPolYZ(MouseEvent e) {
        if (this.opcao == 1) {

            if (e.getSource() == canvasYZ) {
                this.selectPol = new Poligono();
                gcYZ.setStroke(Color.GREEN);
                this.selectPol = this.polControl.selecaoYZ(new Ponto(0, (e.getY() - desY), (e.getX() - desX)));
                indexPol = polControl.getPoligonos().indexOf(selectPol);
                zerarTela();
                gcYZ.setStroke(ColorPikerPoligono.getValue());
                selectPoli = null;
                desenharTela();
            }
        }
    }

    public void selectPoliXY(MouseEvent e) {
        if (this.opcao == 10) {
            if (e.getSource() == canvasXY) {
                this.selectPoli = new Poliedro();
                this.selectPoli = this.poliControl.selecaoPoliXY(new Ponto((e.getX() - desX), (e.getY() - desY), 0));
                //this.poliControl.testeDeVisibilidade(selectPoli);
                indexPoli = poliControl.getPoliedros().indexOf(selectPoli);
                zerarTela();
                gcXY.setStroke(ColorPikerPoligono.getValue());
                this.selectPol = null;
                selectPol = null;
                desenharTela();
            }
        }
    }

    public void selectPoliXZ(MouseEvent e) {
        if (this.opcao == 10) {
            if (e.getSource() == canvasXZ) {
                this.selectPoli = new Poliedro();
                this.selectPoli = this.poliControl.selecaoPoliXZ(new Ponto((e.getX() - desX), 0, (e.getY() - desY)));
                indexPoli = poliControl.getPoliedros().indexOf(selectPoli);
                zerarTela();
                gcXZ.setStroke(ColorPikerPoligono.getValue());
                this.selectPol = null;
                selectPol = null;
                desenharTela();
            }
        }
    }

    public void selectPoliYZ(MouseEvent e) {
        if (this.opcao == 10) {
            if (e.getSource() == canvasYZ) {
                this.selectPoli = new Poliedro();
                this.selectPoli = this.poliControl.selecaoPoliYZ(new Ponto(0, (e.getY() - desY), (e.getX() - desX)));
                indexPoli = poliControl.getPoliedros().indexOf(selectPoli);
                zerarTela();
                gcYZ.setStroke(ColorPikerPoligono.getValue());
                this.selectPol = null;
                selectPol = null;
                desenharTela();
            }
        }
    }

    @FXML
    public void excluirPoligono() {
        zerarTela();
        this.polControl.getPoligonos().remove(indexPol);
        indexPol = -1;
        this.selectPol = null;
        zerarTela();
        desenharTela();
        selopcao(1);
    }

    @FXML
    public void excluirPoliedro() {
        zerarTela();
        this.poliControl.getPoliedros().remove(indexPoli);
        indexPol = -1;
        this.selectPoli = null;
        zerarTela();
        desenharTela();
        selopcao(1);
    }

    @FXML
    public void selecionarPoligono(ActionEvent e) {
        selopcao(1);
        this.canvasXY.setOnMouseClicked(this::selectPolXY);
        this.canvasXZ.setOnMouseClicked(this::selectPolXZ);
        this.canvasYZ.setOnMouseClicked(this::selectPolYZ);
    }

    @FXML
    public void selecionarPoliedro(ActionEvent e) {
        selopcao(10);
        this.canvasXY.setOnMouseClicked(this::selectPoliXY);
        this.canvasXZ.setOnMouseClicked(this::selectPoliXZ);
        this.canvasYZ.setOnMouseClicked(this::selectPoliYZ);
    }

    @FXML
    public void transladarPoligono(ActionEvent e) {
        selopcao(2);
        this.canvasXY.setOnMouseDragged(this::transladarPolXY);
        this.canvasXZ.setOnMouseDragged(this::transladarPolXZ);
        this.canvasYZ.setOnMouseDragged(this::transladarPolYZ);

        this.canvasXY.setOnMouseClicked(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                popSelectList();
            }
        });
        this.canvasXZ.setOnMouseClicked(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                popSelectList();
            }
        });
        this.canvasYZ.setOnMouseClicked(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                popSelectList();
            }
        });

    }

    @FXML
    public void transladarPoliedro(ActionEvent e) {
        selopcao(20);
        this.canvasXY.setOnMouseDragged(this::transladarPoliXY);
        this.canvasXZ.setOnMouseDragged(this::transladarPoliXZ);
        this.canvasYZ.setOnMouseDragged(this::transladarPoliYZ);
        this.canvasXY.setOnMouseClicked(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                popSelectList();
            }
        });
        this.canvasXZ.setOnMouseClicked(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                popSelectList();
            }
        });
        this.canvasYZ.setOnMouseClicked(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                popSelectList();
            }
        });
    }

    public void transladarPoliXY(MouseEvent e) {
        if (this.opcao == 20) {
            if (anterior == null) {

                popSelectList();
                anterior = new Ponto((e.getX() - desX), (e.getY() - desY));
                indexPoli = poliControl.getPoliedros().indexOf(selectPoli);
            } else {
                this.poliControl.transladarXY(selectPoli, anterior, new Ponto((e.getX() - desX), (e.getY() - desY)));
                anterior = new Ponto((e.getX() - desX), (e.getY() - desY));
                zerarTela();
                desenharTela();
                gcXY.setStroke(Color.RED);
                this.selectPoli.drawSelectXY(gcXY);
                gcXY.setStroke(Color.BLACK);
            }
        }

    }

    public void transladarPoliXZ(MouseEvent e) {
        if (this.opcao == 20) {
            if (e.getSource() == canvasXZ) {
                if (anterior == null) {

                    popSelectList();
                    anterior = new Ponto((e.getX() - desX), 0, (e.getY() - desY));
                    indexPoli = poliControl.getPoliedros().indexOf(selectPoli);
                } else {
                    this.poliControl.transladarXZ(selectPoli, anterior, new Ponto((e.getX() - desX), 0, (e.getY() - desY)));
                    anterior = new Ponto((e.getX() - desX), 0, (e.getY() - desY));
                    zerarTela();
                    desenharTela();
                    gcXZ.setStroke(Color.RED);
                    this.selectPoli.drawSelectXZ(gcXZ);
                    gcXZ.setStroke(Color.BLACK);
                }
            }
        }

    }

    public void transladarPoliYZ(MouseEvent e) {
        if (this.opcao == 20) {
            if (anterior == null) {

                popSelectList();
                anterior = new Ponto(0, (e.getY() - desY), (e.getX() - desX));
                indexPoli = poliControl.getPoliedros().indexOf(selectPoli);
            } else {
                this.poliControl.transladarYZ(selectPoli, anterior, new Ponto(0, (e.getY() - desY), (e.getX() - desX)));
                anterior = new Ponto(0, (e.getY() - desY), (e.getX() - desX));
                zerarTela();
                desenharTela();
                gcYZ.setStroke(Color.RED);
                this.selectPoli.drawSelectYZ(gcYZ);
                gcYZ.setStroke(Color.BLACK);
            }
        }

    }

    public void transladarPolXY(MouseEvent e) {
        if (this.opcao == 2) {
            if (e.getSource() == canvasXY) {
                if (anterior == null) {

                    popSelectList();
                    anterior = new Ponto((e.getX() - desX), (e.getY() - desY), 0);
                    indexPol = polControl.getPoligonos().indexOf(selectPol);
                } else {
                    this.polControl.transladarXY(selectPol, anterior, new Ponto((e.getX() - desX), (e.getY() - desY), 0));
                    anterior = new Ponto((e.getX() - desX), (e.getY() - desY), 0);
                    zerarTela();
                    desenharTela();
                    gcXY.setStroke(Color.RED);
                    this.selectPol.drawSelectXY(gcXY);
                    gcXY.setStroke(Color.BLACK);
                }
            }
        }
    }

    public void transladarPolXZ(MouseEvent e) {
        if (this.opcao == 2) {
            if (e.getSource() == canvasXZ) {
                if (anterior == null) {

                    popSelectList();
                    anterior = new Ponto((e.getX() - desX), 0, (e.getY() - desY));
                    indexPol = polControl.getPoligonos().indexOf(selectPol);
                } else {
                    this.polControl.transladarXZ(selectPol, anterior, new Ponto((e.getX() - desX), 0, (e.getY() - desY)));
                    anterior = new Ponto((e.getX() - desX), 0, (e.getY() - desY));
                    zerarTela();
                    desenharTela();
                    gcXZ.setStroke(Color.RED);
                    this.selectPol.drawSelectXZ(gcXZ);
                    gcXZ.setStroke(Color.BLACK);
                }
            }
        }
    }

    public void transladarPolYZ(MouseEvent e) {
        if (this.opcao == 2) {
            if (e.getSource() == canvasYZ) {
                if (anterior == null) {

                    popSelectList();
                    anterior = new Ponto(0, (e.getY() - desY), (e.getX() - desX));
                    indexPol = polControl.getPoligonos().indexOf(selectPol);
                } else {
                    this.polControl.transladarYZ(selectPol, anterior, new Ponto(0, (e.getY() - desY), (e.getX() - desX)));
                    anterior = new Ponto(0, (e.getY() - desY), (e.getX() - desX));
                    zerarTela();
                    desenharTela();
                    gcYZ.setStroke(Color.RED);
                    this.selectPol.drawSelectYZ(gcYZ);
                    gcYZ.setStroke(Color.BLACK);
                }
            }
        }
    }

    @FXML
    public void rotacionarPoligono(ActionEvent e) {
        selopcao(3);
        this.canvasXY.setOnMouseDragged(this::rotacionarPolXY);
        this.canvasXZ.setOnMouseDragged(this::rotacionarPolXZ);
        this.canvasYZ.setOnMouseDragged(this::rotacionarPolYZ);
        this.canvasXY.setOnMouseClicked(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                popSelectList();
            }
        });
        this.canvasXZ.setOnMouseClicked(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                popSelectList();
            }
        });
        this.canvasYZ.setOnMouseClicked(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                popSelectList();
            }
        });
        anterior = null;

    }

    public void rotacionarPolXY(MouseEvent e) {
        if (this.opcao == 3) {
            if (e.getSource() == canvasXY) {

                if (anterior == null) {
                    popSelectList();

                    anterior = new Ponto((e.getX() - desX), (e.getY() - desY));
                    indexPol = polControl.getPoligonos().indexOf(selectPol);
                } else {
                    this.polControl.rotacionarXY(selectPol, anterior, new Ponto((e.getX() - desX), (e.getY() - desY)));
                    anterior = new Ponto((e.getX() - desX), (e.getY() - desY));
                    zerarTela();
                    desenharTela();
                    gcXY.setStroke(Color.RED);
                    this.selectPol.drawSelectXY(gcXY);
                    gcXY.setStroke(Color.BLACK);
                }
            }
        }
    }

    public void rotacionarPolXZ(MouseEvent e) {
        if (this.opcao == 3) {
            if (e.getSource() == canvasXZ) {

                if (anterior == null) {
                    popSelectList();

                    anterior = new Ponto((e.getX() - desX), (e.getY() - desY));
                    indexPol = polControl.getPoligonos().indexOf(selectPol);
                } else {
                    this.polControl.rotacionarXZ(selectPol, anterior, new Ponto((e.getX() - desX), (e.getY() - desY)));
                    anterior = new Ponto((e.getX() - desX), (e.getY() - desY));
                    zerarTela();
                    desenharTela();
                    gcXY.setStroke(Color.RED);
                    this.selectPol.drawSelectXZ(gcXZ);
                    gcXY.setStroke(Color.BLACK);
                }
            }
        }
    }

    public void rotacionarPolYZ(MouseEvent e) {
        if (this.opcao == 3) {
            if (e.getSource() == canvasYZ) {

                if (anterior == null) {
                    popSelectList();

                    anterior = new Ponto((e.getX() - desX), (e.getY() - desY));
                    indexPol = polControl.getPoligonos().indexOf(selectPol);
                } else {
                    this.polControl.rotacionarYZ(selectPol, anterior, new Ponto((e.getX() - desX), (e.getY() - desY)));
                    anterior = new Ponto((e.getX() - desX), (e.getY() - desY));
                    zerarTela();
                    desenharTela();
                    gcXY.setStroke(Color.RED);
                    this.selectPol.drawSelectYZ(gcYZ);
                    gcXY.setStroke(Color.BLACK);
                }
            }
        }
    }

    @FXML
    public void rotacionarPoliedro(ActionEvent e) {
        selopcao(30);
        this.canvasXY.setOnMouseDragged(this::rotacionarPoliXY);
        this.canvasXZ.setOnMouseDragged(this::rotacionarPoliXZ);
        this.canvasYZ.setOnMouseDragged(this::rotacionarPoliYZ);
        anterior = null;

    }

    public void rotacionarPoliXY(MouseEvent e) {
        if (this.opcao == 30) {
            if (e.getSource() == canvasXY) {
                if (anterior == null) {
                    popSelectList();
                    anterior = new Ponto((e.getX() - desX), (e.getY() - desY), 0);
                    indexPoli = poliControl.getPoliedros().indexOf(selectPoli);
                } else {
                    this.poliControl.rotacionarXY(selectPoli, anterior, new Ponto((e.getX() - desX), (e.getY() - desY), 0));
                    anterior = new Ponto((e.getX() - desX), (e.getY() - desY));
                    if (selectPoli.isOcultarFaces()) {
                        this.poliControl.testeDeVisibilidade(selectPoli);
                    }
                    zerarTela();
                    desenharTela();
                    gcXY.setStroke(Color.RED);
                    this.selectPoli.drawSelectXY(gcXY);
                    gcXY.setStroke(Color.BLACK);
                }
            }
        }
    }

    public void rotacionarPoliXZ(MouseEvent e) {

        if (this.opcao == 30) {
            if (e.getSource() == canvasXZ) {
                if (anterior == null) {
                    popSelectList();
                    anterior = new Ponto((e.getX() - desX), 0, (e.getY() - desY));
                    indexPoli = poliControl.getPoliedros().indexOf(selectPoli);
                } else {
                    this.poliControl.rotacionarXZ(selectPoli, anterior, new Ponto((e.getX() - desX), 0, (e.getY() - desY)));
                    anterior = new Ponto((e.getX() - desX), (e.getY() - desY));
                    if (selectPoli.isOcultarFaces()) {
                        this.poliControl.testeDeVisibilidade(selectPoli);
                    }
                    zerarTela();
                    desenharTela();
                    gcXY.setStroke(Color.RED);
                    this.selectPoli.drawSelectXY(gcXY);
                    gcXY.setStroke(Color.BLACK);
                }
            }
        }
    }

    public void rotacionarPoliYZ(MouseEvent e) {
        if (this.opcao == 30) {
            if (e.getSource() == canvasYZ) {
                if (anterior == null) {
                    popSelectList();
                    anterior = new Ponto((e.getX() - desX), (e.getY() - desY));
                    indexPoli = poliControl.getPoliedros().indexOf(selectPoli);
                } else {
                    this.poliControl.rotacionarYZ(selectPoli, anterior, new Ponto(0, (e.getY() - desY), (e.getX() - desX)));
                    anterior = new Ponto((e.getX() - desX), (e.getY() - desY));
                    if (selectPoli.isOcultarFaces()) {
                        this.poliControl.testeDeVisibilidade(selectPoli);
                    }
                    zerarTela();
                    desenharTela();
                    gcXY.setStroke(Color.RED);
                    this.selectPoli.drawSelectXY(gcXY);
                    gcXY.setStroke(Color.BLACK);
                }
            }
        }
    }

    @FXML
    public void escalaPoligono(ActionEvent e) {
        selopcao(4);
        this.canvasXY.setOnMouseDragged(this::escalaPolXY);
        this.canvasXZ.setOnMouseDragged(this::escalaPolXZ);
        this.canvasYZ.setOnMouseDragged(this::escalaPolYZ);
        this.canvasXY.setOnMouseClicked(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                popSelectList();
            }
        });
        this.canvasXZ.setOnMouseClicked(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                popSelectList();
            }
        });
        this.canvasYZ.setOnMouseClicked(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                popSelectList();
            }
        });
        desenharTela();
        anterior = null;
    }

    public void escalaPolXY(MouseEvent e) {
        if (this.opcao == 4) {
            if (e.getSource() == canvasXY) {
                if (anterior == null) {
                    popSelectList();
                    anterior = new Ponto((e.getX() - desX), (e.getY() - desY), 0);
                    indexPol = polControl.getPoligonos().indexOf(selectPol);
                } else {
                    selectPol = polControl.getPoligonos().get(indexPol).getClone();
                    this.polControl.escalaXY(polControl.getPoligonos().get(indexPol), selectPol, anterior, new Ponto((e.getX() - desX), (e.getY() - desY), 0));
                    zerarTela();
                    desenharTela();
                    gcXY.setStroke(Color.RED);
                    this.selectPol.drawSelectXY(gcXY);
                    gcXY.setStroke(Color.BLACK);
                }
            }
        }
    }

    public void escalaPolXZ(MouseEvent e) {
        if (this.opcao == 4) {
            if (e.getSource() == canvasXZ) {
                if (anterior == null) {
                    popSelectList();
                    anterior = new Ponto((e.getX() - desX), 0, (e.getY() - desY));
                    indexPol = polControl.getPoligonos().indexOf(selectPol);
                } else {
                    selectPol = polControl.getPoligonos().get(indexPol).getClone();
                    this.polControl.escalaXZ(polControl.getPoligonos().get(indexPol), selectPol, anterior, new Ponto((e.getX() - desX), 0, (e.getY() - desY)));
                    zerarTela();
                    desenharTela();
                    gcXZ.setStroke(Color.RED);
                    this.selectPol.drawSelectXZ(gcXZ);
                    gcXZ.setStroke(Color.BLACK);
                }
            }
        }
    }

    public void escalaPolYZ(MouseEvent e) {
        if (this.opcao == 4) {
            if (e.getSource() == canvasYZ) {
                if (anterior == null) {
                    popSelectList();
                    anterior = new Ponto(0, (e.getY() - desY), (e.getX() - desX));
                    indexPol = polControl.getPoligonos().indexOf(selectPol);
                } else {
                    selectPol = polControl.getPoligonos().get(indexPol).getClone();
                    this.polControl.escalaYZ(polControl.getPoligonos().get(indexPol), selectPol, anterior, new Ponto(0, (e.getY() - desY), (e.getX() - desX)));
                    zerarTela();
                    desenharTela();
                    gcYZ.setStroke(Color.RED);
                    this.selectPol.drawSelectXY(gcYZ);
                    gcYZ.setStroke(Color.BLACK);
                }
            }
        }
    }

    @FXML
    public void escalaPoliedro(ActionEvent e) {
        selopcao(40);
        this.canvasXY.setOnMouseDragged(this::escalaPoliXY);
        this.canvasXZ.setOnMouseDragged(this::escalaPoliXZ);
        this.canvasYZ.setOnMouseDragged(this::escalaPoliYZ);
        this.canvasXY.setOnMouseClicked(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                popSelectList();
            }
        });
        this.canvasXZ.setOnMouseClicked(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                popSelectList();
            }
        });
        this.canvasYZ.setOnMouseClicked(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                popSelectList();
            }
        });
        desenharTela();
        anterior = null;
    }

    public void escalaPoliXY(MouseEvent e) {
        if (this.opcao == 40) {
            if (e.getSource() == canvasXY) {
                if (anterior == null) {
                    popSelectList();
                    anterior = new Ponto((e.getX() - desX), (e.getY() - desY), 0);
                    indexPoli = poliControl.getPoliedros().indexOf(selectPoli);
                } else {
                    selectPoli = poliControl.getPoliedros().get(indexPoli).getClone();
                    this.poliControl.escalaXY(poliControl.getPoliedros().get(indexPoli), selectPoli, anterior, new Ponto((e.getX() - desX), (e.getY() - desY), 0));
                    zerarTela();
                    desenharTela();
                    gcXY.setStroke(Color.RED);
                    this.selectPoli.drawSelectXY(gcXY);
                    gcXY.setStroke(Color.BLACK);
                }
            }
        }
    }

    public void escalaPoliXZ(MouseEvent e) {
        if (this.opcao == 40) {
            if (e.getSource() == canvasXZ) {
                if (anterior == null) {
                    popSelectList();
                    anterior = new Ponto((e.getX() - desX), 0, (e.getY() - desY));
                    indexPoli = poliControl.getPoliedros().indexOf(selectPoli);
                } else {
                    selectPoli = poliControl.getPoliedros().get(indexPoli).getClone();
                    this.poliControl.escalaXZ(poliControl.getPoliedros().get(indexPoli), selectPoli, anterior, new Ponto((e.getX() - desX), 0, (e.getY() - desY)));
                    zerarTela();
                    desenharTela();
                    gcXZ.setStroke(Color.RED);
                    this.selectPoli.drawSelectXZ(gcXZ);
                    gcXZ.setStroke(Color.BLACK);
                }
            }
        }
    }

    public void escalaPoliYZ(MouseEvent e) {
        if (this.opcao == 40) {
            if (e.getSource() == canvasYZ) {
                if (anterior == null) {
                    popSelectList();
                    anterior = new Ponto(0, (e.getY() - desY), (e.getX() - desX));
                    indexPoli = poliControl.getPoliedros().indexOf(selectPoli);
                } else {
                    selectPoli = poliControl.getPoliedros().get(indexPoli).getClone();
                    this.poliControl.escalaYZ(poliControl.getPoliedros().get(indexPoli), selectPoli, anterior, new Ponto(0, (e.getY() - desY), (e.getX() - desX)));
                    zerarTela();
                    desenharTela();
                    gcYZ.setStroke(Color.RED);
                    this.selectPoli.drawSelectYZ(gcYZ);
                    gcYZ.setStroke(Color.BLACK);
                }
            }
        }
    }

    public void selopcao(int x) {
        this.opcao = x;
    }

    @FXML
    public void cisalharPoligono(ActionEvent e) {
        selopcao(5);

        canvasXY.setOnMouseDragged(this::cisalharPolXY);
        canvasXZ.setOnMouseDragged(this::cisalharPolXZ);
        canvasYZ.setOnMouseDragged(this::cisalharPolYZ);
        this.canvasXY.setOnMouseClicked(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                popSelectList();
            }
        });
        this.canvasXZ.setOnMouseClicked(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                popSelectList();
            }
        });
        this.canvasYZ.setOnMouseClicked(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                popSelectList();
            }
        });
        desenharTela();
        anterior = null;

    }

    @FXML
    public void cisalharPoliedro(ActionEvent e) {
        selopcao(50);

        canvasXY.setOnMouseDragged(this::cisalharPoliXY);
        canvasXZ.setOnMouseDragged(this::cisalharPoliXZ);
        canvasYZ.setOnMouseDragged(this::cisalharPoliYZ);
        this.canvasXY.setOnMouseClicked(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                popSelectList();
            }
        });
        this.canvasXZ.setOnMouseClicked(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                popSelectList();
            }
        });
        this.canvasYZ.setOnMouseClicked(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                popSelectList();
            }
        });
        desenharTela();
        anterior = null;

    }

    public void cisalharPolXY(MouseEvent e) {
        if (this.opcao == 5) {
            if (e.getSource() == canvasXY) {
                if (anterior == null) {
                    popSelectList();
                    anterior = new Ponto((e.getX() - desX), (e.getY() - desY), 0);
                    indexPol = polControl.getPoligonos().indexOf(selectPol);
                } else {
                    selectPol = polControl.getPoligonos().get(indexPol).getClone();
                    this.polControl.cisalharXY(polControl.getPoligonos().get(indexPol), selectPol, anterior, new Ponto((e.getX() - desX), (e.getY() - desY), 0));
                    zerarTela();
                    desenharTela();
                    gcXY.setStroke(Color.RED);
                    this.selectPol.drawSelectXY(gcXY);
                    gcXY.setStroke(Color.BLACK);
                }
            }
        }
    }

    public void cisalharPolXZ(MouseEvent e) {
        if (this.opcao == 5) {
            if (e.getSource() == canvasXZ) {
                if (anterior == null) {
                    popSelectList();
                    anterior = new Ponto((e.getX() - desX), 0, (e.getY() - desY));
                    indexPol = polControl.getPoligonos().indexOf(selectPol);
                } else {
                    selectPol = polControl.getPoligonos().get(indexPol).getClone();
                    this.polControl.cisalharXZ(polControl.getPoligonos().get(indexPol), selectPol, anterior, new Ponto((e.getX() - desX), 0, (e.getY() - desY)));
                    zerarTela();
                    desenharTela();
                    gcXY.setStroke(Color.RED);
                    this.selectPol.drawSelectXY(gcXY);
                    gcXY.setStroke(Color.BLACK);
                }
            }
        }
    }

    public void cisalharPolYZ(MouseEvent e) {
        if (this.opcao == 5) {
            if (e.getSource() == canvasYZ) {
                if (anterior == null) {
                    popSelectList();
                    anterior = new Ponto(0, (e.getY() - desY), (e.getX() - desX));
                    indexPol = polControl.getPoligonos().indexOf(selectPol);
                } else {
                    selectPol = polControl.getPoligonos().get(indexPol).getClone();
                    this.polControl.cisalharYZ(polControl.getPoligonos().get(indexPol), selectPol, anterior, new Ponto(0, (e.getY() - desY), (e.getX() - desX)));
                    zerarTela();
                    desenharTela();
                    gcXY.setStroke(Color.RED);
                    this.selectPol.drawSelectYZ(gcYZ);
                    gcXY.setStroke(Color.BLACK);
                }
            }
        }
    }

    public void cisalharPoliXY(MouseEvent e) {
        if (this.opcao == 50) {
            if (e.getSource() == canvasXY) {
                if (anterior == null) {
                    popSelectList();
                    anterior = new Ponto((e.getX() - desX), (e.getY() - desY), 0);
                    indexPoli = poliControl.getPoliedros().indexOf(selectPoli);
                } else {
                    selectPoli = poliControl.getPoliedros().get(indexPol).getClone();
                    this.poliControl.cisalharXY(poliControl.getPoliedros().get(indexPol), selectPoli, anterior, new Ponto((e.getX() - desX), (e.getY() - desY), 0));
                    zerarTela();
                    desenharTela();
                    gcXY.setStroke(Color.RED);
                    this.selectPoli.drawSelectXY(gcXY);
                    gcXY.setStroke(Color.BLACK);
                }
            }
        }
    }

    public void cisalharPoliXZ(MouseEvent e) {
        if (this.opcao == 50) {
            if (e.getSource() == canvasXZ) {
                if (anterior == null) {
                    popSelectList();
                    anterior = new Ponto((e.getX() - desX), 0, (e.getY() - desY));
                    indexPoli = poliControl.getPoliedros().indexOf(selectPoli);
                } else {
                    selectPoli = poliControl.getPoliedros().get(indexPol).getClone();
                    this.poliControl.cisalharXZ(poliControl.getPoliedros().get(indexPol), selectPoli, anterior, new Ponto((e.getX() - desX), 0, (e.getY() - desY)));
                    zerarTela();
                    desenharTela();
                    gcXZ.setStroke(Color.RED);
                    this.selectPoli.drawSelectXZ(gcXZ);
                    gcXZ.setStroke(Color.BLACK);
                }
            }
        }
    }

    public void cisalharPoliYZ(MouseEvent e) {
        if (this.opcao == 50) {
            if (e.getSource() == canvasYZ) {
                if (anterior == null) {
                    popSelectList();
                    anterior = new Ponto(0, (e.getY() - desY), (e.getX() - desX));
                    indexPoli = poliControl.getPoliedros().indexOf(selectPoli);
                } else {
                    selectPoli = poliControl.getPoliedros().get(indexPol).getClone();
                    this.poliControl.cisalharYZ(poliControl.getPoliedros().get(indexPol), selectPoli, anterior, new Ponto(0, (e.getY() - desY), (e.getX() - desX)));
                    zerarTela();
                    desenharTela();
                    gcXY.setStroke(Color.RED);
                    this.selectPoli.drawSelectXY(gcXY);
                    gcXY.setStroke(Color.BLACK);
                }
            }
        }
    }

    public void zerarTela() {
        gcXY.clearRect(-(canvasXY.getWidth()), -(canvasXY.getHeight()), canvasXY.getWidth() + desX, canvasXY.getHeight() + desY);
        gcXZ.clearRect(-(canvasXY.getWidth()), -(canvasXY.getHeight()), canvasXY.getWidth() + desX, canvasXY.getHeight() + desY);
        gcYZ.clearRect(-(canvasXY.getWidth()), -(canvasXY.getHeight()), canvasXY.getWidth() + desX, canvasXY.getHeight() + desY);
        gcPer.clearRect(-(canvasXY.getWidth()), -(canvasXY.getHeight()), canvasXY.getWidth() + desX, canvasXY.getHeight() + desY);

    }

    @FXML
    public void pintarBordaPoligono(ActionEvent e) {
        this.selectPol.setCorBorda(ColorPikerPoligono.getValue());
        zerarTela();
        desenharTela();
        selopcao(1);
    }

    @FXML
    public void pintarFundoPoligono(ActionEvent e) {
        this.selectPol.setCorFundo(ColorPikerPoligono.getValue());
        zerarTela();
        this.selectPol.autoFillXY(gcXY);
        this.selectPol.autoFillXZ(gcXZ);
        this.selectPol.autoFillYZ(gcYZ);
        desenharTela();
        selopcao(1);

    }

    @FXML
    public void pintarBordaPoliedro(ActionEvent e) {
        this.selectPoli.setCorBorda(ColorPikerPoliedro.getValue());
        zerarTela();
        desenharTela();
        selopcao(1);
    }

    @FXML
    public void pintarFundoPoliedro(ActionEvent e) {
        this.poliControl.testeDeVisibilidade(this.selectPoli);
        this.selectPoli.setCorFundo(ColorPikerPoliedro.getValue());
        zerarTela();
        /*this.selectPoli.autoFillXY(gcXY);
        this.selectPoli.autoFillXZ(gcXZ);
        this.selectPoli.autoFillYZ(gcYZ);*/
        desenharTela();
        selopcao(1);

    }

    @FXML
    public void revolucao(ActionEvent e) {
        int nlados = 0;
        double graus = 0;
        String s = "0";
        s = JOptionPane.showInputDialog(null, "Numero de lados: ", "The title", JOptionPane.QUESTION_MESSAGE);
        if (s != null) {
            nlados = Integer.parseInt(s);
        }

        s = JOptionPane.showInputDialog(null, "graus da rotaçao: ", "The title", JOptionPane.QUESTION_MESSAGE);
        graus = Integer.parseInt(s);
        s = JOptionPane.showInputDialog(null, "Eixo a ser : ", "The title", JOptionPane.QUESTION_MESSAGE);

        if (nlados > 2) {
            this.poliControl.revolucao(this.selectPol, nlados, s, graus);
        }

        this.polControl.getPoligonos().remove(this.selectPol);
        desenharTela();
    }

    @FXML
    public void extrusao(ActionEvent e) {
        int nlados = 0;
        double nfatias = 0;
        String s = "0";
        s = JOptionPane.showInputDialog(null, "Numero de fatias: ", " ", JOptionPane.QUESTION_MESSAGE);
        if (s != null) {
            nlados = Integer.parseInt(s);
        }

        s = JOptionPane.showInputDialog(null, "Tamanho do poliedro: ", " ", JOptionPane.QUESTION_MESSAGE);
        nfatias = Integer.parseInt(s);
        s = JOptionPane.showInputDialog(null, "Eixo a ser feito a instrusao : ", " ", JOptionPane.QUESTION_MESSAGE);

        if (nlados > 2) {
            this.poliControl.extrusao(this.selectPol, nlados, s, nfatias);
        }
        this.polControl.getPoligonos().remove(this.selectPol);

        desenharTela();
    }

    /*public void sombreamentoPoliedro(ActionEvent e) {
        canvasXY.setOnMouseDragged(this::sombreamentoPoli);
        canvasXZ.setOnMouseDragged(this::sombreamentoPoli);
        canvasYZ.setOnMouseDragged(this::sombreamentoPoli);
    }*/
    @FXML
    public void sombreamentoPoli() {
        Il.setRGB(150, 150, 150);
        Ila.setRGB(120, 120, 120);
        /*alterarIl();
        alterarIla();
        alterarLuzIncidente();
        alterarN();
        alterarKd();
        alterarKd();
        alterarKs();*/
        

        selectPoli.setSobrear(true);
        this.poliControl.sombreamentoConstanteXY(gcXY, Ila, Il, luzIncidente, selectPoli);
        this.poliControl.sombreamentoConstanteXZ(gcXZ, Ila, Il, luzIncidente, selectPoli);
        this.poliControl.sombreamentoConstanteYZ(gcYZ, Ila, Il, luzIncidente, selectPoli);
        this.poliControl.sombreamentoConstantePerspec(gcPer, Ila, Il, luzIncidente, selectPoli);
    }

    @FXML
    public void ocultarFace(ActionEvent e) {
        if (this.selectPoli.isOcultarFaces()) {
            this.selectPoli.setOcultarFaces(false);
        } else {
            this.selectPoli.setOcultarFaces(true);
            this.poliControl.testeDeVisibilidade(selectPoli);
        }
        zerarTela();
        desenharTela();
        selopcao(1);

        gcXY.setStroke(Color.GREEN);
        gcXY.strokeLine(selectPoli.getFaces().getLast().getArestas().get(0).getX(), selectPoli.getFaces().getLast().getArestas().get(0).getY(),
                selectPoli.getFaces().getLast().getArestas().get(1).getX(), selectPoli.getFaces().getLast().getArestas().get(1).getY());
        gcXY.setStroke(Color.BLUE);
        gcXY.strokeLine(selectPoli.getFaces().getLast().getArestas().get(2).getX(), selectPoli.getFaces().getLast().getArestas().get(2).getY(),
                selectPoli.getFaces().getLast().getArestas().get(1).getX(), selectPoli.getFaces().getLast().getArestas().get(1).getY());
        gcXY.setStroke(Color.GREEN);
        gcXY.strokeLine(selectPoli.getFaces().getFirst().getArestas().get(0).getX(), selectPoli.getFaces().getFirst().getArestas().get(0).getY(),
                selectPoli.getFaces().getFirst().getArestas().get(1).getX(), selectPoli.getFaces().getFirst().getArestas().get(1).getY());
        gcXY.setStroke(Color.BLUE);
        gcXY.strokeLine(selectPoli.getFaces().getFirst().getArestas().get(2).getX(), selectPoli.getFaces().getFirst().getArestas().get(2).getY(),
                selectPoli.getFaces().getFirst().getArestas().get(1).getX(), selectPoli.getFaces().getFirst().getArestas().get(1).getY());
    }

    public void popSelectList() {
        if (selectPol != null) {
            polControl.getPoligonos().set(indexPol, selectPol);
        }
        if (selectPoli != null) {
            poliControl.getPoliedros().set(indexPoli, selectPoli);
        }
        anterior = null;
    }

    @FXML
    public void alterarVrp() {
        JTextField X = new JTextField();
        JTextField Y = new JTextField();
        JTextField Z = new JTextField();
        Object[] message = {
            "X:", X,
            "Z:", Z,
            "Y:", Y
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Alterar VRP", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            vrpPers.setXYZ(Integer.parseInt(X.getText()), Integer.parseInt(Y.getText()), Integer.parseInt(Z.getText()));
            limparTela();
            desenharTela();
        } else {
            System.out.println("Login canceled");
        }
    }

    @FXML
    public void alterarP() {
        JTextField X = new JTextField();
        JTextField Y = new JTextField();
        JTextField Z = new JTextField();
        Object[] message = {
            "X:", X,
            "Z:", Z,
            "Y:", Y
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Alterar P", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            pPers.setXYZ(Integer.parseInt(X.getText()), Integer.parseInt(Y.getText()), Integer.parseInt(Z.getText()));
            limparTela();
            desenharTela();
        } else {
            System.out.println("Login canceled");
        }
    }

    @FXML
    public void alterarLuzIncidente() {
        JTextField X = new JTextField();
        JTextField Y = new JTextField();
        JTextField Z = new JTextField();
        Object[] message = {
            "X:", X,
            "Z:", Z,
            "Y:", Y
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Alterar Luz Incidente", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            luzIncidente.setXYZ(Integer.parseInt(X.getText()), Integer.parseInt(Y.getText()), Integer.parseInt(Z.getText()));
            limparTela();
            desenharTela();
        } else {
            System.out.println("Login canceled");
        }
    }

    @FXML
    public void alterarKa() {
        JTextField r = new JTextField();
        JTextField b = new JTextField();
        JTextField g = new JTextField();
        Object[] message = {
            "R:", r,
            "G:", g,
            "B:", b
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Alterar Ka", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            selectPoli.setKa(Double.parseDouble(r.getText()), Double.parseDouble(g.getText()), Double.parseDouble(b.getText()));
            limparTela();
            desenharTela();
        } else {
            System.out.println("Login canceled");
        }
    }

    @FXML
    public void alterarKd() {
        JTextField r = new JTextField();
        JTextField b = new JTextField();
        JTextField g = new JTextField();
        Object[] message = {
            "R:", r,
            "G:", g,
            "B:", b
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Alterar Kd", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            selectPoli.setKd(Double.parseDouble(r.getText()), Double.parseDouble(g.getText()), Double.parseDouble(b.getText()));
            limparTela();
            desenharTela();
        } else {
            System.out.println("Login canceled");
        }
    }

    @FXML
    public void alterarKs() {
        JTextField r = new JTextField();
        JTextField b = new JTextField();
        JTextField g = new JTextField();
        Object[] message = {
            "R:", r,
            "G:", g,
            "B:", b
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Alterar Ks", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            selectPoli.setKs(Double.parseDouble(r.getText()), Double.parseDouble(g.getText()), Double.parseDouble(b.getText()));
            limparTela();
            desenharTela();
        } else {
            System.out.println("Login canceled");
        }
    }

    @FXML
    public void alterarDp() {
        JTextField dp = new JTextField();

        Object[] message = {
            "dp:", dp,};
        int option = JOptionPane.showConfirmDialog(null, message, "Alterar Dp", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            dpPers = Double.parseDouble(dp.getText());
            limparTela();
            desenharTela();
        } else {
            System.out.println("Login canceled");
        }
    }
    
    @FXML
    public void alterarN() {
        JTextField dp = new JTextField();

        Object[] message = {
            "N:", dp,};
        int option = JOptionPane.showConfirmDialog(null, message, "Alterar n", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            selectPoli.setN(Double.parseDouble(dp.getText()));
            limparTela();
            desenharTela();
        } else {
            System.out.println("Login canceled");
        }
    }

    @FXML
    public void alterarIla() {
        JTextField r = new JTextField();
        JTextField b = new JTextField();
        JTextField g = new JTextField();
        Object[] message = {
            "R:", r,
            "G:", g,
            "B:", b
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Alterar Ila", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Ila.setRGB(Double.parseDouble(r.getText()), Double.parseDouble(g.getText()), Double.parseDouble(b.getText()));
            limparTela();
            desenharTela();
        } else {
            System.out.println("Login canceled");
        }
    }

    @FXML
    public void alterarIl() {
        JTextField r = new JTextField();
        JTextField b = new JTextField();
        JTextField g = new JTextField();
        Object[] message = {
            "R:", r,
            "G:", g,
            "B:", b
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Alterar Il", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Il.setRGB(Double.parseDouble(r.getText()), Double.parseDouble(g.getText()), Double.parseDouble(b.getText()));
            limparTela();
            desenharTela();
        } else {
            System.out.println("Login canceled");
        }
    }

}
