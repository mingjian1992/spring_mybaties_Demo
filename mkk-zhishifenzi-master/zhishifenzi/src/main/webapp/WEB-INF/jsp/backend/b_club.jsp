
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
    <title>对手</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <!--// alert start -->
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="glyphicon glyphicon-ok"></i>
                <span id="persistSuccess" class="hide">保存对手信息成功!</span>
            </div>
        </div>
        <div class="alert alert-info hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="glyphicon glyphicon-info-sign"></i>
                <span id="archiveFinishInfo" class="hide">删除对手信息完成.</span>
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
        <div class="panel panel-warning">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-md-8">
                        <form id="clubForm" action="" class="form-inline" role="form">
                            <div class="form-group">
                                <label class="sr-only" for="name">对手名称</label>
                                <input id="name" type="text" name="name"
                                       value="${listDto.name}"
                                       title="对手名称" placeholder="对手名称" class="form-control"/>
                            </div>
                            <button class="btn btn-success"><i class="glyphicon glyphicon-search"></i> 查询
                            </button>
                        </form>
                    </div>
                    <div class="col-md-4">
                        <div class="pull-right">
                            <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_CAPTAIN,ROLE_COACH">
                                <a href="club/form/create.zsfz" class="btn btn-primary"><span
                                        class="glyphicon glyphicon-plus"></span> 添加对手</a>
                            </sec:authorize>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <div class="table-responsive">
                    <dis:table id="c" class="table table-striped table-hover" list="${listDto}"
                               form="clubForm">
                        <dis:column title="对手名称">
                            <div class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0)">
                                        ${c.name} <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="club/record.zsfz?guid=${c.guid}"><i
                                            class="glyphicon glyphicon-th-list"></i>
                                        交战纪录</a>
                                    </li>
                                    <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_CAPTAIN,ROLE_COACH">
                                        <li class="divider"></li>
                                        <li><a href="club/form/${c.guid}.zsfz"><i
                                                class="glyphicon glyphicon-pencil"></i>
                                            修改</a>
                                        </li>
                                        <li><a href="club/archive/${c.guid}.zsfz"
                                               onclick="return confirm('确认删除该对手 ?');"><i
                                                class="glyphicon glyphicon-remove-circle"></i>
                                            删除</a>
                                        </li>
                                    </sec:authorize>
                                </ul>
                            </div>
                        </dis:column>
                        <dis:column title="联系人" property="contact"/>
                        <dis:column title="备注">
                            <span title="${c.remark}">${c.shortRemark}</span>
                        </dis:column>
                    </dis:table>
                </div>
            </div>
            <div class="panel-footer">
                <div class="text-muted">
                    共 <strong>${listDto.totalSize}</strong> 个对手.
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        new BackendClub('${param.alert}');
    });
</script>
</body>
</html>