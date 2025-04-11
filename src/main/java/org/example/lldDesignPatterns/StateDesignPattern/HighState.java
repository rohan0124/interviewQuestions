package org.example.lldDesignPatterns.StateDesignPattern;

public class HighState implements State {

    public HighState(Fan fan) {
    }

    @Override
    public void higher(Fan fan) {
        System.out.println("Already in high speed state");

    }

    @Override
    public void lower(Fan fan) {
        fan.setState(new MediumState());
        System.out.println("Current state : " + fan.getCurrentState());
    }
}
