import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

/**
 * A class to generate multiple files with random names and save different
 * HashMaps in each file.
 */
public class FileGenerator {

    public static void main(String[] args) {
        // Default values
        int numOfFiles = 5;
        int filenameLength = 4;
        int codeLength = 5;

        if (args.length >= 3) {
            try {
                numOfFiles = Integer.parseInt(args[0]);
                filenameLength = Integer.parseInt(args[1]);
                codeLength = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format in arguments. Using default values.");
            }
        } else {
            System.out.println("Insufficient arguments provided. Using default values.");
        }

        String directoryPath = "C:\\Ehsaas_college\\java_encription\\logicfiles";
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Generate files with random names and save different HashMaps
        for (int i = 0; i < numOfFiles; i++) {
            HashMap<Character, String> map = generateRandomHashMap(codeLength);

            String fileName = generateRandomFilename(filenameLength) + ".txt";
            File file = new File(directory, fileName);

            saveHashMapToFile(file, map);
        }
    }

    // Generates a random string of specified length using alphanumeric
    // charactersand symbols.
    private static String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";

        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }

    // Generates a random filename.

    private static String generateRandomFilename(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }
        
        return code.toString();
    }
    
    // Generates a HashMap where each key is a character and each value is a
    // randomly generated string of specified length.
    
    private static HashMap<Character, String> generateRandomHashMap(int codeLength) {
        HashMap<Character, String> map = new HashMap<>();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";

        for (char c : characters.toCharArray()) {
                map.put(c, generateRandomCode(codeLength));
        }

        return map;
    }

    // Saves the given HashMap to the specified file. Each key-value pair in the
    // HashMap is written to the file.

    private static void saveHashMapToFile(File file, HashMap<Character, String> map) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (HashMap.Entry<Character, String> entry : map.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
            // System.out.println("Saved HashMap to file: " + file.getPath());
        } catch (IOException e) {
            System.err.println("An error occurred while writing to file: " + file.getPath());
            e.printStackTrace();
        }
    }
}
