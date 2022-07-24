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
	@Test
	void contextLoads() {
	}
}
