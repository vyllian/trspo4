package com.vylitkova.wardrobe.items.clothes;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/items")
public class ClothesController {
    @Autowired
    private ClothesService clothesService;

    @GetMapping("/clothes")
    public ResponseEntity<List<Clothes>> getAllClothes(){
        return new ResponseEntity<>(clothesService.allClothes(), HttpStatus.OK);
    }

    @GetMapping("/clothes/{category}")
    public ResponseEntity<List<Clothes>> getClothesByCategory(@PathVariable String category) {return new ResponseEntity<>(clothesService.clothesByCategory(category),HttpStatus.OK);}

    @GetMapping("/clothes/category/{type}")
    public ResponseEntity<List<Clothes>> getByType(@PathVariable String type) {return new ResponseEntity<>(clothesService.clothesByType(type ), HttpStatus.OK);}

    @GetMapping("/clothes/findByColor/{color}")
    public ResponseEntity<List<Clothes>> getClothesByColor(@PathVariable String color) {
        return new ResponseEntity<>(clothesService.clothesByColor(color), HttpStatus.OK);
    }

//    @GetMapping("/clothes/findClothesById/{id}")
//    public ResponseEntity<Optional<Clothes>> getClothesById(@PathVariable ObjectId id) {
//        return new ResponseEntity<>(clothesService.clothesById(id), HttpStatus.OK);
//    }

    @GetMapping("/clothes/findClothesById/{id}")
    public ResponseEntity<Optional<Clothes>> getClothesById(@PathVariable ObjectId id) {
        return new ResponseEntity<>(clothesService.clothesById(id), HttpStatus.OK);
    }
    @PutMapping("/clothes/update/{id}")
    public ResponseEntity<?> updateCategoryType(@PathVariable ObjectId id, @RequestBody Clothes clothes) {
        return new ResponseEntity<>(clothesService.update(clothes, id), HttpStatus.OK);
    }

    @PostMapping("/clothes/add")
    public ResponseEntity<?> addCloth(@RequestBody Clothes clothes){
        return ResponseEntity.ok(clothesService.save(clothes));
    }

    @DeleteMapping("/clothes/delete/{id}")
    public ResponseEntity<?>  deleteCloth(@PathVariable ObjectId id){
        return ResponseEntity.ok(clothesService.deleteById(id));
    }



}
