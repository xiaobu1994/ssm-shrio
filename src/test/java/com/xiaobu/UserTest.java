package com.xiaobu;

import com.xiaobu.entity.SysUser;
import com.xiaobu.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/12/24 16:27
 * @description V1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {

    @Autowired
    private SysUserService userService;


    @Test
    public void add(){
        SysUser user = new SysUser();
        user.setUsername("root-12");
        user.setPassword("113506-12");
        userService.insert(user);
        System.out.println("插入数据成功。。。。");
    }

}
