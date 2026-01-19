import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class LogAnalyzer {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public List<LogEntry> readLogs(Path file) {
        try {
            return Files.lines(file)
                    .map(this::parseLine)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private LogEntry parseLine(String line) {
        // Expected format:
        // 2024-01-15 10:30:45 INFO User login: alice@email.com
        try {
            String date = line.substring(0, 10);
            String time = line.substring(11, 19);
            String level = line.substring(20, line.indexOf(' ', 21));
            String message = line.substring(line.indexOf(level) + level.length()).trim();

            LocalDateTime timestamp = LocalDateTime.parse(date + " " + time, FORMATTER);

            return new LogEntry(timestamp, level, message);
        } catch (Exception e) {
            System.out.println("Failed to parse line: " + line);
            return null;
        }
    }

    public Map<String, Long> countByLevel(List<LogEntry> logs) {
        return logs.stream()
                .collect(Collectors.groupingBy(LogEntry::level, Collectors.counting()));
    }

    public List<LogEntry> getErrors(List<LogEntry> logs) {
        return logs.stream()
                .filter(e -> e.level().equalsIgnoreCase("ERROR"))
                .collect(Collectors.toList());
    }

    public void writeSummary(Path output, List<LogEntry> logs) {
        Map<String, Long> counts = countByLevel(logs);
        List<LogEntry> errors = getErrors(logs);

        List<String> summary = new ArrayList<>();
        summary.add("LOG SUMMARY");
        summary.add("============");
        summary.add("Total Entries: " + logs.size());
        summary.add("");
        summary.add("Count by Level:");
        counts.forEach((k, v) -> summary.add(k + ": " + v));
        summary.add("");
        summary.add("Errors:");
        errors.forEach(e -> summary.add(e.timestamp() + " - " + e.message()));

        try {
            Files.write(output, summary);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
