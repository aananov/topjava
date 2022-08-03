const mealsAjaxUrl = "ui/meals/";

let filterForm = $('#filter');

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: mealsAjaxUrl,
    updateTable: function () {
        let params = filterForm.serialize();
        $.get(mealsAjaxUrl + "filter", params)
            .done(refreshTableData);
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
                    "desc"
                ]
            ]
        })
    );
});

function applyFilter() {
    ctx.updateTable();
}

function clearFilter() {
    $(filterForm)[0].reset();
    $.get(ctx.ajaxUrl, refreshTableData);
}