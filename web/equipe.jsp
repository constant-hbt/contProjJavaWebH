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
            /*
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

                                let btn = document.createElement('button');
                                btn.className = 'btn btn-success';
                                btn.innerHTML = '<i class="far fa-plus-square"></i>';

                                linha.insertCell(2).append(btn);
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            alert("Erro: " + errorThrow);
                        }
                    });
                });
                let idsp = [];
                $('#salvar').click(function() {
                    
                    var nome = $('#nome').val();
                    var descricao = $('#descricao').val();
                    
                    $('#lista-membros tr').each(function() {
                        idsp.push($(this).find('.idp').html());
                    });
                });
                
            });*/
            function retirarMembro(botao){
                $(botao).parents('tr').remove();
            }
        </script>
    </head>

    <body>
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
                            <textarea name="descricao" id="descricao" placeholder="Descrição" rows="5" class="form-control"><%if(idEquipe != 0){out.println(equipe.getDescricao());} %></textarea>
                        </div>
                        <input type="hidden" id="membros" name="membros">
                    </form>
                </div>
                
                <div class="col-md-3">
                    
                </div>
            </div>
            
            <div class="row my-5">
                
                <div class="col-md-6">
                   
                    <div class="input-group">
                        <input class="form-control" type="text" placeholder="Pesquisar" id="pesquisar" name="pesquisar">
                        <span class="input-group-addon">
                            <button class="fas fa-search" style="background:transparent;border:none"></button>
                        </span>
                    </div>
                    
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
                                    <button type="button" class="btn btn-danger botaoM" data-idp="<%=idMembro%>" data-nomep="<%=nomeMembro%>" onclick="retirarMembro(this)">
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
            
            

            <div class="mt-4 mb-3">
                <button class="btn btn-success" id="salvar" data-acao="<%=acao%>">Salvar</button>
                <button class="btn btn-warning" id="cancelar">Cancelar</button>
                <% if(idEquipe != 0){%>
                <button class="btn btn-danger" id="inativar">Excluir equipe</button>
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
