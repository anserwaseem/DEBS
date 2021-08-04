$(document).ready(function () {
  $('[data-toggle="tooltip"]').tooltip();
  var actions = $("table td:last-child").html();
  var accounts = $("table td:first-child").html();
  // Append table with add row form on add new button click
  $(".add-new").click(function () {
    $(this).attr("disabled", "disabled");
    var index = $("table tbody tr:last-child").index();
    var row =
      "<tr>" +
      "<td>" +
      accounts +
      "</td>" +
      /*'<td><input type="text" class="form-control" name="account" id="account"></td>' +*/
      // '<td><div class="row-fluid"><select class="selectpicker" data-live-search="true"><option>Tom Foolery</option><option>Bill Gordon</option><option>Elizabeth Warren</option><option>Mario Flores</option><option>Don Young</option><option>Marvin Martinez</option></select></div></td>' +
      '<td><input type="text" class="form-control" name="debit" id="debit"></td>' +
      '<td><input type="text" class="form-control" name="credit" id="credit"></td>' +
      '<td><input type="text" class="form-control" name="description" id="description"></td>' +
      /*'<td><input type="text" class="form-control" name="maturityDate" id="maturityDate"></td>' +*/
      "<td>" +
      actions +
      "</td>" +
      "</tr>";
    $("table").append(row);
    $("table tbody tr")
      .eq(index + 1)
      .find(".add, .edit")
      .toggle();
    $('[data-toggle="tooltip"]').tooltip();
  });
  // Add row on add button click
  $(document).on("click", ".add", function () {
    var empty = false;
    var input = $(this).parents("tr").find('input[type="text"]');
    input.each(function () {
      if (!$(this).val()) {
        $(this).addClass("error");
        empty = true;
      } else {
        $(this).removeClass("error");
      }
    });
    $(this).parents("tr").find(".error").first().focus();
    if (!empty) {
      input.each(function () {
        $(this).parent("td").html($(this).val());
      });
      $(this).parents("tr").find(".add, .edit").toggle();
      $(".add-new").removeAttr("disabled");
    }
  });
  // Edit row on edit button click
  $(document).on("click", ".edit", function () {
	$(this)
	  .parents("tr")
	  .find("td:not(:last-child, :first-child)")
	  .each(function () {
	    $(this).html(
	      '<input type="text" class="form-control" value="' +
	        $(this).text() +
	        '">'
	    );
	  });
	 /*$(this)
	  .parents("tr")
	  .find('input[name="account"]')
	  .attr("value", $(this).text());
	 $(this)
	  .parents("tr")
	  .find('input[name="credit"]')
	  .attr("value", "0");
	 $(this)
	  .parents("tr")
	  .find('input[name="debit"]')
	  .attr("value", "0");
	 $(this)
	  .parents("tr")
	  .find('input[name="description"]')
	  .attr("value", $(this).text());*/
    $(this).parents("tr").find(".add, .edit").toggle();
    $(".add-new").attr("disabled", "disabled");
  });
  // Delete row on delete button click
  $(document).on("click", ".delete", function () {
    $(this).parents("tr").remove();
    $(".add-new").removeAttr("disabled");
  });
});

/*var $table = $("#table");
var $remove = $("#remove");
var selections = [];

function getIdSelections() {
  return $.map($table.bootstrapTable("getSelections"), function (row) {
    return row.id;
  });
}

function responseHandler(res) {
  $.each(res.rows, function (i, row) {
    row.state = $.inArray(row.id, selections) !== -1;
  });
  return res;
}

function detailFormatter(index, row) {
  var html = [];
  $.each(row, function (key, value) {
    html.push("<p><b>" + key + ":</b> " + value + "</p>");
  });
  return html.join("");
}

function operateFormatter(value, row, index) {
  return [
    '<a class="like" href="javascript:void(0)" title="Like">',
    '<i class="fa fa-heart"></i>',
    "</a>  ",
    '<a class="remove" href="javascript:void(0)" title="Remove">',
    '<i class="fa fa-trash"></i>',
    "</a>",
  ].join("");
}

window.operateEvents = {
  "click .like": function (e, value, row, index) {
    alert("You click like action, row: " + JSON.stringify(row));
  },
  "click .remove": function (e, value, row, index) {
    $table.bootstrapTable("remove", {
      field: "id",
      values: [row.id],
    });
  },
};

function totalTextFormatter(data) {
  return "Total";
}

function totalNameFormatter(data) {
  return data.length;
}

function totalPriceFormatter(data) {
  var field = this.field;
  return (
    "$" +
    data
      .map(function (row) {
        return +row[field].substring(1);
      })
      .reduce(function (sum, i) {
        return sum + i;
      }, 0)
  );
}

function initTable() {
  $table.bootstrapTable("destroy").bootstrapTable({
    height: 550,
    locale: $("#locale").val(),
    columns: [
      [
        {
          field: "state",
          checkbox: true,
          rowspan: 2,
          align: "center",
          valign: "middle",
        },
        {
          title: "Item ID",
          field: "id",
          rowspan: 2,
          align: "center",
          valign: "middle",
          sortable: true,
          footerFormatter: totalTextFormatter,
        },
        {
          title: "Item Detail",
          colspan: 3,
          align: "center",
        },
      ],
      [
        {
          field: "name",
          title: "Item Name",
          sortable: true,
          footerFormatter: totalNameFormatter,
          align: "center",
        },
        {
          field: "price",
          title: "Item Price",
          sortable: true,
          align: "center",
          footerFormatter: totalPriceFormatter,
        },
        {
          field: "operate",
          title: "Item Operate",
          align: "center",
          clickToSelect: false,
          events: window.operateEvents,
          formatter: operateFormatter,
        },
      ],
    ],
  });
  $table.on(
    "check.bs.table uncheck.bs.table " +
      "check-all.bs.table uncheck-all.bs.table",
    function () {
      $remove.prop("disabled", !$table.bootstrapTable("getSelections").length);

      // save your data, here just save the current page
      selections = getIdSelections();
      // push or splice the selections if you want to save all data selections
    }
  );
  $table.on("all.bs.table", function (e, name, args) {
    console.log(name, args);
  });
  $remove.click(function () {
    var ids = getIdSelections();
    $table.bootstrapTable("remove", {
      field: "id",
      values: ids,
    });
    $remove.prop("disabled", true);
  });
}*/

$(".datepicker").datepicker();