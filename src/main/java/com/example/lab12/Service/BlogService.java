package com.example.lab12.Service;

import com.example.lab12.API.ApiException;
import com.example.lab12.Model.Blog;
import com.example.lab12.Model.MyUser;
import com.example.lab12.Repository.BlogRepository;
import com.example.lab12.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final MyUserRepository userRepository;


    public List<Blog> getAllBlog() {
        return blogRepository.findAll();
    }

    public void addToMyBlog(Integer user_id, Blog blog) {

        MyUser user = userRepository.findMyUserById(user_id);
        if (user == null) {
            throw new ApiException("user not found");
        }
        blog.setUser(user);
        blogRepository.save(blog);

    }

    public void updateMyBlog(Integer blog_id, Integer user_id, Blog blog) {
        MyUser user = userRepository.findMyUserById(user_id);
        Blog oldBlog = blogRepository.findBlogById(blog_id);
        if (user == null) {
            throw new ApiException("user not found");
        }
        if (oldBlog == null) {
            throw new ApiException("blog not found");
        }
        if (user_id != oldBlog.getUser().getId()) {
            throw new ApiException("you are not authorized to update this blog");
        }

        oldBlog.setTitle(blog.getTitle());
        oldBlog.setBody(blog.getBody());
        blogRepository.save(oldBlog);
    }

    public void deleteMyBlog(Integer blog_id, Integer user_id) {
        MyUser user = userRepository.findMyUserById(user_id);
        Blog blog = blogRepository.findBlogById(blog_id);
        if (user == null) {
            throw new ApiException("user not found");
        }
        if (blog == null) {
            throw new ApiException("blog not found");
        }
        if (user_id != blog.getUser().getId()) {
            throw new ApiException("you are not authorized to delete this blog");
        }
        blogRepository.delete(blog);
    }


    public List<Blog> getAllMyBlog(Integer user_id) {
        MyUser user = userRepository.findMyUserById(user_id);
        if (user == null) {
            throw new ApiException("user not found");
        }
        return blogRepository.findBlogsByUser(user);
    }

    public Blog getMyBlog(Integer blog_id, Integer user_id) {
        MyUser user = userRepository.findMyUserById(user_id);
        Blog blog = blogRepository.findBlogById(blog_id);
        if (user == null) {
            throw new ApiException("user not found");
        }
        if (blog == null) {
            throw new ApiException("blog not found");
        }
        if (user_id != blog.getUser().getId()) {
            throw new ApiException("unauthorized, user id mismatch");
        }
        return blog;
    }

    public Blog getBlogByTitle(String title) {
        Blog blog = blogRepository.findBlogByTitle(title);
        if (blog == null) {
            throw new ApiException("blog with this title not found");
        }
        return blog;
    }

}
