package sortingalgos;

import java.util.ArrayList;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * https://github.com/adamulp/sortingAlgos/
 *
 * @authors: Alexis Allendez https://github.com/AlexisAllendez Enzo Casalegno
 *           https://github.com/EnzoCasalegno Nicolás Hollmann
 *           https://github.com/HollmannCod3 Adam Rigg
 *           https://github.com/adamulp/
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
public class Ordenamiento {

    private int numElementos;
    protected int unsortedNums[];
    protected int sortedNums[];
    private String sortHash;
    private String sortAlgoUsed;
    private Long runTimeMs;
    private Long horaComienzo;
    private Long horaFinal;

    @Override
    public String toString() {
        if (sortedNums == null) {
            return "";
        }
        String numString = "";
        for (int num : sortedNums) {
            numString += " " + num + ";";
        }
        return numString;
    }

    public String getSortHash() {
        if (this.sortHash == null) {
            setSortHash();
        }
        return sortHash;
    }

    public String getSortAlgoUsed() {
        return sortAlgoUsed;
    }
 
    // Usar un SHA-256 para comparar entre resultados, como hacen con
    // las descargas de cosas.
    public void setSortHash() {
        if (this.sortedNums == null) {
            System.out.println("El arreglo no esta ordenado");
            return;
        }
        String sortedNumsStr = toString();
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest digesto = MessageDigest.getInstance("SHA-256");
            digesto.update(sortedNumsStr.getBytes());
            byte[] hashBytes = digesto.digest();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

        } catch (NoSuchAlgorithmException e) {
            // Handle NoSuchAlgorithmException
            e.printStackTrace();
        }

