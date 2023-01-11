package poo;

import java.io.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class Menu extends Application {

    public static final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent; -fx-text-fill: white;";
    public static final String HOVERED_BUTTON_STYLE = "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";
    public static final String TEXT_PATTERN_1 = "-fx-text-fill: white; -fx-background-color: transparent; -fx-border-color: transparent; -fx-font-size: 16; -fx-font-family:Rockwell;";
    public static final String TEXT_PATTERN_2 = "-fx-text-fill: yellow; -fx-font-weight: bold; -fx-background-color: transparent; -fx-border-color: transparent;";

    public void start(Stage stage) {
        GridPane grid = new GridPane();
        GridPane bgrd = new GridPane();
        BackgroundImage bgi = new BackgroundImage(new Image("file:Images/background.jpg", 550, 770, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        BackgroundImage bgi2 = new BackgroundImage(new Image("file:Images/brown.jpg", 1800, 1000, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        bgrd.setBackground(new Background(bgi2));
        bgrd.getChildren().add(grid);
        bgrd.setAlignment(Pos.CENTER);

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(5);
        grid.setAlignment(Pos.CENTER);
        grid.setMinHeight(770);
        grid.setMinWidth(550);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setBackground(new Background(bgi));

        final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent;";
        final String HOVERED_BUTTON_STYLE = "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";

        Button btn1 = new Button();
        btn1.setText("NEW GAME");
        btn1.setFont(Font.font("Rockwell", 16));
        grid.add(btn1, 7, 110);
        btn1.setCursor(Cursor.HAND);
        btn1.getCursor();
        btn1.setStyle(IDLE_BUTTON_STYLE);
        btn1.setOnMouseEntered(e -> btn1.setStyle(HOVERED_BUTTON_STYLE));
        btn1.setOnMouseExited(e -> btn1.setStyle(IDLE_BUTTON_STYLE));

        Button btn2 = new Button();
        btn2.setText("CONTINUE");
        btn2.setFont(Font.font("Rockwell", 16));
        grid.add(btn2, 7, 113);
        btn2.setCursor(Cursor.HAND);
        btn2.getCursor();
        btn2.setStyle(IDLE_BUTTON_STYLE);
        btn2.setOnMouseEntered(e -> btn2.setStyle(HOVERED_BUTTON_STYLE));
        btn2.setOnMouseExited(e -> btn2.setStyle(IDLE_BUTTON_STYLE));

        Button btn3 = new Button();
        btn3.setText("HISTORY");
        btn3.setFont(Font.font("Rockwell", 16));
        grid.add(btn3, 7, 116);
        btn3.setCursor(Cursor.HAND);
        btn3.getCursor();
        btn3.setStyle(IDLE_BUTTON_STYLE);
        btn3.setOnMouseEntered(e -> btn3.setStyle(HOVERED_BUTTON_STYLE));
        btn3.setOnMouseExited(e -> btn3.setStyle(IDLE_BUTTON_STYLE));

        Button btn4 = new Button();
        btn4.setText("SETTINGS");
        btn4.setFont(Font.font("Rockwell", 16));
        grid.add(btn4, 7, 119);
        btn4.setCursor(Cursor.HAND);
        btn4.getCursor();
        btn4.setStyle(IDLE_BUTTON_STYLE);
        btn4.setOnMouseEntered(e -> btn4.setStyle(HOVERED_BUTTON_STYLE));
        btn4.setOnMouseExited(e -> btn4.setStyle(IDLE_BUTTON_STYLE));

        Button btn5 = new Button();
        btn5.setText("QUIT");
        btn5.setFont(Font.font("Rockwell", 16));
        grid.add(btn5, 7, 121);
        btn5.setCursor(Cursor.HAND);
        btn5.getCursor();
        btn5.setStyle(IDLE_BUTTON_STYLE);
        btn5.setOnMouseEntered(e -> btn5.setStyle(HOVERED_BUTTON_STYLE));
        btn5.setOnMouseExited(e -> btn5.setStyle(IDLE_BUTTON_STYLE));
        geraTxtQtd();

        btn1.setOnAction((event) -> {
            Game game = new Game(false);
            game.start(new Stage());

        });

        btn2.setOnAction((event) -> {
            Game continueGame = new Game(true);
            continueGame.start(new Stage());
        });

        btn3.setOnAction((event) -> {
            History h = new History();
            h.start(new Stage());
        });

        btn4.setOnAction((event) -> {
            Settings c = new Settings();
            c.start(new Stage());
        });

        btn5.setOnAction((event) -> {
            Platform.exit();
            System.exit(0);
        });

        Scene scene = new Scene(bgrd, bgi.getImage().getWidth(), bgi.getImage().getHeight());

        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("MENU - Zombie Attack Simulation");
        stage.setScene(scene);
        stage.show();
        scene.setCursor(Cursor.DEFAULT);
        scene.getCursor();
    }

    public boolean geraTxtQtd() {
        FileWriter writer;
        try {
            writer = new FileWriter(".AMOUNTS.txt");
            writer.append("\n");
            writer.append("DUMBAMOUNT" + " " + "8\n");
            writer.append("HEALERAMOUNT" + " " + "4\n");
            writer.append("SOLDIERAMOUNT" + " " + "5\n");
            writer.append("ZOMBIEAMOUNT" + " " + "5\n");
            writer.append("RUNNERAMOUNT" + " " + "4");
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