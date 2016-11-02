
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${indexDto.showName}</title>

    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/js/fileupload/jquery.fileupload-ui.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/js/Jcrop/jquery.Jcrop.min.css"/>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <c:forEach items="${indexDto.notices}" var="n">
            <div class="alert alert-info">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <i class="glyphicon glyphicon-info-sign"></i> <strong>比赛预告:</strong> ${n.previewText}
            </div>
        </c:forEach>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <ul class="nav nav-tabs">
            <li class="active"><a href="#myinfo"><i class="glyphicon glyphicon-home"></i> 我的信息</a></li>
            <li><a href="my_data.zsfz"><i class="glyphicon glyphicon-stats"></i> 我的数据</a></li>
            <li><a href="my_match.zsfz"><i class="glyphicon glyphicon-tasks"></i> 我的比赛</a></li>
        </ul>
        <div id="myinfo">
            <div class="row">
                <div class="col-md-4">
                    <div class="row" style="padding-top: 5px;">
                        <div class="col-md-6">
                            <div class="btn btn-sm btn-default fileinput-button pull-right">
                                <img src="${contextPath}/public/player_image/${indexDto.playerGuid}.zsfz"
                                     style="width: 97px;height: 120px;"/>
                                <input id="fileupload" type="file" name="file" title="点击选择头像"/>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <br/>

                            <h1 class="text-success">
                                ${indexDto.showName}(${indexDto.number})
                            </h1>
                            <hr/>
                        </div>
                    </div>
                    <div style="padding-left: 10px;">
                        <ul>
                            <li>
                                <p>
                                    <span class="text-muted">上次登录时间:</span> ${indexDto.lastLoginTime}
                                </p>
                            </li>
                            <c:if test="${not empty indexDto.email}">
                                <li>
                                    <p>
                                        <span class="text-muted">我的邮件地址:</span> <a
                                            href="mailto:${indexDto.email}">${indexDto.email}</a>
                                    </p>
                                </li>
                            </c:if>
                            <c:if test="${not empty indexDto.phone}">
                                <li>
                                    <p>
                                        <span class="text-muted">我的电话号码:</span> ${indexDto.phone}
                                    </p>
                                </li>
                            </c:if>

                            <c:if test="${not empty indexDto.entryDate}">
                                <li>
                                    <p>
                                        <span class="text-muted">加入球队日期:</span> ${indexDto.entryDate}
                                    </p>
                                </li>
                            </c:if>
                            <c:if test="${not empty indexDto.position}">
                                <li>
                                    <p>
                                        <span class="text-muted">我在场上位置:</span> <strong>${indexDto.position}</strong>
                                    </p>
                                </li>
                            </c:if>
                            <li>
                                <p class="text-warning">
                                    ${indexDto.description}
                                </p>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-4">
                    <h2>更新信息</h2>

                    <p class="text-muted">
                        当我的个人信息有变化时, 请在此更新, 包括更新我的电话号码, 邮件地址, 姓名等信息.
                    </p>

                    <p>
                        <a class="btn btn-primary" href="my_profile.zsfz">更新 »</a>
                    </p>
                </div>
                <div class="col-md-4">
                    <h2>修改密码</h2>

                    <p class="text-muted">
                        为了账号的安全, 请定期修改登录密码, 密码长度要求至少6位, 且应该包括大小写字母,数字等.
                    </p>

                    <p>
                        <a class="btn btn-primary" href="change_password.zsfz">修改 »</a>
                    </p>
                </div>
            </div>

        </div>
    </div>
</div>

<!-- crop Modal -->
<div class="modal fade" id="cropModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">裁剪头像</h4>
            </div>
            <form method="post" action="submit_head_image.zsfz" id="cropHeadForm">
                <div class="modal-body">
                    <div>
                        <img src="${contextPath}/resources/images/default_user.png" id="cropImg"
                             class="img-responsive" style="max-width: 600px;max-height: 300px;"/>

                        <p class="help-block">
                            请在调整照片大小后点击"保存"按钮.
                        </p>
                        <input type="hidden" name="fileGuid" id="cropImageGuid"/>

                        <div id="crop_data_hidden">
                            <input type="hidden" name="width" id="i_width" value="0"/>
                            <input type="hidden" name="height" id="i_height" value="0"/>
                            <input type="hidden" name="x1" id="i_x1" value="0"/>
                            <input type="hidden" name="x2" id="i_x2" value="0"/>
                            <input type="hidden" name="y1" id="i_y1" value="0"/>
                            <input type="hidden" name="y2" id="i_y2" value="0"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok"></span> 保存
                    </button>
                    <a href="javascript:void(0)" data-dismiss="modal">取消</a>
                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script type="text/javascript" src="${contextPath}/resources/js/fileupload/jquery.ui.widget.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/fileupload/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/fileupload/jquery.fileupload.js"></script>

<script type="text/javascript" src="${contextPath}/resources/js/Jcrop/jquery.Jcrop.min.js"></script>

<script>
    $(function () {
        new BackendIndex();
    });
</script>
</body>
</html>