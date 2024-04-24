package com.vylitkova.wardrobe.items.post;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/getAllPosts")
    public ResponseEntity<List<Post>> getAllPosts(){
        return new ResponseEntity<>(postService.allPosts(), HttpStatus.OK);
    }

    @GetMapping("/getAllPostsByUser/{user_id}")
    public ResponseEntity<List<Post>> getAllUsers(@PathVariable String user_id){
        return new ResponseEntity<>(postService.allPostsByUser(user_id), HttpStatus.OK);
    }

    @PostMapping("/addPost")
    public ResponseEntity<?> addPost(@RequestBody Post post){
        return ResponseEntity.ok(postService.save(post));
    }

    @PatchMapping("/{user_id}/changeDescription/{post_id}")
    public ResponseEntity<?> update(@PathVariable ObjectId user_id,@PathVariable ObjectId post_id, @RequestBody String description) {
        return new ResponseEntity<>(postService.updateDescription(user_id,post_id, description), HttpStatus.OK);
    }
    @PatchMapping("/putLike/{post_id}")
    public ResponseEntity<?> putLike(@PathVariable ObjectId post_id, @RequestBody String user) {
        return new ResponseEntity<>(postService.updateLikes(post_id, user,"+"), HttpStatus.OK);
    }
    @PatchMapping("/takeLike/{post_id}")
    public ResponseEntity<?> takeLike(@PathVariable ObjectId post_id, @RequestBody String user) {
        return new ResponseEntity<>(postService.updateLikes(post_id, user,"-"), HttpStatus.OK);
    }
    @PatchMapping("/addComment/{post_id}")
    public ResponseEntity<?> updateComment(@PathVariable ObjectId post_id, @RequestBody String comment) {
        return new ResponseEntity<>(postService.addComment(post_id, comment), HttpStatus.OK);
    }
    @DeleteMapping("/{user_id}/deletePost/{post_id}")
    public ResponseEntity<?>  deletePost(@PathVariable ObjectId user_id,@PathVariable ObjectId post_id){
        return ResponseEntity.ok(postService.deleteById(user_id,post_id));
    }
}
