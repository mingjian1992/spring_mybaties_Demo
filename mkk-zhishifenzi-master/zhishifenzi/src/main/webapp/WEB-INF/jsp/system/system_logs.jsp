<%--
 * 
 * @author Shengzhao Li
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="displaytag" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>系统日志</title>
</head>
<body>

<div class="row">

    <div class="col-md-12">
        <div class="panel panel-danger">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-md-10">
                        <form id="logForm" action="" class="form-inline" role="form">
                            <div class="form-group">
                                <label class="sr-only" for="name">日志内容</label>
                                <input id="name" type="text" name="name"
                                       value="${overviewDto.name}"
                                       title="日志内容" placeholder="日志内容" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="type">日志内容</label>
                                <select id="type" name="type" class="form-control">
                                    <option value="">所有日志类型</option>
                                    <c:forEach items="${overviewDto.types}" var="t">
                                        <option value="${t.value}" ${overviewDto.type eq t?'selected':''}>${t.label}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="userGuid">操作人</label>
                                <select id="userGuid" name="userGuid" class="form-control">
                                    <option value="">所有操作人</option>
                                    <c:forEach items="${overviewDto.users}" var="t">
                                        <option value="${t.guid}" ${overviewDto.userGuid eq t.guid?'selected':''}>${t.username}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询
                            </button>
                        </form>
                    </div>
                    <div class="col-md-2">
                        &nbsp;
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <div class="table-responsive">
                    <displaytag:table list="${overviewDto}" id="log" form="logForm"
                                      class="table table-hover table-striped">
                        <displaytag:column property="time" title="记录时间" style="width:12%;"/>
                        <displaytag:column property="username" title="用户" style="width:7%;"/>
                        <displaytag:column property="ipAddress" title="IP地址"/>
                        <displaytag:column property="content" title="内容"/>
                        <displaytag:column style="width:3%;">
                            <a href="#" data-toggle="modal" data-target="#${log.guid}Modal"><i
                                    class="glyphicon glyphicon-info-sign"></i></a>

                            <div class="modal fade" id="${log.guid}Modal" tabindex="-1" role="dialog"
                                 aria-labelledby="${log.guid}ModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal"
                                                    aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="${log.guid}ModalLabel">${log.time}</h4>
                                        </div>
                                        <div class="modal-body">
                                            <p class="text-info">
                                                    ${log.content}
                                            </p>
                                        </div>
                                        <div class="modal-footer">
                                            <a href="javascript:void(0);" class="btn btn-default"
                                               data-dismiss="modal">关闭</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </displaytag:column>
                    </displaytag:table>
                </div>
            </div>
            <div class="panel-footer text-muted">
                <i class="glyphicon glyphicon-info-sign"></i>
                共 <strong>${overviewDto.totalSize}</strong> 条日志记录.
            </div>
        </div>
    </div>
</div>
</body>
</html>