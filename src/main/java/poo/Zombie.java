package poo;

public class Zombie extends Character {
    public Zombie(String energy, int line, int column, String id) {
        super(Integer.parseInt(energy), "Zombie", line, column, Enume.ZOMBIE, id);
        if(Integer.parseInt(energy) <= 0)
            super.setImage("Dead");
    }

    @Override
    public void nextPos() {
        if (this.isAlive()) {
            int dirLin = Game.getInstance().random_1(3) - 1;
            int dirCol = Game.getInstance().random_1(3) - 1;
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
                            if (this.isAlive())
                                p.infect();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void stateStatus() {
        if (!this.isAlive()) {
            this.setImage("Dead");
            this.getCell().setCharacterImage();
        }
    }
}