package com.mathew.user.external.services;

import com.mathew.user.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/api/hotels/{hotelId}")
    Hotel getHotelDetails(@PathVariable("hotelId") String hotelId);
}
