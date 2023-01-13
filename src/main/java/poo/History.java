package poo;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.geometry.Pos;

public class History extends Application {
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

        Button about = new Button();
        about.setAlignment(Pos.BOTTOM_CENTER);
        about.setText("ABOUT");
        about.setFont(Font.font("Rockwell", 14));
        about.setCursor(Cursor.HAND);
        about.getCursor();
        about.setStyle(Menu.IDLE_BUTTON_STYLE);
        about.setOnMouseEntered(e -> about.setStyle(Menu.HOVERED_BUTTON_STYLE));
        about.setOnMouseExited(e -> about.setStyle(Menu.IDLE_BUTTON_STYLE));

        HBox bts = new HBox();
        bts.setBackground(new Background(bgi));
        bts.setAlignment(Pos.TOP_CENTER);
        bts.getChildren().add(back);
        bts.getChildren().add(about);

        back.setOnAction((event) -> {
            stage.close();
        });
        about.setOnAction((event) -> {
            Alert msgBox = new Alert(AlertType.INFORMATION);
            msgBox.setHeaderText("DEVELOPERS");
            msgBox.setContentText("Programming: Eduardo Machado Martins\nVisual producer: Pedro Machado Dorneles");
            msgBox.showAndWait();
        });

        Button title = new Button();
        title.setText("H I S T O R Y");
        title.setAlignment(Pos.TOP_CENTER);
        title.setStyle("-fx-text-fill: white; -fx-font-size: 40; -fx-font-family:Rockwell; -fx-background-color: transparent; -fx-border-color: transparent;");

        TextArea area = new TextArea();
        area.setStyle("-fx-control-inner-background:#482c20; -fx-highlight-fill: #482c20; -fx-font-size: 19; -fx-font-family:Rockwell; -fx-background-color: transparent; -fx-border-color: transparent;");
        area.setPrefColumnCount(5);
        area.setPrefHeight(600);
        area.setPrefWidth(5);
        area.setEditable(false);
        area.setText("In the distant future, humanity faces a global disaster.\nThe zombie apocalypse has come true. To fight back, a\ngroup of developers created a combat simulation. Your\ngoal in this simulation is to use a configuration that\nuses the least amount of resources to finish off as many\nmonsters as possible. Each character has its own\ncharacteristics as follows. The idiot does not move, but\nwith his desperation he manages to take 25 energy points\nfrom the enemy. The healer can move up to two spaces\nand heals infected humans. The soldier moves one space\nat a time and with his weapon takes 75 energy points from\nthe enemy. The normal zombie walks one house at a time\nand infects humans, as does the runner, but this walks\nup to two houses. When infected, the human loses 20\nenergy points per round. All characters have 100 energy\npoints, except the zombie which has 150. Save your\nresources, good luck.");

        VBox vb = new VBox();
        vb.setBackground(new Background(bgi));
        vb.setAlignment(Pos.TOP_CENTER);
        vb.getChildren().add(title);
        vb.getChildren().add(area);
        vb.getChildren().add(bts);

        Scene scene = new Scene(vb, 550, 770);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("HISTORY - Zombie Attack Simulation");
        stage.setScene(scene);
        stage.show();
        scene.setCursor(Cursor.DEFAULT);
        scene.getCursor();
    }

    public static void main(String[] args) {
        launch(args);
    }
}