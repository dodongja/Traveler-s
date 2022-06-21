package com.example.demo.dto.ceo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AvailableRoomResponse{
        Long id;
        String checkDate;
        Long numberOfRoom;
        Long price;
        String useDate;
        Long roomNo;
        String roomName;

        public AvailableRoomResponse(String roomName, Long roomNo){
        this.roomName=roomName;
        this.roomNo=roomNo;
        }

        public AvailableRoomResponse(String roomName,String checkDate,Long numberOfRoom,Long price,String useDate){
        this.roomName=roomName;
        this.checkDate=checkDate;
        this.numberOfRoom=numberOfRoom;
        this.price=price;
        this.useDate=useDate;
        }

}
