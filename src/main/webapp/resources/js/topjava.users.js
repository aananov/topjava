const userAjaxUrl = "admin/users/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: userAjaxUrl,
    updateTable: function () {
        $.get(ctx.ajaxUrl, refreshTableData);
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
    let row = $(el).closest('tr');
    let userId = $(row).attr("id");
    let enabled = el.checked;
    $.ajax({
        type: "POST",
        url: ctx.ajaxUrl + userId,
        data: {"enabled": enabled}
    }).done(function () {
        successNoty("Saved");
        $(row).attr("data-user-enabled", enabled);
    }).fail(function () {
        $(el).prop("checked", !enabled)
    });
}