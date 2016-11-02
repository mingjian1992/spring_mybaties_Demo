
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>
    <link rel="shortcut icon" type="image/x-icon" href="${contextPath}/resources/images/favicon.ico"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <title>知识分子足球队|业余足球|知识分子|成都业余足球队</title>

    <meta name="keywords" content="足球,踢足球,足球队,知识分子足球队,我们的足球,业余足球,成都业余足球队"/>
    <meta name="description" content="我们是知识分子,争先恐后接球,知识分子足球队,我们在一起欢乐的足球时光"/>
    <meta name="author" content="李胜钊,monkeyk"/>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.min.css"/>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <style>
        body {
            font-family: "微软雅黑", Helvetica, Verdana, Arial, sans-serif;
            /*padding-top: 10px;*/
            padding-bottom: 10px;
        }

        .navbar-wrapper {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            z-index: 20;
            margin-top: 10px;
        }

        .carousel-img {
            max-height: 500px;
            max-width: 100%
        }

    </style>
</head>
<body>
<!--navbar -->
<div class="navbar-wrapper">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <nav class="navbar navbar-inverse" role="navigation">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse"
                                data-target="#navbar-collapse-01">
                            <span class="sr-only">Toggle navigation</span>
                        </button>
                        <a class="navbar-brand" href="${contextPath}/index.zsfz">知识分子</a>
                    </div>
                    <div class="collapse navbar-collapse" id="navbar-collapse-01">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="javascript:void(0);">首页</a></li>
                            <li><a href="${contextPath}/match.zsfz">比赛</a></li>
                            <li><a href="${contextPath}/data.zsfz">数据</a></li>
                            <li><a href="${contextPath}/album.zsfz">相册</a></li>
                            <li><a href="${contextPath}/record.zsfz">纪录</a></li>
                            <li><a href="${contextPath}/about.zsfz">关于</a></li>
                        </ul>
                        <!--right navbar-->
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="${contextPath}/go_login.zsfz"><i
                                    class="glyphicon glyphicon-user"></i> 登录</a></li>
                        </ul>
                    </div>
                    <!-- /.navbar-collapse -->
                </nav>
                <!-- /navbar -->
            </div>
        </div>
    </div>
</div>

<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        <li data-target="#carousel-example-generic" data-slide-to="3"></li>
        <!-- <li data-target="#carousel-example-generic" data-slide-to="4"></li> -->
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
        <c:if test="${not indexDto.useDefaultFrontPhotos}">
            <c:forEach items="${indexDto.headPhotos}" var="p" varStatus="status">
                <div class="item ${status.first?'active':''}">
                    <img src="${p.url}" alt="not found" class="img-responsive center-block carousel-img"/>

                    <div class="carousel-caption">
                        <p class="text-muted">${p.description}</p>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${indexDto.useDefaultFrontPhotos}">
            <div class="item active">
                <img src="${contextPath}/resources/images/bb.jpg" alt="index.jpg"
                     class="img-responsive center-block carousel-img"/>

                <div class="carousel-caption">
                    <p class="text-muted">
                        其实, 我们是一群穿着橙色球衣的人, 在那些时候.
                    </p>
                </div>
            </div>
            <div class="item">
                <img src="${contextPath}/resources/images/cc.jpg"
                     alt="about.jpg" class="img-responsive center-block carousel-img"/>

                <div class="carousel-caption">
                    <p class="text-muted">
                        我们在默默哀悼, 祝福一切平安.
                    </p>
                </div>
            </div>
            <div class="item">
                <img src="${contextPath}/resources/images/aa.jpg"
                     alt="about.jpg" class="img-responsive center-block carousel-img"/>

                <div class="carousel-caption">
                    <p class="text-muted">
                        一起欢笑着的时光, 短暂且美好.
                    </p>
                </div>
            </div>
            <div class="item">
                <img src="${contextPath}/resources/images/ee.jpg"
                     alt="about.jpg" class="img-responsive center-block carousel-img"/>

                <div class="carousel-caption">
                    <p class="text-muted">
                        这二人, 这背景, 这场地...
                    </p>
                </div>
            </div>
        </c:if>

    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left"></span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right"></span>
    </a>
</div>

