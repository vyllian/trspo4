package com.vylitkova.wardrobe.items.accessories;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessoriesRepository extends MongoRepository<Accessories, ObjectId> {
    List<Accessories> findAllByColorsContaining(String color);
    List<Accessories> findAllByType(String type);

}
