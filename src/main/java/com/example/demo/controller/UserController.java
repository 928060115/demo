package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.jpa.UserJPA;
import com.example.demo.service.UserService;
import com.example.demo.utils.LoggerUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.controller
 * @Date: 2017/11/29
 * @Time: 15:39
 */
@RestController
@RequestMapping(value = "/user")
public class UserController{

    @Autowired
    private UserJPA userJPA;

    @Resource
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    public String validator(@Valid UserEntity userEntity, BindingResult result){
        if (!result.hasErrors()) {
            return "验证通过，"+"名称："+userEntity.getUsername()+",密码："+userEntity.getPassword();
        }
        StringBuffer msg = new StringBuffer();
        //获取错误字段集合
        List<FieldError> fieldErrors = result.getFieldErrors();
        //获取本地locale，zh_CN
        Locale currentLocale = LocaleContextHolder.getLocale();
        //遍历错误字段获取错误信息
        for (FieldError fieldError : fieldErrors){
            //获取错误信息
            String errorMsg = messageSource.getMessage(fieldError,currentLocale);
            //添加到错误消息集合内
            msg.append(fieldError.getField()+":"+errorMsg+",");
        }
        return msg.toString();
    }

    /**
     * @Author: rogue
     * @Description: 查询用户列表
     * @ClassName: UserController
     * @Date: 2017/11/29
     * @Time: 15:47
     */
    @ApiOperation(value = "用户列表",notes = "查询所有用户信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserEntity> list() {
        return userJPA.findAll();
    }

    @ApiOperation(value = "缓存获取用户列表信息",notes = "查询所有用户信息")
    @RequestMapping(value = "/listForRedis",method = RequestMethod.GET)
    public List<UserEntity> listForRedis(){
        return userService.list();
    }

    /**
     * @Author: rogue
     * @Description: 添加用户
     * @ClassName: UserController
     * @Date: 2017/11/29
     * @Time: 15:50
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public UserEntity save(UserEntity userEntity) {
        return userJPA.save(userEntity);
    }

    /**
     * @Author: rogue
     * @Description: 根据id删除用户信息
     * @ClassName: UserController
     * @Date: 2017/11/29
     * @Time: 15:52
     */

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public List<UserEntity> delete(Integer id) {
        userJPA.delete(id.longValue());
        return userJPA.findAll();
    }


    /**
     * @Author: rogue
     * @Description: 自定义数据库查询语句测试
     * @ClassName: UserController
     * @Date: 2017/12/5
     * @Time: 14:17
     */
    @RequestMapping(value = "/getUserBySex",method = RequestMethod.GET)
    public List<UserEntity> getUserBySex(int sex,HttpServletRequest request){
        request.setAttribute(LoggerUtil.LOGGER_RETURN,userJPA.nativeQuery(sex));
        return userJPA.nativeQuery(sex);
    }

    /**
     * @Author: rogue
     * @Description: 自定义数据库删除语句测试
     * @ClassName: UserController
     * @Date: 2017/12/5
     * @Time: 14:20
     */
    
    @RequestMapping(value = "/deleteByUsername",method = RequestMethod.GET)
    public String deleteByUsername(UserEntity userEntity){
        userJPA.deleteByUsername(userEntity.getUsername(),userEntity.getPassword());
        return "删除用户信息成功";
    }

    /**
     * @Author: rogue
     * @Description: 测试分页查询
     * @ClassName: UserController
     * @Date: 2017/12/5
     * @Time: 14:54
     */
    @RequestMapping(value = "/cutPage",method = RequestMethod.GET)
    public List<UserEntity> cutPage(int pageNo,int pageSize){
        UserEntity user = new UserEntity();
//        user.setSort("asc");
        user.setPageNo(pageNo);
        user.setPageSize(pageSize);
        //获取排序对象
        Sort.Direction sort_direction = Sort.Direction.ASC.toString().equalsIgnoreCase(user.getSort())?Sort.Direction.ASC:Sort.Direction.DESC;
        //设置排序对象参数
        Sort sort = new Sort(sort_direction,user.getSidx());
        //创建分页对象
        PageRequest pageRequest = new PageRequest(user.getPageNo()-1,user.getPageSize(),sort);
        //执行分页查询
        return userJPA.findAll(pageRequest).getContent();
    }

    /**
     * @Author: rogue
     * @Description: 用户登录
     * @ClassName: UserController
     * @Date: 2017/11/30
     * @Time: 11:13
     */
    @ApiOperation(value = "用户登录",notes = "通过用户名密码登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid UserEntity user,BindingResult bindingResult,HttpServletRequest request) {
        //登录成功
        boolean flag = true;
        String result = validator(user,bindingResult);
        //根据用户名查询用户是否存在
        UserEntity userEntity = userJPA.findOne(new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("username"), user.getUsername()));
                return null;
            }
        });

        //用户不存在
        if (userEntity == null) {
            flag = false;
            result = "用户不存在，登录失败";
        }
        //密码错误
        if (!userEntity.getPassword().equals(user.getPassword())) {
            flag = false;
            result = "用户密码错误，登录失败";
        }

        //登录成功
        if (flag){
            //将用户写进session
            request.getSession().setAttribute("_session_user",userEntity);
        }
        return result;
    }

    @ApiOperation(value = "用户登录",notes = "通过用户名密码登录")
    @ApiImplicitParam(name = "user",value = "用户的详细实体",required = true,dataType = "UserEntity")
    @RequestMapping(value = "/login2", method = RequestMethod.POST)
    public String login2(@RequestBody UserEntity user) {
        //登录成功
        boolean flag = true;
        String result = "登录成功";
        //根据用户名查询用户是否存在
        UserEntity userEntity = userJPA.findOne(new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("username"), user.getUsername()));
                return null;
            }
        });

        //用户不存在
        if (userEntity == null) {
            flag = false;
            result = "用户不存在，登录失败";
        }
        //密码错误
        if (!userEntity.getPassword().equals(user.getPassword())) {
            flag = false;
            result = "用户密码错误，登录失败";
        }

        //登录成功
        /*if (flag){
            //将用户写进session
            request.getSession().setAttribute("_session_user",userEntity);
        }*/
        return result;
    }
}