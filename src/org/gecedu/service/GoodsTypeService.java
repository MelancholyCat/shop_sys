package org.gecedu.service;

import java.util.List;

import org.gecedu.model.GoodsType;
import org.gecedu.pojo.PageResult;

public interface GoodsTypeService {
	
	/**
	 * 查询所有
	 * @return
	 */
	List<GoodsType> queryAll();

	
	/**
	 * 添加商品类别
	 * @param GoodsType
	 */
	void add(GoodsType goodsType);
	
	
	/**
	 * 根据ID删除商品类别
	 * @param id
	 */
	void delete(Integer id);
	
	/**
	 * 分页查询
	 * @return
	 */
	PageResult queryByPages(Integer currentPage,Integer pageSize);
	
}
