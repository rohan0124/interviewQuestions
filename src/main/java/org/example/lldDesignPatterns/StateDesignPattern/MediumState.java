package org.example.lldDesignPatterns.StateDesignPattern;

public class MediumState implements State {


    @Override
    public void higher(Fan fan) {
        fan.setState(new HighState(fan));
        System.out.println("Current state : " + fan.getCurrentState());
    }

    @Override
    public void lower(Fan fan) {
        fan.setState(new LowState());
        System.out.println("Current state : " + fan.getCurrentState());
    }
}
