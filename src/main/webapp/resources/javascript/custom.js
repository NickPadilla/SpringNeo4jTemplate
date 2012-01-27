//all built on top of jquery so the lastest build is a must.

$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		var name = this.name;
		if(name.substr(0,1) != "_"){
			if (o[this.name]) {
				if (!o[this.name].push) {
					o[this.name] = [o[this.name]];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		}
	});
	return o;
};

$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		var name = this.name;
		if(name.substr(0,1) != "_"){
			if (o[this.name]) {
				if (!o[this.name].push) {
					o[this.name] = [o[this.name]];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		}
	});
	return o;
};

$.postJSON = function(url, data, callback) {
	return jQuery.ajax({
		'type': 'POST',
		'url': url,
		'contentType': 'application/json',
		'data': JSON.stringify(data),
		'dataType': 'json',
		'success': callback,
		'cache' : false
	});
};

$.getJSONAsync = function(url, async, callback) {
	return jQuery.ajax({
		'type': 'GET',
		'url': url,
		'contentType': 'application/json',
		'data': '',
		'dataType': 'json',
		'success': callback,
		'cache' : false,
		'async' : async
	});
};

$.getData = function(url, data, callback) {
	return jQuery.ajax({
		'type': 'GET',
		'url': url,
		'contentType': 'application/json',
		'data': JSON.stringify(data),
		'dataType': 'json',
		'success': callback,
		'cache' : false,
		'async' : true
	});
};

function displayNoResults(){
	var div = $('#displayTable');
	div.append("<h2>Nothing found matching your search!</h2>");
	$('input[type="button"][id|="export"]').hide();
}

function createTable(data){
	var form = null;
	var div = $('#displayTable');
	var table = $('<table>').attr('id', 'table').attr('border', '1').css('border-collapse','collapse');
	var newForm = false;
	var hasHeader = false;
	$.each(data.hits, function(index, fields) {		
		if(form != fields.formId){
			// here we need to create a new table and display the table headers 			
			if(form != null){
				div.append(table);
				table = $('<table>').attr('id', 'table'+fields.formId).attr('border', '1').css('border-collapse','collapse');			
			}
			newForm = true;
			hasHeader = false;
		}else{
			newForm = false;
		}
		form = fields.formId;
		$.each(fields.hitFields, function(index2, data) {
			if(data.header){
				// ensure the headers are all center and a colspan of 2 
				if(index2 != 0 || index2 != '0'){
					// sub headers since they are not the first 
					table.append($('<tr>').append($("<th>")
							.text(data.dataValue).attr('colspan', 2)).attr('align', 'center'));
				}else{
					if(newForm){					 
						if(data.dataValue != null && data.dataValue != ""){
							hasHeader = true;
							table.append($('<tr>').append($("<th>").append($('<h2>')
									.text(data.dataValue)).attr('colspan', 2)).attr('align', 'center').attr('colspan', 2));
						}
					}else{
						if(hasHeader == false && data.dataValue != null && data.dataValue != ""){
							table.find('tr:first').before($("<tr>").append($("<th>").append($('<h2>').text(data.dataValue))
									.attr('colspan', 2)).attr('colspan', 2));							
							hasHeader = true;
						}						
					}
				}
			}else{
				table.append($("<tr>").append($("<td>").text(data.dataName)).append($('<td>')
						.text(data.dataValue)).attr('align', 'center'));
			}
		});
		// add a blank row seperating the forms 
		table.append($('<tr>').append($("<th>").text("").attr('colspan', 2)).addClass('rowSeparator'));
	});
	div.append(table);
}

function onChange(newPageValue) {
	searchFormModel.page = newPageValue;
	var searchOptionsRet = ko.toJS(searchFormModel);
	$.postJSON("search", searchOptionsRet, function(data){			
		$("#displayTable").empty();
		createTable(data);
	});
}

$(document).ready(function() {
	$('input:submit,input:button').button();
	$('input[type="button"][id|="export"]').hide();
	$('#numberPerPage').spinner({
		min : 0,
		max : 100,
		increment : 'fast'
	});
	//Effects for search box 
	$("#query").focus(function(e) {
		if ($(this).attr("value") == "Search All...") {
			$(this).attr("value", "");
		}
		$(this).addClass("active");
	});
	$("#query").blur(function(e) {
		if ($(this).attr("value") == "") {
			$(this).attr("value", "Search All...");
		}
		$(this).removeClass("active");
	});
	$("#query").focus();
	$("#searchForm").submit(function() {
		$(".pagination").empty();
		$("#displayTable").empty();
		$('input[type="button"][id|="export"]').show();
		submitted = false;
		searchFormModel.page = 1;
		var searchOptionsRet = ko.toJS(searchFormModel);
		$.postJSON("search", searchOptionsRet, function(data) {
			if(data != null && data.hits != null && data.hits != ""){
				createTable(data);
				var total = data.totalNumberOfItems;
				var numPerPage = searchOptionsRet.numberPerPage;
				// create our pagination controls 
				$(".pagination").paging(total, {
					format: "[< (qq-) nncnn (-pp) >]",
					perpage: numPerPage,
					page: 1, 
					onSelect: function (page) {
						if(submitted){
							onChange(page);
						}
						submitted = true;
						return true; 
					},
					onFormat: function(type) {
						switch (type) {
						case 'block':
							if (!this.active){
								return '<span class="disabled">' + this.value + '</span>';
							} else if (this.value != this.page){
								return '<em><a href="#' + this.value + '">' + this.value + '</a></em>';
							} else {
								return '<span class="current">' + this.value + '</span>';
							}
						case 'next':
							if (this.active) {
								return '<a href="#' + this.value + '" class="next">Next ></a>';
							}
							return '<span class="disabled">Next ></span>';
						case 'prev':
							if (this.active) {
								return '<a href="#' + this.value + '" class="prev">< Previous</a>';
							}
							return '<span class="disabled">< Previous</span>';
						case 'first':
							if (this.active) {
								return '<a href="#' + this.value + '" class="first">|< First</a>';
							}
							return '<span class="disabled">|< First</span>';
						case 'last':
							if (this.active) {
								return '<a href="#' + this.value + '" class="prev">Last >|</a>';
							}
							return '<span class="disabled">Last >|</span>';
						case 'fill':
							if (this.active) {
								return "...";
							} else {
								return "";
							}
						case 'left':
							if (this.active) {
								return '<a href="#' + this.value + '">' + this.value + '</a>';
							}else { 
								return "";
							}
						case 'right':
							if (this.active) {
								return '<a href="#' + this.value + '">' + this.value + '</a>';
							}else{
								return "";
							}
						}
					}
				}); 
			}else{
				// show no results message 
				displayNoResults();
			}
		});
		return false;
	});
});