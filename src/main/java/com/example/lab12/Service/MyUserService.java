package com.example.lab12.Service;

import com.example.lab12.API.ApiException;
import com.example.lab12.Model.MyUser;
import com.example.lab12.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserService {

    private final MyUserRepository myUserRepository;

    public MyUserService(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }
    // CRUD

    // register .. add
    public void register(MyUser myUser){
        myUser.setRole("USER");
        String hashedPassword = new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hashedPassword);
        myUserRepository.save(myUser);
    }

    // get all users .. admin
    public List<MyUser> getAllUsers(Integer user_id){
      MyUser myUser = myUserRepository.findMyUserById(user_id);
      if(myUser==null){
          throw new ApiException("Wrong user id");
      }
      return myUserRepository.findAllByRole("USER");
    }

    // update User .. user
    public void updateUser(Integer user_id,MyUser user){
        MyUser myUser = myUserRepository.findMyUserById(user_id);
        if(myUser==null){
            throw new ApiException("Wrong user id");
        }
        myUser.setUsername(user.getUsername());
        myUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        myUserRepository.save(myUser);
    }

    // delete user .. user or maybe the admin
    public void deleteUser(Integer id){
        MyUser myUser=myUserRepository.findMyUserById(id);
        if(myUser==null){
            throw new ApiException("User Not Found");
        }
        myUserRepository.delete(myUser);
    }

}
