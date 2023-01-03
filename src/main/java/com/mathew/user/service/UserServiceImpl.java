package com.mathew.user.service;

import com.mathew.user.entity.Hotel;
import com.mathew.user.entity.Rating;
import com.mathew.user.entity.User;
import com.mathew.user.exception.UserNotFoundException;
import com.mathew.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User save(User user) {
        String userId = UUID.randomUUID().toString();
        user.setId(userId);
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        Optional<User> byId = Optional.ofNullable(userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException("Unable to update user. Invalid userId: " + user.getId())));
        return userRepository.save(user);
    }

    @Override
    public User get(String id) {
        Optional<User> byId = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("No Records Found. Invalid userId: " + id)));
        User userReturned =byId.get();
        Rating[] userRatings = restTemplate.getForObject("http://localhost:8012/api/ratings/all/byUserId/"+userReturned.getId(), Rating[].class);

        List<Rating> userRatingsDone = Arrays.stream(userRatings).collect(Collectors.toList());
        userRatingsDone.stream().map(rating-> {
//http://localhost:8011/api/hotels/17fd148c-0ec9-4836-bb52-4cd75cbd5eac
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8011/api/hotels/" + rating.getHotelId(), Hotel.class);
            rating.setHotel(forEntity.getBody());
            return rating;
        }).collect(Collectors.toList());
        userReturned.setRatings(userRatingsDone);
        return userReturned;
    }

    @Override
    public void delete(String id) {
        Optional<User> byId = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Unable to delete user. Invalid userId: " + id)));
        userRepository.delete(byId.get());
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
