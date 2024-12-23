package com.example.lab12.Repository;

import com.example.lab12.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Integer> {

    MyUser findMyUserById(Integer id);

    MyUser findMyUserByUsername(String username);

    List<MyUser> findAllByRole(String role);
}
