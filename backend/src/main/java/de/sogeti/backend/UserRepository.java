package de.sogeti.backend;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    
	@Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findStudentByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.eid = ?1")
	Optional<User> findEid(int eid);
	
}