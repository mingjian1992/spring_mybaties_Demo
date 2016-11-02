
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
    <title>比赛信息</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/datepicker.css"/>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <!--// alert start -->
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="glyphicon glyphicon-ok"></i>
                <span id="persistSuccess" class="hide">保存比赛信息成功!</span>
            </div>
        </div>
        <div class="alert alert-info hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="glyphicon glyphicon-info-sign"></i>
                <span id="archiveMatchInfo" class="hide">删除比赛信息完成.</span>
            </div>
        </div>
        <!--// alert end -->
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-md-11">
                        <form id="matchForm" action="" class="form-inline" role="form">
                            <div class="form-group">
                                <label class="sr-only" for="seasonGuid">选择赛季</label>
                                <select id="seasonGuid" name="seasonGuid" class="form-control">
                                    <option value="">选择赛季</option>
                                    <c:forEach items="${listDto.seasons}" var="s">
                                        <option value="${s.guid}" ${s.guid eq listDto.seasonGuid?'selected':''}>
                                                ${s.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="result">所有比赛结果</label>
                                <select id="result" name="result" class="form-control">
                                    <option value="3" ${(listDto.result eq 3)?'selected':''}>所有比赛结果</option>
                                    <option value="1" ${(listDto.result eq 1)?'selected':''}>胜</option>
                                    <option value="0" ${(listDto.result eq 0)?'selected':''}>平</option>
                                    <option value="2" ${(listDto.result eq 2)?'selected':''}>负</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="stadiumGuid">选择球场</label>
                                <select id="stadiumGuid" name="stadiumGuid" class="form-control">
                                    <option value="">选择球场</option>
                                    <c:forEach items="${listDto.stadiums}" var="s">
                                        <option value="${s.guid}" ${s.guid eq listDto.stadiumGuid?'selected':''}>
                                                ${s.name} ${s.homeStadium?'(主)':''}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="opponentGuid">选择对手</label>
                                <select id="opponentGuid" name="opponentGuid" class="form-control">
                                    <option value="">选择对手</option>
                                    <c:forEach items="${listDto.clubs}" var="s">
                                        <option value="${s.guid}" ${s.guid eq listDto.opponentGuid?'selected':''}>
                                                ${s.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="matchDate">比赛日期</label>
                                <input type="text" name="matchDate" class="form-control" id="matchDate"
                                       value="${listDto.matchDate}" placeholder="比赛日期"/>
                            </div>
                            <%--<div class="form-group">--%>
                            <%--<label class="sr-only" for="remark">比赛备注</label>--%>
                            <%--<input type="text" name="remark" class="form-control" id="remark"--%>
                            <%--value="${listDto.remark}" placeholder="比赛备注"/>--%>
                            <%--</div>--%>

                            <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-search"></i> 查询
                            </button>
                        </form>
                    </div>
                    <div class="col-md-1">
                        <div class="pull-right">
                            <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_CAPTAIN,ROLE_COACH">
                                <a href="match/form/create.zsfz" class="btn btn-primary"><span
                                        class="glyphicon glyphicon-plus"></span> 添加比赛</a>
                            </sec:authorize>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <div class="table-responsive">
                    <dis:table id="n" class="table table-striped table-hover" list="${listDto}"
                               form="matchForm">
                        <dis:column title="比赛时间">
                            <div class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0)">
                                        ${n.matchTime} <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="match/details/${n.guid}.zsfz"><i
                                            class="glyphicon glyphicon-info-sign"></i>
                                        明细</a>
                                    </li>
                                    <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_CAPTAIN,ROLE_COACH">
                                        <li class="divider"></li>
                                        <li><a href="match/form/${n.guid}.zsfz"><i
                                                class="glyphicon glyphicon-pencil"></i>
                                            修改</a>
                                        </li>
                                        <li><a href="match/archive/${n.guid}.zsfz"
                                               onclick="return confirm('确认删除该比赛信息 ?');"><i
                                                class="glyphicon glyphicon-remove-circle"></i>
                                            删除</a>
                                        </li>
                                    </sec:authorize>
                                </ul>
                            </div>
                        </dis:column>
                        <dis:column title="对手" property="opponent.name"/>
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
                        <dis:column title="赛季" property="season.name"/>
                        <dis:column title="备注">
                            <i title="五人制足球赛"
                               class="text-danger ${n.futsal?'glyphicon glyphicon-star-empty':''}"></i> <span
                                title="${n.remark}">${n.shortRemark}</span>
                        </dis:column>
                    </dis:table>
                </div>
            </div>
            <div class="panel-footer">
                <div class="text-muted">
                    说明: 有 <i class="glyphicon glyphicon-star-empty"></i> 标记的为五人制足球赛. 共
                    <strong>${listDto.totalSize}</strong> 场比赛.
                </div>
            </div>
        </div>
    </div>
</div>


<script src="${contextPath}/resources/js/datepicker/bootstrap-datepicker.js"></script>
<script src="${contextPath}/resources/js/datepicker/bootstrap-datepicker.zh-CN.js"></script>

<script>
    $(function () {
        new BackendMatch('${param.alert}', '${today}');
    });
</script>
</body>
</html>