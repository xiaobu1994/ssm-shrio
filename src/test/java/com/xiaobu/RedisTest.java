package com.xiaobu;

import com.xiaobu.entity.SysUser;
import com.xiaobu.mapper.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/12/20 11:32
 * @description V1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;



    @Test
    public void test(){
        SysUser user = new SysUser();
        user.setUsername("root-12");
        user.setPassword("113506-12");
        //userMapper.insert(user);
        System.out.println("插入数据成功。。。。");
        redisTemplate.opsForValue().set("user",user,1, TimeUnit.HOURS);
    }


}
