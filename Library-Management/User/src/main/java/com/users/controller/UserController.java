package com.users.controller;

import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.users.entity.Users;




@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/admin")
public class UserController {
	
	
	@Autowired
    private RestTemplate restTemplate;

    public UserController() {
        this.restTemplate = new RestTemplate();
    }

    public Users addUserByAdmin(Users user) {
        String url = "http://localhost:8081/users";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Users> request = new HttpEntity<>(user, headers);
        ResponseEntity<Users> response = restTemplate.postForEntity(url, request, Users.class);
        return response.getBody();
    }

    public List<Users> getAllUsers() {
        String url = "http://localhost:8081/users";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<Users[]> response = restTemplate.getForEntity(url, Users[].class);
        Users[] users = response.getBody();
        return Arrays.asList(users);
    }

    public Users getUserById(Integer id) {
        String url = "http://localhost:8081/users/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<Users> response = restTemplate.exchange(url, HttpMethod.GET, entity, Users.class, id);
        return response.getBody();
    }

    public Users updateUser(Integer id, Users user) {
        String url = "http://localhost:8081/users/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Users> request = new HttpEntity<>(user, headers);
        ResponseEntity<Users> response = restTemplate.exchange(url, HttpMethod.PUT, request, Users.class, id);
        return response.getBody();
    }
}
	
//	@Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostMapping("/users")
////    @PreAuthorize("hasRole('Admin')")
//    public Users addUserByAdmin(@RequestBody Users user) {
////        Role role = new Role();
//////        role.setRoleName(UserConstant.DEFAULT_ROLE);
////        role.setRoleName(role.getRoleName());
////        Set<Role> setRole = new HashSet<>();
////        setRole.add(role);
////        user.setRole(setRole);
//        String password = user.getPassword();
//        String encryptPassword = passwordEncoder.encode(password);
//        user.setPassword(encryptPassword);
//        userRepository.save(user);
//        return user;
//    }
//
//    @GetMapping("/users")
//    @PreAuthorize("hasRole('Admin')")
//    public List<Users> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    @PreAuthorize("hasRole('Admin')")
//    @GetMapping("/users/{id}")
//    public ResponseEntity<Users> getUserById(@PathVariable Integer id) throws NotFoundException {
//        Users user = userRepository.findById(id).orElseThrow(() -> new NotFoundException());
//        return ResponseEntity.ok(user);
//    }
//
//    @PreAuthorize("hasRole('Admin')")
//    @PutMapping("/users/{id}")
//    public ResponseEntity<Users> updateUser(@PathVariable Integer id, @RequestBody Users userDetails) throws NotFoundException {
//        Users user = userRepository.findById(id).orElseThrow(() -> new NotFoundException());
//
//        user.setName(userDetails.getName());
//        user.setRole(userDetails.getRole());
//        user.setUsername(userDetails.getUsername());
//
//        Users updatedUser = userRepository.save(user);
//        return ResponseEntity.ok(updatedUser);
//    }
	

