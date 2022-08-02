const userAjaxUrl = "admin/users/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: userAjaxUrl,
    updateTable: function () {
        $.get(ctx.ajaxUrl, function (data) {
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
                    "data": "name"
                },
                {
                    "data": "email"
                },
                {
                    "data": "roles"
                },
                {
                    "data": "enabled"
                },
                {
                    "data": "registered"
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

function enableOrDisable(el) {
    let userId = $(el).closest('tr').attr("id");
    let enabled = el.checked;
    $.ajax({
        type: "POST",
        url: ctx.ajaxUrl + userId,
        data: {"enabled": enabled}
    }).done(function () {
        successNoty("Saved");
    });
}