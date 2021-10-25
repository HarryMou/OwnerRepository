<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <style>
        .left{
            background-color: lightgray;
            width: 20%;
            height: 700px;
            float: left;
        }
        .right{
            background-color: lavenderblush;
            width: 80%;
            height: 700px;
            float: left;
        }
        .one{
            width: 100%;
            border:0;
        }
        .two{
            width: 80%;
            border: 0;
        }
    </style>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(function(){

            var btn1click = false;
            var btn2click = false;
            var userid = undefined;
            $("#btn1").click(function(){
                if(btn1click){
                    $("#div1").attr("style","display: none;")
                    btn1click = false;
                }else{
                    $("#div1").attr("style","display:'';")
                    btn1click = true
                }
            })

            $("#add").click(function(){
                $.ajax({
                    url:"add.html",
                    type:"GET",
                    success:function(data){
                        var result = $(data).find("table");
                        $("#cen").html(result);
                    }
                })
            })

            //显示所有数据的方法
            function cha(){
                $.ajax({
                    url:"select.html",
                    type:"get",
                    success:function(data){
                        var result = $(data).find("table");
                        $("#cen").html(result);
                        $.ajax({
                            url:"selectAll",
                            type:"get",
                            dataType:"json",
                            success:function (res) {
                                $.each(res,function (i,n) {
                                    $("#selectres").append("<tr>" +
                                        "<td><input type=\"checkbox\" class=\"checks\"/></td>" +
                                        "<td>"+n.userid+"</td>" +
                                        "<td>"+n.username+"</td>" +
                                        "<td>"+n.password+"</td>" +
                                        "<td>"+n.sex+"</td>" +
                                        "<td>"+n.email+"</td>" +
                                        "<td><button type='button' class='change' value='"+ n.userid +"'>修改</button><button type='button' class='del' value='"+n.userid+"'>删除</button>" +
                                        "</tr>")
                                })
                            }
                        })
                    }
                })
            }

            //通过代理对插入按钮绑定提交事件，将用户参数通过post方式发给后台
            $(document).on("click","#insert",function () {
                var emil = $('#email').val() + $('#emailfoot>option:selected').text()
                $.ajax({
                    url:"insert",
                    type:"post",
                    data:{"username": $('#username').val(),
                        "password": $('#password2').val(),
                            "sex":$(':radio:checked').val(),
                            "email":emil
                        },
                    success:function (data) {
                        alert(data);
                        cha();
                    }
                })
            })

            //显示所有数据
            $("#drop").click(function(){
                cha();
            })

            //动态加载修改界面到当前页
            $(document).on("click",".change",function () {
                userid = $(this).val()
                $.ajax({
                    url:"change.html",
                    type:"get",
                    success:function (res) {
                        var result = $(res).find("table")
                        $("#cen").html(result);
                        //加载完成后将修改项的数据传入对应的框中
                        $.ajax({
                            url:"selectId",
                            type:"get",
                            data: "userid="+userid,
                            dataType: "json",
                            success:function (data) {
                                $("#username").val(data.username);
                                $("#oldpwd").val(data.password);
                                if(data.sex=="男"){
                                    $("#man").attr("checked","checked")
                                }else {
                                    $("#woman").attr("checked","checked")
                                }
                                $("#newemail").val(data.email);
                            }
                        })
                    }
                })
            })

            //修改数据
            $(document).on("click","#update",function () {
                var pwd = $("#newpwd").val();
                if(null==pwd || ""==pwd){
                    pwd = $("#oldpwd").val();
                }
                var email = $('#newemail').val()
                $.ajax({
                    url:"update",
                    type:"post",
                    data:{"userid":userid,
                        "username": $('#username').val(),
                        "password": pwd,
                        "sex":$(':radio:checked').val(),
                        "email":email,
                    },
                    success:function (data) {
                        alert(data);
                        cha();
                    }
                })
            })

            //删除数据
            $(document).on("click",".del",function () {
                if(window.confirm("你确定要删除数据吗？")){
                    var userid = $(this).val();
                    $.ajax({
                        url:"delete",
                        type:"get",
                        data:"userid="+userid,
                        success:function (res) {
                            alert(res);
                            cha();
                        }
                    })
                }
            })

            //查询
            $(document).on("keyup","#find",function () {
                var k = $("#findfoot>option:selected").val();
                var value = $("#find").val();
                $.ajax({
                    url:"selectOne",
                    type:"get",
                    data:"k="+k+"&value="+value,
                    dataType:"json",
                    success:function (res) {
                        $("#selectres").empty();
                        if($("#find").val==''){
                            cha();
                        }
                        $.each(res,function (i,n) {
                            $("#selectres").append("<tr>" +
                                "<td><input type=\"checkbox\" name=\"checks\"/></td>" +
                                "<td>"+n.userid+"</td>" +
                                "<td>"+n.username+"</td>" +
                                "<td>"+n.password+"</td>" +
                                "<td>"+n.sex+"</td>" +
                                "<td>"+n.email+"</td>" +
                                "<td><button type='button' class='change' value='"+ n.userid +"'>修改</button><button type='button' class='del' value='"+n.userid+"'>删除</button>" +
                                "</tr>")
                        })
                    }
                })
            })

            $("#checkall").click(function () {
                var c = false;
                if(c){
                    $("input:checkbox").attr("checked",false)
                    c = false;
                }else {
                    $("input:checkbox").attr("checked",true)
                    c = true;
                }
            })

            $(document).on("click",".checks",function () {
                var checklist = $(".checks")
                var num = 0;

            })
        })
    </script>
</head>
<body>
<center>
    <p1>基于Mybatis和Ajax的增删改查</p1>
</center><hr />
<div class="left">
    <button id="btn1" class="one">管理员操作</button><br />
    <div id="div1" style="display: none;">
        <button id="add" class="two">添加用户</button>
        <button id="drop" class="two">管理用户</button>
    </div>
</div>
<div class="right">
    <center id="cen">
        <font style="font-size: 50px;line-height:100px;">欢迎使用本系统</font>
    </center>
</div>
</body>
</html>
