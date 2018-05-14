package com.lhdb.game.webSocket;

import org.apache.log4j.Logger;
import org.springframework.web.socket.*;

import com.lhdb.game.entity.Users;

import java.util.Map;

/**
 * title: 
 * des: 
 * @author Horst
 * @date 2016年11月10日 下午4:38:32
 * @version V1.0
 */
public class WebSocketHander implements WebSocketHandler {
    private static final Logger logger = Logger.getLogger(WebSocketHander.class);
    private SessionManager sessionManager = SessionManager.instance();
    
    //初次链接成功执行 连接成功时候，会触发UI上onopen方法
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("链接成功......");
        Object temp = session.getAttributes().get(SessionManager.USER_SESSION);
        if(temp!= null){
            Users user = (Users)temp; //用户信息
            sessionManager.put(user.getUserid(), session);
        }
    }

    //接受消息处理消息 在UI在用js调用websocket.send()时候，会调用该方法
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        Map<String, Object> attributes = webSocketSession.getAttributes();
        Users user = (Users) attributes.get(SessionManager.USER_SESSION);
        if(user!=null){
        	sessionManager.sendMessageToUser(user.getUserid(), webSocketMessage.getPayload()+"");
        }
    }
    
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if(webSocketSession.isOpen()){
            webSocketSession.close();
        }
        logger.error("链接出错，关闭链接......"+throwable.getMessage());
        sessionManager.remove(webSocketSession);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        logger.debug("链接关闭......" + closeStatus.getReason());
        sessionManager.remove(webSocketSession);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
