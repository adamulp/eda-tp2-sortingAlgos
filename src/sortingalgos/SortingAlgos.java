package sortingalgos;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

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

        // mostrarHora();
        insertionSort.empezarCronometro();
        insertionSort.insertionSort();
        insertionSort.pararCronometro();
        insertionSort.setSortHash();

        insertionSort.muestra(20);
        // mostrarHora();
        mostrarTiempoEjecucion(insertionSort.getRunTimeMs());
        System.out.println("");

        // -----------------------------------------------------------------------
        // Prueba de Bubble Sort
        // -----------------------------------------------------------------------
        System.out.println(sectionBreak);
        System.out.println("Bubble Sort");
        System.out.println(sectionBreak);

        Ordenamiento bubbleSort = new Ordenamiento(insertionSort.unsortedNums);
        // Ordenamiento bubbleSort = new Ordenamiento(10);
        // bubbleSort.cargar();
        System.out.println(lineBreak);
        System.out.println("Datos no ordenados:");
        bubbleSort.muestra(bubbleSort.unsortedNums, 20);

        System.out.println(lineBreak);
        System.out.println("Datos Ordenados (Bubble Sort):");

        // mostrarHora();
        bubbleSort.empezarCronometro();
        bubbleSort.bubbleSort();
        bubbleSort.pararCronometro();
        bubbleSort.setSortHash();

        System.out.println("bubbleSort hash:\t" + bubbleSort.getSortHash());
        System.out.println("insertionS hash:\t" + insertionSort.getSortHash());
        if (bubbleSort.getSortHash().equals(insertionSort.getSortHash())) {
            System.out.println("Bubble sort tiene el mismo resultado que"
                    + " insertion sort!");
        } else {
            System.out.println("Bubble sort e insertion sort están dando"
                    + " resultados distintos");
        }

        // bubbleSort.muestra(20);
        // mostrarHora();
        mostrarTiempoEjecucion(bubbleSort.getRunTimeMs());
        bubbleSort.muestra(20);
        System.out.println("");

        // System.out.println("Prueba de numString: ");
        // System.out.println(bubbleSort);
        // System.out.println("Prueba de sortHash: ");
        // System.out.println(bubbleSort.getSortHash());

        // buscarBinariamente(bubbleSort);

        // -----------------------------------------------------------------------
        // Prueba de Shell Sort
        // -----------------------------------------------------------------------
        System.out.println(sectionBreak);
        System.out.println("Bubble Sort");
        System.out.println(sectionBreak);

        Ordenamiento shellSort = new Ordenamiento(bubbleSort.unsortedNums);
        System.out.println(lineBreak);
        System.out.println("Datos no ordenados:");
        shellSort.muestra(bubbleSort.unsortedNums, 20);

        System.out.println(lineBreak);
        System.out.println("Datos Ordenados (Shell Sort):");

        // mostrarHora();
        shellSort.empezarCronometro();
        shellSort.bubbleSort();
        shellSort.pararCronometro();
        shellSort.setSortHash();

        System.out.println("bubbleSort hash:\t" + bubbleSort.getSortHash());
        System.out.println("shellSort  hash:\t" + shellSort.getSortHash());
        if (bubbleSort.getSortHash().equals(shellSort.getSortHash())) {
            System.out.println("Shell sort tiene el mismo resultado que"
                    + " Bubble sort!");
        } else {
            System.out.println("Shell sort y Bubble sort están dando"
                    + " resultados distintos");
        }

        // bubbleSort.muestra(20);
        // mostrarHora();
        mostrarTiempoEjecucion(shellSort.getRunTimeMs());
        shellSort.muestra(20);
        System.out.println("");

        // -----------------------------------------------------------------------
        // Prueba de Bucket Sort
        // -----------------------------------------------------------------------
        System.out.println(sectionBreak);
        System.out.println("Bucket Sort");
        System.out.println(sectionBreak);

        Ordenamiento bucketSort = new Ordenamiento(shellSort.unsortedNums);
        System.out.println(lineBreak);
        System.out.println("Datos no ordenados:");
        shellSort.muestra(bucketSort.unsortedNums, 20);

        System.out.println(lineBreak);
        System.out.println("Datos Ordenados (Bucket Sort):");

        // mostrarHora();
        bucketSort.empezarCronometro();
        bucketSort.bubbleSort();
        bucketSort.pararCronometro();
        bucketSort.setSortHash();

        System.out.println("bucketSort hash:\t" + bucketSort.getSortHash());
        System.out.println("shellSort  hash:\t" + shellSort.getSortHash());
        if (bucketSort.getSortHash().equals(shellSort.getSortHash())) {
            System.out.println("Bucket sort tiene el mismo resultado que"
                    + " Shell sort!");
        } else {
            System.out.println("Bucket sort y Shell sort están dando"
                    + " resultados distintos");
        }

        // bubbleSort.muestra(20);
//        mostrarHora();
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

        // mostrarHora();
        quicksort.empezarCronometro();
        quicksort.bubbleSort();
        quicksort.pararCronometro();
        quicksort.setSortHash();

        System.out.println("bucketSort hash:\t" + bucketSort.getSortHash());
        System.out.println("quicksort  hash:\t" + quicksort.getSortHash());
        if (bucketSort.getSortHash().equals(quicksort.getSortHash())) {
            System.out.println("Quick sort tiene el mismo resultado que"
                    + " bucket sort!");
        } else {
            System.out.println("Bucket sort y quick sort están dando"
                    + " resultados distintos");
        }

        // bubbleSort.muestra(20);
        // mostrarHora();
        mostrarTiempoEjecucion(quicksort.getRunTimeMs());
        quicksort.muestra(20);
        
        // -----------------------------------------------------------------------
        // Prueba de Busquedas
        //-----------------------------------------------------------------------
        System.out.println(sectionBreak);
        System.out.println("Prueba de Busquedas");
        System.out.println(sectionBreak);
        
        probarBusquedas(quicksort);

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
        Integer index;
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
                    index = ordenamiento.busquedaBinaria(busqueda);
                }else if(tipoBusqueda.equals("lineal")){
                    index = ordenamiento.busquedaLineal(busqueda);
                }else{
                    index = null;
                }
                if (index != null) {
                    System.out.println("\t"
                            + "Se encontro " + busqueda + " en la "
                            + "posicion " + index + " del arreglo."
                            + "\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n"
                        + "Solo se puede buscar numeros enteros."
                        + "\n");
            }

        } while (!input.equals("quit"));
    }
}
