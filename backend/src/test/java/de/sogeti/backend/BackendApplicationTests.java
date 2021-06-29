package de.sogeti.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class BackendApplicationTests {

	@Autowired
	UserController userController;
	
	@Autowired
	UserRepository userRepository;
	
	int id = 0;
	int eid = 111111;
	
	public int getCurrenId() {
		
	    List<User> allUser = userController.getUsers();
	    for (User user : allUser) {
			if(user.getEid() == eid) {
				id = user.getId();
			}
		}
	    return id;
	}
	
	@Test
	@Order(1)
	public void testSaveNewUser() {
		User user = new User(1, 111111, "User", "user@sogeti.de", "added");
	    Optional<User> userOptional = userRepository.
	            findStudentByEmail(user.getEmail());
	    Optional<User> userEid = userRepository.
	    		findEid(user.getEid());
        if (userOptional.isPresent() || userEid.isPresent()) {
        }
		User saveUser = userController.postUser(user);
		assertNotNull(saveUser);
		
	}

	@Test
	@Order(2)
	public void testFindAll() {
		List<User> userList = userController.getUsers();
		assertThat(userList).size().isGreaterThan(0);
	}

	@Test
	@Order(3)
	public void testFindById() {
		int findeId = getCurrenId();
		User user = userController.getUser(findeId);
		System.out.println(findeId);
		assertEquals("User", user.getName());
	}

	@Test
	@Order(4)
	public void testUpdateUser() {
		int findeId = getCurrenId();
		System.out.println(findeId);
		User user = userController.getUser(findeId);
		user.setEid(111111);
		user.setEmail("emre@sogeti.de");
		user.setName("Ucal Emre");
		user.setPlace("Frankfurt");
		User savedUser = userController.putUser(user);
		assertThat(savedUser.getEmail().isBlank());
	}

	@Test
	@Order(5)
	public void testDeleteById() {
		int findeId = getCurrenId();
		userController.deleteUser(findeId);
//		assertThat(findeId).isNegative();
		assertThat(userRepository.existsById(findeId));
	}}
