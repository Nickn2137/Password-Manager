package assn07;


import java.util.List;
import java.util.Scanner;

// your code below
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,String> passwordManager = new PasswordManager<>();
        System.out.println("Enter Master Password");
        String input = scanner.nextLine();
        while(!passwordManager.checkMasterPassword(input)) {
            System.out.println("Enter Master Password");
            input = scanner.nextLine();
        }
        String input2 = scanner.nextLine();
        while(!input2.equals("Exit")) {
            if (input2.equals("New password")) {
                String web = scanner.nextLine();
                String pass = scanner.nextLine();
                passwordManager.put(web, pass);
                System.out.println("New password added");
            }
            else if(input2.equals("Get password")) {
                String web = scanner.nextLine();
                if(passwordManager.get( web) == null) {
                    System.out.println("Account does not exist");
                }
                else {
                    System.out.println(passwordManager.get(web));
                }
            }
            else if(input2.equals("Delete account")) {
                String web = scanner.nextLine();
                if (passwordManager.remove(web) != null) {
                    System.out.println("Account deleted");
                }
                else {
                    System.out.println("Account does not exist");
                }
            }
            else if (input2.equals("Check duplicate password")) {
                String pass = scanner.nextLine();
                List<String> table = passwordManager.checkDuplicate(pass);
                if(table.size() != 0) {
                    System.out.println("Websites using that password:");
                    for(int i = 0; i < table.size(); i++) {
                        System.out.println(table.get(i));
                    }
                }
                else {
                    System.out.println("No account uses that password");
                }
            }
            else if (input2.equals("Get accounts")) {
                System.out.println("Your accounts:");
                for(String accounts: passwordManager.keySet()) {
                    System.out.println(accounts);
                }
            }
            else if( input2.equals("Generate random password")){
                String length;
                length = scanner.nextLine();
                int numberLength = Integer.parseInt(length);
                String output = passwordManager.generateRandomPassword(numberLength);
                System.out.println(output);
            }
            else {
                System.out.println("Command not found");
            }
            input2 = scanner.nextLine();
        }
    }
}
