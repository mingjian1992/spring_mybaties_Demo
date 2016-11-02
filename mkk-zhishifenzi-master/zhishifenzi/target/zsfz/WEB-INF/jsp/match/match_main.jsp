
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
    <title>比赛</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/datepicker.css"/>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <c:forEach items="${mainDto.notices}" var="n">
            <div class="alert alert-info">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <i class="glyphicon glyphicon-info-sign"></i> <strong>比赛预告:</strong> ${n.previewText}
            </div>
        </c:forEach>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="panel panel-success">
            <div class="panel-heading">
                <form class="form-inline" role="form" action="" id="mForm">

                    <div class="form-group">
                        <label class="sr-only" for="seasonGuid">选择赛季</label>
                        <select id="seasonGuid" name="seasonGuid" class="form-control">
                            <option value="">所有赛季</option>
                            <c:forEach items="${mainDto.seasons}" var="s">
                                <option value="${s.guid}" ${s.guid eq mainDto.seasonGuid?'selected':''}>
                                        ${s.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="result">所有比赛结果</label>
                        <select id="result" name="result" class="form-control">
                            <option value="3" ${(mainDto.result eq 3)?'selected':''}>所有比赛结果</option>
                            <option value="1" ${(mainDto.result eq 1)?'selected':''}>胜</option>
                            <option value="0" ${(mainDto.result eq 0)?'selected':''}>平</option>
                            <option value="2" ${(mainDto.result eq 2)?'selected':''}>负</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="stadiumGuid">选择球场</label>
                        <select id="stadiumGuid" name="stadiumGuid" class="form-control">
                            <option value="">所有球场</option>
                            <c:forEach items="${mainDto.stadiums}" var="s">
                                <option value="${s.guid}" ${s.guid eq mainDto.stadiumGuid?'selected':''}>
                                        ${s.name} ${s.homeStadium?'(主)':''}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="opponentGuid">选择对手</label>
                        <select id="opponentGuid" name="opponentGuid" class="form-control">
                            <option value="">所有对手</option>
                            <c:forEach items="${mainDto.clubs}" var="s">
                                <option value="${s.guid}" ${s.guid eq mainDto.opponentGuid?'selected':''}>
                                        ${s.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="matchDate">比赛日期</label>
                        <input type="text" name="matchDate" class="form-control" id="matchDate"
                               value="${mainDto.matchDate}" placeholder="比赛日期"/>
                    </div>
                    <%--<div class="form-group">--%>
                    <%--<label class="sr-only" for="remark">比赛备注</label>--%>
                    <%--<input type="text" name="remark" class="form-control" id="remark"--%>
                    <%--value="${mainDto.remark}" placeholder="比赛备注"/>--%>
                    <%--</div>--%>

                    <button type="submit" class="btn btn-primary" title="搜索"><i class="glyphicon glyphicon-search"></i>
                        搜索
                    </button>
                </form>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-9">
                        <div class="table-responsive">
                            <dis:table id="n" class="table table-striped table-hover" list="${mainDto}"
                                       form="mForm">
                                <dis:column title="比赛时间" property="matchTime"/>
                                <dis:column title="对手">
                                    <a href="javascript:void(0);">${n.opponent.name}</a>
                                </dis:column>
                                <dis:column title="比分">
                                    <c:if test="${n.homeScores > n.awayScores}">
                                        <strong class="text-danger">${n.homeScores}:${n.awayScores}</strong>
                                    </c:if>
                                    <c:if test="${n.homeScores eq n.awayScores}">
                                        <strong class="text-muted">${n.homeScores}:${n.awayScores}</strong>
                                    </c:if>
                                    <c:if test="${n.homeScores < n.awayScores}">
                                        <strong class="text-warning">${n.homeScores}:${n.awayScores}</strong>
                                    </c:if>
                                </dis:column>
                                <dis:column title="进球(助攻)" property="goalPlayerNames" style="width:40%;"/>
                                <dis:column title="球场">
                                    ${n.stadium.name} ${n.stadium.homeStadium?'(主)':''}
                                </dis:column>
                                <dis:column title="&nbsp;">
                                    <a href="match_details/${n.guid}.zsfz" title="比赛详细信息"><i
                                            class="glyphicon glyphicon-exclamation-sign"></i> </a>
                                </dis:column>
                            </dis:table>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="panel panel-danger">
                            <div class="panel-heading">
                                <i class="glyphicon glyphicon-stats"></i> 统计信息
                            </div>
                            <ul class="list-group">
                                <li class="list-group-item text-info">
                                    <span class="badge">${mainDto.totalSize}</span>
                                    场次(总)
                                </li>
                                <li class="list-group-item text-success">
                                    <span class="badge">${mainDto.totalScores}</span>
                                    进球
                                </li>
                                <li class="list-group-item text-warning">
                                    <span class="badge">${mainDto.totalConceded}</span>
                                    失球
                                </li>
                                <li class="list-group-item text-muted">
                                    <span class="badge">${mainDto.totalAssistants}</span>
                                    助攻
                                </li>
                                <li class="list-group-item text-muted">
                                    <span class="badge">${mainDto.totalOwnGoal}</span>
                                    OG
                                </li>
                                <li class="list-group-item text-info">
                                    <span class="badge">${mainDto.winCount}/${mainDto.eqCount}/${mainDto.failCount}</span>
                                    胜/平/负
                                </li>
                            </ul>
                        </div>

                    </div>
                </div>
            </div>
            <div class="panel-footer text-danger">
                <i class="glyphicon glyphicon-flag"></i> <em>真正的比赛是在下一场...</em>
            </div>
        </div>
    </div>
</div>

<script src="${contextPath}/resources/js/datepicker/bootstrap-datepicker.js"></script>
<script src="${contextPath}/resources/js/datepicker/bootstrap-datepicker.zh-CN.js"></script>

<script>
    $(function () {
        $('#matchDate').datepicker({
            format:'yyyy-mm-dd',
            autoclose:true,
            language:"zh-CN",
            endDate:'${today}',
            todayHighlight:true,
            weekStart:1
        });
    });
</script>

</body>
</html>