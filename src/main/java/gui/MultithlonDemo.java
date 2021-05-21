package gui;

import common.Calculator;
import common.Event;
import common.ExcelPrinter;
import common.Users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MultithlonDemo {

    public static Scanner scanner = new Scanner(System.in);
    public static String inputString, discipline, result, username;
    public static int rowNr = 0;
    public static double[] values;
    public static int inputNr, score;
    public static Users user;
    public static boolean on, isValid;
    public static Event theEvent;
    public static Calculator calculator;
    public static MultithlonGUI gui;

    //-----------MAIN-----------//
    public static void main(String[] args) throws IOException {

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
    //--------------------------//

    private static void registerResult() throws IOException {
        System.out.print("Enter participant name: ");
        if(scanner.hasNext()) {
            username = scanner.next();
            System.out.println();

            if(nameExists(username)) {
                System.out.println(username + " exists");
                user = getUser(username);
                theEvent = getEvent(user.getEvent());
                chooseDiscipline(theEvent);
                enterResults(discipline, theEvent);
            }
            else { System.out.println(username + " is not registered."); };
        }
    }

    private static void enterResults(String discipline, Event evt) throws IOException {
        System.out.print("Enter result: ");
        if(scanner.hasNext()) {
            result = scanner.next();
            System.out.println();

            isValid = calculator.setResultInput(result);
            if (isValid) {
                double res = Double.valueOf(result);
                if (evt.getName().equals("Decathlon")) {
                    values = evt.Dc.get(discipline);
                } else if (evt.getName().equals("Heptathlon")) {
                    values = evt.Hc.get(discipline);
                }
                System.out.println("result = " + calculator.getResult());
                score = calculator.calculateScore(evt.getName(), discipline, res, values);
                System.out.println("score for discipline " + discipline +
                        " result " + res + " is " + score);
                Object[][] testData = {{"name", user.getUsername()}, {discipline, calculator.getResult()}};
                gui.printer.add(testData, "data", rowNr);
                rowNr++;
                gui.printer.write();
            }
        }
    }

    private static void chooseDiscipline(Event e) {
        System.out.print("For event " + e.getName() + ", enter discipline name: ");
        if(scanner.hasNext()) {
            discipline = scanner.next();
            System.out.println();

            System.out.println("Chosen discipline: " + discipline);
            calculator.pickDisciplineFromMap(e.getName(), discipline);
            System.out.println("values = " + calculator.getValues());
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

    public static Users getUser(String name) {

        Users user = null;
        for(Event evt : gui.events) {
            for (Users u : evt.users) {
                if (u.getUsername().equals(name)) {
                    user = u;
                }
            }
        }

        return user;
    }

    public static Event getEvent(String eventName) {

        theEvent = null;
        for(Event evt : gui.events) {
            if(eventName.equals(evt.getName())) { theEvent = evt; }
        }

        return theEvent;
    }
}
