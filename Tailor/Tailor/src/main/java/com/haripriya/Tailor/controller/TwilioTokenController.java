//package com.haripriya.Tailor.controller;
//
//import com.twilio.jwt.accesstoken.AccessToken;
//import com.twilio.jwt.accesstoken.VoiceGrant;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/twilio")
//@CrossOrigin(origins = "${cors.allowed-origins}", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}, allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
//public class TwilioTokenController {
//
//    @Value("${twilio.accountSid}")
//    private String accountSid;
//
//    @Value("${twilio.apiKey}")
//    private String apiKey;
//
//    @Value("${twilio.apiSecret}")
//    private String apiSecret;
//
//    @Value("${twilio.applicationSid}")  // Outgoing App SID (starts with AP...)
//    private String applicationSid;
//
//    @GetMapping("/token/{userId}")
//    public ResponseEntity<String> getToken(@PathVariable String userId) {
//        String identity = "user_" + userId;
//
//        VoiceGrant grant = new VoiceGrant();
//        grant.setOutgoingApplicationSid(applicationSid);
//        grant.setIncomingAllow(true);
//
//        AccessToken token = new AccessToken.Builder(
//                accountSid,
//                apiKey,
//                apiSecret
//        ).identity(identity).grant(grant).ttl(3600).build();
//
//        return ResponseEntity.ok(token.toJwt());
//    }
//}
