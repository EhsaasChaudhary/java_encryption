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
        int defaultNumOfFiles = 5; 
        int defaultFilenameLength = 4; 
        int defaultMinCodeLength = 3; 
        int defaultMaxCodeLength = 10; 
        
        String encryptedPassword = "";
        Map<String, String> map = new HashMap<>(); 

        Scanner sc = new Scanner(System.in);

        System.out.println("");
        System.out.print("Enter the password to be encrypted: ");
        String password = sc.nextLine();
        System.out.println("");

        String folderPath = "C:\\Ehsaas_college\\java_encription\\logicfiles";

        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        if (files.length < 5) {
            filegenerator.main(new String[] {
                    String.valueOf(defaultNumOfFiles),
                    String.valueOf(defaultFilenameLength),
                    String.valueOf(defaultMinCodeLength),
                    String.valueOf(defaultMaxCodeLength)
            });
        }
        
        File[] myfiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        if (myfiles != null && myfiles.length > 0) {
            Random random = new Random();
            File randomFile = myfiles[random.nextInt(myfiles.length)];

            try {
                String content = new String(Files.readAllBytes(Paths.get(randomFile.getAbsolutePath())));

                System.out.println("Randomly selected file: \n" + randomFile.getName());
                System.out.println("");

                map = parseContentToMap(content);

                System.out.println("Parsed HashMap:\n");
                System.out.println(map  + "\n");

                for (int i = 0; i < password.length(); i++) {
                    String charStr = String.valueOf(password.charAt(i));
                    if (map.containsKey(charStr)) {
                        encryptedPassword += map.get(charStr);
                    }
                }

                String fileNameWithoutExtension = randomFile.getName().replaceFirst("[.][^.]+$", "");
                encryptedPassword += fileNameWithoutExtension;

                System.out.println("Encrypted Password: " + encryptedPassword + "\n");

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No .txt files found in the specified folder.");
        }

        sc.close();
    }

    //Parses the content of the file into a Map.
    
    private static Map<String, String> parseContentToMap(String content) {
        Map<String, String> map = new HashMap<>();
        String[] lines = content.split("\n");

        for (String line : lines) {
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
