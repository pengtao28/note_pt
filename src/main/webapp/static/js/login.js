var imgs;
var index = 0;
var lx;
$(function () {
    imgs = $(".img");
    lx = setInterval("lb()", 4000);//引号不能省
    //动态改变背景，注意url的书写方式
    imgs.click(function () {
        $("body").css("background", "url(" + this.src + ")")
    })
    //复合标签，mouseover时暂停轮播，mouseout时继续轮播
    imgs.hover(function () {
        clearInterval(lx);
    }, function () {
        lx = setInterval("lb()", 4000);
    })
});

//图片轮播方法
function lb() {
    index++;
    if (index < 0) {
        index = imgs.length - 1;
    }
    if (index >= imgs.length) {
        index = 0;
    }
    $(imgs[index]).show();
    imgs.not(imgs[index]).hide();
}

//校验登录信息是否正确
function checkLogin() {
    var userName = $(".pt_name").val();
    var userPwd = $(".pt_password").val();
    if (isEmpty(userName)) {
        $(".msg").html("用户名不能为空");
        return;
    }
    if (isEmpty(userPwd)) {
        $(".msg").html("密码不能为空")
        return;
    }
    $(".loginForm").submit();
}
	
