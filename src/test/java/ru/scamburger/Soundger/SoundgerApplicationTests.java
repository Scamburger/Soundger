package ru.scamburger.Soundger;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.scamburger.Soundger.entity.Role;
import ru.scamburger.Soundger.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Collections;

@SpringBootTest
class SoundgerApplicationTests {
	@PersistenceContext
	private EntityManager entityManager;
	@Test
	void contextLoads() {
	}

	@Test
	@Transactional
	void addUser(){
		User user=new User();
		Role role=new Role();
		role.setRoleName("ROLE_USER");
		user.setUsername("root");
		user.setPassword("root");
		user.setRoles(Collections.singleton(role));
		entityManager.merge(user);
	}

}
