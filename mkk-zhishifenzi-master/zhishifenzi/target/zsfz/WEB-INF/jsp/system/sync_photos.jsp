
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
    <title>照片同步</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <!--// alert start -->
        <div class="alert alert-success hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="glyphicon glyphicon-ok"></i>
                <span id="transferSuccess" class="hide">同步照片到云服务器成功!!</span>
            </div>
        </div>
        <div class="alert alert-danger hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <div><i class="glyphicon glyphicon-info-sign"></i>
                <span id="transferFailed" class="hide">同步照片到云服务器失败.</span>
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
                    <div class="col-md-11">
                        <form id="pForm" action="" class="form-inline" role="form">
                            <div class="form-group">
                                <%--<label class="radio-inline">--%>
                                <%--<input type="radio" name="displayType"--%>
                                <%--value="null" ${syncPhotosDto.displayType eq null?'checked':''}/> 所有照片--%>
                                <%--</label>--%>
                                <label class="radio-inline">
                                    <input type="radio" name="displayType"
                                           value="true" ${syncPhotosDto.displayType?'checked':''}/> 已同步照片
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="displayType"
                                           value="false" ${not syncPhotosDto.displayType?'checked':''}/> 未同步照片
                                </label>
                            </div>
                            <button type="submit" class="btn btn-default"><i class="glyphicon glyphicon-search"></i> 查询
                            </button>
                            &nbsp;&nbsp;
                            <a class="btn btn-primary hidden" href="javascript:void(0)">批量同步</a>
                        </form>
                    </div>
                    <div class="col-md-1">
                        <div class="pull-right">
                            &nbsp;
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <div class="table-responsive">
                    <dis:table id="p" class="table table-striped table-hover" list="${syncPhotosDto}"
                               form="pForm">
                        <c:if test="${not syncPhotosDto.displayType}">
                            <dis:column title="<input type='checkbox' name='waitAllSync'/>">
                                <input type="checkbox" name="waitSync" value="${p.guid}"/>
                            </dis:column>
                        </c:if>
                        <dis:column title="文件名">
                            <div class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void(0)">
                                        ${p.name} <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <c:if test="${empty p.url}">
                                        <li><a href="transfer_photo/${p.guid}.zsfz"><i
                                                class="glyphicon glyphicon-transfer"></i>
                                            同步</a>
                                        </li>
                                    </c:if>
                                    <li><a href="../../public/image/${p.guid}.zsfz" target="_blank"><i
                                            class="glyphicon glyphicon-picture"></i>
                                        预览</a>
                                    </li>
                                </ul>
                            </div>
                        </dis:column>
                        <dis:column title="大小(KB)" property="sizeAsKB"/>
                        <dis:column title="URL" property="url" autolink="true" href="${p.url}" style="width:50%;"/>
                        <dis:column title="所属相册" property="albumName" autolink="true"
                                    href="../album/details/${p.albumGuid}.zsfz"/>
                    </dis:table>
                </div>
            </div>
            <div class="panel-footer">
                <div class="text-muted">
                    共 ${syncPhotosDto.totalSize} 张图片;
                    同步到云存储的照片会加快访问速度, 且不耗服务器的资源, 但可能需要支付云存储费用(超出10G/月流量后).
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        new SyncPhotos('${param.alert}');
    });
</script>
</body>
</html>