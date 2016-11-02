
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>同步照片 ${photoDto.name}</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-warning">
            <div class="panel-body">
                <form method="post" action="" class="form-horizontal" role="form"
                      onsubmit="return confirm('确认同步照片到云存储?')">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">照片名称</label>

                        <div class="col-sm-10">
                            <p class="form-control-static">
                                ${photoDto.name}</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">所属相册</label>

                        <div class="col-sm-10">
                            <p class="form-control-static">
                                <a href="../../album/details/${photoDto.albumGuid}.zsfz">${photoDto.albumName}</a></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">照片预览</label>

                        <div class="col-sm-10">
                            <p class="form-control-static">
                                <a href="../../../public/image/${photoDto.guid}.zsfz" target="_blank"><img
                                        src="../../../public/image/${photoDto.guid}.zsfz?w=150" alt="preview"
                                        class="img-thumbnail"/></a>
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"></label>

                        <div class="col-sm-10">
                            <label class="checkbox-inline">
                                <input type="checkbox" name="deleteLocalPhoto" value="true"/> 删除服务器上的照片
                            </label>

                            <p class="help-block">
                                如果在同步照片到云存储服务器后删除服务器上的照片, 请勾选.
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label"></label>

                        <div class="col-sm-11">
                            <button type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-cloud"></i>
                                执行同步
                            </button>
                            &nbsp;<a href="javascript:history.back();">返回</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>