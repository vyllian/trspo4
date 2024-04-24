package com.vylitkova.wardrobe.items.accessories;


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
public class AccessoriesController {
    @Autowired
    private AccessoriesService accessoriesService;

    @GetMapping("/accessories")
    public ResponseEntity<List<Accessories>> getAllAccessories(){
        return ResponseEntity.ok(accessoriesService.allAccessories());
    }
    @GetMapping("/accessories/findByColor/{color}")
    public ResponseEntity<List<Accessories>> getByColor(@PathVariable String color){
        return new ResponseEntity<>(accessoriesService.allByColor(color), HttpStatus.OK);
    }
    @GetMapping("/accessories/{type}")
    public ResponseEntity<List<Accessories>> getByType(@PathVariable String type){
        return new ResponseEntity<>(accessoriesService.allByType(type), HttpStatus.OK);
    }

    @GetMapping("/accessories/findAccessoryById/{id}")
    public ResponseEntity<Optional<Accessories>> getAccessoryById(@PathVariable ObjectId id) {
        return new ResponseEntity<>(accessoriesService.accessoryById(id), HttpStatus.OK);
    }

    @PostMapping("/accessories/add")
    public ResponseEntity<?> addAccessory(@RequestBody Accessories accessory){
        return ResponseEntity.ok(accessoriesService.save(accessory));
    }

    @DeleteMapping("/accessories/delete/{id}")
    public ResponseEntity<?>  deleteOutfit(@PathVariable ObjectId id){
        return ResponseEntity.ok(accessoriesService.deleteById(id));
    }

    @PutMapping("/accessories/update/{id}")
    public ResponseEntity<?> updateCategoryType(@PathVariable ObjectId id, @RequestBody Accessories accessories) {
        return new ResponseEntity<>(accessoriesService.updateTypeById(id, accessories), HttpStatus.OK);
    }
}
