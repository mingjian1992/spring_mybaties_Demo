
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的数据</title>
    <style>
        .data-content {
            padding-top: 40px;
            color: #222222;
        }
    </style>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <ul class="nav nav-tabs">
            <li><a href="index.zsfz"><i class="glyphicon glyphicon-home"></i> 我的信息</a></li>
            <li class="active"><a href="#mydata"><i class="glyphicon glyphicon-stats"></i> 我的数据</a></li>
            <li><a href="my_match.zsfz"><i class="glyphicon glyphicon-tasks"></i> 我的比赛</a></li>
        </ul>
        <div id="mydata">
            <div class="row">
                <div class="col-md-3 col-md-offset-2">
                    <canvas id="scoreCanvas" height="250" width="250"></canvas>
                </div>
                <div class="col-md-5">
                    <div class="data-content">
                        <h1>${dataDto.myScores}个进球
                            <small>总共${dataDto.totalScores}个进球</small>
                        </h1>
                        <p class="text-info">
                            截止今日(${dataDto.today}), 我已经打进了${dataDto.myScores}个进球,
                            占球队总进球(${dataDto.totalScores}个)的<strong
                                class="text-danger">${dataDto.scoresPercent}%</strong>;
                            我的进攻实力不容小视吧, 哈哈!
                            <i class="glyphicon glyphicon-thumbs-up"></i>
                        </p>
                    </div>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-md-5 col-md-offset-2">
                    <div class="data-content pull-right">
                        <h1>${dataDto.myAssists}次助攻
                            <small>总共${dataDto.totalAssists}次助攻</small>
                        </h1>
                        <p class="text-info">
                            时至今日(${dataDto.today}), 我已经完成了${dataDto.myAssists}次助攻,
                            占球队总助攻(${dataDto.totalAssists}次)的<strong
                                class="text-danger">${dataDto.assistsPercent}%</strong>;
                            别小看我的助攻能力哈!
                        </p>
                    </div>
                </div>
                <div class="col-md-3">
                    <canvas id="assistCanvas" height="250" width="250"></canvas>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-md-3 col-md-offset-2">
                    <canvas id="matchCanvas" height="250" width="250"></canvas>
                </div>
                <div class="col-md-5">
                    <div class="data-content">
                        <h1>${dataDto.myMatches}场比赛
                            <small>总共${dataDto.totalMatches}场比赛</small>
                        </h1>
                        <p class="text-info">
                            从古至今, 我参与了球队一共${dataDto.totalMatches}场正式比赛中的${dataDto.myMatches}场, 参赛率为<strong
                                class="text-danger">${dataDto.matchesPercent}%</strong>; 一个了不起的成就, YeYe!
                            <i class="glyphicon glyphicon-star-empty"></i>
                        </p>
                    </div>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-md-5 col-md-offset-2">
                    <div class="data-content pull-right">
                        <h1>${dataDto.win}次胜利
                            <small>${dataDto.equals}次平局, ${dataDto.fail}次失利</small>
                        </h1>
                        <p class="text-info">
                            回忆过去, 我参与的比赛已经取得了${dataDto.win}次胜利, 外加${dataDto.equals}次平局与${dataDto.fail}次失利, 获胜率为<strong
                                class="text-danger">${dataDto.winPercent}%</strong>;
                            我就是球队赢球的保证, 哟哟!
                            <i class="glyphicon glyphicon-thumbs-down"></i>
                        </p>
                    </div>
                </div>
                <div class="col-md-3">
                    <canvas id="goalCanvas" height="250" width="250"></canvas>
                </div>
            </div>

        </div>
    </div>
</div>

<script src="${contextPath}/resources/js/Chart.min.js"></script>
<script>
    $(function () {
        //score chart
        var doughnutData = [
            {
                value:${dataDto.myScores},
                color:"#F7464A"
            },
            {
                value:${dataDto.restScores},
                color:"#46BFBD"
            }
        ];
        var myDoughnut = new Chart(document.getElementById("scoreCanvas").getContext("2d")).Doughnut(doughnutData);
        //assist chart
        var pieData = [
            {
                value:${dataDto.myAssists},
                color:"#F38630"
            },
            {
                value:${dataDto.restAssists},
                color:"#E0E4CC"
            }
        ];
        var myPie = new Chart(document.getElementById("assistCanvas").getContext("2d")).Pie(pieData);
        //matches chart
        var chartData = [
            {
                value:${dataDto.myMatches},
                color:"#D97041"
            },
            {
                value:${dataDto.restMatches},
                color:"#7D4F6D"
            }
        ];
        var myPolarArea = new Chart(document.getElementById("matchCanvas").getContext("2d")).Pie(chartData);
        //goal chart
        var goalData = [
            {
                value:${dataDto.win},
                color:"#F38630"
            },
            {
                value:${dataDto.equals},
                color:"#00C0FF"
            },
            {
                value:${dataDto.fail},
                color:"#E0E4CC"
            }
        ];
        var goalPie = new Chart(document.getElementById("goalCanvas").getContext("2d")).Doughnut(goalData);
    });
</script>
</body>
</html>