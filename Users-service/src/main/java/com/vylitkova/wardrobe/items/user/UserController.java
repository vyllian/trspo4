package com.vylitkova.wardrobe.items.user;

import com.vylitkova.wardrobe.items.accessories.Accessories;
import com.vylitkova.wardrobe.items.clothes.Clothes;
import com.vylitkova.wardrobe.items.outfits.Outfit;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}/getMyClothes")
    public ResponseEntity<List<Clothes>> getAllClothes(@PathVariable ObjectId id){
        return new ResponseEntity<>(userService.allClothes(id), HttpStatus.OK);
    }
    @GetMapping("/{id}/getMyAccessories")
    public ResponseEntity<List<Accessories>> getAllAccessories(@PathVariable ObjectId id){
        return new ResponseEntity<>(userService.allAccessories(id), HttpStatus.OK);
    }
    @GetMapping("/{id}/getMyOutfits")
    public ResponseEntity<List<Outfit>> getAllOutfits(@PathVariable ObjectId id){
        return new ResponseEntity<>(userService.allOutfits(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/getMyFollowers")
    public ResponseEntity<List<User>> getFollowers(@PathVariable ObjectId id){
        return new ResponseEntity<>(userService.allFollowers(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/getMyFollowings")
    public ResponseEntity<List<User>> getFollowings(@PathVariable ObjectId id){
        return new ResponseEntity<>(userService.allFollowings(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/changeUserInfo")
    public ResponseEntity<?> updateUserInfo(@PathVariable ObjectId id, @RequestBody User user) {
        return new ResponseEntity<>(userService.update(user, id), HttpStatus.OK);
    }
    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user){
        return ResponseEntity.ok(userService.save(user));
    }

    @PatchMapping("/{id}/addItem")
    public ResponseEntity<?> addItem(@PathVariable ObjectId id, @RequestBody String itemId) {
        return new ResponseEntity<>(userService.addItem(id, itemId), HttpStatus.OK);
    }

    @PatchMapping("/{id}/addOutfit")
    public ResponseEntity<?> addOutfit(@PathVariable ObjectId id, @RequestBody String outId) {
        return new ResponseEntity<>(userService.addOutfit(id, outId), HttpStatus.OK);
    }

    @PatchMapping("/{id}/follow")
    public ResponseEntity<?> followUser(@PathVariable ObjectId id, @RequestBody String userId) {
        return new ResponseEntity<>(userService.addFollowing(id, userId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/deleteUser")
    public ResponseEntity<?>  deleteUser(@PathVariable ObjectId id){
        return ResponseEntity.ok(userService.deleteById(id));
    }
}
