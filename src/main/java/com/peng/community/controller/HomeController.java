package com.peng.community.controller;

import com.peng.community.entity.DiscussPost;
import com.peng.community.entity.Page;
import com.peng.community.entity.User;
import com.peng.community.service.DiscussPostService;
import com.peng.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    //在首页的Controller层，我们需要使用到帖子和用户的数据，我们需要将这两个数据注入进来进行使用。
    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page){
        //方法调用前，SpringMVC会自动实例化Model和Page,并将Page注入Model
        //所以,在thymeleaf中可以直接访问到Page对象中的数据，而不需要add添加到Model当中
        page.setRows(discussPostService.findDiscussPostRows(0)); //将我们需要的动态值在设置的时候就查询出来
        page.setPath("/index");//设置分页显示时候要使用的路径

        List<DiscussPost> list = discussPostService.findDiscussPosts(0,page.getOffset(),page.getLimit());
        List<Map<String,Object>> discussPosts = new ArrayList<>();
        if(list != null){
            for (DiscussPost discussPost : list) {
                Map<String,Object> map = new HashMap<>();
                map.put("post",discussPost);
                User user = userService.findUserById(Integer.parseInt(discussPost.getUserId()));
                map.put("user",user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        return "index";
    }
}
