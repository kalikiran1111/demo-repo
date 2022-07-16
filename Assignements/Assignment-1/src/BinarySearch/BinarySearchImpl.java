package BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

public class BinarySearchImpl {

    public static <E extends Comparable<E>> int binarySearch (E[] list, E key) {
        Arrays.sort(list);
        int low = 0;
        int high = list.length - 1;

        while (low <= high) {
            // Accounting for overflow, as int can hold value until a specific limit.
            int mid = low + (high - low) / 2;
            if (list[mid].compareTo(key) == 0) {
                return 1;
            } else if (list[mid].compareTo(key) < 0) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        Integer[] intArray = new Integer[100];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = (int) ((Math.random() * (100 - 0)) + 0);
        }
        System.out.println("***********************************************");
        System.out.println("Contents of the Integer Array :");
        System.out.println(Arrays.toString(intArray));
        System.out.println("Please enter a value(Integer) : ");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        int intBinarySearchResult = binarySearch(intArray, input);
        if (intBinarySearchResult == 1) {
            System.out.println("Provided Input " + input +" is found in the Randomly generated Integer Array");
        } else {
            System.out.println("Provided Input " + input + " is not found in the Randomly generated Integer Array");
        }
        System.out.println("***********************************************");

        String[] strArray = new String[] {"Lorem", "Ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit", "sed", "do"};
        System.out.println("Contents of the String Array :");
        System.out.println(Arrays.toString(strArray));
        System.out.println("Please enter a value(String) - Choose a word from the following text : ");
        System.out.println("\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do\"");
        String strInput = scanner.next();
        strArray= Arrays.stream(strArray).map(String::toLowerCase).toArray(String[]::new);
        int strBinarySearchResult = binarySearch(strArray, strInput.toLowerCase());
        if (strBinarySearchResult == 1) {
            System.out.println("Provided Input "+ strInput +" is found in the String Array");
        } else {
            System.out.println("Provided Input "+ strInput +" is not found in the String Array");
        }
        System.out.println("***********************************************");


        Employee employee1 = new Employee("Luke", "Skywalker", 10000.00);
        Employee employee2 = new Employee("Anakin", "Skywalker", 30000.00);
        Employee employee3 = new Employee("Darth", "Vader", 130000.00);
        Employee employee4 = new Employee("Han", "Solo", 15000.00);
        Employee employee5 = new Employee("Leia", "Organa", 150000.00);
        Employee employee6 = new Employee("Anakin", "Skywalker", 9000.00);

        Employee[] employees = new Employee[] {employee1, employee2, employee3, employee4, employee5, employee6};
        int count = 1;
        System.out.println("Here are the employees :");
        for (Employee employee : employees) {
            System.out.println( "Employee "+ count + " : " + employee.toString());
            count++;
        }

        System.out.println("Please Enter the First Name of your desired employee : ");
        String fName = scanner.next();
        System.out.println("Please Enter the Last Name of your desired employee : ");
        String lName = scanner.next();
        Employee searchKeyEmp = new Employee(fName, lName);

        int empBinarySearchResult = binarySearch(employees, searchKeyEmp);
        if (empBinarySearchResult == 1) {
            System.out.println("Provided Employee "+ searchKeyEmp.getFirstName() +" "+ searchKeyEmp.getLastName() + " " + "is found in the String Array");
        } else {
            System.out.println("Provided Employee "+ searchKeyEmp.getFirstName() +" "+ searchKeyEmp.getLastName() + " " + "is not found in the String Array");
        }
        scanner.close();
        System.out.println("***********************************************");


    }
}
