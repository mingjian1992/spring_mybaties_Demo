
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${empty mainDto.list?'':mainDto.currentAlbum.name} - 相册</title>

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
    <div class="col-md-3">
        <div class="list-group">
            <c:if test="${empty mainDto.list}">
                <a href="javascript:void(0);" class="list-group-item active">
                    尚未添加相册
                </a>
            </c:if>
            <c:forEach items="${mainDto.list}" var="a" varStatus="s">
                <a href="?guid=${a.guid}" class="list-group-item ${a.guid eq mainDto.guid?'active':''}">
                    <span class="badge">${a.photoSize}</span>
                        ${a.name}
                </a>
            </c:forEach>
        </div>

        <%--share --%>
        <div class="bdsharebuttonbox"><a href="#" class="bds_more" data-cmd="more"></a><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a><a href="#" class="bds_sqq" data-cmd="sqq" title="分享到QQ好友"></a><a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a></div>
        <script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"24"},"share":{},"image":{"viewList":["tsina","tqq","weixin","sqq","qzone"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["tsina","tqq","weixin","sqq","qzone"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>

    </div>
    <div class="col-md-9">
        <div class="panel panel-default">
            <div class="panel-body pictures-area">
                <div class="row">
                    <c:forEach items="${mainDto.currentAlbum.photos}" var="p" varStatus="s">
                    <div class="col-md-3">
                        <a href="${contextPath}/public/photo/${p.guid}.zsfz" class="pictureGroup">
                            <img data-original="${contextPath}/public/photo/${p.guid}.zsfz?w=180" alt="..."
                                 src="${contextPath}/resources/images/loading.gif"
                                 class="img-thumbnail lazy"/>
                        </a>
                    </div>
                    <c:if test="${s.count%4 eq 0}">
                </div>
                <div class="row" style="margin-top:5px;">
                    </c:if>
                    </c:forEach>

                </div>
            </div>
            <div class="panel-footer text-info">
                <i class="glyphicon glyphicon-hand-right"></i> ${mainDto.currentAlbum.description}
                <c:if test="${not empty mainDto.currentAlbum.matchGuid}">
                    &nbsp;&nbsp;<a href="match_details/${mainDto.currentAlbum.matchGuid}.zsfz"><span class="label label-default">查看该相册的比赛信息</span></a>
                </c:if>
            </div>
        </div>

    </div>
</div>

<script src="${contextPath}/resources/colorbox/jquery.colorbox-min.js"></script>
<script src="${contextPath}/resources/colorbox/jquery.colorbox-zh-CN.js"></script>
<script src="${contextPath}/resources/js/jquery.scrollUp.min.js"></script>
<script src="${contextPath}/resources/js/jquery.lazyload.min.js"></script>

<script>
    $(function () {
        new AlbumMain();
    });
</script>
</body>
</html>