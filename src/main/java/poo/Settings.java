package poo;

import java.io.*;
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
    public static String DUMBAMOUNT = "8";
    public static String ZOMBIEAMOUNT = "5";
    public static String RUNNERAMOUNT = "4";
    public static String HEALERAMOUNT = "4";
    public static String SOLDIERAMOUNT = "5";

    public void start(Stage stage) {
        BackgroundImage bgi = new BackgroundImage(new Image("file:Images/brown.jpg", 1800, 1000, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Button btn1 = new Button();
        btn1.setAlignment(Pos.BOTTOM_CENTER);
        btn1.setText("BACK");
        btn1.setFont(Font.font("Rockwell", 14));
        btn1.setCursor(Cursor.HAND);
        btn1.getCursor();
        btn1.setStyle(Menu.IDLE_BUTTON_STYLE);
        btn1.setOnMouseEntered(e -> btn1.setStyle(Menu.HOVERED_BUTTON_STYLE));
        btn1.setOnMouseExited(e -> btn1.setStyle(Menu.IDLE_BUTTON_STYLE));

        btn1.setOnAction((event) -> {
            stage.close();
        });

        Button t = new Button();
        t.setText("S E T T I N G S");
        t.setBackground(Background.EMPTY);
        t.setBorder(Border.EMPTY);
        t.setFont(Font.font("Rockwell", 40));
        t.setAlignment(Pos.TOP_CENTER);
        t.setStyle(Menu.IDLE_BUTTON_STYLE);

        GridPane grid = new GridPane();
        grid.setBackground(new Background(bgi));
        grid.setMinSize(500, 45);
        grid.setAlignment(Pos.TOP_CENTER);
        grid.getChildren().add(t);

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

        TextField tx1 = new TextField();
        tx1.setStyle("-fx-text-fill: white;");
        tx1.setBorder(Border.EMPTY);
        tx1.setBackground(Background.EMPTY);
        tx1.setFont(Font.font("Rockwell", 16));
        tx1.setMinWidth(230);
        tx1.setEditable(false);
        tx1.setAlignment(Pos.CENTER);
        tx1.setText("Amount of Dumbs: ");

        TextField tx11 = new TextField();
        tx11.setMaxWidth(45);
        tx11.setFont(Font.font("Rockwell", 16));
        tx11.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    try {
                        long pointI = Integer.parseInt(newValue);
                        tx11.setText(String.valueOf(pointI));
                    } catch (Exception e) {
                        tx11.clear();
                        tx11.setText(getNumber(oldValue));
                    }
                }
            }
        });
        tx11.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,2}([\\.]\\d{0,0})?")) {
                    tx11.setText(oldValue);
                }
            }
        });
        tx11.setText(DUMBAMOUNT);

        Button s2 = new Button("-50 pts");
        s2.setBorder(Border.EMPTY);
        s2.setBackground(Background.EMPTY);
        s2.setFont(Font.font("Rockwell", 22));
        s2.setStyle("-fx-text-fill: red; -fx-font-weight: bold;-fx-font-size: 24; -fx-font-family:Rockwell");
        s2.setOnMouseEntered(e -> s2
                .setStyle("-fx-text-fill: red; -fx-font-weight: bold;-fx-font-size: 24; -fx-font-family:Rockwell"));
        s2.setOnMouseExited(e -> s2
                .setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 24; -fx-font-family:Rockwell"));

        HBox h1 = new HBox();
        h1.setBackground(new Background(bgi));
        h1.setAlignment(Pos.CENTER_LEFT);
        h1.getChildren().add(s11);
        h1.getChildren().add(new ImageView(new Image("file:Images/dumb.jpeg", 120, 120, false, true)));
        h1.getChildren().add(tx1);
        h1.getChildren().add(tx11);
        h1.getChildren().add(s2);

        TextField tx2 = new TextField();
        tx2.setStyle("-fx-text-fill: white;");
        tx2.setBorder(Border.EMPTY);
        tx2.setBackground(Background.EMPTY);
        tx2.setFont(Font.font("Rockwell", 16));
        tx2.setMinWidth(230);
        tx2.setEditable(false);
        tx2.setAlignment(Pos.CENTER);
        tx2.setText("Amount of Healers: ");

        TextField tx21 = new TextField();
        tx21.setMaxWidth(45);
        tx21.setFont(Font.font("Rockwell", 16));
        tx21.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    try {
                        long pointI = Integer.parseInt(newValue);
                        tx21.setText(String.valueOf(pointI));
                    } catch (Exception e) {
                        tx21.clear();
                        tx21.setText(getNumber(oldValue));
                    }
                }
            }
        });
        tx21.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,2}([\\.]\\d{0,0})?")) {
                    tx21.setText(oldValue);
                }
            }
        });
        tx21.setText(HEALERAMOUNT);

        Button s3 = new Button("-75 pts");
        s3.setBorder(Border.EMPTY);
        s3.setBackground(Background.EMPTY);
        s3.setFont(Font.font("Rockwell", 22));
        s3.setStyle("-fx-text-fill: red; -fx-font-weight: bold;-fx-font-size: 24; -fx-font-family:Rockwell");
        s3.setOnMouseEntered(e -> s3
                .setStyle("-fx-text-fill: red; -fx-font-weight: bold;-fx-font-size: 24; -fx-font-family:Rockwell"));
        s3.setOnMouseExited(e -> s3
                .setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 24; -fx-font-family:Rockwell"));

        HBox h2 = new HBox();
        h2.setBackground(new Background(bgi));
        h2.setAlignment(Pos.CENTER_LEFT);
        h2.getChildren().add(s21);
        h2.getChildren().add(new ImageView(new Image("file:Images/healer.jpeg", 120, 120, false, true)));
        h2.getChildren().add(tx2);
        h2.getChildren().add(tx21);
        h2.getChildren().add(s3);

        TextField tx3 = new TextField();
        tx3.setStyle("-fx-text-fill: white;");
        tx3.setBorder(Border.EMPTY);
        tx3.setBackground(Background.EMPTY);
        tx3.setFont(Font.font("Rockwell", 16));
        tx3.setMinWidth(230);
        tx3.setEditable(false);
        tx3.setAlignment(Pos.CENTER);
        tx3.setText("Amount of Soldiers: ");

        TextField tx31 = new TextField();
        tx31.setMaxWidth(45);
        tx31.setFont(Font.font("Rockwell", 16));
        tx31.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    try {
                        long pointI = Integer.parseInt(newValue);
                        tx31.setText(String.valueOf(pointI));
                    } catch (Exception e) {
                        tx31.clear();
                        tx31.setText(getNumber(oldValue));
                    }
                }
            }
        });
        tx31.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,2}([\\.]\\d{0,0})?")) {
                    tx31.setText(oldValue);
                }
            }
        });
        tx31.setText(SOLDIERAMOUNT);

        Button s4 = new Button("-100 pts");
        s4.setBorder(Border.EMPTY);
        s4.setBackground(Background.EMPTY);
        s4.setFont(Font.font("Rockwell", 22));
        s4.setStyle("-fx-text-fill: red; -fx-font-weight: bold;-fx-font-size: 24; -fx-font-family:Rockwell");
        s4.setOnMouseEntered(e -> s4
                .setStyle("-fx-text-fill: red; -fx-font-weight: bold;-fx-font-size: 24; -fx-font-family:Rockwell"));
        s4.setOnMouseExited(e -> s4
                .setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 24; -fx-font-family:Rockwell"));

        HBox h3 = new HBox();
        h3.setBackground(new Background(bgi));
        h3.setAlignment(Pos.CENTER_LEFT);
        h3.getChildren().add(s31);
        h3.getChildren().add(new ImageView(new Image("file:Images/soldier.jpeg", 120, 120, false, true)));
        h3.getChildren().add(tx3);
        h3.getChildren().add(tx31);
        h3.getChildren().add(s4);

        TextField tx4 = new TextField();
        tx4.setStyle("-fx-text-fill: white;");
        tx4.setBorder(Border.EMPTY);
        tx4.setBackground(Background.EMPTY);
        tx4.setFont(Font.font("Rockwell", 16));
        tx4.setMinWidth(230);
        tx4.setEditable(false);
        tx4.setAlignment(Pos.CENTER);
        tx4.setText("Amount of Zombies: ");

        TextField tx41 = new TextField();
        tx41.setMaxWidth(45);
        tx41.setFont(Font.font("Rockwell", 16));
        tx41.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    try {
                        long pointI = Integer.parseInt(newValue);
                        tx41.setText(String.valueOf(pointI));
                    } catch (Exception e) {
                        tx41.clear();
                        tx41.setText(getNumber(oldValue));
                    }
                }
            }
        });
        tx41.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,2}([\\.]\\d{0,0})?")) {
                    tx41.setText(oldValue);
                }
            }
        });
        tx41.setText(ZOMBIEAMOUNT);

        Button s5 = new Button("+200 pts");
        s5.setBorder(Border.EMPTY);
        s5.setBackground(Background.EMPTY);
        s5.setFont(Font.font("Rockwell", 22));
        s5.setStyle("-fx-text-fill: green; -fx-font-weight: bold;-fx-font-size: 24; -fx-font-family:Rockwell");
        s5.setOnMouseEntered(e -> s5
                .setStyle("-fx-text-fill: green; -fx-font-weight: bold;-fx-font-size: 24; -fx-font-family:Rockwell"));
        s5.setOnMouseExited(e -> s5
                .setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 24; -fx-font-family:Rockwell"));

        HBox h4 = new HBox();
        h4.setBackground(new Background(bgi));
        h4.setAlignment(Pos.CENTER_LEFT);
        h4.getChildren().add(s41);
        h4.getChildren().add(new ImageView(new Image("file:Images/zombie.jpeg", 120, 120, false, true)));
        h4.getChildren().add(tx4);
        h4.getChildren().add(tx41);
        h4.getChildren().add(s5);

        TextField tx5 = new TextField();
        tx5.setStyle("-fx-text-fill: white;");
        tx5.setBorder(Border.EMPTY);
        tx5.setBackground(Background.EMPTY);
        tx5.setFont(Font.font("Rockwell", 16));
        tx5.setMinWidth(230);
        tx5.setEditable(false);
        tx5.setAlignment(Pos.CENTER);
        tx5.setText("Amount of Runners: ");

        TextField tx51 = new TextField();
        tx51.setMaxWidth(45);
        tx51.setFont(Font.font("Rockwell", 16));
        tx51.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    try {
                        long pointI = Integer.parseInt(newValue);
                        tx51.setText(String.valueOf(pointI));
                    } catch (Exception e) {
                        tx51.clear();
                        tx51.setText(getNumber(oldValue));
                    }
                }
            }
        });
        tx51.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,2}([\\.]\\d{0,0})?")) {
                    tx51.setText(oldValue);
                }
            }
        });
        tx51.setText(RUNNERAMOUNT);

        Button s6 = new Button("+300 pts");
        s6.setBorder(Border.EMPTY);
        s6.setBackground(Background.EMPTY);
        s6.setFont(Font.font("Rockwell", 22));
        s6.setStyle("-fx-text-fill: green; -fx-font-weight: bold;-fx-font-size: 24; -fx-font-family:Rockwell");
        s6.setOnMouseEntered(e -> s6
                .setStyle("-fx-text-fill: green; -fx-font-weight: bold;-fx-font-size: 24; -fx-font-family:Rockwell"));
        s6.setOnMouseExited(e -> s6
                .setStyle("-fx-text-fill: green; -fx-font-weight: bold; -fx-font-size: 24; -fx-font-family:Rockwell"));

        HBox h5 = new HBox();
        h5.setBackground(new Background(bgi));
        h5.setAlignment(Pos.CENTER_LEFT);
        h5.getChildren().add(s51);
        h5.getChildren().add(new ImageView(new Image("file:Images/runner.jpeg", 120, 120, false, true)));
        h5.getChildren().add(tx5);
        h5.getChildren().add(tx51);
        h5.getChildren().add(s6);

        b1.setOnAction((event) -> {
            tx11.setText(tx11.getText());
            tx21.setText(tx21.getText());
            tx31.setText(tx31.getText());
            tx41.setText(tx41.getText());
            tx51.setText(tx51.getText());
            setB(tx11.getText());
            setC(tx21.getText());
            setS(tx31.getText());
            setZ(tx41.getText());
            setZC(tx51.getText());
            geraCsv();
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
        vb.getChildren().add(btn1);

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

    public void setB(String n) {
        this.DUMBAMOUNT = n;
    }

    public void setC(String n) {
        this.HEALERAMOUNT = n;
    }

    public void setS(String n) {
        this.SOLDIERAMOUNT = n;
    }

    public void setZ(String n) {
        this.ZOMBIEAMOUNT = n;
    }

    public void setZC(String n) {
        this.RUNNERAMOUNT = n;
    }

    public boolean geraCsv() {
        FileWriter writer;
        try {
            writer = new FileWriter(".AMOUNTS.txt");
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