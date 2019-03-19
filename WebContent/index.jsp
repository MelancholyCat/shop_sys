<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>壹特产商城后台首页</title>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/assets/css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/assets/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/assets/js/hardphp.js"></script>
</head>
<body>

    <div class="myheading">
        <nav class="navbar navbar-inner">
            <div class="container-fluid">
                <div class="navbar-header">
                    <!--nav troggle-->
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#hard-navbar">
                        <span class="sr-only">导航切换</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <!--nav troggle-->
                    <!--brand-->
                    <a class="navbar-brand" href="#">壹特产商城后台</a>
                    <!--brand-->
                </div>

                <!--nav links-->
                <div class="collapse navbar-collapse" id="hard-navbar">

                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="aa.html" class="atip" target="appiframe" data-toggle="tooltip" data-placement="bottom" data-title="首页"><span class="glyphicon glyphicon-home" aria-hidden="true"></span></a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                                <span class="glyphicon glyphicon-sunglasses" aria-hidden="true"></span> hardphp
                            </a>
                            <ul class="dropdown-menu dropdown-menu-arrow-right" role="menu">
                                <li><a href="#"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> 修改资料</a></li>
                                <li><a href="#"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> 退出登录</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!--nav links-->
            </div>
        </nav>
    </div>

    <!--main-->
    <div class="container-fluid mybody">
        <div class="main-wapper">
            <!--菜单-->
            <div id="siderbar" class="siderbar-wapper">

                <div class="panel-group menu" id="accordion" role="tablist" aria-multiselectable="true">

                    <div class="panel panel-inner">
                        <div class="panel-heading panel-heading-self" role="tab" id="headingOne">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    <span class="glyphicon glyphicon-list-alt"></span> 用户管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                            <div class="list-group list-group-self">
                                <a href="${pageContext.request.contextPath }/user?method=list" target="appiframe" class="list-group-item"><span class="glyphicon glyphicon-menu-right"></span> 用户列表</a>
                                <a href="add_article.html" target="appiframe" class="list-group-item"><span class="glyphicon glyphicon-menu-right"></span> 权限管理</a>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-inner">
                        <div class="panel-heading panel-heading-self" role="tab" id="headingSeven">
                            <h4 class="panel-title">
                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseSeven" aria-expanded="false" aria-controls="collapseSeven">
                                    <span class="glyphicon glyphicon-th-large"></span> 商品管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseSeven" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSeven">
                            <div class="list-group list-group-self">
                                <a href="${pageContext.request.contextPath }/goodsType?method=list"  target="appiframe" class="list-group-item"><span class="glyphicon glyphicon-menu-right"></span> 商品类别</a>
                                <a href="add_column.html"  target="appiframe" class="list-group-item"><span class="glyphicon glyphicon-menu-right"></span> 商品列表</a>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-inner">
                        <div class="panel-heading panel-heading-self" role="tab" id="headingTwo">
                            <h4 class="panel-title">
                                <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    <span class="glyphicon glyphicon-th"></span> 订单管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                            <div class="list-group list-group-self">
                                <a href="categroy.html"  target="appiframe" class="list-group-item"><span class="glyphicon glyphicon-menu-right"></span> 订单列表</a>
                            </div>
                        </div>
                    </div>

                </div>

            </div>
            <!--菜单-->
            <!--内容-->
            <div id="content" class="main-content">
                <iframe src="${pageContext.request.contextPath }/index?page=welcome" style="width:100%;height: 100%;" id="appiframe" name="appiframe" frameborder="0"></iframe>
            </div>
            <!--内容-->
        </div>

    </div>

    <!--main-->

    <script type="text/javascript">
        $(function(){
            var options={
                animation:true,
                trigger:'hover' //触发tooltip的事件
            }
            $('.atip').tooltip(options);

        });



    </script>
</body>
</html>