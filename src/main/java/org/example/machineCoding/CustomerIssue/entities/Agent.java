package org.example.machineCoding.CustomerIssue.entities;

import java.util.ArrayList;
import java.util.List;

public class Agent {
    private static int agentIdCount = 1;
    private String agentId;
    private String name;

    private String email;
    private List<Integer> skills;
    private ArrayList<String> assignedIds;

    public Agent(String email, String name, List<Integer> skills) {
        this.agentId = "A" + agentIdCount++;
        this.email = email;
        this.name = name;
        this.skills = skills;
        assignedIds = new ArrayList<>();
    }


    public String getAgentId() {
        return agentId;
    }

    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }

    public List<String> getAssignedIds() {
        return assignedIds;
    }

    /**
     * This method is thread safe
     */
    public synchronized void addAssignedIds(String issueId) {
        assignedIds.add(issueId);
    }
}