<br/>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <c:forEach items="${indexDto.notices}" var="n">
                <div class="alert alert-info">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <i class="glyphicon glyphicon-info-sign"></i> <strong>比赛预告:</strong> ${n.previewText}
                </div>
            </c:forEach>
        </div>
    </div>

    <div class="row">
        <div class="col-md-8">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <div class="input-group">
                        <input type="text" class="form-control" id="match" name="match" placeholder="输入关键字搜索比赛">
                     <span class="input-group-btn">
                          <button class="btn btn-default" type="button"><i class="glyphicon glyphicon-search"></i>
                          </button>
                    </span>
                    </div>
                </div>
                <div class="panel-body text-danger">
                    <i class="glyphicon glyphicon-flag"></i> <em>真正的比赛是在下一场...</em>
                </div>
                <!-- List group -->
                <div class="list-group">
                    <c:forEach items="${indexDto.matches}" var="m" varStatus="s">
                        <a href="match_details/${m.guid}.zsfz" class="list-group-item ${s.first?'active':''}">
                            <h1 class="glyphicon glyphicon-time pull-left text-success"></h1>

                            <h3 class="list-group-item-heading">
                                <c:if test="${m.homeScores >m.awayScores}">
                                    <strong class="text-danger">${m.homeScores}:${m.awayScores}</strong>
                                </c:if>
                                <c:if test="${m.homeScores eq m.awayScores}">
                                    <strong class="text-muted">${m.homeScores}:${m.awayScores}</strong>
                                </c:if>
                                <c:if test="${m.homeScores < m.awayScores}">
                                    <strong class="text-warning">${m.homeScores}:${m.awayScores}</strong>
                                </c:if>
                                vs
                                <small class="text-info">${m.opponent.name}</small>
                            </h3>

                            <p class="list-group-item-text text-muted">
                                &nbsp;${m.matchTime}
                                <br/>
                                    ${m.goalPlayerNames}
                            </p>
                        </a>
                    </c:forEach>
                </div>
                <div class="panel-footer">
                    <a href="${contextPath}/match.zsfz"><i class="glyphicon glyphicon-share-alt"></i> 更多比赛...</a>
                </div>
            </div>
            <div class="alert alert-danger">
                <strong><i class="glyphicon glyphicon-hand-right"></i> 约战我们球队, 请使用QQ(79247523)联系郑先生.</strong>
            </div>
        </div>
        <div class="col-md-4">
            <div class="well well-sm">
                <div class="panel panel-info">
                    <div class="panel-body">

                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#goals" data-toggle="tab"><strong>进球</strong></a></li>
                            <li><a href="#assistant" data-toggle="tab"><strong>助攻</strong></a></li>

                        </ul>

                        <!-- Tab panes -->
                        <div class="tab-content">
                            <div class="tab-pane active" id="goals">

                                <table class="table table-striped" style="margin-top:3px;">
                                    <c:forEach items="${indexDto.goalSortDto.goals}" var="g" varStatus="s">
                                        <tr>
                                            <td style="width:15%;">${s.count}.</td>
                                            <td>
                                                <a href="javascript:void(0)" data-toggle="popover"
                                                   data-original-title="${g.playerName}"
                                                   guid="${g.playerGuid}" class="playerPopover">${g.playerName}</a>
                                            </td>
                                            <td style="width:20%;"><span
                                                    class="label ${s.first?'label-danger':(s.count eq 2)?'label-warning':(s.count eq 3)?'label-info':'label-default'}">${g.goals}</span>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                            <div class="tab-pane" id="assistant">
                                <table class="table table-striped" style="margin-top:3px;">
                                    <c:forEach items="${indexDto.assistantSortDto.assistants}" var="g" varStatus="s">
                                        <tr>
                                            <td style="width:15%;">${s.count}.</td>
                                            <td>
                                                <a href="javascript:void(0)" data-toggle="popover"
                                                   data-original-title="${g.playerName}"
                                                   guid="${g.playerGuid}" class="playerPopover">${g.playerName}</a>
                                            </td>
                                            <td style="width:20%;"><span
                                                    class="label ${s.first?'label-danger':(s.count eq 2)?'label-warning':(s.count eq 3)?'label-info':'label-default'}">${g.assistants}</span>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>

                            </div>

                        </div>
                    </div>
                    <div class="panel-footer text-muted">
                        <i class="glyphicon glyphicon-info-sign"></i> 以上数据是所有进球与助攻排名.
                    </div>
                </div>
                <%--share --%>
                <div class="bdsharebuttonbox"><a href="#" class="bds_more" data-cmd="more"></a><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a><a href="#" class="bds_sqq" data-cmd="sqq" title="分享到QQ好友"></a><a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a></div>
                <script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"24"},"share":{},"image":{"viewList":["tsina","tqq","weixin","sqq","qzone"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["tsina","tqq","weixin","sqq","qzone"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>

            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <hr/>
            <p class="text-center text-muted">
                &copy; 2008-2015 知识分子足球队 All rights reserved.
            </p>
        </div>
    </div>
</div>
<!-- /.container -->


<script src="${contextPath}/resources/js/jquery-1.9.1.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script>
    $(function () {
        $("a.playerPopover").popover({
            html:true,
            trigger:"hover",
            placement:"auto left",
            delay:{ show:500, hide:100 },
            content:function () {
                var content = "暂无";
                var playerGuid = $(this).attr("guid");
                $.ajax({
                    url:"player_info/" + playerGuid + ".zsfz",
                    async:false,
                    success:function (data) {
                        content = data;
                    }
                });
                return content;
            }
        });
    });
</script>

<jsp:include page="decorators/analytics.jsp"/>

</body>
</html>