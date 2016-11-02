
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
    <title>球员</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/datepicker.css"/>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-primary">
            <div class="panel-body">
                <form:form commandName="formDto" role="form">
                    <form:hidden path="existName"/>
                    <div class="form-group">
                        <label for="fullName" class="control-label">球员名字</label>
                        <form:input path="fullName" cssClass="form-control" id="fullName" placeholder="请输入球员名字"
                                    required="required"/>
                        <form:errors path="fullName" cssClass="text-danger"/>
                        <p class="help-block">球员名字必须唯一.</p>
                    </div>
                    <div class="form-group">
                        <label for="number" class="control-label">球衣号码</label>
                        <form:input path="number" cssClass="form-control" id="number" placeholder="请输入球衣号码"
                                    required="required"/>
                        <form:errors path="number" cssClass="text-danger"/>
                        <p class="help-block">球衣号码是数字, 如: 6; 可以有相同的号码.</p>
                    </div>
                    <div class="form-group">
                        <label for="position" class="control-label">场上位置</label>
                        <form:select path="position" id="position" cssClass="form-control">
                            <form:option value="" label="选择场上位置"/>
                            <form:options items="${formDto.positions}" itemLabel="label" itemValue="value"/>
                        </form:select>
                        <form:errors path="position" cssClass="text-danger"/>
                        <p class="help-block">球员在场上踢什么位置的.</p>
                    </div>
                    <div class="form-group">
                        <label for="entryDate" class="control-label">加入球队日期</label>
                        <form:input path="entryDate" cssClass="form-control" id="entryDate" placeholder="请选择加入球队日期"/>
                        <form:errors path="entryDate" cssClass="text-danger"/>
                        <p class="help-block">若不清楚具体的日期就选择一个大概的日期啦, 格式: yyyy-MM-dd.</p>
                    </div>
                    <div class="form-group">
                        <label for="birthday" class="control-label">球员生日</label>
                        <form:input path="birthday" cssClass="form-control" id="birthday" placeholder="请选择球员生日"/>
                        <form:errors path="birthday" cssClass="text-danger"/>
                        <p class="help-block">日期格式: yyyy-MM-dd.</p>
                    </div>
                    <div class="form-group">
                        <label for="phone" class="control-label">联系电话</label>
                        <form:input path="phone" cssClass="form-control" id="phone" placeholder="请输入联系电话"/>
                        <form:errors path="phone" cssClass="text-danger"/>
                        <p class="help-block">手机号码, 固定电话等.</p>
                    </div>
                    <div class="form-group">
                        <label for="email" class="control-label">邮件地址</label>
                        <form:input path="email" cssClass="form-control" id="email" placeholder="请输入常用邮件地址"/>
                        <form:errors path="email" cssClass="text-danger"/>
                        <p class="help-block">球队有的活动会发邮件通知.</p>
                    </div>
                    <div class="checkbox">
                        <label>
                            <form:checkbox path="captain"/> 是否为我们的队长
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label">球员介绍</label>
                        <form:textarea path="description" id="description" class="form-control" rows="3"
                                       placeholder="球员的介绍信息"/>
                        <form:errors path="description" cssClass="text-danger"/>
                        <p class="help-block">球员的介绍信息, 如学历爱好等.</p>
                    </div>
                    <br/>
                    <button type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i> 保存
                    </button>
                    &nbsp;<a href="../../player.zsfz">取消</a>

                </form:form>
            </div>
        </div>
    </div>
</div>

<script src="${contextPath}/resources/js/datepicker/bootstrap-datepicker.js"></script>
<script src="${contextPath}/resources/js/datepicker/bootstrap-datepicker.zh-CN.js"></script>

<script>
    $(function () {
        new PlayerForm();
    });
</script>
</body>
</html>