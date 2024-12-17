



import java.io.*;
import java.util.*;

public class Grafo{
    protected NodoGrafo primero;
    protected NodoGrafo ultimo;

    public Grafo() {
        this.primero = null;
        this.ultimo = null;
    }

    public boolean estaVacio() {
        return this.primero == null;
    }

    public boolean existeVertice(String proveedor) {
        NodoGrafo actual = primero;
        while (actual != null) {
            if (actual.getDato().equals(proveedor)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public boolean agregarProveedor(String proveedor) {
        if (existeVertice(proveedor)) {
            return false;
        }
        NodoGrafo nuevoNodo = new NodoGrafo(proveedor);
        if (estaVacio()) {
            this.primero = nuevoNodo;
            this.ultimo = nuevoNodo;
        } else {
            this.ultimo.setSiguiente(nuevoNodo);
            this.ultimo = nuevoNodo;
        }
        return true;
    }

    public boolean agregarJugueteAProveedor(String proveedor, Juguete juguete) {
        if (!existeVertice(proveedor)) {
            System.out.println("El proveedor no existe.");
            return false;
        }
        NodoGrafo actual = primero;
        while (!actual.getDato().equals(proveedor)) {
            actual = actual.getSiguiente();
        }
        actual.getListaAdyacencia().agregarAdyacencia(juguete);
        return true;
    }

    public void mostrarJuguetesDeProveedor(String proveedor) {
        if (!existeVertice(proveedor)) {
            System.out.println("Proveedor no encontrado.");
            return;
        }
        NodoGrafo actual = primero;
        while (!actual.getDato().equals(proveedor)) {
            actual = actual.getSiguiente();
        }
        System.out.println("Juguetes del proveedor " + proveedor + ":");
        System.out.println(actual.getListaAdyacencia());
    }

    public void mostrarProveedoresYJuguetes() {
        if (estaVacio()) {
            System.out.println("No hay proveedores registrados.");
            return;
        }
        NodoGrafo actual = primero;
        while (actual != null) {
            System.out.println("Proveedor: " + actual.getDato());
            System.out.println(actual.getListaAdyacencia());
            actual = actual.getSiguiente();
        }
    }

    public void cargarCSV(String archivoCSV) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            br.readLine(); 
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    String proveedor = datos[0];
                    String nombreJuguete = datos[1];
                    String marca = datos[2];
                    double precio = Double.parseDouble(datos[3]);
                    String tipo = datos[4];

                    Juguete juguete = new Juguete(nombreJuguete, marca, precio, tipo);
                    agregarProveedor(proveedor);
                    agregarJugueteAProveedor(proveedor, juguete);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }
}





