package sortingalgos;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * https://github.com/adamulp/sortingAlgos/
 *
 * @authors:
 *           Alexis Allendez https://github.com/AlexisAllendez
 *           Enzo Casalegno https://github.com/EnzoCasalegno
 *           Nicolás Hollmann https://github.com/HollmannCod3
 *           Adam Rigg https://github.com/adamulp/
 */

/*
 * Ejercicio 1
 * Definir una clase ordenamiento que contenga el atributo datos (un array de
 * enteros) en el cual se agregarán los métodos:
 * 
 * Carga(): que llena el arreglo con 10000 números aleatorios.
 * Muestra()
 * bubbleSort()
 * insertionSort()
 * shellSort()
 * 
 * El método main() de la clase principal, tiene que crear un objeto de la clase
 * ordenamiento e invocar a los métodos y mostrar el arreglo antes y después de
 * ordenar.
 */
public class SortingAlgos {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        mostrarHora();
        String lineBreak = "--------------------------------------------------------------------------------------------------";
        String sectionBreak = "##################################################################################################";

        // -----------------------------------------------------------------------
        // Prueba de Insertion Sort
        // -----------------------------------------------------------------------
        Ordenamiento insertionSort = new Ordenamiento(10);
        insertionSort.cargar();
        System.out.println(sectionBreak);
        System.out.println("Insertion Sort");
        System.out.println(sectionBreak);
        System.out.println("Datos generados, pendientes para ordenar:");
        insertionSort.muestra(insertionSort.unsortedNums, 20);

        System.out.println(lineBreak);
        System.out.println("Insertion Sort:");

        insertionSort.insertionSort();
        insertionSort.muestra(20);
        mostrarTiempoEjecucion(insertionSort.getRunTimeMs());
        System.out.println("");

        // -----------------------------------------------------------------------
        // Prueba de Bubble Sort
        // -----------------------------------------------------------------------
        System.out.println(sectionBreak);
        System.out.println("Bubble Sort");
        System.out.println(sectionBreak);

        Ordenamiento bubbleSort = new Ordenamiento(insertionSort.unsortedNums);
        System.out.println(lineBreak);
        System.out.println("Datos no ordenados:");
        bubbleSort.muestra(bubbleSort.unsortedNums, 20);

        System.out.println(lineBreak);
        System.out.println("Datos Ordenados (Bubble Sort):");
        bubbleSort.bubbleSort();

        System.out.println("bubbleSort hash:\t" + bubbleSort.getSortHash());
        System.out.println("insertionS hash:\t" + insertionSort.getSortHash());
        if (bubbleSort.getSortHash().equals(insertionSort.getSortHash())) {
            System.out.println("Bubble sort tiene el mismo resultado que"
                    + " insertion sort!");
        } else {
            System.out.println("Bubble sort e insertion sort están dando"
                    + " resultados distintos");
        }
        compararOrdenamiento(bubbleSort, insertionSort);

        mostrarTiempoEjecucion(bubbleSort.getRunTimeMs());
        bubbleSort.muestra(20);
        System.out.println("");
        
        // -----------------------------------------------------------------------
        // Shell Sort
        // -----------------------------------------------------------------------
        System.out.println(sectionBreak);
        System.out.println("Shell Sort");
        System.out.println(sectionBreak);

        Ordenamiento shellSort = new Ordenamiento(bubbleSort.unsortedNums);
        System.out.println(lineBreak);
        System.out.println("Datos no ordenados:");
        shellSort.muestra(shellSort.unsortedNums, 20);

        System.out.println(lineBreak);
        shellSort.shellSort();
        
        System.out.println("Datos Ordenados (Shell Sort):");
        shellSort.muestra(20);

        compararOrdenamiento(shellSort, bubbleSort);


        mostrarTiempoEjecucion(shellSort.getRunTimeMs());
        shellSort.muestra(20);
        System.out.println("");

        // -----------------------------------------------------------------------
        // Bucket Sort
        // -----------------------------------------------------------------------
        System.out.println(sectionBreak);
        System.out.println("Bucket Sort");
        System.out.println(sectionBreak);

        Ordenamiento bucketSort = new Ordenamiento(bubbleSort.unsortedNums);
        System.out.println(lineBreak);
        System.out.println("Datos no ordenados:");
        bubbleSort.muestra(bucketSort.unsortedNums, 20);

        System.out.println(lineBreak);
        

        bucketSort.bucketSort();
        System.out.println("\n"
                + "Datos Ordenados (Bucket Sort):");
        bubbleSort.muestra(20);
        compararOrdenamiento(bucketSort, bubbleSort);
        
        System.out.println("");
        mostrarTiempoEjecucion(bucketSort.getRunTimeMs());
        bucketSort.muestra(20);
        System.out.println("");
        
        
    

        

        // -----------------------------------------------------------------------
        // Prueba de Quick Sort
        // -----------------------------------------------------------------------
        System.out.println(sectionBreak);
        System.out.println("Quick Sort");
        System.out.println(sectionBreak);

        Ordenamiento quicksort = new Ordenamiento(bucketSort.unsortedNums);
        System.out.println(lineBreak);
        System.out.println("Datos no ordenados:");
        shellSort.muestra(quicksort.unsortedNums, 20);

