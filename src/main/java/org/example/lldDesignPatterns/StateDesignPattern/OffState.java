package org.example.lldDesignPatterns.StateDesignPattern;

public class OffState implements State {
    @Override
    public void higher(Fan fan) {
        fan.setState(new LowState());
        System.out.println("Current state : " + fan.getCurrentState());
    }

    @Override
    public void lower(Fan fan) {
        System.out.println("Fan is already off");
    }
}
