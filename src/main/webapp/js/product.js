
$( function () {

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#blah').attr('src', e.target.result);
            };
            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#imgInp").change(function(){
        readURL(this);
        callImgAreaSelect();
    });

    $("#blah").load(function(){
        callImgAreaSelect();
    });

    window.onresize = function(){
        callImgAreaSelect();
    };

    function callImgAreaSelect() {
        $('#blah').imgAreaSelect({
            aspectRatio: '3:2',
            x1: 0, y1: 0, x2: 100, y2: 70,
            imageWidth : $('#blah').width(),
            imageHeight : $('#blah').height(),
            movable: true,
            resizable: true,
            onSelectEnd: imgCrop,
            onInit: imgCrop,
            onSelectChange: imgCrop
        });
    }


} );




function imgCrop(img, selection) {
    $('#width').val($('#blah').width());
    $('#height').val($('#blah').height());
    $('#x1').val(selection.x1);
    $('#x2').val(selection.x2);
    $('#y1').val(selection.y1);
    $('#y2').val(selection.y2);
}


$('#form').submit(function (e) {
    e.preventDefault();
    $('#form').validate();

    if (!$('#form').valid()) {
        return;
    }

    var dataPost = new FormData($('#form')[0]);
    var $this = $(e);

    $.ajax({
        type: "POST",
        url: "/uploadController",
        data: dataPost,
        processData: false,
        contentType: false,
        cache: false,

        success: function (data, textStatus, jqXHR) {
            var objData = JSON.parse(data);
            if (objData.success === false) {
                if (objData.wrongData !== undefined) {
                    document.getElementById("wrongData").style.display = 'inherit';
                } else if (objData.accessDenied !== undefined) {
                    document.getElementById("accessDenied").style.display = 'inherit';
                } else if (objData.wrongUpload !== undefined) {
                    document.getElementById("wrongUpload").style.display = 'inherit';
                } else {
                    document.getElementById("wrongDB").style.display = 'inherit';
                }
            } else {
                window.location.reload();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            document.getElementById("errorResponse").style.display = 'inherit';
        },
        beforeSend: function (jqXHR, settings) {},
        complete: function (jqXHR, textStatus) {}
    });
});

document.getElementById('create').onclick = function (e) {
    var createField =  document.getElementById('createField');
    var createBtn =  document.getElementById('create');
    var productContent = document.getElementById('productContent');

    if (createField.style.display === 'inherit') {
        $('#blah').imgAreaSelect({hide : true});
        createField.style.display = 'none';
        productContent.style.display = 'inherit';
        createBtn.innerHTML = "<i class='fa fa-plus-circle'></i>";
    } else {
        createField.style.display = 'inherit';
        productContent.style.display = 'none';
        createBtn.innerHTML = "<i class='fa fa-times-circle'></i>";

    }
};

function edit(e, id) {
    var $this = $(e);
    var $contanier = $("#" + id);

    if ($this.attr('editing') != '1') {
        $this.attr('editing', 1);

        $this.html("");
        $this.html("<i class='fa fa-save'></i>");
        $contanier.find('.name').each(function () {
            var value = $(this).text().toString().trim();
            $this.attr('oldName', value);
            var input = $('<input class="name" />').val(value);
            $(this).replaceWith(input);
        });
    }
    else {
        $this.removeAttr('editing');
        $this.html("");
        $this.html("<i class='fa fa-edit'></i>");
        $contanier.find('.name').each(function () {
            var name = $(this).val().toString().trim();
            var div = $('<div class="name" />').text(name);
            $(this).replaceWith(div);
            save(e, id, name);
        });
    }
}

function save(e, id, name) {

    //get the form data using another method
    var newName = $("input#countryCode").val();
    var dataString = "kindOfSportId=" + id + "&newName="+ name + "&command=update_kind_of_sport";

    var $this = $(e);
    var $contanier = $("#" + id);
    //make the AJAX request, dataType is set to json
    //meaning we are expecting JSON data in response from the server
    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataString,
        dataType: "json",

        //if received a response from the server
        success: function (data, textStatus, jqXHR) {
            //our country code was correct so we have some information to display
            if (data.success === false) {
                console.log("Something really bad happened " + textStatus);

                $contanier.find('.name').each(function () {
                    $(this).text($this.attr("oldName"));
                });
                modal_edit_wrong.style.display = 'block';
            }
        },

        //If there was no resonse from the server
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);

            $contanier.find('.name').each(function () {
                $(this).text($this.attr("oldName"));
            });
            modal_edit_error.style.display = 'block';
        },

        //capture the request before it was sent to server
        beforeSend: function (jqXHR, settings) {},

        //this is called after the response or error functions are finsihed
        //so that we can take some action
        complete: function (jqXHR, textStatus) {}
    });
}

function del(e, id, imageUrl) {

    let $this = $(e);
    let $contanier = $("#" + id);
    let $modal = $("#modal_delete" + id);

    $modal.css("display","none");
    $contanier.css("display","none");
    //get the form data using another method
    let dataString = `productId=${id}&productImageUrl=${imageUrl}&command=delete_product`;
    //make the AJAX request, dataType is set to json
    //meaning we are expecting JSON data in response from the server
    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataString,
        dataType: "json",

        //if received a response from the server
        success: function (data, textStatus, jqXHR) {
            //our country code was correct so we have some information to display
            if (data.success === false) {
                console.log("Something really bad happened " + textStatus);

                $contanier.css("display","inherit");
                modal_delete_wrong.style.display = 'block';
            }
        },

        //If there was no resonse from the server
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);

            $contanier.css("display","inherit");
            modal_delete_error.style.display = 'block';

        },

        //capture the request before it was sent to server
        beforeSend: function (jqXHR, settings) {},

        //this is called after the response or error functions are finsihed
        //so that we can take some action
        complete: function (jqXHR, textStatus) {}
    });
}

/*document.getElementById('create').onclick = function (e) {
   var createField =  document.getElementById('createField');
   var createBtn =  document.getElementById('create');

   if (createField.style.display === 'inherit') {
       createField.style.display = 'none';
       createBtn.innerHTML = "<i class='fa fa-plus-circle'></i>";
   } else {
       createField.style.display = 'inherit';
       createBtn.innerHTML = "<i class='fa fa-times-circle'></i>";

   }
};*/

if (document.getElementById('wrong') !== null) {
    var event = new Event("click");
    create.dispatchEvent(event);
}

