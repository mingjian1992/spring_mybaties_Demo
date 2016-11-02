
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="displaytag" uri="http://displaytag.sf.net" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>用户</title>
</head>
<body>

<div class="row">
    <div class="col-md-12">
        <!--// alert start -->
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="glyphicon glyphicon-ok"></i>
                <span id="archiveSuccess" class="hide">删除用户成功!</span>
                <span id="persistSuccess" class="hide">添加/更新用户信息成功!</span>
            </div>
        </div>
        <div class="alert alert-info hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="glyphicon glyphicon-info-sign"></i>
                <span id="enableAlertInfo" class="hide">启用用户完成!</span>
                <span id="disableAlertInfo" class="hide">禁用用户完成!</span>
            </div>
        </div>
        <div class="alert alert-error hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="icon-warning-sign icon-white"></i>
                <%--<span id="errorInfo" class="hide">Move task has been finished.!</span>--%>
            </div>
        </div>
        <!--// alert end -->
    </div>
</div>

<div class="row">

    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-md-10">
                        <form id="userForm" action="" class="form-inline" role="form">
                            <div class="form-group">
                                <label class="sr-only" for="username">用户名</label>
                                <input id="username" type="text" name="username"
                                       value="${userOverviewDto.username}"
                                       title="用户名" placeholder="用户名" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <select name="deptGuid" class="form-control">
                                    <option value="">选择部门</option>
                                    <c:forEach items="${userOverviewDto.departments}" var="dept">
                                        <option value="${dept.guid}" ${dept.guid eq userOverviewDto.deptGuid?'selected':'' }>${dept.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button class="btn btn-success btn-mini"><i class="glyphicon glyphicon-search"></i> 查询
                            </button>
                        </form>
                    </div>
                    <div class="col-md-2">
                        <a href="form/create.wdcy" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i>
                            添加用户</a>
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <div class="table-responsive">
                    <displaytag:table list="${userOverviewDto}" id="user" form="userForm"
                                      class="table table-bordered table-hover table-striped">
                        <displaytag:column title="登录名">
                            <div class="dropdown">
                                <a data-toggle="dropdown" href="javascript:void(0);">${user.username} <span
                                        class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                    <c:if test="${user.status.disabled}">
                                        <li><a tabindex="-1" href="change_status/${user.guid}/ENABLED.wdcy"
                                               onclick="return confirm('确认启用该用户吗?');"><i
                                                class="glyphicon glyphicon-ok-sign"></i> 启用</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${user.status.enabled}">
                                        <li><a tabindex="-1" href="reset_password/${user.guid}.wdcy"
                                               onclick="return confirm('确认重置该用户的密码?');"><i
                                                class="glyphicon glyphicon-repeat"></i> 重置密码</a></li>
                                        <li class="divider"></li>
                                        <li><a tabindex="-1" href="form/${user.guid}.wdcy"><i
                                                class="glyphicon glyphicon-pencil"></i>
                                            编辑</a></li>
                                        <c:if test="${not user.defaultUser}">
                                            <li><a tabindex="-1" href="archive/${user.guid}.wdcy"
                                                   onclick="return confirm('确认删除该用户吗?');"><i
                                                    class="glyphicon glyphicon-remove"></i> 删除</a>
                                            </li>
                                            <li class="divider"></li>
                                            <li><a tabindex="-1" href="change_status/${user.guid}/DISABLED.wdcy"
                                                   onclick="return confirm('确认禁用该用户吗?');"><i
                                                    class="glyphicon glyphicon-lock"></i> 禁用</a>
                                            </li>
                                        </c:if>
                                    </c:if>
                                    <li class="divider"></li>
                                    <li><a tabindex="-1" href="details/${user.guid}.wdcy"><i
                                            class="glyphicon glyphicon-eye-open"></i> 明细</a>
                                    </li>
                                </ul>
                            </div>
                        </displaytag:column>
                        <displaytag:column property="fullName" title="姓名"
                                           class="${user.status.disabled?'text-muted':''}"/>
                        <displaytag:column title="部门" class="${user.status.disabled?'text-muted':''}">
                            <span title="${user.department.description}">${user.department.name}</span>
                        </displaytag:column>
                        <displaytag:column property="userRole.label" title="用户类型"
                                           class="${user.status.disabled?'text-muted':''}"/>
                        <displaytag:column property="status.label" title="状态"
                                           class="${user.status.disabled?'text-muted':''}"/>
                        <displaytag:column property="phone" title="电话" class="${user.status.disabled?'text-muted':''}"/>
                        <displaytag:column title="邮件地址" class="${user.status.disabled?'text-muted':''}">
                            <a href="mailto:${user.email}">${user.email}</a>
                        </displaytag:column>
                        <displaytag:column property="lastLoginTime" title="上次登录时间"
                                           class="${user.status.disabled?'text-muted':''}"/>
                    </displaytag:table>
                    <!--// table end -->
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        new UserOverview('${param.alert}');
    });
</script>
</body>
</html>