
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>相册明细</title>

    <link rel="stylesheet" href="${contextPath}/resources/colorbox/colorbox.css"/>

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
                            <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_CAPTAIN">
                                <a href="../manage/${manageDto.guid}.zsfz" class="btn btn-success btn-sm"><span
                                        class="glyphicon glyphicon-cog"></span> 管理照片</a>
                                &nbsp;
                            </sec:authorize>
                            <a href="../../album.zsfz" class="btn btn-default btn-sm"><span
                                    class="glyphicon glyphicon-chevron-left"></span> 返回</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-body pictures-area">

                <div class="row">
                    <c:forEach items="${manageDto.photos}" var="a" varStatus="s">
                    <div class="col-md-3">
                        <a href="${contextPath}/public/photo/${a.guid}.zsfz" title="${a.name}" class="pictureGroup">
                            <img class="img-thumbnail lazy"
                                 data-original="${contextPath}/public/photo/${a.guid}.zsfz?w=200"
                                 src="${contextPath}/resources/images/loading.gif"
                                 alt="P"/>
                        </a>
                        <br/>
                    </div>
                    <c:if test="${s.count%4==0}">
                </div>
                <div class="row">
                    </c:if>
                    </c:forEach>
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
<script src="${contextPath}/resources/js/jquery.lazyload.min.js"></script>
<script>
    $(function () {
        new AlbumDetails();
    });
</script>
</body>
</html>