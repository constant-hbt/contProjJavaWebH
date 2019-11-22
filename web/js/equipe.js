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
    /*
    $(".botaoM").click(function() {
        $(this).parents('tr').remove();
    });
    
    $('.botaoM').click(function(){
        deleteRow(this.parentNode.parentNode.rowIndex);
    });
    */
    
});


