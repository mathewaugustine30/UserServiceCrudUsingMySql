package com.mathew.user.service;

import com.mathew.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
	public User save(User user);
	public User update(User student);
	public User get(String id);
	public void delete(String id);
	public List<User> getAllUsers();
}