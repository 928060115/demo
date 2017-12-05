package com.example.demo.base;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: rogue
 * @Description:
 * @Package: com.example.demo.base
 * @Date: 2017/12/5
 * @Time: 17:34
 */
public class BaseInquirer {
    //查询条件集合
    private List<BooleanExpression> expressions;
    public BaseInquirer(){
        this.expressions = new ArrayList<>();
    }

    /**
     * @Author: rogue
     * @Description: 添加查询条件到Query集合内
     * @ClassName: BaseInquirer
     * @Date: 2017/12/5
     * @Time: 17:36
     */
    public BaseInquirer putExpression(BooleanExpression expression){
        /**
         * @MethodName: putExpression
         * @Param: [expression]     查询条件继承BooleanExpression抽象对象的具体实体对象，querydsl已经封装
         */
        //添加到条件集合
        expressions.add(expression);
        return this;
    }

    /**
     * @Author: rogue
     * @Description: 构建出查询findAll函数使用的Predicate接口查询对象
     * 调用buildQuery()可直接作为findAll参数查询条件使用
     * @ClassName: BaseInquirer
     * @Date: 2017/12/5
     * @Time: 17:43
     */
    public Predicate buildQuery(){
        //第一个查询条件对象
        BooleanExpression expression = null;
        //遍历所有查询条件，以第一个开始的and
        for (int i=0;i<expressions.size();i++){
            if (i==0){
                expression = expressions.get(i);
            }else {
                expression = expression.and(expressions.get(i));
            }
        }
        return expression;
    }
    
    /**
     * @Author: rogue
     * @Description: 将Iterable集合转换成ArrayList集合
     * @ClassName: BaseInquirer
     * @Date: 2017/12/5
     * @Time: 17:48
     */

    public <T> List<T> iteratorToList(Iterable<T> iterable){
        List<T> returnList = new ArrayList<>();
        Iterator<T> iterator = iterable.iterator();
        while (iterator.hasNext()){
            returnList.add(iterator.next());
        }
        return returnList;
    }
}
