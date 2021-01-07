package com.shida.labchecksys.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMessage {

    protected Long id;

    // 收消息用户的id
    private Long userId;

    // 发消息用户的id
    private Long userFromId;

    private String messageTitle;

    private String messageContent;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date messageTime;

    protected int isRead;//0是未读1是已读

    // 消息备注，可以为空，不超过500个字符
    private String messageRemark;
}
