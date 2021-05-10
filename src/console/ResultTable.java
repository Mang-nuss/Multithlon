package console;

import java.io.Serializable;

public class ResultTable implements Serializable {

    private String name;
    private String competition;
    private String event;
    private int points;

    public ResultTable() {
    }

    public ResultTable(String name, String competition, String event, int points) {
        this.name = name;
        this.competition = competition;
        this.event = event;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public String getCompetition() {
        return competition;
    }

    public String getEvent() {
        return event;
    }

    public int getPoints() {
        return points;
    }

}
