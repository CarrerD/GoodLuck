package de.sogeti.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class BackendApplicationTests {
	
	int eid = 2000;
	
	public int getId() {
		int id = 0;
		List<User> userList = userRepo.findAll();
		for (User user : userList) {
			if(user.getEid() == eid) 
				id = user.getId();
			}
		return id;
	}
	
	@Autowired
	UserRepository userRepo;

	@Test
	public void testSaveNewUser() {
		User user = new User(1020,2000,"Hallo2aa","junit2@sogeti.de","hallotest2");
		User saveUser = userRepo.save(user);
		assertNotNull(saveUser);
	}
		
	@Test
	public void testFindAll() {
		List<User> userList = userRepo.findAll();
		assertThat(userList).size().isGreaterThan(0);	
	}
	
	
	@Test
	public void testFindById(){
		int id = getId();
		User user = userRepo.findById(id).get();
		assertEquals("Ucal Emre", user.getName());
	}
	
	@Test
	public void testUpdateUser() {
		int id = getId();
		System.out.println(id);
		User user = userRepo.findById(id).get();
		user.setEmail("emre@sogeti.de");
		user.setName("Ucal Emre");
		user.setPlace("Frankfurt");
		userRepo.save(user);
		assertNotEquals("München", userRepo.findById(id).get().getPlace());
	}
	
	
	
	@Test
	public void testDeleteById(){
		int id = getId();
		userRepo.deleteById(id);
		assertThat(userRepo.existsById(id)).isFalse();
	}
	

}
