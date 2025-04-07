package org.example.machineCoding.CustomerIssue.managers;

import org.example.machineCoding.CustomerIssue.entities.Issue;

import java.util.HashMap;
import java.util.List;

class AgentAssigner {
    // assignStrategy vs AgentSelector
    private HashMap<Integer, AgentSelector> map = new HashMap<>();

    AgentAssigner(IssueSubject issueSubject,
                  AgentsManager agentManager, List<String> issueTypes){
        var lowestIssuesOpenAssigner =  new LowestIssuesOpenSelector(agentManager);

        map.put(0, lowestIssuesOpenAssigner); // can add other implementation strategy

        issueSubject.attach(lowestIssuesOpenAssigner);

    }

    // returns id of the agent or empty string i.e ""
    String getAgentForAssigningIssue(Issue issue, int assignStrategy ){
        int issueType = issue.getIssueTypeCode();
        AgentSelector strategy = map.get(assignStrategy);
        if(strategy!=null)return strategy.assignAgent(issueType);
        return "";
    }
}