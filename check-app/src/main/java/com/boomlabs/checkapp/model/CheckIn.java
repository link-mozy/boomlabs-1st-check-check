package com.boomlabs.checkapp.model;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class CheckIn {
    private int userId;
    private String discordChannelId;
    private Timestamp checkInDate;
}
