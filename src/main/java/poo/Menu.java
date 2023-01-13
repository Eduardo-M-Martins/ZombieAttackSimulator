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
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        BackgroundImage bgi2 = new BackgroundImage(new Image("file:Images/brown.jpg", 1800, 1000, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

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
        
        Button newGame = new Button();
        newGame.setText("NEW GAME");
        newGame.setFont(Font.font("Rockwell", 16));
        grid.add(newGame, 7, 110);
        newGame.setCursor(Cursor.HAND);
        newGame.getCursor();
        newGame.setStyle(IDLE_BUTTON_STYLE);
        newGame.setOnMouseEntered(e -> newGame.setStyle(HOVERED_BUTTON_STYLE));
        newGame.setOnMouseExited(e -> newGame.setStyle(IDLE_BUTTON_STYLE));

        Button continueOption = new Button();
        continueOption.setText("CONTINUE");
        continueOption.setFont(Font.font("Rockwell", 16));
        grid.add(continueOption, 7, 113);
        continueOption.setCursor(Cursor.HAND);
        continueOption.getCursor();
        continueOption.setStyle(IDLE_BUTTON_STYLE);
        continueOption.setOnMouseEntered(e -> continueOption.setStyle(HOVERED_BUTTON_STYLE));
        continueOption.setOnMouseExited(e -> continueOption.setStyle(IDLE_BUTTON_STYLE));

        Button history = new Button();
        history.setText("HISTORY");
        history.setFont(Font.font("Rockwell", 16));
        grid.add(history, 7, 116);
        history.setCursor(Cursor.HAND);
        history.getCursor();
        history.setStyle(IDLE_BUTTON_STYLE);
        history.setOnMouseEntered(e -> history.setStyle(HOVERED_BUTTON_STYLE));
        history.setOnMouseExited(e -> history.setStyle(IDLE_BUTTON_STYLE));

        Button settings = new Button();
        settings.setText("SETTINGS");
        settings.setFont(Font.font("Rockwell", 16));
        grid.add(settings, 7, 119);
        settings.setCursor(Cursor.HAND);
        settings.getCursor();
        settings.setStyle(IDLE_BUTTON_STYLE);
        settings.setOnMouseEntered(e -> settings.setStyle(HOVERED_BUTTON_STYLE));
        settings.setOnMouseExited(e -> settings.setStyle(IDLE_BUTTON_STYLE));

        Button quit = new Button();
        quit.setText("QUIT");
        quit.setFont(Font.font("Rockwell", 16));
        grid.add(quit, 7, 121);
        quit.setCursor(Cursor.HAND);
        quit.getCursor();
        quit.setStyle(IDLE_BUTTON_STYLE);
        quit.setOnMouseEntered(e -> quit.setStyle(HOVERED_BUTTON_STYLE));
        quit.setOnMouseExited(e -> quit.setStyle(IDLE_BUTTON_STYLE));
        createAmounts();

        newGame.setOnAction((event) -> {
            Game game = new Game(false);
            game.start(new Stage());
        });
        continueOption.setOnAction((event) -> {
            Game continueGame = new Game(true);
            continueGame.start(new Stage());
        });
        history.setOnAction((event) -> {
            History h = new History();
            h.start(new Stage());
        });
        settings.setOnAction((event) -> {
            Settings c = new Settings();
            c.start(new Stage());
        });
        quit.setOnAction((event) -> {
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

    public boolean createAmounts() {
        try {
            FileWriter writer = new FileWriter(".AMOUNTS.txt");
            writer.append("\nDUMBAMOUNT 8\n");
            writer.append("HEALERAMOUNT 4\n");
            writer.append("SOLDIERAMOUNT 5\n");
            writer.append("ZOMBIEAMOUNT 5\n");
            writer.append("RUNNERAMOUNT 4");
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