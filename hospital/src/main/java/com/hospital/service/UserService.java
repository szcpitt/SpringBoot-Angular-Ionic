package com.hospital.service;

import com.hospital.model.User;

public interface UserService {
    User findByUsername(String username);
}
