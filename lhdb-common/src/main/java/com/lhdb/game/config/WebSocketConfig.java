package com.lhdb.game.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.lhdb.game.webSocket.HandshakeInterceptor;
import com.lhdb.game.webSocket.WebSocketHander;

/**
 * title: 开启websocket
 * des: 
 * @author Horst
 * @date 2016年11月10日 下午4:48:57
 * @version V1.0
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(),"/moco").addInterceptors(myInterceptor());
        registry.addHandler(myHandler(),"/sockjs/moco").addInterceptors(myInterceptor()).withSockJS();
    }
    
    @Bean
    public WebSocketHander myHandler() {
        return new WebSocketHander();
    }
    
    @Bean
    public HandshakeInterceptor myInterceptor() {
        return new HandshakeInterceptor();
    }

}
