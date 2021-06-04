package com.vyasmeet.rest.webservices.restfulwebservices.repository;

import com.vyasmeet.rest.webservices.restfulwebservices.model.Post;
import com.vyasmeet.rest.webservices.restfulwebservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
