package com.mathew.user.external.services;

import com.mathew.user.entity.Hotel;
import com.mathew.user.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("/api/ratings/all/byUserId/{userId}")
    Rating[] getUserRatings(@PathVariable("userId") String userId);
}
