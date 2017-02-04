

function coursemanager() {

	var operater="";
	
    var modelIds = ""; //当前选中记录ID
    
    var subindex = 0;
    
    var subcount = 0;
    
    var photoindex = 0;
    
    var photocount = 0;
    
    var pageIndex = 1; //当前显示的页码

    var pageCount = 0; //总页数

    var listhref = "content/courselist.jsp";

    var modelhref = "content/courseform.jsp";

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
            url: "course/show", //要访问的后台地址
            dataType: "json",   //返回数据类型
            data: { "Method": "Show", "id": sid},
            success: function (response) {//response为返回的数据，在这里做数据绑定
                if (response.data != null) {
                	
                    $.each(response.data, function (skey, svalue) {
                        var sinput = $("input[name='" + skey + "']")
                        if (sinput != null) {
                            sinput.val(svalue);
                        }
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
                    });
                    $.each(response.data.subCourse,function(n,value){
                     	addsubcourse(value);
                    });
                  	$.each(response.data.piclist,function(n,value){
                		var imgcontent='<img id="imghead'+photoindex+'" name="pichead" src="'+value+'" height="200" width="200" />';
                    	$("#divimg").append('<div id="divimg_'+photoindex+'" style="border:1px solid #b8dcff;float:left;">'+imgcontent+'</div>');
                    	photoindex++
                    });
                    if(operater=="show"){
                        setInputDisable();
                    }
                }
            } //返回成功完成
        });
    }; //加载对象结束

    //添加数据
    function addModel() {

        var options = {
            url: "course/add", //要访问的后台地址
            cache: false,
            type: 'post',
            dataType: "json",
            data: { "Method": "Add","subindex":subindex,"subcount":subcount},
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
            url: "course/edit", //要访问的后台地址
            cache: false,
            type: 'post',
            dataType: "json",
            data: { "Method": "Update","subindex":subindex,"subcount":subcount  },
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
                    url: "course/delete", //要访问的后台地址
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
    
    //发布数据
    function publishModel(sid) {
    	$.ajax({
            url: "course/publish", //要访问的后台地址
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
            url: "course/top", //要访问的后台地址
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

    //验证所有输入并提交form
    function submitModel() {
    	if (subcount<=0) {
            alertify.success("请添加课程大纲...");
        } else if ($("#form1").valid()) {
        	if (operater == "add") {
			     addModel();
			 } else if (operater == "edit") {
			     updateModel(modelIds);
			 }
        }
    };
    
    function loadTeachers(sltteacher,teacherid) {
    	
        $("#"+sltteacher).empty();
        $("#"+sltteacher).append($("<option>").text("请选择").val(""));
        $.ajax({
            cache: false,
            type: "GET",
            url: "teacher/getAlllist",
            data: { "Method": "select" },
            dataType: "json",
            success: function (data) {
                $(data.records).each(function () {

                    for (var i = 0; i < $(this).length; i++) {
                        var option = $("<option>").text($(this)[i].realname).val($(this)[i].id);
                        $("#"+sltteacher).append(option);
                    }
                });
                if (teacherid != null) {

                    for (var i = 0; i < $("#"+sltteacher).get(0).options.length; i++) {
                        if ($("#"+sltteacher).get(0).options[i].value == teacherid) {
                            $("#"+sltteacher).get(0).options[i].selected = true;
                            break;
                        }
                    }
                }
            	$("#"+sltteacher).comboSelect();
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown);
            }
        });
    }

    //加载数据到列表
    function loadModels() {
        $("[id=ready]").remove();
        var params = getAjaxParams();
        $.ajax({
            url: "course/getListbyPage", //要访问的后台地址
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
                        row.find("#name").text(n.name);
                        row.find("#teacherid").text(n.teacherid);
                        row.find("#vstage").text(n.vstage);
                        row.find("#vsubject").text(n.vsubject);
                        row.find("#vgrade").text(n.vgrade);
                        row.find("#cmode").text(n.cmode);
                        row.find("#firsttype").text(n.firsttype);
                        row.find("#price").text(n.price);
                        row.find("#progress").text(n.subnumber+"/"+n.subcount);
                        row.find("#ccount").text(n.cnumber+"/"+n.ccount);
                        row.find("#published").text(n.published);
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
            modelIds = "";
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
        
        subindex = 0;
        
        subcount = 0;

        $("#txtpage").val(pageIndex);

        loadModels();
    };


    //绑定form页面的按钮事件
    this.bindForm = function () {
    	//绑定form验证
        $("#form1").validate({
            rules: {
                name: {
                    required: true,
                    sqlrange: true,
                    rangelength: [2, 20]
                },
                teacherid: {
                	required: true,
                },
                price: {
                    required: true,
                    number: true,
                    range: [0, 9999999999.99]
                },
                ccount: {
                    required: true,
                    number: true,
                    range: [0, 9999999999.99]
                }
            },
            messages: {
                name: {
                    required: "名称不能为空",
                    sqlrange: "名称含有非法字符",
                    rangelength: $.validator.format("级别名称必须介于{0}-{1}个字符")
                },
                teacherid: {
                	required: "开课教师不能为空"
                },
                price: {
                    required: "价格不能为空",
                    number: "请输入有效数字",
                    range: $.validator.format("价格必须在{0}-{1}之间")
                },
                ccount: {
                    required: "总数不能为空",
                    number: "请输入有效数字",
                    range: $.validator.format("总数必须在{0}-{1}之间")
                }
            }
        });

        $("#btnsubmit").click(function () {
            submitModel();
        });
        
        $("#btnaddsub").click(function () {
        	addsubcourse(null);
        });
        
        $("#btnaddfile").click(function () {
        	addphoto("");
        });
        
        $("#btnlist").click(function () {
       	   replace(listhref);
        });
        
        if(operater=="show"||operater=="edit"){
        	subindex=0;
        	subcount=0;
        	showModel(modelIds);
        }
        
        $("#filehead").uploadPreview({ Img: "imghead", Width: 200, Height: 160, ImgType: ["gif", "jpeg", "jpg", "bmp", "png"], Callback: function () { }});
    }
    
    function addphoto(path){
    	
    	var filecontent='<input id="filehead'+photoindex+'" name="file'+photoindex+'" type="file" class="dfinput"/>';
    	filecontent=filecontent+'<input type="button" value="删除" id="btndeletefile_'+photoindex+'" class="btn"/>';
    	var imgcontent='<img id="imghead'+photoindex+'" name="pichead" src="'+path+'" height="200" width="200" />';
    	$("#divfile").append('<div id="divfile_'+photoindex+'" style="border:1px solid #b8dcff;float:left;">'+filecontent+'</div>');
    	$("#divimg").append('<div id="divimg_'+photoindex+'" style="border:1px solid #b8dcff;float:left;">'+imgcontent+'</div>');
    	$("#filehead"+photoindex).uploadPreview({ Img: "imghead"+photoindex, Width: 200, Height: 160, ImgType: ["gif", "jpeg", "jpg", "bmp", "png"], Callback: function () { }});

    	
    	$("#btndeletefile_"+photoindex).on("click",function(){
			var str = this.id.split("_");
			deletephoto(str[1]);
		})
		
    	photoindex++;
    }
    
    function deletephoto(index){
    	$("#divfile_"+index).remove();
    	$("#divimg_"+index).remove();
    }
    
    function addsubcourse(subcourse){
    	var txtcontent='<div style="float:left"><label>开始时间：<b>*</b></label><input  name="datestart'+subindex+'" type="text" class="dfinput"/></div>';
    	txtcontent=txtcontent+'<div style="float:left"><label>结束时间：<b>*</b></label><input  name="dateend'+subindex+'" type="text" class="dfinput"/></div>';
    	txtcontent=txtcontent+'<div style="float:left"><label>课程名称：<b>*</b></label><input  name="subname'+subindex+'" type="text" class="dfinput"/></div>';
    	txtcontent=txtcontent+'<div style="float:left"><label>课程类型：<b>*</b></label><select name="subtype'+subindex+'" class="scselect"><option value="核心课">核心课</option><option value="其他课">其他课</option></select></div>';
    	txtcontent=txtcontent+'<div style="float:left"><label>讲师：<b>*</b></label><div style="width: 240px;float:left"><select id="sltteacherid'+subindex+'" name="teacherid'+subindex+'" class="scselect"></select></div></div>'
    	txtcontent=txtcontent+'<div><input type="button" value="删除" id="btndeletesub_'+subindex+'" class="btn"/><input type="button" value="确认修改" id="btnupdatesub_'+subindex+'" class="btn"/><input name="substate'+subindex+'" type="text" readonly="true"/>';
    	$("#divnew").append('<div id="div_'+subindex+'" style="border:1px solid #b8dcff;float:left;">'+txtcontent+'</div>');
    	if(subcourse!=null){
    		$("input[name=datestart" + subindex + "]").val(subcourse.stratdate);
        	$("input[name=dateend" + subindex + "]").val(subcourse.enddate);
        	$("input[name=subname" + subindex + "]").val(subcourse.subname);
        	$("select[name=subtype" + subindex + "]").val(subcourse.subtype);
        	$("select[name=teacherid" + subindex + "]").val(subcourse.teacherid);
        	$("input[name=substate" + subindex + "]").val(subcourse.substate);
        	loadTeachers("sltteacherid"+subindex,subcourse.teacherid);
    	}else{
    		loadTeachers("sltteacherid"+subindex,null);
       	 	
    	}
    	
    	$("input[name=datestart" + subindex + "]").rules("add", {required: true, messages: {required: "请输入开始时间"} });
    	$("input[name=datestart" + subindex + "]").rules("add", {newdatetime: true, messages: {newdatetime: "开始时间格式不正确"} });
    	$("input[name=dateend" + subindex + "]").rules("add", {required: true, messages: {required: "请输入结束时间"} });
    	$("input[name=dateend" + subindex + "]").rules("add", {newdatetime: true, messages: {newdatetime: "结束时间格式不正确"} });
    	$("input[name=subname" + subindex + "]").rules("add", {required: true, messages: {required: "请输入课程名称"} });
    	$("select[name=teacherid" + subindex + "]").rules("add", {required: true, messages: {required: "请选择讲师"} });

		
		$("#btndeletesub_"+subindex).on("click",function(){
			var str = this.id.split("_");
			deletesubcourse(str[1]);
		})
		
		$("#btnupdatesub_"+subindex).on("click",function(){
			var str = this.id.split("_");
			var subcourseid = subcourse.id;
			var datestart = $("input[name=datestart" + str[1] + "]").val();
			var dateend = $("input[name=dateend" + str[1] + "]").val();
			var subname = $("input[name=subname" + str[1] + "]").val();
			var subtype = $("select[name=subtype" + str[1] + "]").val();
			var teacherid = $("select[name=teacherid" + str[1] + "]").val();
			updatesubcourse(subcourseid,datestart,dateend,subname,subtype,teacherid);
		})
		
		subindex = subindex + 1;
		subcount =subcount+1;
    }
    
    function deletesubcourse(index){

    	$("#div_"+index).remove();
    	subcount =subcount-1;
    }
    function updatesubcourse(subcourseid,datestart,dateend,subname,subtype,teacherid){
    	var params = { "subcourseid": subcourseid, "datestart": datestart,"dateend": dateend,"subname":subname,"subtype":subtype,"teacherid":teacherid};
        $.ajax({
            url: "subcourse/edit", //要访问的后台地址
            type: "POST", //使用get方法访问后台
            cache: false,
            dataType: "json",
            data: params,
            success: function (response) {//msg为返回的数据，在这里做数据绑定
                alert(response.message);
            } //返回成功完成
        });
    }
}


