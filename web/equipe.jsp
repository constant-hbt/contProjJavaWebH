<%-- 
    Document   : equipe
    Created on : 21/11/2019, 15:52:53
    Author     : henrique
--%>

<%@page import="model.Equipes"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="controller.Inscricoes"%>
<%@page import="controller.EquipesData"%>
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
        <script src="js/equipe.js"></script>
        
        <script>
            function removerMembro(botao){
                console.log("Entrou no remover");
                $(botao).parents('tr').remove();
            }
            
            function pesquisa(){
                let nome = document.getElementById("pesquisa").value;
                console.log(nome);
                
                $.ajax({
                    url: "equipe",
                    type: "POST",
                    data: {
                        idUsuario : 3,
                        acao : 1,
                        nome: nome
                    },
                    success: function(responseText){
                        let participantes = [];
                        participantes = responseText.split(" # ");
                        
                        $('#listaParticipantes tr').remove();
                        
                        for(let i of participantes){
                            part = [];
                            part = i.split(" / ");
                            console.log("id: " + part[0] + "nome: " + part[1]);
                            
                            if(!(part[0] == "" || part[0] == undefined || part[1] == "" || part[1] == undefined)){
                                let listaM = [];
                                listaM = document.getElementsByClassName('botaoM');
                                let naoListaMembros = true;
                                for(let mem of listaM){
                                    if($(mem).data('idp') == part[0]){
                                        naoListaMembros = false;
                                    }
                                }
                                if(naoListaMembros){
                                    var row = $("<tr>");
                                    let btn = document.createElement('button');
                                    let td = document.createElement('td');
                                    btn.className = 'btn btn-success botaoP';
                                    btn.innerHTML = "<i class='far fa-plus-square'></i>";
                                    btn.setAttribute('type', 'button');
                                    btn.setAttribute('data-idp', part[0]);
                                    btn.setAttribute('data-nomep', part[1]);
                                    btn.onclick = function(){
                                        let idpart = $(this).data('idp');
                                        let nomepart = $(this).data('nomep');
                                        var row = $("<tr>");
                                        let btn = document.createElement('button');
                                        let td = document.createElement('td');
                                        btn.className = 'btn btn-danger botaoM';
                                        btn.innerHTML = "<i class='fas fa-minus-square'></i>";
                                        btn.setAttribute('type', 'button');
                                        btn.setAttribute('data-idp', idpart);
                                        btn.setAttribute('data-nomep', nomepart);
                                        btn.onclick = function(){
                                           $(this).parents('tr').remove();
                                        }
                                        td.appendChild(btn);

                                        row.append($("<td>" + idpart + "</td>"))
                                           .append($("<td>" + nomepart + "</td>"))
                                           .append(td);

                                        $("#listaMembros tbody").append(row);
                                    }
                                    td.appendChild(btn);

                                    row.append($("<td>" + part[0] + "</td>"))
                                       .append($("<td>" + part[1] + "</td>"))
                                       .append(td);

                                    $("#listaParticipantes").append(row);
                                }
                            }
                        }
                        
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert("Erro");
                    }
                });
            }
            
            function compararPartMembros(){
                let participantes = document.getElementsByClassName("botaoP");
                let membros = document.getElementsByClassName("botaoM");
                console.log(participantes);
                for(let part of participantes){
                    for(let membro of membros){
                        if($(part).data('idp') === $(membro).data('idp')){
                            $(part).parents('tr').remove();
                        }
                    }
                }
            }
        </script>
    </head>

    <body onload="compararPartMembros()">
        <%
            int idUsuario = 3;
            String acao = "alterar";
            Inscricoes DAO = new Inscricoes();
            EquipesData DAOE = new EquipesData();
            int idp = DAO.pegarIdParticipante(idUsuario);
            int ide = DAO.verificarInscTodosEventos(idp);
            int idEquipe = 12;
            Equipes equipe = new Equipes();
            if(idEquipe != 0){
                equipe = DAOE.getEquipeById(idEquipe);
            }
        %>
        <div class="container">
            <div class="row mt-5">
                <div class="col-md-3">

                </div>
    
                <div class="col-md-6">
                    <form action="" method="" name="formEquipe">
                        <div data-idequipe="<%=idEquipe%>" name="idequipe" id="idequipe">
                            
                        </div>
                        <div class="form-group">
                            <label for="nome" class="control-label">Nome da equipe</label>
                            <input type="text" id="nome" name="nome" placeholder="Nome da equipe" class="form-control" value="<%if(idEquipe != 0){out.println(equipe.getNome());} %>" />
                        </div>
                        <div class="form-group">
                            <label for="descricao" class="control-label">Descrição da equipe</label>
                            <textarea name="descricao" id="descricao" placeholder="Descrição" rows="3" class="form-control"><%if(idEquipe != 0){out.println(equipe.getDescricao());} %></textarea>
                        </div>
                        <input type="hidden" id="membros" name="membros">
                    </form>
                </div>
                
                <div class="col-md-3">
                    
                </div>
            </div>
            
            <div class="row my-5">
                
                <div class="col-md-6">
                    <h2 class="h4 my-3 text-center">Lista de participantes do evento</h2>
                    <div class="input-group">
                        <input class="form-control" type="text" placeholder="Pesquisar" id="pesquisa" name="pesquisa" onkeyup="pesquisa()"/>
                        <span class="input-group-addon">
                            <button class="fas fa-search" style="background:transparent;border:none"></button>
                        </span>
                    </div>
                    
                    <table class="table my-3" >
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nome</th>
                                <th></th>
                                <!-- <i class="far fa-plus-square"></i>-->
                            </tr>
                        </thead>

                        <tbody id="listaParticipantes">
                            <% 
                                List<String> participantes = new ArrayList();
                                participantes = DAOE.listarPartEvento(ide, idp);
                                for(String part: participantes){
                                    String[] pa = part.split(";");
                                    String nome = pa[0];
                                    int idparticipante = Integer.parseInt(pa[1]);
                            %>
                            <tr>
                                <td><%=idparticipante%></td>
                                <td><%=nome%></td>
                                <td style="text-align: right">
                                    <button type="button" class="btn btn-success botaoP" data-idp="<%=idparticipante%>" data-nomep="<%=nome%>">
                                        <i class="far fa-plus-square"></i>
                                    </button>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
                
                <div class="col-md-6">
                    <h2 class="h4 my-3 text-center">Lista de membros da equipe</h2>
                    
                    <table class="table" id="listaMembros">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nome</th>
                                <th></th>
                            </tr>
                        </thead>

                        <tbody >
                            <% 
                                if(idEquipe != 0){
                                    List<String> membros = new ArrayList();
                                    membros = DAOE.listarMembrosEquipe(idEquipe);
                                    for(String membro: membros){
                                        String[] m = membro.split(";");
                                        String nomeMembro = m[0];
                                        int idMembro = Integer.parseInt(m[1]);
                            %>
                            <tr>
                                <td><%=idMembro%></td>
                                <td><%=nomeMembro%></td>
                                <td style="text-align: right">
                                    <button type="button" class="btn btn-danger botaoM" data-idp="<%=idMembro%>" data-nomep="<%=nomeMembro%>" onclick="removerMembro(this)"/>
                                        <i class="fas fa-minus-square"></i>
                                    </button>
                                </td>
                            </tr>
                            <%
                                }
                            }
                            %>
                        </tbody>
                    </table>
                </div>
                
            </div>
            
            <div class="mt-4 d-flex bd-highlight mb-3">
                <button class="btn btn-success p-2 bd-highlight mr-2" id="salvar" data-acao="<%=acao%>">Salvar</button>
                <button class="btn btn-warning p-2 bd-highlight mx-2" id="cancelar">Cancelar</button>
                <% if(idEquipe != 0){%>
                <button class="btn btn-danger ml-auto p-2 bd-highlight" id="inativar">Excluir equipe</button>
                <%}%>
            </div>
            
            <!-- Modal -->
            <div class="modal fade" id="modalInscricao" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div id="modal_titulo_div" class="modal-header text-success">
                            <h5 class="modal-title" id="modal_titulo"></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-success" id="modal_btn" data-dismiss="modal">OK!</button>
                        </div>
                    </div>
                </div>
            </div> <!-- Fim do modal -->
            
            <!-- Modal -->
            <div class="modal fade" id="modalExcluirEquipe" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div id="modal_titulo_div2" class="modal-header text-danger">
                            <h5 class="modal-title" id="modal_titulo2">Tem certeza que deseja excluir a equipe?</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body" id="modal_conteudo">
                            Se sua equipe estiver inscrita em algum sub-evento, desinscreva ela primeiramente!
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" id="modal_btn_excluir" data-dismiss="modal">Excluir</button>
                            <button type="button" class="btn btn-secondary" id="modal_bt_voltar" data-dismiss="modal">Voltar</button>
                        </div>
                    </div>
                </div>
            </div> <!-- Fim do modal -->
        </div> <!-- Fim container-->
    </body>
</html>
