
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


    <title><decorator:title default=""/> - 知识分子足球队|业余足球|知识分子|成都业余足球队</title>

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
            <nav class="navbar navbar-inverse" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#navbar-collapse-01">
                        <span class="sr-only">Toggle navigation</span>
                    </button>
                    <a class="navbar-brand" href="${contextPath}/index.zsfz">知识分子</a>
                </div>
                <div class="collapse navbar-collapse" id="navbar-collapse-01">
                    <ul class="nav navbar-nav" id="mainMenu">
                        <li><a href="${contextPath}/index.zsfz">首页</a></li>
                        <li class="matchLi"><a href="${contextPath}/match.zsfz">比赛</a></li>
                        <li class="dataLi"><a href="${contextPath}/data.zsfz">数据</a></li>
                        <li class="albumLi"><a href="${contextPath}/album.zsfz">相册</a></li>
                        <li class="recordLi"><a href="${contextPath}/record.zsfz">纪录</a></li>
                        <li class="aboutLi"><a href="${contextPath}/about.zsfz">关于</a></li>
                    </ul>
                    <!--right navbar-->
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="${contextPath}/go_login.zsfz"><i
                                class="glyphicon glyphicon-user"></i> 登录</a></li>
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
    <script src="${contextPath}/resources/js/zhishifenzi.js"></script>

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