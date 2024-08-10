import java.util.InputMismatchException;
import java.util.Scanner;

public class EncryptDecrypt {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
            int operation = 0; // Initialize with a default value

            while (operation != 3) {
                printMenu();
                try {
                    operation = sc.nextInt();
                    sc.nextLine(); // Consume the newline character

                    if (operation >= 1 && operation <= 3) {
                        executeOperation(operation, args);
                    } else {
                        System.out.println("Invalid Operation Code. Please enter a valid number.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    sc.nextLine(); // Clear the invalid input
                }
            }
            System.out.println("Ending execution.");
            sc.close();
    }

    private static void printMenu() {
        System.out.println("1 for Encryption");
        System.out.println("2 for Decryption");
        System.out.println("3 to End Execution\n");
        System.out.print("Enter the Operation code: ");
    }

    private static void executeOperation(int operation, String[] args) {
        switch (operation) {
            case 1:
                Encryption.main(args);
                break;
            case 2:
                Decryption.main(args);
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
