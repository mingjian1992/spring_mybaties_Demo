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
    <title>全局设置</title>
</head>
<body>

<div class="row">
    <div class="col-md-12">
        <!--// alert start -->
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="glyphicon glyphicon-ok"></i>
                <span id="updateSuccess" class="hide">保存全局设置成功!!</span>
            </div>
        </div>
        <!--// alert end -->
    </div>
</div>

<div class="row">

    <div class="col-md-12">
        <div class="panel panel-danger">
            <div class="panel-body">
                <form:form commandName="formDto" onsubmit="return confirm('确认保存全局设置并立刻生效?');">
                    <div class="form-group">
                        <label>使用默认的首页背景图片</label>
                        <br/>
                        <label class="radio-inline">
                            <form:radiobutton path="useDefaultFrontPhotos" value="true"/>是, 使用默认的
                        </label>
                        <label class="radio-inline">
                            <form:radiobutton path="useDefaultFrontPhotos" value="false"/>否, 启用自定义的
                        </label>
                        <form:errors path="useDefaultFrontPhotos" cssClass="text-danger"/>
                        <p class="help-block">首页背景图片是指首页菜单后面滚动的图片. 若不使用默认的则从 "相册"->"首页背景图片" 中加载对应的图片,可用于动态修改背景图片</p>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-ok"></i> 保存设置
                        </button>
                    </div>
                </form:form>
            </div>
            <div class="panel-footer text-muted">
                <i class="glyphicon glyphicon-info-sign"></i>
                每一项设置对应具体的功能点.
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        new GlobalSetting('${param.alert}');
    });
</script>
</body>
</html>