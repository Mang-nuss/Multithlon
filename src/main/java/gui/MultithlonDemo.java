package gui;

import common.Calculator;
import common.Event;
import common.ExcelPrinter;
import common.Users;

import java.util.ArrayList;
import java.util.Scanner;

public class MultithlonDemo {

    public static Scanner scanner = new Scanner(System.in);
    public static String inputString;
    public static int inputNr;
    public static Users user;
    public static String username;
    public static boolean on;
    public static Event theEvent;
    public static Calculator calculator;
    public static MultithlonGUI gui;

    //-----------MAIN-----------//
    public static void main(String[] args) {

        gui = new MultithlonGUI();
        calculator = new Calculator();
        on = true;

        while(on) {

            System.out.println("-----------\nWelcome.\n" +
                    "1. Choose event (Decathlon or Heptathlon)\n" +
                    "2. Register participant\n" +
                    "3. Enter result\n" +
                    "4. Result table\n" +
                    "5. Quit");

            System.out.print("Enter nr: ");

            inputNr = scanner.nextInt();

            switch (inputNr) {
                case 1:
                    chooseEvent();
                    break;
                case 2:
                    System.out.println("Register");
                    registerParticipant(theEvent);
                    break;
                case 3:
                    System.out.println("Enter results for participant");
                    registerResult();
                    break;
                case 4:
                    System.out.println("Result table");
                    showResultTable();
                    break;
                case 5:
                    System.out.println("Ciao");
                    on = false;
                    break;
            }
        }

        //System.out.print("input: " + in);
    }

    private static void registerResult() {
        System.out.print("Enter participant name: ");
        if(scanner.hasNext()) {
            username = scanner.next();
            System.out.println();

            if(nameExists(username)) {
                System.out.println(username + " exists");
/*                evt =
                        discipline = disc;
                calculator.pickDisciplineFromMap(evt.getName(), disc);
                System.out.println("values = " + calculator.getValues());*/
            }
            else { System.out.println(username + " is not registered."); };
        }
    }

    private static void showResultTable() {
    }

    private static void registerParticipant(Event evt) {

        System.out.print("Enter participant name: ");
        if(scanner.hasNext()) {
            username = scanner.next();
            System.out.println();

            user = new Users(username, evt);
            //theEvent.addToUsersList(new Users(username, evt));
            String actualMessage = evt.addUser(username, evt);
            System.out.println(actualMessage);
        }
        printParticipants();

    }

    private static void chooseEvent() {
        System.out.print("Choose event (Decathlon or Heptathlon): ");
        if(scanner.hasNext()) {
            inputString = scanner.next();
            //System.out.println("in: " + inputString);

            System.out.println();

            if (inputString.equals("Decathlon")) {
                System.out.println("in: " + inputString);
                theEvent = new Event("Decathlon");
            } else if (inputString.equals("Heptathlon")) {
                theEvent = new Event("Heptathlon");
            }

            gui.events.add(theEvent);
            System.out.println("events for Gui object: ");
            for (Event e : gui.events) {
                System.out.println(e.getName());
            }
            //event = userEvent;
        }
    }

    private static void printParticipants() {

        System.out.println("-----------");
        for(Event evt : gui.events) {
            System.out.println("users in " + evt.getName() + ": ");
            for (Users u : evt.users) {
                System.out.println(u.getUsername());
            }
        }
        System.out.println("-----------");
    }

    private static boolean nameExists(String name) {
        boolean exists = false;

        for(Event evt : gui.events) {
            exists = evt.isAlreadyRegistered(name);
        }

        return exists;
    }
}
