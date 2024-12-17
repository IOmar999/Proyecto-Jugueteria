




import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ColaDinamica {
    private Nodo primero;
    private Nodo ultimo;

    public ColaDinamica() {
        this.primero = null;
        this.ultimo = null;
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public boolean agregar(Juguete juguete) {
        Nodo nuevo = new Nodo(juguete);
        if (estaVacia()) {
            this.primero = nuevo;
            this.ultimo = nuevo;
            return true;
        }
        this.ultimo.setSiguiente(nuevo);
        this.ultimo = nuevo;
        return true;
    }

    public Juguete eliminar() throws Exception {
        if (estaVacia()) {
            throw new Exception("Cola vacía");
        }
        Juguete valor = primero.getValor();
        this.primero = primero.getSiguiente();
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

                this.agregar(juguete);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } 
    }

    public void PedidosPorMarca(String marca) {
        if (estaVacia()) {
            System.out.println("La cola está vacía.");
            return;
        }
        System.out.println("Procesando pedidos para la marca: " + marca);
        int count = 0;
        Nodo actual = primero;
        while (actual != null) {
            Juguete juguete = actual.getValor();
            if (juguete.getMarca().equals(marca)) {
                System.out.println("Pedido atendido: " + juguete.getNombre() + "  Precio: " + juguete.getPrecio());
                count++;
            }
            actual = actual.getSiguiente();
        }

        if (count == 0) {
            System.out.println("No hay pedidos para la marca: " + marca);
        }
     }
}



