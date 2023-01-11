package poo;

public class Dumb extends Character {
    private int attackNumber;

    public Dumb(int line, int column, int attackNumber, String id) {
        super(100, "Dumb", line, column, Enume.DUMB, id);
        this.attackNumber = attackNumber;
    }

    public Dumb(int line, int column, int attackNumber, String id, String status) {
        super(100, "Dead", line, column, Enume.DUMB, id);
        this.attackNumber = attackNumber;
        super.setImage(status);
    }

    @Override
    public void infect() {
        if (this.isInfected()) {
            return;
        }
        super.infect();
        this.setImage("DumbInf");
        this.getCell().setCharacterImage();
    }

    @Override
    public void cure() {
        if (!this.isInfected()) {
            return;
        }
        super.cure();
    }

    @Override
    public void nextPos() {
    }

    @Override
    public void action() {
        int lin = this.getCell().getLine();
        int col = this.getCell().getColumn();
        for (int l = lin - 1; l <= lin + 1; l++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if (l >= 0 && l < Game.NLIN && c >= 0 && c < Game.NCOL) {
                    if (!(lin == l && col == c)) {
                        Character p = Game.getInstance().getCelula(l, c).getCharacter();
                        if (p != null) {
                            if (p.getEnume() == Enume.RUNNER || p.getEnume() == Enume.ZOMBIE) {
                                if (this.isAlive()) {
                                    if (this.attackNumber > 0){
                                        p.decEnergy(45);
                                        attackNumber--;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void stateStatus() {
        if (this.isInfected()) {
            decEnergy(15);
        }

        if (this.getEnergy() <= 0) {
            this.setImage("Dead");
            this.setId("BM");
            this.getCell().setCharacterImage();
        }
    }
}