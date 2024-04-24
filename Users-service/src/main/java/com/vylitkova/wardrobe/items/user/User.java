package com.vylitkova.wardrobe.items.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private ObjectId id;
    private String username;
    private String email;
    private String birth_date;
    private String sex;
    private String profile_image;
    private String bio;
    private List<String> items;
    private List<String> outfits;
    private List<String> followings;
    private List<String> followers;
}

