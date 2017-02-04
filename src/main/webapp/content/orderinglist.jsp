<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>订单管理</title>
    <script type="text/javascript">
        $(document).ready(function () {
        	ordering.bindList();
        });
    </script>
</head>
<body>
    <div>
        <div class="place">
            <span>位置：</span>
            <ul class="placeul">
                <li><a href="#">首页</a></li>
                <li><a href="#">订单管理</a></li>
                <li><a href="#">订单列表</a></li>
            </ul>
        </div>
        <div class="rightinfo">
            <div class="tools">
               <ul class="toolbar">
                    <li id="lbtnadd" class="click"><span>
                        <!--<img src="images/t01.png" alt="添加" />--></span>下单</li>
                </ul>
                <ul class="toolbar1">
                    <li>
                        <label>查询关键字</label>
                        <input id="txtword" type="text" class="scinput" /></li>
                         <li><label>推荐人</label>
                        <input id="txttjr" type="text" class="scinput" /></li>
                    <li id="btnquery" class="click"><span>
                        <img src="images/t05.png" alt="查询" /></span>查询</li>
                </ul>
            </div>
            <table class="tablelist">
                <thead>
                    <tr>
                        <th style="width: 75px">
                            <input id='checkall' type="checkbox" />
                        </th>
                        <th>
                           	订单编号
                        </th>
                        <th>
                           	学员手机号
                        </th>
                        <th>
                         	教师姓名
                        </th>
                        <th>
                           	课程名称
                        </th>
                         <th>
                          	课程科目
                        </th>
                        <th>
                          	授课模式
                        </th>
                        <th>
                         	总价
                        </th>
                        <th>
                           	进度
                        </th>
                         <th>
                           	订单时间
                        </th>
                         <!-- <th>
                           	订单状态
                        </th> -->
                    </tr>
                </thead>
                <tbody id="datas">
                    <tr id="template">
                        <td id="id">
                        </td>
                        <td id="ordercode">
                        </td>
                        <td id="studentmobile">
                        </td>
                        <td id="teacherid">
                        </td>
                        <td id="coursename">
                        </td>
                        <td id="vsubject">
                        </td>
                        <td id="cmode">
                        </td>
                        <td id="summoney">
                        </td>
                        <td id="progress">
                        </td>
                        <td id="ordertime">
                        </td>
                       <!--  -->  
                    </tr>
                </tbody>
            </table>
            <div class="pagin">
                <div class="message">
                    共<i id="lblrcdcount" class="blue">0</i>条记录，共&nbsp;<i id="lblpagecount" class="blue">1&nbsp;</i>页</div>
                <ul class="paginList">
                    <li id="btnfirst" class="paginItem"><a><span>首页</span></a></li>
                    <li id="btnprevious" class="paginItem"><a><span>上一页</span></a></li>
                    <li class="paginItem"><a>
                        <input id="txtpage" type="text" class="scinput" style="width: 45px; height: 25px" /></a></li>
                    <li id="btnnext" class="paginItem"><a><span>下一页</span></a></li>
                    <li id="btnlast" class="paginItem"><a><span>尾页</span></a></li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>