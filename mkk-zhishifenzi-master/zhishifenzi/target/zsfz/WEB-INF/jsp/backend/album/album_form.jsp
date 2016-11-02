
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
    <title>相册</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-body">
                <form:form commandName="formDto" role="form" enctype="multipart/form-data" id="albumForm">
                    <form:hidden path="existName"/>
                    <div class="form-group">
                        <label for="name" class="control-label">相册名称</label>
                        <form:input path="name" cssClass="form-control" id="name" placeholder="请输入相册名称"
                                    required="required"/>
                        <form:errors path="name" cssClass="text-danger"/>
                        <p class="help-block">相册名称必须唯一, 建议名称为: 日期 + 活动内容, 如: '2013-04-09 球队和环保局比赛照片'.</p>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label">相册描述</label>
                        <form:textarea path="description" id="description" class="form-control" rows="3"
                                       placeholder="请输入相册描述信息" required="true"/>
                        <form:errors path="description" cssClass="text-danger"/>
                        <p class="help-block">与该相册相关的描述信息.</p>
                    </div>
                    <c:if test="${formDto.newly}">
                        <p class="text-primary">
                            <i class="glyphicon glyphicon-picture"></i> 相册封面照片(图片大小不能超过1MB).
                        </p>

                        <div class="form-group">
                            <input type="file" id="file0" name="files[0]" required="required"/>
                            <form:errors path="files" cssClass="text-danger"/>
                            <p class="help-block">在创建相册成功后, 在相册的菜单中点击'管理照片'可上传相册更多照片.</p>
                        </div>
                    </c:if>

                    <button type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i> 保存
                    </button>
                    &nbsp;<a href="../../album.zsfz">取消</a>
                    &nbsp;&nbsp;<span class="text-danger well well-sm hide" id="loadingInfo">
                    <img src="${contextPath}/resources/images/loading.gif" alt="loading"/> 保存中, 请稍候...</span>
                </form:form>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        new AlbumForm();
    });
</script>
</body>
</html>