// 三级联动的 js
$(function () {
    address.init({
        provinceElement: $("#province"),
        areaElement: $("#area"),
        cityElement: $("#city")
    });
    var province = "广东省";
    var city = "深圳市";
    var area = "坪山区";
    address.query(province, city, area);
});

$(document).ready(function () {
    $("#login-manager").click(function () {
        getAllLogin($(".admin-tablebody"))
    })

    $(".type").change(function () {
        showDifferent($(".type").val())
    })

    $("#submit").click(function () {
        var condition = $(".type").val();
        if (condition == -1) {
            addAdmin();
        } else if (condition == 0) {
            addCustomer();
        } else if (condition == 1) {
            addHousekeeper();
        }
        getAllLogin($(".admin-tablebody"))
    })

    getLoginUser();
    searchByPhone($(".admin-tablebody"));

    // 模态框显示出来，然后将关联元素的 username 传到模态框中，点击确定之后，调用函数
    $("#deleteUser").on("show.bs.modal", function (event) {
        var usernameVal = $(event.relatedTarget).parent().siblings(".user-username").text().trim();
        $(".submit-delete").click(function () {
            deleteLogin(usernameVal);
        })
    })

    // 修改用户账户信息
    $("#reviseUser").on("show.bs.modal", function (event) {
        var id = $(event.relatedTarget).attr("id");
        $(".submit-info").click(function () {
            alert("sfhwiefhdi");
            modifyUser(id);
        })
    })

    // 展示各个角色的所有认证
    getAllCustomerCertify($(".cm-tablebody"));
    getAllHousekeeperCertify($(".hk-tablebody"));
    getAllCompanyCertify($(".cp-tablebody"));

    // 显示各个角色的认证详情
    showCustomerDetail();
    showHousekeeperDetail();
    showCompanyDetail();
})

// 获取登录用户的用户名
function getLoginUser() {
    $.ajax({
        "url": "admin/getCurrentUsername",
        "type": "Post",
        "success": function (json) {
            if (json.state == 200) {
                // alert("登录用户的手机号为：" + json.data);
                $("#userName").text("当前用户:" + json.data);
            } else {
                window.location.href = "login.html";
            }
        }
    })
}

// 获取所有的用户信息
function getAllLogin(container) {
    var url = "admin/selectAllLogin";
    $.ajax({
        "url": url,
        "dataType": "json",
        "type": "Post",
        "success": function (json) {
            container.empty();
            var list = json.data;
            for (var i = 0; i < list.length; i ++) {
                var optionData = "<div class=\"row\">\n" +
                    "                            <div class=\"user-id col-xs-2 \">\n" +
                    "                                #{id}\n" +
                    "                            </div>\n" +
                    "                            <div class=\"user-username col-xs-2\">\n" +
                    "                                #{username}\n" +
                    "                            </div>\n" +
                    "                            <div class=\"col-xs-2\">\n" +
                    "                                #{password}\n" +
                    "                            </div>\n" +
                    "                            <div class=\"col-xs-2\">\n" +
                    "                                #{role}\n" +
                    "                            </div>\n" +
                    "                            <div class=\"col-xs-2\">\n" +
                    "                                #{status}\n" +
                    "                            </div>\n" +
                    "                            <div class=\"col-xs-2\">\n" +
                    "                                <button id='#{id}' class=\"modify-user btn btn-success btn-xs\" data-toggle=\"modal\" data-target=\"#reviseUser\">修改</button>\n" +
                    "                                <button class=\"btn btn-danger btn-xs\" data-toggle=\"modal\" data-target=\"#deleteUser\">删除</button>\n" +
                    "                            </div>\n" +
                    "                        </div>";
                optionData = optionData.replace("#{id}", i + 1);
                optionData = optionData.replace("#{username}", list[i].username);
                optionData = optionData.replace("#{password}", list[i].password);
                // 用户角色
                if (list[i].role == 0) {
                    optionData = optionData.replace("#{role}", "消费者");
                } else if (list[i].role == 1) {
                    optionData = optionData.replace("#{role}", "家政人员");
                } else if (list[i].role == -1) {
                    optionData = optionData.replace("#{role}", "管理员");
                }

                // 用户状态
                if (list[i].status == 1) {
                    optionData = optionData.replace("#{status}", "有效");
                } else if (list[i].status == 0) {
                    optionData = optionData.replace("#{status}", "无效");
                }

                optionData = optionData.replace("#{id}", list[i].id);
                container.append(optionData);
                modifyUser();
            }
        }
    })
}

