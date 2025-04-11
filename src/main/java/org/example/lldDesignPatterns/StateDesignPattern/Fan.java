package org.example.lldDesignPatterns.StateDesignPattern;

// Type - Behavioural Design Pattern
// example imagine a table fan with 3 states , and with function higher or lower
public class Fan {
    private State state;

    Fan() {
        this.state = new OffState();
    }

    void setState(State state) {
        this.state = state;
    }

    void goHigher() {
        state.higher(this);
    }

    void goLower() {
        state.lower(this);
    }

    String getCurrentState() {
        return state.getClass().getSimpleName();
    }

    public static void main(String[] args) {
        Fan fan = new Fan();
        System.out.println(fan.getCurrentState());
        fan.goLower();
        fan.goHigher();
        fan.goHigher();
        fan.goHigher();
        fan.goHigher();
        fan.goLower();
        fan.goLower();
    }
}

