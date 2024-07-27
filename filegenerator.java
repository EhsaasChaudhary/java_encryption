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
public class filegenerator {

    public static void main(String[] args) {
        // Default values
        int numOfFiles = 5;        // Default number of files
        int filenameLength = 4;    // Default length of filenames
        int codeLength = 3;        // Default length of random codes

        // Check if arguments are provided and valid
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

        // Define the directory path where files will be saved
        String directoryPath = "C:\\Ehsaas_college\\java_encription\\logicfiles";
        File directory = new File(directoryPath);

        // Ensure the directory exists, create it if it does not
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory if it doesn't exist
        }

        // Generate the specified number of files with random names
        // and save different HashMaps to each file
        for (int i = 0; i < numOfFiles; i++) {
            // Generate a new HashMap with random codes
            HashMap<Character, String> map = generateRandomHashMap(codeLength);

            // Generate a random filename of specified length
            String fileName = generateRandomFilename(filenameLength) + ".txt";
            File file = new File(directory, fileName);

            // Save the HashMap to the file
            saveHashMapToFile(file, map);
        }
    }

    /**
     * Generates a random string of specified length using alphanumeric characters
     * and symbols.
     * 
     * @param length Length of the random string
     * @return Randomly generated string
     */
    private static String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        // String characters = "ABC";
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }

    /**
     * Generates a random filename of specified length using alphanumeric characters.
     * 
     * @param length Length of the filename
     * @return Randomly generated filename
     */
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

    /**
     * Generates a HashMap where each key is a character and each value is a
     * randomly generated string of specified length.
     * 
     * @param codeLength Length of the random code to be used as the value in the HashMap
     * @return Newly created HashMap with random values
     */
    private static HashMap<Character, String> generateRandomHashMap(int codeLength) {
        HashMap<Character, String> map = new HashMap<>();
        String characters = " ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";

        // Populate the HashMap with each character as the key and a random code as the value
        for (char c : characters.toCharArray()) {
            map.put(c, generateRandomCode(codeLength));
        }

        return map;
    }

    /**
     * Saves the given HashMap to the specified file. Each key-value pair in the
     * HashMap is written to the file.
     * 
     * @param file The file to which the HashMap will be saved
     * @param map  The HashMap to be saved
     */
    private static void saveHashMapToFile(File file, HashMap<Character, String> map) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (HashMap.Entry<Character, String> entry : map.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
            System.out.println("Saved HashMap to file: " + file.getPath());
        } catch (IOException e) {
            System.err.println("An error occurred while writing to file: " + file.getPath());
            e.printStackTrace();
        }
    }
}
