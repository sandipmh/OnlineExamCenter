package com.onlineexamcenter.comitsa.OnlineExamCenter.repositories;

import com.onlineexamcenter.comitsa.OnlineExamCenter.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {
    public User findUserByEmail(String email);


    @Query("SELECT u FROM User u WHERE " +
            "LOWER(u.firstName) LIKE %:searchTerm% OR " +
            "LOWER(u.lastName) LIKE %:searchTerm% OR " +
            "LOWER(u.email) LIKE %:searchTerm% OR " +
            "CAST(u.mobile AS string) LIKE %:searchTerm%")
    List<User> findBySearchTerm(@Param("searchTerm") String searchTerm);
}
