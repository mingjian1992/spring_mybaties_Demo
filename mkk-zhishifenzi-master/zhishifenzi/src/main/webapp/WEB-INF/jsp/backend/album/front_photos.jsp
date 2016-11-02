
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>首页背景图片</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <!--// alert start -->
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="glyphicon glyphicon-ok"></i>
                <%--<span id="uploadPhotosSuccess" class="hide">上传照片成功!</span>--%>
            </div>
        </div>
        <!--// alert end -->
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="panel panel-warning">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-md-8">
                        &nbsp;
                    </div>
                    <div class="col-md-4">
                        <div class="pull-right">
                            <a href="../../" target="_blank" class="btn btn-info btn-sm"><span
                                    class="glyphicon glyphicon-picture"></span> 首页预览</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-striped">
                    <thead>
                    <tr>
                        <th>URL</th>
                        <th>预览</th>
                        <th>描述</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${frontPhotosDto.photos}" var="p">
                        <tr>
                            <td>
                                <a href="${p.url}" target="_blank">${p.url}</a>
                            </td>
                            <td>
                                <a href="${p.url}" target="_blank"><img src="${p.url}" alt="" style="max-width: 100px"
                                                                        class="img-responsive img-thumbnail"/></a>
                            </td>
                            <td>${p.description}</td>
                            <td>
                                <a href="front_form/${p.guid}.zsfz">编辑</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="panel-footer text-warning">
                <i class="glyphicon glyphicon-pushpin"></i> 首页背景图片只有4张, 图片大小为 1920x500; 只能编辑, 不能添加与删除
            </div>
        </div>
    </div>
</div>

</body>
</html>