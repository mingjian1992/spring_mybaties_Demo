
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>纪录</title>

    <style>
        .record .col-lg-4 {
            text-align: center;
            margin-bottom: 10px;
        }

        .img-circle {
            width: 140px;
            height: 140px;
        }
    </style>

    <script src="${contextPath}/resources/js/holder.js"></script>

</head>
<body>
<div class="row record">
    <div class="col-lg-4">
        <img class="img-circle"
             data-src="${contextPath}/resources/js/holder.js/140x140/social/text:${recordDto.totalGoalsPlayerName} (${recordDto.totalGoals})"
             alt="最佳射手"/>

        <h2 class="text-success">最佳射手</h2>

        <p class="text-muted">知识分子足球队的最佳射手是 <strong>${recordDto.totalGoalsPlayerName}</strong>,
            他在参加的比赛中一共打入了${recordDto.totalGoals}个进球.</p>

        <p><a class="btn btn-default"
              href="record_player_goal.zsfz?guid=${recordDto.totalGoalsPlayerGuid}&name=${recordDto.totalGoalsPlayerName}"
              role="button">查看进球纪录 &raquo;</a>
        </p>
    </div>
    <!-- /.col-lg-4 -->
    <div class="col-lg-4">
        <img class="img-circle"
             data-src="${contextPath}/resources/js/holder.js/140x140/sky/text:${recordDto.totalAssistsPlayerName} (${recordDto.totalAssists})"
             alt="助攻王"/>

        <h2 class="text-success">助攻王</h2>

        <p class="text-muted">他默默奉献, 他甘当绿叶, 他就是 <strong>${recordDto.totalAssistsPlayerName}</strong>, 他是我们学习的好榜样.</p>

        <p><a class="btn btn-default"
              href="record_player_assist.zsfz?guid=${recordDto.totalAssistsPlayerGuid}&name=${recordDto.totalAssistsPlayerName}"
              role="button">查看助攻纪录 &raquo;</a></p>
    </div>
    <!-- /.col-lg-4 -->
    <div class="col-lg-4">
        <img class="img-circle"
             data-src="${contextPath}/resources/js/holder.js/140x140/lava/text:${recordDto.singleMatchGoalsPlayerName} (${recordDto.singleMatchGoals})"
             alt="个人单场最多进球"/>

        <h2 class="text-success">个人单场最多进球</h2>

        <p class="text-muted">
            在 ${recordDto.singleGoalsMatchDate} 与 (${recordDto.singleGoalsMatchOpponent}) 的比赛中,
            <strong>${recordDto.singleMatchGoalsPlayerName}</strong> 攻入了${recordDto.singleMatchGoals}个进球,
            是球队个人单场最多进球的纪录保持者.
        </p>

        <p><a class="btn btn-default" href="match_details/${recordDto.singleGoalsMatchGuid}.zsfz"
              role="button">查看该场比赛 &raquo;</a></p>
    </div>
    <!-- /.col-lg-4 -->
</div>
<!-- /.row -->

