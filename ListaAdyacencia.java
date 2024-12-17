import java.util.List;
import java.util.ArrayList;

class ListaAdyacencia {
    private final List<Juguete> adyacencias;

    public ListaAdyacencia() {
        this.adyacencias = new ArrayList<>();
    }

    public void agregarAdyacencia(Juguete juguete) {
        adyacencias.add(juguete);
    }

    @Override
    public String toString() {
        StringBuilder cadena = new StringBuilder();
        for (Juguete juguete : adyacencias) {
            cadena.append("  - ").append(juguete).append("\n");
        }
        return cadena.toString();
    }
}
