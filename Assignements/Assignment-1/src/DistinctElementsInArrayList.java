import java.util.*;

public class DistinctElementsInArrayList{
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        System.out.println("Size of the List : " + list.size() );
        String formattedList = String.join(", ", list.toString());
        System.out.println("Array List with duplicates : " + formattedList);
        ArrayList<E> finalList = new ArrayList<>(
                new HashSet<>(list));
        return finalList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> intArrayList = new ArrayList<>();
        for (int i = 0; i <= 99; i++) {
            intArrayList.add((int) ((Math.random() * (10 - 0)) + 0));
        }
        ArrayList<Integer> finalIntArrayList = removeDuplicates(intArrayList);
        System.out.println("Final Integer Array list without duplicates : " + finalIntArrayList);

        Random rnd = new Random();
        ArrayList<Character> charArrayList = new ArrayList<>();
        for (int i = 0; i <= 99; i++) {
            charArrayList.add((char) ('a' + rnd.nextInt(26)));
        }
        ArrayList<Character> finalCharList = removeDuplicates(charArrayList);
        System.out.println("Final Character Array list without duplicates : " + finalCharList);
    }
}
