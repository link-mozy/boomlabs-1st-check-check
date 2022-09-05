package com.boomlabs.checkapp.mapper;

import com.boomlabs.checkapp.model.CheckIn;
import com.boomlabs.checkapp.model.CheckInList;
import com.boomlabs.checkapp.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface CheckMapper {

    User selectUserByDiscordId (String discordId) throws SQLException;

    int existsCheck(User user) throws SQLException;

    int existsUser(User user) throws SQLException;

    int checkInUser (CheckIn checkIn) throws SQLException;

    int registerUser (User user) throws SQLException;

    int updateUserAddress (User user) throws SQLException;

    List<HashMap> selectCheckInList (Map param) throws SQLException;

    List<HashMap> selectCheckInDateByDiscordChannelIdAndDiscordId (Map param) throws SQLException;
}
