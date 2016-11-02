
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
    <title>球场</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-warning">
            <div class="panel-body">
                <form:form commandName="formDto" role="form">
                    <form:hidden path="existName"/>
                    <div class="form-group">
                        <label for="name" class="control-label">球场名称</label>
                        <form:input path="name" cssClass="form-control" id="name" placeholder="请输入球场名称"
                                    required="required"/>
                        <form:errors path="name" cssClass="text-danger"/>
                        <p class="help-block">球场名称必须唯一.</p>
                    </div>
                    <div class="form-group">
                        <label for="contact" class="control-label">联系人</label>
                        <form:input path="contact" cssClass="form-control" id="contact" placeholder="请输入联系人"
                                    required="required"/>
                        <form:errors path="contact" cssClass="text-danger"/>
                        <p class="help-block">该球场的联系人信息, 如: '李四 13300231117(手机) 87658767(QQ号)'.</p>
                    </div>
                    <div class="form-group">
                        <label for="address" class="control-label">球场地址</label>
                        <form:input path="address" cssClass="form-control" id="address" placeholder="请输入球场地址"
                                    required="required"/>
                        <form:errors path="address" cssClass="text-danger"/>
                        <p class="help-block">球场的具体位置.</p>
                    </div>
                    <div class="checkbox">
                        <label>
                            <form:checkbox path="homeStadium"/> 是否为我们的主场
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="remark" class="control-label">备注信息</label>
                        <form:textarea path="remark" id="remark" class="form-control" rows="3"
                                       placeholder="关于该球场的其他信息, 在这儿输入."/>
                        <form:errors path="remark" cssClass="text-danger"/>
                        <p class="help-block">球场的其他信息.</p>
                    </div>

                    <button type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i> 保存
                    </button>
                    &nbsp;<a href="../../stadium.zsfz">取消</a>

                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>