package com.vylitkova.wardrobe.items.outfits;

import com.vylitkova.wardrobe.items.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutfitService {
    @Autowired
    private OutfitRepository outfitRepository;

    public List<Outfit> allOutfit(){return outfitRepository.findAll();}

    public Outfit save(Outfit outfit) {
       outfit.determineColors();
        outfitRepository.save(outfit);
        return outfit;
    }
    public Outfit updateLike(ObjectId id,Outfit outfit) {

        Outfit updateOutfit = outfitRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Outfit's not exist with id: ","id",id.toHexString()));
        //updateOutfit.setLikes(updateOutfit.getLikes()+1);
        outfitRepository.save(outfit);
        return outfit;
    }
    public Optional<Outfit> singleOutfitById(ObjectId id){
        return outfitRepository.findById(id);
    }
    public List<Outfit> outfitsBySeason(String season){
        return outfitRepository.findAllBySeason(season);
    }

    public List<Outfit> outfitsByStyle(String style){
        return outfitRepository.findAllByStyle(style);
    }
    public List<Outfit> outfitsByMood(String mood){
        return outfitRepository.findAllByMood(mood);
    }
    public List<Outfit> outfitsByColor(String color){
        return outfitRepository.findAllByColorsContaining(color);
    }

    public boolean deleteById(ObjectId id){
        if (outfitRepository.existsById(id)) {
            outfitRepository.deleteById(id);
            return true;
        }
        return false;
    }





}
