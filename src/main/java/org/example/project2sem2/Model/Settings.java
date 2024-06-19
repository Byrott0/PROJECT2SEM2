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


    // Getters and Setters
    public String getCurrentEmail() {
        return currentEmail;
    }

    public void setCurrentEmail(String currentEmail) {
        this.currentEmail = currentEmail;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewEmail() {
        return newEmail;
    }


    public String getNewUsername() {
        return newUsername;
    }


    public String getNewPassword() {
        return newPassword;
    }


    public void addObserver(SetObserver observer) {
        this.observer = observer;
    }

    public String getChangeInfo() {
        return changeInfo;
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

