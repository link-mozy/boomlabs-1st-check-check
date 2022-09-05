package com.boomlabs.checkapp.controller;

import com.boomlabs.checkapp.model.CheckInDTO;
import com.boomlabs.checkapp.model.CheckInList;
import com.boomlabs.checkapp.model.User;
import com.boomlabs.checkapp.model.UserDTO;
import com.boomlabs.checkapp.service.CheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@Slf4j
public class CheckController {

    @Autowired
    CheckService checkService;
    private ResponseEntity responseEntity;

    /**
     * 출석 기록
     * @return
     */
    @PostMapping("/check-in")
    public ResponseEntity checkIn (@RequestBody CheckInDTO checkInDTO) {
        log.info(checkInDTO.toString());
        Map<String, Object> map = new HashMap<>();
        try {
            checkService.checkIn(checkInDTO);
            map.put("status", true);
            map.put("message", "체크인 성공");
        } catch (Exception e){
            log.info(e.getMessage());
            map.put("status", false);
            map.put("message", "체크인 실패");
        }
        return ResponseEntity.ok(map);
    }

    /**
     * 지갑 주소 등록
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity register (@RequestBody UserDTO dto) {
        log.info(dto.toString());
        Map<String, Object> map = new HashMap<>();
        try {
            User user = new User(dto.getUser_address(), dto.getDiscord_channel_id(), dto.getDiscord_id());
            checkService.register(user);
            map.put("status", true);
            map.put("message", "등록 성공");
        } catch (Exception e){
            log.info(e.getMessage());
            map.put("status", false);
            map.put("message", "등록 실패");
        }
        return ResponseEntity.ok(map);
    }

    /**
     * 현재 까지의 출석부 리스트 출력 (확인 용)
     * @return
     */
    @GetMapping("/show-list/{discordChannelId}/{conditionCount}")
    public ResponseEntity showList (@PathVariable("discordChannelId") String discordChannelId,
                                          @PathVariable("conditionCount") int conditionCount) {
        log.info(String.format("show-list param: \n discordChannelId: %s \n conditionCount: %d", discordChannelId, conditionCount));
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, String> param = new HashMap<>();
            param.put("discordChannelId", discordChannelId);
            param.put("conditionCount", Integer.toString(conditionCount));
            List<HashMap> list = checkService.showList(param);
            map.put("status", true);
            map.put("message", "조회 성공");
            map.put("data", list);
        } catch (Exception e){
            log.info(e.getMessage());
            map.put("status", false);
            map.put("message", "조회 실패");
        }
        return ResponseEntity.ok(map);
    }

    /**
     * 오늘까지 체크인한 기록 리스트업
     * @return
     */
    @GetMapping("/show-my-checkins/{discordChannelId}/{discordId}")
    public ResponseEntity showMyCheckins (@PathVariable("discordChannelId") String discordChannelId,
                                          @PathVariable("discordId") String discordId) {
        log.info(String.format("show-my-checkins param: \n discordChannelId: %s \n conditionCount: %s", discordChannelId, discordId));
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, String> param = new HashMap<>();
            param.put("discordChannelId", discordChannelId);
            param.put("discordId", discordId);
            List<HashMap> list = checkService.showMyCheckIn(param);
            map.put("status", true);
            map.put("message", "조회 성공");
            map.put("data", list);
        } catch (Exception e){
            log.info(e.getMessage());
            map.put("status", false);
            map.put("message", "조회 실패");
        }
        return ResponseEntity.ok(map);
    }

}
