package project.cc101_project.sql;

import java.time.LocalDate;

public class MaintenanceReport {
    private final int reportID;
    private final int toolID;
    private final String issueDescription;
    private final LocalDate reportDate;
    private final String status;

    public MaintenanceReport(int reportID, int toolID, String issueDescription, LocalDate reportDate, String status) {
        this.reportID = reportID;
        this.toolID = toolID;
        this.issueDescription = issueDescription;
        this.reportDate = reportDate;
        this.status = status;
    }

    public int getReportID() {
        return reportID;
    }

    public int getToolID() {
        return toolID;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public String getStatus() {
        return status;
    }
}