        System.out.println(lineBreak);
        System.out.println("Datos Ordenados (Quick Sort):");

        quicksort.quickSort();


        compararOrdenamiento(quicksort, bucketSort);

        mostrarTiempoEjecucion(quicksort.getRunTimeMs());
        quicksort.muestra(20);
        
        // -----------------------------------------------------------------------
        // Prueba de Busquedas
        //-----------------------------------------------------------------------
        System.out.println(sectionBreak);
        System.out.println("Prueba de Busquedas");
        System.out.println(sectionBreak);
        
        probarBusquedas(insertionSort);

    }
    public static void compararOrdenamiento(Ordenamiento algo1, Ordenamiento algo2){
        String algoHash1 = algo1.getSortHash();
        String algoHash2 = algo2.getSortHash();
        if(algoHash1 == null){
            JOptionPane.showMessageDialog(null,
                    "algo1 todavia no esta ordenado"
                    );
            return;
        }
        if(algoHash2 == null){
            JOptionPane.showMessageDialog(null,
                    "algo2 todavia no esta ordenado"
                    );
            return;
        }
        if(algoHash1.equals(algoHash2)){
            JOptionPane.showMessageDialog(null,
                    "El arreglo ordenado usando "
                + algo1.getSortAlgoUsed() + ""
                + "  es igual al arreglo ordenado por "
                + algo2.getSortAlgoUsed()
                    );
            return;
        }

        JOptionPane.showMessageDialog(null,
                "El arreglo ordenado despues de aplicar "
                + algo1.getSortAlgoUsed() + ""
                + " no es igual a el del algoritmo "
                + algo2.getSortAlgoUsed()
                );
       
    }

    public static void mostrarHora() {
        String lineBreak = "-------------------------------------------------";
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        Ordenamiento testCarga = new Ordenamiento(100);

        System.out.println(lineBreak);
        // El siguiente sout imprime la hora en pantalla
        System.out.println("Hora actual: " + dateFormat.format(date));
        System.out.println(lineBreak);
    }

    public static void mostrarTiempoEjecucionSegundos(long runtimeMs) {
        double runtimeSegundos = (double) runtimeMs / 1000;
        int decimalPlaces = 6;
        DecimalFormat df = new DecimalFormat("#." + new String(new char[decimalPlaces]).replace("\0", "#"));
        String formattedNumber = df.format(runtimeSegundos);
        System.out.println("Tiempo de ejecucion: " + formattedNumber + " segundos");
    }

    public static void mostrarTiempoEjecucion(String intervalo, long runtimeMs) {
        switch (intervalo) {
            case "segundos":
                mostrarTiempoEjecucionSegundos(runtimeMs);
                break;
            case "ms":
                System.out.println("Tiempo de ejecucion: " + runtimeMs + "ms");
                break;
            default:
                throw new AssertionError();
        }
    }

    public static void mostrarTiempoEjecucion(Long runtimeMs) {
        mostrarTiempoEjecucion("ms", runtimeMs);
    }
    public static void probarBusquedas(Ordenamiento ordenamiento){
        Scanner leer = new Scanner(System.in);
        String input;
        char opcion = 'D';
        do{
            System.out.println("\n"
                    + "Tipo de buqueda?");
            System.out.println("\t[b]inaria");
            System.out.println("\t[l]ineal");
            System.out.println("\t[q]uit");
            System.out.println("\t[m]ostrar arreglo ordenado");
            System.out.print("busqueda>> ");
            input = leer.nextLine();
            if(!input.equals("")){
                opcion = input.toUpperCase().charAt(0);
            }
            switch (opcion) {
                case 'B':
                    buscar(ordenamiento, "binaria");
                    break;
                case 'L':
                    buscar(ordenamiento, "lineal");
                case 'M':
                    ordenamiento.muestra(20);
                    break;
                case 'Q':
                    input = "quit";
                    return;
            }
        }while (!input.equals("quit"));
    }
    public static void buscar(Ordenamiento ordenamiento,
                                          String tipoBusqueda) {
        Scanner leer = new Scanner(System.in);
        String input;
        Integer busqueda;
        // Integer index;
        Integer resultado[] = null;
        do {
            System.out.print("\n"
                    + "Que numero queres buscar? (quit para salir): ");
            input = leer.nextLine();
            if (input.equalsIgnoreCase("quit")) {
                break;
            }
            try {
                busqueda = Integer.valueOf(input);
                if(tipoBusqueda.equals("binaria")){
                    resultado = ordenamiento.busquedaBinaria(busqueda);
                }else if(tipoBusqueda.equals("lineal")){
                    resultado =  ordenamiento.busquedaLineal(busqueda);
                }
                
                if (resultado != null) {
                    System.out.print("\t"
                            + "Se encontro " + busqueda + " en la "
                            + "posicion " + resultado[0] + " del arreglo.");
                    if (resultado.length == 2){
                        System.out.print(" Se extiende hasta la posicion "
                                         + resultado[1]);
                    }
                }else{
                    System.out.println("No se encontro el numero buscado");
                }
                
                System.out.println("");
            } catch (NumberFormatException e) {
                System.out.println("\n"
                        + "Solo se puede buscar numeros enteros."
                        + "\n");
            }

        } while (!input.equals("quit"));
    }
}
