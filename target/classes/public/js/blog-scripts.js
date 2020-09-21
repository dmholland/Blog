$(function() {
    $('#messages li').cick(function(){
        $(this).fadeOut();
    });
    setTimeout(function() {
        $('#messages li.info').fadeOut();
    })
})