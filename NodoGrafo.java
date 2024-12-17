

class NodoGrafo {
    protected String dato;
    protected NodoGrafo siguiente;
    protected ListaAdyacencia listaAdyacencia;

    public NodoGrafo(String dato) {
        this.dato = dato;
        this.siguiente = null;
        this.listaAdyacencia = new ListaAdyacencia();
    }

    public String getDato() {
        return dato;
    }

    public NodoGrafo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoGrafo siguiente) {
        this.siguiente = siguiente;
    }

    public ListaAdyacencia getListaAdyacencia() {
        return listaAdyacencia;
    }
}
