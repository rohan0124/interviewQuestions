package org.example.machineCoding.CustomerIssue.managers;

import org.example.machineCoding.CustomerIssue.entities.Issue;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class LowestIssuesOpenSelector implements AgentSelector, IssueObserver {
    final static int maxAssignment =1000*1000*1000;
    // agentId vs open issue count
    private final ConcurrentHashMap<String, AtomicInteger> agentOpenIssuesMap =
            new ConcurrentHashMap<>();
    private final AgentsManager agentsManager;

    LowestIssuesOpenSelector(AgentsManager agentsManager){
        this.agentsManager=agentsManager;
    }

    // returns id of the agent or empty string i.e ""
    public String assignAgent(int issueType) {
        String chosenAgentId ="";
        Collection<String> agentIds = agentsManager.getAgentIdsForIssue(issueType);
        for(String agentId: agentIds){
            AtomicInteger open= agentOpenIssuesMap.getOrDefault(
                    agentId, new AtomicInteger(0));
            if(open.get()==0) return agentId;
            if(open.get()<=maxAssignment){
                chosenAgentId=agentId;
            }
        }
        return chosenAgentId;
    }

    public void updated(Issue issue) {
        if(!issue.isAssigned()) return;
        agentOpenIssuesMap.putIfAbsent(
                issue.getAgentId(), new AtomicInteger(0));
        AtomicInteger existing = agentOpenIssuesMap.get(issue.getAgentId());
        if(!issue.isResolved()) existing.addAndGet(1);
        else existing.addAndGet(-1);
    }

}