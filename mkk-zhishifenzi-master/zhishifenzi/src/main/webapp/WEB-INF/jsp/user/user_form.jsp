
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>用户</title>

    <link href="${contextPath}/resources/css/datepicker.css" rel="stylesheet"/>

</head>
<body>
<div class="row">
    <div class="col-md-12">
        <form:form commandName="userFormDto" id="userForm">
            <div class="panel panel-default">
                <div class="panel-body">
                        <%--<form:hidden path="guid"/>--%>
                    <form:hidden path="existUsername"/>

                    <div class="form-group">
                        <label for="fullName">姓名</label><form:input path="fullName" id="fullName"
                                                                    placeholder="请输入用户姓名" size="30"
                                                                    cssClass="form-control"
                                                                    maxlength="255" required="true"/>
                        <form:errors path="fullName" cssClass="text-danger"/>
                        <p class="help-block">建议输入用户的中文名称</p>
                    </div>
                    <div class="form-group">
                        <label for="username">登录名</label><form:input path="username" id="username"
                                                                     placeholder="请输入登录名" size="30"
                                                                     cssClass="form-control"
                                                                     maxlength="255" required="true"/>
                        <form:errors path="username" cssClass="text-danger"/>
                        <p class="help-block">登录名必须唯一,建议用姓名的拼音字母</p>
                    </div>
                    <div class="form-group">
                        <label for="email">邮件地址</label><form:input path="email" id="email"
                                                                   placeholder="请输入邮件地址" size="30"
                                                                   cssClass="form-control"
                                                                   maxlength="255" required="true"/>
                        <form:errors path="email" cssClass="text-danger"/>
                        <p class="help-block">公司邮件地址默认为 xxx@wdcy.cc</p>
                    </div>
                    <div class="form-group">
                        <label for="phone">联系电话</label><form:input path="phone" id="phone"
                                                                   placeholder="请输入联系电话" size="30"
                                                                   cssClass="form-control"
                                                                   maxlength="255"/>
                        <form:errors path="phone" cssClass="text-danger"/>
                    </div>
                    <div class="form-group">
                        <label for="entryDate">入职日期</label><form:input path="entryDate" id="entryDate"
                                                                       placeholder="请选择入职日期" size="30"
                                                                       cssClass="form-control"
                                                                       maxlength="255"/>
                        <form:errors path="entryDate" cssClass="text-danger"/>
                        <p class="help-block">入职日期格式: yyyy-MM-dd</p>
                    </div>
                    <div class="form-group">
                        <label for="birthday">生日日期</label><form:input path="birthday" id="birthday"
                                                                      placeholder="请选择生日日期" size="30"
                                                                      cssClass="form-control"
                                                                      maxlength="255"/>
                        <form:errors path="birthday" cssClass="text-danger"/>
                        <p class="help-block">生日日期格式: yyyy-MM-dd</p>
                    </div>
                    <div class="form-group">
                        <label for="userRole">用户类型</label>
                        <form:select path="userRole" id="userRole" items="${userFormDto.userRoles}"
                                     itemLabel="label" cssClass="form-control"
                                     itemValue="value" disabled="${not userFormDto.newly}"/>
                        <form:errors path="userRole" cssClass="text-danger"/>
                        <p class="help-block">不同的用户类型对应不同的权限.</p>

                            <%--If userRole is disabled, add a hidden field for validation--%>
                        <c:if test="${not userFormDto.newly}">
                            <form:hidden path="userRole"/>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="department">所属部门&nbsp;
                            <a href="#" class="btn" title="点击添加部门" data-toggle="modal"
                               data-target="#deptModal"><i class="glyphicon glyphicon-plus-sign"></i></a>
                        </label>
                        <form:select path="department.guid" id="department" cssClass="form-control">
                            <form:option value="" label="选择部门"/>
                            <form:options items="${userFormDto.departments}" itemLabel="name" itemValue="guid"/>
                        </form:select>
                        <form:errors path="department.guid" cssClass="text-danger"/>
                        <p class="help-block">选择用户所属的部门; 如果用户类型为'部门负责人', 则必须选择一个部门.</p>

                            <%--For bind if validation failed--%>
                        <c:forEach items="${userFormDto.departments}" var="d" varStatus="s">
                            <input type="hidden" name="departments[${s.index}].guid" value="${d.guid}"/>
                            <input type="hidden" name="departments[${s.index}].name" value="${d.name}"/>
                        </c:forEach>

                    </div>
                    <c:if test="${userFormDto.newly}">
                        <div class="form-group">
                            <label for="password">用户密码&nbsp;
                                <a href="javascript:void(0);" id="defaultPasswordLink" title="使用默认密码"><i
                                        class="glyphicon glyphicon-asterisk"></i></a>
                            </label>
                            <input type="hidden" id="defaultPassword" value="${defaultPassword}"/>
                            <form:password path="password" id="password" placeholder="用户密码, 长度至少6位" title="用户密码"
                                           maxlength="255" required="true"
                                           size="50" cssClass="form-control"/>
                            <form:errors path="password" cssClass="text-danger"/>
                            <p class="help-block">用户密码, 长度至少6位.</p>
                        </div>
                        <div class="form-group">
                            <label for="rePassword">确认密码</label>
                            <form:password path="rePassword" id="rePassword" placeholder="确认密码" title="确认密码"
                                           maxlength="255" required="true"
                                           size="50" cssClass="form-control"/>
                            <form:errors path="rePassword" cssClass="text-danger"/>
                            <p class="help-block">请再次输入用户密码.</p>
                        </div>
                    </c:if>
                    <div class="form-group">
                        <label class="others">其他信息</label>
                        <form:textarea path="others" id="others" rows="3" cssClass="form-control"/>
                        <p class="help-block">用户的其他信息, 若有则填写在此..</p>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i> 保存
                        </button>
                        &nbsp;<a href="../overview.wdcy">取消</a>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>

<%--department form--%>
<!-- Modal -->
<div class="modal fade" id="deptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加部门</h4>
            </div>
            <form action="${contextPath}/dept/form/create.wdcy" method="post" id="deptForm" role="form"
                  target="userFormIframe">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="deptName">部门名称</label>
                        <input type="text" name="name" id="deptName" placeholder="请输入部门名称" required="required"
                               class="form-control"/>

                        <p class="help-block">部门名称必须唯一.</p>
                    </div>
                    <div class="form-group">
                        <label for="deptDescription">部门描述</label>
                        <textarea id="deptDescription" name="description" class="form-control" rows="3"
                                  placeholder="请输入部门描述信息"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" id="deptSubmitBtn"><i
                            class="glyphicon glyphicon-plus"></i> 保存
                    </button>
                    &nbsp;<a href="javascript:void(0);" data-dismiss="modal">取消</a>
                </div>
            </form>
        </div>
    </div>
</div>
<iframe id="userFormIframe" name="userFormIframe" class="hide"></iframe>

<script src="${contextPath}/resources/js/datepicker/bootstrap-datepicker.js"></script>
<script src="${contextPath}/resources/js/datepicker/bootstrap-datepicker.zh-CN.js"></script>

<script>
    $(function () {
        new UserForm();
    });

    //call back by dept iframe
    function closeDeptModal() {
        $("#deptModal").modal("hide");
    }
</script>
</body>
</html>