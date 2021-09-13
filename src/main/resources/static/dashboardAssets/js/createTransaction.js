var arrHead = new Array();	// array for header.
arrHead = ['', 'Accounts', 'Debit', 'Credit', 'Description'];

// first create TABLE structure with the headers. 
function createTable() {
	var empTable = document.createElement('table');
	empTable.setAttribute('id', 'empTable'); // value of table's 'id' attribute is set to 'empTable'

	var tr = empTable.insertRow(-1); // insert a row at the end of the table
	for (var h = 0; h < arrHead.length; h++) {
		var th = document.createElement('th'); // create table headers
		th.innerHTML = arrHead[h];
		tr.appendChild(th);
	}

	var div = document.getElementById('cont');
	div.appendChild(empTable);  // add the TABLE to the container.
}

// now, add a new row to the TABLE.
function addNewRow() {
	var empTab = document.getElementById('empTable');

	var rowCnt = empTab.rows.length;   // table row count. //returns the amount of ALL <tr> elements within the table.
	var tr = empTab.insertRow(rowCnt); // the table row. //If index is -1 or equal to the number of rows, the row is appended as the last row (mdn)

	for (var c = 0; c < arrHead.length; c++) {
		var td = document.createElement('td'); // table definition.
		td = tr.insertCell(c);

		if (c == 0) {      // the first column.
			// add a button in every new row in the first column.
			var button = document.createElement('input');

			// set input attributes.
			button.setAttribute('type', 'button');
			button.setAttribute('value', 'Remove');

			// add button's 'onclick' event.
			button.setAttribute('onclick', 'removeRow(this)');

			td.appendChild(button);
		} else if (c == 1) {
			/*<![CDATA[*/
			var customers =/*[[${customerAccounts}]]*/ '';
			console.log("customers\n" + customers);
			/*]]>*/

			var customerOptions = "";
			for (var o = 0; o < customers.length; o++) {
				customerOptions += "<option>" + customers[o] + "</option>";
			}


			const div = document.createElement('div')
			div.className = 'row-fluid';

			/*const select=document.createElement('select');
			select.className = 'selectpicker';
			select.setAttribute('data-live-search', 'true');
			select.setAttribute('data-live-search-placeholder', 'Search');
			
			div.appendChild(select);
			div.firstElementChild.innerHTML+=customerOptions;*/

			div.innerHTML = `
				    <select class="selectpicker" data-live-search="true"
						data-live-search-placeholder="Search">
						<option value=""> -- Nothing Selected -- </option>
						<optgroup label="Customers">
							`+ customerOptions + `
						</optgroup>
						<!--<optgroup label="Vendors">
							<option th:text="\${vendor}"
								th:each="vendor : \${vendorAccounts}"></option>
						</optgroup>
						<optgroup label="Assets">
							<option th:text="\${asset}"
								th:each="asset : \${assetAccounts}">
							</option>
						</optgroup>
						<optgroup label="Liabilities">
							<option th:text="\${liability}"
								th:each="liability : \${liabilityAccounts}"></option>
						</optgroup>
						<optgroup label="Expenses">
							<option th:text="\${expense}"
								th:each="expense : \${expenseAccounts}"></option>
						</optgroup>
						<optgroup label="Partner">
							<option th:text="\${partner}"
								th:each="partner : \${partnerAccounts}"></option>
						</optgroup>-->
					</select>
				  `;


			td.appendChild(div);
			$('.selectpicker').selectpicker('render');
		} else {
			// 3rd, 4th and 5th column, will have textbox.
			var ele = document.createElement('input');
			ele.setAttribute('type', 'text');
			ele.setAttribute('value', '');

			td.appendChild(ele);
		}
	}
}

// delete TABLE row function.
function removeRow(oButton) {
	var empTab = document.getElementById('empTable');
	empTab.deleteRow(oButton.parentNode.parentNode.rowIndex); // button -> td -> tr.
}

// function to extract and submit table data.
function submit() {
	var myTab = document.getElementById('empTable');
	var arrValues = new Array();

	// loop through each row of the table.
	for (row = 1; row < myTab.rows.length; row++) {
		// loop through each cell in a row.
		for (c = 0; c < myTab.rows[row].cells.length; c++) {
			var element = myTab.rows.item(row).cells[c];
			if (element.childNodes[0].getAttribute('type') == 'text') {
				arrValues.push("'" + element.childNodes[0].value + "'");
			}
		}
	}

	// The final output.
	document.getElementById('output').innerHTML = arrValues;
	//console.log (arrValues);   // you can see the array values in your browsers console window. Thanks :-)
}