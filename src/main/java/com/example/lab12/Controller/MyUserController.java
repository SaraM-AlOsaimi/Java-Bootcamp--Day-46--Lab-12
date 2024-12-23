package com.example.lab12.Controller;

import com.example.lab12.API.ApiResponse;
import com.example.lab12.Model.MyUser;
import com.example.lab12.Service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog/user")
@RequiredArgsConstructor
public class MyUserController {

    private final MyUserService myUserService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid MyUser myUser){
        myUserService.register(myUser);
        return ResponseEntity.status(200).body(new ApiResponse("Register done Successfully"));
    }


    // get all user
    @GetMapping("/get-all")
    public ResponseEntity<?> getALlUsers(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(myUserService.getAllUsers(myUser.getId()));
    }

    @PutMapping("/update")
    public ResponseEntity<?> Update(@AuthenticationPrincipal MyUser user, @RequestBody @Valid MyUser updateUser) {
           myUserService.updateUser(user.getId(),updateUser);
            return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
        }

    @DeleteMapping("/delete")
    public ResponseEntity<?> Delete(@AuthenticationPrincipal MyUser user) {
        myUserService.deleteUser(user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
        }



}
