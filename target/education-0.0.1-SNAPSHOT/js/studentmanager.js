

function studentmanager() {

    var modelIds = ""; //当前选中记录ID

    var pageIndex = 1; //当前显示的页码

    var pageCount = 0; //总页数

    var listhref = "content/studentlist.jsp";

    var modelhref = "content/studentform.jsp";

    function setInputEnble(){
    	$("input").each(function(){
    		$(this).removeAttr("disabled");
    	});
    }
    
    function setInputDisable(){
    	$("input").each(function(){
    		$(this).attr("disabled", true);
    	});
    	$("select").each(function(){
    		$(this).attr("disabled", true);
    	});
    	$("textarea").each(function(){
    		$(this).attr("disabled", true);
    	});
    	$("input[type=button]").each(function(){
    		$(this).hide();
    	});
    	$("#liupload").hide();
    	$("#lisetaddress").hide();

    	$("#btnlist").removeAttr("disabled");
    	$("#btnlist").show();
    }
    
    //根据ID加载对象
    function showModel(sid) {
        if (sid == null) {
            return false;
        }
        $.ajax({
            cache: false,
            type: "GET", //使用get方法访问后台
            url: "student/show", //要访问的后台地址
            dataType: "json",   //返回数据类型
            data: { "Method": "Show", "id": sid },
            success: function (response) {//response为返回的数据，在这里做数据绑定
                if (response.data != null) {
                    $.each(response.data, function (skey, svalue) {
                    	var sinput = $("input[name='" + skey + "']")
                        if (sinput != null) {
                            sinput.val(svalue);
                        };
                        var simg = $("img[name='" + skey + "']");
                        if (simg != null) {
                        	simg.attr('src',svalue);
                        };
                        var sselect = $("select[name='" + skey + "']");
                        if (sselect != null) {
                        	sselect.val(svalue);
                        };
                    });
                }
                if(operater=="show"){
                    setInputDisable();
                }
            } //返回成功完成
        });
    }; //加载对象结束

    //添加数据
    function addModel() {

        var options = {
            url: "student/add", //要访问的后台地址
            cache: false,
            type: 'post',
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

    //编辑数据
    function updateModel(sid) {
        var options = {
            url: "student/edit", //要访问的后台地址
            cache: false,
            type: 'post',
            dataType: "json",
            data: { "Method": "Update" },
            success: function (response) {
                //返回结果验证
                if (response.success) {
                    alertify.alert(response.message);
                    replace(listhref);

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
                    url: "student/delete", //要访问的后台地址
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
    		 if (operater == "add") {
			     addModel();
			 } else if (operater == "edit") {
			     updateModel(modelIds);
			 }
        }
    };

    //加载数据到列表
    function loadModels() {
        $("[id=ready]").remove();
        var params = getAjaxParams();
        $.ajax({
            url: "student/getListbyPage", //要访问的后台地址
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
                        row.find("#accounts").text(n.accounts);
                        row.find("#realname").text(n.realname);
                        row.find("#nickname").text(n.nickname);
                        row.find("#mobile").text(n.mobile);
                        row.find("#mobileother").text(n.mobileother);
                        row.find("#school").text(n.school);
                        row.find("#grade").text(n.grade);
                        row.find("#address").text(n.address);
                        if(n.statOrder!=null){
                        	row.find("#ordercount").text(n.statOrder.ordercount);
                        	row.find("#progress").text(n.statOrder.progress+"/"+n.statOrder.sumprogress);
                        	row.find("#summoney").text(n.statOrder.summoney);
                        }else{
                        	row.find("#ordercount").text("0");
                        	row.find("#summoney").text("0");
                        }
                        row.attr("id", "ready"); //改变绑定好数据的行的id
                        row.appendTo("#datas"); //添加到模板的容器中
                    });
                    $("#lblrcdcount").text(response.recordcount);
                }
            } //返回成功完成
        });
    };
    
    //获取参数
    function getAjaxParams() {
        var skeyword = $("#txtword").val();
        var data = { "Method": "list", "keyword": skeyword,"pageindex": pageIndex};
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

    	//编辑按钮click事件
        $("#lbtnlook").click(function () {
            var ids = getSelectIds();
            if (ids == "") {
                alertify.success("请将需要编辑的记录选中...");
            } else if (ids.length > 32) {
                alertify.success("需要编辑的记录只能选中单行，请将多余选中的记录弃选...");
            } else {
                operater="show";
                modelIds = ids;
                replace(modelhref);
            }
        });
        
        //添加按钮click事件
        $("#lbtnadd").click(function () {
            operater="add";
            replace(modelhref);
        });

        //编辑按钮click事件
        $("#lbtnedit").click(function () {
            var ids = getSelectIds();
            if (ids == "") {
                alertify.success("请将需要编辑的记录选中...");
            } else if (ids.length > 32) {
                alertify.success("需要编辑的记录只能选中单行，请将多余选中的记录弃选...");
            } else {
                modelIds = ids;
                replace(modelhref);
            }
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

        //查询按钮click事件
        $("#btnquery").click(function () {
            loadModels();
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
            	realname: {
                    required: true,
                    sqlrange: true,
                    rangelength: [2, 20]
                },
                mobile: {
                	required: true,
                    sqlrange: true,
                    maxlength: 13
                },
                school: {
                	required: true,
                    sqlrange: true,
                    maxlength: 100
                },
                grade: {
                	required: true,
                    sqlrange: true
                }
            },
            messages: {
            	realname: {
                    required: "姓名不能为空",
                    sqlrange: "姓名含有非法字符",
                    rangelength: $.validator.format("级别名称必须介于{0}-{1}个字符")
                },
                mobile: {
                	required: "手机号码不能为空",
                    sqlrange: "手机号码含有非法字符",
                    maxlength: $.validator.format("手机号码不能大于{0}个字符")
                },
                school: {
                	required: "学校不能为空",
                    sqlrange: "学校含有非法字符",
                    maxlength: $.validator.format("学校不能大于{0}个字符")
                },
                grade: {
                	required: "年级不能为空"
                }
            }
        });
        
        setProvinceList("", null);

        $("#btnsubmit").click(function () {
            submitModel();
        });
        
        $("#btnlist").click(function () {
        	replace(listhref);
        });
        
        if(operater=="show"){
        	showModel(modelIds);
        }else if(operater=="edit"){
        	showModel(modelIds);
        }
        
        $("#filehead").uploadPreview({ Img: "imghead", Width: 200, Height: 160, ImgType: ["gif", "jpeg", "jpg", "bmp", "png"], Callback: function () { }});
    }
}