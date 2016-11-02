
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
    <title>比赛信息</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/bootstrap-datetimepicker.min.css"/>

    <style>
        #goalTable tbody tr td:last-of-type, #goalTable tbody tr td:first-of-type {
            text-align: center;
            vertical-align: middle;
        }
    </style>
</head>
<body>
<div class="row">
<div class="col-md-12">
<div class="panel panel-info">
<div class="panel-body">
<form:form commandName="formDto" role="form" enctype="multipart/form-data">
<c:if test="${formDto.newly}">
    <div class="form-group">
        <label for="noticeGuid" class="control-label">比赛预告</label>
        <form:select path="notice.guid" id="noticeGuid" cssClass="form-control">
            <form:option value="" label="选择比赛预告"/>
            <c:forEach items="${formDto.notices}" var="n">
                <form:option value="${n.guid}" label="${n.previewText}" stadiumGuid="${n.stadium.guid}" time="${n.time}"
                             clubGuid="${n.opponent.guid}"/>
            </c:forEach>
        </form:select>
        <form:errors path="notice.guid" cssClass="text-danger"/>
        <p class="help-block">若该比赛有对应的预告信息, 则选择(选择后将自动填充比赛时间,球场与对手).</p>

            <%--for bind when validation failed--%>
        <c:forEach items="${formDto.notices}" var="n" varStatus="s">
            <input type="hidden" name="notices[${s.index}].guid" value="${n.guid}"/>
            <input type="hidden" name="notices[${s.index}].stadium.guid" value="${n.stadium.guid}"/>
            <input type="hidden" name="notices[${s.index}].stadium.name" value="${n.stadium.name}"/>
            <input type="hidden" name="notices[${s.index}].time" value="${n.time}"/>
            <input type="hidden" name="notices[${s.index}].previewTime" value="${n.previewTime}"/>
            <input type="hidden" name="notices[${s.index}].previewTime" value="${n.previewTime}"/>
            <input type="hidden" name="notices[${s.index}].opponent.guid" value="${n.opponent.guid}"/>
            <input type="hidden" name="notices[${s.index}].opponent.name" value="${n.opponent.name}"/>
            <input type="hidden" name="notices[${s.index}].remark" value="${n.remark}"/>
        </c:forEach>
    </div>
</c:if>

<div class="form-group">
    <label for="matchTime" class="control-label">比赛时间</label>
    <form:input path="matchTime" cssClass="form-control" id="matchTime" placeholder="选择比赛时间"
                required="required"/>
    <form:errors path="matchTime" cssClass="text-danger"/>
    <p class="help-block">该比赛的开球时间.</p>
</div>
<div class="form-group">
    <label for="stadiumGuid" class="control-label">比赛球场</label>
    <form:select path="stadium.guid" id="stadiumGuid" cssClass="form-control">
        <form:option value="" label="选择比赛球场"/>
        <form:options items="${formDto.stadiums}" itemLabel="nameIncludeHome" itemValue="guid"/>
    </form:select>
    <form:errors path="stadium.guid" cssClass="text-danger"/>
    <p class="help-block">在哪踢这场球赛的.</p>

        <%--for bind when validation failed--%>
    <c:forEach items="${formDto.stadiums}" var="n" varStatus="s">
        <input type="hidden" name="stadiums[${s.index}].guid" value="${n.guid}"/>
        <input type="hidden" name="stadiums[${s.index}].homeStadium" value="${n.homeStadium}"/>
        <input type="hidden" name="stadiums[${s.index}].name" value="${n.name}"/>
    </c:forEach>
</div>
<div class="form-group">
    <label for="opponentGuid" class="control-label">比赛对手</label>
    <form:select path="opponent.guid" id="opponentGuid" cssClass="form-control">
        <form:option value="" label="选择比赛对手"/>
        <form:options items="${formDto.clubs}" itemLabel="name" itemValue="guid"/>
    </form:select>
    <form:errors path="opponent.guid" cssClass="text-danger"/>
    <p class="help-block">选择比赛和谁踢.</p>

        <%--for bind when validation failed--%>
    <c:forEach items="${formDto.clubs}" var="n" varStatus="s">
        <input type="hidden" name="clubs[${s.index}].guid" value="${n.guid}"/>
        <input type="hidden" name="clubs[${s.index}].name" value="${n.name}"/>
    </c:forEach>
