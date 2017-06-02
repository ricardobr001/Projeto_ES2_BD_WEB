/*JavaScript para adicionar atores dinamicamente*/
$(document).ready(function() {
    var max_fields      = 10; //maximum input boxes allowed
    var wrapper         = $(".input_fields_wrap"); //Fields wrapper
    var add_button      = $(".add_field_button"); //Add button ID

    var x = 1; //initlal text box count
    $(add_button).click(function(e){ //on add input button click
        e.preventDefault();
        if(x < max_fields){ //max input box allowed
            x++; //text box increment
            $(wrapper).append('<div class="row control-group"><div class="form-group col-xs-9 floating-label-form-group controls"><label for="nome">Nome do Ator(a)</label><input type="text" class="form-control" placeholder="Nome do ator(a)" id="name" required data-validation-required-message="Please enter your name."></div><div class="form-group col-xs-3 controls"><button class="remove_field list-group-item list-group-item-danger">Remover</button></div></div>'); //add input box
        }
    });

    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
        e.preventDefault();
        /*Removendo a div row control-group*/
        $(this).parent().parent('div').remove();
        x--;
    })
});

/*Código inserido pelo $(wrapper).append() e removido pelo $(this).parent().parent('div').remove()*/
/*' <div class="row control-group">
        <div class="form-group col-xs-9 floating-label-form-group controls">
            <label for="nome">Nome do Ator(a)</label>
            <input type="text" class="form-control" placeholder="Nome do ator(a)" id="name" required data-validation-required-message="Please enter your name.">
        </div>
        <div class="form-group col-xs-3 controls">
            <button class="remove_field list-group-item list-group-item-danger">Remover</button>
        </div>
    </div>'*/
