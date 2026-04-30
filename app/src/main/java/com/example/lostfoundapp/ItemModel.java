package com.example.lostfoundapp;

public class ItemModel {

    String id, name, type, phone, description, date, location, category, image, timestamp;

    public ItemModel(String id, String name, String type, String phone, String description,
                     String date, String location, String category, String image, String timestamp) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.location = location;
        this.category = category;
        this.image = image;
        this.timestamp = timestamp;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getPhone() { return phone; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getLocation() { return location; }
    public String getCategory() { return category; }
    public String getImage() { return image; }
    public String getTimestamp() { return timestamp; }
}