package org.example.machineCoding.CustomerIssue.managers;

import org.example.machineCoding.CustomerIssue.entities.Agent;
import org.example.machineCoding.CustomerIssue.entities.Issue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

class AgentsManager {
    // issueType vs list of agentIds
    private HashMap<Integer, ConcurrentLinkedDeque<String>> agentSkillsMap;
    private ConcurrentHashMap<String, Agent> agents;

    AgentsManager(List<String> issueTypes) {
        agentSkillsMap = new HashMap<>();
        agents = new ConcurrentHashMap<>();
        // initialize all issueTypes i.e skills
        for (int i = 0; i < issueTypes.size(); i++)
            agentSkillsMap.put(i, new ConcurrentLinkedDeque<>());
    }

    public Agent getAgent(String agentEmailId) {
        return agents.get(agentEmailId);
    }

    //returns success or agent already exists
    public String addAgent(String agentEmailId, String name, List<String> expertise) {
        if (agents.containsKey(agentEmailId)) return "agent already exists";

        List<Integer> expertiseCodes = expertise.stream()
                .map(issueType -> Issue.IssueTypes.getCode(issueType))
                .collect(Collectors.toList());

        agents.put(agentEmailId, new Agent(agentEmailId, name, expertiseCodes));

        for (String skill : expertise) {
            Integer skillCode = Issue.IssueTypes.getCode(skill); // Convert skill name to Integer

            agentSkillsMap.putIfAbsent(skillCode, new ConcurrentLinkedDeque<>());

            agentSkillsMap.get(skillCode).add(agentEmailId);
        }
        return "success: created agent :" + agentEmailId;
    }


    Collection<String> getAgentIdsForIssue(int issueType) {
        Collection<String> agents = agentSkillsMap.getOrDefault(
                issueType, new ConcurrentLinkedDeque<>());
        return Collections.unmodifiableCollection(agents);
    }

    public Map<String, List<String>> getAgentHistory() {
        List<Agent> agentList = new ArrayList<>(agents.values());
        Map<String, List<String>> agentHistoryMap = new HashMap<>();
        for (Agent agent : agentList) {
            agentHistoryMap.put(agent.getAgentId(), agent.getAssignedIds());
        }
        return agentHistoryMap;

    }

    public void assignIssueToAgent(String agentEmailId, String issueId) {
        agents.get(agentEmailId).addAssignedIds(issueId);
    }
}