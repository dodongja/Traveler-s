package com.example.demo.service.ceo;

import com.example.demo.controller.room.AvailableRoom;
import com.example.demo.dto.ceo.AvailableRoomRequest;
import com.example.demo.dto.ceo.AvailableRoomResponse;
import com.example.demo.entity.hotel.Hotel;
import com.example.demo.entity.room.Room;
import com.example.demo.repository.ceo.AvailableRoomRepository;
import com.example.demo.repository.hotel.HotelRepository;
import com.example.demo.repository.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CeoServiceImpl implements CeoService {

    private final AvailableRoomRepository availableRoomRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    @Override
    public List<AvailableRoomResponse> list(AvailableRoomRequest availableRoomRequest){

        Optional<Hotel> roomType= hotelRepository.findByWriter(availableRoomRequest.getWriter());
        Hotel hotel=roomType.get();
        List<AvailableRoomResponse> rooms = new ArrayList<>();
        for(int i=0;i<hotel.getRooms().size();i++){

            Long roomNo=hotel.getRooms().get(i).getRoomNo();

            List<AvailableRoom>availableRoomList=availableRoomRepository.finByRoomNo(roomNo);

            for(AvailableRoom room:availableRoomList){

                if(room.getCheckDate().equals(availableRoomRequest.getCheckDate())){

                    AvailableRoomResponse roomInfo = new AvailableRoomResponse(
                            room.getRoom().getRoomName(),room.getCheckDate(),room.getNumberOfRoom(),room.getPrice(),
                            room.getUseDate());

                    rooms.add(roomInfo);
                }
            }
        }

        return rooms;
    }

    @Override
    public List<AvailableRoomResponse> findRoomType(String writer){
        //writer로 호텔 구함
        List<Hotel>roomType=hotelRepository.findByWriter(writer);

        Hotel hotel=roomType.get();
        log.info("hotelNo:"+hotel.getHotelNo());
        Long hotelNo=hotel.getHotelNo();
        List<Room>rooms = roomRepository.findByHotelNo(hotelNo);
        List<AvailableRoomResponse> roomNames= new ArrayList<>();

        for(Room room: rooms){
            AvailableRoomResponse roomInfo= new AvailableRoomResponse(room.getRoomName(),room.getRoomNo());
            roomNames.add(roomInfo);
        }

        return roomNames;
    }

    @Override
    public void register(AvailableRoomRequest availableRoomRequest){
        Room room= roomRepository.findByRoomNo(availableRoomRequest.getRoomSetting());

        for(int i=0;i<availableRoomRequest.getSettingDates().size();i++){
            AvailableRoom availableRoom= AvailableRoom.builder()
                    .price(availableRoomRequest.getSettingPrice())
                    .numberOfRoom(availableRoomRequest.getSettingAvailableRoom())
                    .useDate(availableRoomRequest.getSettingDates().get(i))
                    .checkDate(availableRoomRequest.getSettingDates().get(i).substring(0,7))
                    .room(room)
                    .build();


            availableRoomRepository.save(availableRoom);
        }
    }


}
