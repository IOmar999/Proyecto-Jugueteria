
public class NodoAVL {
    protected Juguete juguete;
    protected NodoAVL izquierdo;
    protected NodoAVL derecho;
    protected int altura;

    public NodoAVL(Juguete juguete) {
        this.juguete = juguete;
        this.izquierdo = null;
        this.derecho = null;
        this.altura = 1; 
    }

    public Juguete getJuguete() {
        return juguete;
    }

    public NodoAVL getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoAVL izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoAVL getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoAVL derecho) {
        this.derecho = derecho;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}
