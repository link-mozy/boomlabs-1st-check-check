package com.boomlabs.checkapp.service;

import com.boomlabs.checkapp.mapper.CheckMapper;
import com.boomlabs.checkapp.model.CheckIn;
import com.boomlabs.checkapp.model.CheckInDTO;
import com.boomlabs.checkapp.model.CheckInList;
import com.boomlabs.checkapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckService {

    @Autowired
    CheckMapper checkMapper;

    public void checkIn (CheckInDTO checkInDTO) throws Exception{
        String discordId = checkInDTO.getDiscord_id();
        User user = checkMapper.selectUserByDiscordId(checkInDTO.getDiscord_id());
        if(user == null) {
            throw new Exception("CHECK-IN ERROR: 사용자 조회 실패");
        } else {
            Timestamp date = new Timestamp(new Date().getTime());
            CheckIn checkIn = new CheckIn(
                        user.getUserId(),
                        checkInDTO.getDiscord_channel_id(),
                        date);
            checkMapper.checkInUser(checkIn);
        }
    }

    public void register (User user) throws Exception {
        int exists = checkMapper.existsCheck(user);
        if (exists != 0) {
            throw new Exception("REGISTER ERROR: 이미 등록된 사용자 정보");
        } else {
            exists = checkMapper.existsUser(user);
            if (exists != 0) {
                checkMapper.updateUserAddress(user);
            } else {
                checkMapper.registerUser(user);
            }
        }

    }

    public List<HashMap> showList(Map param) throws Exception {
        return checkMapper.selectCheckInList(param);
    }

    public List<HashMap> showMyCheckIn(Map param) throws Exception {
        return checkMapper.selectCheckInDateByDiscordChannelIdAndDiscordId(param);
    }
}
