
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
    <title>对手</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-warning">
            <div class="panel-body">
                <form:form commandName="formDto" role="form">
                    <form:hidden path="existName"/>
                    <div class="form-group">
                        <label for="name" class="control-label">对手名称</label>
                        <form:input path="name" cssClass="form-control" id="name" placeholder="请输入对手名称"
                                    required="required"/>
                        <form:errors path="name" cssClass="text-danger"/>
                        <p class="help-block">对手名称应该是一支足球队名称, 如 '清江足球队'.</p>
                    </div>
                    <div class="form-group">
                        <label for="contact" class="control-label">联系人</label>
                        <form:input path="contact" cssClass="form-control" id="contact" placeholder="请输入联系人"
                                    required="required"/>
                        <form:errors path="contact" cssClass="text-danger"/>
                        <p class="help-block">指该对手的联系人信息, 如: '李四 13300231117(手机) 87658767(QQ号)'.</p>
                    </div>
                    <div class="form-group">
                        <label for="remark" class="control-label">备注信息</label>
                        <form:textarea path="remark" id="remark" class="form-control" rows="3"
                                       placeholder="关于该对手的其他信息, 在这儿输入."/>
                        <form:errors path="remark" cssClass="text-danger"/>
                        <p class="help-block">对手的其他信息.</p>
                    </div>

                    <button type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i> 保存
                    </button>
                    &nbsp;<a href="../../club.zsfz">取消</a>

                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>