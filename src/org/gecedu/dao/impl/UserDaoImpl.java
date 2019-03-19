package org.gecedu.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.gecedu.dao.UserDao;
import org.gecedu.model.User;
import org.gecedu.pojo.PageResult;
import org.gecedu.utils.JdbcUtils;

public class UserDaoImpl implements UserDao{

	/**
	 * 添加用户
	 */
	@Override
	public void add(User user) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = JdbcUtils.getConnection();
			//3.获取statement
			String sql ="insert into user(name,phone,password,gender,province,city,create_time,update_time) value(?,?,?,?,?,?,?,?) ";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getPhone());
			prepareStatement.setString(3, user.getPassword());
			prepareStatement.setInt(4, user.getGender());
			prepareStatement.setString(5, user.getProvince());
			prepareStatement.setString(6, user.getCity());
			prepareStatement.setDate(7, new Date(System.currentTimeMillis()));
			prepareStatement.setDate(8, new Date(System.currentTimeMillis()));
			//4.执行sql
			prepareStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//5.释放资源 connection prepareStatement
			JdbcUtils.close(connection, prepareStatement, null);
		}
	}

	
	
	@Override
	public User queryById(Integer id) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			connection = JdbcUtils.getConnection();
			//3.获取statement
			String sql ="select * from user where id=? ";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			//4.执行sql
			resultSet = prepareStatement.executeQuery();
			//5.处理查询出来的数据
			while(resultSet.next()) {
				String name = resultSet.getString("name");
				String phone = resultSet.getString("phone");
				Integer gender = resultSet.getInt("gender");
				String province = resultSet.getString("province");
				String city = resultSet.getString("city");
				Date createTime = resultSet.getDate("create_time");
				Date updateTime = resultSet.getDate("update_time");
				user = new User(id, name, phone, gender, province, city, createTime, updateTime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.close(connection, prepareStatement, resultSet);
		}
		return user;
	}

	@Override
	public List<User> queryAll() {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		List<User> userList = new ArrayList<>();
		try {
			connection = JdbcUtils.getConnection();
			//3.获取statement
			String sql ="select * from user";
			prepareStatement = connection.prepareStatement(sql);
			//4.执行sql
			resultSet = prepareStatement.executeQuery();
			//5.处理查询出来的数据
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				User user = new User(id,name);
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//5.释放资源 connection prepareStatement resultSet
			JdbcUtils.close(connection, prepareStatement, resultSet);
		}
		return userList;
	}

	@Override
	public void update(User user) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = JdbcUtils.getConnection();
			//3.获取statement
			String sql ="update user set name=?,phone=?,gender=?,province=?,city=?,update_time=? where id=?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getPhone());
			prepareStatement.setInt(3, user.getGender());
			prepareStatement.setString(4, user.getProvince());
			prepareStatement.setString(5, user.getCity());
			prepareStatement.setDate(6, new Date(System.currentTimeMillis()));
			prepareStatement.setInt(7, user.getId());
			//4.执行sql
			prepareStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//5.释放资源 connection prepareStatement
			JdbcUtils.close(connection, prepareStatement, null);
			
		}
		
	}

	@Override
	public void delete(Integer id) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = JdbcUtils.getConnection();
			//3.获取statement
			String sql ="delete from user where id=?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			//4.执行sql
			prepareStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//5.释放资源 connection prepareStatement
			JdbcUtils.close(connection, prepareStatement, null);
		}
	}



	/**
	 * 分页查询
	 */
	@Override
	public PageResult queryByPages(Integer currentPage, Integer pageSize, String userName) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		PageResult pageResult = null;
		try {
			connection = JdbcUtils.getConnection();
			String whereSql = "";
			if(userName!=null && userName.trim().length()>0) {
				whereSql = "where name like '%"+userName+"%' ";
			}
			//3.获取statement
			String sql ="select * from user "+whereSql+" limit ?,?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, (currentPage-1)*pageSize);
			prepareStatement.setInt(2, pageSize);
			//4.执行sql
			resultSet = prepareStatement.executeQuery();
			//5.处理查询出来的数据
			List<User> userList = new ArrayList<>();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String phone = resultSet.getString("phone");
				Integer gender = resultSet.getInt("gender");
				String province = resultSet.getString("province");
				String city = resultSet.getString("city");
				Date createTime = resultSet.getDate("create_time");
				Date updateTime = resultSet.getDate("update_time");
				User user = new User(id, name, phone, gender, province, city, createTime, updateTime);
				userList.add(user);
			}
			String sql2 ="select count(id) from user "+whereSql;
			prepareStatement = connection.prepareStatement(sql2);
			resultSet = prepareStatement.executeQuery();
			Long totalRecord = 0L;
			if(resultSet.next()) {
				totalRecord = resultSet.getLong(1);
			}
			pageResult = new PageResult(userList, totalRecord, pageSize, currentPage);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//5.释放资源 connection prepareStatement
			JdbcUtils.close(connection, prepareStatement, resultSet);
		}
		return pageResult;
	}



	@Override
	public User queryByAccountAndPassword(String phone, String password) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			connection = JdbcUtils.getConnection();
			//3.获取statement
			String sql ="select * from user where phone=? and password=?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, phone.trim());
			prepareStatement.setString(2, password.trim());
			//4.执行sql
			resultSet = prepareStatement.executeQuery();
			//5.处理查询出来的数据
			while(resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Integer gender = resultSet.getInt("gender");
				String province = resultSet.getString("province");
				String city = resultSet.getString("city");
				Date createTime = resultSet.getDate("create_time");
				Date updateTime = resultSet.getDate("update_time");
				user = new User(id, name, phone, gender, province, city, createTime, updateTime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.close(connection, prepareStatement, resultSet);
		}
		return user;
	}
	
	
	
	
	

}
