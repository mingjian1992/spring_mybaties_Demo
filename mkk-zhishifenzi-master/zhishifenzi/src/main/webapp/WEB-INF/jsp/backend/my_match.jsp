
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
    <title>我的比赛</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <ul class="nav nav-tabs">
            <li><a href="index.zsfz"><i class="glyphicon glyphicon-home"></i> 我的信息</a></li>
            <li><a href="my_data.zsfz"><i class="glyphicon glyphicon-stats"></i> 我的数据</a></li>
            <li class="active"><a href="#mymatch"><i class="glyphicon glyphicon-tasks"></i> 我的比赛</a></li>
        </ul>
        <div id="mymatch" style="margin-top: 5px;">
            <div class="well well-sm">
                <div class="alert alert-success">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <i class="glyphicon glyphicon-info-sign"></i>
                    以下显示我参与球队的所有比赛记录, 一共 ${matchesDto.totalSize} 场.
                </div>
                <form class="form-inline" role="form" action="" id="mForm">

                    <div class="form-group">
                        <label class="sr-only" for="seasonGuid">选择赛季</label>
                        <select id="seasonGuid" name="seasonGuid" class="form-control">
                            <option value="">所有赛季</option>
                            <c:forEach items="${matchesDto.seasons}" var="s">
                                <option value="${s.guid}" ${s.guid eq matchesDto.seasonGuid?'selected':''}>
                                        ${s.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="result">所有比赛结果</label>
                        <select id="result" name="result" class="form-control">
                            <option value="3" ${(matchesDto.result eq 3)?'selected':''}>所有比赛结果</option>
                            <option value="1" ${(matchesDto.result eq 1)?'selected':''}>胜</option>
                            <option value="0" ${(matchesDto.result eq 0)?'selected':''}>平</option>
                            <option value="2" ${(matchesDto.result eq 2)?'selected':''}>负</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="checkbox-inline">
                            <input type="checkbox" name="hasGoal" value="true" ${matchesDto.hasGoal?'checked':''}/> 有我进球
                        </label>
                    </div>
                    <div class="form-group">
                        <label class="checkbox-inline">
                            <input type="checkbox" name="hasAssist" value="true" ${matchesDto.hasAssist?'checked':''}/>
                            有我助攻
                        </label>
                    </div>
                    &nbsp;
                    <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-search"></i>
                        搜索
                    </button>
                </form>
                <hr/>
                <div class="table-responsive">
                    <dis:table id="n" class="table table-striped table-hover" list="${matchesDto}"
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
                            <a href="match/details/${n.guid}.zsfz" title="比赛详细信息"><i
                                    class="glyphicon glyphicon-exclamation-sign"></i> </a>
                        </dis:column>
                    </dis:table>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>