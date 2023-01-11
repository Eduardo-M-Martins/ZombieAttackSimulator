package poo;

import javafx.scene.image.*;
import javafx.scene.layout.*;

public class Cell extends javafx.scene.control.Button {
    private Character character;
    private int line;
    private int column;

    public Cell(int l, int c) {
        this.line = l;
        this.column = c;
        character = null;
        setCharacterImage();
    }

    public void setCharacterImage() {
        if (character != null) {
            String image = "";
            if (this.getCharacter().isAlive()) {
                image = this.getCharacter().getImage();
            } else {
                image = "Dead";
            }
            ImageView iVaux = new ImageView(Game.getInstance().getImage(image));
            iVaux.setFitWidth(Game.CELL_SIDE);
            iVaux.setFitHeight(Game.CELL_SIDE);
            setGraphic(iVaux);
        } else {
            ImageView iVaux = new ImageView(Game.getInstance().getImage("Grass"));
            iVaux.setFitWidth(Game.CELL_SIDE);
            iVaux.setFitHeight(Game.CELL_SIDE);
            setGraphic(iVaux);
            BackgroundImage bgi = new BackgroundImage(new Image("file:Images/grass.jpeg", 25, 25, false, true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT);
            setBackground(new Background(bgi));
        }
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character c) {
        this.character = c;
        if (c != null) {
            c.setCell(this);
        }
        setCharacterImage();
    }

    public int getLine() {
        return line;
    }

    public void setLine(int l) {
        this.line = l;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int c) {
        this.column = c;
    }
}