
$(document).ready(function(){

    /************Users **********/
    $('.table .eBtn').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');

            $.get(href, function (user, status) {
                $('.myForm #id').val(user.id);
                $('.myForm #nom').val(user.nom);
                $('.myForm #prenom').val(user.prenom);
                $('.myForm #genre').val(user.genre);
                $('.myForm #email').val(user.email);
                $('.myForm #role').val(user.role);


            });

            $('.myForm #exampleModalUp').modal();
    });

    $('.newBtn').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
            $('.myForm #nom').val('');
            $('.myForm #prenom').val('');
            $('.myForm #genre').val('');
            $('.myForm #email').val('');
            $('.myForm #role').val('');
            $('.myForm #exampleModal').modal();
        });


    $('.table .delBtn').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('#myModal #delRef').attr('href',href);
        $('#myModal').modal();
    });

    $('.btnEtu').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('#myModalEtu #etu').attr('href',href);
        $('#myModalEtu').modal();
    });


    /**********  Cours *************/

    $('.btnAddCours').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('.myForm #id').val('');
        $('.myForm #titre').val('');
        $('.myForm #description').val('');
        $('.myForm #files').val('');
        $('.myForm #filiere').val('');
        $('.myForm #exampleModalCours').modal();
    });
/***** Remarque***/

    $('.btnDel').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('#myModalRem #delr').attr('href',href);
        $('#myModalRem').modal();
    });

    /**********  Filiere *************/

    $('.Fbtn').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('.myForm #id').val('');
        $('.myForm #abr').val('');
        $('.myForm #nom').val('');
        $('.myForm #exampleModalFiliere').modal();
    });

    $('.table .btnDelF').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('#myModalF #delFf').attr('href',href);
        $('#myModalF').modal();
    });



});

function hideThis(_div){
    var obj = document.getElementById(_div);
    if(obj.style.display == "block")
        obj.style.display = "none";
    else
        obj.style.display = "block";
}
