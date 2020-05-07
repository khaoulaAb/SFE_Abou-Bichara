


/*
$(document).ready(function(){
    $('.table .eBtn').on('click',function (event) {
        event.preventDefault();
        var href=$(this).attr('href');


      $.get(href,function (user,status) {
            $('.myForm #nom').val(user.nom);
            $('.myForm #prenom').val(user.prenom);
            $('.myForm #email').val(user.id);
            $('.myForm #password').val(user.password);
            $('.myForm #role').val(user.role);

        });

        $('.myForm #exampleModal').modal();
    });
});
*/

$(document).ready(function(){
    $(' .nBtn, .table .eBtn').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();
        if(text=='Edit') {

            $.get(href, function (user, status) {
                $('.myForm #nom').val(user.nom);
                $('.myForm #prenom').val(user.prenom);
                $('.myForm #genre').val(user.genre);
                $('.myForm #email').val(user.email);
                $('.myForm #role').val(user.role);


            });

            $('.myForm #exampleModal').modal();
        }else{

            $('.myForm #nom').val('');
            $('.myForm #prenom').val('');
            $('.myForm #genre').val('');
            $('.myForm #email').val('');
            $('.myForm #role').val('');
            $('.myForm #exampleModal').modal();
        }
    });

    $('.table .delBtn').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('#myModal #delRef').attr('href',href);
        $('#myModal').modal();
    });
});
