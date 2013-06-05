function updateRate(rate) {
	document.getElementById('rate').value = rate;
}

function updateConvertLabel(label) {
	document.getElementById('convertLabel').innerHTML = label;
}

function updateCurrencyRate(isIn) {
	var currency1 = document.getElementById('currencyTo').value;
	var currency2 = document.getElementById('currencyFrom').value;

	var sign1 = document.getElementById('sign' + currency1).value;
	var sign2 = document.getElementById('sign' + currency2).value;
	var label = isIn ? sign2 + " -> " + sign1 : sign1 + " -> " + sign2;

	if (currency1 == currency2) {
		updateRate(1);
		updateConvertLabel(label)
		return;
	}
	var id = currency2 + "_" + currency1;
	var rate = document.getElementById(id).value;
	updateRate(rate);
	updateConvertLabel(label)
}

function enableExchange(b) {
	document.getElementById('rate').disabled = !b;
	document.getElementById('currencyTo').disabled = !b;
	document.getElementById('convertLabel').style.display = b ? "block" : "none";
}