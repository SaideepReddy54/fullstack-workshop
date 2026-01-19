import java.util.*;
import java.util.stream.Collectors;

public class StreamExercise {

    // Employee record (Java 16+)
    record Employee(
            Long id,
            String name,
            String department,
            double salary,
            int yearsOfService,
            List<String> skills) {
    }

    // Data setup
    static List<Employee> employees = List.of(
            new Employee(1L, "Alice", "Engineering", 85000, 5, List.of("Java", "Python")),
            new Employee(2L, "Bob", "Engineering", 75000, 3, List.of("Java", "JavaScript")),
            new Employee(3L, "Charlie", "Sales", 65000, 7, List.of("Communication", "CRM")),
            new Employee(4L, "Diana", "Engineering", 95000, 8, List.of("Java", "Kotlin", "Go")),
            new Employee(5L, "Eve", "HR", 55000, 2, List.of("Recruiting", "Communication")),
            new Employee(6L, "Frank", "Sales", 70000, 4, List.of("Negotiation", "CRM")));

    public static void main(String[] args) {

        System.out.println("1. Engineering sorted by salary: " +
                getEngineeringSortedBySalary());

        System.out.println("2. Names uppercase: " +
                getNamesUppercase());

        System.out.println("3. Grouped by department: " +
                groupByDepartment());

        System.out.println("4. Statistics: " +
                salaryStatistics());

        System.out.println("5. Unique skills: " +
                getUniqueSkills());

        System.out.println("6. Partitioned by salary > 70000: " +
                partitionBySalary());

        System.out.println("7. Total years of service: " +
                totalYearsOfService() + " years");

        System.out.println("8. High paying departments: " +
                departmentsWithHighAverageSalary());
    }

    // 1. Filter & Sort
    static List<String> getEngineeringSortedBySalary() {
        return employees.stream()
                .filter(e -> e.department().equals("Engineering"))
                .sorted(Comparator.comparingDouble(Employee::salary).reversed())
                .map(Employee::name)
                .toList();
    }

    // 2. Map & Collect
    static List<String> getNamesUppercase() {
        return employees.stream()
                .map(e -> e.name().toUpperCase())
                .toList();
    }

    // 3. Grouping
    static Map<String, List<String>> groupByDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.mapping(Employee::name, Collectors.toList())));
    }

    // 4. Statistics
    static Map<String, Object> salaryStatistics() {

        double totalSalary = employees.stream()
                .mapToDouble(Employee::salary)
                .sum();

        Map<String, Double> avgByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.averagingDouble(Employee::salary)));

        Optional<Employee> highestPaid = employees.stream()
                .max(Comparator.comparingDouble(Employee::salary));

        Map<String, Object> result = new HashMap<>();
        result.put("Total", totalSalary);
        result.put("AvgByDept", avgByDept);
        result.put("HighestPaid", highestPaid.map(Employee::name).orElse("N/A"));

        return result;
    }

    // 5. FlatMap
    static Set<String> getUniqueSkills() {
        return employees.stream()
                .flatMap(e -> e.skills().stream())
                .collect(Collectors.toSet());
    }

    // 6. Partitioning
    static Map<Boolean, List<String>> partitionBySalary() {
        return employees.stream()
                .collect(Collectors.partitioningBy(
                        e -> e.salary() > 70000,
                        Collectors.mapping(Employee::name, Collectors.toList())));
    }

    // 7. Reduce
    static int totalYearsOfService() {
        return employees.stream()
                .map(Employee::yearsOfService)
                .reduce(0, Integer::sum);
    }

    // 8. Complex query
    static List<String> departmentsWithHighAverageSalary() {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.averagingDouble(Employee::salary)))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 70000)
                .map(Map.Entry::getKey)
                .toList();
    }
}
