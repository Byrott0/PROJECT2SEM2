package org.example.project2sem2.Model;
import org.example.project2sem2.Utils.SetObserver;

public class Settings {
    private String currentEmail;
    private String currentUsername;
    private String currentPassword;
    private String newEmail;
    private String newUsername;
    private String newPassword;
    private SetObserver observer;

    private static Settings instance;

    private String changeInfo;

    private Settings() {}

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public void addObserver(SetObserver observer) {
        this.observer = observer;
    }


    protected void notifyObserver(String info) {
        this.changeInfo = info; // Store the settings change information
        if (observer != null) {
            observer.update(info);
        }
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
        notifyObserver("New email set to: " + newEmail);
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
        notifyObserver("New username set to: " + newUsername);
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        notifyObserver("New password set");
    }
}

