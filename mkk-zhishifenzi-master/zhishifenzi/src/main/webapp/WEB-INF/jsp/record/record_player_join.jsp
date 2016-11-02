
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
    <title>${listDto.name}的参赛记录</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-warning">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-md-8">
                        <strong>${listDto.name}的参赛记录</strong>

                        <form class="form-inline" role="form" action="" id="mForm">
                            <input type="hidden" name="guid" value="${listDto.guid}"/>
                            <input type="hidden" name="name" value="${listDto.name}"/>
                        </form>
                    </div>
                    <div class="col-md-4">
                        <div class="pull-right">
                            <a href="record.zsfz">&laquo; 返回</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <div class="table-responsive">
                    <c:set var="totalScores" value="0"/>
                    <dis:table id="n" class="table table-striped table-hover" list="${listDto}"
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
        </div>
    </div>
</div>
</body>
</html>