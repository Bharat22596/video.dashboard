package com.gvbr.video.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gvbr.video.dashboard.models.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public List<User> findAllByUserNameAndPassword(String username , String password);
}
