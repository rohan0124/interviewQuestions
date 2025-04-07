package org.example.machineCoding.CustomerIssue.entities;

public class Issue{


    private final IssueTypes issueType;
    private String issueId, orderId, description,subject,email;
    private int issueTypeCode;

    private String agentId, resolution;

    // status =0 for unassigned, 1 for assigned and 2 for resolved
    private IssueStatus issueStatus;

    // we could also have used builder patter for better readability
    public Issue(String issueId, String orderId, String description,IssueTypes issueType,String subject, String email) {
        this.issueId = issueId;
        this.orderId = orderId;
        this.description = description;
        this.issueType = issueType;
        this.issueTypeCode = issueType.getCode();
        this.email =email;
        this.subject =subject;
        agentId="";
        resolution="";
        issueStatus = IssueStatus.OPEN;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getAgentId(){
        return agentId;
    }

    public IssueTypes getIssueType() {
        return issueType;
    }

    public IssueStatus getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(IssueStatus issueStatus) {
        this.issueStatus = issueStatus;
    }

    public int getIssueTypeCode(){
        return issueTypeCode;
    }

    public void assignIssue(String agentId){
        this.agentId=agentId;
        issueStatus=IssueStatus.IN_PROGRESS;
    }

    public boolean isAssigned(){
        return agentId!=null && !agentId.isBlank();
    }

    public boolean isResolved(){
        return issueStatus.equals(IssueStatus.RESOLVED);
    }

    public void resolveIssue(String resolution){
        this.resolution=resolution;
        issueStatus=IssueStatus.RESOLVED;
    }

    @Override
    public String toString() {
        return issueId + " {\"" +
                orderId + "\", \"" +
                issueType.name + "\", \"" +
               subject+"\",\""+
                description + "\", \"" +
                email + "\", \"" +
                issueStatus.name() + "\"}";
    }

    public String getIssueId() {
        return issueId;
    }

    public String getEmail() {
        return email;
    }

    public enum IssueStatus{
        OPEN("Open",0),
        IN_PROGRESS("In Progress",1),
        RESOLVED("Resolved",2);
        private final String name;
        private final int value;

        IssueStatus (String name,int value){
            this.name=name;
            this.value=value;
        }
        public static boolean isValid(String name) {
            for (IssueStatus type : values()) {
                if (type.name.equalsIgnoreCase(name)) return true;
            }
            return false;
        }
        public static IssueStatus fromName(String name) {
            for (IssueStatus status : values()) {
                if (status.name.equalsIgnoreCase(name)) { // Ignore case
                    return status;
                }
            }
            throw new IllegalArgumentException("Invalid status: " + name);
        }
    }

   public enum IssueTypes {
        PAYMENT("Payment Related", 0),
        MUTUAL_FUNDS("Mutual Fund Related", 1),
        GOLD("Gold Related", 2),
        INSURANCE("Insurance Related", 3);

        private final String name;
        private final int code;

        IssueTypes(String name, int code) {
            this.name = name;
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public static boolean isValid(String name) {
            for (IssueTypes type : values()) {
                if (type.name.equalsIgnoreCase(name)) return true;
            }
            return false;
        }

        public static int getCode(String name) {
            for (IssueTypes type : values()) {
                if (type.name.equalsIgnoreCase(name)) return type.code;
            }
            return -1; // Invalid case
        }

       public String getName() {
            return name;
       }
   }
}