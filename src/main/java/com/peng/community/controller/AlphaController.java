package com.peng.community.controller;

import com.peng.community.util.ComunityUtil;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello Spring Boot";
    }

    @Autowired
    private ComunityUtil comunityUtil;

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        response.setContentType("text/html;charset=utf-8");
        try (PrintWriter writer = response.getWriter();){
            writer.write("<h1>继续努力哇</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(path = "/studnets",method = RequestMethod.GET)
    @ResponseBody
    public String getString(
            @RequestParam(name = "current",required = false,defaultValue = "1") int current,
            @RequestParam(name = "limit",required = false,defaultValue = "10") int limit
    ){
        System.out.println(current);
        System.out.println(limit);
        return "Some students";
    }


    @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(
            @PathVariable("id") int id
    ){
        System.out.println(id);
        return "a student";
    }

    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String student(String name,Integer age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(ModelAndView modelAndView){
        modelAndView.addObject("name","张三");
        modelAndView.addObject("age",30);
        modelAndView.setViewName("/demo/view"); //设置的这个view应该是在templates文件夹下进性查找，因为最后一层都是以.html结尾的文件信息，所以我们设置的名称可以不设置后缀
        return modelAndView;
    }


    @RequestMapping(value = "/school",method = RequestMethod.GET)
    public String getSchoole(Model model){
        model.addAttribute("name","郑州职业技术学院");
        model.addAttribute("age",46);
        return "/demo/view";
    }

    @RequestMapping(value = "/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> emp(){
        Map<String,Object> emp = new HashMap<String, Object>();
        emp.put("name","魏坤鹏");
        emp.put("age","19");
        emp.put("school","郑州职业技术学院");
        return emp;
    }

    @RequestMapping(value = "/emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> emps(){
        List<Map<String,Object>> emps = new ArrayList<>();
        Map<String,Object> emp = new HashMap<String, Object>();
        emp.put("name","魏坤鹏");
        emp.put("age","19");
        emp.put("school","郑州职业技术学院");
        Map<String,Object> emp2 = new HashMap<String, Object>();
        emp2.put("name","魏坤鹏");
        emp2.put("age","19");
        emp2.put("school","郑州职业技术学院");
        emps.add(emp);
        emps.add(emp2);
        return emps;
    }


    //Cookie示例
    @RequestMapping(path = "/cookie/set",method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response){
        //创建Cookie
        Cookie cookie = new Cookie("code",comunityUtil.getUUID());
        //正常来说cookie是仅仅存活再一次会话的，当我们关闭浏览器就会消失，所以我们需要进行设置他的生存时间
        cookie.setMaxAge(600*10);//单位是毫秒
        //我们的Cookie还可以设置携带的范围  只要是在/community/alpha下的访问路径 这个cookie全部都是有效的
        cookie.setPath("/community/alpha");
        //发送cookie
        response.addCookie(cookie);
        return "set cookie";
    }

    @RequestMapping(path = "/cookie/get",method = RequestMethod.GET)
    @ResponseBody
    public String getCookie(@CookieValue("code") String code){
        System.out.println(code);
        return "get Cookie";
    }

    //Session示例  他的默认方位就是整个项目
    @RequestMapping(path = "/session/set",method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session){
        session.setAttribute("id",1);
        session.setAttribute("name","魏坤鹏");
        return "set Session";
    }

    @RequestMapping(path = "/session/get",method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session){
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get Session";
    }





}
