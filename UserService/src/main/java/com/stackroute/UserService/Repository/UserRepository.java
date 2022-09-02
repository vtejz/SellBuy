package com.stackroute.UserService.Repository;

import com.stackroute.UserService.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String > {

   List<User> findAllByType(String type);

}