        this.sortHash = hexString.toString();
    }

    public Ordenamiento(int[] unsortedNums) {
        this.unsortedNums = unsortedNums;
        this.sortedNums = null;
        this.numElementos = unsortedNums.length;
        
        this.sortHash = null;
        this.sortAlgoUsed = null;
        this.runTimeMs = null;
        this.horaComienzo = null;
        this.horaFinal = null;
    }

    public Ordenamiento(int numElementos) {
        this(new int[numElementos]);
    }

    public void cargar() {
        int i = 0;
        // System.out.println("Size of unsortedNums=" + unsortedNums.length);
        for (int num : unsortedNums) {
            unsortedNums[i] = (int) (Math.random() * 5);
            i++;
        }
    }

    public void empezarCronometro() {
        this.horaFinal = null;
        this.runTimeMs = null;
        this.horaComienzo = System.currentTimeMillis();
    }

    public void pararCronometro() {
        if (horaComienzo != null) {
            this.horaFinal = System.currentTimeMillis();
            runTimeMs = horaFinal - horaComienzo;
            return;
        }
        System.out.println("Aun no comienza el cronometro");

    }

    public Long getRunTimeMs() {
        return runTimeMs;
    }

    public Long getHoraComienzo() {
        return horaComienzo;
    }

    public Long getHoraFinal() {
        return horaFinal;
    }

    public boolean estaOrdenado() {
        return this.sortedNums != null;
    }

    // buscarFrontera devuelve el indice de la ultima coincidencia
    // que se encuentra con el elemento ubicado en el indice del inicio.

    // Un paso negativo quiere decir buscar hacia la izquierda.
    public Integer buscarFrontera(int inicio, int paso) {
        Integer fin = 0;
        Integer i = null;
        if (paso > 0) {
            fin = sortedNums.length - 1;
        }

        for (i = inicio; i != fin; i += paso) {
            if (sortedNums[inicio] != sortedNums[i]) {
                return i - paso;
            }
        }
        if(Objects.equals(i, fin)){
            return fin;
        }
        return inicio;
    }

    public Integer[] busquedaBinaria(int num) {
        Integer[] resultado;
        if (estaVacio(this.unsortedNums)) {
            System.out.println("No se cargo el arreglo");
            return null;
        }
        if (this.sortedNums == null) {
            System.out.println("El arreglo no esta ordenado");
            return null;
        }

        int izq = 0;
        int der = sortedNums.length - 1;
        int cur;
        while (izq <= der) {
            cur = (izq + der) / 2;
            if (sortedNums[cur] == num) {
                resultado = new Integer[]{
                                        buscarFrontera(cur, -1), 
                                        buscarFrontera(cur, 1)
                                    };
                if(Objects.equals(resultado[0], resultado[1])){
                    return new Integer[]{cur};
                }
                return resultado;
              
            }
            if (sortedNums[cur] < num) {
                izq = cur + 1;
            } else {
                der = cur - 1;
            }
        }
        return null;
    }

    public Integer[] busquedaLineal(int num) {
        Integer[] resultado;
        int j;
        if (estaVacio(this.unsortedNums)) {
            System.out.println("No se cargo el arreglo");
            return null;
        }
        if (this.sortedNums == null) {
            System.out.println("El arreglo no esta ordenado");
            return null;
        }

        for (int i = 0; i < sortedNums.length; i++) {
            if (num == sortedNums[i]) {
                j = buscarFrontera(i, 1);
                if(i == j){
                    return new Integer[]{i};
                }
                return new Integer[]{i, j};
            }
        }
//        System.out.println("No se encontro " + num);
        return null;
    }

    public static boolean estaVacio(int nums[]) {
        if (nums == null) {
            System.out.println("El arreglo está vacío.");
            return true;
        }
        return false;
    }

    public static void muestra(int nums[], int anchoPantalla) {
        if (estaVacio(nums)) {
            return;
        }
        int i;
        for (i = 0; i < nums.length; i++) {
            while (i <= anchoPantalla && i < nums.length) {
                System.out.print(nums[i] + ",\t");
                i++;
            }
            System.out.print("\n");
        }
    }

    public void muestra(int anchoPantalla) {
        muestra(this.sortedNums, anchoPantalla);
    }

    private static void intercambiar(int[] nums, int i, int j) {
        try {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        } catch (ArrayIndexOutOfBoundsException obe) {
            System.out.println("No se puede cambiar el elemento "
                    + i + " con el elemento " + j);
        }
    }

    public static void insertionSort(int nums[]) {
        int aux;
        int j;
        for (int i = 1; i <= (nums.length - 1); i++) {
            aux = nums[i];
            j = i;
            while (j > 0 && nums[j - 1] > aux) {
                intercambiar(nums, j, (j - 1));
                j--;
            }
        }
        // return nums;
    }

    /*
     * public static native void arraycopy(Object src, int srcPos,
     * Object dest, int destPos,
     * int length);
     */
    public void insertionSort() {
        this.sortedNums = new int[this.unsortedNums.length];
        System.arraycopy(unsortedNums, 0, sortedNums, 0, unsortedNums.length);
        
        empezarCronometro();
        insertionSort(sortedNums);
        pararCronometro();
        
        setSortHash();
        this.sortAlgoUsed = "Insertion Sort";
    }

    public void bubbleSort(int[] nums) {
        if (estaVacio(nums)) {
            System.out.println("El arreglo esta vacio");
            return;
        }

        if (nums.length <= 1) {
            return;
        }

        for (int i = (nums.length - 1); i >= 0; i--) {
            for (int j = 1; j <= i; j++) {
                if (nums[j - 1] > nums[j]) {
                    intercambiar(nums, (j - 1), j);
                }
            }
        }
    }

    public void bubbleSort() {
        this.sortedNums = new int[this.unsortedNums.length];
        System.arraycopy(unsortedNums, 0, sortedNums, 0, unsortedNums.length);
        
        empezarCronometro();
        bubbleSort(sortedNums);
        pararCronometro();
        
        setSortHash();
        this.sortAlgoUsed = "Bubble Sort";
    }

    private static int particion(int[] nums, int primerElemento, int ultimoElemento) {
        int pivote = nums[primerElemento];
        return particion(nums, primerElemento, ultimoElemento, pivote);
    }

    private static int particion(int[] nums, int primerElemento, int ultimoElemento, int pivote) {
        int cotaInferior = primerElemento + 1;
        int cotaSuperior = ultimoElemento;

        while (cotaSuperior > cotaInferior) {
            while (cotaInferior <= cotaSuperior && nums[cotaInferior] <= pivote) {
                cotaInferior++;
            }
            while (cotaInferior <= cotaSuperior && nums[cotaSuperior] > pivote) {
                cotaSuperior--;
            }
            if (cotaSuperior > cotaInferior) {
                intercambiar(nums, nums[cotaSuperior], nums[cotaInferior]);
            }
        }

        while (cotaSuperior > primerElemento && nums[cotaSuperior] >= pivote) {
            cotaSuperior--;
        }

        if (pivote > nums[cotaSuperior]) {
            nums[primerElemento] = nums[cotaSuperior];
            nums[cotaSuperior] = pivote;
            return cotaSuperior;
        } else {
            return primerElemento;
        }
    }

    public static void quickSort(int[] nums, int primerElemento, int ultimoElemento) {
        if (ultimoElemento > primerElemento) {
            int pivote = particion(nums, primerElemento, ultimoElemento);
            quickSort(nums, primerElemento, pivote - 1);
            quickSort(nums, pivote + 1, ultimoElemento);
        }
    }

    public static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }
    // https://github.com/apinkney97/Sorts/tree/master

    /*
     * public static native void arraycopy(Object src, int srcPos,
     * Object dest, int destPos,
     * int length);
     */
    public void quickSort() {
        this.sortedNums = new int[this.unsortedNums.length];
        System.arraycopy(unsortedNums, 0, sortedNums, 0, unsortedNums.length);
        
        empezarCronometro();
        quickSort(sortedNums);
        pararCronometro();
        
        setSortHash();
        this.sortAlgoUsed = "Quick Sort";
    }

    /*
     * # Sort an array a[0...n-1].
     * gaps = [701, 301, 132, 57, 23, 10, 4, 1] # Ciura gap sequence
     * 
     * # Start with the largest gap and work down to a gap of 1
     * # similar to insertion sort but instead of 1, gap is being used in each step
     * foreach (gap in gaps)
     * {
     * # Do a gapped insertion sort for every elements in gaps
     * # Each loop leaves a[0..gap-1] in gapped order
     * for (i = gap; i < n; i += 1)
     * {
     * # save a[i] in temp and make a hole at position i
     * temp = a[i]
     * # shift earlier gap-sorted elements up until the correct location for a[i] is
     * found
     * for (j = i; (j >= gap) && (a[j - gap] > temp); j -= gap)
     * {
     * a[j] = a[j - gap]
     * }
     * # put temp (the original a[i]) in its correct location
     * a[j] = temp
     * }
     * }
     * # https://en.wikipedia.org/wiki/Shellsort
     */

    public void shellSort(int nums[]) {
        int aux;
        List<Integer> saltos = new ArrayList<>();
        int saltoBinario = nums.length / 2;
        while (saltoBinario > 0) {
            saltos.add(saltoBinario);
            saltoBinario = saltoBinario / 2;
        }

        for (int salto : saltos) {
            for (int i = salto; i < nums.length; i++) {
                int j;
                aux = nums[i];
                for (j = i; (j >= salto) && (nums[j - salto] > aux); j -= salto) {
                    nums[j] = nums[j - salto];
                }
                nums[j] = aux;
            }

        }
    }
    
    public void shellSort() {
        this.sortedNums = new int[this.unsortedNums.length];
        System.arraycopy(unsortedNums, 0, sortedNums, 0, unsortedNums.length);
        
        empezarCronometro();
        shellSort(sortedNums);
        pararCronometro();
        
        setSortHash();
        this.sortAlgoUsed = "Shell Sort";
    }

    private static int generarBucketIndex(int num, int maxValor, int numDeTarros) {
        double razonNumMax = (double) num / maxValor;
        return (int) (razonNumMax * (numDeTarros - 1));
    }

    private static int findMax(int nums[]) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        return max;
    }

    public static void insertionSort(List<Integer> buckets) {
        for (int i = 1; i < buckets.size(); ++i) {
            int llave = buckets.get(i);
            int j = i - 1;
            while (j >= 0 && buckets.get(j) > llave) {
                buckets.set(j + 1, buckets.get(j));
                j--;
            }
            buckets.set(j + 1, llave);
        }
    }

    // Bucket Sort (ordenamiento de tarros)
    public static void bucketSort(int[] nums) {
        // Si el algoritmo tiene complejidad temporal en el peor caso de
        // O(n^2), no rinde tener más tarros que la raiz cuadrada del tamanio.
        // https://en.wikipedia.org/wiki/Bucket_sort
        int numTarros = (int) Math.sqrt(nums.length);

        // 1) Crear n tarros vacios (empty buckets)
        List<Integer>[] buckets = new ArrayList[numTarros];
        for (int i = 0; i < numTarros; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Colocar los elementos del arreglo en tarros distintos
        int maxValor = findMax(nums);
        for (int num : nums) {
            buckets[generarBucketIndex(num, maxValor, numTarros)].add(nums[num]);
        }

        // 3) Ordenar tarros individuales utilizando insertion sort
        for (int i = 0; i < numTarros; i++) {
            insertionSort(buckets[i]);
        }

        // 4) Concatenar todos los tarros en un solo arreglo
        int index = 0;
        for (int i = 0; i < numTarros; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                nums[index++] = buckets[i].get(j);
            }
        }
    }
    // https://www.geeksforgeeks.org/bucket-sort-2/
    // https://www.baeldung.com/java-bucket-sort
    // https://introcs.cs.princeton.edu/java/11precedence/

     public void bucketSort() {
        this.sortedNums = new int[this.unsortedNums.length];
        System.arraycopy(unsortedNums, 0, sortedNums, 0, unsortedNums.length);
        
        empezarCronometro();
        bucketSort(sortedNums);
        pararCronometro();
        
        setSortHash();
        this.sortAlgoUsed = "Bucket Sort";
    }
    
}
