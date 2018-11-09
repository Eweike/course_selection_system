$(document).ready(function(){
    $("#menu-list li").click(function() {
        $(this).siblings().removeClass('actived');  // 删除其他兄弟元素的样式
        $(this).addClass('actived');                // 添加当前元素的样式
    })
    tipScroll();
});
function tipScroll(){
    var scrollIndex=0;
    var Timer=null;
    function scroll_f(){
        clearInterval(Timer);
        var ul=$("#scroll ul");
        var li=ul.children("li");
        var h=li.height();
        var index=0;
        ul.css("height",h*li.length*2);
        ul.html(ul.html()+ul.html());
        function run()
        {
            if(scrollIndex>=li.length){
                ul.css({top:0});
                scrollIndex=1;
                ul.animate({top:-scrollIndex*h},10);
            }
            else{
                scrollIndex++;
                ul.animate({top:-scrollIndex*h},10);
            }
        }
        Timer=setInterval(run,1200);
    }
    $(function(){
        scroll_f();
    })
};