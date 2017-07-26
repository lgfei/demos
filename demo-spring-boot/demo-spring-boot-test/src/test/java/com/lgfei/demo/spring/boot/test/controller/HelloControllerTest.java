package com.lgfei.demo.spring.boot.test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.lgfei.demo.spring.boot.web.controller.HelloController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.lgfei.demo.spring.boot.web.controller.HelloController.class)
public class HelloControllerTest
{
    private MockMvc mvc;
    
    @Before
    public void setUp()
        throws Exception
    {
        mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }
    
    @Test
    public void testHelloGet()
    {
        try
        {
            mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testHelloPost()
    {
        try
        {
            mvc.perform(MockMvcRequestBuilders.post("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}