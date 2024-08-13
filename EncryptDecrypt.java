import java.util.Scanner;

public class EncryptDecrypt {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int operation = 0; 

        System.out.println("1 for Encryption");
        System.out.println("2 for Decryption");
        System.out.println("3 to End Execution");

        while (operation != 3) {

            System.out.print("Enter the Operation code: ");
            operation = sc.nextInt();
            sc.nextLine();

            switch (operation) {
                case 1:
                    PasswordEncryption.main(args);
                    break;
                case 2:
                    PasswordDecryption.main(args);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid Operation Code. Please enter a valid number.");
                    break;
            }
        }
        System.out.println("Ending execution.");
        sc.close();
    }
}
