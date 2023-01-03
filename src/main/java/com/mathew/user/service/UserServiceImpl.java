package com.mathew.user.service;

import com.mathew.user.entity.Rating;
import com.mathew.user.entity.User;
import com.mathew.user.exception.UserNotFoundException;
import com.mathew.user.external.services.HotelService;
import com.mathew.user.external.services.RatingService;
import com.mathew.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

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
        Rating[] userRatings = ratingService.getUserRatings(userReturned.getId());
        List<Rating> userRatingsDone = Arrays.stream(userRatings).collect(Collectors.toList());
        userRatingsDone.stream().map(rating-> {
            rating.setHotel(hotelService.getHotelDetails(rating.getHotelId()));
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
