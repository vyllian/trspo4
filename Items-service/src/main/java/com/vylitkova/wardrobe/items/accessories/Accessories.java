package com.vylitkova.wardrobe.items.accessories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "Accessories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accessories {
    @Id
    private ObjectId id;
    private String type;
    private ArrayList<String> colors;
    private String image;
}