// 获取所有已上传认证资料的消费者认证信息
function getAllCustomerCertify(container) {
    $.ajax({
        "url": "admin/getAllCustomerCertify",
        "type": "Post",
        "success": function (json) {
            if (json.state == 200) {
                var list = json.data;
                for (var i = 0; i < list.length; i ++) {
                    var optionData = "<div class=\"row\">\n" +
                        "<div class=\"col-xs-2 \">\n" +
                        "#{num}\n" +
                        "</div>\n" +
                        "<div class=\"col-xs-2\">\n" +
                        "#{name}\n" +
                        "</div>\n" +
                        "<div class=\"col-xs-3\">\n" +
                        "#{cardID}\n" +
                        "</div>\n" +
                        "<div class=\"col-xs-2\">\n" +
                        "<button id='#{id}' class=\"cm-see-btn btn btn-success btn-xs\">查看</button>\n" +
                        "</div>\n" +
                        "<div class=\"col-xs-3\">\n" +
                        "<button id='#{btn-id}' class=\"#{btn-class} #{btn-func} btn btn-xs\" data-toggle=\"modal\" data-target=\"#certifyCustomer\">#{btn-content}</button>\n" +
                        "</div>\n" +
                        "</div>";

                    if (list[i].cmStatus == 1) {
                        optionData = optionData.replace("#{btn-content}", "取消认证")
                        optionData = optionData.replace("#{btn-class}", "btn-danger")
                        optionData = optionData.replace("#{btn-func}", "cm-cancel")
                    } else {
                        optionData = optionData.replace("#{btn-content}", "给予认证")
                        optionData = optionData.replace("#{btn-class}", "btn-success")
                        optionData = optionData.replace("#{btn-func}", "cm-certify")
                    }
                    optionData = optionData.replace("#{num}", i + 1);
                    optionData = optionData.replace("#{name}", list[i].cmName);
                    optionData = optionData.replace("#{cardID}", list[i].cmCardID);
                    optionData = optionData.replace("#{id}", list[i].id);
                    optionData = optionData.replace("#{btn-id}", list[i].id);
                    container.append(optionData);
                }

                showCustomerDetail();
                cancelCustomerCertify();
                confirmCustomerCertify();
            }
        }
    })
}

// 获取所有已上传认证资料的家政人员认证信息
function getAllHousekeeperCertify(container) {
    $.ajax({
        "url": "admin/getAllHousekeeperCertify",
        "type": "Post",
        "success": function (json) {
            if (json.state == 200) {
                var list = json.data;
                for (var i = 0; i < list.length; i ++) {
                    var optionData = "<div class=\"row\">\n" +
                        "<div class=\"col-xs-2 \">\n" +
                        "#{num}\n" +
                        "</div>\n" +
                        "<div class=\"col-xs-2\">\n" +
                        "#{name}\n" +
                        "</div>\n" +
                        "<div class=\"col-xs-3\">\n" +
                        "#{cardID}\n" +
                        "</div>\n" +
                        "<div class=\"col-xs-2\">\n" +
                        "<button id='#{id}' class=\"hk-see-btn btn btn-success btn-xs\">查看</button>\n" +
                        "</div>\n" +
                        "<div class=\"col-xs-3\">\n" +
                        "<button id='#{btn-id}' class=\"#{btn-class} #{btn-func} btn btn-xs\" data-toggle=\"modal\" data-target=\"#certify-housekeeper\">#{btn-content}</button>\n" +
                        "</div>\n" +
                        "</div>";
                    optionData = optionData.replace("#{num}", i + 1);
                    optionData = optionData.replace("#{name}", list[i].hkName);
                    optionData = optionData.replace("#{cardID}", list[i].hkCardID);
                    optionData = optionData.replace("#{id}", list[i].id);
                    optionData = optionData.replace("#{btn-id}", list[i].id);
                    if (list[i].hkStatus == 1) {
                        optionData = optionData.replace("#{btn-content}", "取消认证")
                        optionData = optionData.replace("#{btn-class}", "btn-danger")
                        optionData = optionData.replace("#{btn-func}", "hk-cancel")
                    } else {
                        optionData = optionData.replace("#{btn-content}", "给予认证")
                        optionData = optionData.replace("#{btn-class}", "btn-success")
                        optionData = optionData.replace("#{btn-func}", "hk-certify")
                    }

                    container.append(optionData);
                }
                showHousekeeperDetail();
                cancelHousekeeperCertify();
                confirmHousekeeperCertify();
            }
        }
    })
}

