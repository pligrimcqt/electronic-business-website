package org.example.hystrix;


import com.itheima.b2b.commonmodule.model.User;
import org.example.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceHystrix implements UserService {
    @Override
    public User login(String uaccount) {
        return null;
    }

    @Override
    public int register(String upassword, String uname, String usex) {
        return 0;
    }
}
