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
    <title>添加用户</title>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/assets/css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/assets/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>
    
</head>
<body>

<!--路径导航-->
<ol class="breadcrumb breadcrumb_nav">
    <li>首页</li>
    <li>用户管理</li>
    <li class="active">添加用户</li>
</ol>
<!--路径导航-->

<div class="page-content">
    <div class="panel panel-default">
        <div class="panel-heading">添加用户</div>
        <div class="panel-body">
            <form action="${pageContext.request.contextPath }/user?method=edit"  method="post">
            	
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group clearfix">
                                <label>昵称：</label>
 								<input type="text" class="form-control" id="name" name="name" value="${user.name }" placeholder="请输入手机号">
                            </div>
                            <div class="form-group clearfix">
                                <label>手机号</label>
 								<input type="text" class="form-control" id="phone" name="phone" value="${user.phone }" placeholder="请输入手机号">
                            </div>
                            <div class="form-group">
                                <label for="email">性别:</label>
                                	男<input type="radio" name="gender" value="1"  <c:if test="${user.gender ==1}">checked="checked"</c:if>  >
                             		 女<input type="radio" name="gender" value="2" <c:if test="${user.gender ==2}">checked="checked"</c:if>  >
                             		 保密<input type="radio" name="gender" value="3" <c:if test="${user.gender ==3}">checked="checked"</c:if> >
                             		
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label > 省份：</label>
                                   		 <select name="province"></select>
                                    <label>城市： </label>
                                        <select name="city"></select>
                                        
                            </div>
                        </div>
						<input type="hidden" id="provinceId" value="${user.province }"/>
						<input type="hidden" id="cityId" value="${user.city }"/>
						<input type="hidden" name="id" value="${user.id }"/>
                    </div>
                    <button type="submit" class="btn btn-primary">保存</button>
                </div>
            </form>
        </div>

    </div>

</div>
<script src="${pageContext.request.contextPath }/assets/js/pcasunzip.js"></script>
<script type="text/javascript">
	new PCAS("province","city",$("#provinceId").val(),$("#cityId").val());
</script> 

</body>
</html>