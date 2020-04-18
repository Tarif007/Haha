package com.example.myplaces.DatabaseOperation;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EntityTable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String address;
    private String description;
    private int priority;

    public EntityTable(String address, String description, int priority) {
        this.address = address;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
