
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
    <title>比赛纪录</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <div class="alert alert-success">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="glyphicon glyphicon-info-sign"></i>
                <span>我们在 <strong>${recordDto.stadiumDto.name}</strong> 踢了 ${recordDto.totalSize} 场比赛.
                        <c:if test="${recordDto.stadiumDto.homeStadium}">
                            <span class="glyphicon glyphicon-heart" title="我们的主场"></span>
                        </c:if>
                </span>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="table-responsive">
            <dis:table id="n" class="table table-striped table-hover" list="${recordDto}"
                       requestURI="">
                <dis:column title="比赛时间" property="matchTime"/>
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
        <div class="text-center">
            <a href="../stadium.zsfz" class="btn btn-success">&laquo; 返回</a>
        </div>
    </div>
</div>

</body>
</html>