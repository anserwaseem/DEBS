function initializeJS() {
	//tool tips
	jQuery(".tooltips").tooltip();

	//popovers
	jQuery(".popovers").popover();

	//custom scrollbar
	//for html
	jQuery("html").niceScroll({
		styler: "fb",
		cursorcolor: "#007AFF",
		cursorwidth: "6",
		cursorborderradius: "10px",
		background: "#F7F7F7",
		cursorborder: "",
		zindex: "1000",
	});
	//for sidebar
	jQuery("#sidebar").niceScroll({
		styler: "fb",
		cursorcolor: "#007AFF",
		cursorwidth: "3",
		cursorborderradius: "10px",
		background: "#F7F7F7",
		cursorborder: "",
	});
	// for scroll panel
	jQuery(".scroll-panel").niceScroll({
		styler: "fb",
		cursorcolor: "#007AFF",
		cursorwidth: "3",
		cursorborderradius: "10px",
		background: "#F7F7F7",
		cursorborder: "",
	});

	//sidebar dropdown menu
	jQuery("#sidebar .sub-menu > a").click(function() {
		var last = jQuery(".sub-menu.open", jQuery("#sidebar"));
		jQuery(".menu-arrow").removeClass("arrow_carrot-right");
		jQuery(".sub", last).slideUp(200);
		var sub = jQuery(this).next();
		if (sub.is(":visible")) {
			jQuery(".menu-arrow").addClass("arrow_carrot-right");
			sub.slideUp(200);
		} else {
			jQuery(".menu-arrow").addClass("arrow_carrot-down");
			sub.slideDown(200);
		}
		var o = jQuery(this).offset();
		diff = 200 - o.top;
		if (diff > 0) jQuery("#sidebar").scrollTo("-=" + Math.abs(diff), 500);
		else jQuery("#sidebar").scrollTo("+=" + Math.abs(diff), 500);
	});

	// sidebar menu toggle
	jQuery(function() {
		function responsiveView() {
			var wSize = jQuery(window).width();
			if (wSize <= 768) {
				jQuery("#container").addClass("sidebar-close");
				jQuery("#sidebar > ul").hide();
			}

			if (wSize > 768) {
				jQuery("#container").removeClass("sidebar-close");
				jQuery("#sidebar > ul").show();
			}
		}
		jQuery(window).on("load", responsiveView);
		jQuery(window).on("resize", responsiveView);
	});

	jQuery(".toggle-nav").click(function() {
		if (jQuery("#sidebar > ul").is(":visible") === true) {
			jQuery("#main-content").css({
				"margin-left": "0px",
			});
			jQuery("#sidebar").css({
				"margin-left": "-180px",
			});
			jQuery("#sidebar > ul").hide();
			jQuery("#container").addClass("sidebar-closed");
		} else {
			jQuery("#main-content").css({
				"margin-left": "180px",
			});
			jQuery("#sidebar > ul").show();
			jQuery("#sidebar").css({
				"margin-left": "0",
			});
			jQuery("#container").removeClass("sidebar-closed");
		}
	});
}

jQuery(document).ready(function() {
	initializeJS();
});

// displpaying dataTable: for pages; asset, capital, customer, expense, liability, product, vendor
/*$("#dataTable").DataTable();*/ 

// validating that required fields are not null: for add & update pages of asset, customer, expense, liability, and vendor. 
function validateForm() {
	if (document.forms["validateUpdateForm"]["name"].value == "" || document.forms["validateUpdateForm"]["officeName"].value == "" || document.forms["validateUpdateForm"]["address"].value == "" || document.forms["validateUpdateForm"]["phone"].value == "" || document.forms["validateUpdateForm"]["amount"].value == "") {
		alert("Please fill out required fields.");
		return false;
	}
}

// validating that required fields are not null: for add & update page of product.
function validateFormProduct() {
	if (document.forms["formProduct"]["name"].value == "" || document.forms["formProduct"]["quantity"].value == "" || document.forms["formProduct"]["openingStock"].value == "" || document.forms["formProduct"]["costPrice"].value == "" || document.forms["formProduct"]["salePrice"].value == "") {
		alert("Please fill out required fields.");
		return false;
	}
}

// validating that required fields are not null: for add & update page of partner.
function validateFormPartner() {
	if (document.forms["formPartner"]["name"].value == "" || document.forms["formPartner"]["amount"].value == "") {
		alert("Please fill out required fields.");
		return false;
	}
}