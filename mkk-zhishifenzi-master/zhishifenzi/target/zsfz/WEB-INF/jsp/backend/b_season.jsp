
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dis" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>赛季</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/datepicker.css"/>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <!--// alert start -->
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="glyphicon glyphicon-ok"></i>
                <span id="persistSuccess" class="hide">保存赛季信息成功!</span>
            </div>
        </div>
        <div class="alert alert-info hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="glyphicon glyphicon-info-sign"></i>
                <span id="archiveFinishInfo" class="hide">删除赛季信息完成.</span>
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
    <div class="col-md-8">
        <div class="panel panel-info">
            <div class="panel-body">
                <div class="table-responsive">
                    <dis:table id="s" class="table table-striped table-hover" list="${listDto}"
                               requestURI="season.zsfz">
                        <dis:column title="名称">
                            <div class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0)">
                                        ${s.name} <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="?seasonGuid=${s.guid}"><i class="glyphicon glyphicon-pencil"></i>
                                        修改</a>
                                    </li>
                                    <li><a href="season/archive/${s.guid}.zsfz"
                                           onclick="return confirm('确认删除(将同时删除该赛季的所有比赛记录) ?');"><i
                                            class="glyphicon glyphicon-remove-circle"></i>
                                        删除</a>
                                    </li>
                                </ul>
                            </div>
                        </dis:column>
                        <dis:column title="开始日期 / 结束日期" property="startEnd"/>
                        <dis:column title="状态">
                            <c:if test="${s.finished}">
                                <span class="label label-default">已结束</span>
                            </c:if>
                            <c:if test="${s.pending}">
                                <span class="label label-primary">进行中</span>
                            </c:if>
                            <c:if test="${s.created}">
                                <span class="label label-warning">未开始</span>
                            </c:if>
                        </dis:column>
                        <dis:column title="备注">
                            <span title="${s.remark}">${s.shortRemark}</span>
                        </dis:column>
                    </dis:table>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="panel panel-info">
            <div class="panel-body">
                <fieldset>
                    <legend>${listDto.add?'添加':'修改'}赛季</legend>
                    <form:form action="${contextPath}/b/season/submit.zsfz" commandName="listDto.formDto" role="form">
                        <input type="hidden" name="guid" value="${listDto.seasonGuid}"/>

                        <div class="form-group">
                            <label for="name" class="control-label">赛季名称</label>
                            <form:input path="name" cssClass="form-control" id="name" placeholder="请输入赛季名称"
                                        required="required"/>
                            <form:errors path="name" cssClass="text-danger"/>
                            <p class="help-block">赛季名称建议为: 年+赛季, 如 '2012 赛季'.</p>
                        </div>
                        <div class="form-group">
                            <label for="start" class="control-label">开始日期</label>
                            <form:input path="start" cssClass="form-control" id="start" placeholder="请选择开始日期"
                                        required="required"/>
                            <form:errors path="start" cssClass="text-danger"/>
                            <p class="help-block">开始日期建议为每年的第一天, 格式: yyyy-MM-dd.</p>
                        </div>
                        <div class="form-group">
                            <label for="end" class="control-label">结束日期</label>
                            <form:input path="end" cssClass="form-control" id="end" placeholder="请选择结束日期"
                                        required="required"/>
                            <form:errors path="end" cssClass="text-danger"/>
                            <p class="help-block">结束日期必须在开始日期之后, 格式: yyyy-MM-dd.</p>
                        </div>
                        <div class="form-group">
                            <label for="remark" class="control-label">备注信息</label>
                            <form:textarea path="remark" id="remark" class="form-control" rows="3"
                                           placeholder="有备注信息,请添加在这儿"/>
                            <form:errors path="remark" cssClass="text-danger"/>
                            <p class="help-block">写点对这赛季的展望或总结等.</p>
                        </div>

                        <button type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i> 保存
                        </button>

                    </form:form>
                </fieldset>
            </div>
        </div>
    </div>

</div>

<script src="${contextPath}/resources/js/datepicker/bootstrap-datepicker.js"></script>
<script src="${contextPath}/resources/js/datepicker/bootstrap-datepicker.zh-CN.js"></script>

<script>
    $(function () {
        new BackendSeason('${param.alert}', ${listDto.add});
    });
</script>
</body>
</html>