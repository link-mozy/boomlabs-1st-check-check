package com.boomlabs.checkapp.model;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 8371380860370944955L;

    private String user_address;
    private String discord_channel_id;
    private String discord_id;
}
