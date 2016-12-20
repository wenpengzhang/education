<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>学生管理</title>
    <script type="text/javascript">
        $(document).ready(function () {
        	student.bindList();
        });
    </script>
</head>
<body>
    <div>
        <div class="place">
            <span>位置：</span>
            <ul class="placeul">
                <li><a href="#">首页</a></li>
                <li><a href="#">学生管理</a></li>
                <li><a href="#">学生列表</a></li>
            </ul>
        </div>
        <div class="rightinfo">
            <div class="tools">
                <ul class="toolbar">
                 	<li id="lbtnlook" class="click"><span>
                        <img src="images/m03.png" alt="查看" /></span>查看</li>
                    <li id="lbtnadd" class="click"><span>
                        <img src="images/t01.png" alt="添加" /></span>添加</li>
                    <li id="lbtnedit" class="click"><span>
                        <img src="images/t02.png" alt="修改" /></span>修改</li>
                    <li id="lbtndelete"><span>
                        <img src="images/t03.png" alt="删除" /></span>删除</li>
                </ul>
                <ul class="toolbar1">
                    <li>
                        <label>查询关键字</label>
                        <input id="txtword" type="text" class="scinput" /></li>
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
                           	学院帐号
                        </th>
                        <th>
                           	学员姓名
                        </th>
                        <th>
                         	昵    称
                        </th>
                        <th>
                         	联系电话
                        </th>
                        <th>
                           	家长电话
                        </th>
                        <th>
                           	所在学校
                        </th>
                        <th>
                          	 所在年级
                        </th>
                        <th>
                           	所在地区
                        </th>
                        <th>
                         	订单数
                        </th>
                         <th>
                           	完成课时数
                        </th>
                         <th>
                           	消费金额
                        </th>
                    </tr>
                </thead>
                <tbody id="datas">
                    <tr id="template">
                        <td id="id">
                        </td>
                        <td id="accounts">
                        </td>
                        <td id="realname">
                        </td>
                        <td id="nickname">
                        </td>
                        <td id="mobile">
                        </td>
                        <td id="mobileother">
                        </td>
                        <td id="school">
                        </td>
                        <td id="grade">
                        </td>
                        <td id="address">
                        </td>
                        <td id="ordercount">
                        </td>
                        <td id="progress">
                        </td>
                        <td id="summoney">
                        </td>
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
