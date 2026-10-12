import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

    }

    static List<Employee> employees = List.of(
            new Employee("Əli", 25, "IT", new BigDecimal("3000")),
            new Employee("Aysel", 32, "HR", new BigDecimal("2500")),
            new Employee("Murad", 35, "IT", new BigDecimal("4500")),
            new Employee("Leyla", 28, "Finance", new BigDecimal("3500")),
            new Employee("Nigar", 40, "Marketing", new BigDecimal("5000"))
    );

    public static void task1() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        List<Integer> squaredNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .toList();

        System.out.println(squaredNumbers);
    }

    public static void task2() {
        List<String> programmingLanguages = List.of(
                "Java", "Rust", "C", "JavaScript",
                "Swift", "Kotlin", "Go", "C++"
        );

        List<String> sortedLanguages = programmingLanguages.stream()
                .filter(language -> language.length() > 3)
                .distinct()
                .sorted((a, b) -> b.length() - a.length())
                .limit(3)
                .toList();

        System.out.println(sortedLanguages);
    }

    public static void task3() {
        Optional<Employee> maxSalary = employees.stream()
                .max(Comparator.comparing(Employee::getSalary));

        BigDecimal averageSalary = employees.stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        averageSalary = averageSalary.divide(
                BigDecimal.valueOf(employees.size())
        );

        BigDecimal totalSalary = employees.stream()
                .filter(employee -> employee.getAge() > 30)
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static void task4() {
        Map<String, List<Employee>> departments = employees.stream()
                .collect(Collectors.toMap(
                        Employee::getDepartment,
                        employee -> new ArrayList<>(List.of(employee)),
                        (list1, list2) -> {
                            list1.addAll(list2);
                            return list1;
                        }
                ));

        departments.forEach((department, employeeList) ->
                System.out.println(department + " : " + employeeList));

        Map<String, Long> employeeCounts = employees.stream()
                .collect(Collectors.toMap(
                        Employee::getDepartment,
                        employee -> 1L,
                        Long::sum
                ));


        employeeCounts.forEach((department, count) ->
                System.out.println(department + " : " + count));

        Map<String, BigDecimal> totalSalaries = employees.stream()
                .collect(Collectors.toMap(
                        Employee::getDepartment,
                        Employee::getSalary,
                        BigDecimal::add
                ));

        Map<String, BigDecimal> averageSalaries = totalSalaries.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().divide(
                                BigDecimal.valueOf(employeeCounts.get(entry.getKey()))
                        )
                ));

        averageSalaries.forEach((department, salary) ->
                System.out.println(department + " : " + salary));
    }

    public static void task5() {

        List<String> emails = List.of(
                "Ibrahim@gmail.com",
                "Ibrahim@yahoo.com",
                "ibrahim@gmail.com",
                "Ibrahim123@gmail.com",
                "IBRAHIM@GMAIL.COM",
                "ibrahim123@gmail.com",
                "Ibrahim@hotmail.com"
        );

        long count = emails.stream()
                .map(String::toLowerCase)
                .filter(email -> email.endsWith("@gmail.com"))
                .distinct()
                .count();

        System.out.println("Unique: " + count);
    }

    public static void task6() {

        List<String> students = List.of("Aysel", "Orxan", "Leyla");

        students.stream()
                .map(name -> "Student: " + name)
                .forEach(System.out::println);
    }

    public static void task7() {
        List<Integer> numbers = List.of(5, 12, 8, 15, 20);

        numbers.stream()
                .filter(number -> number > 10)
                .map(number -> "Number: " + number)
                .forEach(System.out::println);
    }

    public static void task8() {
        Map<String, Integer> grades = Map.of(
                "Riyaziyyat", 90,
                "Fizika", 85,
                "İnformatika", 95
        );

        grades.forEach((key, value) ->
                System.out.println(key + " : " + value)
        );
    }

}