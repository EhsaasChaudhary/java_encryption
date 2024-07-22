import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * A class to generate multiple files with random names and save different HashMaps in each file.
 */
public class filegenerator {

    public static void main(String[] args) {
        // Define the directory path where files will be saved
        String directoryPath = "C:\\Ehsaas_college\\java_encription\\logicfiles";
        File directory = new File(directoryPath);
        
        // Create a Scanner object for user input
        Scanner sc = new Scanner(System.in);
        
        // Prompt user for the number of files to generate
        System.out.println("Enter the number of files you want to generate: ");
        int numOfFiles = sc.nextInt();
        
        // Prompt user for the length of the filenames
        System.out.println("Enter the length of the filename: ");
        int filenameLength = sc.nextInt();
        
        // Prompt user for the length of the random code
        System.out.println("Enter the length of the code: ");
        int codeLength = sc.nextInt();
        
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
        
        // Close the Scanner to release resources
        sc.close();
    }

    /**
     * Generates a random string of specified length using alphanumeric characters and symbols.
     * 
     * @param length Length of the random string
     * @return Randomly generated string
     */
    private static String generateRandomCode(int codelength) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < codelength; i++) {
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
    private static String generateRandomFilename(int filenamelength) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < filenamelength; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }

    /**
     * Generates a HashMap where each key is a character and each value is a randomly generated string of specified length.
     * 
     * @param codeLength Length of the random code to be used as the value in the HashMap
     * @return Newly created HashMap with random values
     */
    private static HashMap<Character, String> generateRandomHashMap(int codeLength) {
        HashMap<Character, String> map = new HashMap<>();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";

        // Populate the HashMap with each character as the key and a random code as the value
        for (char c : characters.toCharArray()) {
            map.put(c, generateRandomCode(codeLength));
        }

        return map;
    }

    /**
     * Saves the given HashMap to the specified file. Each key-value pair in the HashMap is written to the file.
     * 
     * @param file The file to which the HashMap will be saved
     * @param map The HashMap to be saved
     */
    private static void saveHashMapToFile(File file, HashMap<Character, String> map) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Write each key-value pair to the file
            for (HashMap.Entry<Character, String> entry : map.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
            System.out.println("Saved HashMap to file: " + file.getPath());
        } catch (IOException e) {
            // Print error message if file writing fails
            System.err.println("An error occurred while writing to file: " + file.getPath());
            e.printStackTrace();
        }
    }
}
