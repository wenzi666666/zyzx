package net.tfedu.zhl.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

/**
 * 基础服务类
 * 
 * @author Bruce
 * @Autowired按byType自动注入，而@Resource默认按 byName自动注入罢了
 * @param <T>
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    CoreMapper<T> mapper;

    protected ResultJSON result;
    protected CustomException exception;

    @Override
    public ResultJSON get(int id) {
        try {
            T data = mapper.selectByPrimaryKey(id);
            exception = CustomException.SUCCESS;
            result = new ResultJSON(exception.getCode(), exception.getMessage(), data, "");
        } catch (Exception e) {
            exception = CustomException.UNCUSTOM;
            result = new ResultJSON(exception.getCode(), e.getMessage(), "", "");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ResultJSON insert(T c) {
        try {
            int data = mapper.insert(c);
            exception = CustomException.SUCCESS;
            result = new ResultJSON(exception.getCode(), exception.getMessage(), data, "");
        } catch (Exception e) {
            exception = CustomException.UNCUSTOM;
            result = new ResultJSON(exception.getCode(), e.getMessage(), "", "");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ResultJSON delete(int id) {
        try {
            int data = mapper.deleteByPrimaryKey(id);
            exception = CustomException.SUCCESS;
            result = new ResultJSON(exception.getCode(), exception.getMessage(), data, "");
        } catch (Exception e) {
            exception = CustomException.UNCUSTOM;
            result = new ResultJSON(exception.getCode(), e.getMessage(), "", "");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新部分属性
     */
    @Override
    public ResultJSON update(T c) {
        try {
            int data = mapper.updateByPrimaryKeySelective(c);
            exception = CustomException.SUCCESS;
            result = new ResultJSON(exception.getCode(), exception.getMessage(), data, "");
        } catch (Exception e) {
            exception = CustomException.UNCUSTOM;
            result = new ResultJSON(exception.getCode(), e.getMessage(), "", "");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量插入
     */
    @Override
    public ResultJSON insert(List<T> datas) {
        try {
            int data = mapper.insertList(datas);
            exception = CustomException.SUCCESS;
            result = new ResultJSON(exception.getCode(), exception.getMessage(), data, "");
        } catch (Exception e) {
            exception = CustomException.UNCUSTOM;
            result = new ResultJSON(exception.getCode(), e.getMessage(), "", "");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 分页查询
     */
    @Override
    public ResultJSON getPage(int pageNum, int pageSize) {
        // 用PageInfo对结果进行包装
        try {
            // Page插件必须放在查询语句之前紧挨的第一个位置
            PageHelper.startPage(pageNum, pageSize);
            PageHelper.orderBy("id desc");
            // 这里不能放其它语句
            List<T> list = mapper.selectAll();
            PageInfo<T> page = new PageInfo<T>(list);
            exception = CustomException.SUCCESS;
            result = new ResultJSON(exception.getCode(), exception.getMessage(), page, "");
        } catch (Exception e) {
            exception = CustomException.UNCUSTOM;
            result = new ResultJSON(exception.getCode(), e.getMessage(), "", "");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 按属性值查询
     */
    @Override
    public ResultJSON select(T c) {
        try {
            List<T> data = mapper.select(c);
            exception = CustomException.SUCCESS;
            result = new ResultJSON(exception.getCode(), exception.getMessage(), data, "");
        } catch (Exception e) {
            exception = CustomException.UNCUSTOM;
            result = new ResultJSON(exception.getCode(), e.getMessage(), "", "");
            e.printStackTrace();
        }
        return result;
    }

}
