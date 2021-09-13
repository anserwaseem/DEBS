$(document).ready(function() {
	$('[data-toggle="tooltip"]').tooltip();
	var accounts = $("table td:first-child select").get(0).outerHTML;
	var actions = $("table td:last-child").html();
	// Append table with add row form on add new button click
	$(".add-new").click(function() {
		$(this).attr("disabled", "disabled");
		var index = $("table tbody tr:last-child").index();
		var row =
			"<tr>" +
			"<td>" +
			accounts +
			"</td>" +
			/*"<td><div class=\"row-fluid\"><select class=\"selectpicker\" data-live-search=\"true\"><optgroup label=\"Customers\"><option th:text=\"${customer}\" th:each=\"customer : ${customerAccounts}\"></option></optgroup><optgroup label=\"Vendors\"><option th:text=\"${vendor}\" th:each=\"vendor : ${vendorAccounts}\"></option></optgroup><optgroup label=\"Assets\"><option th:text=\"${asset}\" th:each=\"asset : ${assetAccounts}\"></option></optgroup><optgroup label=\"Liabilities\"><option th:text=\"${liability}\" th:each=\"liability : ${liabilityAccounts}\"></option></optgroup><optgroup label=\"Expenses\"><option th:text=\"${expense}\" th:each=\"expense : ${expenseAccounts}\"></option></optgroup><optgroup label=\"Partner\"><option th:text=\"${partner}\" th:each=\"partner : ${partnerAccounts}\"></option></optgroup></select></div></td>"+*/
			/*'<td><input type="text" class="form-control" name="account" id="account"></td>' +*/
			// '<td><div class="row-fluid"><select class="selectpicker" data-live-search="true"><option>Tom Foolery</option><option>Bill Gordon</option><option>Elizabeth Warren</option><option>Mario Flores</option><option>Don Young</option><option>Marvin Martinez</option></select></div></td>' +
			'<td><input type="text" class="form-control" name="debit"></td>' +
			'<td><input type="text" class="form-control" name="credit"></td>' +
			'<td><input type="text" class="form-control" name="description"></td>' +
			/*'<td><input type="text" class="form-control" name="maturityDate"></td>' +*/
			"<td>" +
			actions +
			"</td>" +
			"</tr>";
		$("table tbody").append(row);
		$("table tbody tr")
			.eq(index + 1)
			.find(".add, .edit")
			.toggle();
		$('[data-toggle="tooltip"]').tooltip();
		$('.selectpicker').selectpicker('render');
	});
	// Add row on add button click
	$(document).on("click", ".add", function() {
		var empty = false;
		var input = $(this).parents("tr").find('input[type="text"]');//:not(:first)
		input.each(function() {
			if (!$(this).val()) {
				$(this).addClass("error");
				empty = true;
			} else {
				$(this).removeClass("error");
			}
		});
		$(this).parents("tr").find(".error").first().focus();
		if (!empty) {
			input.each(function() {
				$(this).parent("td").html($(this).val());
			});
			$(this).parents("tr").find(".add, .edit").toggle();
			$(".add-new").removeAttr("disabled");
		}
	});
	// Edit row on edit button click
	$(document).on("click", ".edit", function() {
		$(this)
			.parents("tr")
			.find("td:not(:last-child, :first-child)")
			.each(function() {
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
	$(document).on("click", ".delete", function() {
		$(this).parents("tr").remove();
		$(".add-new").removeAttr("disabled");
	});
	// Submit all data on button click
	$(document).on("click", ".commit", function() {
		var myTab = document.getElementById('transactionTable');
		/*console.log ("myTab\n"+myTab.innerHTML);*/

		// save label of each optgroup
		var optGroupLabels = new Array();
		var optGroupTags = document.getElementsByTagName("optgroup");
		for (var o = 0; o < optGroupTags.length; o++) {
			optGroupLabels.push(optGroupTags.item(o).getAttribute('label'));
		}
		optGroupLabels = [... new Set(optGroupLabels)];// save only unique values
		console.log(optGroupLabels);

		// save label of each selected optgroup
		var selectedOptGroupLabels = new Array();
		// find all elements having class name = "optgroup-", and then find class "selected" among each of those elements. Finally, get the optgroup number(s) of selected option by the user. 
		document.querySelectorAll("*[class^=\"optgroup-\"]").forEach((element) => {
			var ele = element.className;
			var indexOfEle = ele.search("selected");
			if (indexOfEle != -1) {// if found
				var optGroupNumber = ele[indexOfEle - 2];// -2 because indexOfEle is the starting index of "selected" class, -2 will get the targetted optgroup number
				selectedOptGroupLabels.push(optGroupLabels[optGroupNumber - 1]);//-1 because optGroupsLabels array is starting from 0, while optgroups start from 1*/
			}
		}); console.log(selectedOptGroupLabels);

		var arrValues = new Array();
		var errorInInput = false;
		// loop through each row of the table.
		for (row = 1; row < myTab.rows.length; row++) {
			//save the selected option of user of each row
			var element = myTab.rows.item(row).cells[0].children[0].children[1].getAttribute('title');// table -> all rows -> current row -> first column -> its first child (div of row-fluid) -> its first child (di of dropdown) -> its second child (button) -> selected option by user 

			/*HTMLCollection [<li class="selected active">, <a id="bs-select-1-0">, <li class="optgroup-5 selected active">, <a id="bs-select-1-21">] (4)*/

			/*var selected_option = $(this).find(":selected"); // get selected option for the changed select only
			var selected_value = selected_option.val();
			var optgroup = selected_option.parent().attr('label');

			console.log("\n", selected_option, "\n", selected_value, "\n", optgroup, "\n")*/





			/*var collection = myTab.rows.item(row).cells[0].children[0].children[2].children[1].children[0].getElementsByClassName("selected");
			var optGroup = document.getElementsByClassName("optgroup-1")[2].outerHTML;
			var optGroupIndex = optGroup.search("optgroup-");
			console.log(optGroup, optGroup[optGroupIndex + 9]);// +9 to get to the number of optgroup*/

			/*console.log(myTab.rows.item(row).cells[0].children[0].children[0].children.getElementsByTagName("optgroup"));*/
			/*console.log(document.getElementsByTagName("optgroup").item(0).getAttribute('label'));*/

			/*for (var i = 0; i < collection.length; i++) {
				if (collection[i].find("optgroup")) {
					return true;
				}
			}*/

			arrValues.push(selectedOptGroupLabels.shift());

			if (element == "-- Nothing Selected --") {
				errorInInput = true;
				break;
			}
			arrValues.push(element);

			// loop through each cell in a row.
			for (c = 1; c < myTab.rows[row].cells.length - 1; c++) {
				element = myTab.rows.item(row).cells[c];

				if (c < 3) {//if it's debit or credit, check that their value must only be digit(s)
					if (/^\d+$/.test(element.innerHTML)) {
						arrValues.push(element.innerHTML);
					} else {
						errorInInput = true;
					}
				} else {
					arrValues.push(element.innerHTML);
				}
				if (errorInInput)
					break;
			}
		}

		// The final output.
		if (errorInInput)
			document.getElementById('text').value = "Wrong input format";
		else
			document.getElementById('text').value = arrValues.toString();
		console.log(arrValues.toString());
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

/*$(".datepicker").datepicker();*/