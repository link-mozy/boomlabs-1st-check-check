package com.boomlabs.checkapp.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class User {
    public User(String userAddress, String discordChannelId, String discordId) {
        this.userAddress = userAddress;
        this.discordChannelId = discordChannelId;
        this.discordId = discordId;
    }

    private int userId;
    // 유저 지갑 주소
    private String userAddress;
    // 디스코드 채널 아이디
    private String discordChannelId;
    // 사용자 디스코드 아이디
    private String discordId;
}
