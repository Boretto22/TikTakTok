import processing.core.PApplet;
import processing.core.PImage;


public class Tauler {

    // Imatges del símbols del joc
    PImage imgCreu, imgCercle;

    // Símbol del guanyador (X o O)
    char guanyador;

    // Partida finalitzada
    boolean finalPartida;

    // Número de tirades
    int numTirades;

    // Dimensions de les caselles del tauler
    float midaCasella;

    // Array bidimensional de caselles
    Casella[][] caselles;


    // Constructor
    public Tauler(int n, float w){
        caselles = new Casella[n][n];
        this.midaCasella = w / n;
        hihaGuanyador = false;
        guanyador = ' ';
        finalPartida = false;
        numTirades = 0;
        for(int f = 0; f< caselles.length; f++){
            for(int c = 0; c< caselles[f].length; c++){
                caselles[f][c] = new Casella(f, c, midaCasella *c, midaCasella *f, midaCasella);
            }
        }
    }

    // Setter per definir les imatges del Tres en ratlla
    public void setImatges(PApplet p5){
        this.imgCreu   = p5.loadImage("creu.png");
        this.imgCercle = p5.loadImage("cercle.png");
    }

    // Dibuixa el tauler del joc
    public void display(PApplet p5){
        for(int f = 0; f< caselles.length; f++){
            for(int c = 0; c< caselles[f].length; c++){
                caselles[f][c].display(p5, imgCreu, imgCercle);
            }
        }
    }

    // Comprova si clicam sobre alguna casella del tauler
    public void casellaPitjada(PApplet p5){
        if(!finalPartida) {

            for (int f = 0; f < caselles.length; f++) {
                for (int c = 0; c < caselles[f].length; c++) {

                    if (caselles[f][c].estaDins(p5.mouseX, p5.mouseY) &&
                            caselles[f][c].valor == Casella.VALOR.BLANC) {

                        if (numTirades%2==0) {
                            caselles[f][c].setValor(Casella.VALOR.CERCLE);
                        } else {
                            caselles[f][c].setValor(Casella.VALOR.CREU);
                        }

                        numTirades++;
                        break;

                    }

                }
            }

        }

    }
    // Comprova si els valors de la fila f són iguals
    public boolean filaIguals(int f){
        if (caselles[f][0].valor == caselles[f][1].valor &&
                caselles[f][1].valor == caselles[f][2].valor &&
                caselles[f][0].valor != Casella.VALOR.BLANC){
            return true;
        }
        else {
            return false;
        }
    }
    // Comprova si els valors de la columna c són iguals
    public boolean columnaIguals(int c){
        if (caselles[0][c].valor == caselles[1][c].valor &&
                caselles[1][c].valor == caselles[2][c].valor &&
                caselles[0][c].valor != Casella.VALOR.BLANC){
            return true;
        }
        else {
            return false;
        }
    }
    // Comprova si els valors de la diagonal desc. són iguals
    public boolean diagonalDescIguals(){
        if (caselles[0][0].valor == caselles[1][1].valor &&
                caselles[1][1].valor == caselles[2][2].valor &&
                caselles[0][0].valor != Casella.VALOR.BLANC){
            return true;
        }
        else {
            return false;
        }
    }
    // Comprova si els valors de la diagonal asc. són iguals
    public boolean diagonalAscIguals(){
        if (caselles[2][0].valor == caselles[1][1].valor &&
                caselles[1][1].valor == caselles[0][2].valor &&
                caselles[2][0].valor != Casella.VALOR.BLANC){
            return true;
        }
        else {
            return false;
        }
    }
    // Comprova files, columnes i diagonals
    public boolean comprovaGuanyador(){

        // Comprova files
        for(int f = 0; f< caselles.length; f++){
            if(filaIguals(f)== true){
                return true;
            }
        }

        // Comprova columnes
        for(int c = 0; c< caselles[0].length; c++){
            if(columnaIguals(c)== true){
                return true;
            }
        }

        // Comprova diagonals
        return diagonalAscIguals() || diagonalDescIguals();
    }
    // Indica si hi ha alguna línia guanyadora
    boolean hihaGuanyador;

    // Actualitza l'estat de la propietat hihaGuanyador
    public void actualitzaGuanyador(){
        hihaGuanyador = comprovaGuanyador();
        hihaGuanyador = comprovaGuanyador();
        if(hihaGuanyador){
            guanyador = numTirades%2==0 ? 'X' : 'O';
        }
        if(numTirades == 9 || hihaGuanyador){
            finalPartida = true;
        }
    }


}
