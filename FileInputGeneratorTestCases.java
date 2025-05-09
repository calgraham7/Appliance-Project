
import java.io.*;

public class FileInputGeneratorTestCases {
    public static void main(String[] args) {
        log("--- FileInputGenerator Tests ---");

        // Default location count check
        log("Default Location Count: " + FileInputGenerator.LOCATION_COUNT);

        // Set custom location count (via reflection or if setter added)
        // Simulate running the generator and check for file
        try {
            FileInputGenerator.main(new String[] {});
            File output = new File("output.txt");
            log("Output file exists: " + output.exists());
        } catch (IOException e) {
            logException(e);
        }
    }

    private static void log(String message) {
        System.out.println(message);
    }

    private static void logException(Exception e) {
        try (FileWriter fw = new FileWriter("log.txt", true)) {
            fw.write("[FileInputGenerator] " + e.getMessage() + "\n");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}