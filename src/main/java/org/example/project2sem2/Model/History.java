package org.example.project2sem2.Model;

import java.util.ArrayList;
import java.util.List;

public class History {
    private List<String> messages;

    public History() {
        this.messages = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.join("\n", messages);
    }
}