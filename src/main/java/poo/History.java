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

        final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent; -fx-text-fill: white;";
        final String HOVERED_BUTTON_STYLE = "-fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, -fx-body-color;";

        Button btn1 = new Button();
        btn1.setAlignment(Pos.BOTTOM_CENTER);
        btn1.setText("BACK");
        btn1.setFont(Font.font("Rockwell", 14));
        btn1.setCursor(Cursor.HAND);
        btn1.getCursor();
        btn1.setStyle(IDLE_BUTTON_STYLE);
        btn1.setOnMouseEntered(e -> btn1.setStyle(HOVERED_BUTTON_STYLE));
        btn1.setOnMouseExited(e -> btn1.setStyle(IDLE_BUTTON_STYLE));

        Button btn2 = new Button();
        btn2.setAlignment(Pos.BOTTOM_CENTER);
        btn2.setText("ABOUT");
        btn2.setFont(Font.font("Rockwell", 14));
        btn2.setCursor(Cursor.HAND);
        btn2.getCursor();
        btn2.setStyle(IDLE_BUTTON_STYLE);
        btn2.setOnMouseEntered(e -> btn2.setStyle(HOVERED_BUTTON_STYLE));
        btn2.setOnMouseExited(e -> btn2.setStyle(IDLE_BUTTON_STYLE));

        HBox bts = new HBox();
        bts.setBackground(new Background(bgi));
        bts.setAlignment(Pos.TOP_CENTER);
        bts.getChildren().add(btn1);
        bts.getChildren().add(btn2);

        btn1.setOnAction((event) -> {
            stage.close();
        });

        btn2.setOnAction((event) -> {
            Alert msgBox = new Alert(AlertType.INFORMATION);
            msgBox.setHeaderText("DEVELOPERS");
            msgBox.setContentText("Programming: Eduardo Machado Martins\nVisual producer: Pedro Machado Dorneles");
            msgBox.showAndWait();
        });

        Button t = new Button();
        t.setText("H I S T O R Y");
        t.setBackground(Background.EMPTY);
        t.setMinSize(0, 150);
        t.setBorder(Border.EMPTY);
        t.setFont(Font.font("Rockwell", 40));
        t.setAlignment(Pos.TOP_CENTER);
        t.setStyle(IDLE_BUTTON_STYLE);

        TextArea area = new TextArea();
        area.setStyle("-fx-control-inner-background:#482c20; -fx-highlight-fill: #482c20;");
        area.setBorder(Border.EMPTY);
        area.setBackground(Background.EMPTY);
        area.setPrefColumnCount(5);
        area.setPrefHeight(500);
        area.setPrefWidth(5);
        area.setFont(Font.font("Rockwell", 16));
        area.setEditable(false);
        area.setText("History...");

        GridPane grid = new GridPane();
        grid.setBackground(new Background(bgi));
        grid.setMinSize(500, 45);
        grid.setAlignment(Pos.TOP_CENTER);
        grid.getChildren().add(t);

        VBox vb = new VBox();
        vb.setBackground(new Background(bgi));
        vb.setAlignment(Pos.TOP_CENTER);
        vb.getChildren().add(grid);
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