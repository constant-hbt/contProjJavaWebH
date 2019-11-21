<%-- 
    Document   : equipe
    Created on : 21/11/2019, 15:52:53
    Author     : henrique
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Equipe</title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    
        <!-- Font Awesome -->
        <script defer src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
        
        <script>
            $('document').ready(function() {
                $('body').load(function() {
                    let listaParticipantes = $('#listaParticipantes');
                    listaParticipantes.innerHTML = "";
                    $.ajax({
                        url: "equipe",
                        type: "POST",
                        data: {
                            idUsuario : 3,
                            acao : 1
                        },
                        success: function(responseText){
                            let part = [];
                            part = responseText.split("|");
                            for(let i = 0; i < part.size; i++){
                                let p = [];
                                p = participante[i].split(";");
                                let linha = listaParticipantes.insertRow();
                                linha.insertCell(0).innerHTML = p[0];
                                linha.insertCell(1).innerHTML = p[1];

                                let btn = document.createElement('button')
                                btn.className = 'btn btn-success'
                                btn.innerHTML = '<i class="far fa-plus-square"></i>';

                                btn.onclick = function(){

                                }
                                linha.insertCell(2).append(btn)
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            alert("Erro: " + errorThrow);
                        }
                    });
                });
            /*
                let idsp = [];
                $('#salvar').click(function() {
                    
                    var nome = $('#nome').val();
                    var descricao = $('#descricao').val();
                    
                    $('#lista-membros tr').each(function() {
                        idsp.push($(this).find('.idp').html());
                    });
                });
                */
            });
        </script>
    </head>

    <body>
        <div class="container">
            <div class="row mt-5">
                <div class="col-md-3">

                </div>
    
                <div class="col-md-6">
                    <form action="" method="" name="formEquipe">
                        <div class="form-group">
                            <input type="text" id="nome" name="nome" placeholder="Nome da equipe" class="form-control">
                        </div>
                        <div class="form-group">
                            <textarea name="descricao" id="descricao" placeholder="Descrição" rows="5" class="form-control"></textarea>
                        </div>
                        <input type="hidden" id="membros" name="membros">
                    </form>

                    <div class="row">
                        <div class="col">
                            <table class="table" >
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Nome</th>
                                        <th></th>
                                        <!-- <i class = "fas fa-times"></i>-->
                                    </tr>
                                </thead>
                    
                                <tbody id="listaMembros">
                                    
                                </tbody>
                            </table>
                        </div>
                    </div>

                    
                    <div class="my-5">
                        <div class="input-group">
                            <input class="form-control" type="text" placeholder="Pesquisar" id="pesquisar" name="pesquisar">
                            <span class="input-group-addon">
                                <button class="fas fa-search" style="background:transparent;border:none"></button>
                            </span>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col">
                            <table class="table" >
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Nome</th>
                                        <th></th>
                                        <!-- <i class="far fa-plus-square"></i>-->
                                    </tr>
                                </thead>
                    
                                <tbody id="listaParticipantes">
                                    
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="mt-4 mb-3">
                        <button class="btn btn-success" id="salvar">Salvar</button>
                        <button class="btn btn-warning" id="cancelar">Cancelar</button>
                    </div>
                    
                </div> <!-- Fim conteúdo -->
    
                <div class="col-md-3">
    
                </div>
            </div> <!-- Fim row -->
        </div> <!-- Fim container -->
    </body>
</html>
