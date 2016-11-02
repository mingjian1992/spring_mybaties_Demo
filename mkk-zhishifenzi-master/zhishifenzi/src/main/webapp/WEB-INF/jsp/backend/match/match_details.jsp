
<%--
 * 
 * @author Shengzhao Li
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>比赛明细</title>
    <link rel="stylesheet" href="${contextPath}/resources/colorbox/colorbox.css"/>

    <style>
        .goal-ul li {
            margin-left: 20px;
        }

        .pictures-area a img {
            max-width: 180px;
            max-height: 140px;
        }
    </style>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-info">
            <div class="panel-heading">
                比赛明细
            </div>
            <div class="panel-body">
                <ul class="list-group">
                    <c:if test="${not empty detailsDto.notice}">
                        <li class="list-group-item">
                        <span class="text-muted"><i
                                class="glyphicon glyphicon-calendar"></i> 比赛预告: </span>
                            <i>${detailsDto.notice.previewText}</i>
                        </li>
                    </c:if>
                    <li class="list-group-item">
                        <span class="text-muted"><i
                                class="glyphicon glyphicon-calendar"></i> 比赛时间: </span> ${detailsDto.matchTime}
                    </li>
                    <li class="list-group-item">
                        <span class="text-muted"><i class="glyphicon glyphicon-hand-right"></i> 比赛对手: </span> <a
                            href="javascript:void(0)">${detailsDto.opponent.name}</a>
                    </li>
                    <li class="list-group-item">
                        <h3 class="list-group-item-heading">
                            <span class="text-muted"><i class="glyphicon glyphicon-pushpin"></i> 比分: </span>
                            <c:if test="${detailsDto.homeScores > detailsDto.awayScores}">
                                <strong class="text-danger">${detailsDto.homeScores}:${detailsDto.awayScores}</strong>
                            </c:if>
                            <c:if test="${detailsDto.homeScores eq detailsDto.awayScores}">
                                <strong class="text-muted">${detailsDto.homeScores}:${detailsDto.awayScores}</strong>
                            </c:if>
                            <c:if test="${detailsDto.homeScores <detailsDto.awayScores}">
                                <strong class="text-warning">${detailsDto.homeScores}:${detailsDto.awayScores}</strong>
                            </c:if>
                        </h3>

                        <div class="list-group-item-text">
                            <ul class="list-unstyled goal-ul">
                                <c:forEach items="${detailsDto.goals}" var="g" varStatus="s">
                                    <li>
                                        <img src="${contextPath}/resources/images/football_1.png"
                                             class="img-responsive pull-left" style="max-width: 16px;"/>&nbsp;
                                        <c:if test="${g.goalTime > -1}">
                                            第 ${g.goalTime} 分钟,
                                        </c:if>
                                        <c:if test="${g.ownGoal}">
                                            对手OG
                                        </c:if>
                                        <c:if test="${not g.ownGoal}">
                                            <a href="javascript:void(0)">${g.goalPlayer.fullName}</a> 进球, <a
                                                href="javascript:void(0)">${empty g.assistantPlayer.fullName?'无':g.assistantPlayer.fullName}</a> 助攻.
                                        </c:if>
                                    </li>
                                </c:forEach>
                            </ul>

                        </div>
                    </li>
                    <li class="list-group-item">
                        <span class="text-muted"><i class="glyphicon glyphicon-tower"></i> 比赛球场: </span> <a
                            href="javascript:void(0)">${detailsDto.stadium.name}</a>
                    </li>
                    <li class="list-group-item">
                        <span class="text-muted"><i class="glyphicon glyphicon-th"></i> 赛季: </span> <a
                            href="javascript:void(0)">${detailsDto.season.name}</a>
                    </li>
                    <li class="list-group-item">
                        <span class="text-muted"><i class="glyphicon glyphicon-user"></i> 参赛球员: </span>
                        <c:forEach items="${detailsDto.players}" var="p" varStatus="s">
                            <a href="javascript:void(0)">${p.fullName}</a>&nbsp;
                        </c:forEach>
                    </li>
                    <li class="list-group-item">
                        <span class="text-muted"><i class="glyphicon glyphicon-pencil"></i> 比赛备注: </span> <a
                            href="javascript:void(0)">${detailsDto.remark}</a>
                    </li>
                    <c:if test="${detailsDto.futsal}">
                        <li class="list-group-item">
                            <h4 class="text-danger"><i class="glyphicon glyphicon-star-empty"></i> 这是一场五人制足球赛.</h4>
                        </li>
                    </c:if>
                </ul>
                <div id="matchPhotos" guid="${detailsDto.guid}"></div>
                <p class="text-center">
                    <a href="javascript:history.back();" class="btn btn-info">&laquo; 返回</a>
                </p>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        new MatchDetails();
    });
</script>
</body>
</html>