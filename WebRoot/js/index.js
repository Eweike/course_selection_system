var keyCode = "";

$(document).ready(function (e) {
    // 获取屏幕宽高并设置全局变量
    var screenWidth = document.documentElement.clientWidth || document.body.clientWidth;
    var screenHeight = document.documentElement.clientHeight || document.body.clientHeight;
    window.screenWidth = screenWidth;
    window.screenHeight = screenHeight;
    // 获取屏幕宽高并设置全局变量
    creatCode();            //生成验证码
    $("#keyValue").click(function(){      //点击重新生成验证码
        creatCode();
    })
    $(".container,canvas").width(screenWidth);
    $(".container,canvas").height(screenHeight);
    drawCircle(200);
    $("#sub_student").click(function () {
        getFormValue(0);
    });
    $("#sub_teacher").click(function () {
        getFormValue(1);
    });
});

//定义一个圆类
function Circle(x, y, radius, vx, vy) {
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.vx = vx;
    this.vy = vy;
    this.connNum = 0;
};

//随机创建一个圆形
function createCircle() {
    var x = Math.random() * screenWidth;
    var y = Math.random() * screenHeight;
    var radius = 2;
    var vx = Math.random() * 2 - 1;     //生成（-1,1）之间的随机数
    var vy = Math.random() * 2 - 1;
    var circle = new Circle(x, y, radius, vx, vy);
    return circle;
};

// 准备画布
function drawCircle(count) {
    // var canvas = $("#circleCanvas");        //获得将要作画的画布
    var canvas = document.getElementById("circleCanvas");        //获得将要作画的画布
    var canvasContext = canvas.getContext("2d");    //指定画布为2d平面画布
    canvasContext.fillStyle = "#E8DFE8";          //指定要填充的内部颜色
    canvasContext.strokeStyle = "#E8DFE8";
    var pointList = [];
    for (var i = 0; i < count; i++) {
        var circle = createCircle();
        pointList.push(circle);         //将所有圆形储存起来
    }
    setInterval(function () {       //设置时间间隔，每隔1000/60毫秒调用一次，即一秒钟调用六十次,这里采用人眼帧数 1/24秒
        drawPoint(canvas, pointList);
    }, 1000 / 24);
};

//画圆
function drawPoint(canvas, pointList) {
    // var point;
    // console.log(pointList)
    var canvasContext = canvas.getContext("2d");
    canvasContext.clearRect(0, 0, 1000, 500);           //清除当前画布所有内容
    for (var i = 0; i < pointList.length; i++) {
        pointList[i] = borderPoint(pointList[i]);       //边界处理+速度处理，并返回处理后新圆形
        canvasContext.beginPath();              //停止刚才那一笔，开始新的笔画
        canvasContext.arc(pointList[i].x, pointList[i].y, 2, 0, 2 * Math.PI);   // 画出一个圆形（x,y,半径，开始角度，结束角度）
        canvasContext.fill();       //填充制定好的颜色
        canvasContext.stroke();     //填充边框
    }
    drawLine(canvasContext, canvas, pointList);
    canvasContext.closePath();  //完成图形封闭
}

// 边界处理
function borderPoint(point) {
    var p = point;
    if (point.x <= 0 || point.x >= 1000) {    //横向到达边界，水平方向速度取反
        p.vx = -p.vx;
        p.x += p.vx;
    } else if (point.y <= 0 || point.y >= 500) {  //垂直方向，与水平方向同理
        p.vy = -p.vy;
        p.y += p.vy;
    } else {        //没有到达边界，重置坐标=坐标+速度 实现一帧的动画
        p = {
            x: p.x + p.vx,
            y: p.y + p.vy,
            vx: p.vx,
            vy: p.vy
        }
    }
    return p;
}

//画线
function drawLine(context, canvas, pointList) {
    context = context || canvas.context;
    for (var i = 0; i < 200; i++) {
        // 初始化最大连接数
        pointList[i].connNum = 0;
        // point to point
        for (var j = 0; j < 200; j++) {
            if (i != j) {
                dist = Math.round(Math.pow(pointList[i].x - pointList[j].x, 2) + Math.pow(pointList[i].y - pointList[j].y, 2));
                // 两点距离小于吸附距离，而且小于最大连接数，则画线
                if (dist <= 6000 && pointList[i].connNum < 10) {
                    pointList[i].connNum++
                    // 距离越远，线条越细，而且越透明
                    context.lineWidth = 0.5 - dist / 6000;
                    context.strokeStyle = "rgba(130,255,255," + (1 - dist / 20000) + ")";     //rgba(r,g,b,透明度)
                    context.beginPath();
                    context.moveTo(pointList[i].x, pointList[i].y);     //线的起点
                    context.lineTo(pointList[j].x, pointList[j].y);     //线的终点
                    context.stroke();
                }
            }
        }
    }
}

//获取表单内容（传参登录者身份 0-学生  1-教师）
function getFormValue(status) {
    var t = $('#loginForm').serializeArray();         //获取表单内容
    var nameInput = t[0].value;
    var pwdInput = t[1].value;
    var keyInput = t[2].value;
    var tipText = document.getElementById("tipText");
    var inputBox = document.getElementById("input-box");
    if (nameInput == "") {
        ErroAlert("用户名不能为空");
    } else if (pwdInput == "") {
        ErroAlert("密码不能为空");
    } else if (keyInput == "") {
        ErroAlert("验证码不能为空");
    } else if (keyInput != keyCode) {
        ErroAlert("验证码输入错误");
    } else {
        console.log("通过")
        inputBox.setAttribute('style', 'transform:rotateX(70deg) translateZ(-100px) translateX(350px);');
        setTimeout(function () {
            tipText.innerHTML = "验证中...";
            layer.load(1);
            //此处演示关闭
            setTimeout(function () {
                layer.closeAll('loading');      //调用AJAX方法后再调用此方法
                var state = true;
                if (state) {
                    tipText.innerHTML = "登录成功，欢迎使用本系统";
                    setTimeout(function () {
                        window.location.href = "main-teacher.jsp";
                    },1300);
                } else {
                    tipText.innerHTML = "登录失败，请核实您的信息";
                    inputBox.setAttribute('style', 'transform:rotateX(0) translateZ(0) translateX(0);');
                }
            }, 1500);
        }, 500)
    }
}

//弹出
function ErroAlert(e) {
    layer.msg(e, function () {
        //关闭后的操作
    });
}

//生成验证码
function creatCode() {
    keyCode = Math.floor(Math.random() * 8999 + 1000);
    $("#keyValue").html(keyCode)
}