

var images=[];
$('.image-confirm-delete').on('click', function(e){
    e.preventDefault();
    var id= $(this).data('id');
    var name=$(this).data('name');
    images.push(name);
    $('#removeImages').val(images);
    $('#imageindex'+id).hide();

});

var images=[];
$('.confirm-delete').on('click', function(e){
    e.preventDefault();
    var id= $(this).data('id');

    $("#idModalLink").attr('href','/cours/deletecours/'+id);
    $("#myModalCours").modal();


});