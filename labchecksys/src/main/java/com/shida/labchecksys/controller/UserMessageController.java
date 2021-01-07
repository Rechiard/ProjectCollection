package com.shida.labchecksys.controller;

import com.shida.labchecksys.common.JsonResponse;
import com.shida.labchecksys.pojo.User;
import com.shida.labchecksys.pojo.UserMessage;
import com.shida.labchecksys.pojo.dto.UserMessageDto;
import com.shida.labchecksys.pojo.dto.UserMessageSearchCondition;
import com.alibaba.fastjson.JSON;
import com.shida.labchecksys.service.UserMessageService;
import com.shida.labchecksys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/user/message")
public class UserMessageController {


    @Autowired
    UserMessageService userMessageService;

    @Autowired
    UserService userService;

    // 跳转用户消息中心 ,计算未读消息的数量，传给model
    @RequestMapping("/page")
    public String page(Model model, HttpSession session) {
        Long userId = userService.getSessionUserInfo(session).getUserId();
        long countUnread = userMessageService.countUserMessagesByUserIdAndRead(userId, 0);
        model.addAttribute("countUnread", countUnread);
        return "user/message/page";
    }

    ///跳转用户消息详情
    @RequestMapping("/detail")
    public String detail(Model model, Long id) {
        UserMessageDto userMessageDto = userMessageService.getUserMessageDtoById(id);
        if (userMessageDto.getIsRead() == 0) {
            //点了消息，将未读状态更新为已读
            userMessageService.updateUserMessageReadById(id);
        }
        model.addAttribute("Message", userMessageDto);
        return "user/message/detail";
    }


    //查询用户已读还是未读消息的
    @RequestMapping("/getMessageInfo")
    public JsonResponse getReadMessageInfo(HttpSession session, int isRead) {
        Long userId = userService.getSessionUserInfo(session).getUserId();
        UserMessageSearchCondition condition = new UserMessageSearchCondition();
        condition.setUserId(userId);
        condition.setIsRead(isRead);
        List<UserMessageDto> pageInfo = userMessageService.listUserMessages(condition);
        return JsonResponse.toSuccess(pageInfo);
    }

    //删除消息
    @RequestMapping("/delete")
    public JsonResponse delete(Long id) {
        userMessageService.deleteUserMessagesById(id);
        return JsonResponse.toSuccess("删除成功！");
    }

    //发送消息给用户，插入数据库
    @RequestMapping("/insertSendMessage")
    public JsonResponse insertSendMessage(HttpSession session, UserMessage message) {
        Long userId = userService.getSessionUserInfo(session).getUserId();
        message.setUserFromId(userId);
        message.setMessageTime(new Date());
        userMessageService.insertOneUserMessage(message);
        return JsonResponse.toSuccess("发送成功！");
    }

    //批量发送消息给用户，插入数据库
    @RequestMapping("/many/insertSendMessage")
    public Map<String, Object> manyInsertSendMessage(HttpSession session, String ids, UserMessage message) {
        Map<String, Object> map = new HashMap<>(1);
        Long userId = userService.getSessionUserInfo(session).getUserId();
        List<Long> idsParsed = JSON.parseArray(ids, Long.class);
        List<UserMessage> userMessages = new ArrayList<>(idsParsed.size());
        for (Long id : idsParsed) {
            UserMessage userMessage = new UserMessage();
            userMessage.setUserId(id);
            userMessage.setUserFromId(userId);
            userMessage.setMessageTitle(message.getMessageTitle());
            userMessage.setMessageContent(message.getMessageContent());
            userMessage.setMessageTime(new Date());
            userMessage.setMessageRemark(message.getMessageRemark());
            userMessages.add(userMessage);
        }
        try {
            map.put("data", userMessageService.insertManyUserMessages(userMessages));
        } catch (Exception e) {
            e.printStackTrace();
            map.put("data", "failure");
        }
        return map;
    }

    // 发送全体消息给用户，插入数据库 message 新发送的消息
    @RequestMapping("/all/insertSendMessage")
    public Map<String, Object> allInsertSendMessage(HttpSession session, UserMessage message) {
        Map<String, Object> map = new HashMap<>(1);
        Long userId = userService.getSessionUserInfo(session).getUserId();
        List<User> users = userService.findAllUser();
        List<UserMessage> userMessages = new ArrayList<>(users.size());
        for (User user : users) {
            UserMessage userMessage = new UserMessage();
            userMessage.setUserId(user.getUserId());
            userMessage.setUserFromId(userId);
            userMessage.setMessageTitle(message.getMessageTitle());
            userMessage.setMessageContent(message.getMessageContent());
            userMessage.setMessageTime(new Date());
            userMessage.setMessageRemark(message.getMessageRemark());
            userMessages.add(userMessage);

        }
        try {
            map.put("data", userMessageService.insertManyUserMessages(userMessages));
        } catch (Exception e) {
            e.printStackTrace();
            map.put("data", "failure");
        }

        return map;
    }

    /*
       //跳转发送消息
    @RequestMapping("/send")
    public String send(Model model) {
        model.addAttribute("role", JSON.toJSONString(User.ROLES));
        model.addAttribute("department", JSON.toJSONString(CampusEnum.getCampusNamesList()));
        return "user/message/send";
    }

   // 重定向到发送消息页面
    @RequestMapping("/sendForm")
    public String sendForm(Model model, User user) {
        model.addAttribute(ModelConstants.USER_SEND_TO_MODEL_KEY, user);
        model.addAttribute(ModelConstants.USER_SEND_TO_SHOW_MODEL_KEY, "@" + user.getUserRole() + "-" + user.getUserRealName());
        return "user/message/sendForm";
    }
     */

       /*//消息详情页面点击“回复”跳转发送信息页面
    @RequestMapping("/reply")
    public String reply(Model model, User user) {
        model.addAttribute(ModelConstants.USER_SEND_TO_MODEL_KEY, user);
        model.addAttribute(ModelConstants.USER_SEND_TO_SHOW_MODEL_KEY, "@" + user.getUserRole() + "-" + user.getUserRealName());
        return "user/message/sendForm";
    }

    //返回@指定用户的字符串显示结果 如，"@管理员-张三"
    private String getUserSendToShow(User user){
        if (user == null){
            return "";
        }
        return "@" + user.getUserRole() + "-" + user.getUserRealName()+"; ";
    }


    //返回@指定用户的字符串显示结果    *  如，"@管理员-张三"
    private String getUserSendToShow(UserSendTo user){
        if (user == null){
            return "";
        }
        return "@" + user.getUserRole() + "-" + user.getUserRealName()+"; ";
    }

    //回复时重定向到发送消息页面     要回复的用户id      原消息标题
    @RequestMapping("/replyForm")
    public String replyForm(Model model, @RequestParam("id") Long id, @RequestParam(value = "title", required = false) String title) {
        User user = userService.getUserById(id);
        model.addAttribute(ModelConstants.USER_SEND_TO_MODEL_KEY, user);
        model.addAttribute(ModelConstants.USER_SEND_TO_SHOW_MODEL_KEY, getUserSendToShow(user));
        model.addAttribute(ModelConstants.REPLY_TITLE_MODEL_KEY, "回复--" + title);
        return "user/message/sendForm";
    }*/

    /*//重定向到发送全体消息页面
    @RequestMapping("/all/sendForm")
    public String allSendForm(Model model) {
        model.addAttribute(ModelConstants.USER_SEND_TO_SHOW_MODEL_KEY, "@全体用户");
        return "user/message/allSendForm";
    }*/

}
