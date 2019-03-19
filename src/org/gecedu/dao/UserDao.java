package org.gecedu.dao;

import java.util.List;

import org.gecedu.model.User;
import org.gecedu.pojo.PageResult;

public interface UserDao {
	
	/**
	 * 添加用户
	 * @param user
	 */
	void add(User user);
	
	/**
	 * 根据用户ID查询
	 * @param id
	 * @return
	 */
	User queryById(Integer id);
	
	/**
	 * 查询所有
	 * @return
	 */
	List<User> queryAll();
	
	
	/**
	 * 查询所有
	 * @return
	 */
	PageResult queryByPages(Integer currentPage,Integer pageSize,String userName);
	
	/**
	 * 更新用户
	 * @param user
	 */
	void update(User user);
	
	
	/**
	 * 根据ID删除用户
	 * @param id
	 */
	void delete(Integer id);

	User queryByAccountAndPassword(String phone, String password);
	
	
	
	

}
