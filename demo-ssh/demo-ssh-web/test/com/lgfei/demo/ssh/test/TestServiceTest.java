package com.lgfei.demo.ssh.test;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lgfei.demo.ssh.services.ITestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext.xml"})
public class TestServiceTest {
	
	@Autowired
	private ITestService service;

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		List<?> list = service.findAll();
		System.out.println(list.size());
	}
}
