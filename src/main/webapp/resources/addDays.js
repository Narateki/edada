$(document).ready(function () {
    let qdays = $("#qdays").val();

    let daysContainer = $("#days-container");
    for(let i = 0; i<qdays; i++){
        let dayContainer = $("<div>", {"id":"dayContainer"+i});
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
            "millage" : [],
            "masl": [],
            "totalRise": []
        };

        for(let i = 0; i<qdays; i++){
            let inRise = $("#rise"+i).val();
            let inMasl = $("#masl"+i).val();
            let inMilage = $("#milage"+i).val();
            data.masl.push(inMasl);
            data.millage.push(inMilage);
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
            success: function(responseJsonObject){
                console.log(responseJsonObject);
            }
        });
    });
});