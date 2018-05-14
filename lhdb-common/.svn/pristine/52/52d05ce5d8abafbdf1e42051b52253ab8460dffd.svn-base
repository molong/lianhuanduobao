package com.lhdb.game.webSocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;

import com.lhdb.game.entity.Users;
import com.lhdb.game.util.business.Code;

import javax.servlet.http.HttpSession;

import java.util.Map;

/**
 * title: 握手
 * des: 
 * @author Horst
 * @date 2016年11月10日 下午4:43:12
 * @version V1.0
 */
public class HandshakeInterceptor implements org.springframework.web.socket.server.HandshakeInterceptor {

	//初次握手访问前
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
    	boolean istrue = false;
    	if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(true);
            if (session != null) {
                //使用userName区分WebSocketHandler，以便定向发送消息
                Object temp = session.getAttribute(Code.loginFlagName);
                if(temp!= null){
                    if(temp instanceof Users){
                        Users user = (Users)temp;
                        map.put(SessionManager.USER_SESSION,user);  //存入数据，方便在hander中获取
                        istrue = true;
                    }
                }
            }
        }
        return istrue;
    }

    //初次握手访问后
    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        System.err.println("有人访问了：" + serverHttpRequest.getRemoteAddress());
    }
}