
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty photosDto.photos}">
    <h2>比赛照片</h2>

    <div class="pictures-area">
        <div class="row">
            <c:forEach items="${photosDto.photos}" var="p" varStatus="s">
            <div class="col-md-3">
                <a href="${contextPath}/public/image/${p.guid}.zsfz" class="pictureGroup">
                    <img data-original="${contextPath}/public/image/${p.guid}.zsfz?w=180" alt="..."
                         src="${contextPath}/resources/images/loading.gif"
                         class="img-thumbnail lazy"/>
                </a>
            </div>
            <c:if test="${s.count%4 eq 0}">
        </div>
        <div class="row" style="margin-top:5px;">
            </c:if>
            </c:forEach>

        </div>
    </div>


    <script src="${contextPath}/resources/colorbox/jquery.colorbox-min.js"></script>
    <script src="${contextPath}/resources/colorbox/jquery.colorbox-zh-CN.js"></script>
    <script src="${contextPath}/resources/js/jquery.lazyload.min.js"></script>

    <script type="text/javascript">
        $(function () {
            //colorbox init
            $(".pictures-area a").colorbox({
                maxWidth:'80%',
                rel:'pictureGroup',
                photo:true
            });
            $("img.lazy").lazyload({
                effect:"fadeIn",
                threshold:10
            });
        });
    </script>
    <hr/>
</c:if>