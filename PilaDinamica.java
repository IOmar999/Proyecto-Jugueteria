import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class PilaDinamica {
    private Nodo tope;

    public PilaDinamica() {
        this.tope = null;
    }

    public boolean estaVacia() {
        return this.tope == null;
    }

    public boolean push(Juguete juguete) {
        Nodo n = new Nodo(juguete);
        if (estaVacia()) {
            tope = n;
            return true;
        } else {
            n.setSiguiente(tope);
            tope = n;
        }
        return true;
    }

    public Juguete pop() throws Exception {
        if (estaVacia())
            throw new Exception("Pila vacía");

        Juguete valor = tope.getValor();
        this.tope = tope.getSiguiente();
        return valor;
    }

    public void cargarCSV(String archivoCSV) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            br.readLine();

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0];
                String marca = datos[1];
                double precio = Double.parseDouble(datos[2]);
                String tipo = datos[3];

                Juguete juguete = new Juguete(nombre, marca, precio, tipo);

                this.push(juguete);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } 
    }

    public void DevolucionesPorPrecio(double precioLimite) {
        if (estaVacia()) {
            System.out.println("La pila está vacía.");
            return;
        }

        System.out.println("Procesando devoluciones con precio menor a: " + precioLimite);
        
        Nodo actual = tope; 
        while (actual != null) {
            Juguete juguete = actual.getValor();
            if (juguete.getPrecio() < precioLimite) {
                System.out.println("Devolviendo: " + juguete.getNombre() + "  Precio: " + juguete.getPrecio());
            }
            actual = actual.getSiguiente(); 
        }
    }
}


