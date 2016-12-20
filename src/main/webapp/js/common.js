/**
* HTML接收参数
*/
function request(paramName) {
    //获取URL的字符串
    var sSource = String(window.document.location);
    var sName = paramName;
    var sReturn = "";
    var sQUS = "?";
    var sAMP = "&";
    var sEQ = "=";
    var iPos;

    //获取sSource中的"?"，无则返回 -1
    iPos = sSource.indexOf(sQUS);
    if (iPos == -1)
        return "";

    //汲取参数，从iPos位置到sSource.length-iPos的位置，
    //若iPos = -1，则：从-1 到 sSource.length+1
    var strQuery = sSource.substr(iPos, sSource.length - iPos);

    // alert(strQuery); 
    //先全部转换为小写
    var strLCQuery = strQuery.toLowerCase();
    var strLCName = sName.toLowerCase();

    //从子字符串strLCQuery中查找“?”、参数名，以及“=”，即“?参数名=”
    iPos = strLCQuery.indexOf(sQUS + strLCName + sEQ);
    //alert(iPos);
    //如果不存在
    if (iPos == -1) {
        //继续查找可能的后一个参数，即带“&参数名=”
        iPos = strLCQuery.indexOf(sAMP + strLCName + sEQ);
    }

    //判断是否存在参数
    if (iPos != -1) {
        sReturn = strQuery.substr(iPos + sName.length + 2, strQuery.length - (iPos + sName.length + 2));
        var iPosAMP = sReturn.indexOf(sAMP);
        if (iPosAMP == -1) {
            return sReturn;
        }
        else {
            sReturn = sReturn.substr(0, iPosAMP);
        }
    }
    return sReturn;
}


function timeStamp2String (time){
	 var datetime = new Date();
	 datetime.setTime(time);
	 var year = datetime.getFullYear();
	 var month = datetime.getMonth() + 1;
	 var date = datetime.getDate();
	 var hour = datetime.getHours();
	 var minute = datetime.getMinutes();
	 var second = datetime.getSeconds();
	 var mseconds = datetime.getMilliseconds();
	 return year + "-" + month + "-" + date;
};

alertify.set({ labels: { ok: "确定", cancel: "取消"} });
alertify.set({ buttonReverse: true }); // true, false

alertify.set({
    labels: {
        ok: "确认",
        cancel: "取消"
    },
    delay: 5000,
    buttonReverse: true,
    buttonFocus: "ok"
});


$.ajaxSetup({
    //timeout: 5000,
    aysnc: false, // 默认同步加载
    //发送请求前触发
    contentType: "application/x-www-form-urlencoded;charset=utf-8",
    beforeSend: function (xhr) {
        //可以设置自定义标头 
        //xhr.setRequestHeader('Content-Type', 'application/xml;charset=utf-8'); 
        $(document).mask('正在加载数据...'); //AJAX请求时显示loading提示
    },
    error: function (xhr, status, errorThrown) {
        alert('发送AJAX请求到"' + this.url + '"时出错[' + status + ']：' + errorThrown);
    },
    //完成请求后触发。即在success或error触发后触发 
    complete: function (xhr, status) {
        $(document).unmask(); //AJAX请求完成时隐藏loading提示
        if (status == "success") {
            var resText = xhr.responseText;
            if (resText.indexOf("sessionstatus") > 0) {
                window.location.replace("login.aspx");
            }
        }
    }
})


//首页运行
$(function () {
    //顶部导航切换
    $(".nav li a").click(function () {
        $(".nav li a.selected").removeClass("selected")
        $(this).addClass("selected");
    })

    //右侧导航切换
    $(".menuson li").click(function () {
        $(".menuson li.active").removeClass("active")
        $(this).addClass("active");
    });

    $('.title').click(function () {
        var $ul = $(this).next('ul');
        $('dd').find('ul').slideUp();
        if ($ul.is(':visible')) {
            $(this).next('ul').slideUp();
        } else {
            $(this).next('ul').slideDown();
        }
    });
})

var currentid = "";
//替换中心div内容(页面跳转功能)
function replace(url, id) {
    if (id) {
        currentid = id;
    }
    $.ajax({
        aysnc: false, // 默认同步加载
        url: url, //这里是静态页的地址
        type: "POST", //静态页用get方法，否则服务器会抛出405错误
        success: function (data) {
            $("#content").html(data);
        }
    });
}

