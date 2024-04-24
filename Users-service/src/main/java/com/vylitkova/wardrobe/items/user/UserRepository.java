package com.vylitkova.wardrobe.items.user;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    @Query(value = "{'_id': ?0}", fields = "{'items': 1, '_id': 0}")
    String findAllItems(ObjectId id);

    @Query(value = "{'_id': ?0}", fields = "{'followers': 1, '_id': 0}")
    String findFollowers(ObjectId id);

    @Query(value = "{'_id': ?0}", fields = "{'followings': 1, '_id': 0}")
    String findFollowings(ObjectId id);

    Optional<User> findUserById(String followingId);
    @Query(value = "{'_id': ?0}", fields = "{'outfits': 1, '_id': 0}")
    String findOutfits(ObjectId id);
}

