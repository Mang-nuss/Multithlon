package console;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public final class Methods {

    private FileInputStream fis;
    private ObjectInputStream ois;
    private FileOutputStream fos;
    private ObjectOutputStream oos;

    private final ArrayList<Participant> participants;
    private final ArrayList<Decathlon> decathlon;
    private final ArrayList<Heptathlon> heptathlon;
    private final ArrayList<ResultTable> resultTable;

    public Methods() {

        participants = new ArrayList<>();
        decathlon = new ArrayList<>();
        heptathlon = new ArrayList<>();
        resultTable = new ArrayList<>();

        loadDatafromFiles();

    }

    boolean nameValidations(String name) {

        boolean flag = true;

        if (!name.trim().isEmpty()) {
            if (name.length() < 20) {
                for (int i = 0; i < name.length(); i++) {
                    if (name.charAt(i) >= 'A' && name.charAt(i) <= 'Z'
                            || name.charAt(i) >= 'a' && name.charAt(i) <= 'z') {
                        flag = true;
                    } else {
                        flag = false;
                        break;
                    }
                }
                if (!flag == false) {

                    for (Participant participant : participants) {
                        if (participant.getName().equalsIgnoreCase(name)) {
                            flag = false;
                        }
                    }

                    if (participants.size() <= 40) {
                        if (flag == true) {
                            return true;
                        } else {
                            System.out.println("Name already taken..");
                        }
                    } else {
                        System.out.println("Limit (40 Participants Max)");
                    }

                } else {
                    System.out.println("Only alphabets Required");
                }
            } else {
                System.out.println("20 Characters Allow for name..");
            }
        } else {
            System.out.println("Name Required");
        }

        return false;
    }

    void createParticipant() {

        Scanner input = new Scanner(System.in);
        String name = null;
        String competition = null;

        System.out.print("Enter Participant name: ");
        name = input.next();
        input.nextLine();

        if (nameValidations(name)) {
            System.out.print("Enter Competition: ");
            competition = input.next();

            if (competitionValidation(competition)) {
                String c = Character.toString(competition.charAt(0)).toUpperCase();
                competition = c + competition.substring(1, competition.length());
                participants.add(new Participant(name, competition));
                saveParticipants();
                System.out.println("\nSuccesfull..");
            }
        }
    }

    boolean competitionValidation(String competition) {
        int count = 0;
        if (!competition.trim().isEmpty()) {
            if (competition.equalsIgnoreCase("heptathlon")
                    || competition.equalsIgnoreCase("decathlon")) {
                for (Participant participant : participants) {
                    if (participant.getCompetition().equalsIgnoreCase(competition)) {
                        count++;
                    }
                }

                if (count < 20) {
                    return true;
                } else {
                    System.out.println("Limit (20 "
                            + competition + " ) Participants Max)");
                }

            } else {
                System.out.println("Available Compettions are"
                        + " Heptathlon and Decathlon");
            }
        } else {
            System.out.println("Competition Required");
        }

        return false;
    }

    void addMatchDetails() {

        Scanner input = new Scanner(System.in);
        String name = null;
        String competition = null;
        String event = null;
        double value = 0.0;

        int count = 0;
        boolean flag = false;

        System.out.print("Enter participant Name: ");
        name = input.next();
        input.nextLine();

        for (Participant participant : participants) {
            if (name.equalsIgnoreCase(participant.getName())) {
                name = participant.getName();
                competition = participant.getCompetition();
                count++;
                break;
            }
        }
        if (count == 0) {
            System.out.println("Participant with Name " + name + " not exist\n"
                    + "Create Participant 1st");
        } else {
            count = 0;
            System.out.println("********Choose an event*******");
            if (competition.equalsIgnoreCase("Decathlon")) {
                for (int i = 0; i < decathlon.size(); i++) {
                    System.out.println("  " + (i + 1) + " => " + decathlon.get(i).getEvent());
                    count = (i + 1);
                }
            } else {
                for (int i = 0; i < heptathlon.size(); i++) {
                    System.out.println("  " + (i + 1) + " => " + heptathlon.get(i).getEvent());
                    count = i + 1;
                }
            }
            System.out.println("******************************");

            System.out.print("Choose an option: ");
            String option = input.next();

            try {
                int op = Integer.parseInt(option);
                if (op > 0 && op <= op) {
                    if (competition.equalsIgnoreCase("Decathlon")) {
                        event = decathlon.get(op - 1).getEvent();
                    } else {
                        event = heptathlon.get(op - 1).getEvent();
                    }

                    System.out.print("Enter Value: ");
                    int points = 0;
                    try {
                        value = input.nextDouble();
                        if (value >= 0) {
                            flag = true;
                            if (competition.equalsIgnoreCase("Decathlon")) {
                                for (Decathlon dec : decathlon) {
                                    if (dec.getEvent().equals(event)) {
                                        points = calculatePoints(dec.getA(),
                                                dec.getB(), dec.getC(), value,
                                                competition);
                                        break;
                                    }
                                }

                            } else {
                                for (Heptathlon hec : heptathlon) {
                                    if (hec.getEvent().equals(event)) {
                                        points = calculatePoints(hec.getA(),
                                                hec.getB(), hec.getC(), value,
                                                competition);
                                        break;
                                    }
                                }
                            }
                            count = 0;
                            for (int i = 0; i < resultTable.size(); i++) {
                                if (resultTable.get(i).getPoints() <= points) {
                                    resultTable.add(i, new ResultTable(name,
                                            competition, event, points));
                                    saveResults();
                                    System.out.println("\nSuccesfull..");
                                    count = 1;
                                    break;
                                }
                            }
                            if (count == 0) {
                                resultTable.add(new ResultTable(name,
                                        competition, event, points));
                                saveResults();
                                System.out.println("\nSuccesfull..");
                            }
                        }
                    } catch (Exception NumberFormatException) {
                        System.out.println("Value should be a Number..");
                    }

                } else {
                    System.out.println("Invalid Option");
                }
            } catch (Exception NumberFormatException) {
                System.out.println("Invalid Option");
            }

        }
        if (flag == false) {
            System.out.println("\nFailed ! Try Again..");
        }

    }

    int calculatePoints(double A, double B, double C, double value, String com) {
        double Points = 0;
        int points = 0;
        if (com.equalsIgnoreCase("decathlon")) {
            Points = A * (Math.pow((B - value), C));
            points = (int) Points;
        } else {
            Points = A * (Math.pow((value - B), C));
            points = (int) Points;
        }
        return points;
    }

    void showResults() {
        System.out.println("--------------------------------------------------------");
        System.out.println("Name           Competition    Event             Points");
        System.out.println("--------------------------------------------------------");
        for (ResultTable resTab : resultTable) {
            System.out.println(String.format("%-15s", resTab.getName())
                    + String.format("%-15s", resTab.getCompetition())
                    + String.format("%-18s", resTab.getEvent())
                    + String.format("%6s", resTab.getPoints()));
        }
        System.out.println("--------------------------------------------------------");

    }

    void loadDatafromFiles() {

        loadParticipantFile();
        loadDecathlonFile();
        loadHeptathlonFile();
        loadResultTableFile();

    }

    boolean getSize() {
        if (resultTable.size() > 0) {
            return true;
        }
        return false;
    }

    //save data into files
    void saveParticipants() {
        try {
            fos = new FileOutputStream("Participants.dat");
            oos = new ObjectOutputStream(fos);
            for (Participant p : participants) {
                oos.writeObject(p);
            }
            fos.close();
            oos.close();

        } catch (IOException e) {
        }
    }

    void saveResults() {
        try {
            fos = new FileOutputStream("ResultTable.dat");
            oos = new ObjectOutputStream(fos);
            for (ResultTable rt : resultTable) {
                oos.writeObject(rt);
            }
            fos.close();
            oos.close();

        } catch (IOException e) {
        }
    }

    //load files data
    private void loadParticipantFile() {

        participants.clear();
        try {
            fis = new FileInputStream("Participants.dat");
            ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    Object object = ois.readObject();
                    Participant p = (Participant) object;

                    //add to array list
                    participants.add(p);
                } catch (EOFException eof) {
                    fis.close();
                    ois.close();
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
        }
    }

    private void loadDecathlonFile() {
        decathlon.clear();
        try {
            fis = new FileInputStream("Decathlon.dat");
            ois = new ObjectInputStream(fis);

            while (true) {
                try {

                    Object object = ois.readObject();;
                    Decathlon d = (Decathlon) object;

                    //add to array list
                    decathlon.add(d);
                } catch (EOFException eof) {
                    fis.close();
                    ois.close();
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
        }

    }

    private void loadHeptathlonFile() {

        heptathlon.clear();
        try {
            fis = new FileInputStream("Heptathlon.dat");
            ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    Object object = ois.readObject();
                    Heptathlon h = (Heptathlon) object;

                    //add to array list
                    heptathlon.add(h);
                } catch (EOFException eof) {
                    fis.close();
                    ois.close();
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
        }
    }

    private void loadResultTableFile() {
        resultTable.clear();
        try {
            fis = new FileInputStream("ResultTable.dat");
            ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    Object object = ois.readObject();
                    ResultTable rt = (ResultTable) object;

                    //add to array list
                    resultTable.add(rt);
                } catch (EOFException eof) {
                    fis.close();
                    ois.close();
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
        }
    }
}
