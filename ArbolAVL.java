import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

  
    private int obtenerAltura(NodoAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.getAltura();
    }

    
    private int obtenerBalance(NodoAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return obtenerAltura(nodo.getIzquierdo()) - obtenerAltura(nodo.getDerecho());
    }

    private NodoAVL rotarIzquierda(NodoAVL raiz) {
        NodoAVL nuevoRaiz = raiz.getDerecho();
        NodoAVL temp = nuevoRaiz.getIzquierdo();

        nuevoRaiz.setIzquierdo(raiz);
        raiz.setDerecho(temp);

        raiz.setAltura(Math.max(obtenerAltura(raiz.getIzquierdo()), obtenerAltura(raiz.getDerecho())) + 1);
        nuevoRaiz.setAltura(Math.max(obtenerAltura(nuevoRaiz.getIzquierdo()), obtenerAltura(nuevoRaiz.getDerecho())) + 1);

        return nuevoRaiz;
    }

    private NodoAVL rotarDerecha(NodoAVL raiz) {
        NodoAVL nuevoRaiz = raiz.getIzquierdo();
        NodoAVL temp = nuevoRaiz.getDerecho();

        nuevoRaiz.setDerecho(raiz);
        raiz.setIzquierdo(temp);

        raiz.setAltura(Math.max(obtenerAltura(raiz.getIzquierdo()), obtenerAltura(raiz.getDerecho())) + 1);
        nuevoRaiz.setAltura(Math.max(obtenerAltura(nuevoRaiz.getIzquierdo()), obtenerAltura(nuevoRaiz.getDerecho())) + 1);

        return nuevoRaiz;
    }

    
    public NodoAVL insertar(NodoAVL nodo, Juguete juguete) {
      
        if (nodo == null) {
            return new NodoAVL(juguete);
        }

        if (juguete.getPrecio() < nodo.getJuguete().getPrecio()) {
            nodo.setIzquierdo(insertar(nodo.getIzquierdo(), juguete));
        } else if (juguete.getPrecio() > nodo.getJuguete().getPrecio()) {
            nodo.setDerecho(insertar(nodo.getDerecho(), juguete));
        } else {
            
            return nodo;
        }
        
        nodo.setAltura(1 + Math.max(obtenerAltura(nodo.getIzquierdo()), obtenerAltura(nodo.getDerecho())));
        int balance = obtenerBalance(nodo);

        if (balance > 1 && juguete.getPrecio() < nodo.getIzquierdo().getJuguete().getPrecio()) {
            return rotarDerecha(nodo);
        }

        if (balance < -1 && juguete.getPrecio() > nodo.getDerecho().getJuguete().getPrecio()) {
            return rotarIzquierda(nodo);
        }

        if (balance > 1 && juguete.getPrecio() > nodo.getIzquierdo().getJuguete().getPrecio()) {
            nodo.setIzquierdo(rotarIzquierda(nodo.getIzquierdo()));
            return rotarDerecha(nodo);
        }

        if (balance < -1 && juguete.getPrecio() < nodo.getDerecho().getJuguete().getPrecio()) {
            nodo.setDerecho(rotarDerecha(nodo.getDerecho()));
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    public void insertar(Juguete juguete) {
        raiz = insertar(raiz, juguete);
    }
    
     public void mostrarJuguetes() {
        mostrarEnOrden(raiz);
    }
    
    public void mostrarEnOrden(NodoAVL nodo) {
        if (nodo != null) {
            mostrarEnOrden(nodo.getIzquierdo());
            System.out.println(nodo.getJuguete());
            mostrarEnOrden(nodo.getDerecho());
        }
    }
    
    public void insertarJuguete(Juguete juguete) {
        raiz = insertar(raiz, juguete);  
    }
    
    public void cargarCSV(String archivoCSV) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    String nombre = datos[0].trim();
                    String marca = datos[1].trim();
                    double precio = Double.parseDouble(datos[2].trim());
                    String tipo = datos[3].trim();

                    Juguete juguete = new Juguete(nombre, marca, precio, tipo);
                    insertarJuguete(juguete);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }
    
    public void mostrarEnOrden() {
        mostrarEnOrden(raiz);
    }
    
        public void mostrarJuguetesPorTipo(String tipo) {
        mostrarJuguetesPorTipo(raiz, tipo);
    }

    private void mostrarJuguetesPorTipo(NodoAVL nodo, String tipo) {
        if (nodo != null) {
        mostrarJuguetesPorTipo(nodo.getIzquierdo(), tipo);

        if (nodo.getJuguete().getTipo().equalsIgnoreCase(tipo)) {
            System.out.println(nodo.getJuguete());
        }

        mostrarJuguetesPorTipo(nodo.getDerecho(), tipo);
        }
    } 
    
    public Juguete buscarPrecio(double precio) {
        return buscarRecursivoPrecio(raiz, precio);
    }

    public  Juguete buscarRecursivoPrecio(NodoAVL nodo, double precio) {
        if (nodo == null) {
            return null; 
        }
        if (nodo.juguete.getPrecio()==precio) {
            return nodo.juguete; 
        }
        if (precio < nodo.juguete.getPrecio()) {
            return buscarRecursivoPrecio(nodo.izquierdo, precio);
        } else {
            return buscarRecursivoPrecio(nodo.derecho, precio); 
        }
    }
}








