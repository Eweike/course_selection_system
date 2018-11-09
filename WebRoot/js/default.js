$(document).ready(function(){
    var screenWidth = document.documentElement.clientWidth || document.body.clientWidth;
    var screenHeight = document.documentElement.clientHeight || document.body.clientHeight;
    $("body").width(screenWidth)
    $("body").height(screenHeight)
});