<div class="row record">
    <div class="col-lg-4">
        <img class="img-circle"
             data-src="${contextPath}/resources/js/holder.js/140x140/lava/text:${recordDto.singleAssistsPlayerName} (${recordDto.singleMatchAssists})"
             alt="个人单场最多助攻"/>

        <h2 class="text-success">个人单场最多助攻</h2>

        <p class="text-muted">
            在 ${recordDto.singleAssistsMatchDate} 与 (${recordDto.singleAssistsMatchOpponent} ) 的比赛中,
            <strong>${recordDto.singleAssistsPlayerName}</strong> 完成了${recordDto.singleMatchAssists}次助攻,
            是球队个人单场最多助攻的纪录保持者.
        </p>

        <p><a class="btn btn-default" href="match_details/${recordDto.singleAssistsMatchGuid}.zsfz"
              role="button">查看该场比赛 &raquo;</a></p>
    </div>
    <!-- /.col-lg-4 -->
    <div class="col-lg-4">
        <img class="img-circle"
             data-src="${contextPath}/resources/js/holder.js/140x140/industrial/text:${recordDto.totalJoinMatchesPlayerName} (${recordDto.totalJoinMatches})"
             alt="参赛最多的队员"/>

        <h2 class="text-success">参赛最多的队员</h2>

        <p class="text-muted">
            在球队参加的${recordDto.totalMatches}场比赛中, <strong>${recordDto.totalJoinMatchesPlayerName}</strong>
            参加了其中的${recordDto.totalJoinMatches}场比赛, 是参赛最多的队员的纪录保持者.
        </p>

        <p><a class="btn btn-default"
              href="record_player_join.zsfz?guid=${recordDto.totalJoinMatchesPlayerGuid}&name=${recordDto.totalJoinMatchesPlayerName}"
              role="button">查看他参加的比赛 &raquo;</a></p>
    </div>
    <!-- /.col-lg-4 -->
    <div class="col-lg-4">
        <img class="img-circle"
             data-src="${contextPath}/resources/js/holder.js/140x140/social/text:${recordDto.totalSingleMatchGoals}"
             alt="单场最多进球"/>

        <h2 class="text-success">单场最多进球</h2>

        <p class="text-muted">
            在 ${recordDto.totalSingleMatchDate} 与 (${recordDto.totalSingleMatchOpponent}) 的比赛中,
            球队一共攻入了${recordDto.totalSingleMatchGoals}粒进球, 是至今单场最多进球的纪录.
        </p>

        <p><a class="btn btn-default" href="match_details/${recordDto.totalSingleMatchGuid}.zsfz"
              role="button">查看该场比赛 &raquo;</a></p>
    </div>
    <!-- /.col-lg-4 -->
</div>
<!-- /.row -->

<div class="row record">
    <div class="col-lg-4">
        <img class="img-circle"
             data-src="${contextPath}/resources/js/holder.js/140x140/sky/text:${recordDto.longestWinMatches}"
             alt="最长连胜"/>

        <h2 class="text-success">最长连胜</h2>

        <p class="text-muted">
            从 ${recordDto.longestWinMatchesStart} 到 ${recordDto.longestWinMatchesEnd} 的${recordDto.longestWinMatches}场比赛中,
            我们取得了${recordDto.longestWinMatches}连胜, 是至今球队最长连胜纪录 &Atilde;.
        </p>

        <p><a class="btn btn-default"
              href="record_period_match.zsfz?start=${recordDto.longestWinMatchesStart}&end=${recordDto.longestWinMatchesEnd}&name=最长连胜"
              role="button">查看连胜的比赛 &raquo;</a></p>
    </div>
    <!-- /.col-lg-4 -->
    <div class="col-lg-4">
        <img class="img-circle"
             data-src="${contextPath}/resources/js/holder.js/140x140/grey/text:${recordDto.longestFailMatches}"
             alt="最长连败"/>

        <h2 class="text-success">最长连败</h2>

        <p class="text-muted">
            从 ${recordDto.longestFailMatchesStart} 到 ${recordDto.longestFailMatchesEnd} 的${recordDto.longestFailMatches}场比赛中,
            我们连败${recordDto.longestFailMatches}场, 是至今球队最长连败纪录 &prod;.
        </p>

        <p><a class="btn btn-default"
              href="record_period_match.zsfz?start=${recordDto.longestFailMatchesStart}&end=${recordDto.longestFailMatchesEnd}&name=最长连败"
              role="button">查看连败的比赛 &raquo;</a></p>
    </div>
    <!-- /.col-lg-4 -->
    <div class="col-lg-4">
        ...
    </div>
    <!-- /.col-lg-4 -->
</div>
<!-- /.row -->

<div class="row">
    <div class="col-md-12">
        <%--share --%>
            <div class="bdsharebuttonbox"><a href="#" class="bds_more" data-cmd="more"></a><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a><a href="#" class="bds_sqq" data-cmd="sqq" title="分享到QQ好友"></a><a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a></div>
            <script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"24"},"share":{},"image":{"viewList":["tsina","tqq","weixin","sqq","qzone"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["tsina","tqq","weixin","sqq","qzone"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>

    </div>
</div>

</body>
</html>