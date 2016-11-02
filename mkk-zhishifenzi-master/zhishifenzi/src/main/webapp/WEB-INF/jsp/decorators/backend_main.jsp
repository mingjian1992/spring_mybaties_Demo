
<%--
 *
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>
    <link rel="shortcut icon" type="image/x-icon" href="${contextPath}/resources/images/favicon.ico"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <meta name="keywords" content="足球,踢足球,足球队,知识分子足球队,我们的足球,业余足球,成都业余足球队"/>
    <meta name="description" content="我们是知识分子,争先恐后接球,知识分子足球队,我们在一起欢乐的足球时光"/>
    <meta name="author" content="李胜钊,monkeyk"/>

    <title><decorator:title default=""/> - 知识分子足球队</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${contextPath}/resources/css/custom.css"/>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->


    <script>
        <%--JS gloable varilible--%>
        var contextPath = "${contextPath}";
    </script>
    <decorator:head/>

</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <nav class="navbar navbar-default" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#navbar-collapse-01">
                        <span class="sr-only">Toggle navigation</span>
                    </button>
                    <a class="navbar-brand" href="${contextPath}/b/index.zsfz">知识分子</a>
                </div>
                <div class="collapse navbar-collapse" id="navbar-collapse-01">
                    <ul class="nav navbar-nav" id="mainMenu">
                        <li><a href="${contextPath}/b/index.zsfz">首页</a></li>
                        <li class="dropdown matchLi">
                            <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">比赛 <b
                                    class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="${contextPath}/b/match.zsfz"><i class="glyphicon glyphicon-tasks"></i> 比赛信息</a>
                                </li>
                                <li><a href="${contextPath}/b/match_notice.zsfz"><i
                                        class="glyphicon glyphicon-map-marker"></i> 比赛预告</a></li>
                                <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_CAPTAIN,ROLE_COACH">
                                    <li class="divider"></li>
                                    <li><a href="${contextPath}/b/season.zsfz"><i class="glyphicon glyphicon-eject"></i>
                                        赛季</a></li>
                                </sec:authorize>
                            </ul>
                        </li>
                        <li class="clubLi"><a href="${contextPath}/b/club.zsfz">对手</a></li>
                        <li class="albumLi"><a href="${contextPath}/b/album.zsfz">相册</a></li>
                        <li class="playerLi"><a href="${contextPath}/b/player.zsfz">球员</a></li>
                        <li class="stadiumLi"><a href="${contextPath}/b/stadium.zsfz">球场</a></li>
                        <sec:authorize ifAnyGranted="ROLE_ADMIN">
                            <li class="dropdown systemLi">
                                <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">系统 <b
                                        class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="${contextPath}/b/system/global_setting.zsfz"><i
                                            class="glyphicon glyphicon-wrench"></i> 全局设置</a></li>
                                    <li><a href="${contextPath}/b/system/sync_photos.zsfz"><i
                                            class="glyphicon glyphicon-transfer"></i> 照片同步</a></li>
                                    <li class="divider"></li>
                                    <li><a href="${contextPath}/b/system/logs.zsfz"><i
                                            class="glyphicon glyphicon-list-alt"></i> 日志管理</a></li>
                                </ul>
                            </li>
                        </sec:authorize>
                    </ul>
                    <!--right navbar-->
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <div class="navbar-text">
                                ${SPRING_SECURITY_CONTEXT.authentication.principal.displayName}&nbsp;
                                <a href="${contextPath}/signout" title="退出" class="navbar-link"><i
                                        class="glyphicon glyphicon-log-out"></i></a>
                            </div>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </nav>
            <!-- /navbar -->
        </div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${contextPath}/resources/js/jquery-1.9.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/zhishifenzi_backend.js"></script>

    <decorator:body/>

    <div class="row">
        <div class="col-md-12">
            <hr/>
            <p class="text-center text-muted">
                &copy; 2008-2015 知识分子足球队 All rights reserved.
            </p>
        </div>
    </div>
</div>
<!-- /.container -->

<jsp:include page="analytics.jsp"/>

</body>
</html>