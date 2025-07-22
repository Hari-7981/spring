//package com.haripriya.Tailor.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.http.server.ServletServerHttpRequest;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.server.HandshakeInterceptor;
//
//import java.util.Map;
//
//public class CustomHandshakeInterceptor implements HandshakeInterceptor {
//
//    @Autowired
//    private JwtService jwtService;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Override
//    public boolean beforeHandshake(
//            ServerHttpRequest request, ServerHttpResponse response,
//            WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
//
//        if (request instanceof ServletServerHttpRequest servletRequest) {
//            String authHeader = servletRequest.getServletRequest().getHeader("Authorization");
//
//            if (authHeader != null && authHeader.startsWith("Bearer ")) {
//                String jwt = authHeader.substring(7);
//                String username = jwtService.extractUsername(jwt);
//
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//                if (jwtService.isTokenValid(jwt, userDetails)) {
//                    attributes.put("user", userDetails); // Save user for later
//                }
//            }
//        }
//
//        return true;
//    }
//
//    @Override
//    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
//
//    }
//}