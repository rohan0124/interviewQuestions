package org.example.machineCoding.CustomerIssue.managers;

import org.example.machineCoding.CustomerIssue.entities.Issue;

public interface IssueObserver {
    void updated(Issue issue);
}
