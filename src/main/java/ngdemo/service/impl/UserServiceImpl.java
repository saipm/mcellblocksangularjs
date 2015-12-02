package ngdemo.service.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ngdemo.domain.User;
import ngdemo.repositories.contract.UserRepository;
import ngdemo.service.contract.UserService;

import java.util.List;

@Singleton
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Inject
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    
    public List getAllUsers() {
        return this.userRepository.getAll();
    }

    
    public User getById(int id) {
        return this.userRepository.getById(id);
    }

    
    public User createNewUser(User user) {
        User u = this.userRepository.create(user);
        return u;
    }

    
    public User update(User user) {
        return this.userRepository.update(user);
    }

    
    public void remove(int id) {
        this.userRepository.remove(id);
    }

    
    public int getNumberOfUsers() {
        return this.userRepository.getNumberOfUsers();
    }
}
