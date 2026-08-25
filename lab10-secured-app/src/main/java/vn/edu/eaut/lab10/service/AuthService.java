package vn.edu.eaut.lab10.service;

import vn.edu.eaut.lab10.model.User;
import vn.edu.eaut.lab10.repository.UserRepository;

public class AuthService {

    private final UserRepository userRepository = new UserRepository();

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || !user.isActive()) {
            return null;
        }
        if (!user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }

    public void changePassword(Integer userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId);
        if (user == null || !user.getPassword().equals(oldPassword)) {
            throw new IllegalArgumentException("Mật khẩu cũ không chính xác!");
        }
        user.setPassword(newPassword);
        userRepository.update(user);
    }

    public void updateProfile(Integer userId, String fullName) {
        User user = userRepository.findById(userId);
        if (user != null) {
            user.setFullName(fullName);
            userRepository.update(user);
        }
    }
}