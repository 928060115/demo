package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.jpa.UserJPA;
import com.example.demo.utils.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.controller
 * @Date: 2017/11/29
 * @Time: 15:39
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserJPA userJPA;

    /**
     * @Author: rogue
     * @Description: 查询用户列表
     * @ClassName: UserController
     * @Date: 2017/11/29
     * @Time: 15:47
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserEntity> list() {
        return userJPA.findAll();
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
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(UserEntity user, HttpServletRequest request) {
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
        if (flag){
            //将用户写进session
            request.getSession().setAttribute("_session_user",userEntity);
        }
        return result;
    }
}