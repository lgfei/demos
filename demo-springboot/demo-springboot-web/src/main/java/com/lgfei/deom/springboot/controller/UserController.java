package com.lgfei.deom.springboot.controller;

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
import com.lgfei.deom.springboot.common.constants.Constants;
import com.lgfei.deom.springboot.common.constants.Signature;
import com.lgfei.deom.springboot.common.util.StringUtil;
import com.lgfei.deom.springboot.model.PageResult;
import com.lgfei.deom.springboot.model.PageVO;
import com.lgfei.deom.springboot.model.UserVO;
import com.lgfei.deom.springboot.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController
{
    private final static Logger LOG = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("/test/{name}")
    public String testThymeleaf(@PathVariable("name") String name, Model model)
    {
        LOG.info("name:{}", name);
        if (Signature.AUTHOR.equals(name))
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
