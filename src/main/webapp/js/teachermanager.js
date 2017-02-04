

function teachermanager() {
	
	var operater="";

    var modelIds = ""; //当前选中记录ID
    
    var subindex = 0;
    
    var subcount = 0;

    var pageIndex = 1; //当前显示的页码

    var pageCount = 0; //总页数

    var listhref = "content/teacherlist.jsp";

    var modelhref = "content/teacherform.jsp";

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
    	$("#lisetsubject").hide();

    	$("#btnlist").removeAttr("disabled");
    	$("#btnlist").show();
    }
    
    //展示数据
    function showModel(sid) {
        if (sid == null) {
            return false;
        }
        $.ajax({
            cache: false,
            type: "GET", //使用get方法访问后台
            url: "teacher/show", //要访问的后台地址
            dataType: "json",   //返回数据类型
            data: { "Method": "Show", "id": sid },
            success: function (response) {//response为返回的数据，在这里做数据绑定
                if (response.data != null) {
                	$.each(response.data.experience,function(n,value){
                		addexp(value);
                    });
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
                        var stextarea = $("textarea[name='" + skey + "']");
                        if (stextarea != null) {
                        	stextarea.val(svalue);
                        };
                        if(skey=="birthday"&&svalue!=""){
                        	$("#datebirthday").val(timeStamp2String(svalue))
                        }
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
            url: "teacher/add", //要访问的后台地址
            cache: false,
            type: 'post',
            dataType: "json",   //返回数据类型
            data: { "Method": "Add","subindex":subindex,"subcount":subcount },
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
            url: "teacher/edit", //要访问的后台地址
            cache: false,
            type: 'post',
            dataType: "json",   //返回数据类型
            data: { "Method": "Update","subindex":subindex,"subcount":subcount },
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
    
    //发布数据
    function publishModel(sid) {
    	$.ajax({
            url: "teacher/publish", //要访问的后台地址
            cache: false,
            type: "POST", //使用get方法访问后台
            dataType: "json",   //返回数据类型
            data: { "Method": "public", "id": sid },
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
    };
    
    //置顶数据
    function topModel(sid) {
    	$.ajax({
            url: "teacher/top", //要访问的后台地址
            cache: false,
            type: "POST", //使用get方法访问后台
            dataType: "json",   //返回数据类型
            data: { "Method": "Top", "id": sid },
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
                    url: "teacher/delete", //要访问的后台地址
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
                    sqlrange: true
                },
                seniority: {
                	digits: true
                },
            },
            messages: {
            	realname: {
                    required: "名称不能为空",
                    sqlrange: "名称含有非法字符",
                    rangelength: $.validator.format("级别名称必须介于{0}-{1}个字符")
                },
                mobile: {
                	required: "手机号码不能为空",
                    sqlrange: "手机号码描述含有非法字符"
                },
                seniority: {
                	digits: "教龄只能输入整数"
                },
            }
        });
        
        $("#datebirthday").datepicker();
        
        $("#filehead").uploadPreview({ Img: "imghead", Width: 200, Height: 160, ImgType: ["gif", "jpeg", "jpg", "bmp", "png"], Callback: function () { }});
        $("#fileidcord").uploadPreview({ Img: "imgidcard", Width: 200, Height: 160, ImgType: ["gif", "jpeg", "jpg", "bmp", "png"], Callback: function () { }});
        $("#fileedu").uploadPreview({ Img: "imgeducation", Width: 200, Height: 160, ImgType: ["gif", "jpeg", "jpg", "bmp", "png"], Callback: function () { }});
        $("#fileteacher").uploadPreview({ Img: "imgteacher", Width: 200, Height: 160, ImgType: ["gif", "jpeg", "jpg", "bmp", "png"], Callback: function () { }});
        $("#fileother").uploadPreview({ Img: "imgother", Width: 200, Height: 160, ImgType: ["gif", "jpeg", "jpg", "bmp", "png"], Callback: function () { }});

        $("#btnsubmit").click(function () {
            submitModel();
        });
        
        $("#btnlist").click(function () {
        	 replace(listhref);
        });
        
        $("#btnaddexp").click(function () {
        	addexp();
        });
        
        $("#btnaddsub").click(function () {
        	var subject = $("#txtsubject").attr("value");
        	var newsubject=$("#sltgrade").attr("value")+"_"+$("#sltsubject").attr("value");
        	if(subject.indexOf(newsubject)<0){
        		if(subject==""){
        			 $("#txtsubject").val(newsubject);
        		}else{
        			 $("#txtsubject").val(subject+","+newsubject);
        		}
        	}//添加对象
        });
        
        $("#btnclearsub").click(function () {
        	 $("#txtsubject").val("");
        });
        
        setProvinceList("", null);
        
        if(operater=="show"){
        	showModel(modelIds);
        }else if(operater=="edit"){
        	showModel(modelIds);
        }
    }
    
    function addexp(experience){
    	var txtcontent='<div style="float:left"><label>时间阶段：<b>*</b></label><input  name="stage'+subindex+'" type="text" class="dfinput"/></div>';
    	txtcontent=txtcontent+'<div style="float:left"><label>供职学校：<b>*</b></label><input  name="school'+subindex+'" type="text" class="dfinput"/></div>';
    	txtcontent=txtcontent+'<div><input type="button" value="删除" id="btndltexp_'+subindex+'" class="btn"/>';
    	$("#divnew").append('<div id="divexp_'+subindex+'" style="float:left;width:450px">'+txtcontent+'</div>');
    	
    	if(experience!=null){
    		$("input[name=stage" + subindex + "]").val(experience.stage);
        	$("input[name=school" + subindex + "]").val(experience.school);
    	}
    	
    	$("input[name=stage" + subindex + "]").rules("add", {required: true, messages: {required: "请输入时间段"} });
    	$("input[name=school" + subindex + "]").rules("add", {required: true, messages: {required: "请输入供职学校"} });
    	
    	$("#btndltexp_"+subindex).on("click",function(){
			var str = this.id.split("_");
			deleteexp(str[1]);
		})
		
		subindex = subindex + 1;
		subcount =subcount+1;
    }
    
    function deleteexp(index){
    	$("#divexp_"+index).remove();
    	subcount =subcount-1;
    }

    
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

    //加载数据到列表
    function loadModels() {
        $("[id=ready]").remove();
        var params = getAjaxParams();
        $.ajax({
            url: "teacher/getListbyPage", //要访问的后台地址
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
                        row.find("#realname").text(n.realname);
                        row.find("#mobile").text(n.mobile);
                        row.find("#stype").text(n.stype);
                        row.find("#school").text(n.school);
                        row.find("#subject").text(n.subject);
                        row.find("#published").text(n.published);
                        row.find("#showtop").text(n.showtop);
                        if(n.statOrder!=null){
                        	row.find("#ordercount").text(n.statOrder.ordercount);
                        	row.find("#summoney").text(n.statOrder.summoney);
                        }else{
                        	row.find("#ordercount").text("0");
                        	row.find("#summoney").text("0");
                        }
                        row.attr("id", "ready"); //改变绑定好数据的行的id
                        row.appendTo("#datas"); //添加到模板的容器中
                    });
                    
                    $("#lblrcdcount").text(response.total);
                    pageCount = response.totalpage;
                }
            } //返回成功完成
        });
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
                operater="edit";
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
        
       //发布按钮click事件
        $("#lbtnpublish").click(function () {
            var ids = getSelectIds();
            if (ids == "") {
                alertify.success("请将需要发布/隐藏的记录选中...");
            } else {
                publishModel(ids);
            }
        });
        
        
       //置顶按钮click事件
        $("#lbtntop").click(function () {
            var ids = getSelectIds();
            if (ids == "") {
                alertify.success("请将需要置顶的记录选中...");
            } else {
                topModel(ids);
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
        
        subindex = 0;
        
        subcount = 0;
        
        loadModels();
    };
}

