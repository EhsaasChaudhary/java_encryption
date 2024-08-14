import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Decryption {

    public static void main(String[] args) {
        int defaultFilenameLength = 4;
        String encryptedString = "";
        String FOLDER_PATH = "";

        if (args.length >= 2) {
            try {
                encryptedString = args[0];
                FOLDER_PATH = args[1];
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format in arguments. Using default values.");
            }
        } else {
            System.out.println("Insufficient arguments provided. Using default values.");
        }

        if (encryptedString.isEmpty()) {
            System.out.println("Encrypted string cannot be empty.");
            return;
        }

        String targetFileName = encryptedString.substring(encryptedString.length() - defaultFilenameLength) + ".txt";
        File targetFile = new File(FOLDER_PATH, targetFileName);

        if (targetFile.exists() && targetFile.isFile()) {
            try {
                String content = Files.readString(Paths.get(targetFile.getAbsolutePath()));
                Map<String, String> map = parseContentToMap(content);

                String password = decryptEncryptedString(
                        encryptedString.substring(0, encryptedString.length() - defaultFilenameLength), map);

                System.out.println("Original Password: " + password);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("The file " + targetFileName + " does not exist in the specified folder.");
        }
    }

    private static Map<String, String> parseContentToMap(String content) {
        Map<String, String> map = new HashMap<>();
        String[] lines = content.split("\n");

        for (String line : lines) {
            String[] parts = line.split(":", 2);
            if (parts.length == 2) {
                map.put(parts[1].trim(), parts[0].trim());
            }
        }
        return map;
    }

    private static String decryptEncryptedString(String encryptedString, Map<String, String> map) {
        StringBuilder password = new StringBuilder();

        while (!encryptedString.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                if (encryptedString.startsWith(key)) {
                    password.append(entry.getValue()); // Append the matched value to the result
                    encryptedString = encryptedString.substring(key.length()); // Remove the matched substring
                    break; // Break the loop to start again with the modified string
                }
            }
        }
        return password.toString();
    }
}
