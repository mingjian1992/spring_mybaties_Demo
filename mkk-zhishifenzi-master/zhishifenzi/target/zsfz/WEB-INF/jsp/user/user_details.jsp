
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>用户</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-warning">
            <div class="panel-body">
                <p>
                    <span class="text-muted">姓名: </span> ${detailsDto.fullName}
                </p>

                <p>
                    <span class="text-muted">登录名: </span> ${detailsDto.username}
                </p>

                <p>
                    <span class="text-muted">状态: </span> <strong
                        class="${detailsDto.status.enabled?'text-success':'text-danger'}">${detailsDto.status.label}</strong>
                </p>

                <p>
                    <span class="text-muted">电话: </span> ${detailsDto.phone}
                </p>

                <p>
                    <span class="text-muted">入职日期: </span> ${detailsDto.entryDate}
                </p>

                <p>
                    <span class="text-muted">生日日期: </span> ${detailsDto.birthday}
                </p>

                <p>
                    <span class="text-muted">用户类型: </span> ${detailsDto.userRole.label}
                </p>


                <p>
                    <span class="text-muted">邮件地址: </span> <a href="mailto:${detailsDto.email}">${detailsDto.email}</a>
                </p>

                <p>
                    <span class="text-muted">添加日期: </span> ${detailsDto.createDate}
                </p>

                <p>
                    <span class="text-muted">上次登录时间: </span> ${detailsDto.lastLoginTime}
                </p>

                <p>
                    <span class="text-muted">其他信息: </span> ${detailsDto.others}
                </p>

                <p>
                    <a href="javascript:history.back();" class="btn btn-info">返回</a>
                </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>