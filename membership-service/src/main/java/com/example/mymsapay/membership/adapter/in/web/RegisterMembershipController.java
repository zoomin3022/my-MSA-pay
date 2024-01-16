package com.example.mymsapay.membership.adapter.in.web;

import com.example.mymsapay.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@WebAdapter
public class RegisterMembershipController {

    @RequestMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test Controller");
    }
}
