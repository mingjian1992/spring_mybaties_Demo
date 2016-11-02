
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <div class="row">
        <div class="col-md-6">
            <img src="${contextPath}/public/player_image/${playerDto.guid}.zsfz" style="width: 97px;height: 120px;"
                 alt="img" class="thumbnail"/>
        </div>
        <div class="col-md-6">
            <strong>${playerDto.number}</strong>号 <br/>
            <span class="text-muted">位置:</span> ${playerDto.position.label} <br/>

            <p class="text-info">
                ${playerDto.description}
            </p>
        </div>
    </div>
</div>