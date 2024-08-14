import java.util.Scanner;

public class EncryptDecrypt {

    public static void main(String[] args) {

        String FOLDER_PATH = "C:\\Ehsaas_college\\java_encription\\logicfiles";

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

                    System.out.print("Enter the password to be encrypted: ");
                    String password = sc.nextLine();

                    Encryption.main(new String[] {
                            password,
                            FOLDER_PATH
                    });

                    break;
                case 2:

                    System.out.print("Enter the encrypted string to be decrypted: ");
                    String encryptedString = sc.nextLine();

                    Decryption.main(new String[] {
                            encryptedString,
                            FOLDER_PATH
                    });

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
