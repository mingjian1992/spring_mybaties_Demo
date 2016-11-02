
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>管理照片</title>

    <link rel="stylesheet" href="${contextPath}/resources/colorbox/colorbox.css"/>
    <link rel="stylesheet" href="${contextPath}/resources/uploadify/uploadify.css"/>

    <style>
        .pictures-area a img {
            max-width: 180px;
            max-height: 140px;
        }
    </style>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <!--// alert start -->
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="glyphicon glyphicon-ok"></i>
                <span id="removePhotoSuccess" class="hide">删除照片成功!</span>
                <span id="uploadPhotosSuccess" class="hide">上传照片成功!</span>
            </div>
        </div>
        <div class="alert alert-info hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="glyphicon glyphicon-info-sign"></i>
                <span id="archiveFinishInfo" class="hide">删除相册完成.</span>
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
        <div class="panel panel-default">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-md-8">
                        <p>
                            ${manageDto.name} <span class="badge">${manageDto.photoSize}</span>
                        </p>
                    </div>
                    <div class="col-md-4">
                        <div class="pull-right">
                            <a href="javascript:void(0)" id="showUploadA" class="btn btn-primary btn-sm"><span
                                    class="glyphicon glyphicon-upload"></span> 上传照片</a>
                            &nbsp;
                            <a href="../../album.zsfz" class="btn btn-default btn-sm"><span
                                    class="glyphicon glyphicon-chevron-left"></span> 返回</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <div class="well well-sm" style="display: none;">
                    <input type="file" name="file_upload" id="file_upload"/>
                    <span class="text-warning">(单张图片大小不能超过1MB, 每次最多上传10张)</span>

                    <div id="fileQueue"></div>
                    <br/>
                    <button class="btn btn-primary" id="uploadBtn"><i class="glyphicon glyphicon-arrow-up"></i> 上传
                    </button>
                </div>
                <div class="pictures-area">
                    <div class="row">
                        <c:forEach items="${manageDto.photos}" var="a" varStatus="s">
                        <div class="col-md-3 text-center">
                            <a href="${contextPath}/public/photo/${a.guid}.zsfz" title="${a.name}" class="pictureGroup">
                                <img class="img-thumbnail text-center lazy"
                                     data-original="${contextPath}/public/photo/${a.guid}.zsfz?w=200"
                                     src="${contextPath}/resources/images/loading.gif"
                                     alt="P"/>
                            </a>

                            <div style="margin-top: 3px;">
                                <c:if test="${not manageDto.onlyOnePhoto}">
                                    <a href="remove/${a.guid}.zsfz" class="btn btn-danger btn-xs"
                                       onclick="return confirm('确认删除该照片?');"><i
                                            class="glyphicon glyphicon-remove-circle"></i> 删除照片</a>
                                </c:if>
                            </div>
                        </div>
                        <c:if test="${s.count%4==0}">
                    </div>
                    <div class="row">
                        <br/>
                        </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="panel-footer text-info">
                <i class="glyphicon glyphicon-pushpin"></i> ${manageDto.description}
                <c:if test="${not empty manageDto.matchGuid}">
                    &nbsp;&nbsp;<a href="../../match/details/${manageDto.matchGuid}.zsfz"><span class="label label-default">查看比赛信息</span></a>
                </c:if>
            </div>
        </div>
    </div>
</div>


<script src="${contextPath}/resources/colorbox/jquery.colorbox-min.js"></script>
<script src="${contextPath}/resources/colorbox/jquery.colorbox-zh-CN.js"></script>

<script src="${contextPath}/resources/uploadify/js/jquery.uploadify.min.js"></script>
<script src="${contextPath}/resources/js/jquery.lazyload.min.js"></script>
<script>
    $(function () {
        new AlbumManage('${param.alert}', '${manageDto.guid}');
    });
</script>
</body>
</html>