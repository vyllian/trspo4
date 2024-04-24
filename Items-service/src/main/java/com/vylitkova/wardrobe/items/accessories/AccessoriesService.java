package com.vylitkova.wardrobe.items.accessories;

import com.vylitkova.wardrobe.items.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccessoriesService {
    @Autowired
    private AccessoriesRepository accessoriesRepository;

    public List<Accessories> allAccessories(){return accessoriesRepository.findAll();}
    public List<Accessories> allByColor(String color) {return accessoriesRepository.findAllByColorsContaining(color);}

    public List<Accessories> allByType(String type) {return accessoriesRepository.findAllByType(type);}

    public Accessories save(Accessories accessories) {
        accessoriesRepository.save(accessories);
        return accessories;
    }
    public boolean deleteById(ObjectId id){
        if (accessoriesRepository.existsById(id)) {
            accessoriesRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Accessories updateTypeById(ObjectId id, Accessories accessories) {
        Accessories updateItem = accessoriesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Accessory's not exist with id: ","id",id.toHexString()));
        accessoriesRepository.save(accessories);
        return accessories;
    }

    public Optional<Accessories> accessoryById(ObjectId id) {
        return accessoriesRepository.findById(id);
    }
}
