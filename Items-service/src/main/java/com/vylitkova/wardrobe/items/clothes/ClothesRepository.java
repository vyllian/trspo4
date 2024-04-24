package com.vylitkova.wardrobe.items.clothes;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClothesRepository extends MongoRepository<Clothes, ObjectId> {
    List<Clothes> findAllByCategory(String category);
    List<Clothes> findAllByType(String type);
    List<Clothes> findAllByColorsContaining(String color);


    Optional<Clothes> findById(ObjectId id);
}
