<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
    
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户列表</title>

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
    <li class="active">用户列表</li>
</ol>
<!--路径导航-->

<div class="page-content">
    <div class="panel panel-default">
        <div class="panel-heading">用户列表</div>
        <div class="panel-body">
            <div class="pull-right">
                <div class="form-group">
                    <label class="sr-only" for="exampleInputEmail3">用户名</label>
                    <input type="email" class="form-control" id="userName" placeholder="请输入用户名">
                </div>
                <button type="button" class="btn btn-default" onclick="queryByPages()" >查询</button>
                <a href="${pageContext.request.contextPath }/user?method=editUI" class="btn btn-default" >添加</a>
            </div>

        </div>
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th width="3%">
                        <input class="check_all" type="checkbox">
                    </th>
                    <th width="5%">ID</th>
                    <th width="5%">姓名</th>
                    <th width="5%">手机号</th>
                    <th width="5%">性别</th>
                    <th width="5%">省份</th>
                    <th width="5%">城市</th>
                    <th width="5%">注册时间</th>
                    <th width="5%">更新时间</th>
                    <th width="5%">操作</th>
                </tr>
                </thead>
                <tbody>
					<c:forEach items="${pageResult.list}" var="item">
						<tr>
	                        <td>
	                           <input class="check_all" type="checkbox" name="ids[]" value="8">
	                        </td>
	                        <td>${item.id }</td>
	                        <td>${item.name}</td>
	                        <td>${item.phone}</td>
	                        <td>
	                        	<c:if test="${item.gender==1}">男</c:if>
	                        	<c:if test="${item.gender==2}">女</c:if>
	                        	<c:if test="${item.gender==3}">保密</c:if>
	                        </td>
	                        <td>${item.province}</td>
	                        <td>${item.city}</td>
	                        <td>${item.createTime}</td>
	                        <td>${item.updateTime}</td>
	                        <td>
	                            <a href="${pageContext.request.contextPath }/user?method=editUI&id=${item.id}" class="btn btn-info btn-xs"><span class="glyphicon glyphicon-edit"></span>编辑</a>
	                            <a href="${pageContext.request.contextPath }/user?method=delete&id=${item.id}" class="btn btn-warning btn-xs"><span class="glyphicon glyphicon-eye-open"></span>删除</a>
	                        </td>
	                    </tr>
					</c:forEach>
                </tbody>
            </table>

            <div class="panel-footer clearfix">
                <div class="pull-left">
                    <div class="form-group">
                        <label>总记录数：${pageResult.totalRecord}</label>
                        <label>每页显示：</label>
                        <select class="form-control" id="pageSize" onchange="queryByPages()">
                            <option value="5"  <c:if test="${pageResult.pageSize eq 5}">selected="selected"</c:if> >5</option>
                            <option value="10" <c:if test="${pageResult.pageSize eq 10}">selected="selected"</c:if> >10</option>
                            <option value="15" <c:if test="${pageResult.pageSize eq 15}">selected="selected"</c:if> >15</option>
                        </select>
                    </div>
                </div>
                <nav class="pull-right">
                    <ul class="pagination pagination-self">
                    	<!--  上一页  -->
                    	<c:if test="${pageResult.currentPage == 1}">
                    		 <li>
	                             <span aria-hidden="true">&laquo;</span>
	                        </li>
                    	</c:if>
                    	<c:if test="${pageResult.currentPage >1}">
                    		 <li>
	                            <a href="${pageContext.request.contextPath }/user?method=list&currentPage=${pageResult.currentPage-1}" aria-label="Previous">
	                                <span aria-hidden="true">&laquo;</span>
	                            </a>
	                        </li>
                    	</c:if>
                       
                        <c:forEach begin="${pageResult.beginIndex }" end="${pageResult.endIndex }" varStatus="status">
                        	<c:if test="${status.index eq pageResult.currentPage}">
                        		<li><span style="color: black;">${status.index }</span></li>
                        	</c:if>
                        	<c:if test="${status.index ne pageResult.currentPage}">
                        		<li><a href="${pageContext.request.contextPath }/user?method=list&currentPage=${status.index}">${status.index }</a></li>
                        	</c:if>
                        </c:forEach>
                        
                        <!-- 下一页 -->
                        <c:if test="${pageResult.currentPage == pageResult.totalPages}">
	                        <li>
	                               <span aria-hidden="true">&raquo;</span>
	                        </li>
                        </c:if>
                        <c:if test="${pageResult.currentPage < pageResult.totalPages}">
	                        <li>
	                            <a href="${pageContext.request.contextPath }/user?method=list&currentPage=${pageResult.currentPage+1}" aria-label="Next">
	                                <span aria-hidden="true">&raquo;</span>
	                            </a>
	                        </li>
                        </c:if>
                    </ul>
                </nav>
            </div>

        </div>
    </div>

</div>

<script type="text/javascript">
	//定义js方法格式： function 方法名(){}
	// js 只有一种数据类型 var
	function queryByPages(){
		//设置当前浏览器的地址
		var pageSize = document.getElementById("pageSize").value;
		var userName = document.getElementById("userName").value;
		document.location.href="${pageContext.request.contextPath }/user?method=list&currentPage=1&pageSize="+pageSize+"&userName="+userName;
	}
	

</script>
</body>
</html>