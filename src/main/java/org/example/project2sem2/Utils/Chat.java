package org.example.project2sem2.Utils;

public class Chat {
    private String title;
    private String history;
    private String name;
    private boolean loadedFromDB;

    public Chat(String title, String name) {
        this.title = title;
        this.name = name;
        this.history = "";
        this.loadedFromDB = false;
    }

    public Chat(String title, String name, boolean loadedFromDB) {
        this.title = title;
        this.name = name;
        this.history = "";
        this.loadedFromDB = loadedFromDB;
    }

    public Chat() {

    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isLoadedFromDB() {
        return loadedFromDB;
    }

    public void setLoadedFromDB(boolean loadedFromDB) {
        this.loadedFromDB = loadedFromDB;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "name='" + name + '\'' +
                ", history='" + history + '\'' +
                '}';
    }
}
