import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class MaxElementInAnArray {

    public static <E extends Comparable<E>> E max(E[] list) {
        Arrays.sort(list);
        System.out.println(Arrays.toString(list));
        PriorityQueue<E> priorityQueue = new PriorityQueue<E>(
                (a, b) -> {
                    if (a.compareTo(b) < 0) {return +1;}
                    if (a.compareTo(b) > 0) {return -1;}
                    return 0;
                }
        );
        Collections.addAll(priorityQueue, list);
        return (E) priorityQueue.poll();
    }

    public static void main(String[] args) {
        Integer[] intArray = new Integer[100];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = (int) ((Math.random() * (10000 - 0)) + 0);
        }
        System.out.println("***********************************************");
        System.out.println("Contents of the Integer Array in the Ascending Order :");
        int maxValInIntegerArray = max(intArray);
        System.out.println("Max value in the Integer Array is : " + maxValInIntegerArray);
        System.out.println("***********************************************");
        Double[] doubleArray = new Double[100];
        for (int i = 0; i < doubleArray.length; i++) {
            doubleArray[i] = ((Math.random() * ((1.0 - 0.0))) + 0.0);
        }
        System.out.println("Contents of the Double Array in the Ascending Order :");
        double maxValInDoubleArray = max(doubleArray);
        System.out.println("Max value in the Double Array is : " + maxValInDoubleArray);
        System.out.println("***********************************************");

    }
}
