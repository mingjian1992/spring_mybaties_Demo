
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>球员账号</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <!--// alert start -->
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="glyphicon glyphicon-ok"></i>
                <span id="persistSuccess" class="hide">保存球员账号信息成功!</span>
                <span id="enableAccountSuccess" class="hide">启用球员账号成功!</span>
            </div>
        </div>
        <div class="alert alert-info hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="glyphicon glyphicon-info-sign"></i>
                <span id="disableFinishInfo" class="hide">禁用球员账号完成.</span>
            </div>
        </div>
        <div class="alert alert-error hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-warning-sign icon-white"></i>

            </div>
        </div>
        <!--// alert end -->
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="panel panel-primary">
            <div class="panel-body">
                <form:form commandName="formDto" role="form" cssClass="form-horizontal">
                    <form:hidden path="guid"/>
                    <form:hidden path="existUsername"/>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">球员</label>

                        <div class="col-sm-10">
                            <p class="form-control-static">${formDto.player.fullName}</p>
                            <form:hidden path="player.guid"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="username" class="col-sm-2 control-label">用户名</label>

                        <div class="col-sm-10">
                            <form:input path="username" cssClass="form-control" id="username" placeholder="请输入用户名"
                                        required="true"/>
                            <form:errors path="username" cssClass="text-danger"/>
                            <p class="help-block">用户名默认为球员的拼音字母.</p>
                        </div>
                    </div>
                    <c:if test="${formDto.newly}">
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">密码</label>

                            <div class="col-sm-10">
                                <form:password path="password" cssClass="form-control" id="password"
                                               placeholder="请输入密码,至少6位"
                                               required="true"/>
                                <form:errors path="password" cssClass="text-danger"/>
                                <p class="help-block">用户密码长度至少6位.</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="rePassword" class="col-sm-2 control-label">确认密码</label>

                            <div class="col-sm-10">
                                <form:password path="rePassword" cssClass="form-control" id="rePassword"
                                               placeholder="请再次输入用户密码"
                                               required="true"/>
                                <form:errors path="rePassword" cssClass="text-danger"/>
                                <p class="help-block">两次输入的密码必须一致.</p>
                            </div>
                        </div>
                    </c:if>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">角色</label>

                        <div class="col-sm-10">
                            <p class="form-control-static">${formDto.userRole.label}</p>
                            <form:hidden path="userRole"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="others" class="col-sm-2 control-label">其他信息</label>

                        <div class="col-sm-10">
                            <form:textarea path="others" id="others" rows="3" cssClass="form-control"
                                           playerholder="关于账号的其他信息"/>
                            <p class="help-block">关于账号的其他信息, 在这儿输入.</p>
                        </div>
                    </div>
                    <br/>

                    <div class="form-group">
                        <div class="col-md-2"></div>
                        <div class="col-md-10">
                            <button type="submit" class="btn btn-primary"><i
                                    class="glyphicon glyphicon-floppy-saved"></i> 保存
                            </button>
                            &nbsp;<a href="../../player.zsfz">返回</a>
                            <c:if test="${not formDto.newly}">
                                &nbsp;|&nbsp;&nbsp;
                                <div class="btn-group">
                                    <c:if test="${formDto.status.enabled}">
                                        <a href="disable/${formDto.guid}.zsfz" onclick="return confirm('确认禁用该账号?');"
                                           class="btn btn-warning"><i
                                                class="glyphicon glyphicon-lock"></i> 禁用账号</a>
                                        <a href="reset_password/${formDto.guid}.zsfz"
                                           onclick="return confirm('确认重置该账号密码?');" class="btn btn-info"><i
                                                class="glyphicon glyphicon-repeat"></i> 重置密码</a>
                                    </c:if>
                                    <c:if test="${formDto.status.disabled}">
                                        <a href="enable/${formDto.guid}.zsfz" onclick="return confirm('确认启用该账号?');"
                                           class="btn btn-warning"><i
                                                class="glyphicon glyphicon-retweet"></i>
                                            启用账号</a>
                                    </c:if>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        new PlayerAccountForm('${param.alert}');
    });
</script>
</body>
</html>