</div>
<div class="form-group">
    <label for="seasonGuid" class="control-label">赛季</label>
    <form:select path="season.guid" id="seasonGuid" cssClass="form-control">
        <form:option value="" label="选择赛季"/>
        <c:forEach items="${formDto.seasons}" var="s">
            <form:option value="${s.guid}" label="${s.name}"/>
        </c:forEach>
    </form:select>
    <form:errors path="season.guid" cssClass="text-danger"/>
    <p class="help-block">选择比赛是属于哪个赛季的, 默认根据当前时间计算的赛季.</p>

        <%--for bind when validation failed--%>
    <c:forEach items="${formDto.seasons}" var="n" varStatus="s">
        <input type="hidden" name="seasons[${s.index}].guid" value="${n.guid}"/>
        <input type="hidden" name="seasons[${s.index}].name" value="${n.name}"/>
    </c:forEach>
</div>
<div class="form-group has-success">
    <label for="homeScores" class="control-label">比赛比分</label>

    <div class="row">
        <div class="col-xs-1">
            <form:input path="homeScores" cssClass="form-control" id="homeScores"
                        placeholder="我们进球数" title="我们进球数"
                        required="required"/>

        </div>
        <div class="col-xs-1" style="width:10px;"><strong>:</strong></div>
        <div class="col-xs-1">
            <form:input path="awayScores" cssClass="form-control" id="awayScores"
                        placeholder="对手进球数" title="对手进球数"
                        required="required"/>
        </div>
    </div>

    <form:errors path="homeScores" cssClass="text-danger"/>
    <p class="help-block">第一个输入框是我们进球数,第二个输入框是对手进球数. 我们进球数必须和下面的进球信息一致.</p>
</div>
<div class="form-group">
    <label class="control-label">进球信息</label>

    <div class="row">
        <div class="col-xs-12">
            <div class="table-responsive">
                <table class="table table-bordered" id="goalTable">
                    <thead>
                    <tr style="background-color: #eff0ef;">
                        <th><a href="javascript:void(0)" title="添加进球信息" id="addGoalLine"><i
                                class="glyphicon glyphicon-plus-sign"></i></a></th>
                        <th>进球球员</th>
                        <th>助攻球员</th>
                        <th>进球时间</th>
                        <th>OG</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${formDto.newly and formDto.goalsSize == 0}">
                        <tr index="0">
                            <td>1</td>
                            <td>
                                <form:select path="goals[0].goalPlayer.guid" cssClass="form-control">
                                    <form:option value="" label="选择进球球员"/>
                                    <form:options items="${formDto.players}" itemLabel="fullName"
                                                  itemValue="guid"/>
                                </form:select>
                            </td>
                            <td>
                                <form:select path="goals[0].assistantPlayer.guid"
                                             cssClass="form-control">
                                    <form:option value="" label="选择助攻球员"/>
                                    <form:options items="${formDto.players}" itemLabel="fullName"
                                                  itemValue="guid"/>
                                </form:select>
                            </td>
                            <td>
                                <form:select path="goals[0].goalTime"
                                             cssClass="form-control">
                                    <form:option value="-1" label="选择进球时间"/>
                                    <c:forEach var="t" begin="1" end="125">
                                        <form:option value="${t}" label="第 ${t} 分钟"/>
                                    </c:forEach>
                                </form:select>
                            </td>
                            <td>
                                <div class="checkbox">
                                    <label>
                                        <form:checkbox path="goals[0].ownGoal" cssClass="ownGoal"/> 对手OG
                                    </label>
                                </div>
                            </td>
                            <td>
                                <a href="javascript:void(0)" title="删除该进球信息" class="removeGoal"><i
                                        class="glyphicon glyphicon-remove-sign"></i></a>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${(formDto.goalsSize  > 1) or (not formDto.newly)}">
                        <c:forEach items="${formDto.goals}" var="g" varStatus="s">
                            <tr index="${s.index}">
                                <td>${s.count}</td>
                                <td>
                                    <form:select path="goals[${s.index}].goalPlayer.guid" cssClass="form-control">
                                        <form:option value="" label="选择进球球员"/>
                                        <form:options items="${formDto.players}" itemLabel="fullName"
                                                      itemValue="guid"/>
                                    </form:select>
                                </td>
                                <td>
                                    <form:select path="goals[${s.index}].assistantPlayer.guid"
                                                 cssClass="form-control">
                                        <form:option value="" label="选择助攻球员"/>
                                        <form:options items="${formDto.players}" itemLabel="fullName"
                                                      itemValue="guid"/>
                                    </form:select>
                                </td>
                                <td>
                                    <form:select path="goals[${s.index}].goalTime"
                                                 cssClass="form-control">
                                        <form:option value="-1" label="选择进球时间"/>
                                        <c:forEach var="t" begin="1" end="125">
                                            <form:option value="${t}" label="第 ${t} 分钟"/>
                                        </c:forEach>
                                    </form:select>
                                </td>
                                <td>
                                    <div class="checkbox">
                                        <label>
                                            <form:checkbox path="goals[${s.index}].ownGoal" cssClass="ownGoal"/> 对手OG
                                        </label>
                                    </div>
                                </td>
                                <td>
                                    <a href="javascript:void(0)" title="删除该进球信息"
                                       class="removeGoal ${s.last?'hidden':''}"><i
                                            class="glyphicon glyphicon-remove-sign"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <form:errors path="goals" cssClass="text-danger"/>
    <p class="help-block">进球信息要与进球数一致, 如有3个进球, 则有3条进球信息; 若无助攻则不选择助攻球员; 若不清楚进球时间则不选择.</p>
