

function orderstatistics() {

	 function setInputDisable(){
	    	$("input").each(function(){
	    		$(this).attr("disabled", true);
	    	});
	    }
	 
    //根据ID加载对象
    function showModel() {
        $.ajax({
            cache: false,
            type: "GET", //使用get方法访问后台
            url: "ordering/statistics", //要访问的后台地址
            dataType: "json",   //返回数据类型
            data: { "Method": "Show"},
            success: function (response) {//response为返回的数据，在这里做数据绑定
                if (response != null) {
                    $.each(response, function (skey, svalue) {
                        var sinput = $("input[name='" + skey + "']")
                        if (sinput != null) {
                        	if(svalue!=null){
                                sinput.val(svalue);
                        	}else{
                                sinput.val("0");
                        	}
                        }
                    });
                }

            } //返回成功完成
        });
    }; //加载对象结束

    //绑定form页面的按钮事件
    this.bindForm = function () {
    	setInputDisable();
    	showModel();
    }
}