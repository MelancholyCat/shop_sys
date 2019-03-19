package org.gecedu.service.impl;

import java.util.List;

import org.gecedu.dao.GoodsTypeDao;
import org.gecedu.dao.impl.GoodsTypeDaoImpl;
import org.gecedu.model.GoodsType;
import org.gecedu.pojo.PageResult;
import org.gecedu.service.GoodsTypeService;

public class GoodsTypeServiceImpl implements GoodsTypeService{

	@Override
	public List<GoodsType> queryAll() {
		GoodsTypeDao GoodsTypeDao = new GoodsTypeDaoImpl();
		return GoodsTypeDao.queryAll();
	}

	@Override
	public void add(GoodsType goodsType) {
		GoodsTypeDao GoodsTypeDao = new GoodsTypeDaoImpl();
		GoodsTypeDao.add(goodsType);
	}

	@Override
	public void delete(Integer id) {
		GoodsTypeDao GoodsTypeDao = new GoodsTypeDaoImpl();
		GoodsTypeDao.delete(id);
	}

	@Override
	public PageResult queryByPages(Integer currentPage, Integer pageSize) {
		GoodsTypeDao GoodsTypeDao = new GoodsTypeDaoImpl();
		return GoodsTypeDao.queryByPages(currentPage, pageSize);
	}

}