</div>

<div class="form-group">
    <label class="control-label">参赛球员 &nbsp;&nbsp;<a href="javascript:void(0);" title="勾选所有球员"
                                                     class="checkAllPlayers"><i class="glyphicon glyphicon-ok-sign"></i></a></label>

    <div>
        <c:forEach items="${formDto.players}" var="p">
            <span class="checkbox-inline">
                 <label>
                     <form:checkbox path="joinPlayerGuids" value="${p.guid}"/> ${p.fullName}
                 </label>
            </span>
        </c:forEach>
    </div>
    <form:errors path="joinPlayerGuids" cssClass="text-danger"/>
    <p class="help-block">勾选上参加比赛的球员.</p>

        <%--for bind when validation failed--%>
    <c:forEach items="${formDto.players}" var="n" varStatus="s">
        <input type="hidden" name="players[${s.index}].guid" value="${n.guid}"/>
        <input type="hidden" name="players[${s.index}].fullName" value="${n.fullName}"/>
    </c:forEach>
</div>

<div class="checkbox">
    <label class="text-danger">
        <form:checkbox path="futsal"/> 是否为五人制球赛
    </label>
</div>

<div class="form-group">
    <label for="remark" class="control-label">备注信息</label>
    <form:textarea path="remark" id="remark" class="form-control" rows="3"
                   placeholder="写点比赛的总结,或者有关的信息喽" required="true"/>
    <form:errors path="remark" cssClass="text-danger"/>
    <p class="help-block">比赛的备注信息.</p>
</div>

<c:if test="${formDto.newly}">
    <div class="form-group">
        <label class="control-label">比赛照片</label><br/>
        照片一 <input type="file" id="file0" name="photos[0]"/>
        照片二 <input type="file" id="file1" name="photos[1]"/>
        照片三 <input type="file" id="file2" name="photos[2]"/>
        <form:errors path="photos" cssClass="text-danger"/>
        <p class="help-block">若该比赛有照片, 则可上传, 最多上传3张,且每张大小限制为1MB; 若有更多的照片需要上传,待比赛信息保存完成后去相册管理上传; 上传是可选的.</p>
    </div>
</c:if>

<button type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i> 保存
</button>
&nbsp;<a href="../../match.zsfz">取消</a>

</form:form>
</div>
</div>
</div>
</div>


<script src="${contextPath}/resources/js/datepicker/bootstrap-datetimepicker.min.js"></script>
<script src="${contextPath}/resources/js/datepicker/bootstrap-datetimepicker.zh-CN.js"></script>

<script>
    $(function () {
        new MatchForm();
    });
</script>
</body>
</html>