$('document').ready(function(){
    let listaMembros = document.getElementById('listaDespesas');
    $('.botaoP').click(function(){
        var idp = $(this).data('idp');
        var nomep = $(this).data('nomep');
        /*
        let linha = listaMembros.insertRow();
        linha.insertCell(0).innerHTML = idp;
        linha.insertCell(1).innerHTML = nomep;

        linha.insertCell(2).style.textAlign = "right";
        let btn = document.createElement('button');
        btn.className = 'btn btn-danger botaoM';
        btn.innerHTML = "<i style='width=15px; height=15px;' class='fas fa-minus-square'></i>";
        btn.setAttribute('type', 'button');
        btn.setAttribute('data-idp', idp);
        btn.setAttribute('data-nomep', nomep);
        btn.onclick = function(){
            $(this).parents('tr').remove();
        }

        linha.insertCell(2).append(btn);
        linha.insertCell(2).innerHTML = `<button type='button' class='btn btn-danger botaoM' data-idp= ${idp} data-nomep=${nomep}><i class='far fa-plus-square'></i></button>`;
        
       */
        var row = $("<tr>");
        let btn = document.createElement('button');
        let td = document.createElement('td');
        btn.className = 'btn btn-danger botaoM';
        btn.innerHTML = "<i class='fas fa-minus-square'></i>";
        btn.setAttribute('type', 'button');
        btn.setAttribute('data-idp', idp);
        btn.setAttribute('data-nomep', nomep);
        btn.onclick = function(){
           $(this).parents('tr').remove();
           //deleteRow(this.parentNode.parentNode.rowIndex);
        }
        td.appendChild(btn);
        
        row.append($("<td>" + idp + "</td>"))
           .append($("<td>" + nomep + "</td>"))
           .append(td);
           
        $("#listaMembros tbody").append(row);
    });
    
    $('#salvar').click(function(){
        var nome = $('#nome').val();
        var descricao = $('#descricao').val();
        
        let elementos = document.getElementsByClassName('botaoM');
        let ids = [];
        for(var i=0; i<elementos.length; i++){
            ids.push($(elementos[i]).data('idp'));
        }   
        console.log(ids);
        let idsMembros = "";
        for(var i = 0; i<ids.length; i++){
            idsMembros += ids[i] + " / ";
        }
        
        $.ajax({
            url: "equipe",
            type: "POST",
            data: {
                idUsuario : 3,
                nome : nome,
                descricao : descricao,
                idsMembros : idsMembros,
                acao : 2
            },
            success: function(responseText){
                $("#modal_titulo_div").attr("class", "modal-header text-success");
                $("#modal_titulo").text(responseText);
                $('#modalInscricao').modal('show');
                $('#modal_btn').text("OK!");
                $('#modal_btn').attr("class", "btn btn-success");
                $('#modal_btn').click({
                    //$(location).attr('href', 'http://www.google.com');
                });
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $("#modal_titulo").text("Erro ao criar a equipe");
                $("#modal_titulo_div").attr("class", "modal-header text-danger");
                $("#modal_btn").attr("class", "btn btn-danger");
                $('#modalInscricao').modal('show');
            }
        });
    });
});


