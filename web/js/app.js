/*JavaScript para adicionar atores  e idiomas dinamicamente*/
$(document).ready(function() {
    var max_fields      = 100; //numero maximo de input permitido

    /*Adiciona caixas de nome de ator dinâmicamente*/
    var x = 1;
    // reconhece o clique no botão
    $(".add_field_button_ator").click(function(e){
        e.preventDefault();
        if(x < max_fields){
            x++;
            // adiciona input quando clica
            $(".input_fields_wrap_ator").append('<div class="row control-group"><div class="form-group col-xs-9 floating-label-form-group controls"><label for="nome">Nome do Ator(a)</label><input type="text" class="form-control" placeholder="Nome do ator(a)" id="nome" name="nome" required data-validation-required-message="Please enter your name."></div><div class="form-group col-xs-3 controls"><button class="remove_field list-group-item list-group-item-danger">Remover</button></div></div>'); //add input box
        }
    });

    /*Adiciona caixas de idioma dinâmicamente*/
    var y = 1;
    // reconhece o clique no botão
    $(".add_field_button_idioma").click(function(e){
        e.preventDefault();
        if(y < max_fields){
            y++;
            // adiciona input quando clica
            $(".input_fields_wrap_idioma").append('<div class="row control-group"><div class="form-group col-xs-9 floating-label-form-group controls"><label for="idioma">Idioma</label><input type="text" class="form-control" placeholder="Idioma" id="idioma" name="idioma" required data-validation-required-message="Please enter a language."></div><div class="form-group col-xs-3 controls"><button class="remove_field list-group-item list-group-item-danger">Remover</button></div></div>'); //add input box
        }
    });

    /*Remove caixas de ator*/
    $(".input_fields_wrap_ator").on("click",".remove_field", function(e){
        e.preventDefault();
        /*Removendo a div row control-group*/
        $(this).parent().parent('div').remove();
        x--;
    })

    /*Removendo caixas de idioma*/
    $(".input_fields_wrap_idioma").on("click",".remove_field", function(e){
        e.preventDefault();
        /*Removendo a div row control-group*/
        $(this).parent().parent('div').remove();
        y--;
    })
});