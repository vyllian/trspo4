package com.vylitkova.wardrobe.items.outfits;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutfitRepository extends MongoRepository<Outfit, ObjectId> {

    List<Outfit> findAllBySeason(String season);
    List<Outfit> findAllByStyle(String style);
    List<Outfit> findAllByMood(String mood);
    List<Outfit> findAllByColorsContaining(String color);




}
