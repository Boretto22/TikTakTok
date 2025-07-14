import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class TresEnRatlla extends PApplet{

    // 3 variables de classe Casella
    Casella c1, c2, c3;
    // Variable de classe Tauler
    Tauler t;
    // Fonts per mostrar textos
    PFont font1, font2;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("TresEnRatlla");
    }

    public void setup(){
// Instanciació de les 3 caselles amb el constructor

        c1 = new Casella(0, 0, 100, 100, 200);
        c1.setValor(Casella.VALOR.CERCLE);

        c2 = new Casella(1, 2, 400, 100, 200);
        c2.setValor(Casella.VALOR.CREU);

        c3 = new Casella(2, 0, 100, 400, 200);

        // Crea un Tauler de 3x3 de tota l'amplada de la finestra
        t = new Tauler(3, width);
        t.setImatges(this);

        // Carregar fonts
        font1 = createFont("Dimitri.ttf", 48);
        font2 = createFont("Platinum.ttf", 24);


    }

    public void draw(){
        background(200,100,100);

        // Dibuixa el tauler (i totes les seves caselles)
        t.display(this);
        if(t.hihaGuanyador){
            textAlign(CENTER); textSize(24); fill(0);
            textFont(font1);
            text("GUANYADOR "+t.guanyador,width/2,height/2-20);
        }
        if(t.finalPartida){
            textAlign(CENTER); textSize(24); fill(0);
            textFont(font2);
            text("FINAL PARTIDA", width/2, height/2 + 20);
        }


    }

    public void mousePressed(){
        t.casellaPitjada(this);
        t.actualitzaGuanyador();

    }

    public void keyPressed(){
        if(key=='r' || key=='R'){
            t = new Tauler(3, width);
        }
    }


}
