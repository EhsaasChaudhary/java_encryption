import java.util.Scanner;

public class EncryptDecrypt {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int operation = 0; // Initialize with a default value

        System.out.println("1 for Encryption");
        System.out.println("2 for Decryption");
        System.out.println("3 to End Execution\n");

        while (operation != 3) {

            System.out.print("Enter the Operation code: ");
        
            operation = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            if (operation >= 1 && operation <= 3) {
                executeOperation(operation, args);
            } else {
                System.out.println("Invalid Operation Code. Please enter a valid number.");
            }
        }
        System.out.println("Ending execution.");
        sc.close();
    }

    private static void executeOperation(int operation, String[] args) {
        switch (operation) {
            case 1:
                PasswordEncryption.main(args);
                break;
            case 2:
                PasswordDecryption.main(args);
                break;
            case 3:
                // No action needed here, loop will exit
                break;
            default:
                System.out.println("Invalid Operation Code");
                break;
        }
    }
}
