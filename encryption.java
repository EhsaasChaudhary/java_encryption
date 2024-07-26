import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class encryption {
    public static void main(String[] args) {

        int defaultNumOfFiles = 5;        // Default number of files
        int defaultFilenameLength = 4;    // Default length of filenames
        int defaultCodeLength = 3;        // Default length of random codes

        // Hardcoded folder path
        String folderPath = "C:\\Ehsaas_college\\java_encription\\logicfiles";
        
        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
        
        if (files.length<5) {
            filegenerator.main(new String[] { 
                String.valueOf(defaultNumOfFiles), 
                String.valueOf(defaultFilenameLength), 
                String.valueOf(defaultCodeLength) 
            });
        }
        File[] myfiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
        
        if (myfiles != null && myfiles.length > 0) {
            Random random = new Random();
            File randomFile = myfiles[random.nextInt(myfiles.length)];
            
            try {
                String content = new String(Files.readAllBytes(Paths.get(randomFile.getAbsolutePath())));
                
                // Print the selected file name and content
                System.out.println("Randomly selected file: " + randomFile.getName());
                // System.out.println("Content:\n" + content);
                
                // Convert content to HashMap
                Map<String, String> map = parseContentToMap(content);
                
                // Print the HashMap
                System.out.println("Parsed HashMap:");
                System.out.println(map);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No .txt files found in the specified folder.");
        }
    }
    
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
