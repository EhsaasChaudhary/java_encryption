import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class Encryption {

    private static final String FOLDER_PATH = "C:\\Ehsaas_college\\java_encription\\logicfiles";

    public static void main(String[] args) {
        int defaultNumOfFiles = 5;
        int defaultFilenameLength = 4;
        int defaultCodeLength = 5;

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter the password to be encrypted: ");
            String password = sc.nextLine();

            if (password.isEmpty()) {
                System.out.println("Password cannot be empty.");
                return;
            }

            File folder = new File(FOLDER_PATH);
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

            if (files == null || files.length < defaultNumOfFiles) {
                FileGenerator.main(new String[]{
                        String.valueOf(defaultNumOfFiles),
                        String.valueOf(defaultFilenameLength),
                        String.valueOf(defaultCodeLength)
                });
                files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
            }

            Optional<File> randomFile = Optional.ofNullable(files)
                    .filter(f -> f.length > 0)
                    .map(f -> f[new Random().nextInt(f.length)]);

            if (randomFile.isPresent()) {
                try {
                    String content = Files.readString(Paths.get(randomFile.get().getAbsolutePath()));
                    Map<String, String> map = parseContentToMap(content);

                    StringBuilder encryptedPassword = new StringBuilder();
                    for (char c : password.toCharArray()) {
                        String charStr = String.valueOf(c);
                        encryptedPassword.append(map.getOrDefault(charStr, charStr));
                    }

                    String fileNameWithoutExtension = randomFile.get().getName().replaceFirst("[.][^.]+$", "");
                    encryptedPassword.append(fileNameWithoutExtension);

                    System.out.println("Encrypted Password: " + encryptedPassword);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("No .txt files found in the specified folder.");
            }
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
