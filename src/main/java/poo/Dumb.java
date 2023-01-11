package poo;

public class Dumb extends Character {
    public Dumb(String energy, int line, int column, String id) {
        super(Integer.parseInt(energy), "Dumb", line, column, Enume.DUMB, id);
        if(Integer.parseInt(energy) <= 0)
            super.setImage("Dead");
    }

    @Override
    public void infect() {
        if (this.isInfected()) {
            return;
        }
        super.infect();
        this.setImage("DumbInf");
        this.setId("DI");
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
                                    p.decEnergy(20);
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
            this.getCell().setCharacterImage();
        }
    }
}