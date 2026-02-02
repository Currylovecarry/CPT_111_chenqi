import java.util.Scanner;
public class QuizSystem {
    public static void main(String[] args) {
        //Call the quiztools method to read the user data in the csv
        QuizTools quizTools = new QuizTools();
        quizTools.readdata();
        //Start page
        loop:while (true) {//Give the loop a label named loop
            System.out.println("--------Welcome to CPT111 Quiz System---------");
            System.out.println("Do you have an account?");
            System.out.println("1. Yes,login");
            System.out.println("2. No,register");
            System.out.println("3. Exit");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:{
                    //Call the methods in quiztools to complete user login
                    Users user = quizTools.login();
                    if (user != null) {
                        quizTools.userMenu(user);
                    }
                    break;
                }
                case 2:{
                    //Call the methods in quiztools to complete user register
                    quizTools.register();
                    Users user = quizTools.login();
                    //If the user menu is successfully displayed
                    if (user != null) {
                        quizTools.userMenu(user);
                    }
                    break;
                }
                case 3:{
                    System.out.println("Goodbye!");
                    break loop;//break the loop not the switch
                }
                default:
                    System.out.println("Invalid selection, please try again");
                    break;
            }
        }
    }
}