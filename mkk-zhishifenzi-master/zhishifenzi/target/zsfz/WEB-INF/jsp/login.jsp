
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>
    <link rel="shortcut icon" type="image/x-icon" href="${contextPath}/resources/images/favicon.ico"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <meta name="keywords" content="足球,踢足球,足球队,知识分子足球队,我们的足球,业余足球,成都业余足球队"/>
    <meta name="description" content="我们是知识分子,争先恐后接球,知识分子足球队,我们在一起欢乐的足球时光"/>
    <meta name="author" content="李胜钊,monkeyk"/>

    <title>登录 - 知识分子足球队</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.min.css">
    <style>
        body {
            font-family: "微软雅黑", Helvetica, Verdana, Arial, sans-serif;
        }

        .login-row {
            padding-top: 12%;
        }

        .login-row .panel {
            background-color: #eeeeee;
        }

        .login-form {
            margin: 15px;
        }

        .logo-area img {
            margin-top: 25px;
            max-width: 220px;
            opacity: 0.8;
        }

    </style>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<c:set var="loginError" value="${param.error == '1'}"/>
<c:set var="accessError" value="${param.error == '2'}"/>


<div class="container">
    <div class="row login-row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="row">
                    <div class="col-md-6">
                        <div class="logo-area text-center">
                            <a href="${contextPath}">
                                <img src="${contextPath}/resources/images/3.jpg"
                                     title="知识分子足球队" class="img-circle" alt="zsfz"/>
                            </a>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <form role="form" action="${contextPath}/signin" method="post" class="login-form">
                            <div class="form-group ${loginError?'has-error':''}">
                                <label for="username" class="control-label">用户</label>
                                <input type="text" class="form-control" id="username" value="${username}"
                                       placeholder="请输入用户" name="j_username" required="required"/>
                            </div>
                            <div class="form-group ${loginError?'has-error':''}">
                                <label for="password" class="control-label">密码</label>
                                <input type="password" class="form-control" id="password" name="j_password"
                                       placeholder="请输入密码" required="required"/>
                            </div>
                            <div class="checkbox">
                                <label class="text-warning">
                                    <input type="checkbox" name="_spring_security_remember_me"/> 记住用户
                                </label>
                            </div>
                            <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-hand-right"></i>
                                登录
                            </button>
                            &nbsp;
                            <span class="text-danger">
                                <c:if test="${loginError}">
                                    <i class="glyphicon glyphicon-warning-sign"></i> 登录失败, 请检查.
                                </c:if>
                                <c:if test="${accessError}">
                                    <i class="glyphicon glyphicon-warning-sign"></i> 访问被拒绝, 请检查.
                                </c:if>
                            </span>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <p class="text-muted text-center">&copy; 2008-2015 知识分子足球队 All rights reserved.</p>
        </div>
    </div>
</div>
</body>
</html>