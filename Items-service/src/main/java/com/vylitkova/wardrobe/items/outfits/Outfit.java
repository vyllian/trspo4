package com.vylitkova.wardrobe.items.outfits;

import com.vylitkova.wardrobe.items.accessories.Accessories;
import com.vylitkova.wardrobe.items.clothes.Clothes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Document(collection = "Outfits")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Outfit {
    @Id
    private ObjectId id;
    private String date;
    private String season;
    private String style;
    private String mood;
    private List<String> clothes;
    private List<String> accessories;
    private ArrayList<String> colors;
    private int likes;
    private String image;

    public void setLikes(int likes) {
        this.likes = likes;
    }
    public void determineColors(){

        String url1="http://localhost:8080/clothes/findById/";
        String url2="http://localhost:8080/accessories/findById/";
        RestTemplate restTemplate = new RestTemplate();

        List<Clothes> clothesList = new ArrayList<>();
        for(String id: clothes){
            ResponseEntity<Optional<Clothes>> responseEntity = restTemplate.exchange(url1+id,  HttpMethod.GET,null,new ParameterizedTypeReference<Optional<Clothes>>() {});
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                Optional<Clothes> optionalClothes = responseEntity.getBody();
                optionalClothes.ifPresent(clothesList::add);
            }

        }
        for(Clothes clo : clothesList){
            List<String> cloColors = clo.getColors();
            for(String str: cloColors){
                if(!colors.contains(str)){
                    colors.add(str);
                }
            }
        }
        List<Accessories> accessoriesList = new ArrayList<>();
        for(String id: accessories){
            ResponseEntity<Optional<Accessories>> responseEntity = restTemplate.exchange(url2+id,  HttpMethod.GET,null,new ParameterizedTypeReference<Optional<Accessories>>() {});
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                Optional<Accessories> optionalAccessories = responseEntity.getBody();
                optionalAccessories.ifPresent(accessoriesList::add);
            }

        }
        System.out.println(accessoriesList);
        for(Accessories acc : accessoriesList){
            List<String> accColors = acc.getColors();
            for(String str: accColors){
                if(!colors.contains(str)){
                    colors.add(str);
                }
            }
        }
        System.out.println(colors);




    }
}


