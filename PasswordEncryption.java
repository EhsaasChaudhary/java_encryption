import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class PasswordEncryption {

    private static final String FOLDER_PATH = "C:\\Ehsaas_college\\java_encription\\logicfiles";

    public static void main(String[] args) {
        int filenameLength = 4;
        int defaultmaxCodeLength = 10;
        int defaultminCodeLength = 3;

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter the password to be encrypted: ");
            String password = sc.nextLine();

            if (password.isEmpty()) {
                System.out.println("Password cannot be empty.");
                return;
            }

            String defaultFilename = generateRandomFilename(filenameLength) + ".txt";
            generateFiles(defaultFilename, defaultmaxCodeLength, defaultminCodeLength, password);

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
    }


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


    private static void generateFiles(String filename, int maxcodeLength, int mincodeLength, String password) {
        FileGenerator.main(new String[]{
            filename,
            String.valueOf(maxcodeLength),
            String.valueOf(mincodeLength),
            password
        });
    }


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
