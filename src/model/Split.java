package model;

public class Split {
    User user;
    double value;

    public Split(User user) {
        this.user = user;
    }

    public Split(User user, double value) {
        this.user = user;
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
