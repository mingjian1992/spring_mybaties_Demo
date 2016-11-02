
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>球员</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <!--// alert start -->
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="glyphicon glyphicon-ok"></i>
                <span id="persistSuccess" class="hide">保存球员信息成功!</span>
            </div>
        </div>
        <div class="alert alert-info hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="glyphicon glyphicon-info-sign"></i>
                <span id="archiveFinishInfo" class="hide">删除球员信息完成.</span>
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
        <div class="panel panel-primary">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-md-8">
                        <form id="playerForm" action="" class="form-inline" role="form">
                            <div class="form-group">
                                <label class="sr-only" for="name">球员名字</label>
                                <input id="name" type="text" name="name"
                                       value="${listDto.name}"
                                       title="球员名字" placeholder="球员名字" class="form-control"/>
                            </div>
                            <button class="btn btn-success"><i class="glyphicon glyphicon-search"></i> 查询
                            </button>
                        </form>
                    </div>
                    <div class="col-md-4">
                        <div class="pull-right">
                            <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_CAPTAIN,ROLE_COACH">
                                <a href="player/form/create.zsfz" class="btn btn-default"><span
                                        class="glyphicon glyphicon-plus"></span> 添加球员</a>
                            </sec:authorize>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <c:if test="${empty listDto.groupResults}">
                    <div class="text-muted">
                        尚未添加任何球员, 点击"添加球员"按钮添加.
                    </div>
                </c:if>
                <c:forEach items="${listDto.groupResults}" var="g">
                    <div class="panel panel-default">
                        <div class="panel-heading"><i class="glyphicon glyphicon-star-empty"></i> ${g.key}
                            (${g.resultsSize}人)
                        </div>

                        <!-- List group -->
                        <ul class="list-group">
                            <c:forEach items="${g.results}" var="p">
                                <li class="list-group-item">
                                    <div class="row">
                                        <div class="col-md-2">
                                            <div class="dropdown">
                                                <a class="dropdown-toggle" data-toggle="dropdown"
                                                   href="javascript:void(0)">
                                                    <i class="glyphicon ${p.captain?'glyphicon-flag':'glyphicon-user'}"></i> ${p.fullName}
                                                    <span class="caret"></span>
                                                </a>
                                                <ul class="dropdown-menu">
                                                    <li><a href="player/data_${p.guid}.zsfz"><i
                                                            class="glyphicon glyphicon-stats"></i>
                                                        数据...</a>
                                                    </li>
                                                    <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_CAPTAIN">
                                                        <li><a href="player/account/${p.guid}.zsfz"><i
                                                                class="glyphicon glyphicon-lock"></i>
                                                            账号</a>
                                                        </li>
                                                    </sec:authorize>
                                                    <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_CAPTAIN,ROLE_COACH">
                                                        <li class="divider"></li>
                                                        <li><a href="player/form/${p.guid}.zsfz"><i
                                                                class="glyphicon glyphicon-pencil"></i>
                                                            修改</a>
                                                        </li>
                                                        <li><a href="player/archive/${p.guid}.zsfz"
                                                               onclick="return confirm('确认删除该球员(将同时删除对应的登录账号) ?');"><i
                                                                class="glyphicon glyphicon-remove-circle"></i>
                                                            删除</a>
                                                        </li>
                                                    </sec:authorize>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="col-md-2 text-primary">
                                            <strong>${p.number}</strong>&nbsp;&nbsp;${p.position.label}</div>
                                        <div class="col-md-2" title="加入球队日期">${p.entryDate}</div>
                                        <div class="col-md-2" title="联系电话">${p.phone}</div>
                                        <div class="col-md-2" title="邮件地址">${p.email}</div>
                                        <div class="col-md-2">
                                            <span title="生日">${p.birthday}</span>
                                            <a class="text-muted pull-right playerPopover"
                                               data-toggle="popover" data-original-title="${p.fullName}"
                                               guid="${p.guid}" href="javascript:void(0)"><i
                                                    class="glyphicon glyphicon-exclamation-sign"></i></a>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:forEach>
            </div>
            <div class="panel-footer text-muted">
                <i class="glyphicon glyphicon-star"></i> 共 <strong>${listDto.totalSize}</strong> 名球员.
            </div>
        </div>

    </div>
</div>

<script src="${contextPath}/resources/js/jquery.scrollUp.min.js"></script>
<script>
    $(function () {
        new BackendPlayer('${param.alert}');
    });
</script>
</body>
</html>