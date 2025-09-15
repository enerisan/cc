package enerisan.incident.repository;

import enerisan.incident.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);



}

// Alternative approach: using a native SQL query with a positional parameter (?1) instead of JPQL.
//@Repository
//public interface UserRepository extends JpaRepository<User, Integer> {
//
//    @Query(
//            value = "SELECT * FROM user WHERE email = ?1",
//            nativeQuery = true
//    )
//    Optional<User> findByEmailNativePositional(String email);
//}