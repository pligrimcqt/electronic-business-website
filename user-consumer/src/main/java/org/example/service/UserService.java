package org.example.service;

import com.itheima.b2b.commonmodule.model.User;
import org.example.hystrix.UserServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "b2b-user-provider",fallback = UserServiceHystrix.class)
public interface UserService {
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public User login(@RequestParam(value = "uname") String uname);

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public  int register(@RequestParam(value = "upassword")String upassword,
                         @RequestParam(value = "uname")String uname,
                         @RequestParam(value = "usex") String usex);
}