// 获取所有已上传认证资料的公司认证信息
function getAllCompanyCertify(container) {
    $.ajax({
        "url": "admin/getAllCompanyCertify",
        "type": "Post",
        "success": function (json) {
            if (json.state == 200) {
                var list = json.data;
                for (var i = 0; i < list.length; i ++) {
                    var optionData = "<div class=\"row\">\n" +
                        "<div class=\"col-xs-2 \">\n" +
                        "#{num}\n" +
                        "</div>\n" +
                        "<div class=\"col-xs-2\">\n" +
                        "#{name}\n" +
                        "</div>\n" +
                        "<div class=\"col-xs-2\">\n" +
                        "#{phone}\n" +
                        "</div>\n" +
                        "<div class=\"col-xs-2\">\n" +
                        "#{regID}\n" +
                        "</div>\n" +
                        "<div class=\"col-xs-2\">\n" +
                        "<button id='#{id}' class=\"cp-see-btn btn btn-success btn-xs\">查看</button>\n" +
                        "</div>\n" +
                        "<div class=\"col-xs-2\">\n" +
                        "<button id='#{btn-id}' class=\"btn #{btn-class} #{btn-func} btn-xs\" data-toggle=\"modal\">#{btn-content}</button>\n" +
                        "</div>\n" +
                        "</div>";
                    if (list[i].companyStatus == 1) {
                        optionData = optionData.replace("#{btn-content}", "取消认证")
                        optionData = optionData.replace("#{btn-class}", "btn-danger")
                        optionData = optionData.replace("#{btn-func}", "cp-cancel")
                    } else {
                        optionData = optionData.replace("#{btn-content}", "给予认证")
                        optionData = optionData.replace("#{btn-class}", "btn-success")
                        optionData = optionData.replace("#{btn-func}", "cp-certify")
                    }

                    optionData = optionData.replace("#{num}", i + 1);
                    optionData = optionData.replace("#{name}", list[i].name);
                    optionData = optionData.replace("#{phone}", list[i].phone);
                    optionData = optionData.replace("#{regID}", list[i].busRegNum);
                    optionData = optionData.replace("#{id}", list[i].id);
                    optionData = optionData.replace("#{btn-id}", list[i].id);

                    container.append(optionData);
                }
                showCompanyDetail();
                cancelCompanyCertify();
                confirmCompanyCertify();
            }
        }
    })
}

