package poo;

public abstract class Character {
    private String image;
    private int energy;
    private boolean infected;
    private Cell cell;
    private Enume enume;
    private String id;

    enum Enume {
        DUMB, ZOMBIE, RUNNER, HEALER, SOLDIER
    };

    public Character(int initialEnergy, String initialImage, int line, int column, Enume enume, String id) {
        this.image = initialImage;
        this.energy = initialEnergy;
        Game.getInstance().getCelula(line, column).setCharacter(this);
        this.infected = false;
        this.enume = enume;
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getEnergy() {
        return energy;
    }

    public void kill() {
        this.energy = 0;
    }

    public void incEnergy(int value) {
        if (value < 0)
            throw new IllegalArgumentException("Invalid energy value");
        energy += value;
    }

    public void decEnergy(int value) {
        if (value < 0)
            throw new IllegalArgumentException("Invalid energy value");
        energy -= value;
        if (energy < 0) {
            energy = 0;
        }
    }

    public boolean isInfected() {
        return infected;
    }

    public void infect() {
        infected = true;
    }

    public void cure() {
        infected = false;
    }

    public boolean isAlive() {
        return getEnergy() > 0;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Enume getEnume() {
        return enume;
    }

    public abstract void nextPos();

    public abstract void stateStatus();

    public abstract void action();
}