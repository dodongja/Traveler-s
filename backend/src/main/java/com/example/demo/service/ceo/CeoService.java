package com.example.demo.service.ceo;

import com.example.demo.dto.ceo.AvailableRoomRequest;
import com.example.demo.dto.ceo.AvailableRoomResponse;

import java.util.List;

public interface CeoService {
    public void register(AvailableRoomRequest availableRoomRequest);
    public List<AvailableRoomResponse>list(AvailableRoomRequest availableRoomRequest);
    public List<AvailableRoomResponse> findRoomType(String writer);

}
