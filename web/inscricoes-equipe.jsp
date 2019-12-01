<%-- 
    Document   : inscricoes-equipe
    Created on : 01/12/2019, 11:33:20
    Author     : henrique
--%>

<%@page import="java.util.Date"%>
<%@page import="model.Subeventos"%>
<%@page import="controller.EquipesData"%>
<%@page import="model.Equipes"%>
<%@page import="java.util.List"%>
<%@page import="controller.Inscricoes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Inscrições</title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        
        <script>
            function atualizarStatus(botao){
                return $(botao).data("inscritoev");
            }
            
            
            $('document').ready(function(){
                
                $(".insc-equipe-sub").click(function() {
                    var botaoSub = this;
                    var idSub = $(botaoSub).data("idsubevento");
                    var idEquipe = $(botaoSub).data("idequipe");
                    
                    if($(botaoSub).text() == "Inscrever equipe"){
                        $.ajax({
                            url: "inscequipesub",
                            type: "POST",
                            data: {
                                idUsuario : 3,
                                idSubevento : idSub,
                                idEquipe : idEquipe,
                                acao: "inscrever"
                            },
                            success: function(responseText){
                                $("#modal_titulo_div").attr("class", "modal-header text-info");
                                $("#modal_titulo").text(responseText);
                                $('#modalInscricao').modal('show');
                                $(botaoSub).attr("data-statussub", "1");
                                $(botaoSub).text("Desinscrever equipe");
                                $(botaoSub).attr("class", "btn btn-outline-danger");
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                $("#modal_titulo").text(errorThrown);
                                $("#modal_titulo_div").attr("class", "modal-header text-danger");
                                $("#modal_btn").attr("class", "btn btn-danger");
                                $('#modalInscricao').modal('show');
                            }
                        });
                    }else{
                        $.ajax({
                            url: "inscequipesub",
                            type: "POST",
                            data: {
                                idUsuario : 3,
                                idSubevento : idSub,
                                idEquipe : idEquipe,
                                acao : "desinscrever"
                            },
                            success: function(responseText){
                                $("#modal_titulo").text(responseText);
                                $("#modal_titulo_div").attr("class", "modal-header text-info");
                                $("#modal_btn").attr("class", "btn btn-success");
                                $(botaoSub).text("Inscrever equipe");
                                $(botaoSub).attr("class", "btn btn-outline-success");
                                $(botaoSub).attr("data-inscritoev", "2");
                                $('#modalInscricao').modal('show');
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                $("#modal_titulo").text(errorThrown);
                                $("#modal_titulo_div").attr("class", "modal-header text-danger");
                                $("#modal_btn").attr("class", "btn btn-danger");
                                $('#modalInscricao').modal('show');
                            }
                        });
                    }
                });
                
                $(".insc-subexpirada").click(function() {
                    $("#modal_titulo_div").attr("class", "modal-header text-secondary");
                    $("#modal_titulo").text("Não é permitido se inscrever/desinscrever fora do período definido!");
                    $('#modalInscricao').modal('show');
                });
            });
        </script>
    </head>
    
    <body>
        <div class="container">
            <div class="row">

                <div>
                    <div class="alert alert-danger mt-3" role="alert">
                        <p>Veja as equipes em que está participando!</p>
                    </div>
                    
                    <% 
                        try{
                            int idUsuario = 3;
                            Inscricoes DAO = new Inscricoes();
                            EquipesData DAOE = new EquipesData();
                            int idp = DAO.pegarIdPart(idUsuario);
                            int ide = DAO.verificarInscTodosEventos(idp);
                            List<Equipes> equipes = DAOE.pegarEquipesPartInsc(idp);
                            if(equipes.size() == 0){
                                out.println("<h2>Não existem eventos disponíveis!</h2>");
                            }else{
                                for(Equipes equipe: equipes){
                                List<String> participantes = DAOE.listarMembrosEquipe(equipe.getIdequipe());
                    %>

                    <div class="jumbotron jumbotron-fluid mt-5 p-3">
                        <h1 id="nomeEquipe" class="h4"><%= equipe.getNome() %></h1>
                        <p>ID: <span id="idEquipe<%= equipe.getIdequipe() %>"><%= equipe.getIdequipe() %></span></p>
                        <p id="descEquipe" class="lead"><%= equipe.getDescricao() %></p>
                        <h5 class="h5 text-center" >Membros da equipe</h5>
                        <div class="row my-3">
                            <% 
                                for(String part: participantes){
                                    String[] p = part.split(";");
                            %>
                            <div class="col-md-4"><span class="mx-3">ID: <%= p[1]%></span><span class="mx-3">Nome: <%= p[0] %></span></div>
                            <%  }%>
                        </div>
                        <div class="dropdown">
                            <button id="botaoVerSubeventos<%= equipe.getIdequipe() %>" class="btn btn-info" type="button" id="dropdownMenuButton" data-toggle="collapse" data-target="#subeventos<%= equipe.getIdequipe()%>">
                                Ver sub-eventos
                            </button>
                            <div class="collapse navbar-collapse" id="subeventos<%=equipe.getIdequipe()%>">
                                <div class="container">
                                    <ul class="navbar-nav ml-auto">
                                        <% 
                                            List<Subeventos> subeventos = DAOE.listarSubeventosParaEquipes(ide);
                                            if(subeventos.size() == 0){
                                                out.println("<p>Não existem sub-eventos disponíveis para este evento!</p>");
                                            }else{
                                                for(Subeventos subevento: subeventos){
                                        %>
                                        <li>
                                            <div class="card my-4">
                                                <div class="card-body">
                                                    <h4 class="card-title"> <%= subevento.getNome() %> </h4>
                                                    <p>ID: <span id="idSubevento<%= subevento.getIdsubevento() %>"><%= subevento.getIdsubevento() %></span></p>
                                                    <p class="card-text"><%= subevento.getDescricao()%></p>
                                                    <div class="card-text row">
                                                        <div class="col-md-3">Começa em: <%= subevento.getDatahorainicio()%> </div>
                                                        <div class="col-md-3">Termina em: <%= subevento.getDatahorafim()%></div>
                                                        <div class="col-md-3">Inscrições começam em: <%= subevento.getDatainicioinsc() %></div>
                                                        <div class="col-md-3">Inscrições terminam em: <%= subevento.getDatafiminsc() %></div>
                                                        <div class="col-md-3">Quantidade mínima de participantes em cada equipe: <%= subevento.getQtdemin() %></div>
                                                        <div class="col-md-3">Quantidade máxima de participantes em cada equipe: <%= subevento.getQtdemax() %></div>
                                                        <div class="col-md-3">Quantidade máxima de equipes: <%= subevento.getQtdemaxequipes() %></div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <p class="card-text">Local: sala<%= subevento.getSalas().getIdsala()%>, descrição: <%= subevento.getSalas().getDescricao() %></p>
                                                        </div>
                                                        <div class="col-md-2">
                                                        <% 
                                                        if(DAOE.verificarSeLiderEquipe(idp, equipe.getIdequipe())){
                                                            if(DAO.verificarEquipeInscSub(equipe.getIdequipe(), subevento.getIdsubevento())){ 
                                                        %>
                                                            <button class="btn btn-outline-danger insc-equipe-sub" id="inscSub<%=subevento.getIdsubevento() %>" data-idsubevento="<%=subevento.getIdsubevento() %>" data-statussub = "1" data-idequipe="<%= equipe.getIdequipe()%>" >Desinscrever equipe</button>
                                                        <% }else{ %>
                                                            <button class="btn btn-outline-primary insc-equipe-sub" id="inscSub<%=subevento.getIdsubevento() %>" data-idsubevento="<%=subevento.getIdsubevento() %>" data-statussub = "2" data-idequipe="<%= equipe.getIdequipe()%>" >Inscrever equipe</button>
                                                        <%  }
                                                        }%>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                        <%
                                                } 
                                            }
                                        %>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%          
                                }
                            }
                        }catch(Exception e){
                            System.out.println("Erro: " + e.getMessage());
                        }
                    %>
                    
                </div>
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
                            <button type="button" class="btn btn-success" id="modal_btn" data-dismiss="modal">Voltar</button>
                        </div>
                    </div>
                </div>
            </div> <!-- Fim do modal -->
        </div>
    </body>
</html>
