package com.example.demo.repository.ceo;

import com.example.demo.controller.room.AvailableRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AvailableRoomRepository extends JpaRepository<AvailableRoom,Long> {

    @Query(value="select * from available_room m where m.room_no=:roomNo",nativeQuery=true)
    List<AvailableRoom> finByRoomNo(@Param("roomNo")Long roomNo);
}
