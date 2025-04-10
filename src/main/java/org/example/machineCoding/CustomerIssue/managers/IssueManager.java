package org.example.machineCoding.CustomerIssue.managers;

import org.example.machineCoding.CustomerIssue.entities.Issue;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class IssueManager implements IssueSubject {
    static int issueIdCount=1;
    private ArrayList<IssueObserver> observeAllIssues = new ArrayList<>();
    private ConcurrentHashMap<String, Issue> allIssues= new ConcurrentHashMap<>();

    public Issue createIssue(String orderId, String issueTypeName, String subject, String description, String email) {
        if (orderId == null || issueTypeName == null || subject == null || description == null || email == null) {
            throw new IllegalArgumentException("Invalid input: null values are not allowed");
        }
        //validator

        if (!Issue.IssueTypes.isValid(issueTypeName)) {
            throw new IllegalArgumentException("Invalid issue type: " + issueTypeName);
        }

        Issue.IssueTypes issueType = Arrays.stream(Issue.IssueTypes.values())
                .filter(type -> type.getName().equalsIgnoreCase(issueTypeName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid issue type: " + issueTypeName));

        String issueId = "I" + issueIdCount;
        Issue issue = new Issue(issueId, orderId, description, issueType, subject, email);
        allIssues.put(issueId, issue);
        issueIdCount++;
        return issue;
    }

    public void resolveIssue(String issueId, String resolution) {
        Issue issue = allIssues.get(issueId);
        if(issue==null) return; // throw exception
        issue.resolveIssue(resolution);
        notifyAll(issue);
    }

    public void updateIssue(String issueId, String status, String resolution){
        Issue issue = allIssues.get(issueId);
        if(issue==null) {
            System.out.println("Invalid issueId ");
            return;
        }
        if(!Issue.IssueStatus.isValid(status)){
            System.out.println("Invalid status ");
            return;
        }
        issue.setIssueStatus(Issue.IssueStatus.fromName(status));
        notifyAll(issue);
        issue.setResolution(resolution);

    }

    public List<Issue> getIssues(Map<String, String> filter) {
        Stream<Issue> issueStream = allIssues.values().stream();

        //  Class for filter type
        String emailFilter = filter.get("email");
        String typeFilterStr = filter.get("type");
        String statusFilterStr = filter.get("status"); // Added status filter key

        // Apply email filter if present
        if (emailFilter != null && !emailFilter.isEmpty()) {
            issueStream = issueStream.filter(issue -> emailFilter.equalsIgnoreCase(issue.getEmail()));
        }

        // Apply type filter if present
        if (typeFilterStr != null && !typeFilterStr.isEmpty()) {
            issueStream = issueStream.filter(issue -> {
                Issue.IssueTypes type = issue.getIssueType();
                if (type == null) return false;
                String enumDisplayName = type.getName(); // Get the correct display name
                return enumDisplayName.equalsIgnoreCase(typeFilterStr);
            });
        }

        // Apply status filter if present
        if (statusFilterStr != null && !statusFilterStr.isEmpty()) {
            issueStream = issueStream.filter(issue -> {
                Issue.IssueStatus status = issue.getIssueStatus();
                if (status == null) return false;
                String enumName = status.name(); // e.g., OPEN, IN_PROGRESS
           // Compare filter string ("In Progress") to enum name ("IN_PROGRESS")
                String comparableFilterStr = statusFilterStr.toUpperCase().replace(" ", "_");
                return enumName.equalsIgnoreCase(comparableFilterStr);
            });
        }

        return issueStream.collect(Collectors.toList());
    }

    public Issue getIssue(String issueId){
        return allIssues.getOrDefault(issueId, null);
    }


    void assignIssue(String issueId, String agentId) {
        Issue issue = allIssues.get(issueId);
        if(issue==null) return;
        issue.assignIssue(agentId);
        notifyAll(issue);
    }

    public void attach(IssueObserver observer) {
        observeAllIssues.add(observer);
    }

    public void notifyAll(Issue issue) {
        for(IssueObserver observer: observeAllIssues)
            observer.updated(issue);
    }
}