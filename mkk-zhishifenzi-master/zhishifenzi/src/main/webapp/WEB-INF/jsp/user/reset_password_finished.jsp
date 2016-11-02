
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>重置密码成功</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <!--// alert start -->
        <div class="alert alert-success text-center">
            <div>
                <h3>重置密码成功!</h3>

                该用户的新密码为: <span class="label label-default">${newPass}</span>,
                请提醒该用户在登录成功后修改密码.
            </div>
            <p>
                <br/>
                <a class="btn btn-info" role="button" href="javascript:history.back();">返回</a>
            </p>
        </div>
        <!--// alert end -->
    </div>
</div>
</body>
</html>