

import java.util.Scanner;
import java.io.BufferedReader;
import java.util.List;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;

public class TiendaJugueteria{

    public static void mostrarMenu() {
        System.out.println("\n=== Menú de Opciones ===");
        System.out.println("1. Cargar juguetes en la pila");
        System.out.println("2. Cargar juguetes en la cola");
        System.out.println("3. Ver juguetes en la pila");
        System.out.println("4. Ver juguetes en la cola");
        System.out.println("5. Cargar juguetes en el árbol AVL");
        System.out.println("6. Ver juguetes en el árbol AVL (ordenados por precio)");
        System.out.println("7. Ordenar por nombres(Quiksort)");
        System.out.println("8. Cargar juguetes en el grafo y sus juguestes");
        System.out.println("9. Ver juguetes de un proveedor");
        System.out.println("10. Ver todos los proveedores y sus jugutes");
        System.out.println("11. Procesar pedidos de una marca");
        System.out.println("12. Procesar devoluciones por precio");
        System.out.println("13. Ver todos los juguetes de un tipo específico");
        System.out.println("14. Agregar un juguete al árbol AVL");
        System.out.println("15. Buscar y ver un juguete por precio");
        System.out.println("16. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static void verPila(PilaDinamica pila) {
        if (pila.estaVacia()) {
            System.out.println("La pila está vacía.");
        } else {
            System.out.println("Juguetes en la pila:");
            while (!pila.estaVacia()) {
                try {
                    Juguete juguete = pila.pop();
                    System.out.println(juguete);
                } catch (Exception e) {
                    System.out.println("Error al eliminar de la pila: " + e.getMessage());
                }
            }
        }
    }

    public static void verCola(ColaDinamica cola) {
        if (cola.estaVacia()) {
            System.out.println("La cola está vacía.");
        } else {
            System.out.println("Juguetes en la cola:");
            while (!cola.estaVacia()) {
                try {
                    Juguete juguete = cola.eliminar();
                    System.out.println(juguete);
                } catch (Exception e) {
                    System.out.println("Error al eliminar de la cola: " + e.getMessage());
                }
            }
        }
    }
    
     public static void cargarGrafoYMostrar(Grafo grafo, String archivoCSV) {
        System.out.println("Cargando proveedores y juguetes desde el archivo CSV...");
        grafo.cargarCSV(archivoCSV);
        System.out.println("Proveedores y juguetes cargados correctamente.");
        System.out.println("Proveedores y sus juguetes:");
        grafo.mostrarProveedoresYJuguetes();
    }
    
    public static void verArbol(ArbolAVL arbol) {
        if (arbol == null) {
            System.out.println("El árbol está vacío.");
        } else {
            System.out.println("Juguetes en el árbol AVL (ordenados por precio):");
            arbol.mostrarEnOrden();
        }
    }

    public static void ordenarPorQuicksort(String archivoCSV) {
        List<String> nombresJuguetes = CargarDesdeCSV(archivoCSV);

        if (nombresJuguetes.isEmpty()) {
            System.out.println("No se encontraron juguetes en el archivo.");
            return;
        }

        String[] nombresArray = nombresJuguetes.toArray(new String[0]);

        Quicksort quicksort = new Quicksort();
        quicksort.quick(nombresArray);

        System.out.println("\nJuguetes ordenados por nombre:");
        for (String nombre : nombresArray) {
            System.out.println(nombre);
        }
    }

    public static List<String> CargarDesdeCSV(String archivoCSV) {
        List<String> nombres = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length > 0) {
                    nombres.add(datos[0].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return nombres;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PilaDinamica pilaJuguetes = new PilaDinamica();
        PilaDinamica pilaDevoluciones = new PilaDinamica();
        ColaDinamica colaJuguetes = new ColaDinamica();
        ColaDinamica colaPedidos = new ColaDinamica();
        ArbolAVL arbolJuguetes = new ArbolAVL();
        ArbolAVL arbolTipos = new ArbolAVL();
        Grafo grafo = new Grafo();
        String archivoCSV = "juguetes.csv"; 
        pilaDevoluciones.cargarCSV(archivoCSV);
        colaPedidos.cargarCSV(archivoCSV);
        arbolTipos.cargarCSV(archivoCSV);
        String archivo1CSV = "juguetes2.csv";

        int opcion;
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();  

            switch (opcion) {
                case 1:
                    System.out.println("Cargando juguetes en la pila...");
                    pilaJuguetes.cargarCSV(archivoCSV);
                    System.out.println("Juguetes cargados en la pila.");
                    break;

                case 2:
                    System.out.println("Cargando juguetes en la cola...");
                    colaJuguetes.cargarCSV(archivoCSV);
                    System.out.println("Juguetes cargados en la cola.");
                    break;

                case 3:
                    verPila(pilaJuguetes);
                    break;

                case 4:
                    verCola(colaJuguetes);
                    break;

                case 5:
                    System.out.println("Cargando juguetes en el árbol AVL...");
                    pilaJuguetes.cargarCSV(archivoCSV); 
                    
                    while (!pilaJuguetes.estaVacia()) {
                        try {
                            Juguete juguete = pilaJuguetes.pop();
                            arbolJuguetes.insertar(juguete);
                        } catch (Exception e) {
                            System.out.println("Error al cargar el juguete en el árbol: " + e.getMessage());
                        }
                    }
                    System.out.println("Juguetes cargados en el árbol AVL.");
                    break;

                case 6:
                    verArbol(arbolJuguetes);
                    break;

                case 7:
                    System.out.println("Ordenando juguetes por nombre (Quicksort)...");
                    ordenarPorQuicksort(archivoCSV);
                    break;
                case 8:
                    System.out.println("Cargando proveedores y juguetes desde el archivo CSV...");
                    grafo.cargarCSV(archivo1CSV);
                    System.out.println("Proveedores y juguetes cargados correctamente.");
                    break;
                case 9:
                    System.out.print("Ingrese el nombre del proveedor: ");
                    String proveedor = scanner.nextLine();
                    grafo.mostrarJuguetesDeProveedor(proveedor);
                    break;
                case 10:
                    System.out.println("Proveedores y sus juguetes:");
                    grafo.mostrarProveedoresYJuguetes();
                    break;
                case 11:
                    System.out.print("Ingrese la marca para procesar los pedidos: ");
                    String marca = scanner.nextLine();
                    colaPedidos.PedidosPorMarca(marca);
                    break;
                case 12:
                    System.out.print("Ingrese el precio límite para las devoluciones: ");
                    double precioLimite = scanner.nextDouble();
                    pilaDevoluciones.DevolucionesPorPrecio(precioLimite);
                    break;
                case 13:
                    System.out.print("Ingrese el tipo de juguete para mostrar: ");
                    String tipo = scanner.nextLine();
                    System.out.println("Juguetes del tipo '" + tipo + "':");
                    arbolTipos.mostrarJuguetesPorTipo(tipo);
                    break;
                case 14:
                    System.out.println("Ingrese los detalles del juguete para agregarlo al árbol AVL:");
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Marca: ");
                    String marcaJuguete = scanner.nextLine();
                    System.out.print("Precio: ");
                    double precio = scanner.nextDouble();
                    scanner.nextLine();  
                    System.out.print("Tipo: ");
                    String tipoJuguete = scanner.nextLine();

                    Juguete nuevoJuguete = new Juguete(nombre, marcaJuguete, precio, tipoJuguete);
                    arbolJuguetes.insertar(nuevoJuguete);
                    System.out.println("Juguete agregado correctamente al árbol AVL.");
                    break;
                
                case 15:
                System.out.print("Ingrese el precio del juguete a buscar: ");
                double precioBusqueda = scanner.nextDouble();
                Juguete jugueteEncontrado = arbolJuguetes.buscarPrecio(precioBusqueda);
                if (jugueteEncontrado != null) {
                    System.out.println("Juguete encontrado: " + jugueteEncontrado);
                } else {
                    System.out.println("No se encontró ningún juguete con ese precio.");
                }
                break;

                    
                case 16:
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    break;
                
                

                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }

        scanner.close();
    }
}
