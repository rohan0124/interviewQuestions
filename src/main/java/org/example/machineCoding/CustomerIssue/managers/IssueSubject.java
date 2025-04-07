package org.example.machineCoding.CustomerIssue.managers;

import org.example.machineCoding.CustomerIssue.entities.Issue;

public interface IssueSubject {
    void attach(IssueObserver observer);
    void notifyAll(Issue issue);
}
