package com.dtech.Ortaklasalim.service;

import com.dtech.Ortaklasalim.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> { }
