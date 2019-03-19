package org.gecedu.service;

import java.util.List;

import org.gecedu.model.User;
import org.gecedu.pojo.PageResult;

public interface UserService {
	
	/**
	 * 查询所有
	 * @return
	 */
	List<User> queryAll();

	
	/**
	 * 添加用户
	 * @param user
	 */
	void add(User user);
	
	
	/**
	 * 根据ID删除用户
	 * @param id
	 */
	void delete(Integer id);
	
	User queryById(Integer id);
	
	
	void update(User user);
	
	/**
	 * 分页查询
	 * @return
	 */
	PageResult queryByPages(Integer currentPage,Integer pageSize,String userName);

	
	/**
	 * 根据用户名和密码查询
	 * @param phone
	 * @param password
	 * @return
	 */
	User queryByAccountAndPassword(String phone, String password);
	
}
