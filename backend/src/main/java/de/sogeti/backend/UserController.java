package de.sogeti.backend;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public User postUser(@RequestBody User user) {
        Optional<User> userOptional = userRepository.
                findStudentByEmail(user.getEmail());
        
        Optional<User> userEid = userRepository.
        		findEid(user.getEid());
        
        if (userOptional.isPresent() || userEid.isPresent()) {
        	throw new IllegalStateException("email existiert");
        }
    
        return userRepository.save(user);
    }
    

    @PutMapping("/")
    public User putUser(@RequestBody User user) {
        User updateUser = userRepository.findById(user.getId()).orElse(null);
        updateUser.setName(user.getName());
        updateUser.setEid(user.getEid());
        updateUser.setEmail(user.getEmail());
        updateUser.setPlace(user.getPlace());
        return userRepository.save(updateUser);
    }

    @DeleteMapping("/{id}")
    public Integer deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return id;
    }
}