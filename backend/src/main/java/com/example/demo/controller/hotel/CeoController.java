package com.example.demo.controller.hotel;

import com.example.demo.dto.ceo.AvailableRoomRequest;
import com.example.demo.dto.ceo.AvailableRoomResponse;
import com.example.demo.dto.hotel.RoomRequest;
import com.example.demo.service.ceo.CeoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping
public class CeoController {

    @Autowired
    CeoService ceoService;

    @PostMapping("/calendar")
    public void calendar (@RequestBody AvailableRoomRequest availableRoomRequest){
        log.info("***"+availableRoomRequest.toString());

        ceoService.register(availableRoomRequest);
    }

    @PostMapping("/roomList")
    public List<AvailableRoomResponse> roomList(@RequestBody AvailableRoomRequest availableRoomRequest){

        return ceoService.list(availableRoomRequest);
    }

    @PostMapping("/roomTypeList")
    public List<AvailableRoomResponse> roomTypeList (@RequestBody RoomRequest RoomRequest){
        log.info("writer:"+RoomRequest);
        return ceoService.findRoomType(RoomRequest.getWriter());
    }
}
