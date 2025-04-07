package org.example.machineCoding.CustomerIssue;

import org.example.machineCoding.CustomerIssue.entities.Issue;
import org.example.machineCoding.CustomerIssue.managers.CustomerIssueAgentSystem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverClass {
    public static void main(String[] args){
        testCustomerResolutionDefaultWithSampleInput();
        // Design Patterns used, observer pattern and strategy pattern

        // Possible imporvements
        // add input and output formatter to avoid hardcoding
        // create other types of assignment strategies
        // Incase there are more fields in Issues create using Builder pattern
        // Design Patterns used, observer pattern and strategy patter
    }

    public static void testCustomerResolutionDefaultWithSampleInput() {
        CustomerIssueAgentSystem customerIssueService = new CustomerIssueAgentSystem();

        // Create Issues
        customerIssueService.createIssue("T1", "Payment Related", "Payment Failed", "My payment failed but money is debited", "testUser1@test.com");
        customerIssueService.createIssue("T2", "Mutual Fund Related", "Purchase Failed", "Unable to purchase Mutual Fund", "testUser2@test.com");
        customerIssueService.createIssue("T3", "Payment Related", "Payment Failed", "My payment failed but money is debited", "testUser2@test.com");

        System.out.println("__________________");
        System.out.println();

        // Add Agents
        customerIssueService.addAgent("agent1@test.com", "Agent 1", Arrays.asList("Payment Related", "Gold Related"));
        customerIssueService.addAgent("agent2@test.com", "Agent 2", List.of("Mutual Fund Related"));
        System.out.println("__________________");
        System.out.println();

        // Assign Issues
        customerIssueService.assignIssue("I1");
        customerIssueService.assignIssue("I2");
        customerIssueService.assignIssue("I3");
        System.out.println("__________________");
        System.out.println();

        // Get Issues by Email
        System.out.println("__________________");
        System.out.println("getIssue with filter " +Map.of("email", "testUser2@test.com"));

        customerIssueService.getIssues(Map.of("email", "testUser2@test.com"));

        System.out.println("__________________");
        // Get Issues by Type
        System.out.println("getIssue with filter " +Map.of("type", "Payment Related"));

        customerIssueService.getIssues(Map.of("type", "Payment Related"));
        System.out.println("__________________");
        System.out.println();

        // Update and Resolve Issue
        customerIssueService.updateIssue("I3", "In Progress", "Waiting for payment confirmation");
        customerIssueService.resolveIssue("I3", "PaymentFailed debited amount will get reversed");

        System.out.println("__________________");
        System.out.println("viewAgentsWorkHistory");
        // View Agent Work History
        customerIssueService.viewAgentsWorkHistory();
    }


}
