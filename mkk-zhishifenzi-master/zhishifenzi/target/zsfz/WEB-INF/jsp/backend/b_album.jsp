
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dis" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>相册</title>

    <style>
        .img-circle {
            max-width: 140px;
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
                <span id="persistSuccess" class="hide">保存相册信息成功!</span>
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
                        <form id="albumForm" action="" class="form-inline" role="form">
                            <div class="form-group">
                                <label class="sr-only" for="name">相册名称</label>
                                <input id="name" type="text" name="name"
                                       value="${listDto.name}"
                                       title="相册名称" placeholder="相册名称" class="form-control"/>
                            </div>
                            <button class="btn btn-success"><i class="glyphicon glyphicon-search"></i> 查询
                            </button>
                        </form>
                    </div>
                    <div class="col-md-4">
                        <div class="pull-right">
                            <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_CAPTAIN">
                                <a href="album/form/create.zsfz" class="btn btn-primary"><span
                                        class="glyphicon glyphicon-camera"></span> 创建相册</a>
                                <sec:authorize ifAnyGranted="ROLE_ADMIN">
                                    <a href="album/front_photos.zsfz" class="btn btn-warning"><span
                                            class="glyphicon glyphicon-picture"></span> 首页背景图片</a>
                                </sec:authorize>
                            </sec:authorize>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <c:if test="${empty listDto.list}">
                    <div class="text-muted">
                        尚未添加任何的相册, 请点击'创建相册'按钮添加.
                    </div>
                </c:if>

                <div class="row">
                    <c:forEach items="${listDto.list}" var="a" varStatus="s">
                    <div class="col-lg-4 text-center">
                        <a href="album/details/${a.guid}.zsfz" title="共 ${a.photoSize} 张照片">
                            <img class="img-circle lazy"
                                 data-original="${contextPath}/public/photo/${a.firstPhotoGuid}.zsfz?w=200"
                                 src="${contextPath}/resources/images/loading.gif"
                                 alt=""/>
                        </a>

                        <h2 class="text-info">${a.name} </h2>

                        <p class="text-muted">${a.description}</p>

                        <div class="btn-group">
                            <a href="album/details/${a.guid}.zsfz" class="btn btn-default btn-sm">查看 &raquo;</a>
                            <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_CAPTAIN">
                                <a href="javascript:void(0)" class="btn btn-default btn-sm dropdown-toggle"
                                   data-toggle="dropdown">
                                    <span class="caret"></span>
                                    <span class="sr-only">Toggle Dropdown</span>
                                </a>
                                <ul class="dropdown-menu text-left" role="menu">
                                    <li><a href="album/manage/${a.guid}.zsfz"><i class="glyphicon glyphicon-cog"></i>
                                        管理照片</a></li>
                                    <li class="divider"></li>
                                    <li><a href="album/form/${a.guid}.zsfz"><i class="glyphicon glyphicon-pencil"></i>
                                        编辑相册</a></li>
                                    <li><a href="album/archive/${a.guid}.zsfz"
                                           onclick="return confirm('确认删除该相册(将同时删除相册的所有照片)?');"><i
                                            class="glyphicon glyphicon-remove-circle"></i> 删除相册</a></li>
                                </ul>
                            </sec:authorize>
                        </div>
                        <br/>
                        <br/>
                    </div>
                    <c:if test="${s.count%3==0}">
                </div>
                <div class="row">
                    </c:if>
                    </c:forEach>
                </div>

                <div class="text-center">
                    <dis:table id="c" class="table hidden" list="${listDto}"
                               form="albumForm">
                    </dis:table>
                </div>
            </div>
            <div class="panel-footer">
                <div class="text-warning">
                    <i class="glyphicon glyphicon-exclamation-sign"></i> 共 <strong>${listDto.totalSize}</strong> 个相册.
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${contextPath}/resources/js/jquery.lazyload.min.js"></script>
<script>
    $(function () {
        new BackendAlbum('${param.alert}');
    });
</script>
</body>
</html>