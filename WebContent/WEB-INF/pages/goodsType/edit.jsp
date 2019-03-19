<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="content-type" content = "text/html;charset=utf-8">
    <title>添加商品类别</title>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/assets/css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/assets/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>
    <style type="text/css">
    	.clearfix{
    		float: none;
    		clear: both;
    	}
    </style>
</head>
<body>

<!--路径导航-->
<ol class="breadcrumb breadcrumb_nav">
    <li>首页</li>
    <li>商品类别管理</li>
    <li class="active">添加商品类别</li>
</ol>
<!--路径导航-->

<div class="page-content">
    <div class="panel panel-default">
        <div class="panel-heading">添加商品类别</div>
        <div class="panel-body">
            <form id="goodsTypeForm" method="post" enctype="multipart/form-data" >
            
                <div class="container-fluid">
                            <div class="form-group clearfix col-sm-4" >
                                <label>类别名称：</label>
 								<input type="text" class="form-control" id="name" name="name" placeholder="请输入类别名称">
                            </div>
                            <div class="form-group clearfix col-sm-4">
                                <label>级别</label>
 								<input type="text" class="form-control" id="level" name="level" placeholder="请输入级别">
                            </div>
                            <div class="form-group clearfix col-sm-4">
                                <label for="name">父级类别</label>
                                <select id="upperId" name="upperId">
                                	<c:forEach items="${goodsTypeList}" var="item">
                                		<option value="${item.gtId}">${item.name }</option>
                                	</c:forEach>
                                </select>
                            </div>
                            <div class="form-group clearfix col-sm-4">
                                <label for="email">排序号</label>
                                <input type="text" class="form-control" id="orderNum" name="orderNum" placeholder="">
                            </div>
                            <div class="form-group clearfix col-sm-4" id="updatebox">
                            	<input type="file" id="file"  name="imageName" />
                              <!-- <label for="file">点击此处触发上传类别图片
										<div class="panel updatepanel">
											<div class="addbox"><span class="icon-add-50"></span></div>
											<input type="file" id="file" style="display: none" name="imageName" />
										</div>
							 </label> -->
                            </div>
                            
                            <div class="form-group clearfix col-sm-4" id="updatebox">
                              <a href="#"  class="btn btn-primary" onclick="submit()">保存</a>
                            </div>
                    </div>
            </form>
        </div>
    </div>

</div>
<script type="text/javascript">
	function submit(){
		
		var goodsTypeForm = document.getElementById("goodsTypeForm");
		
		
		var name = document.getElementById("name").value;
		var level = document.getElementById("level").value;
		var upperId = document.getElementById("upperId").value;
		var orderNum = document.getElementById("orderNum").value;
		goodsTypeForm.action="${pageContext.request.contextPath }/goodsType?method=edit&name="+name+"&level="+level+"&upperId="+upperId+"&orderNum="+orderNum;
		goodsTypeForm.submit();
	}

</script>
</body>
</html>