// 根据搜索框中输入手机号查询用户信息
function searchByPhone(container) {
    $(".search-btn").click(function () {
        $.ajax ({
            "url": "admin/getUserByPhone",
            "data": "phone=" + $(".search-input").val(),
            "dataType": "json",
            "type": "Post",
            "success": function (json) {
                if (json.state ==200) {
                    container.empty();
                    var list = json.data;
                    for (var i = 0; i < list.length; i ++) {
                        var optionData = "<div class=\"row\">\n" +
                            "<div class=\"user-id col-xs-2 \">\n" +
                            "#{id}\n" +
                            "</div>\n" +
                            "<div class=\"user-username col-xs-2\">\n" +
                            "#{username}\n" +
                            "</div>\n" +
                            "<div class=\"col-xs-2\">\n" +
                            "#{password}\n" +
                            "</div>\n" +
                            "<div class=\"col-xs-2\">\n" +
                            "#{role}\n" +
                            "</div>\n" +
                            "<div class=\"col-xs-2\">\n" +
                            "#{status}\n" +
                            "</div>\n" +
                            "<div class=\"col-xs-2\">\n" +
                            "<button id='#{id}' class=\"modify-user btn btn-success btn-xs\" data-toggle=\"modal\" data-target=\"#reviseUser\">修改</button>\n" +
                            "<button class=\"btn btn-danger btn-xs\" data-toggle=\"modal\" data-target=\"#deleteUser\">删除</button>\n" +
                            "</div>\n" +
                            "</div>";
                        optionData = optionData.replace("#{id}", i + 1);
                        optionData = optionData.replace("#{username}", list[i].username);
                        optionData = optionData.replace("#{password}", list[i].password);
                        // 用户角色
                        if (list[i].role == 0) {
                            optionData = optionData.replace("#{role}", "消费者");
                        } else if (list[i].role == 1) {
                            optionData = optionData.replace("#{role}", "家政人员");
                        } else if (list[i].role == -1) {
                            optionData = optionData.replace("#{role}", "管理员");
                        }

                        // 用户状态
                        if (list[i].status == 1) {
                            optionData = optionData.replace("#{status}", "有效");
                        } else if (list[i].status == 0) {
                            optionData = optionData.replace("#{status}", "无效");
                        }

                        optionData = optionData.replace("#{id}", list[i].id);
                        container.append(optionData);
                    }
                } else {
                    alert(json.message)
                }

            }
        })
    })

}

// 修改用户信息
function modifyUser(id) {
        var url = "admin/updateUserInfo";
        var data = "id=" + id + "&password=" + $(".user-password").val() + "&role=" +$(".user-role").val() + "&status=" + $("input[name='user-status']").val();
        $.ajax ({
            "url": url,
            "data": data,
            "dataType": "json",
            "type": "Post",
            "success": function (json) {
                if (json.state == 200) {
                    alert("修改成功");
                    getAllLogin($(".admin-tablebody"))
                }
            }
        })
}

// 获取所有的用户信息
function getAllLogin(container) {
    var url = "admin/selectAllLogin";
    $.ajax({
        "url": url,
        "dataType": "json",
        "type": "Post",
        "success": function (json) {
            container.empty();
            var list = json.data;
            for (var i = 0; i < list.length; i ++) {
                var optionData = "<div class=\"row\">\n" +
                    "<div class=\"user-id col-xs-2 \">\n" +
                    "#{id}\n" +
                    "</div>\n" +
                    "<div class=\"user-username col-xs-2\">\n" +
                    "#{username}\n" +
                    "</div>\n" +
                    "<div class=\"col-xs-2\">\n" +
                    "#{password}\n" +
                    "</div>\n" +
                    "<div class=\"col-xs-2\">\n" +
                    "#{role}\n" +
                    "</div>\n" +
                    "<div class=\"col-xs-2\">\n" +
                    "#{status}\n" +
                    "</div>\n" +
                    "<div class=\"col-xs-2\">\n" +
                    "<button id='#{id}' class=\"modify-user btn btn-success btn-xs\" data-toggle=\"modal\" data-target=\"#reviseUser\">修改</button>\n" +
                    "<button class=\"btn btn-danger btn-xs\" data-toggle=\"modal\" data-target=\"#deleteUser\">删除</button>\n" +
                    "</div>\n" +
                    "</div>";
                optionData = optionData.replace("#{id}", i + 1);
                optionData = optionData.replace("#{username}", list[i].username);
                optionData = optionData.replace("#{password}", list[i].password);
                // 用户角色
                if (list[i].role == 0) {
                    optionData = optionData.replace("#{role}", "消费者");
                } else if (list[i].role == 1) {
                    optionData = optionData.replace("#{role}", "家政人员");
                } else if (list[i].role == -1) {
                    optionData = optionData.replace("#{role}", "管理员");
                }

                // 用户状态
                if (list[i].status == 1) {
                    optionData = optionData.replace("#{status}", "有效");
                } else if (list[i].status == 0) {
                    optionData = optionData.replace("#{status}", "无效");
                }

                optionData = optionData.replace("#{id}", list[i].id);
                container.append(optionData);
            }
        }
    })
}

