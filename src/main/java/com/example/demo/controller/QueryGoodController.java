package com.example.demo.controller;

import com.example.demo.base.BaseInquirer;
import com.example.demo.entity.GoodEntity;
import com.example.demo.entity.QGoodEntity;
import com.example.demo.jpa.GoodJPA;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: rogue
 * @Description: 测试querydsl
 * @Package: com.example.demo.controller
 * @Date: 2017/12/5
 * @Time: 15:51
 */
@RestController
@RequestMapping(value = "/good")
public class QueryGoodController {

    @Autowired
    private GoodJPA goodJPA;

    //注入EntityManager
    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/query")
    public List<GoodEntity> queryGood(){
        //querydsl查询实体
        QGoodEntity _good = QGoodEntity.goodEntity;
        //构建JPA查询对象
        JPAQuery<GoodEntity> jpaQuery = new JPAQuery<>(entityManager);
        //返回查询接口
        return jpaQuery
                //查询字段
                .select(_good)
                //查询表
                .from(_good)
                //查询条件
                .where(_good.type.id.eq(Long.valueOf("1")))
                //返回结果
                .fetch();
    }

    /**
     * @Author: rogue
     * @Description: spring data jpa整合querydsl查询
     * @ClassName: QueryGoodController
     * @Date: 2017/12/5
     * @Time: 16:10
     */
    @RequestMapping(value = "/join")
    public List<GoodEntity> join(){
        //querydsl查询实体
        QGoodEntity _good = QGoodEntity.goodEntity;

        //查询条件
        BooleanExpression expression = _good.type.id.eq(Long.valueOf("1"));
       /*
       //单个进行查询
        //执行查询
        Iterator<GoodEntity> iterator = goodJPA.findAll(expression).iterator();
        List<GoodEntity> goods = new ArrayList<>();
        //转成list
        while (iterator.hasNext()){
            goods.add(iterator.next());
        }
        return goods;*/

       //使用封装方法进行查询
       //自定义查询对象
        BaseInquirer inquirer = new BaseInquirer();
        //添加查询条件
        inquirer.putExpression(expression);
        //返回查询结果
        return inquirer.iteratorToList(goodJPA.findAll(inquirer.buildQuery()));
    }
}
