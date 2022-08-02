const mealsAjaxUrl = "ui/meals/";

let filterForm = $('#filter');

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: mealsAjaxUrl,
    updateTable: function () {
        let params = filterForm.serialize();
        $.get(mealsAjaxUrl + "filter", params).done(function (data) {
            ctx.datatableApi.clear().rows.add(data).draw();
        });
    }
}

// $(document).ready(function () {
$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        })
    );
});

function applyFilter() {
    let params = filterForm.serialize();
    $.get(mealsAjaxUrl + "filter", params).done(function (data) {
        ctx.datatableApi.clear().rows.add(data).draw();
    });
}

function clearFilter() {
    filterForm.find(":input").val("")
    ctx.updateTable();
}