// 展示消费者认证的详情
function showCustomerDetail() {
    $(".cm-see-btn").parent().on("click", "button", function () {
        var id = $(this).attr("id");
        $.ajax ({
            "url": "admin/getCustomerByID",
            "data": "id=" + id,
            "dataType": "json",
            "type": "Post",
            "success": function (json) {
                if (json.state == 200) {
                    var result = json.data;
                    $(".cm-name").val(result.cmName);
                    $(".cm-phone").val(result.cmPhone);
                    $(".cm-cardID").val(result.cmCardID);
                    $(".cm-cardPhoto").attr({"src": result.cmCardPhoto});
                }
            }
        })
        $("#customerDetailModal").modal("show");
    })
}

// 展示家政人员认证的详情
function showHousekeeperDetail() {
    $(".hk-see-btn").parent().on("click", "button", function () {
        var id = $(this).attr("id");
        $.ajax ({
            "url": "admin/getHousekeeperByID",
            "data": "id=" + id,
            "dataType": "json",
            "type": "Post",
            "success": function (json) {
                if (json.state == 200) {
                    var result = json.data;
                    $(".hk-name").val(result.hkName);
                    $(".hk-phone").val(result.hkPhone);
                    $(".hk-cardID").val(result.hkCardID);
                    $(".hk-cardPhoto").attr({"src": result.hkCardPhoto});
                    $(".hk-certifyPhoto").attr({"src": result.hkCertifyPhoto});
                }
            }
        })
        $("#housekeeperDetailModal").modal("show");
    })
}

// 展示公司认证的详情
function showCompanyDetail() {
    $(".cp-see-btn").parent().on("click", "button", function () {
        var id = $(this).attr("id");
        $.ajax ({
            "url": "admin/getCompanyByID",
            "data": "id=" + id,
            "dataType": "json",
            "type": "Post",
            "success": function (json) {
                if (json.state == 200) {
                    var result = json.data;
                    $(".cp-name").val(result.name);
                    $(".cp-phone").val(result.phone);
                    $(".cp-address").val(result.address);
                    $(".cp-regID").val(result.busRegNum);
                    $(".cp-regPhoto").attr({"src": result.busLicPhoto});
                }
            }
        })
        $("#companyDetailModal").modal("show");
    })
}

// 取消消费者认证
function cancelCustomerCertify() {
    $(".cm-cancel").parent().on("click", "button", function () {
        var id = $(this).attr("id");
        $.ajax ({
            "url": "admin/cancelCustomerByID",
            "data": "id=" + id,
            "dataType": "json",
            "type": "Post",
            "success": function (json) {
                if (json.state == 200) {
                    swal({
                        title: "提示",
                        text: "取消认证成功"
                    })
                } else {
                    swal({
                        title: "提示",
                        text: "取消认证失败"
                    })
                }
            }
        })
    })
}

// 给予消费者认证
function confirmCustomerCertify() {
    $(".cm-certify").parent().on("click", "button", function () {
        var id = $(this).attr("id");
        $.ajax ({
            "url": "admin/certifyCustomerByID",
            "data": "id=" + id,
            "dataType": "json",
            "type": "Post",
            "success": function (json) {
                if (json.state == 200) {
                    swal({
                        title: "提示",
                        text: "给予认证成功"
                    })

                } else {
                    swal({
                        title: "提示",
                        text: "给予认证失败"
                    })
                }
            }
        })
    })
}

// 取消家政人员认证
function cancelHousekeeperCertify() {
    $(".hk-cancel").parent().on("click", "button", function () {
        var id = $(this).attr("id");
        $.ajax ({
            "url": "admin/cancelHousekeeperByID",
            "data": "id=" + id,
            "dataType": "json",
            "type": "Post",
            "success": function (json) {
                if (json.state == 200) {
                    swal({
                        title: "提示",
                        text: "取消认证成功"
                    })
                } else {
                    swal({
                        title: "提示",
                        text: "取消认证失败"
                    })
                }
            }
        })
    })
}

