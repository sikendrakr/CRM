package com.crm.Controller;

import com.crm.Repository.CommentRepository;
import com.crm.Repository.PostRepository;
import com.crm.entity.Comment;
import com.crm.entity.Post;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public CommentController(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }
    @PostMapping
    public String createComment(
            @RequestBody Comment comment,
            @RequestParam Long postId
    ){
        Post post = postRepository.findById(postId).get();
        comment.setPost(post);
        commentRepository.save(comment);
        return "Comment created successfully";
    }
}

