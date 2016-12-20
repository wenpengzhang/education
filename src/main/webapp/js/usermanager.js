

function usermanager() {

    var modelIds = ""; //当前选中记录ID

    var pageIndex = 1; //当前显示的页码

    var pageCount = 0; //总页数

    var listhref = "content/userlist.html";

    var userRolehref = "content/userrolelist.html";

    //解锁数据
    function unlockModel(sid) {
        if (sid == null) {
            return false;
        }
        //confirm dialog 
        alertify.confirm("您确定要解锁锁定这些记录？", function (jreturn) {
            if (jreturn) {
                $.ajax({
                    url: "handlers/sys/useredit.ashx", //要访问的后台地址
                    cache: false,
                    type: "POST", //使用get方法访问后台
                    dataType: "json",   //返回数据类型
                    data: { "Method": "UnLock", "id": sid },
                    success: function (response) {//response为返回的数据，在这里做数据绑定
                        //返回结果验证
                        if (response.success) {
                            alertify.alert(response.message);
                            loadModels();
                        } else {
                            alertify.alert(response.message);
                        }
                    } //返回成功完成
                });
            }
        });
    };

    //锁定数据
    function lockModel(sid) {

        if (sid == null) {
            return false;
        }
        //confirm dialog 
        alertify.confirm("您确定要锁定该记录？", function (jreturn) {
            if (jreturn) {
                $.ajax({
                    url: "handlers/sys/useredit.ashx", //要访问的后台地址
                    cache: false,
                    type: "POST", //使用get方法访问后台
                    dataType: "json",   //返回数据类型
                    data: { "Method": "Lock", "id": sid },
                    success: function (response) {//response为返回的数据，在这里做数据绑定
                        //返回结果验证
                        if (response.success) {
                            alertify.alert(response.message);
                            loadModels();
                        } else {
                            alertify.alert(response.message);
                        }
                    } //返回成功完成
                });
            }
        });
    };


    //重置密码
    function resetModel(sid) {

        if (sid == null) {
            return false;
        }
        //confirm dialog 
        alertify.confirm("密码重置后为111111，您确定要重置用户的密码？", function (jreturn) {
            if (jreturn) {
                $.ajax({
                    url: "handlers/sys/useredit.ashx", //要访问的后台地址
                    cache: false,
                    type: "POST", //使用get方法访问后台
                    dataType: "json",   //返回数据类型
                    data: { "Method": "Reset", "id": sid },
                    success: function (response) {//response为返回的数据，在这里做数据绑定
                        //返回结果验证
                        if (response.success) {
                            alertify.alert(response.message);
                            loadModels();
                        } else {
                            alertify.alert(response.message);
                        }
                    } //返回成功完成
                });
            }
        });
    };

    //激活数据
    function activateModel(sid) {

        if (sid == null) {
            return false;
        }
        //confirm dialog 
        alertify.confirm("您确定要激活该记录？", function (jreturn) {
            if (jreturn) {
                $.ajax({
                    url: "handlers/sys/useredit.ashx", //要访问的后台地址
                    cache: false,
                    type: "POST", //使用get方法访问后台
                    dataType: "json",   //返回数据类型
                    data: { "Method": "Activate", "id": sid },
                    success: function (response) {//response为返回的数据，在这里做数据绑定
                        //返回结果验证
                        if (response.success) {
                            alertify.alert(response.message);
                            loadModels();
                        } else {
                            alertify.alert(response.message);
                        }
                    } //返回成功完成
                });
            }
        });
    };

    //加载数据到列表
    function loadModels() {
        $("[id=ready]").remove();
        var params = getAjaxParams("list");
        $.ajax({
            url: "handlers/sys/userlist.ashx", //要访问的后台地址
            type: "GET", //使用get方法访问后台
            cache: false,
            dataType: "json",
            data: params,
            success: function (response) {//msg为返回的数据，在这里做数据绑定
                if (response.records != null) {
                    //遍历数组添加数据
                    var records = response.records;
                    $.each(records, function (i, n) {
                        var row = $("#template").clone();
                        var chkselect = "<input name='id' type='checkbox' value='" + n.id + "'/>";
                        row.find("#id").append(chkselect);
                        row.find("#username").text(n.username);
                        row.find("#classname").text(n.classname);
                        row.find("#name").text(n.name);
                        row.find("#phono").text(n.phono);
                        row.find("#email").text(n.email);
                        row.find("#major").text(n.major);
                        row.find("#rolename").text(n.rolename);
                        row.find("#lockstate").text(n.lockstate);
                        var date = timeStamp2String(n.currentdate)
                        row.find("#currentdate").text(date);
                        row.attr("id", "ready"); //改变绑定好数据的行的id
                        row.appendTo("#datas"); //添加到模板的容器中
                    });
                    $("#lblrcdcount").text(response.recordcount);
                    $("#lblpagecount").text(response.pagecount);
                    pageCount = response.pagecount;
                }
            } //返回成功完成
        });
    };

    //加载数据到列表
    function loadRecommendModels() {
        $("[id=ready]").remove();
        var params = getAjaxParams("recomendlist");
        $.ajax({
            url: "handlers/sys/userlist.ashx", //要访问的后台地址
            type: "GET", //使用get方法访问后台
            cache: false,
            dataType: "json",
            data: params,
            success: function (response) {//msg为返回的数据，在这里做数据绑定
                if (response.records != null) {
                    //遍历数组添加数据
                    var records = response.records;
                    $.each(records, function (i, n) {
                        var row = $("#template").clone();
                        var chkselect = "<input name='id' type='checkbox' value='" + n.id + "'/>";
                        row.find("#id").append(chkselect);
                        row.find("#username").text(n.username);
                        row.find("#classname").text(n.classname);
                        row.find("#name").text(n.name);
                        row.find("#phono").text(n.phono);
                        row.find("#email").text(n.email);
                        row.find("#major").text(n.major);
                        row.find("#lockstate").text(n.lockstate);
                        row.attr("id", "ready"); //改变绑定好数据的行的id
                        row.appendTo("#datas"); //添加到模板的容器中
                    });
                    $("#lblrcdcount").text(response.recordcount);
                    $("#lblpagecount").text(response.pagecount);
                    pageCount = response.pagecount;
                }
            } //返回成功完成
        });
    };

    function loadUserRole() {
        $("[id=ready]").remove();
        $.ajax({
            url: "handlers/sys/userlist.ashx", //要访问的后台地址
            type: "GET", //使用get方法访问后台
            cache: false,
            dataType: "json",
            data: { "Method": "userRole", "id": modelIds },
            success: function (response) {//msg为返回的数据，在这里做数据绑定
                if (response.records != null) {
                    //遍历数组添加数据
                    var records = response.records;
                    $.each(records, function (i, n) {
                        var row = $("#template").clone();
                        var check = "";
                        if (response.userids != null && response.userids.indexOf(n.id) >= 0) {
                            check = "checked='checked'";
                        }
                        var chkselect = "<input name='id' type='checkbox' value='" + n.id + "' " + check + "/>";
                        row.find("#id").append(chkselect);
                        row.find("#name").text(n.name);
                        row.find("#descp").text(n.descp);
                        row.attr("id", "ready"); //改变绑定好数据的行的id
                        row.appendTo("#datas"); //添加到模板的容器中
                    });
                }
            } //返回成功完成
        });
    }

    function submitUserRole() {
        $.ajax({
            url: "handlers/sys/useredit.ashx", //要访问的后台地址
            type: "GET", //使用get方法访问后台
            cache: false,
            dataType: "json",
            data: { "Method": "userRole", "id": modelIds, "rids": getSelectIds() },
            success: function (response) {//msg为返回的数据，在这里做数据绑定
                //返回结果验证
                if (response.success) {
                    alertify.alert(response.message);
                    replace(listhref);
                } else {
                    alertify.alert(response.message);
                }
            } //返回成功完成
        });
    }

    //获取参数
    function getAjaxParams(method) {
        var skeyword = $("#txtword").val();
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
        var data = { "Method": method, "pageindex": pageIndex, "keyword": skeyword, "startDate": startDate, "endDate": endDate };
        return data;
    };

    function getSelectIds() {
        var ids = '';
        var chkIds = $("input[name='id']:checked");
        $.each(chkIds, function (i, item) {
            if (ids != "") {
                ids = ids + "," + chkIds[i].value;
            } else {
                ids = chkIds[i].value;
            }
        });
        return ids;
    };

    //绑定list页面的按钮事件
    this.bindList = function () {

        $("#startDate").datepicker();
        $("#endDate").datepicker();

        //第一页按钮click事件
        $("#btnfirst").click(function () {
            pageIndex = 1;
            //changePageState(pageIndex);
            $("#txtpage").val(pageIndex);
            loadModels();
        });

        //最后一页按钮click事件
        $("#btnlast").click(function () {
            pageIndex = pageCount;
            //changePageState(pageIndex);
            $("#txtpage").val(pageIndex);
            loadModels();
        });

        //上一页按钮click事件
        $("#btnprevious").click(function () {
            pageIndex -= 1;
            if (pageIndex <= 1) {
                pageIndex = 1;
            }
            $("#txtpage").val(pageIndex);
            loadModels();
        });

        //下一页按钮click事件
        $("#btnnext").click(function () {
            pageIndex += 1;
            if (pageIndex >= pageCount) {
                pageIndex = pageCount;
            }
            $("#txtpage").val(pageIndex);
            loadModels();
        });

        //锁定按钮click事件
        $("#lbtnlock").click(function () {
            var ids = getSelectIds();
            if (ids == "") {
                alertify.success("请将需要锁定的用户选中...");
            } else {
                lockModel(ids);
            }
        });

        //解锁按钮click事件
        $("#lbtnunlock").click(function () {
            var ids = getSelectIds();
            if (ids == "") {
                alertify.success("请将需要解锁的用户选中...");
            } else {
                unlockModel(ids);
            }
        });

        //解锁按钮click事件
        $("#lbtnreset").click(function () {
            var ids = getSelectIds();
            if (ids == "") {
                alertify.success("请将需要重置密码的用户选中...");
            } else {
                resetModel(ids);
            }
        });

        //分配角色按钮click事件
        $("#lbtnpara").click(function () {
            var ids = getSelectIds();
            if (ids == "") {
                alertify.success("请将需要编辑的记录选中...");
            } else if (ids.length > 32) {
                alertify.success("需要编辑的记录只能选中单行，请将多余选中的记录弃选...");
            } else {
                modelIds = ids;
                replace(userRolehref);
            }
        });

        //查询按钮click事件
        $("#btnquery").click(function () {
            loadModels();
        });

        //设置全选click事件
        $("#checkall").bind("click", function () {
            $("[name = id]:checkbox").attr("checked", this.checked);
        });

        $("#txtpage").val(pageIndex);
        loadModels();
    };

    //绑定list页面的按钮事件
    this.bindRecommendList = function () {

        $("#startDate").datepicker();
        $("#endDate").datepicker();

        //第一页按钮click事件
        $("#btnfirst").click(function () {
            pageIndex = 1;
            //changePageState(pageIndex);
            $("#txtpage").val(pageIndex);
            loadRecommendModels();
        });

        //最后一页按钮click事件
        $("#btnlast").click(function () {
            pageIndex = pageCount;
            //changePageState(pageIndex);
            $("#txtpage").val(pageIndex);
            loadRecommendModels();
        });

        //上一页按钮click事件
        $("#btnprevious").click(function () {
            pageIndex -= 1;
            if (pageIndex <= 1) {
                pageIndex = 1;
            }
            $("#txtpage").val(pageIndex);
            loadRecommendModels();
        });

        //下一页按钮click事件
        $("#btnnext").click(function () {
            pageIndex += 1;
            if (pageIndex >= pageCount) {
                pageIndex = pageCount;
            }
            $("#txtpage").val(pageIndex);
            loadRecommendModels();
        });

        //查询按钮click事件
        $("#btnquery").click(function () {
            loadRecommendModels();
        });

        $("#txtpage").val(pageIndex);
        loadRecommendModels();
    };

    this.bindUserRole = function () {

        $("#btnOK").click(function () {
            var ids = getSelectIds();
            if (ids == "") {
                alertify.success("请选择要分配给用户的角色...");
//            } else if (ids.length > 32) {
//                alertify.success("只能给用户分配一种角色，请选择一条记录...");
            }else{
                submitUserRole();
            }
        });

        //设置全选click事件
        $("#checkall").bind("click", function () {
            $("[name = id]:checkbox").attr("checked", this.checked);
        });

        loadUserRole();
    }
}