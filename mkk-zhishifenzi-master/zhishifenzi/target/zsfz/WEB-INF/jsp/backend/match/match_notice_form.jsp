
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>比赛预告</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/bootstrap-datetimepicker.min.css"/>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <div class="panel panel-danger">
            <div class="panel-body">
                <form:form commandName="formDto" role="form">
                    <div class="form-group">
                        <label for="time" class="control-label">比赛时间</label>
                        <form:input path="time" cssClass="form-control" id="time" placeholder="请选择比赛时间"
                                    required="required"/>
                        <form:errors path="time" cssClass="text-danger"/>
                        <p class="help-block">比赛时间必须在现在之后.</p>
                    </div>
                    <div class="form-group">
                        <label for="stadiumGuid" class="control-label">比赛球场</label>
                        <form:select path="stadium.guid" id="stadiumGuid" cssClass="form-control">
                            <form:option value="" label="选择比赛球场"/>
                            <form:options items="${formDto.stadiums}" itemLabel="nameIncludeHome" itemValue="guid"/>
                        </form:select>
                        <form:errors path="stadium.guid" cssClass="text-danger"/>
                        <p class="help-block">选择比赛在哪球场踢, 若没有对应的球场则点击'球场'菜单去添加.</p>
                    </div>
                    <div class="form-group">
                        <label for="opponentGuid" class="control-label">比赛对手</label>
                        <form:select path="opponent.guid" id="opponentGuid" cssClass="form-control">
                            <form:option value="" label="选择比赛对手"/>
                            <form:options items="${formDto.clubs}" itemLabel="name" itemValue="guid"/>
                        </form:select>
                        <form:errors path="opponent.guid" cssClass="text-danger"/>
                        <p class="help-block">选择比赛和谁踢, 若没有对应的对手则点击'对手'菜单去添加.</p>
                    </div>
                    <div class="form-group">
                        <label for="remark" class="control-label">备注信息</label>
                        <form:textarea path="remark" id="remark" class="form-control" rows="3"
                                       placeholder="关于该比赛预告的其他信息, 在这儿输入." required="true"/>
                        <form:errors path="remark" cssClass="text-danger"/>
                        <p class="help-block">比赛预告的其他信息.</p>
                    </div>

                    <button type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i> 保存
                    </button>
                    &nbsp;<a href="../../match_notice.zsfz">取消</a>

                </form:form>
            </div>
        </div>
    </div>
</div>

<script src="${contextPath}/resources/js/datepicker/bootstrap-datetimepicker.min.js"></script>
<script src="${contextPath}/resources/js/datepicker/bootstrap-datetimepicker.zh-CN.js"></script>

<script>
    $(function () {
        new MatchNoticeForm('${startTime}');
    });
</script>
</body>
</html>