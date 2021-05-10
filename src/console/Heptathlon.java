package console;

import java.io.Serializable;

public class Heptathlon implements Serializable {

    private String event;
    private double A;
    private double B;
    private double C;

    public Heptathlon() {
    }

    public Heptathlon(String event, double A, double B, double C) {
        this.event = event;
        this.A = A;
        this.B = B;
        this.C = C;
    }

    public String getEvent() {
        return event;
    }

    public double getA() {
        return A;
    }

    public double getB() {
        return B;
    }

    public double getC() {
        return C;
    }

}
