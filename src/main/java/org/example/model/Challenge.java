package org.example.model;

public class Challenge {
    int id;
    String name;
    String description;
    String threshold;
    
    public Challenge(int id, String name, String description, String threshold) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.threshold = threshold;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getThreshold() {
        return threshold;
    }
    
    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }
    
    @Override
    public String toString() {
        return "Challenge [id=" + id + ", name=" + name + ", description=" + description + ", threshold=" + threshold
                + "]";
    }
    
}
