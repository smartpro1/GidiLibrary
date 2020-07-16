package com.gidilibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gidilibrary.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User getByRegNo(String userRegNo);

}
