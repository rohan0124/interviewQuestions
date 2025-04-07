package org.example.machineCoding.CustomerIssue.managers;

import org.example.machineCoding.CustomerIssue.entities.Issue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerIssueAgentSystem implements ICustomerIssueAgentService {
    private IssueManager issueManager;
    private AgentAssigner agentAssigner;
    private AgentsManager agentManager;
    private List<String> issueTypes;


    public CustomerIssueAgentSystem() {
        this.init();
    }

    public void init() {
        this.issueTypes = Arrays.stream(Issue.IssueTypes.values())
                .map(Issue.IssueTypes::getName)
                .collect(Collectors.toList());        issueManager = new IssueManager();
        agentManager = new AgentsManager(issueTypes);
        agentAssigner = new AgentAssigner(
                issueManager, agentManager, issueTypes);
    }


    public void resolveIssue(String issueId, String resolution) {
        Issue issue = issueManager.getIssue(issueId);
        if (issue == null || issue.isResolved() || !issue.isAssigned()){
            System.out.println("Issue resolved before assignmed");
            return;
        }
        issueManager.resolveIssue(issueId, resolution);
    }

    // returns success, agent already exists

    @Override
    public String addAgent(String agentId, String name, List<String> expertise) {
        return agentManager.addAgent(agentId, name, expertise);
    }

    // Prints a list of issueId's resolved by agents
    public void viewAgentsWorkHistory() {

         Map<String,List<String>> agentHistory= agentManager.getAgentHistory();
         for(String agentId:agentHistory.keySet()){
             String issueIds =String.join(",",agentHistory.get(agentId));
             System.out.println(agentId + " -> " +issueIds);
         }
    }

    // assignStrategy: 0 -> agent with the least open issues,
    // other possible strategy implementation maybe:
    // 1 -> agent with most resolved issues  of given issueType,etc
    public String assignIssue(String issueId) {
        Issue issue = issueManager.getIssue(issueId);
        if (issue == null) return "issue doesn't exist";
        if (issue.isAssigned()) return "issue already assigned";
        String agentEmailId = agentAssigner.getAgentForAssigningIssue(issue, 0);
        if (agentEmailId.isEmpty()) return "agent with expertise doesn't exist";
        agentManager.assignIssueToAgent(agentEmailId,issueId);
        issueManager.assignIssue(issueId, agentEmailId);
        System.out.println("Issue :"+issueId + " assigned to Agent :" +agentManager.getAgent(agentEmailId).getAgentId());
        return agentEmailId;
    }

    @Override
    public List<Issue> getIssues(Map<String, String> filter) {
        List<Issue> filteredIssues= issueManager.getIssues(filter);
        for(Issue issue:filteredIssues){
            System.out.println(issue);
        }
        return filteredIssues;
    }

    // Prints "issue created", "invalid issue type"
    @Override
    public void createIssue(String transactionId, String issueType, String subject, String description, String email) {
        Issue issue =issueManager.createIssue(transactionId,issueType,
                 subject, description, email);
        System.out.println("Issue "+issue.getIssueId()+" created against transaction "+transactionId);
    }



    @Override
    public void updateIssue(String issueId, String status, String resolution) {
        issueManager.updateIssue(issueId,status,resolution);
    }


}
