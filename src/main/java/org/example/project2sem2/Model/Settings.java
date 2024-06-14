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

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }


    public void addObserver(SetObserver observer) {
        this.observer = observer;
    }

    protected void notifyObserver(String info) {
            if (observer != null) {
                observer.update(info);
            }
        }

    public void Talen(){
            // Wijzigingen aanbrengen...
            String info = "Language settings changed";

            // Informeer de observer over de wijziging
            notifyObserver(info);
        }

    public void Wijzig(){
            // Wijzigingen aanbrengen...
            String info = "General settings changed";

            // Informeer de observer over de wijziging
            notifyObserver(info);
        }

    public void LogUit(){
            // Wijzigingen aanbrengen...
            String info = "User logged out";

            // Informeer de observer over de wijziging
            notifyObserver(info);
        }
    }