// 给予家政人员认证
function confirmHousekeeperCertify() {
    $(".hk-certify").parent().on("click", "button", function () {
        var id = $(this).attr("id");
        $.ajax ({
            "url": "admin/certifyHousekeeperByID",
            "data": "id=" + id,
            "dataType": "json",
            "type": "Post",
            "success": function (json) {
                if (json.state == 200) {
                    swal({
                        title: "提示",
                        text: "给予认证成功"
                    })

                } else {
                    swal({
                        title: "提示",
                        text: "给予认证失败"
                    })
                }
            }
        })
    })
}

// 取消公司认证
function cancelCompanyCertify() {
    $(".cp-cancel").parent().on("click", "button", function () {
        var id = $(this).attr("id");
        $.ajax ({
            "url": "admin/cancelCompanyByID",
            "data": "id=" + id,
            "dataType": "json",
            "type": "Post",
            "success": function (json) {
                if (json.state == 200) {
                    swal({
                        title: "提示",
                        text: "取消认证成功"
                    })
                } else {
                    swal({
                        title: "提示",
                        text: "取消认证失败"
                    })
                }
            }
        })
    })
}

// 给予公司认证
function confirmCompanyCertify() {
    $(".cp-certify").parent().on("click", "button", function () {
        var id = $(this).attr("id");
        $.ajax ({
            "url": "admin/certifyCompanyByID",
            "data": "id=" + id,
            "dataType": "json",
            "type": "Post",
            "success": function (json) {
                if (json.state == 200) {
                    swal({
                        title: "提示",
                        text: "给予认证成功"
                    })

                } else {
                    swal({
                        title: "提示",
                        text: "给予认证失败"
                    })
                }
            }
        })
    })
}

// 根据人员不同，显示不同
function showDifferent(val) {
    if (val == -1) {
        $(".option").hide();
        $(".option-name").show();
        $(".option-gender").show();
        $(".option-password").show();
        $(".option-phone").show();
    } else if (val == 0) {
        $(".option").show();
    } else if (val == 1) {

    }
}

// 添加管理员的函数
function addAdmin() {
    var nameVal = $(".name").val();
    var genderVal = $(".gender").val();
    var phoneVal = $(".phone").val();
    var passwordVal = $(".init-password").val();
    var url = "admin/insertAdmin";
    var data = "name=" + nameVal +"&gender=" + genderVal + "&phone=" + phoneVal + "&password=" + passwordVal;
    $.ajax({
        "url": url,
        "data": data,
        "dataType": "json",
        "type": "Post",
        "success": function (json) {
            if (json.state == 200) {
                alert("管理员权限的用户添加成功！");
                $("#addUser").modal("hide");
                $("#addUser").find("input").val("");
            } else if (json.state == 401) {
                alert(json.message);
            }
        }
    })
}

// 添加消费者的函数
function addCustomer () {
    var nicknameVal = $(".nickname").val();
    var nameVal = $(".name").val();
    var genderVal = $(".gender").val();
    var phoneVal = $(".phone").val();
    var emailVal = $(".email").val();
    var passwordVal = $(".init-password").val();
    var provinceVal = $(".province").text();
    var cityVal = $(".city").text();
    var areaVal = $(".area").text();
    var address = provinceVal + "-" + cityVal + "-" +areaVal;
    var url = "admin/insertCustomer";
    var data = "nickname=" + nicknameVal + "&name=" + nameVal + "&gender=" + genderVal + "&phone=" + phoneVal
                + "&email=" +emailVal + "&password=" + passwordVal + "&address=" + address;
    $.ajax ({
        "url": url,
        "data": data,
        "dataType": "json",
        "type": "Post",
        "success": function (json) {
            if (json.state == 200) {
                alert("注册成功！");
            } else if (json.state == 401) {
                alert(json.message);
            }
        }
    })
}

// 添加家政人员的函数
function addHousekeeper () {
    var url = "";
    var data = "";
    $.ajax({
        "url": url,
        "data": data,
        "dataType": "Post",
        "type": "json",
        "success": function (json) {

        }
    })
}

