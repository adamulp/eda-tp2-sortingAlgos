package sortingalgos;

/**
 * https://github.com/adamulp/sortingAlgos/
 *
 * @authors:
 *           Alexis Allendez        https://github.com/AlexisAllendez
 *           Enzo Casalegno         https://github.com/EnzoCasalegno
 *           Nicolás Hollmann       https://github.com/HollmannCod3
 *           Adam Rigg              https://github.com/adamulp/
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
    private final static int ARREGLO_TAMANIO = 10000;
    protected int unsortedNums[];
    protected int sortedNums[];

    public Ordenamiento(int[] unsortedNums) {
        this.unsortedNums = unsortedNums;
        this.sortedNums = null;
    }

    public Ordenamiento() {
        this(new int[ARREGLO_TAMANIO]);
    }

    public void cargar() {
        int i = 0;
        for (int num : unsortedNums) {
            unsortedNums[i] = (int) (Math.random() * 10001);
            i++;
        }
    }
    
    public static boolean esVacio(int nums[]){
        if(nums==null){
            System.out.println("El arreglo está vacío.");
            return true;
        }
        return false;
    }

    public static void muestra(int nums[], int anchoPantalla) {
        if(esVacio(nums)){
            return;
        }
        int i = 0;
        for (int num : nums) {
            while (i <= anchoPantalla) {
                System.out.print(num + ",\t");
                i++;
            }
            i = 0;
            System.out.print("\n");
        }
    }

    public void muestra(int anchoPantalla) {
        muestra(this.sortedNums, anchoPantalla);
    }
    
    public static void intercambiar(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public static int[] insertionSort(int nums[]){
        if(esVacio(nums)){
            return null;
        }
        
        int aux;
        int j;
        for(int i=1; i <= (nums.length -1); i++){
            aux = nums[i];
            j=i;
            while(j > 0 && nums[j-1] > aux){
                intercambiar(nums, j, (j-1));
                j--;
            }
        }
        return nums;
    }
    /*
     * public static native void arraycopy(Object src, int srcPos,
     * Object dest, int destPos,
     * int length);
     */
    public void insertionSort() {
        this.sortedNums = new int[this.unsortedNums.length];
        System.arraycopy(unsortedNums, 0, sortedNums, 0, unsortedNums.length);
        sortedNums = insertionSort(unsortedNums);
    }
    public void bubbleSort(int []nums){
        int k,j,temp;
        int tamanio = nums.length;
        for ( k = (tamanio - 1); k >= 0; k--) {
            for ( j = 1; j <= k; j++) {
                if (nums[j-1] > nums[j]) {
                    temp = nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

}
