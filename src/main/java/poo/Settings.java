package poo;

import java.io.*;
import java.util.HashMap;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.beans.value.*;
import javafx.scene.image.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.geometry.Pos;

public class Settings extends Application {
    public static final String RED_PATTERN = "-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 22; -fx-font-family:Rockwell; -fx-background-color: transparent; -fx-border-color: transparent;";
    public static final String GREEN_PATTERN = "-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 22; -fx-font-family:Rockwell; -fx-background-color: transparent; -fx-border-color: transparent;";

    public static String DUMBAMOUNT = "8";
    public static String ZOMBIEAMOUNT = "5";
    public static String RUNNERAMOUNT = "4";
    public static String HEALERAMOUNT = "4";
    public static String SOLDIERAMOUNT = "5";

    public void start(Stage stage) {
        BackgroundImage bgi = new BackgroundImage(new Image("file:Images/brown.jpg", 1800, 1000, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Button back = new Button();
        back.setAlignment(Pos.BOTTOM_CENTER);
        back.setText("BACK");
        back.setFont(Font.font("Rockwell", 14));
        back.setCursor(Cursor.HAND);
        back.getCursor();
        back.setStyle(Menu.IDLE_BUTTON_STYLE);
        back.setOnMouseEntered(e -> back.setStyle(Menu.HOVERED_BUTTON_STYLE));
        back.setOnMouseExited(e -> back.setStyle(Menu.IDLE_BUTTON_STYLE));

        back.setOnAction((event) -> {
            stage.close();
        });

        Button title = new Button();
        title.setText("S E T T I N G S");
        title.setAlignment(Pos.TOP_CENTER);
        title.setStyle("-fx-text-fill: white; -fx-font-size: 40; -fx-font-family:Rockwell; -fx-background-color: transparent; -fx-border-color: transparent;");

        GridPane grid = new GridPane();
        grid.setBackground(new Background(bgi));
        grid.setMinSize(500, 45);
        grid.setAlignment(Pos.TOP_CENTER);
        grid.getChildren().add(title);

        Button b1 = new Button();
        b1.setAlignment(Pos.BOTTOM_CENTER);
        b1.setText("CONFIRM");
        b1.setFont(Font.font("Rockwell", 14));
        b1.setCursor(Cursor.HAND);
        b1.getCursor();
        b1.setStyle(Menu.IDLE_BUTTON_STYLE);
        b1.setOnMouseEntered(e -> b1.setStyle(Menu.HOVERED_BUTTON_STYLE));
        b1.setOnMouseExited(e -> b1.setStyle(Menu.IDLE_BUTTON_STYLE));

        Button s1 = new Button("   ");
        s1.setBorder(Border.EMPTY);
        s1.setBackground(Background.EMPTY);

        Button s11 = new Button("   ");
        s11.setBorder(Border.EMPTY);
        s11.setBackground(Background.EMPTY);

        Button s21 = new Button("   ");
        s21.setBorder(Border.EMPTY);
        s21.setBackground(Background.EMPTY);

        Button s31 = new Button("   ");
        s31.setBorder(Border.EMPTY);
        s31.setBackground(Background.EMPTY);

        Button s41 = new Button("   ");
        s41.setBorder(Border.EMPTY);
        s41.setBackground(Background.EMPTY);

        Button s51 = new Button("   ");
        s51.setBorder(Border.EMPTY);
        s51.setBackground(Background.EMPTY);

        HashMap<String, TextField> inputText = new HashMap();
        inputText.put("dumbAmount", new TextField());
        inputText.put("healerAmount", new TextField());
        inputText.put("soldierAmount", new TextField());
        inputText.put("zombieAmount", new TextField());
        inputText.put("runnerAmount", new TextField());

        inputText.forEach((key, value) -> {
            value.setMaxWidth(45);
            value.setFont(Font.font("Rockwell", 16));
            value.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.isEmpty()) {
                        try {
                            long pointI = Integer.parseInt(newValue);
                            value.setText(String.valueOf(pointI));
                        } catch (Exception e) {
                            value.clear();
                            value.setText(getNumber(oldValue));
                        }
                    }
                }
            });
            value.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("\\d{0,2}([\\.]\\d{0,0})?")) {
                        value.setText(oldValue);
                    }
                }
            });
        });

        inputText.get("dumbAmount").setText(DUMBAMOUNT);
        inputText.get("healerAmount").setText(HEALERAMOUNT);
        inputText.get("soldierAmount").setText(SOLDIERAMOUNT);
        inputText.get("zombieAmount").setText(ZOMBIEAMOUNT);
        inputText.get("runnerAmount").setText(RUNNERAMOUNT);

        HashMap<String, TextField> texFields = new HashMap();
        texFields.put("Amount of Dumbs: ", new TextField());
        texFields.put("Amount of Healers: ", new TextField());
        texFields.put("Amount of Soldiers: ", new TextField());
        texFields.put("Amount of Zombies: ", new TextField());
        texFields.put("Amount of Runners: ", new TextField());

        texFields.forEach((key, value) -> {
            value.setStyle("-fx-text-fill: white; -fx-background-color: transparent; -fx-border-color: transparent; -fx-font-size: 16; -fx-font-family:Rockwell;");
            value.setMinWidth(230);
            value.setEditable(false);
            value.setAlignment(Pos.CENTER);
            value.setText(key);
        });

        Button dumbPts = new Button("-50 pts");
        dumbPts.setStyle(RED_PATTERN);
        Button healerPts = new Button("-75 pts");
        healerPts.setStyle(RED_PATTERN);
        Button soldierPts = new Button("-100 pts");
        soldierPts.setStyle(RED_PATTERN);
        Button zombiePts = new Button("+200 pts");
        zombiePts.setStyle(GREEN_PATTERN);
        Button runnerPts = new Button("+300 pts");
        runnerPts.setStyle(GREEN_PATTERN);

        HBox h1 = new HBox();
        h1.setBackground(new Background(bgi));
        h1.setAlignment(Pos.CENTER_LEFT);
        h1.getChildren().add(s11);
        h1.getChildren().add(new ImageView(new Image("file:Images/dumb.jpeg", 120, 120, false, true)));
        h1.getChildren().add(texFields.get("Amount of Dumbs: "));
        h1.getChildren().add(inputText.get("dumbAmount"));
        h1.getChildren().add(dumbPts);

        HBox h2 = new HBox();
        h2.setBackground(new Background(bgi));
        h2.setAlignment(Pos.CENTER_LEFT);
        h2.getChildren().add(s21);
        h2.getChildren().add(new ImageView(new Image("file:Images/healer.jpeg", 120, 120, false, true)));
        h2.getChildren().add(texFields.get("Amount of Healers: "));
        h2.getChildren().add(inputText.get("healerAmount"));
        h2.getChildren().add(healerPts);

        HBox h3 = new HBox();
        h3.setBackground(new Background(bgi));
        h3.setAlignment(Pos.CENTER_LEFT);
        h3.getChildren().add(s31);
        h3.getChildren().add(new ImageView(new Image("file:Images/soldier.jpeg", 120, 120, false, true)));
        h3.getChildren().add(texFields.get("Amount of Soldiers: "));
        h3.getChildren().add(inputText.get("soldierAmount"));
        h3.getChildren().add(soldierPts);

        HBox h4 = new HBox();
        h4.setBackground(new Background(bgi));
        h4.setAlignment(Pos.CENTER_LEFT);
        h4.getChildren().add(s41);
        h4.getChildren().add(new ImageView(new Image("file:Images/zombie.jpeg", 120, 120, false, true)));
        h4.getChildren().add(texFields.get("Amount of Zombies: "));
        h4.getChildren().add(inputText.get("zombieAmount"));
        h4.getChildren().add(zombiePts);

        HBox h5 = new HBox();
        h5.setBackground(new Background(bgi));
        h5.setAlignment(Pos.CENTER_LEFT);
        h5.getChildren().add(s51);
        h5.getChildren().add(new ImageView(new Image("file:Images/runner.jpeg", 120, 120, false, true)));
        h5.getChildren().add(texFields.get("Amount of Runners: "));
        h5.getChildren().add(inputText.get("runnerAmount"));
        h5.getChildren().add(runnerPts);

        b1.setOnAction((event) -> {
            inputText.get("dumbAmount").setText(inputText.get("dumbAmount").getText());
            inputText.get("healerAmount").setText(inputText.get("healerAmount").getText());
            inputText.get("soldierAmount").setText(inputText.get("soldierAmount").getText());
            inputText.get("zombieAmount").setText(inputText.get("zombieAmount").getText());
            inputText.get("runnerAmount").setText(inputText.get("runnerAmount").getText());
            this.DUMBAMOUNT = inputText.get("dumbAmount").getText();
            this.HEALERAMOUNT = inputText.get("healerAmount").getText();
            this.SOLDIERAMOUNT = inputText.get("soldierAmount").getText();
            this.ZOMBIEAMOUNT = inputText.get("zombieAmount").getText();
            this.RUNNERAMOUNT = inputText.get("runnerAmount").getText();
            createAmounts();
        });

        VBox vbc = new VBox();
        vbc.setBackground(new Background(bgi));
        vbc.setAlignment(Pos.TOP_CENTER);
        vbc.getChildren().add(h1);
        vbc.getChildren().add(h2);
        vbc.getChildren().add(h3);
        vbc.getChildren().add(h4);
        vbc.getChildren().add(h5);

        VBox vb = new VBox();
        vb.setBackground(new Background(bgi));
        vb.setAlignment(Pos.TOP_CENTER);
        vb.getChildren().add(grid);
        vb.getChildren().add(vbc);
        vb.getChildren().add(s1);
        vb.getChildren().add(b1);
        vb.getChildren().add(back);

        Scene scene = new Scene(vb, 550, 770);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("SETTINGS - Zombie Attack Simulation");
        stage.setScene(scene);
        stage.show();
        scene.setCursor(Cursor.DEFAULT);
        scene.getCursor();
    }

    private String getNumber(String value) {
        String n = "";
        try {
            return String.valueOf(Integer.parseInt(value));
        } catch (Exception e) {
            String[] array = value.split("");
            for (String tab : array) {
                try {
                    System.out.println(tab);
                    n = n.concat(String.valueOf(Integer.parseInt(String.valueOf(tab))));
                } catch (Exception ex) {
                    System.out.println("not number");
                }
            }
            return n;
        }
    }

    public boolean createAmounts() {
        try {
            FileWriter writer = new FileWriter(".AMOUNTS.txt");
            writer.append("\n");
            writer.append("DUMBAMOUNT" + " " + DUMBAMOUNT + "\n");
            writer.append("HEALERAMOUNT" + " " + HEALERAMOUNT + "\n");
            writer.append("SOLDIERAMOUNT" + " " + SOLDIERAMOUNT + "\n");
            writer.append("ZOMBIEAMOUNT" + " " + ZOMBIEAMOUNT + "\n");
            writer.append("RUNNERAMOUNT" + " " + RUNNERAMOUNT);
            writer.flush();
            writer.close();
        } catch (IOException x) {
            System.err.format("I/O error: %s%n", x);
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}