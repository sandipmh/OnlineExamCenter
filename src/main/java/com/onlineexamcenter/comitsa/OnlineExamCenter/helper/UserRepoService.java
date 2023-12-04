package com.onlineexamcenter.comitsa.OnlineExamCenter.helper;

import com.onlineexamcenter.comitsa.OnlineExamCenter.model.User;
import com.onlineexamcenter.comitsa.OnlineExamCenter.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRepoService {
    @Autowired
    private UserRepo userRepo;

    public Optional<User> getUser(Long uid){
        return userRepo.findById(uid);
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
}
