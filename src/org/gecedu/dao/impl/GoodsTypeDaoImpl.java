package org.gecedu.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.gecedu.dao.GoodsTypeDao;
import org.gecedu.model.GoodsType;
import org.gecedu.pojo.PageResult;
import org.gecedu.utils.JdbcUtils;

public class GoodsTypeDaoImpl implements GoodsTypeDao{

	/**
	 * 添加商品类别
	 */
	@Override
	public void add(GoodsType goodsType) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = JdbcUtils.getConnection();
			//3.获取statement
			String sql ="insert into goods_type(name,level,upper_id,create_time,image_name,order_num,image_url)"
					+ " value(?,?,?,?,?,?,?) ";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, goodsType.getName());
			prepareStatement.setInt(2, goodsType.getLevel());
			prepareStatement.setInt(3, goodsType.getUpperId());
			prepareStatement.setDate(4, new Date(System.currentTimeMillis()));
			prepareStatement.setString(5, goodsType.getImageName());
			prepareStatement.setInt(6, goodsType.getOrderNum());
			prepareStatement.setString(7, goodsType.getImageUrl());
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
	public GoodsType queryById(Integer id) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		GoodsType goodsType = null;
		try {
			connection = JdbcUtils.getConnection();
			//3.获取statement
			String sql ="select * from goods_type where id=? ";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			//4.执行sql
			resultSet = prepareStatement.executeQuery();
			//5.处理查询出来的数据
			while(resultSet.next()) {
				String name = resultSet.getString("name");
				Integer level = resultSet.getInt("level");
				Integer upperId = resultSet.getInt("upper_id");
				Date createTime = resultSet.getDate("create_time");
				String imageName = resultSet.getString("image_name");
				Integer orderNum = resultSet.getInt("order_num");
				String imageUrl = resultSet.getString("image_url");
				goodsType = new GoodsType(id, name, level, upperId, createTime, imageName, orderNum, imageUrl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.close(connection, prepareStatement, resultSet);
		}
		return goodsType;
	}

	@Override
	public List<GoodsType> queryAll() {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		List<GoodsType> goodsTypeList = new ArrayList<>();
		try {
			connection = JdbcUtils.getConnection();
			//3.获取statement
			String sql ="select * from goods_type";
			prepareStatement = connection.prepareStatement(sql);
			//4.执行sql
			resultSet = prepareStatement.executeQuery();
			//5.处理查询出来的数据
			while(resultSet.next()) {
				Integer id = resultSet.getInt("gt_id");
				String name = resultSet.getString("name");
				Integer level = resultSet.getInt("level");
				Integer upperId = resultSet.getInt("upper_id");
				Date createTime = resultSet.getDate("create_time");
				String imageName = resultSet.getString("image_name");
				Integer orderNum = resultSet.getInt("order_num");
				String imageUrl = resultSet.getString("image_url");
				GoodsType goodsType = new GoodsType(id, name, level, upperId, createTime, imageName, orderNum, imageUrl);
				goodsTypeList.add(goodsType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//5.释放资源 connection prepareStatement resultSet
			JdbcUtils.close(connection, prepareStatement, resultSet);
		}
		return goodsTypeList;
	}

	@Override
	public void update(GoodsType goodsType) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = JdbcUtils.getConnection();
			//3.获取statement
			String sql ="update goods_type set name=?,levle=?,upper_id=?,image_name=?,order_num=?, "
					+ " image_url=? where gt_id=?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, goodsType.getName());
			prepareStatement.setInt(2, goodsType.getLevel());
			prepareStatement.setInt(3, goodsType.getUpperId());
			prepareStatement.setString(4, goodsType.getImageName());
			prepareStatement.setInt(5, goodsType.getOrderNum());
			prepareStatement.setString(6, goodsType.getImageUrl());
			prepareStatement.setInt(7, goodsType.getGtId());
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
			// 商品类别是树状结构，删除时需要同时删除子类别，或者提示用户先删除子类别
			String sql ="delete from goods_type where id=?";
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
	public PageResult queryByPages(Integer currentPage, Integer pageSize) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		PageResult pageResult = null;
		try {
			connection = JdbcUtils.getConnection();
			//3.获取statement
			String sql ="select  gt1.*,gt2.`name` as upperName from goods_type gt1 LEFT JOIN goods_type gt2 on gt1.upper_id = gt2.gt_id  limit ?,?";
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, (currentPage-1)*pageSize);
			prepareStatement.setInt(2, pageSize);
			//4.执行sql
			resultSet = prepareStatement.executeQuery();
			//5.处理查询出来的数据
			List<GoodsType> goodsTypeList = new ArrayList<>();
			while(resultSet.next()) {
				Integer id = resultSet.getInt("gt_id");
				String name = resultSet.getString("name");
				Integer level = resultSet.getInt("level");
				Integer upperId = resultSet.getInt("upper_id");
				Date createTime = resultSet.getDate("create_time");
				String imageName = resultSet.getString("image_name");
				Integer orderNum = resultSet.getInt("order_num");
				String imageUrl = resultSet.getString("image_url");
				String upperName = resultSet.getString("upperName");
				GoodsType goodsType = new GoodsType(id, name, level, upperId, createTime, imageName, orderNum, imageUrl);
				goodsType.setUpperName(upperName);
				goodsTypeList.add(goodsType);
			}
			String sql2 ="select count(gt_id) from goods_type ";
			prepareStatement = connection.prepareStatement(sql2);
			resultSet = prepareStatement.executeQuery();
			Long totalRecord = 0L;
			if(resultSet.next()) {
				totalRecord = resultSet.getLong(1);
			}
			pageResult = new PageResult(goodsTypeList, totalRecord, pageSize, currentPage);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//5.释放资源 connection prepareStatement
			JdbcUtils.close(connection, prepareStatement, resultSet);
		}
		return pageResult;
	}


}
