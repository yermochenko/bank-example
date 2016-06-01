/* кликабельные строки таблиц */
handlers.add({
	'sequence': [
		{
			'type': SelectorType.TAG,
			'value': "table"
		}, {
			'type': SelectorType.TAG,
			'value': "tr",
			'attribute': "id"
		}
	]
}, HandlerType.ONCLICK, function() {
	window.location = this.id;
});

/* выпадающие пункты главного меню */

var currentOpenMenu = null;
var dropMenuClicked = false;

handlers.add(null, HandlerType.ONLOAD, function() {
	var menus = elements({
		'sequence': [
			{
				'type': SelectorType.ID,
				'value': "header"
			}, {
				'type': SelectorType.TAG,
				'value': "ol"
			}
		]
	});
	for(var i = 0; i < menus.length; i++) {
		menus[i].style.display = "none";
	}
});

handlers.add(null, HandlerType.ONCLICK, function() {
	if(currentOpenMenu !== null) {
		if(!dropMenuClicked) {
			currentOpenMenu.style.display = "none";
			currentOpenMenu = null;
		} else {
			dropMenuClicked = false;
		}
	}
});

handlers.add({
	'sequence': [
		{
			'type': SelectorType.ID,
			'value': "header"
		}, {
			'type': SelectorType.TAG,
			'value': "ol",
			'parent': true
		}, {
			'type': SelectorType.TAG,
			'value': "a",
			'index': SelectorIndexType.FIRST
		}
	]
}, HandlerType.ONCLICK, function() {
	var menu = elements({
		'type': SelectorType.TAG,
		'value': "ol",
		'index': SelectorIndexType.FIRST
	}, this.parentNode);
	if(menu[0].style.display === "none") {
		if(currentOpenMenu !== null && menu[0] !== currentOpenMenu) {
			currentOpenMenu.style.display = "none";
		}
		menu[0].style.display = "block";
		currentOpenMenu = menu[0];
		dropMenuClicked = true;
	}
	return false;
});