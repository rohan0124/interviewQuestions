package org.example.machineCoding.CustomerIssue.managers;

import org.example.machineCoding.CustomerIssue.entities.Issue;

import java.util.List;
import java.util.Map;

public interface ICustomerIssueAgentService {
        void createIssue(String transactionId, String issueType, String subject, String description, String email);
        String addAgent(String agentEmail, String agentName, List<String> issueTypes);
        String assignIssue(String issueId);
        List<Issue> getIssues(Map<String, String> filter);
        void updateIssue(String issueId, String status, String resolution);
        void resolveIssue(String issueId, String resolution);
        void viewAgentsWorkHistory();
}