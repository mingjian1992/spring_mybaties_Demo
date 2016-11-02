
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>修改密码</title>
</head>
<body>

<div class="row">
    <div class="col-md-12">
        <form:form commandName="changePasswordDto" id="changePasswordDtoForm">
            <div class="panel panel-primary">
                <div class="panel-body">
                    <div class="form-group">
                        <div class="alert alert-warning">
                            <strong>注意:</strong> 修改密码成功后请重新登录系统.
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="username">登录名</label><form:input path="username" id="username"
                                                                     placeholder="请输入登录名" size="30"
                                                                     cssClass="form-control" disabled="true"
                                                                     maxlength="255" required="true"/>
                    </div>
                    <div class="form-group">
                        <label for="oldPassword">旧密码</label>
                        <form:password path="oldPassword" id="oldPassword" placeholder="请输入旧密码" maxlength="255"
                                       size="50" cssClass="form-control" required="true"/>
                        <form:errors path="oldPassword" cssClass="text-danger"/>
                    </div>
                    <div class="form-group">
                        <label for="newPassword">新密码</label>
                        <form:password path="newPassword" id="newPassword" placeholder="新密码, 长度至少6位" title="新密码, 长度至少6位"
                                       maxlength="255"
                                       size="50" cssClass="form-control" required="true"/>
                        <form:errors path="newPassword" cssClass="text-danger"/>
                    </div>

                    <div class="form-group">
                        <label for="confirmPassword">确认密码</label>
                        <form:password path="confirmPassword" id="confirmPassword" placeholder="请再次输入密码" title="请再次输入密码"
                                       maxlength="255" cssClass="form-control"
                                       size="50" required="true"/>
                        <form:errors path="confirmPassword" cssClass="text-danger"/>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-ok"></i> 修改
                        </button>
                        &nbsp;<a href="index.zsfz">取消</a>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>

</body>
</html>