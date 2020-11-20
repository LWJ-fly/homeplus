
// 加载全局函数
$(document).ready(function(){
	loginSuccess();

	searchContent();

	loadType();

	loadHouseKeeperList("index/getTopHousekeeper", $(".housekeeper-list-hour"));
	loadHouseKeeperList("index/getTopRepair", $(".housekeeper-list-repair"));
	loadHouseKeeperList("index/getTopMove", $(".housekeeper-list-move"));

	$(".big-type").on("click", ".first-type", function () {
        loadSmallType($(this).next(".small-type"), $(this).attr("id"));
        $(this).next(".small-type").toggle(300);
        $(this).siblings(".first-type").next(".small-type").hide(300);
    })

    loadRecommend($(".recommend"));

    $(".log-out").click (function () {
        logout();
    })
});

// 搜索框输入内容，实现搜索
function searchContent() {
    $(".search-btn").click(function () {
        alert($(".search-input").val())
        var data = "param=" + $(".search-input").val();
        $.ajax ({
            "url": "index/getTypeID",
            "data": data,
            "dataType": "json",
            "type": "Get",
            "success": function (json) {
                if (json.state == 200) {
                    var id = json.data;
                    window.location.href = "http://localhost:8081/search-result.html?typeid=" + id;
                }
            }
        })
    })
}

// 加载大的类型
function loadType () {
	var url = "index/getAllType";
	$.ajax({
		"url": url,
		"dataType": "json",
		"type": "Post",
		"success": function (json) {
			var list = json.data;
			for (var i = 0; i < list.length; i ++) {
                var optionData = "<li class=\"list-group-item first-type\" id='#{id}'>\n" +
                    "<img src=\"#{image_url}\">\n" +
                    "<span>#{type_name}</span>\n" +
                    "</li>" +
                    "<ul class=\"list-group small-type hot\">" +
                    "</ul>";
                optionData = optionData.replace("#{id}", list[i].id);
                optionData = optionData.replace("#{image_url}", list[i].imageUrl);
                optionData = optionData.replace("#{type_name}", list[i].name);
                $(".big-type").append(optionData);
			}
        }
	})
}

// 加载小的类型
function loadSmallType(container, type_id) {
    var url = "index/getAllSmallType";
    var data = "type_id=" + type_id;
    $.ajax({
        "url": url,
        "data": data,
        "dataType": "json",
        "type": "Post",
        "success": function (json) {
            container.empty();
            if (json.state == 200) {
                var list = json.data;
                for (var i = 0; i < list.length; i ++) {
                    var optionData = "<li class=\"list-group-item\">\n" +
                        "<a href=\"search-result.html?typeid=#{typeid}\">#{small_type_name}</a>\n" +
                        "</li>";
                    optionData = optionData.replace("#{typeid}", list[i].id);
                    optionData = optionData.replace("#{small_type_name}", list[i].name);
                    container.append(optionData);
                }
            }
        }
    })
    // container.hide();
}

// 加载推荐人员
function loadRecommend(container) {
    $.ajax ({
        "url": "index/getRecommend",
        "type": "Post",
        "success": function (json) {
            if (json.state == 200) {
                var list = json.data;
                for (var i = 0; i < list.length; i ++) {
                    var optionData = "<li style=\"text-align: center\"  class=\"list-group-item\"><a href=\"#{user_detail}\">#{name}</a></li>";
                    optionData = optionData.replace("#{user_detail}", "housekeeper.html?id=" + list[i].id );
                    optionData = optionData.replace("#{name}", list[i].hkNickname);
                    container.append(optionData);
                }
            }
        }
    })
}

// 加载家政人员列表
function loadHouseKeeperList(url, container) {
    $.ajax({
        "url": url,
        "dataType": "json",
        "type": "Post",
        "success": function (json) {
            var list = json.data;
            for (var i = 0; i < list.length; i ++) {
                var optionData=
                    "<div class=\"housekeeper-list-item clearfix\">\n" +
                    "<div class=\"col-md-3 headImg\">\n" +
                    "<img style='width: 100px; height: 100px;' src=\"#{hk_headPhoto}\" alt=\"家政人员照片\" class=\"img-rounded\">\n" +
                    "</div>\n" +
                    "<div class=\"col-md-6\">\n" +
                    "<div class=\"h4\">#{hk_Slogan}</div>\n" +
                    "<div>#{hk_hkDesc}</div>\n" +
                    "<div><a href=\"#\">#{hk_companyName}</a></div>\n" +
                    "<div>\n" +
                    // "<span class=\"badge\">金牌认证</span>\n" +
                    // "<span class=\"badge\">带娃能手</span>\n" +
                    "#{span_list}\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<div class=\"col-md-3\">\n" +
                    "<a href=\"housekeeper.html?id=#{hk_id}\" class=\"btn btn-primary go-single\">进去看看</a>\n" +
                    "</div>\n" +
                    "</div>";
                optionData = optionData.replace("#{hk_headPhoto}", list[i].hkHeadphoto);
                optionData = optionData.replace("#{hk_hkDesc}", list[i].hkDesc);
                optionData = optionData.replace("#{hk_id}", list[i].id);
                optionData = optionData.replace("#{hk_Slogan}", list[i].hkSlogan);
                optionData = optionData.replace("#{hk_companyName}", list[i].companyName);
                optionData = optionData.replace("#{span_list}", getLabelList(list[i].hkLabel));
                container.append(optionData);
            }
        }
    })
}

// 获取家政人员的徽章列表
function getLabelList(str) {
    var result = "";
    var list = str.split("-");
    for (var i = 0; i < list.length; i ++) {
        var optionData = "<span class=\"badge\">"+ list[i] +"</span>";
        result += optionData;
    }
    return result;
}


// 登录成功
function loginSuccess() {
    $(".log-out").css({"display": "none"});
    $.ajax({
        "url": "user/loginSuccess",
        "type": "Post",
        "success": function (json) {
            if (json.state == 200) {
                $(".log-out").css({"display": "block"});
                $(".log-in").css({"display": "none"});
                $(".register").css({"display": "none"});
            }
        }
    })
}

// 退出系统
function logout() {
    $.ajax ({
        "url": "user/logout",
        "type": "Post",
        "success": function (json) {
            if (json.state == 200) {
                alert("退出成功...");
            }
        }
    })
}
