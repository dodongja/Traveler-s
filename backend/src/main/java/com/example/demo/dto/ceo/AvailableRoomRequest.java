package com.example.demo.dto.ceo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class AvailableRoomRequest{
        List<String>settingDates;
        Long roomSetting;
        Long settingPrice;
        Long settingAvailableRoom;
        String checkDate;
        String writer;

        }