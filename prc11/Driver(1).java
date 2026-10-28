
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;
import java.util.List;

public class Driver {
    public static void main(String[] args) throws IOException {
        Path folder = Paths.get("sample_logs");
        Files.createDirectories(folder);

        // Create sample log files
        Files.write(folder.resolve("log1.txt"), List.of(
            "INFO: Application started",
            "ERROR: File not found",
            "INFO: Application running"
        ));

        Files.write(folder.resolve("log2.txt"), List.of(
            "INFO: User logged in",
            "ERROR: Connection failed"
        ));

        Files.write(folder.resolve("log3.txt"), List.of(
            "INFO: Task completed",
            "INFO: Application closed"
        ));

        String keyword = "ERROR";
        int totalLines = 0;
        int matchingLines = 0;

        try (DirectoryStream<Path> files =
                Files.newDirectoryStream(folder, "*.txt")) {

            for (Path file : files) {
                BasicFileAttributes attr =
                    Files.readAttributes(
                        file, BasicFileAttributes.class);

                int fileLines = 0;

                for (String line : Files.readAllLines(file)) {
                    totalLines++;
                    fileLines++;

                    if (line.contains(keyword)) {
                        matchingLines++;
                    }
                }

                System.out.println("File: " + file.getFileName());
                System.out.println("Size: " + attr.size() + " bytes");
                System.out.println("Lines: " + fileLines);
                System.out.println();
            }
        }

        System.out.println("Total lines: " + totalLines);
        System.out.println("Lines containing " + keyword + ": "
                + matchingLines);
    }
}
