import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class LambdaRefactor {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>(List.of("Alice", "Bob", "Christopher"));
        List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));

        // ORIGINAL (Anonymous class)
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });

        // REFACTORED (Lambda)
        names.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));

        // REFACTORED (Method reference)
        names.sort(Comparator.comparingInt(String::length));

        // ORIGINAL (Loop)
        List<Integer> evens = new ArrayList<>();
        for (Integer n : numbers) {
            if (n % 2 == 0) {
                evens.add(n);
            }
        }

        // REFACTORED (Lambda + Predicate)
        List<Integer> evensLambda = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        // ORIGINAL (Loop)
        for (String s : names) {
            System.out.println(s);
        }

        // REFACTORED (Method reference + Consumer)
        names.forEach(System.out::println);

        // ORIGINAL (Anonymous Runnable)
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Running");
            }
        });
        t1.start();

        // REFACTORED (Lambda)
        Thread t2 = new Thread(() -> System.out.println("Running"));
        t2.start();

        // ORIGINAL (Loop)
        List<String> upper = new ArrayList<>();
        for (String s : names) {
            upper.add(s.toUpperCase());
        }

        // REFACTORED (Lambda + Function)
        List<String> upperLambda = names.stream()
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());

        // REFACTORED (Method reference)
        List<String> upperMethodRef = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }
}
