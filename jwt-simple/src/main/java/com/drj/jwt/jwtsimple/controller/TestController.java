package com.drj.jwt.jwtsimple.controller;

import com.drj.jwt.jwtsimple.common.CommonResult;
import com.drj.jwt.jwtsimple.model.UserModule;
import com.drj.jwt.jwtsimple.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试
 *
 * @author drj
 */
@Controller
@Api(description = "测试")
@RequestMapping("/test")
public class TestController {


    @ApiOperation(value = "测试地址")
    @GetMapping(value = "/hello")
    @ResponseBody
    public CommonResult<String> sayHello() {

        return CommonResult.success("Hello Word!!!");
    }
}
