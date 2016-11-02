
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
    <title>比赛预告</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <!--// alert start -->
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="glyphicon glyphicon-ok"></i>
                <span id="persistSuccess" class="hide">保存比赛预告信息成功!</span>
                <span id="archiveMatchNoticeSuccess" class="hide">删除比赛预告信息成功!</span>
            </div>
        </div>
        <div class="alert alert-info hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="glyphicon glyphicon-info-sign"></i>
                <span id="archiveFinishInfo" class="hide">删除比赛预告信息完成.</span>
                <span id="publishMatchNoticeInfo" class="hide">发布比赛预告信息完成.</span>
                <span id="cancelMatchNoticeInfo" class="hide">取消发布比赛预告信息完成.</span>
                <span id="noticePreview" class="hidden"><strong>比赛预告:</strong> <span></span></span>
            </div>
        </div>
        <div class="alert alert-error hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-warning-sign icon-white"></i>

            </div>
        </div>
        <!--// alert end -->
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="panel panel-danger">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-md-8">
                        <form id="noticeForm" action="" class="form-inline" role="form">
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
                            <button class="btn btn-success"><i class="glyphicon glyphicon-search"></i> 查询
                            </button>
                        </form>
                    </div>
                    <div class="col-md-4">
                        <div class="pull-right">
                            <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_CAPTAIN,ROLE_COACH">
                                <a href="match_notice/form/create.zsfz" class="btn btn-primary"><span
                                        class="glyphicon glyphicon-plus"></span> 添加比赛预告</a>
                            </sec:authorize>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <div class="table-responsive">
                    <dis:table id="n" class="table table-striped table-hover" list="${listDto}"
                               form="noticeForm">
                        <dis:column title="比赛时间">
                            <div class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0)">
                                        ${n.time} <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="javascript:void(0);" preview="${n.previewText}"><i
                                            class="glyphicon glyphicon-bullhorn"></i>
                                        预览</a>
                                    </li>
                                    <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_CAPTAIN,ROLE_COACH">
                                        <c:if test="${n.pending}" var="pending">
                                            <li><a href="match_notice/cancel/${n.guid}.zsfz"
                                                   onclick="return confirm('确认取消发布该比赛预告信息?');"><i
                                                    class="glyphicon glyphicon-repeat"></i>
                                                取消发布</a>
                                            </li>
                                            <li><a href="match_notice/form/${n.guid}.zsfz"><i
                                                    class="glyphicon glyphicon-pencil"></i>
                                                修改</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${(not pending) and (not n.pastTime)}">
                                            <li><a href="match_notice/publish/${n.guid}.zsfz"
                                                   onclick="return confirm('确认发布该比赛预告信息?');"><i
                                                    class="glyphicon glyphicon-ok-sign"></i>
                                                发布</a>
                                            </li>
                                            <li class="divider"></li>
                                            <li><a href="match_notice/form/${n.guid}.zsfz"><i
                                                    class="glyphicon glyphicon-pencil"></i>
                                                修改</a>
                                            </li>
                                            <li><a href="match_notice/archive/${n.guid}.zsfz"
                                                   onclick="return confirm('确认删除该比赛预告信息 ?');"><i
                                                    class="glyphicon glyphicon-remove-circle"></i>
                                                删除</a>
                                            </li>
                                        </c:if>
                                    </sec:authorize>
                                </ul>
                            </div>
                        </dis:column>
                        <dis:column title="球场">
                            ${n.stadium.name} ${n.stadium.homeStadium?'(主)':''}
                        </dis:column>
                        <dis:column title="对手" property="opponent.name"/>
                        <dis:column title="状态">
                            <c:if test="${not n.pastTime and n.pending}">
                                <span class="label label-primary">已发布</span>
                            </c:if>
                            <c:if test="${n.pastTime and n.pending}">
                                <span class="label label-warning">已过期</span>
                            </c:if>
                            <c:if test="${n.pastTime and not n.pending}">
                                <span class="label label-default">已结束</span>
                            </c:if>
                            <c:if test="${not n.pastTime and not n.pending}">
                                <span class="label label-success">已创建</span>
                            </c:if>
                        </dis:column>
                        <dis:column title="备注">
                            <span title="${n.remark}">${n.shortRemark}</span>
                        </dis:column>
                    </dis:table>
                </div>
            </div>
            <div class="panel-footer">
                <div class="text-muted">
                    共 <strong>${listDto.totalSize}</strong> 条比赛预告信息.
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        new BackendMatchNotice('${param.alert}');
    });
</script>
</body>
</html>