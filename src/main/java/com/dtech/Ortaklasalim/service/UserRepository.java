package com.dtech.Ortaklasalim.service;

import com.dtech.Ortaklasalim.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> { }
