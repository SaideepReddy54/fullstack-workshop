import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class LogAnalyzerTest {

    public static void main(String[] args) throws Exception {

        LogAnalyzer analyzer = new LogAnalyzer();

        List<LogEntry> logs = analyzer.readLogs(Path.of("io-concurrency/logs/app.log"));

        Map<String, Long> counts = analyzer.countByLevel(logs);

        System.out.println("Counts by level: " + counts);

        List<LogEntry> errors = analyzer.getErrors(logs);

        System.out.println("\nERROR logs:");
        errors.forEach(System.out::println);

        analyzer.writeSummary(
                Path.of("io-concurrency/logs/summary.txt"),
                logs);

        System.out.println("\nSummary written to summary.txt");
    }
}
