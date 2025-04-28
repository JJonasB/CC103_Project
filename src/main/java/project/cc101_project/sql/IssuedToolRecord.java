package project.cc101_project.sql;

import java.time.LocalDate;

public class IssuedToolRecord {
    private final int issueID;
    private final int toolID;
    private final String toolName;
    private final LocalDate issueDate;
    private final LocalDate dueDate;
    private final String status;
    private final String studentName;

    public IssuedToolRecord(int issueID, int toolID,String toolName, LocalDate issueDate,
                            LocalDate dueDate, String status, String studentName) {
        this.issueID = issueID;
        this.toolID = toolID;
        this.toolName = toolName;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.status = status;
        this.studentName = studentName;
    }

    // Getters
    public int getIssueID() { return issueID; }
    public int getToolID() { return toolID;}
    public String getToolName() { return toolName; }
    public LocalDate getIssueDate() { return issueDate; }
    public LocalDate getDueDate() { return dueDate; }
    public String getStatus() { return status; }
    public String getStudentName() { return studentName; }
}