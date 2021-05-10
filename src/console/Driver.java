package console;

import java.util.Scanner;

public class Driver {

    @SuppressWarnings("resource")
	public static void main(String[] args) {

        Methods m = new Methods();

        boolean flag = true;
        Scanner input = new Scanner(System.in);
        while (flag) {
            //Menu...
            System.out.println("");
            System.out.println("/***********( All_Round Scoring System )***********/");
            System.out.println("//           1  ==> Create Participant            //");
            System.out.println("//           2  ==> Add Match Details             //");
            if (m.getSize()) {
                System.out.println("//           3  ==> Result Table                  //");
            }
            System.out.println("//           0 ==> Quit                           //");
            System.out.println("/**************************************************/");

            System.out.print("Choose an option: ");
            String option = input.next();
            input.nextLine();

            switch (option) {
                case "1":
                    m.createParticipant();
                    break;
                case "2":
                    m.addMatchDetails();
                    break;
                case "3":
                    m.showResults();
                    break;
                case "0":
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }

        }

    }

}
