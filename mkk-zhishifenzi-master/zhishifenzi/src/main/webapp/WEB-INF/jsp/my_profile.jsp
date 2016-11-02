
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
    <title>更新信息</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/datepicker.css"/>
</head>
<body>

<div class="row">
    <div class="col-md-12">
        <!--// alert start -->
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="glyphicon glyphicon-ok"></i>
                <span id="updateSuccess" class="hide">更新个人信息成功!</span>
            </div>
        </div>
        <c:if test="${not formDto.enabled}">
            <div class="alert alert-danger">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <div><i class="glyphicon glyphicon-warning-sign"></i>
                    <span>你的角色是非球员, 不能修改个人信息!</span>
                </div>
            </div>
        </c:if>
        <!--// alert end -->
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <form:form commandName="formDto" id="profileForm" action="${contextPath}/b/my_profile.zsfz">
            <form:hidden path="enabled"/>
            <div class="panel panel-primary">
                <div class="panel-body">
                    <div class="form-group">
                        <label for="fullName">姓名</label><form:input path="fullName" id="fullName"
                                                                    placeholder="请输入用户姓名" size="30"
                                                                    cssClass="form-control"
                                                                    maxlength="255" required="true"/>
                        <form:errors path="fullName" cssClass="text-danger"/>
                        <p class="help-block">你的尊姓大名.</p>
                    </div>
                    <div class="form-group">
                        <label for="number" class="control-label">球衣号码</label>
                        <form:input path="number" cssClass="form-control" id="number" placeholder="请输入球衣号码"
                                    required="required"/>
                        <form:errors path="number" cssClass="text-danger"/>
                        <p class="help-block">我的球衣号码.</p>
                    </div>
                    <div class="form-group">
                        <label for="email">邮件地址</label><form:input path="email" id="email"
                                                                   placeholder="请输入邮件地址" size="30"
                                                                   cssClass="form-control" required="true"
                                                                   maxlength="255"/>
                        <form:errors path="email" cssClass="text-danger"/>
                        <p class="help-block">球队有的信息会通过发送邮件通知.</p>
                    </div>
                    <div class="form-group">
                        <label for="phone">联系电话</label><form:input path="phone" id="phone"
                                                                   placeholder="请输入联系电话"
                                                                   cssClass="form-control"
                                                                   maxlength="255"/>
                        <form:errors path="phone" cssClass="text-danger"/>
                        <p class="help-block">方便大家联系.</p>
                    </div>
                    <div class="form-group">
                        <label for="entryDate">加入球队日期</label><form:input path="entryDate" id="entryDate"
                                                                         placeholder="请选择加入球队日期"
                                                                         cssClass="form-control"
                                                                         maxlength="255"/>
                        <form:errors path="entryDate" cssClass="text-danger"/>
                        <p class="help-block">若不清楚具体的日期就选择一个大概的日期啦.</p>
                    </div>
                    <div class="form-group">
                        <label for="birthday">生日日期</label><form:input path="birthday" id="birthday"
                                                                      placeholder="请选择我的生日日期"
                                                                      cssClass="form-control"
                                                                      maxlength="255"/>
                        <form:errors path="birthday" cssClass="text-danger"/>
                        <p class="help-block">这个日期不会记错吧.</p>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label">自我描述</label>
                        <form:textarea path="description" id="description" class="form-control" rows="3"
                                       placeholder="写点自我描述吧"/>
                        <form:errors path="description" cssClass="text-danger"/>
                        <p class="help-block">自我描述, 如你的兴趣,学历等等你想告诉我们的.</p>
                    </div>
                    <div class="form-group">
                        <c:if test="${formDto.enabled}">
                            <button type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-ok"></i> 保存
                            </button>
                        </c:if>
                        &nbsp;<a href="index.zsfz">返回</a>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>

<script src="${contextPath}/resources/js/datepicker/bootstrap-datepicker.js"></script>
<script src="${contextPath}/resources/js/datepicker/bootstrap-datepicker.zh-CN.js"></script>

<script>
    $(function () {
        new MyProfile('${param.alert}');
    });
</script>
</body>
</html>