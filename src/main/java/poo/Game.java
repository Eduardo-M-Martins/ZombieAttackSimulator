package poo;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.Charset;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.stage.*;
import poo.Character.Enume;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class Game extends Application {
    public static final int CELL_WIDTH = 50;
    public static final int CELL_HEIGHT = 50;
    public static final int NLIN = 10;
    public static final int NCOL = 16;

    public static int DUMBAMOUNT;
    public static int ZOMBIEAMOUNT;
    public static int RUNNERAMOUNT;
    public static int HEALERAMOUNT;
    public static int SOLDIERAMOUNT;
    public static Game game = null;

    private Random random;
    private Map<String, Image> image;
    private List<Cell> cells;
    private List<Character> characters;
    private boolean continueOption;

    public static Game getInstance() {
        return game;
    }

    public Game(boolean continueOption) {
        game = this;
        random = new Random();
        this.continueOption = continueOption;
    }

    public boolean carregaDados() {
        Path path = Paths.get(".AMOUNTS.txt");
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("utf8"))) {
            String line = null;
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                if (data[0].equals("DUMBAMOUNT")) {
                    DUMBAMOUNT = Integer.parseInt(data[1]);
                }
                if (data[0].equals("HEALERAMOUNT")) {
                    HEALERAMOUNT = Integer.parseInt(data[1]);
                }
                if (data[0].equals("SOLDIERAMOUNT")) {
                    SOLDIERAMOUNT = Integer.parseInt(data[1]);
                }
                if (data[0].equals("ZOMBIEAMOUNT")) {
                    ZOMBIEAMOUNT = Integer.parseInt(data[1]);
                }
                if (data[0].equals("RUNNERAMOUNT")) {
                    RUNNERAMOUNT = Integer.parseInt(data[1]);
                }
            }
        } catch (IOException x) {
            System.err.format("I/O error: %s%n", x);
        }
        return true;
    }

    public int random_1(int limit) {
        return random.nextInt(limit);
    }

    public int random_2(int limit) {
        int rand = 1;
        while (rand == 1 || rand == -1) {
            int max = limit;
            int min = -limit;
            int range = max - min + 1;
            rand = (int) (Math.random() * range) + min;
        }
        return rand;
    }

    public Cell getCelula(int nLin, int nCol) {
        int pos = (nLin * NCOL) + nCol;
        return cells.get(pos);
    }

    private void loadImagens() {
        image = new HashMap<>();

        Image aux = new Image("file:Images/dumb.jpeg");
        image.put("Dumb", aux);
        aux = new Image("file:Images/dumbInfected.jpeg");
        image.put("DumbInf", aux);
        aux = new Image("file:Images/zombie.jpeg");
        image.put("Zombie", aux);
        aux = new Image("file:Images/rip.jpeg");
        image.put("Dead", aux);
        aux = new Image("file:Images/grass.jpeg");
        image.put("Grass", aux);
        aux = new Image("file:Images/runner.jpeg");
        image.put("Runner", aux);
        aux = new Image("file:Images/healer.jpeg");
        image.put("Healer", aux);
        aux = new Image("file:Images/healerInfected.jpeg");
        image.put("HealerInf", aux);
        aux = new Image("file:Images/soldier.jpeg");
        image.put("Soldier", aux);
        aux = new Image("file:Images/soldierInfected.jpeg");
        image.put("SoldierInf", aux);
        image.put("Null", null);
    }

    public Image getImage(String id) {
        return image.get(id);
    }

    @Override
    public void start(Stage primaryStage) {
        if (!continueOption) {
            carregaDados();
        }

        primaryStage.setTitle("GAME - Zombie Attack Simulation");

        loadImagens();

        GridPane tab = new GridPane();
        BackgroundImage bgi = new BackgroundImage(new Image("file:Images/brown.jpg", 575, 620, false, true),
                BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        BackgroundImage bgi2 = new BackgroundImage(new Image("file:Images/black.jpg", 980, 620, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        tab.setBackground(new Background(bgi2));
        tab.setAlignment(Pos.CENTER);
        tab.setHgap(-16); // -15
        tab.setVgap(-8); // -7
        tab.setPadding(new Insets(5, 5, 5, 5));

        cells = new ArrayList<>(NLIN * NCOL);
        for (int lin = 0; lin < NLIN; lin++) {
            for (int col = 0; col < NCOL; col++) {
                Cell cel = new Cell(lin, col);
                cells.add(cel);
                tab.add(cel, col, lin);
            }
        }

        characters = new ArrayList<>(NLIN * NCOL);

        if (continueOption) {
            readSave(characters, cells);
            fixAmount();
        }

        if (!continueOption) {
            for (int i = 0; i < SOLDIERAMOUNT; i++) {
                boolean posOk = false;
                while (!posOk) {
                    int lin = random.nextInt(NLIN);
                    int col = random.nextInt(NCOL);
                    if (this.getCelula(lin, col).getCharacter() == null) {
                        characters.add(new Soldier(lin, col, "SV"));
                        posOk = true;
                    }
                }
            }

            for (int i = 0; i < HEALERAMOUNT; i++) {
                boolean posOk = false;
                while (!posOk) {
                    int lin = random.nextInt(NLIN);
                    int col = random.nextInt(NCOL);
                    if (this.getCelula(lin, col).getCharacter() == null) {
                        characters.add(new Healer(lin, col, "CV"));
                        posOk = true;
                    }
                }
            }

            for (int i = 0; i < ZOMBIEAMOUNT; i++) {
                boolean posOk = false;
                while (!posOk) {
                    int lin = random.nextInt(NLIN);
                    int col = random.nextInt(NCOL);
                    if (this.getCelula(lin, col).getCharacter() == null) {
                        characters.add(new Zombie(lin, col, "ZV"));
                        posOk = true;
                    }
                }
            }

            for (int i = 0; i < RUNNERAMOUNT; i++) {
                boolean posOk = false;
                while (!posOk) {
                    int lin = random.nextInt(NLIN);
                    int col = random.nextInt(NCOL);
                    if (this.getCelula(lin, col).getCharacter() == null) {
                        characters.add(new Runner(lin, col, "RV"));
                        posOk = true;
                    }
                }
            }

            for (int i = 0; i < DUMBAMOUNT; i++) {
                boolean posOk = false;
                while (!posOk) {
                    int lin = random.nextInt(NLIN);
                    int col = random.nextInt(NCOL);
                    if (this.getCelula(lin, col).getCharacter() == null) {
                        characters.add(new Dumb(lin, col, 2, "BV"));
                        posOk = true;
                    }
                }
            }
            crateRefresh();
            fixAmount();
        }

        final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent; -fx-text-fill: white;";
        final String HOVERED_BUTTON_STYLE = "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";

        HashMap<String, Button> buttons = new HashMap();
        buttons.put("NEXT STEP", new Button("NEXT STEP"));
        buttons.put("NEXT 10", new Button("NEXT 10"));
        buttons.put("NEXT 100", new Button("NEXT 100"));
        buttons.put("BACK", new Button("BACK"));
        buttons.put("SAVE", new Button("SAVE"));
        buttons.put("RESET", new Button("RESET"));

        buttons.forEach((key, value) -> {
            value.setFont(Font.font("Rockwell", 14));
            value.setCursor(Cursor.HAND);
            value.getCursor();
            value.setStyle(IDLE_BUTTON_STYLE);
            value.setAlignment(Pos.TOP_CENTER);
            value.setOnMouseEntered(e -> value.setStyle(HOVERED_BUTTON_STYLE));
            value.setOnMouseExited(e -> value.setStyle(IDLE_BUTTON_STYLE));
        });

        TextField score = new TextField();
        score.setStyle("-fx-text-fill: yellow; -fx-font-weight: bold");
        score.setBorder(Border.EMPTY);
        score.setBackground(Background.EMPTY);
        score.setFont(Font.font("Rockwell", 14));
        score.setEditable(false);
        score.setAlignment(Pos.TOP_RIGHT);
        score.setMinSize(180, 20);
        int intpts = ((ZOMBIEAMOUNT * 200) + (RUNNERAMOUNT * 300))
                - ((DUMBAMOUNT * 50) + (HEALERAMOUNT * 75) + (SOLDIERAMOUNT * 100));
        score.setText("SCORE: " + intpts);

        VBox vb = new VBox();
        vb.setBackground(new Background(bgi));
        HBox hb = new HBox();

        hb.setBackground(new Background(bgi));
        vb.setAlignment(Pos.CENTER);
        hb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(25, 25, 25, 25));
        hb.setSpacing(10);
        hb.getChildren().add(buttons.get("BACK"));
        hb.getChildren().add(buttons.get("SAVE"));
        hb.getChildren().add(buttons.get("RESET"));
        hb.getChildren().add(buttons.get("NEXT STEP"));
        hb.getChildren().add(buttons.get("NEXT 10"));
        hb.getChildren().add(buttons.get("NEXT 100"));
        hb.getChildren().add(score);
        vb.getChildren().add(hb);
        vb.getChildren().add(tab);

        HBox hbt = new HBox();
        hbt.setBackground(new Background(bgi));
        VBox v1 = new VBox();
        v1.setBackground(new Background(bgi));
        VBox v2 = new VBox();
        v2.setBackground(new Background(bgi));
        VBox v3 = new VBox();
        v3.setBackground(new Background(bgi));
        VBox v4 = new VBox();
        v4.setBackground(new Background(bgi));
        VBox v5 = new VBox();
        v5.setBackground(new Background(bgi));

        vb.getChildren().add(hbt);
        hbt.getChildren().add(v1);
        hbt.getChildren().add(v2);
        hbt.getChildren().add(v3);
        hbt.getChildren().add(v4);
        hbt.getChildren().add(v5);
        hbt.setAlignment(Pos.CENTER);

        TextField tb1 = new TextField();
        tb1.setStyle("-fx-text-fill: white;");
        tb1.setBorder(Border.EMPTY);
        tb1.setBackground(Background.EMPTY);
        tb1.setFont(Font.font("Rockwell", 16));
        tb1.setEditable(false);
        tb1.setMinSize(180, 30);
        tb1.setText("       Dumbs alive: " + dumbsAlive());
        v1.getChildren().add(tb1);

        TextField tb2 = new TextField();
        tb2.setStyle("-fx-text-fill: white;");
        tb2.setBorder(Border.EMPTY);
        tb2.setBackground(Background.EMPTY);
        tb2.setFont(Font.font("Rockwell", 16));
        tb2.setEditable(false);
        tb2.setMinSize(180, 30);
        tb2.setText("       Dumbs infected: " + dumbsInf());
        v1.getChildren().add(tb2);

        TextField tb3 = new TextField();
        tb3.setStyle("-fx-text-fill: white;");
        tb3.setBorder(Border.EMPTY);
        tb3.setBackground(Background.EMPTY);
        tb3.setFont(Font.font("Rockwell", 16));
        tb3.setEditable(false);
        tb3.setMinSize(180, 30);
        tb3.setText("       Dumbs dead: " + dumbsDead());
        v1.getChildren().add(tb3);

        TextField tcur1 = new TextField();
        tcur1.setStyle("-fx-text-fill: white;");
        tcur1.setBorder(Border.EMPTY);
        tcur1.setBackground(Background.EMPTY);
        tcur1.setFont(Font.font("Rockwell", 16));
        tcur1.setEditable(false);
        tcur1.setMinSize(180, 30);
        tcur1.setText("    Healer alive: " + healersAlive());
        v2.getChildren().add(tcur1);

        TextField tcur2 = new TextField();
        tcur2.setStyle("-fx-text-fill: white;");
        tcur2.setBorder(Border.EMPTY);
        tcur2.setBackground(Background.EMPTY);
        tcur2.setFont(Font.font("Rockwell", 16));
        tcur2.setEditable(false);
        tcur2.setMinSize(180, 30);
        tcur2.setText("    Healer infected: " + healersInf());
        v2.getChildren().add(tcur2);

        TextField tcur3 = new TextField();
        tcur3.setStyle("-fx-text-fill: white;");
        tcur3.setBorder(Border.EMPTY);
        tcur3.setBackground(Background.EMPTY);
        tcur3.setFont(Font.font("Rockwell", 16));
        tcur3.setEditable(false);
        tcur3.setMinSize(180, 30);
        tcur3.setText("    Healer dead: " + healersDead());
        v2.getChildren().add(tcur3);

        TextField ts1 = new TextField();
        ts1.setStyle("-fx-text-fill: white;");
        ts1.setBorder(Border.EMPTY);
        ts1.setBackground(Background.EMPTY);
        ts1.setFont(Font.font("Rockwell", 16));
        ts1.setEditable(false);
        ts1.setMinSize(180, 30);
        ts1.setText("    Soldiers alive: " + soldiersAlive());
        v3.getChildren().add(ts1);

        TextField ts2 = new TextField();
        ts2.setStyle("-fx-text-fill: white;");
        ts2.setBorder(Border.EMPTY);
        ts2.setBackground(Background.EMPTY);
        ts2.setFont(Font.font("Rockwell", 16));
        ts2.setEditable(false);
        ts2.setMinSize(180, 30);
        ts2.setText("    Soldiers infected: " + soldiersInf());
        v3.getChildren().add(ts2);

        TextField ts3 = new TextField();
        ts3.setStyle("-fx-text-fill: white;");
        ts3.setBorder(Border.EMPTY);
        ts3.setBackground(Background.EMPTY);
        ts3.setFont(Font.font("Rockwell", 16));
        ts3.setEditable(false);
        ts3.setMinSize(180, 30);
        ts3.setText("    Soldiers dead: " + soldiersDead());
        v3.getChildren().add(ts3);

        TextField tz1 = new TextField();
        tz1.setStyle("-fx-text-fill: white;");
        tz1.setBorder(Border.EMPTY);
        tz1.setBackground(Background.EMPTY);
        tz1.setFont(Font.font("Rockwell", 16));
        tz1.setEditable(false);
        tz1.setMinSize(180, 30);
        tz1.setText("    Zombies alive: " + zombiesAlive());
        v4.getChildren().add(tz1);
        TextField tz2 = new TextField();

        tz2.setStyle("-fx-text-fill: white;");
        tz2.setBorder(Border.EMPTY);
        tz2.setBackground(Background.EMPTY);
        tz2.setFont(Font.font("Rockwell", 16));
        tz2.setEditable(false);
        tz2.setMinSize(180, 30);
        tz2.setText("    Zombies dead: " + zombiesDead());
        v4.getChildren().add(tz2);

        TextField tcor1 = new TextField();
        tcor1.setStyle("-fx-text-fill: white;");
        tcor1.setBorder(Border.EMPTY);
        tcor1.setBackground(Background.EMPTY);
        tcor1.setFont(Font.font("Rockwell", 16));
        tcor1.setEditable(false);
        tcor1.setMinSize(180, 30);
        tcor1.setText(" Runner alive: " + runnersAlive());
        v5.getChildren().add(tcor1);

        TextField tcor2 = new TextField();
        tcor2.setStyle("-fx-text-fill: white;");
        tcor2.setBorder(Border.EMPTY);
        tcor2.setBackground(Background.EMPTY);
        tcor2.setFont(Font.font("Rockwell", 16));
        tcor2.setEditable(false);
        tcor2.setMinSize(180, 30);
        tcor2.setText(" Runners dead: " + runnersDead());
        v5.getChildren().add(tcor2);

        TextField ph = new TextField();
        ph.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 20; -fx-font-family:Rockwell");
        ph.setBorder(Border.EMPTY);
        ph.setBackground(Background.EMPTY);
        ph.setEditable(false);
        ph.setMinSize(180, 20);
        ph.setText("    %H:        " + pH() + "%");
        v4.getChildren().add(ph);

        TextField pz = new TextField();
        pz.setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 20; -fx-font-family:Rockwell");
        pz.setBorder(Border.EMPTY);
        pz.setBackground(Background.EMPTY);
        pz.setEditable(false);
        pz.setMinSize(180, 20);
        pz.setText(" %Z:        " + pZ() + "%");
        v5.getChildren().add(pz);

        buttons.get("BACK").setOnAction(e -> primaryStage.close());
        buttons.get("SAVE").setOnAction(e -> geraTxtSave());
        buttons.get("RESET")
                .setOnAction(e -> setRefresh(characters, cells, tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1,
                        tz2, tcor1, tcor2, pz, ph));
        buttons.get("NEXT STEP")
                .setOnAction(e -> avancaSimulacao(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2, tcor1,
                        tcor2, pz, ph));
        buttons.get("NEXT 10")
                .setOnAction(e -> avancaSimulacao10(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2,
                        tcor1, tcor2, pz, ph));
        buttons.get("NEXT 100")
                .setOnAction(e -> avancaSimulacao100(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2,
                        tcor1, tcor2, pz, ph));

        Scene scene = new Scene(vb, 1300, 750);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public String dumbsAlive() {
        long dumbAlive = characters.stream()
                .filter(p -> (p instanceof Dumb))
                .filter(p -> p.isAlive())
                .count();
        return dumbAlive + "";
    }

    public String dumbsInf() {
        long dumbInf = characters.stream()
                .filter(p -> (p instanceof Dumb))
                .filter(p -> p.isAlive())
                .filter(p -> p.isInfected())
                .count();
        return dumbInf + "";
    }

    public String dumbsDead() {
        long dumbsDead = characters.stream()
                .filter(p -> (p instanceof Dumb))
                .filter(p -> !(p.isAlive()))
                .count();
        return dumbsDead + "";
    }

    public String healersAlive() {
        long healersAlive = characters.stream()
                .filter(p -> (p instanceof Healer))
                .filter(p -> p.isAlive())
                .count();
        return healersAlive + "";
    }

    public String healersInf() {
        long healersInf = characters.stream()
                .filter(p -> (p instanceof Healer))
                .filter(p -> p.isAlive())
                .filter(p -> p.isInfected())
                .count();
        return healersInf + "";
    }

    public String healersDead() {
        long healersDead = characters.stream()
                .filter(p -> (p instanceof Healer))
                .filter(p -> !(p.isAlive()))
                .count();
        return healersDead + "";
    }

    public String soldiersAlive() {
        long soldiersAlive = characters.stream()
                .filter(p -> (p instanceof Soldier))
                .filter(p -> p.isAlive())
                .count();
        return soldiersAlive + "";
    }

    public String soldiersInf() {
        long soldiersInf = characters.stream()
                .filter(p -> (p instanceof Soldier))
                .filter(p -> p.isAlive())
                .filter(p -> p.isInfected())
                .count();
        return soldiersInf + "";
    }

    public String soldiersDead() {
        long soldiersDead = characters.stream()
                .filter(p -> (p instanceof Soldier))
                .filter(p -> !(p.isAlive()))
                .count();
        return soldiersDead + "";
    }

    public String zombiesAlive() {
        long zombiesAlive = characters.stream()
                .filter(p -> (p instanceof Zombie))
                .filter(p -> p.isAlive())
                .count();
        return zombiesAlive + "";
    }

    public String zombiesDead() {
        long zombiesDead = characters.stream()
                .filter(p -> (p instanceof Zombie))
                .filter(p -> !(p.isAlive()))
                .count();
        return zombiesDead + "";
    }

    public String runnersAlive() {
        long runnersAlive = characters.stream()
                .filter(p -> (p instanceof Runner))
                .filter(p -> p.isAlive())
                .count();
        return runnersAlive + "";
    }

    public String runnersDead() {
        long runnersDead = characters.stream()
                .filter(p -> (p instanceof Runner))
                .filter(p -> !(p.isAlive()))
                .count();
        return runnersDead + "";
    }

    public String pZ() {
        long pZ = characters.stream()
                .filter(p -> !(p instanceof Dumb))
                .filter(p -> !(p instanceof Healer))
                .filter(p -> !(p instanceof Soldier))
                .filter(p -> p.isAlive())
                .count();
        long result = 100 * pZ / (RUNNERAMOUNT + ZOMBIEAMOUNT);
        return result + "";
    }

    public String pH() {
        long pH = characters.stream()
                .filter(p -> !(p instanceof Zombie))
                .filter(p -> !(p instanceof Runner))
                .filter(p -> p.isAlive())
                .count();
        long result = 100 * pH / (DUMBAMOUNT + HEALERAMOUNT + SOLDIERAMOUNT);
        return result + "";
    }

    public void avancaSimulacao(TextField tb1, TextField tb2, TextField tb3, TextField tcur1, TextField tcur2,
            TextField tcur3, TextField ts1, TextField ts2, TextField ts3, TextField tz1, TextField tz2, TextField tcor1,
            TextField tcor2, TextField pz, TextField ph) {
        characters.forEach(p -> {
            p.nextPos();
            p.stateStatus();
            p.action();
        });

        tb1.setText("       Dumbs alive: " + dumbsAlive());
        tb2.setText("       Dumbs infected: " + dumbsInf());
        tb3.setText("       Dumbs dead: " + dumbsDead());
        tcur1.setText("    Healers alive: " + healersAlive());
        tcur2.setText("    Healers infected: " + healersInf());
        tcur3.setText("    Healers dead: " + healersDead());
        ts1.setText("    Soldiers alive: " + soldiersAlive());
        ts2.setText("    Sodiers infected: " + soldiersInf());
        ts3.setText("    Soldiers dead: " + soldiersDead());
        tz1.setText("    Zombies alive: " + zombiesAlive());
        tz2.setText("    Zombies dead: " + zombiesDead());
        tcor1.setText(" Runners alive: " + runnersAlive());
        tcor2.setText(" Runners dead: " + runnersDead());
        pz.setText(" %Z:        " + pZ() + "%");
        ph.setText("    %H:        " + pH() + "%");

        if (pH().equalsIgnoreCase("0" + "")) {
            Alert msgBox = new Alert(AlertType.INFORMATION);
            msgBox.setHeaderText("Game Over");
            msgBox.setContentText("All humans are dead!\nMonster's victory!");
            msgBox.showAndWait();
            System.exit(0);
        }

        if (pZ().equalsIgnoreCase("0" + "")) {
            Alert msgBox = new Alert(AlertType.INFORMATION);
            msgBox.setHeaderText("Congratulations");
            msgBox.setContentText("All monsters are dead!\nHuman's victory!");
            msgBox.showAndWait();
            System.exit(0);
        }
    }

    public void avancaSimulacao10(TextField tb1, TextField tb2, TextField tb3, TextField tcur1, TextField tcur2,
            TextField tcur3, TextField ts1, TextField ts2, TextField ts3, TextField tz1, TextField tz2, TextField tcor1,
            TextField tcor2, TextField pz, TextField ph) {
        avancaSimulacao(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2, tcor1, tcor2, pz, ph);
        avancaSimulacao(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2, tcor1, tcor2, pz, ph);
        avancaSimulacao(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2, tcor1, tcor2, pz, ph);
        avancaSimulacao(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2, tcor1, tcor2, pz, ph);
        avancaSimulacao(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2, tcor1, tcor2, pz, ph);
        avancaSimulacao(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2, tcor1, tcor2, pz, ph);
        avancaSimulacao(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2, tcor1, tcor2, pz, ph);
        avancaSimulacao(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2, tcor1, tcor2, pz, ph);
        avancaSimulacao(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2, tcor1, tcor2, pz, ph);
        avancaSimulacao(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2, tcor1, tcor2, pz, ph);
    }

    public void avancaSimulacao100(TextField tb1, TextField tb2, TextField tb3, TextField tcur1, TextField tcur2,
            TextField tcur3, TextField ts1, TextField ts2, TextField ts3, TextField tz1, TextField tz2, TextField tcor1,
            TextField tcor2, TextField pz, TextField ph) {
        avancaSimulacao10(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2, tcor1, tcor2, pz, ph);
        avancaSimulacao10(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2, tcor1, tcor2, pz, ph);
        avancaSimulacao10(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2, tcor1, tcor2, pz, ph);
        avancaSimulacao10(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2, tcor1, tcor2, pz, ph);
        avancaSimulacao10(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2, tcor1, tcor2, pz, ph);
        avancaSimulacao10(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2, tcor1, tcor2, pz, ph);
        avancaSimulacao10(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2, tcor1, tcor2, pz, ph);
        avancaSimulacao10(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2, tcor1, tcor2, pz, ph);
        avancaSimulacao10(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2, tcor1, tcor2, pz, ph);
        avancaSimulacao10(tb1, tb2, tb3, tcur1, tcur2, tcur3, ts1, ts2, ts3, tz1, tz2, tcor1, tcor2, pz, ph);
    }

    public boolean geraTxtSave() {
        FileWriter writer;
        try {
            writer = new FileWriter(".SAVE.txt");
            StringBuilder aux = new StringBuilder("\n");
            int separate = 0;
            for (Cell cel : cells) {
                if (separate != 0 && separate % 16 == 0) {
                    aux.append("\n");
                }

                if (cel.getCharacter() == null) {
                    aux.append("GG" + " ");
                } else {
                    aux.append(cel.getCharacter().getId() + " ");
                }
                separate++;
            }
            writer.append(aux);
            writer.flush();
            writer.close();
        } catch (IOException x) {
            System.err.format("I/O error: %s%n", x);
        }
        return true;
    }

    public boolean readSave(List<Character> characters, List<Cell> cells) {
        Path path = Paths.get(".SAVE.txt");
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("utf8"))) {
            String line = null;
            line = reader.readLine();
            int lin = 0;
            while ((line = reader.readLine()) != null) {
                int col = 0;
                String[] data = line.split(" ");
                setCharacter(data[0], lin, col, characters);
                col++;
                setCharacter(data[1], lin, col, characters);
                col++;
                setCharacter(data[2], lin, col, characters);
                col++;
                setCharacter(data[3], lin, col, characters);
                col++;
                setCharacter(data[4], lin, col, characters);
                col++;
                setCharacter(data[5], lin, col, characters);
                col++;
                setCharacter(data[6], lin, col, characters);
                col++;
                setCharacter(data[7], lin, col, characters);
                col++;
                setCharacter(data[8], lin, col, characters);
                col++;
                setCharacter(data[9], lin, col, characters);
                col++;
                setCharacter(data[10], lin, col, characters);
                col++;
                setCharacter(data[11], lin, col, characters);
                col++;
                setCharacter(data[12], lin, col, characters);
                col++;
                setCharacter(data[13], lin, col, characters);
                col++;
                setCharacter(data[14], lin, col, characters);
                col++;
                setCharacter(data[15], lin, col, characters);
                col++;
                lin++;
            }
        } catch (IOException x) {
            System.err.format("I/O error: %s%n", x);
        }
        return true;
    }

    public void setCharacter(String data, int lin, int col, List characters) {
        if (data.equals("BV")) {
            characters.add(new Dumb(lin, col, 2, "BV"));
        }
        if (data.equals("BM")) {
            Dumb aux = new Dumb(lin, col, 0, "BM", "Dead");
            aux.kill();
            aux.setImage("Dead");
            characters.add(aux);
        }
        if (data.equals("CV")) {
            characters.add(new Healer(lin, col, "CV"));
        }
        if (data.equals("CM")) {
            Healer aux = new Healer(lin, col, "CM", "Dead");
            aux.kill();
            aux.setImage("Dead");
            characters.add(aux);
        }
        if (data.equals("SV")) {
            characters.add(new Soldier(lin, col, "SV"));
        }
        if (data.equals("SM")) {
            Soldier aux = new Soldier(lin, col, "SM", "Dead");
            aux.kill();
            aux.setImage("Dead");
            characters.add(aux);
        }
        if (data.equals("ZV")) {
            characters.add(new Zombie(lin, col, "ZV"));
        }
        if (data.equals("ZM")) {
            Zombie aux = new Zombie(lin, col, "ZM", "Dead");
            aux.kill();
            aux.setImage("Dead");
            characters.add(aux);
        }
        if (data.equals("RV")) {
            characters.add(new Runner(lin, col, "RV"));
        }
        if (data.equals("RM")) {
            Runner aux = new Runner(lin, col, "RM", "Dead");
            aux.kill();
            aux.setImage("Dead");
            characters.add(aux);
        }
        if (data.equals("GG")) {
        }
        ;
    }

    public void fixAmount() {
        DUMBAMOUNT = 0;
        HEALERAMOUNT = 0;
        SOLDIERAMOUNT = 0;
        ZOMBIEAMOUNT = 0;
        RUNNERAMOUNT = 0;
        for (Character p : characters) {
            if (p.getEnume() == Enume.DUMB) {
                DUMBAMOUNT++;
            }
            if (p.getEnume() == Enume.HEALER) {
                HEALERAMOUNT++;
            }
            if (p.getEnume() == Enume.SOLDIER) {
                SOLDIERAMOUNT++;
            }
            if (p.getEnume() == Enume.ZOMBIE) {
                ZOMBIEAMOUNT++;
            }
            if (p.getEnume() == Enume.RUNNER) {
                RUNNERAMOUNT++;
            }
        }
    }

    public boolean crateRefresh() {
        FileWriter writer;
        try {
            writer = new FileWriter(".RESET.txt");
            StringBuilder aux = new StringBuilder("\n");
            int separate = 0;
            for (Cell cel : cells) {
                if (separate != 0 && separate % 16 == 0) {
                    aux.append("\n");
                }

                if (cel.getCharacter() == null) {
                    aux.append("GG" + " ");
                } else {
                    aux.append(cel.getCharacter().getId() + " ");
                }
                separate++;
            }
            writer.append(aux);
            writer.flush();
            writer.close();
        } catch (IOException x) {
            System.err.format("I/O error: %s%n", x);
        }
        return true;
    }

    public boolean setRefresh(List<Character> characters, List<Cell> cells, TextField tb1, TextField tb2, TextField tb3,
            TextField tcur1, TextField tcur2, TextField tcur3, TextField ts1, TextField ts2, TextField ts3,
            TextField tz1, TextField tz2, TextField tcor1, TextField tcor2, TextField pz, TextField ph) {
        characters.clear();
        for (Cell c : cells) {
            c.setCharacter(null);
        }

        Path path = Paths.get(".RESET.txt");
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("utf8"))) {
            String line = null;
            line = reader.readLine();
            int lin = 0;
            while ((line = reader.readLine()) != null) {
                int col = 0;
                String[] dados = line.split(" ");
                setCharacter(dados[0], lin, col, characters);
                col++;
                setCharacter(dados[1], lin, col, characters);
                col++;
                setCharacter(dados[2], lin, col, characters);
                col++;
                setCharacter(dados[3], lin, col, characters);
                col++;
                setCharacter(dados[4], lin, col, characters);
                col++;
                setCharacter(dados[5], lin, col, characters);
                col++;
                setCharacter(dados[6], lin, col, characters);
                col++;
                setCharacter(dados[7], lin, col, characters);
                col++;
                setCharacter(dados[8], lin, col, characters);
                col++;
                setCharacter(dados[9], lin, col, characters);
                col++;
                setCharacter(dados[10], lin, col, characters);
                col++;
                setCharacter(dados[11], lin, col, characters);
                col++;
                setCharacter(dados[12], lin, col, characters);
                col++;
                setCharacter(dados[13], lin, col, characters);
                col++;
                setCharacter(dados[14], lin, col, characters);
                col++;
                setCharacter(dados[15], lin, col, characters);
                col++;
                lin++;

                tb1.setText("       Dumbs alive: " + dumbsAlive());
                tb2.setText("       Dumbs infected: " + dumbsInf());
                tb3.setText("       Dumbs dead: " + dumbsDead());
                tcur1.setText("    Healers alive: " + healersAlive());
                tcur2.setText("    Healers infected: " + healersInf());
                tcur3.setText("    Healers dead: " + healersDead());
                ts1.setText("    Soldiers alive: " + soldiersAlive());
                ts2.setText("    Soldiers infected: " + soldiersInf());
                ts3.setText("    Soldiers dead: " + soldiersDead());
                tz1.setText("    Zombies alive: " + zombiesAlive());
                tz2.setText("    Zombies dead: " + zombiesDead());
                tcor1.setText(" Runners alive: " + runnersAlive());
                tcor2.setText(" Runners dead: " + runnersDead());
                pz.setText(" %Z:        " + pZ() + "%");
                ph.setText("    %H:        " + pH() + "%");
            }
        } catch (IOException x) {
            System.err.format("I/O error: %s%n", x);
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}