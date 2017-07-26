package com.lgfei.demo.spring.boot.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.lgfei.demo.spring.boot.common.constants.Constants;
import com.lgfei.demo.spring.boot.common.enums.Authors;
import com.lgfei.demo.spring.boot.common.model.PageResult;
import com.lgfei.demo.spring.boot.common.model.PageVO;
import com.lgfei.demo.spring.boot.common.model.UserVO;
import com.lgfei.demo.spring.boot.common.util.StringUtil;
import com.lgfei.demo.spring.boot.core.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController
{
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("/test/{name}")
    public String testThymeleaf(@PathVariable("name") String name, Model model)
    {
        LOG.info("name:{}", name);
        if (Authors.LGFEI.getName().equals(name))
        {
            model.addAttribute("name", name);
            return "user";
        }
        else
        {
            return "forbidden";
        }
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UserVO findById(@PathVariable("id") String id)
    {
        LOG.info("id:{}", id);
        if (StringUtil.isLong(id))
        {
            return userService.findById(Long.parseLong(id));
        }
        return null;
    }
    
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<UserVO> findAll()
    {
        return userService.findAll();
    }
    
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject find(UserVO userVO, @Valid PageVO pageVO, BindingResult bindingResult)
        throws JSONException
    {
        JSONObject json = new JSONObject();
        if (bindingResult.hasErrors())
        {
            json.put("message", bindingResult.getFieldError().getDefaultMessage());
            return json;
        }
        PageResult<UserVO> result = userService.find(userVO, pageVO);
        json.put("result", result);
        json.put("message", Constants.SUCCESS);
        return json;
    }
}
