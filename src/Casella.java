import processing.core.PApplet;
import processing.core.PImage;

public class Casella {

    // Enumerat amb els valors de les caselles del tauler
    public enum VALOR { BLANC, CREU, CERCLE };

    // Valor guardat de la casella
    VALOR valor;
    // Números de fila i columna de la casella en el tauler
    int fila, columna;
    // Coordenades (x,y) de la casella
    float x, y;
    // Mides de la casella
    float w;
    // Valor de la casella segons el Minimax
    int valorMiniMax;

    // Constructor
    public Casella(int f, int c, float x, float y, float w){
        this.fila = f;
        this.columna = c;
        this.valor = VALOR.BLANC;
        this.x = x;
        this.y = y;
        this.w = w;
    }
    // Setter per a la propietat valor
    public void setValor(VALOR v){
        this.valor = v;
    }
    // Setter de la propietat valorMiniMax
    public void setValorMiniMax(int m){
        this.valorMiniMax = m;
    }

    // Indica si el punt(x, y) està dins la casella (quadrada)
    public boolean estaDins(float x, float y){
        return (this.x<=x && x<=this.x+w &&
                this.y<=y && y<=this.y+w);
    }

    // Mètode per dibuixar la casella (emprant processing)
    public void display(PApplet p5, PImage imgCercle, PImage imgCreu){
        p5.pushStyle();
        p5.rectMode(p5.CORNER);
        p5.fill(255);
        if(estaDins(p5.mouseX, p5.mouseY)){
            p5.fill(200);
        }
        p5.rect(x, y, w, w);

        if(valor==VALOR.CREU){
            p5.image(imgCreu, x, y, w, w);
        }
        else if(valor==VALOR.CERCLE){
            p5.image(imgCercle,x,y,w,w);
        }
        p5.popStyle();
    }


}
