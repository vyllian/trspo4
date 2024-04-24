package com.vylitkova.wardrobe.items.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vylitkova.wardrobe.items.accessories.Accessories;
import com.vylitkova.wardrobe.items.clothes.Clothes;
import com.vylitkova.wardrobe.items.outfits.Outfit;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    ItemsInterface itemsInterface;


    public List<User> allUsers(){return userRepository.findAll();}
    public List<Clothes> allClothes(ObjectId id) {
        String itemsJson= userRepository.findAllItems(id);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        try {
            node = mapper.readTree(itemsJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode itemsNode = node.get("items");
        List<String> itemsList = mapper.convertValue(itemsNode, List.class);
        List<Clothes> clothes = new ArrayList<>();
        for (String item:
             itemsList) {
            try{
                Clothes s = Objects.requireNonNull(itemsInterface.getClothesById(new ObjectId(item)).getBody()).get();
                clothes.add(s);
            }catch (NullPointerException ignored) {

            }

        }

        return clothes;
    }
    public List<Accessories> allAccessories(ObjectId id) {
        String itemsJson= userRepository.findAllItems(id);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        try {
            node = mapper.readTree(itemsJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode itemsNode = node.get("items");
        List<String> itemsList = mapper.convertValue(itemsNode, List.class);
        List<Accessories> accessories = new ArrayList<>();
        for (String item:
                itemsList) {
            try{
                Accessories s = itemsInterface.getAccessoryById(new ObjectId(item)).getBody().get();
                accessories.add(s);
            }catch (NoSuchElementException ignored){}
        }
        return accessories;
    }
    public List<Outfit> allOutfits(ObjectId id) {
        String outJson= userRepository.findOutfits(id);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        try {
            node = mapper.readTree(outJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode itemsNode = node.get("outfits");
        List<String> outList = mapper.convertValue(itemsNode, List.class);
        List<Outfit> outfits = new ArrayList<>();
        for (String item:
                outList) {
            try{
               Outfit s = itemsInterface.getOutfitById(new ObjectId(item)).getBody().get();
                outfits.add(s);
            }catch (NoSuchElementException ignored){}

        }
        return outfits;
    }

    public List<User> allFollowers(ObjectId id){
        String folJson= userRepository.findFollowers(id);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        try {
            node = mapper.readTree(folJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode folowNode = node.get("followers");
        List<String> folowersList = mapper.convertValue(folowNode, List.class);
        List<User> followers = new ArrayList<>();
        for (String f:
                folowersList) {
            User u = Objects.requireNonNull(userRepository.findUserById(f).get());
            followers.add(u);
        }
        return followers;
    }
    public List<User> allFollowings(ObjectId id){
        String folJson= userRepository.findFollowings(id);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        try {
            node = mapper.readTree(folJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode folowNode = node.get("followings");
        List<String> followingsList = mapper.convertValue(folowNode, List.class);
        List<User> followings = new ArrayList<>();
        for (String f:
                followingsList) {
            User u = Objects.requireNonNull(userRepository.findUserById(f).get());
            followings.add(u);
        }
        return followings;
    }
    public User update(User user, ObjectId id) {
        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User's not exist with id: ","id",id.toHexString()));
        userRepository.save(user);
        return user;
    }
    public User addItem(ObjectId id, String item) {
        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User's not exist with id: ","id",id.toHexString()));
        updateUser.getItems().add(item);
        userRepository.save(updateUser);
        return updateUser;
    }
    public User addOutfit(ObjectId id, String out) {
        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User's not exist with id: ","id",id.toHexString()));
        updateUser.getOutfits().add(out);
        userRepository.save(updateUser);
        return updateUser;
    }
    public User addFollowing(ObjectId id, String followingId) {
        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User's not exist with id: ","id",id.toHexString()));
        updateUser.getFollowings().add(followingId);
        userRepository.save(updateUser);
        User updateFollowing = userRepository.findUserById(followingId)
                .orElseThrow(() -> new ResourceNotFoundException("User's not exist with id: ","id",followingId));
        updateFollowing.getFollowers().add(id.toString());
        userRepository.save(updateFollowing);
        return updateUser;
    }
    public User save(User user) {
        userRepository.save(user);
        return user;
    }
    public boolean deleteById(ObjectId id){
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

}