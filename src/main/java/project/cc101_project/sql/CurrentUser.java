package project.cc101_project.sql;

public class CurrentUser {
    private static CurrentUser instance;
    private int id;
    private String fullName;
    private boolean isStudent;

    private CurrentUser() {} // Private constructor

    public static CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }

    // Getters and Setters
    public void setUserData(int id, String fullName, boolean isStudent) {
        this.id = id;
        this.fullName = fullName;
        this.isStudent = isStudent;
    }

    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public boolean isStudent() { return isStudent; }
}