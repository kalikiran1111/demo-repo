package BinarySearch;

public class Employee implements Comparable<Employee>{
    String firstName;
    String lastName;
    Double salary;

    Employee(String firstName, String lastName, Double salary) {
        this.firstName = firstName.toLowerCase();
        this.lastName = lastName.toLowerCase();
        this.salary = salary;
    }

    Employee(String firstName, String lastName) {
        this.firstName = firstName.toLowerCase();
        this.lastName = lastName.toLowerCase();
        this.salary = 0.00;
    }

    @Override
    public int compareTo(Employee employee) {
        if (lastName.compareTo(employee.lastName) == 0) {
            return firstName.compareTo(employee.firstName);
        }
        return lastName.compareTo(employee.lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "{firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                '}';
    }
}
