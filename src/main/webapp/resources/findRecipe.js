// $(document).ready(function () {
//
//     $("#btn-ingr").click(function (event) {
//         event.preventDefault();
//
//         $.getJSON("getIngredients", function (data) {
//             $("#tbody-ingr tr").remove();
//             //console.log(data);
//             let tbl = $("#tbody-ingr");
//             let qingr = $("#q-ingr").val();
//             for (let i = 0; i < qingr; i++) {
//                 let tr = $("<tr>", {
//                     'class': "ingr-input"
//                 });
//                 let tds = $("<td>");
//                 let select = $("<select>", {"id": "s-ingr" + i});
//                 $.each(data, function (i, item) {
//                     select.append($("<option>", {'text': item.name, 'value': item.id}));
//                 });
//                 tds.append(select);
//                 tr.append(tds);
//                 tbl.append(tr);
//             }
//         });
//     });
//
//     // $("#btn-invent").click(function (event) {
//     //     event.preventDefault();
//     //
//     //     $.getJSON("getInventory", function (data) {
//     //         $("#tbody-invent tr").remove();
//     //         //console.log(data);
//     //         let tbl = $("#tbody-invent");
//     //         let qinvent = $("#q-invent").val();
//     //         for (let i = 0; i < qinvent; i++) {
//     //             let tr = $("<tr>", {
//     //                 'class': "invent-input"
//     //             });
//     //             let tds = $("<td>");
//     //             let select = $("<select>", {"id": "s-invent" + i});
//     //             $.each(data, function (i, item) {
//     //                 select.append($("<option>", {'text': item.name, 'value': item.id}));
//     //             });
//     //             tds.append(select);
//     //             tr.append(tds);
//     //             tbl.append(tr);
//     //         }
//     //     });
//     // });
// });