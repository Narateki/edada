$(document).ready(function () {

    $("#btn-ingr").click(function(event) {
        event.preventDefault();

        $.getJSON("getIngredients", function (data) {
            $("#tbody-ingr tr").remove();
            //console.log(data);
            let tbl = $("#tbody-ingr");
            let qingr = $("#q-ingr").val();
            for (let i = 0; i<qingr; i++) {
                let tr = $("<tr>", {
                    'class': "ingr-input"
                });
                let tds = $("<td>");
                let select = $("<select>", {"id":"s-ingr"+i});
                $.each(data,function (i,item) {
                    select.append($("<option>",{'text':item.name, 'value':item.id}));
                });
                tds.append(select);
                tr.append(tds);
                let td = $("<td>");
                let input = $("<input>", {"id":"inp-ingr"+i});
                td.append(input);
                tr.append(td);
                tbl.append(tr);
            }
        });
    });

    $("#btn-invent").click(function(event) {
        event.preventDefault();

        $.getJSON("getInventory", function (data) {
            $("#tbody-invent tr").remove();
            //console.log(data);
            let tbl = $("#tbody-invent");
            let qinvent = $("#q-invent").val();
            for (let i = 0; i<qinvent; i++) {
                let tr = $("<tr>", {
                    'class': "invent-input"
                });
                let tds = $("<td>");
                let select = $("<select>", {"id":"s-invent"+i});
                $.each(data,function (i,item) {
                    select.append($("<option>",{'text':item.name, 'value':item.id}));
                });
                tds.append(select);
                tr.append(tds);
                tbl.append(tr);
            }
        });
    });

    $("#btn-add").click(function(event) {
        event.preventDefault();
        let data = {
            "name": $("#name").val(),
            "description": $("#description").val(),
            "category_id": $("#category").val(),
            "ingrs_id": [],
            "ingrs_q": [],
            "invent_id": []
        };

        let qingr = $("#q-ingr").val();
        for (let i = 0; i<qingr; i++) {
            data.ingrs_id.push($("#s-ingr"+i).val());
            data.ingrs_q.push($("#inp-ingr"+i).val());
        }
        let qinvent = $("#q-invent").val();
        for (let i = 0; i<qinvent; i++) {
            data.invent_id.push($("#s-invent"+i).val());
        }

        let token = $("meta[name='_csrf']").attr("content");

        $.ajax({
            url:"addjs",
            headers: {"X-CSRF-TOKEN": token},
            type:"POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            async: false,
            cache: false,
            processData:false,
            success: function(resposeJsonObject){
                alert("all good");
            }
        });

        // $.post("addjson", data, function (data) {
        //         alert("all good");
        // });

    });
});