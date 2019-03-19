package org.gecedu.service.impl;

import java.util.List;

import org.gecedu.dao.UserDao;
import org.gecedu.dao.impl.UserDaoImpl;
import org.gecedu.model.User;
import org.gecedu.pojo.PageResult;
import org.gecedu.service.UserService;

public class UserServiceImpl implements UserService{

	@Override
	public List<User> queryAll() {
		UserDao userDao = new UserDaoImpl();
		//以后企业项目在service里面可能会做很多事情
		return userDao.queryAll();
	}

	@Override
	public void add(User user) {
		UserDao userDao = new UserDaoImpl();
		userDao.add(user);
	}

	@Override
	public void delete(Integer id) {
		UserDao userDao = new UserDaoImpl();
		userDao.delete(id);
	}

	@Override
	public PageResult queryByPages(Integer currentPage, Integer pageSize, String userName) {
		UserDao userDao = new UserDaoImpl();
		return userDao.queryByPages(currentPage, pageSize, userName);
	}

	@Override
	public User queryById(Integer id) {
		UserDao userDao = new UserDaoImpl();
		return userDao.queryById(id);
	}

	@Override
	public void update(User user) {
		UserDao userDao = new UserDaoImpl();
		userDao.update(user);
	}

	@Override
	public User queryByAccountAndPassword(String phone, String password) {
		UserDao userDao = new UserDaoImpl();
		return userDao.queryByAccountAndPassword(phone,password);
	}

}
