package org.example.lldDesignPatterns.StateDesignPattern;

public class LowState implements State {

    @Override
    public void higher(Fan fan) {
        fan.setState(new MediumState());
        System.out.println("Current state : " + fan.getCurrentState());
    }

    @Override
    public void lower(Fan fan) {
        fan.setState(new OffState());
        System.out.println("Current state : " + fan.getCurrentState());
    }
}
