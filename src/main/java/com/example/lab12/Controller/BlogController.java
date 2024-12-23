package com.example.lab12.Controller;

import com.example.lab12.API.ApiResponse;
import com.example.lab12.Model.Blog;
import com.example.lab12.Model.MyUser;
import com.example.lab12.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/show-blogs")
    public ResponseEntity<?> showBlogs() {
        List<Blog> allBlogs = blogService.getAllBlog();
        return ResponseEntity.status(200).body(allBlogs);
    }

    @PostMapping("/my-blog/add")
    public ResponseEntity<?> addBlog(@AuthenticationPrincipal MyUser user, @RequestBody @Valid Blog blog) {
        blogService.addToMyBlog(user.getId(), blog);
        return ResponseEntity.status(200).body(new ApiResponse("blog added successfully"));
    }

    @PutMapping("/my-blog/update/blog-id/{blogId}")
    public ResponseEntity<?> updateBlog(@PathVariable Integer blogId,@AuthenticationPrincipal MyUser user, @RequestBody @Valid Blog blog) {
        blogService.updateMyBlog(blogId, user.getId(), blog);
        return ResponseEntity.status(200).body(new ApiResponse("blog updated successfully"));
    }

    @DeleteMapping("/my-blog/delete/blog-id/{blogId}")
    public ResponseEntity<?> deleteBlog(@PathVariable Integer blogId, @AuthenticationPrincipal MyUser user) {
        blogService.deleteMyBlog(blogId, user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("blog deleted successfully"));
    }

    @GetMapping("/my-blog/all")
    public ResponseEntity<?> allMyBlogs(@AuthenticationPrincipal MyUser user) {
        List<Blog> allBlogs = blogService.getAllMyBlog(user.getId());
        return ResponseEntity.status(200).body(allBlogs);
    }

    @GetMapping("/my-blog/blog-id/{blogId}")
    public ResponseEntity<?> blogById(@PathVariable Integer blogId, @AuthenticationPrincipal MyUser user) {
        Blog blog = blogService.getMyBlog(blogId, user.getId());
        return ResponseEntity.status(200).body(blog);
    }

    @GetMapping("/my-blog/title/{title}")
    public ResponseEntity<?> blogByTitle(@PathVariable String title, @AuthenticationPrincipal MyUser user) {
        Blog blog = blogService.getBlogByTitle(title);
        return ResponseEntity.status(200).body(blog);
    }

}
