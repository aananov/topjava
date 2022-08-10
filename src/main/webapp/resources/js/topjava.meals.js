const mealAjaxUrl = "profile/meals/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: mealAjaxUrl,
    updateTable: function () {
        $.ajax({
            type: "GET",
            url: mealAjaxUrl + "filter",
            data: $("#filter").serialize()
        }).done(updateTableByData);
    }
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(mealAjaxUrl, updateTableByData);
}

$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "ajax": {
                "url": mealAjaxUrl,
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime",
                    "render": function (data, type, row) {
                        if (type === "display") {
                            return formatDate(data);
                        }
                        return data;
                    }
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false,
                    "render": renderEditBtn
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false,
                    "render": renderDeleteBtn
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {
                $(row).attr("data-meal-excess", data.excess);
            }
        })
    );

    $("#dateTime").datetimepicker({
        format: 'Y-m-d H:i',

    });

    $("#startDate").datetimepicker({
        timepicker: false,
        format: 'Y-m-d',
        onShow: function () {
            let endDate = $("#endDate");
            this.setOptions({
                maxDate: endDate.val() ? endDate.val() : false
            })
        },
    });

    $("#endDate").datetimepicker({
        timepicker: false,
        format: 'Y-m-d',
        onShow: function () {
            let startDate = $("#startDate");
            this.setOptions({
                minDate: startDate.val() ? startDate.val() : false
            })
        },
    });

    $("#startTime").datetimepicker({
        datepicker: false,
        format: 'H:i',
        onShow: function () {
            let endTime = $("#endTime");
            this.setOptions({
                maxTime: endTime.val() ? endTime.val() : false
            })
        },
    });

    $("#endTime").datetimepicker({
        datepicker: false,
        format: 'H:i',
        onShow: function () {
            let startTime = $("#startTime");
            this.setOptions({
                minTime: startTime.val() ? startTime.val() : false
            })
        },
    });
});