package com.vylitkova.wardrobe.items.post;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public List<Post> allPosts(){return postRepository.findAll();}
    public List<Post> allPostsByUser(String id){return postRepository.findPostByUser(id);}

    public Post updateDescription(ObjectId user,ObjectId id, String text) {
        Post updatePost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post's not exist with id: ","id",id.toHexString()));
        if (updatePost.getUser().equals(user.toHexString())) {
            updatePost.setDescription(text);
            postRepository.save(updatePost);
        }else return null;
        return updatePost;
    }
    public Post addComment(ObjectId id, String text) {
        Post updatePost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post's not exist with id: ","id",id.toHexString()));
        updatePost.getComments().add(text);
        postRepository.save(updatePost);
        return updatePost;
    }
    public Post updateLikes(ObjectId id, String user, String action) {
        Post updatePost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post's not exist with id: ","id",id.toHexString()));
        switch (action) {
            case "+" -> {
                if (!updatePost.getLikes().contains(user)) updatePost.getLikes().add(user);
            }
            case "-" -> {
                updatePost.getLikes().remove(user);
            }
        }
        postRepository.save(updatePost);
        return updatePost;
    }
    public Post save(Post post) {
        postRepository.save(post);
        return post;
    }
    public boolean deleteById(ObjectId user, ObjectId id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post's not exist with id: ","id",id.toHexString()));
        if (post.getUser().equals(user.toHexString())) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
