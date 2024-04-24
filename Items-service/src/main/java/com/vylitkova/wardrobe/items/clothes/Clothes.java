package com.vylitkova.wardrobe.items.clothes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Clothes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clothes {
    @Id
    private ObjectId id;
    private String category;
    private String type;
    private List<String> colors;
    private String image;
    public void setCategory(String category) {
        this.category=category;
    }
    public void setType(String type) {
        this.type=type;
    }
}