// 修改用户状态的函数
function deleteLogin (usernameVal) {
    var url = "admin/updateLoginStatus";
    var data = "username=" + usernameVal;
    $.ajax({
        "url": url,
        "data": data,
        "dataType": "json",
        "success": function (json) {
            if (json.state == 200) {
                alert("删除成功！");
                $("#deleteUser").modal("hide");
                getAllLogin($(".admin-tablebody"));
            } else {
                alert("删除失败！")
            }
        }
    })
}
// 渲染echarts
$(function () {
    $.ajax({
        url:"admin/getOrderData",
        type:"GET",
        success:function (result) {
            var data1 = new Array();
            var data2 = new Array();
            if(result.state==200){
                if(result.data!=null){
                    var list = result.data;
                    for(var i=0;i<list.length;i++){
                        data1.push(list[i].name);
                        data2.push({value:list[i].num, name:list[i].name})
                    }
                    var myChart = echarts.init(document.getElementById('orders'));

                    // option = {
                    //     title : {
                    //         text: '预约类型统计',
                    //         x:'left'
                    //     },
                    //     tooltip : {
                    //         trigger: 'item',
                    //         formatter: "{a} <br/>{b} : {c} ({d}%)"
                    //     },
                    //     legend: {
                    //         orient: 'vertical',
                    //         left: 'left',
                    //         data: data1
                    //     },
                    //     series : [
                    //         {
                    //             name: '类型',
                    //             type: 'pie',
                    //             radius : '55%',
                    //             center: ['50%', '60%'],
                    //             data:data2,
                    //             itemStyle: {
                    //                 emphasis: {
                    //                     shadowBlur: 10,
                    //                     shadowOffsetX: 0,
                    //                     shadowColor: 'rgba(0, 0, 0, 0.5)'
                    //                 }
                    //             }
                    //         }
                    //     ]
                    // };
                    option = {
                        backgroundColor: '#eff3f6',

                        title: {
                            text: '订单类型统计',
                            left: 'center',
                            top: 20,
                            textStyle: {
                                color: '#000'
                            }
                        },

                        tooltip : {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },

                        visualMap: {
                            show: false,
                            min: 80,
                            max: 600,
                            inRange: {
                                colorLightness: [0, 1]
                            }
                        },
                        series : [
                            {
                                name:'类型',
                                type:'pie',
                                radius : '55%',
                                center: ['50%', '50%'],
                                data:data2.sort(function (a, b) { return a.value - b.value; }),
                                roseType: 'radius',
                                label: {
                                    normal: {
                                        textStyle: {
                                            color: 'rgba(255, 255, 255, 0.3)'
                                        }
                                    }
                                },
                                labelLine: {
                                    normal: {
                                        lineStyle: {
                                            color: 'rgba(255, 255, 255, 0.3)'
                                        },
                                        smooth: 0.2,
                                        length: 10,
                                        length2: 20
                                    }
                                },
                                itemStyle: {
                                    normal: {
                                        color: '#c23531',
                                        shadowBlur: 200,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                },

                                animationType: 'scale',
                                animationEasing: 'elasticOut',
                                animationDelay: function (idx) {
                                    return Math.random() * 200;
                                }
                            }
                        ]
                    };
                    myChart.setOption(option);
                }else {
                    alert("暂无数据!");
                }
            }else{
                alert(result.message);
            }
        }
    })

    $.ajax({
        url:"admin/getAppoimentData",
        type:"GET",
        success:function (result) {
            var data1 = new Array();
            var data2 = new Array();
            if(result.state == 200){
                if(result.data!=null){
                    var list = result.data;
                    for(var i=0;i<list.length;i++){
                        data1.push(list[i].name);
                        data2.push({value:list[i].num, name:list[i].name})
                    }
                    var myChart = echarts.init(document.getElementById('apps'));
                    var option = {
                        title : {
                            text: '预约类型统计',
                            x:'left'
                        },
                        tooltip : {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        legend: {
                            orient: 'vertical',
                            left: 'left',
                            data: data1
                        },
                        series : [
                            {
                                name: '类型',
                                type: 'pie',
                                radius : '55%',
                                center: ['50%', '60%'],
                                data:data2,
                                itemStyle: {
                                    emphasis: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    };

                    myChart.setOption(option);
                }else {
                    alert("暂无数据!");
                }
            }else{
                alert(result.message);
            }
        }
    })
})