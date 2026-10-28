
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;
import java.io.PrintWriter;

public class Driver {
    public static void main(String[] args) throws IOException {
        Path folder = Paths.get("sample_folder");
        Files.createDirectories(folder.resolve("documents"));
        Files.createDirectories(folder.resolve("images"));

        // Create sample files
        Files.writeString(
            folder.resolve("notes.txt"), "Java NIO practical");

        Files.writeString(
            folder.resolve("documents/report.txt"),
            "Sample report");

        Files.writeString(
            folder.resolve("images/info.txt"),
            "Image folder example");

        Path report = Paths.get("folder_report.txt");

        try (PrintWriter writer =
                new PrintWriter(Files.newBufferedWriter(report))) {

            writer.println("FOLDER TREE REPORT");
            writer.println("==================");

            Files.walkFileTree(folder,
                new SimpleFileVisitor<Path>() {

                    @Override
                    public FileVisitResult visitFile(
                            Path file, BasicFileAttributes attrs) {

                        writer.println("Name: "
                                + folder.relativize(file));
                        writer.println("Size: "
                                + attrs.size() + " bytes");
                        writer.println("Modified: "
                                + attrs.lastModifiedTime());
                        writer.println();

                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(
                            Path file, IOException exc) {
                        writer.println("Could not access: " + file);
                        return FileVisitResult.CONTINUE;
                    }
                });
        }

        System.out.println("Folder report created successfully.");
        System.out.println("Report saved to: "
                + report.toAbsolutePath());

        System.out.println("\nReport contents:");

        for (String line : Files.readAllLines(report)) {
            System.out.println(line);
        }
    }
}
