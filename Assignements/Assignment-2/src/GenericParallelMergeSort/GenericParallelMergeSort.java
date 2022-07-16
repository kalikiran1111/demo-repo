package GenericParallelMergeSort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class GenericParallelMergeSort {
    public static void main(String[] args) throws IOException {
        Integer[] integerList = new Integer[1000];

        for (int i = 0; i < integerList.length; i++) {
            integerList[i] = (int)(Math.random() * 1000);
        }
        System.out.println("*************************************************************************************************************************");
        System.out.println("Unsorted Integer Array :");
        System.out.println(Arrays.toString(integerList));

        long startTime = System.currentTimeMillis();
        parallelMergeSort(integerList); // Invoke parallel merge sort
        System.out.println();
        System.out.println("Sorted Integer Array :");
        System.out.println(Arrays.toString(integerList));
        long endTime = System.currentTimeMillis();
        System.out.println("\nParallel time for sorting Integer arrays with "
                + Runtime.getRuntime().availableProcessors() +
                " processors is " + (endTime - startTime) + " milliseconds");
        System.out.println("*************************************************************************************************************************");

        String[] stringArray = new String[1000];
        Path fileName
                = Path.of("src/GenericParallelMergeSort/TextFile.txt");
        String str = Files.readString(fileName);
        String result = str.replaceAll("\\p{Punct}", "");
        stringArray = result.split(" ");
        System.out.println();
        System.out.println("Unsorted String Array :");
        System.out.println(Arrays.toString(stringArray));
        System.out.println();
        long startTimeForStr = System.currentTimeMillis();
        parallelMergeSort(stringArray); // Invoke parallel merge sort
        System.out.println("----------------------------------------------------------------------------");
        System.out.println();
        System.out.println("Sorted Integer Array :");
        System.out.println(Arrays.toString(stringArray));
        System.out.println();
        long endTimeForStr = System.currentTimeMillis();
        System.out.println("\nParallel time for sorting String arrays with "
                + Runtime.getRuntime().availableProcessors() +
                " processors is " + (endTimeForStr - startTimeForStr) + " milliseconds");
        System.out.println("*************************************************************************************************************************");


    }

    public static <E extends Comparable<E>> void parallelMergeSort(E[] list) {
        RecursiveAction mainTask = new SortTask(list);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mainTask);
    }

    private static class SortTask<E extends Comparable<E>> extends RecursiveAction {
        private final int THRESHOLD = 500;
        private E[] list;

        SortTask(E[] list) {
            this.list = list;
        }

        @Override
        protected void compute() {
            if (list.length < THRESHOLD)
                java.util.Arrays.sort(list);
            else {
                // Obtain the first half
                E[] firstHalf = (E[])new Comparable[list.length / 2];
                System.arraycopy(list, 0, firstHalf, 0, list.length / 2);

                // Obtain the second half
                int secondHalfLength = list.length - list.length / 2;
                E[] secondHalf = (E[])new Comparable[secondHalfLength];
                System.arraycopy(list, list.length / 2,
                        secondHalf, 0, secondHalfLength);

                // Recursively sort the two halves
                invokeAll(new SortTask(firstHalf),
                        new SortTask(secondHalf));

                // Merge firstHalf with secondHalf into list
                MergeSort.merge(firstHalf, secondHalf, list);
            }
        }
    }
}
