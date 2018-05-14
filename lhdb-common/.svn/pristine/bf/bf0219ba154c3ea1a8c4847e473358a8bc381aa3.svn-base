package com.lhdb.game.webSocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.lhdb.game.entity.Users;

import java.io.IOException;
import java.util.*;

/**
 * title: Session管理中心
 * des: 
 * @author Horst
 * @date 2016年11月10日 下午4:26:25
 * @version V1.0
 */
public class SessionManager {
    /**
     * session中的用户信息
     */
    public static final String USER_SESSION = "user_session";

    private SessionManager() {
    }

    public static SessionManager instance() {
        return SingletonHolder.sessionManager;
    }

    private static class SingletonHolder {
        private static SessionManager sessionManager = new SessionManager();
    }

    //有id 的用户 k = 用户id
    private HashMap<Integer, WebSocketSession> userMap = new HashMap<>();

    /**
     * 增加一个用户session
     *
     * @param userId 用户id
     * @param webSocketSession
     * @param userType 用户类型
     */
    public void put(Integer userId, WebSocketSession webSocketSession) {
    	userMap.put(userId, webSocketSession);
    }

    /**
     * sss
     * @param webSocketSession
     */
    public void remove(WebSocketSession webSocketSession){
        Map<String, Object> attributes = webSocketSession.getAttributes();
        Users user = (Users) attributes.get(SessionManager.USER_SESSION);
        Integer userId = user.getUserid();
        userMap.remove(userId);
    }
    
    /**
     * 给某个用户发送消息
     *
     * @param userId
     * @param message
     */
    public void sendMessageToUser(long userId, String message) {
        WebSocketSession session = this.userMap.get(userId);
        if (session != null) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(message));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
