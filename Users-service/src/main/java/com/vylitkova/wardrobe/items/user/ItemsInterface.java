package com.vylitkova.wardrobe.items.user;

import com.vylitkova.wardrobe.items.accessories.Accessories;
import com.vylitkova.wardrobe.items.clothes.Clothes;
import com.vylitkova.wardrobe.items.outfits.Outfit;
import org.bson.types.ObjectId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient("ITEMS-SERVICE")
public interface ItemsInterface {
    @GetMapping("/items/outfits/findOutfitById/{id}")
    public ResponseEntity<Optional<Outfit>> getOutfitById(@PathVariable ObjectId id) ;
    @GetMapping("/items/clothes/findClothesById/{id}")
    public ResponseEntity<Optional<Clothes>> getClothesById(@PathVariable ObjectId id);
    @GetMapping("/items/accessories/findAccessoryById/{id}")
    public ResponseEntity<Optional<Accessories>> getAccessoryById(@PathVariable ObjectId id);

}
