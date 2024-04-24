package com.vylitkova.wardrobe.items.clothes;

import com.vylitkova.wardrobe.items.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClothesService {
    @Autowired
    private ClothesRepository clothesRepository;

    public List<Clothes> allClothes(){
        return clothesRepository.findAll();
    }
    public Optional<Clothes> clothesById(ObjectId id){return clothesRepository.findById(id);}

    public List<Clothes>  clothesByCategory(String category) {return clothesRepository.findAllByCategory(category);}
    public List<Clothes> clothesByType(String type) {return clothesRepository.findAllByType(type);}
    public List<Clothes> clothesByColor(String color) {return clothesRepository.findAllByColorsContaining(color);    }
    public Clothes update(Clothes clothes, ObjectId id) {
        Clothes updateItem = clothesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clothes not exist with id: ","id",id.toHexString()));

        clothesRepository.save(clothes);
        return clothes;
    }

    public Clothes save(Clothes clothes) {
        clothesRepository.save(clothes);
        return clothes;
    }
    public boolean deleteById(ObjectId id){
        if (clothesRepository.existsById(id)) {
            clothesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
