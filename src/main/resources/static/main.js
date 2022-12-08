$('.delete-btn').on('click', function (e) {
    e.preventDefault();
    var href =$(this).attr('href');
    $('#myModal #btnDelteYes').attr('href',href);
    $('#myModal').modal();
});


$('#myModal').on('shown.bs.modal', function () {
    $('#myInput').trigger('focus')
})