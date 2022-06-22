package com.example.demo.repository.room;

import com.example.demo.entity.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, String> {

    @Query(value="select * from room m where m.hotel_no=:hotelNo",nativeQuery=true)
    List<Room> findByHotelNo(@Param("hotelNo")Long hotelNo);

    @Query(value="select * from room m where m.room_no=:roomNo",nativeQuery=true)
    Room findByRoomNo(@Param("roomNo")Long roomNo);


