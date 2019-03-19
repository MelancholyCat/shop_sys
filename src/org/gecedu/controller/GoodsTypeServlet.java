package org.gecedu.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.gecedu.model.GoodsType;
import org.gecedu.pojo.PageResult;
import org.gecedu.service.GoodsTypeService;
import org.gecedu.service.impl.GoodsTypeServiceImpl;
import org.gecedu.utils.FileUploadUtil;

@WebServlet("/goodsType")
public class GoodsTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 适用于post请求里面参数中文乱码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String method = request.getParameter("method");
		switch (method) {
		case "list":
			list(request, response);
			break;
		case "editUI":
			editUI(request, response);
			break;
		case "edit":
			try {
				edit(request, response);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			break;
		case "queryById":
			queryById(request, response);
			break;
		case "delete":
			delete(request, response);
			break;
		default:
			request.getRequestDispatcher("/WEB-INF/pages/welcome.jsp").forward(request, response);
			break;
		}
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		GoodsTypeService GoodsTypeService = new GoodsTypeServiceImpl();
		GoodsTypeService.delete(Integer.parseInt(id));
		// 回到列表页面 因为我们使用iframe框架，所以可以直接调用方法。
		list(request, response);
		// response.sendRedirect(request.getContextPath() + "/user?method=list");
	}

	/**
	 * 根据商品类别Id查询
	 * 
	 * @param request
	 * @param response
	 */
	private void queryById(HttpServletRequest request, HttpServletResponse response) {
			
	}

	/**
	 * 编辑
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * @throws FileUploadException 
	 */
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileUploadException {
		String name = request.getParameter("name");
		String level = request.getParameter("level");
		String upperId = request.getParameter("upperId");
		String imageUrl = upload(request);
		System.out.println(imageUrl);
		GoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();
		list(request, response);
	}

	/**
	 * 上传图片
	 * 
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws FileUploadException 
	 */
	private String upload(HttpServletRequest request) throws IOException, ServletException, FileUploadException {
		String savePath = request.getRealPath("/upload/img/goodsType");
		String resultUrl = "";
		File file = new File(savePath);
		// 判断上传文件的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
			// 创建目录
			file.mkdir();
		}
		//设置系统环境  
        DiskFileItemFactory factory = new DiskFileItemFactory();  
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(4 * 1024 * 1024);// 设置单个文件大小不能超过4M
		upload.setSizeMax(4 * 1024 * 1024);// 设置总文件上传大小不能超过6M
		List<FileItem> items = upload.parseRequest(request);
		for (FileItem item : items) {
				InputStream in = item.getInputStream();
				//上传之后文件设置
				String fileName = item.getName();
				if (fileName == null || "".equals(fileName.trim())) {
					continue;
				}
				fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
				String fileExt = FileUploadUtil.getFileExt(fileName);
				fileName = System.currentTimeMillis() +"."+ fileExt;
				// 按日期来建文件夹
				String newStorePath = FileUploadUtil.getTimeDesPath();
				newStorePath = savePath +"\\"+ newStorePath + "\\";
				String storeFile = makeStorePath(newStorePath) + fileName;
				
				resultUrl = "/upload/img/goodsType"+newStorePath+fileName;
				OutputStream out = new FileOutputStream(storeFile);
				
				
				byte[] b = new byte[1024];
				int len = -1;
				while ((len = in.read(b)) != -1) {
					out.write(b, 0, len);
				}
				in.close();
				out.close();
				item.delete();// 删除临时文件
		}
		return resultUrl;
	}
	
	 private String makeStorePath(String storePath) {  
	        File file = new File(storePath);  
	        if(!file.exists())  
	        {  
	            file.mkdirs();//创建多级目录，mkdir只创建一级目录  
	        }  
	        return storePath;  
	           
	    } 

	/**
	 * 跳转编辑页面
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void editUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();
		List<GoodsType> goodsTypeList = goodsTypeService.queryAll();
		request.setAttribute("goodsTypeList", goodsTypeList);
		request.getRequestDispatcher("/WEB-INF/pages/goodsType/edit.jsp").forward(request, response);
	}

	/**
	 * 商品类别列表
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");
		String pageSize = request.getParameter("pageSize");
		Integer pageSizeInt = 5;
		Integer currentPageInt = 1;
		if (pageSize != null && pageSize.length() > 0) {
			pageSizeInt = Integer.parseInt(pageSize);
		}
		if (currentPage != null && currentPage.length() > 0) {
			currentPageInt = Integer.parseInt(currentPage);
		}

		GoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();
		PageResult pageResult = goodsTypeService.queryByPages(currentPageInt, pageSizeInt);
		request.setAttribute("pageResult", pageResult);
		request.getRequestDispatcher("/WEB-INF/pages/goodsType/list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
