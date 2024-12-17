

public class Nodo {
    private Juguete valor;
    private Nodo siguiente;
    private Nodo anterior;

 
    public Nodo(Juguete valor) {
        this.valor = valor;
        this.siguiente = null;
        this.anterior = null;
    }


    public Juguete getValor() {
        return valor;
    }

    public void setValor(Juguete valor) {
        this.valor = valor;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }
}

