

function setProvinceList(countryId, provinceId) {

    $("#sltprovince").empty();
    $("#sltprovince").append($("<option>").text("请选择").val("0"));
    $.ajax({
        cache: false,
        type: "POST",
        dataType: "json", //返回json格式的数据
        url: "area/getListByParentid",
        data: { "parentId": countryId },
        success: function (data) {
            $(data.records).each(
                    function () {
                        for (var i = 0; i < $(this).length; i++) {
                            var option = $("<option>").text($(this)[i].name).val($(this)[i].code);
                            $("#sltprovince").append(option);
                        }
                    }
                )

            if ($("#sltprovince").get(0).options.length <= 0) return;

            if (provinceId != null) {

                for (var i = 0; i < $("#sltprovince").get(0).options.length; i++) {
                    if ($("#sltprovince").get(0).options[i].value == provinceId) {
                        $("#sltprovince").get(0).options[i].selected = true;
                        break;
                    }
                }
            }
            showAddress();
        },
        error: function (xhr, ajaxOptions, thrownError) {
        }
    });
}

function setCityList(provinceId, cityId) {

    $("#sltcounty").empty();
    $("#sltcounty").append($("<option>").text("请选择").val("0"));

    $("#sltcity").empty();
    $("#sltcity").append($("<option>").text("请选择").val("0"));

    $.ajax({
        cache: false,
        type: "POST",
        dataType: "json", //返回json格式的数据
        url: "area/getListByParentid",
        data: { "parentId": provinceId },
        success: function (data) {
            //var list = eval(data); // JSON字符串转JSON对象
            $(data.records).each(
                function () {
                    for (var i = 0; i < $(this).length; i++) {

                        var option = $("<option>").text($(this)[i].name).val($(this)[i].code);
                        $("#sltcity").append(option);
                    }
                }
             );

            if ($("#sltcity").get(0).options.length <= 0) return;

            if (cityId != null) {
                for (var i = 0; i < $("#sltcity").get(0).options.length; i++) {
                    if ($("#sltcity").get(0).options[i].value == cityId) {
                        $("#sltcity").get(0).options[i].selected = true;
                        break;
                    }
                }
            }
            showAddress();
        },
        error: function (xhr, ajaxOptions, thrownError) {
        }
    });
}
function setDistrictList(cityId, countyId) {

    $("#sltcounty").empty();
    $("#sltcounty").append($("<option>").text("请选择").val("0"));
    $.ajax({
        cache: false,
        type: "POST",
        dataType: "json", //返回json格式的数据
        url: "area/getListByParentid",
        data: { "parentId": cityId },
        success: function (data) {
            $(data.records).each(
                function () {
                    for (var i = 0; i < $(this).length; i++) {
                        var option = $("<option>").text($(this)[i].name).val($(this)[i].code);
                        $("#sltcounty").append(option);
                    }
                }
            );

            if ($("#sltcounty").get(0).options.length <= 0) return;

            if (countyId != null) {
                for (var i = 0; i < $("#sltcounty").get(0).options.length; i++) {
                    if ($("#sltcounty").get(0).options[i].value == countyId) {

                        $("#sltcounty").get(0).options[i].selected = true;
                        break;
                    }
                }
            }
            showAddress();
        },
        error: function (xhr, ajaxOptions, thrownError) {
        }
    });
}

//加载地址
function showAddress() {
	var porvince = $("#sltprovince").find("option:selected").text();
	var city = $("#sltcity").find("option:selected").text();
	var county = $("#sltcounty").find("option:selected").text();
	if(porvince!="请选择"&&city!="请选择"&&county!="请选择"){
		var svalue=porvince+"省"+city+"市"+county;
		$("#txtaddress").attr("value",svalue);//填充内容 
	}
}