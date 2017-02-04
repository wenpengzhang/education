

function orderingmanager() {

    var modelIds = ""; //当前选中记录ID

    var pageIndex = 1; //当前显示的页码

    var pageCount = 3; //总页数

    var listhref = "content/orderinglist.jsp";
    
    var modelhref = "content/orderingform.jsp";

    //添加数据
    function addModel() {

        var options = {
            url: "ordering/setOrder", //要访问的后台地址
            cache: false,
            type: 'post',
            dataType: "json",
            data: { "Method": "Add" },
            success: function (response) {
                //返回结果验证
                if (response.success) {
                    alertify.alert(response.message);
                    replace(listhref)
                } else {
                    alertify.alert(response.message);
                }
            }
        };
        $("#form1").ajaxSubmit(options); //提交表单
    };

    //删除数据
    function deleteModel(sid) {

        if (sid == null) {
            return false;
        }
        //confirm dialog 
        alertify.confirm("记录删除后将无法恢复，您确定要删除该记录？", function (jreturn) {
            if (jreturn) {
                $.ajax({
                    url: "ordering/delete", //要访问的后台地址
                    cache: false,
                    type: "POST", //使用get方法访问后台
                    dataType: "json",   //返回数据类型
                    data: { "Method": "Delete", "id": sid },
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

    //验证所有输入并提交form
    function submitModel() {
        if ($("#form1").valid()) {
        	if (modelIds == "") {
                addModel();
            } else {
                updateModel(modelIds);
            }
        }
    };
    
    function loadStudents() {

        $("#sltstudent").empty();
        $("#sltstudent").append($("<option>").text("请选择").val(""));
        $.ajax({
            cache: false,
            type: "GET",
            url: "student/getAllList",
            data: { "Method": "select" },
            dataType: "json",
            success: function (data) {
                $(data.records).each(function () {

                    for (var i = 0; i < $(this).length; i++) {
                        var option = $("<option>").text($(this)[i].realname).val($(this)[i].id);
                        $("#sltstudent").append(option);
                    }
                })
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown);
            }
        });
    }
    
    function loadCourses() {

        $("#sltcourse").empty();
        $("#sltcourse").append($("<option>").text("请选择").val(""));
        $.ajax({
            cache: false,
            type: "GET",
            url: "course/getAllList",
            data: { "Method": "select"},
            dataType: "json",
            success: function (data) {
                $(data.records).each(function () {
                    for (var i = 0; i < $(this).length; i++) {
                        var option = $("<option>").text($(this)[i].name).val($(this)[i].id);
                        $("#sltcourse").append(option);
                    }
                })
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown);
            }
        });
    }
    
  //根据ID加载对象
    function showCourseModel(sid) {
        if (sid == null) {
            return false;
        }
        $.ajax({
            cache: false,
            type: "GET", //使用get方法访问后台
            url: "course/show", //要访问的后台地址
            dataType: "json",   //返回数据类型
            data: { "Method": "Show", "id": sid },
            success: function (response) {//response为返回的数据，在这里做数据绑定
                if (response.data != null) {
                    $.each(response.data, function (skey, svalue) {
                        var sinput = $("input[name='" + skey + "']")
                        if (sinput != null) {
                            sinput.val(svalue);
                        }
                    });
                }
            } //返回成功完成
        });
    }; //加载对象结束
    
    //加载数据到列表
    function loadModels() {
        $("[id=ready]").remove();
        var params = getAjaxParams();
        $.ajax({
            url: "ordering/getListbyPage", //要访问的后台地址
            type: "POST", //使用get方法访问后台
            cache: false,
            dataType: "json",
            data: params,
            success: function (response) {//msg为返回的数据，在这里做数据绑定
                if (response.records != null) {
                    //遍历数组添加数据
                    var records = response.records;
                    $.each(records, function (i, n) {
                        var row = $("#template").clone();
                        var chkselect = "<input name='id' type='checkbox' value='" + n.id + "'/>"
                        row.find("#id").append(chkselect);
                        row.find("#ordercode").text(n.ordercode);
                        row.find("#studentmobile").text(n.studentmobile);
                        row.find("#teacherid").text(n.teacherid);
                        row.find("#coursename").text(n.coursename);
                        row.find("#vsubject").text(n.vsubject);
                        row.find("#vsubject").text(n.vsubject);
                        row.find("#cmode").text(n.cmode);
                        row.find("#summoney").text(n.summoney);
                        var date = timeStamp2String(n.ordertime.toString())
                        row.find("#ordertime").text(date);
                        row.find("#progress").text(n.progress+"/"+n.sumprogress);
                        //row.find("#pstate").text(n.pstate);
                        row.attr("id", "ready"); //改变绑定好数据的行的id
                        row.appendTo("#datas"); //添加到模板的容器中
                    });
                    $("#lblrcdcount").text(response.total);
                    pageCount = response.totalpage;
                }
            } //返回成功完成
        });
    };
    
    //获取参数
    function getAjaxParams() {
        var skeyword = $("#txtword").val();
        var skeytjr = $("#txttjr").val();
        var data = { "Method": "list", "keyword": skeyword,"skeytjr":skeytjr,"pageindex": pageIndex};
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

    	 //添加按钮click事件
        $("#lbtnadd").click(function () {
            modelIds = "";
            replace(modelhref);
        });

        //删除按钮click事件
        $("#lbtndelete").click(function () {
            var ids = getSelectIds();
            if (ids == "") {
                alertify.success("请将需要删除的记录选中...");
            } else {
                deleteModel(ids);
            }
        });

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


    //绑定form页面的按钮事件
    this.bindForm = function () {
    
      //绑定form验证
        $("#form1").validate({
            rules: {
                studentid: {
                    required: true
                },
                courseid: {
                    required: true
                }
            },
            messages: {
            	studentid: {
                    required: "学生不能为空"
                },
                courseid: {
                    required: "课程不能为空"
                }
            }
        });
        
        loadStudents();
        
        loadCourses();
        
        $('#sltcourse').change(function(){ 
        	var courseid=$(this).children('option:selected').val();//这就是selected的值 
        	showCourseModel(courseid);
        })

        $("#btnsubmit").click(function () {
            submitModel();
        });
        
        if (modelIds != "") {
            showModel(modelIds);
        }
    }
}