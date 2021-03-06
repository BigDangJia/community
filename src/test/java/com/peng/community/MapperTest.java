package com.peng.community;

import com.peng.community.dao.DiscussPostMapper;
import com.peng.community.dao.LoginTicketMapper;
import com.peng.community.dao.UserMapper;
import com.peng.community.entity.DiscussPost;
import com.peng.community.entity.LoginTicket;
import com.peng.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;


//SpringBoot测试类的三大注解配置
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTest {

    //自动注入用户的mapper
    @Autowired
    private UserMapper userMapper;
    //用户登陆的Mapper
    @Autowired
    private LoginTicketMapper ticketMapper;

    //自动注入DiscussPostMapper
    @Autowired
    private DiscussPostMapper discussPostMapper;

    //测试我们使用的查找方法
    @Test
    public void findUserById() {
        User user = userMapper.selectById(101);
        System.out.println(user);
        user = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
        user = userMapper.selectByName("liubei");
        System.out.println(user);
    }

    //测试我们使用的导入方法
    @Test
    public void insertUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("www.baidu.com/101.png");
        user.setCreateTime(new Date());
        //导入用户的信息
        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    //测试我们使用的修改方法
    @Test
    public void updateUser() {
        int row = userMapper.updateStatus(150, 1);
        System.out.println(row);
        row = userMapper.updateHeader(150, "www.mi.com");
        System.out.println(row);
        row = userMapper.updatePassword(150, "123123");
        System.out.println(row);
    }


    @Test
    public void testSelectPosts() {
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(149, 0, 10);
        for (DiscussPost discussPost : discussPosts) {
            System.out.println(discussPost);
        }

        int rows = discussPostMapper.selectDiscussPostRows(149);
        System.out.println(rows);
    }

    //来进行测试登录模块的mapper
    @Test
    public void testInsetLoginTicket(){
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setId(10);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis()+1000 * 60 * 10));
        int i = ticketMapper.insetLoginTicket(loginTicket);
        System.out.println(i);
    }
    //根据登陆的令牌来进行查寻登陆的状况
    @Test
    public void testSelectTicket(){
        LoginTicket loginTicket = ticketMapper.selectByTicket("abc");
        System.out.println(loginTicket);
    }

    //根据登陆的令牌来修改登录i的状态
    @Test
    public void updateTicket(){
        int abc = ticketMapper.updateStatus("abc",1);
        System.out.println(abc);
    }
}
