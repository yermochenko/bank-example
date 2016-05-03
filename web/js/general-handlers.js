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