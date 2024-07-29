import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class encryption {
    public static void main(String[] args) {

        // Default configuration values
        int defaultNumOfFiles = 5; // Default number of files to generate
        int defaultFilenameLength = 4; // Default length of filenames
        int defaultCodeLength = 3; // Default length of random codes
        String encryptedPassword = ""; // To store the final encrypted password
        Map<String, String> map = new HashMap<>(); // To store the key-value pairs from the file

        // Scanner to take user input
        Scanner sc = new Scanner(System.in);

        // Prompt user to enter a password to be encrypted
        System.out.println("");
        System.out.print("Enter the password to be encrypted: ");
        String password = sc.nextLine();
        System.out.println("");

        // Hardcoded folder path where the files are stored
        String folderPath = "C:\\Ehsaas_college\\java_encription\\logicfiles";

        // Load the files from the specified folder
        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        // If there are fewer than 5 files, generate more files using filegenerator
        if (files.length < 5) {
            filegenerator.main(new String[] {
                    String.valueOf(defaultNumOfFiles),
                    String.valueOf(defaultFilenameLength),
                    String.valueOf(defaultCodeLength)
            });
        }
        
        // Reload the files after generating more files, if needed
        File[] myfiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        // Check if there are any .txt files in the folder
        if (myfiles != null && myfiles.length > 0) {
            Random random = new Random();
            // Select a random file from the list of files
            File randomFile = myfiles[random.nextInt(myfiles.length)];

            try {
                // Read the content of the selected file
                String content = new String(Files.readAllBytes(Paths.get(randomFile.getAbsolutePath())));

                // Print the selected file name
                System.out.println("Randomly selected file: \n" + randomFile.getName());
                System.out.println("");

                // Convert the file content to a HashMap
                map = parseContentToMap(content);

                // Print the parsed HashMap
                System.out.println("Parsed HashMap:\n");
                System.out.println(map  + "\n");

                // Encrypt the password using the HashMap
                for (int i = 0; i < password.length(); i++) {
                    String charStr = String.valueOf(password.charAt(i));
                    if (map.containsKey(charStr)) {
                        encryptedPassword += map.get(charStr);
                    }
                }

                // Add the chosen file name without the .txt extension to the encrypted password
                String fileNameWithoutExtension = randomFile.getName().replaceFirst("[.][^.]+$", "");
                encryptedPassword += fileNameWithoutExtension;

                // Print the final encrypted password
                System.out.println("Encrypted Password: " + encryptedPassword + "\n");

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No .txt files found in the specified folder.");
        }

        // Close the scanner to avoid resource leaks
        sc.close();
    }

    /**
     * Parses the content of the file into a HashMap.
     * Each line in the file is split into a key-value pair.
     *
     * @param content The content of the file as a string.
     * @return A HashMap containing the key-value pairs from the file.
     */
    private static Map<String, String> parseContentToMap(String content) {
        Map<String, String> map = new HashMap<>();
        String[] lines = content.split("\n");

        for (String line : lines) {
            // Split each line into key and value
            String[] parts = line.split(":", 2);
            if (parts.length == 2) {
                String key = parts[0];
                String value = parts[1].trim();
                map.put(key, value);
            }
        }

        return map;
    }
}
