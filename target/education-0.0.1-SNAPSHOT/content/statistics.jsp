<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>财务统计</title>
    <script type="text/javascript">
        $(document).ready(function () {
        	statistics.bindForm();
        });
    </script>
</head>
<body>
    <div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="#">首页</a></li>
            <li><a href="#">销售管理</a></li>
            <li><a href="#">财务统计</a></li>
        </ul>
    </div>
    <div class="formbody">
        <form id='form1' action="#" enctype="multipart/form-data" method="post">
        <div class="formtitle">
            <span>销量信息</span></div>
        <ul class="forminfo">
            <li><label style="float:left">本日销量</label>
            <div style="border:1px solid #b8dcff;float:left;"><label style="float:left">今日成交额</label>
            	<input name="daysale" type="text" class="dfinput" style="float:left"/>
                <label style="float:left">今日退款额</label>
                <input name="dayrefund" type="text" class="dfinput" style="float:left"/>
                <label style="float:left">支付手续费</label>
                <input name="dayfee" type="text" class="dfinput" style="float:left"/>
            </div></li>
            <li><label style="float:left">本周销量</label>
            <div style="border:1px solid #b8dcff;float:left;"><label style="float:left">本周成交额</label>
            	<input name="weaksale" type="text" class="dfinput" style="float:left"/>
                <label style="float:left">本周退款额</label>
                <input name="weakrefund" type="text" class="dfinput" style="float:left"/>
                <label style="float:left">支付手续费</label>
                <input name="weakfee" type="text" class="dfinput" style="float:left"/>
            </div></li>
            <li><label style="float:left">本月销量</label>
            <div style="border:1px solid #b8dcff;float:left;"><label style="float:left">本月成交额</label>
            	<input name="monthsale" type="text" class="dfinput" style="float:left"/>
            </div></li>
            <li><label style="float:left">本年销量</label>
            <div style="border:1px solid #b8dcff;float:left;"><label style="float:left">本年成交额</label>
            	<input name="yearsale" type="text" class="dfinput" style="float:left"/>
            </div></li>
        </ul>
        </form>
    </div>
</body>
</html>
