package com.wrh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

/**
 * Created by wuranghao on 2017/5/13.
 */
//@SessionAttributes({"name","time"})
@Controller
public class HelloController {

    /**
     * 第一种方法：使用ModelAndView完成模型数据到视图中的传递
     * */
    @RequestMapping(value = "/testModelAndView.do",method = RequestMethod.GET)
    public ModelAndView testModelAndView(){
        String viewName = "hello";
        ModelAndView modelAndView = new ModelAndView(viewName);

        modelAndView.addObject("time",new Date());
        modelAndView.addObject("name","ModelAndView");
        return modelAndView;
    }

    /**
     * 第二种方法：使用Model／Map来完成模型数据到视图中的传递
     * */
    @RequestMapping(value = "/testModel.do",method = RequestMethod.GET)
    public String testModel(Model model){
        model.addAttribute("time",new Date());
        model.addAttribute("name","Model");
        return "hello";
    }

    @RequestMapping(value = "/testMap.do",method = RequestMethod.GET)
    public String testMap(Map<String,Object> map){
        map.put("time",new Date());
        map.put("name","Map");
        return "hello";
    }
    /**
     * 第三种方法：在类级别使用@SessionAttributes注解，完成模型数据到视图间的传递
     * */
    @RequestMapping(value = "/testSessionAttribute.do",method = RequestMethod.GET)
    public String testSessionAttribute(Map<String,Object> map){
        map.put("time",new Date());
        map.put("name","SessionAttribute");
        return "sessionAttribute";
    }

    /**
     * 第四种方法：使用ModelAttribute来完成模型数据到视图中的传递
     * */

    /**
     * @ModelAttribute注解在方法的入參上，在页面中使用${user.name}即可访问
     * */
    @RequestMapping(value = "/testModelAttribute.do",method = RequestMethod.GET)
    public String testModelAttribute(@ModelAttribute("user") User user ){
        user.setName("wojiushimogui");
        return "modelAttribute";

    }
//    /**
//     * 此方法不行，在页面中使用${name}不能访问，解决方法：需要显示添加name到Model中。
//     * */
//    @RequestMapping(value = "/testModelAttribute2.do",method = RequestMethod.GET)
//    public String testModelAttribute(@ModelAttribute("name") String name ){
//        name="wojiushimogui2";
//        return "modelAttribute2";
//
//    }
    /**
     * 对象合并指定对象名称
     * */
    @RequestMapping(value = "/testModelAttribute4.do",method = RequestMethod.GET)
    public String testModelAttribute4(@ModelAttribute("myUser") User user ){
        //对模型对象中的对象user进行更改
        user.setAge(20);
        return "modelAttribute4";

    }
    @ModelAttribute(value = "myUser")//指定对象名称，并添加到模型对象中
    public User myUser(){
        User user = new User();
        user.setName("wojiushimogui");
        return user;
    }

    /**
     * 将接受到的参数保存到模型中，进而有模型传递到视图中
     * */
    @ModelAttribute
    public void para(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);

    }
    @RequestMapping(value = "/testModelAttribute3.do",method = RequestMethod.GET)
    public String testModelAttribute2(){
        return "modelAttribute2";
    }

    /**@String abc
     * 将@ModelAttribute 注解在有返回值的方法上，
     * 则当用户请求 http://localhost:8080/test/testModelAttributeInReturnValueMethod.do时，
     * 首先访问addUser方法，返回User对象，model属性的名称没有指定，它由返回类型隐含表示，
     * 如这个方法返回User类型，那么这个model属性的名称是user。
     *这个例子中model属性名称有返回对象类型隐含表示，model属性对象就是方法的返回值。它无须要特定的参数
     * */

    @ModelAttribute
    public User addUser(User user){
        user.setName("Model Attribute in Mehtod ,this method has return value");
        return user;

    }

    @RequestMapping(value = "/testModelAttributeInReturnValueMethod.do",method = RequestMethod.GET)
    public String testModelAttributeInReturnValueMethod(){
        return "modelAttributeInRVMethod";
    }

    /**
     * @ModelAttribute注释void返回值的方法
     * */
    @ModelAttribute
    public void addUser(User user,Model model){
        user.setName("Model Attribute in Mehtod ,this method  not return value");
        model.addAttribute("user",user);
    }
    @RequestMapping(value = "/testModelAttributeInNonReturnValueMethod.do",method = RequestMethod.GET)
    public String testModelAttributeInNonReturnValueMethod(){
        return "modelAttributeInNonRVMethod";
    }

}
