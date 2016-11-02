
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dis" uri="http://displaytag.sf.net" %>
<!DOCTYPE html>
<html>
<head>
    <title>${dataDto.currentDto.seasonDto.name} - 数据</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <div class="panel-group" id="accordion">
            <c:if test="${empty dataDto.seasons}">
                <div class="text-warning">
                    暂无数据.
                </div>
            </c:if>
            <c:forEach items="${dataDto.seasons}" var="s" varStatus="status">
                <c:set var="currSeason" value="${s.guid eq dataDto.currentDto.seasonDto.guid}"/>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-toggle="collapse" data-parent="#accordion"
                               currSeason="${currSeason}" href="#collapse${s.guid}" seasonGuid="${s.guid}">
                                    ${s.name}
                                <c:if test="${s.pending}">
                                    <small>(数据截止 ${today})</small>
                                </c:if>
                            </a>
                        </h4>
                    </div>
                    <c:if test="${not currSeason}">
                        <div id="collapse${s.guid}" class="panel-collapse collapse">
                            <div style="margin: 10px;">
                                <img src="${contextPath}/resources/images/loading.gif" alt="loading"
                                     class="img-responsive" title="加载中..."/>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${currSeason}">
                        <div id="collapse${s.guid}" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-9">
                                        <div class="table-responsive">
                                            <table class="table table-striped table-hover">
                                                <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>比赛时间</th>
                                                    <th>对手</th>
                                                    <th>比分</th>
                                                    <th>进球(助攻)</th>
                                                        <%--<th>球场</th>--%>
                                                    <th>&nbsp;</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach items="${dataDto.currentDto.matches}" var="m" varStatus="vs">
                                                    <tr>
                                                        <td>${vs.count}</td>
                                                        <td>${m.matchTime}</td>
                                                        <td><a href="javascript:void(0);">${m.opponent.name}</a></td>
                                                        <td>
                                                            <c:if test="${m.homeScores > m.awayScores}">
                                                                <strong class="text-danger">${m.homeScores}:${m.awayScores}</strong>
                                                            </c:if>
                                                            <c:if test="${m.homeScores eq m.awayScores}">
                                                                <strong class="text-muted">${m.homeScores}:${m.awayScores}</strong>
                                                            </c:if>
                                                            <c:if test="${m.homeScores < m.awayScores}">
                                                                <strong class="text-warning">${m.homeScores}:${m.awayScores}</strong>
                                                            </c:if>
                                                        </td>
                                                        <td style="max-width:40%;">${m.goalPlayerNames}</td>
                                                            <%--<td>${m.stadium.name} ${m.stadium.homeStadium?'(主)':''}</td>--%>
                                                        <td>
                                                            <a href="match_details/${m.guid}.zsfz" title="比赛详细信息"><i
                                                                    class="glyphicon glyphicon-exclamation-sign"></i></a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <!-- Nav tabs -->
                                        <ul class="nav nav-tabs">
                                            <li class="active"><a href="#goals"
                                                                  data-toggle="tab"><strong>进球(${dataDto.currentDto.totalScores})</strong></a>
                                            </li>
                                            <li><a href="#assistant"
                                                   data-toggle="tab"><strong>助攻(${dataDto.currentDto.totalAssistants})</strong></a>
                                            </li>

                                        </ul>

                                        <!-- Tab panes -->
                                        <div class="tab-content">
                                            <div class="tab-pane active" id="goals">
                                                <table class="table table-striped" style="margin-top:3px;">
                                                    <c:forEach items="${dataDto.currentDto.goalSortDto.goals}" var="g"
                                                               varStatus="gs">
                                                        <tr>
                                                            <td style="width:15%;">${gs.count}.</td>
                                                            <td>
                                                                <a href="javascript:void(0)" data-toggle="popover"
                                                                   data-original-title="${g.playerName}"
                                                                   guid="${g.playerGuid}" class="playerPopover">${g.playerName}</a>
                                                            </td>
                                                            <td style="width:20%;"><span
                                                                    class="label ${gs.first?'label-danger':(gs.count eq 2)?'label-warning':(gs.count eq 3)?'label-info':'label-default'}">${g.goals}</span>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </table>
                                            </div>
                                            <div class="tab-pane" id="assistant">
                                                <table class="table table-striped" style="margin-top:3px;">
                                                    <c:forEach items="${dataDto.currentDto.assistantSortDto.assistants}"
                                                               var="g"
                                                               varStatus="sa">
                                                        <tr>
                                                            <td style="width:15%;">${sa.count}.</td>
                                                            <td>
                                                                <a href="javascript:void(0)" data-toggle="popover"
                                                                   data-original-title="${g.playerName}"
                                                                   guid="${g.playerGuid}" class="playerPopover">${g.playerName}</a>
                                                            </td>
                                                            <td style="width:20%;"><span
                                                                    class="label ${sa.first?'label-danger':(sa.count eq 2)?'label-warning':(sa.count eq 3)?'label-info':'label-default'}">${g.assistants}</span>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </table>
                                            </div>

                                        </div>

                                    </div>
                                </div>
                            </div>
                            <div class="panel-footer text-success">
                                <i class="glyphicon glyphicon-stats"></i> 积分:
                                <strong>${dataDto.currentDto.integration}</strong> |
                                胜 ${dataDto.currentDto.winCount},平 ${dataDto.currentDto.eqCount},负 ${dataDto.currentDto.failCount}
                                | 进球
                                <strong>${dataDto.currentDto.totalScores}</strong>,失球
                                    ${dataDto.currentDto.totalConceded},得失球 ${dataDto.currentDto.pureScores}
                                | 助攻 ${dataDto.currentDto.totalAssistants}.
                            </div>
                        </div>
                    </c:if>
                </div>
            </c:forEach>

        </div>
    </div>
</div>

<script src="${contextPath}/resources/js/jquery.scrollUp.min.js"></script>
<script>
    $(function () {
        new DataMain();
    })
</script>
</body>
</html>