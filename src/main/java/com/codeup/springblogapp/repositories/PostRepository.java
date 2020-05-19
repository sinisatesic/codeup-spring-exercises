package com.codeup.springblogapp.repositories;


import com.codeup.springblogapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
//    Post getAllById(long id);

    Post getPostById(Long id);
    Post getPostByTitle(String title);
//    Post deleteById(long id);
}
