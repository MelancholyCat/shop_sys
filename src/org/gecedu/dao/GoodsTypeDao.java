package org.gecedu.dao;

import java.util.List;

import org.gecedu.model.GoodsType;
import org.gecedu.pojo.PageResult;

public interface GoodsTypeDao {
	
	/**
	 * 添加商品类别
	 * @param GoodsType
	 */
	void add(GoodsType goodsType);
	
	/**
	 * 根据商品类别ID查询
	 * @param id
	 * @return
	 */
	GoodsType queryById(Integer id);
	
	/**
	 * 查询所有
	 * @return
	 */
	List<GoodsType> queryAll();
	
	
	/**
	 * 查询所有
	 * @return
	 */
	PageResult queryByPages(Integer currentPage,Integer pageSize);
	
	/**
	 * 更新商品类别
	 * @param GoodsType
	 */
	void update(GoodsType goodsType);
	
	
	/**
	 * 根据ID删除商品类别
	 * @param id
	 */
	void delete(Integer id);
	
	
	

}
