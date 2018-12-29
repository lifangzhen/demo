<!DOCTYPE html>
<html lang="en-US">
<head>
    <meta charset="UTF-8">
    <title>打卡页面</title>
</head>
<body>

<button id="start">打卡开始</button>
<button id="end">打卡结束</button>

<table>
    <tr>
        <td>日  期</td>
        <td>时段一</td>
        <td>时段二</td>
        <td>时段三</td>
    </tr>
    <#list list as clock>
        <tr>
            <td>${clock.id}</td>
            <#if clock.start1??&&clock.end1??>
                <td>${clock.start1}-${clock.end1}</td>
            <#else>
                <td>----</td>
            </#if>
            <#if clock.start2??&&clock.end2??>
                <td>${clock.start2}-${clock.end2}</td>
            <#else>
                <td>----</td>
            </#if>
            <#if clock.start3??&&clock.end3??>
                <td>${clock.start3}-${clock.end3}</td>
            <#else>
                <td>----</td>
            </#if>
        </tr>
    </#list>
</table>
</body>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js">

    <script>
    $("#start").click(function(){
        $.get("/start.json",function(data,status){
            alert(data);
        });
    });
    </script>
</html>