function setDates(from, to) {
	document.getElementById('from').value = from;
	document.getElementById('to').value = to;
}

function order(by, type) {
	document.getElementById('orderBy').value = by;
	document.getElementById('orderType').value = type;
	document.forms[0].submit();
}