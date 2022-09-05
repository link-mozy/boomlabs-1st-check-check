package com.boomlabs.checkapp.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class CheckInList {
    private String discordId;
    private String count;
}
