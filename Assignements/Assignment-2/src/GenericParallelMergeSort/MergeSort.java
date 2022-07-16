package GenericParallelMergeSort;

public class MergeSort {
  /** The method for sorting the numbers */
  public static <E extends Comparable<E>> void parallelMergeSort(E[] list) {
    if (list.length > 1) {
      // Merge sort the first half
      E[] firstHalf = (E[]) new Comparable[list.length / 2];
      System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
      parallelMergeSort(firstHalf);

      // Merge sort the second half
      int secondHalfLength = list.length - list.length / 2;
      E[] secondHalf = (E[]) new Comparable[secondHalfLength];
      System.arraycopy(list, list.length / 2,
        secondHalf, 0, secondHalfLength);
      parallelMergeSort(secondHalf);

      // Merge firstHalf with secondHalf into list
      merge(firstHalf, secondHalf, list);
    }
  }

  /** Merge two sorted lists */
  public static <E extends Comparable<E>> void merge(E[] list1, E[] list2, E[] temp) {
    int current1 = 0; // Current index in list1
    int current2 = 0; // Current index in list2
    int current3 = 0; // Current index in temp

    while (current1 < list1.length && current2 < list2.length) {
      if (list1[current1].compareTo(list2[current2]) < 0) {
        temp[current3++] = list1[current1++];
      } else{
        temp[current3++] = list2[current2++];
      }
    }

    while (current1 < list1.length)
      temp[current3++] = list1[current1++];

    while (current2 < list2.length)
      temp[current3++] = list2[current2++];
  }

}
