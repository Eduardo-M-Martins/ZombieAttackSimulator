package poo;

public class Healer extends Character {

    public Healer(String energy, int line, int column, String id) {
        super(Integer.parseInt(energy), "Healer", line, column, Enume.HEALER, id);
        if(Integer.parseInt(energy) <= 0)
            super.setImage("Dead");
    }

    @Override
    public void infect() {
        if (this.isInfected()) {
            return;
        }
        super.infect();
        this.setImage("HealerInf");
        this.setId("HI");
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
        if (this.getEnergy() > 0) {
            int dirLin = Game.getInstance().random_2(2);
            int dirCol = Game.getInstance().random_2(2);
            int oldLin = this.getCell().getLine();
            int oldCol = this.getCell().getColumn();
            int lin = oldLin + dirLin;
            int col = oldCol + dirCol;
            if (lin < 0)
                lin = 0;
            if (lin >= Game.NLIN)
                lin = Game.NLIN - 1;
            if (col < 0)
                col = 0;
            if (col >= Game.NCOL)
                col = Game.NCOL - 1;
            if (Game.getInstance().getCelula(lin, col).getCharacter() != null) {
                return;
            } else {
                Game.getInstance().getCelula(oldLin, oldCol).setCharacter(null);
                Game.getInstance().getCelula(lin, col).setCharacter(this);
            }
        }
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
                            if (this.getEnergy() >= 0) {
                                if (p.isInfected() == true && this.isAlive() && p.isAlive())
                                    p.cure();
                                if (p.getEnume() == Enume.DUMB && p.isAlive()) {
                                    p.setImage("Dumb");
                                    p.setId("DC");
                                    p.getCell().setCharacterImage();
                                }
                                if (p.getEnume() == Enume.HEALER && p.isAlive()) {
                                    p.setImage("Healer");
                                    p.setId("HC");
                                    p.getCell().setCharacterImage();
                                }
                                if (p.getEnume() == Enume.SOLDIER && p.isAlive()) {
                                    p.setId("SC");
                                    p.getCell().setCharacterImage();
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
            decEnergy(20);
        }
        if (this.getEnergy() <= 0) {
            this.setImage("Dead");
            this.getCell().setCharacterImage();
        }
    }
}