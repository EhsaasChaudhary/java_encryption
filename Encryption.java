import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Encryption {

    // private static final String FOLDER_PATH = "C:\\Ehsaas_college\\java_encription\\logicfiles";

    public static void main(String[] args) {
        // default values
        int filenameLength = 4;
        int defaultmaxCodeLength = 10;
        int defaultminCodeLength = 3;
        String FOLDER_PATH = "";
        String password = "";


        if (args.length >= 2) {
            try {
                password = args[0];
                FOLDER_PATH = args[1];
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format in arguments. Using default values.");
            }
        } else {
            System.out.println("Insufficient arguments provided. Using default values.");
        }

        if (password.isEmpty()) {
            System.out.println("Password cannot be empty.");
            return;
        }

        String defaultFilename = generateRandomFilename(filenameLength) + ".txt";
        generateFiles(defaultFilename, defaultmaxCodeLength, defaultminCodeLength, password,FOLDER_PATH);

        File targetFile = new File(FOLDER_PATH, defaultFilename);
        if (targetFile.exists() && targetFile.isFile()) {
            String encryptedPassword = encryptPassword(targetFile, password);
            if (encryptedPassword != null) {
                System.out.println("Encrypted Password: " + encryptedPassword);
            } else {
                System.out.println("Error during encryption.");
            }
        } else {
            System.out.println("No .txt files found in the specified folder.");
        }
    }

    // Generate random file name of specific length using the alphanumeric
    // characters

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

    // Generate the file and hashmap containing the ecnryption logic by calling
    // generateFiles method

    private static void generateFiles(String filename, int maxcodeLength, int mincodeLength, String password, String folderpath) {
        FileGenerator.main(new String[] {
                filename,
                String.valueOf(maxcodeLength),
                String.valueOf(mincodeLength),
                password,
                folderpath
        });
    }

    // Encrypt the password provided by the user

    private static String encryptPassword(File file, String password) {
        try {
            String content = Files.readString(Paths.get(file.getAbsolutePath()));
            Map<String, String> map = parseContentToMap(content);

            StringBuilder encryptedPassword = new StringBuilder();
            for (char c : password.toCharArray()) {
                String charStr = String.valueOf(c);
                encryptedPassword.append(map.getOrDefault(charStr, charStr));
            }

            String fileNameWithoutExtension = file.getName().replaceFirst("[.][^.]+$", "");
            encryptedPassword.append(fileNameWithoutExtension);

            return encryptedPassword.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Parse the content of the file to a map

    private static Map<String, String> parseContentToMap(String content) {
        Map<String, String> map = new HashMap<>();
        String[] lines = content.split("\n");

        for (String line : lines) {
            String[] parts = line.split(":", 2);
            if (parts.length == 2) {
                map.put(parts[0].trim(), parts[1].trim());
            }
        }

        return map;
    }
}
