
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
    <title>首页背景图片</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-warning">
            <div class="panel-body">
                <form:form commandName="formDto" role="form" id="photo_Form">
                    <form:hidden path="existUrl" id="existUrl"/>
                    <div class="form-group">
                        <label for="url" class="control-label">URL</label>
                        <form:input path="url" cssClass="form-control" id="url" placeholder="首页图片URL"
                                    required="required"/>
                        <form:errors path="url" cssClass="text-danger"/>
                        <p class="help-block">首页图片URL必须是一张图片,大小为1920x500, 且必须以http://开头, 如:
                            http://zsfz.qiniudn.com/aa.jpg.</p>
                    </div>
                    <div class="form-group">
                        <img src="${formDto.url}" alt="" style="max-width: 400px;" title="预览"
                             class="img-responsive img-thumbnail"/>
                        <a href="javascript:void(0)" id="updatePreview">更新预览</a>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label">描述</label>
                        <form:input path="description" id="description" class="form-control"
                                    placeholder="首页图片描述信息, 最多50个字"
                                    required="true" maxlength="50"/>
                        <form:errors path="description" cssClass="text-danger"/>
                        <p class="help-block">首页图片描述信息, 最多50个字.</p>
                    </div>

                    <button type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-ok-circle"></i> 更新
                    </button>
                    &nbsp;<a href="../front_photos.zsfz">取消</a>
                </form:form>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        new FrontPhotoForm();
    })
</script>
</body>
</html>