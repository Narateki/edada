$(document).ready(function () {
    let qdays = $("#qdays").val();

    let daysContainer = $("#days-container");
    for(let i = 0; i<qdays; i++){
        let dayContainer = $("<div>", {"id":"dayContainer"+i});

        let hDay = $("<h3>", {"class":"text-info", "text":"День"+(i+1)});
        dayContainer.append(hDay);

        let formGrMilage = $("<div>", {"class":"form-group"});
        let formGrMasl = $("<div>", {"class":"form-group"});
        let formGrRise = $("<div>", {"class":"form-group"});

        let lableMilage = $("<lable>", {"for":"milage"+i, "text":"Километраж, км"});
        let lableMasl = $("<lable>", {"for":"masl"+i, "text":"Высота над уровнем моря, м"});
        let lableRise = $("<lable>", {"for":"rise"+i, "text":"Суммарный подъем, м"});

        let inRise = $("<input>", {"id":"rise"+i, "class": "form-control", "value":"1000"});
        let inMasl = $("<input>", {"id":"masl"+i, "class": "form-control", "value":"1000"});
        let inMilage = $("<input>", {"id":"milage"+i, "class": "form-control", "value":"20"});

        formGrMilage.append(lableMilage);
        formGrMilage.append(inMilage);
        dayContainer.append(formGrMilage);

        formGrMasl.append(lableMasl);
        formGrMasl.append(inMasl);
        dayContainer.append(formGrMasl);

        formGrRise.append(lableRise);
        formGrRise.append(inRise);
        dayContainer.append(formGrRise);

        let selectB = $("<select>", {"id":"b"+i, "class": "form-control"});
        let selectL = $("<select>", {"id":"l"+i, "class": "form-control"});
        let selectD = $("<select>", {"id":"d"+i, "class": "form-control"});
        dayContainer.append(selectB);
        dayContainer.append(selectL);
        dayContainer.append(selectD);

        daysContainer.append(dayContainer);
    }

    $("#btn-load").click(function(event) {
        event.preventDefault();
        let data = {
            "id" : $("#rId").val(),
            "mileage" : [],
            "masl": [],
            "totalRise": []
        };

        for(let i = 0; i<qdays; i++){
            let inRise = $("#rise"+i).val();
            let inMasl = $("#masl"+i).val();
            let inMilage = $("#milage"+i).val();
            data.masl.push(inMasl);
            data.mileage.push(inMilage);
            data.totalRise.push(inRise);
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
            success: function(res){
                console.log(res);
                $.each(res,function (i,day) {
                    let selectB = $("#b"+i);
                    let selectL = $("#l"+i);
                    let selectD = $("#d"+i);
                    let recIds = Object.keys(day);
                    $.each(recIds,function (j,recId) {
                        selectB.append($("<option>", {'text': day[recId], 'value': recId}));
                        selectL.append($("<option>", {'text': day[recId], 'value': recId}));
                        selectD.append($("<option>", {'text': day[recId], 'value': recId}));
                    });
                });
                let btns = $("#btns");
                let btnSave =  $("<button>", {'id':'btn-save','text': "Сохранить", "class": "btn btn-primary"});
                btns.append(btnSave);

                $("#btn-save").click(function(event) {
                    event.preventDefault();

                    let data = {
                        "id": $("#rId").val(),
                        "breakfast": [],
                        "lunch": [],
                        "dinner": []
                    };

                    for(let i = 0; i<qdays; i++) {
                        let selectB = $("#b"+i);
                        let selectL = $("#l"+i);
                        let selectD = $("#d"+i);
                        data.breakfast.push(selectB.val());
                        data.lunch.push(selectL.val());
                        data.dinner.push(selectD.val());
                    }

                    let token = $("meta[name='_csrf']").attr("content");

                    $.ajax({
                        url:"saveDays",
                        headers: {"X-CSRF-TOKEN": token},
                        type:"POST",
                        contentType: "application/json; charset=utf-8",
                        data: JSON.stringify(data),
                        async: false,
                        cache: false,
                        processData:false,
                        success: function(res){
                            let container = $(".container");
                            container.remove();
                            let body = $("body");
                            let h3 = $("<h3>", {"text":"Рацион успешно добавлен.", "class":"text-success"});
                            body.append(h3);
                        }
                    });

                });
            }
        });
    });


});