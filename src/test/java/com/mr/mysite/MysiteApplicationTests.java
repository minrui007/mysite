package com.mr.mysite;

import com.mr.mysite.dao.UserDao;
import com.mr.mysite.domain.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MysiteApplicationTests {

	@Autowired
	UserDao userDao;

	@Test
	public void contextLoads() {
		UserDto userDto = new UserDto();
		userDto.setName("张三");
		userDto.setPassword("123");
		userDto.setPhone("431");
		userDao.insert(userDto);
	}

}
