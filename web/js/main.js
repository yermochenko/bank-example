var SelectorType = new function() {
	this.ID = 0;
	this.TAG = 1;
	this.CLASS = 2;
}

var SelectorIndexType = new function() {
	this.FIRST = 0;
	this.LAST = 1;
}

var HandlerType = new function() {
	this.ONLOAD = 0;
	this.ONCLICK = 1;
	this.ONSUBMIT = 2;
}

window.onload = function() {
	handlers.register();
}

var handlers = new function() {
	this.__handlers = new Array();

	this.add = function(selector, type, handler) {
		this.__handlers.push({
			'selector': selector,
			'type': type,
			'handler': handler
		});
	}

	this.register = function() {
		for(var i = 0; i < this.__handlers.length; i++) {
			if(this.__handlers[i].type !== HandlerType.ONLOAD) {
				var nodes = elements(this.__handlers[i].selector);
				for(var j = 0; j < nodes.length; j++) {
					switch(this.__handlers[i].type) {
						case HandlerType.ONCLICK:
							nodes[j].onclick = this.__handlers[i].handler;
							break;
						case HandlerType.ONSUBMIT:
							nodes[j].onsubmit = this.__handlers[i].handler;
							break;
					}
				}
			}
		}
		for(var i = 0; i < this.__handlers.length; i++) {
			if(this.__handlers[i].type === HandlerType.ONLOAD) {
				this.__handlers[i].handler();
			}
		}
	}
}

function elements(selector, parent) {
	selector = selector !== undefined ? selector : null;
	parent = parent !== undefined ? parent : document;
	var result = new Array();
	if(selector === null) {
		result.push(window);
	} else if(selector instanceof Array) {
		for(var i = 0; i < selector.length; i++) {
			var temp = elements(selector[i], parent);
			for(var j = 0; j < temp.length; j++) {
				var isUnique = true;
				for(var k = 0; isUnique && k < result.length; k++) {
					if(temp[j] === result[k]) {
						isUnique = false;
					}
				}
				if(isUnique) {
					result.push(temporary[j]);
				}
			}
		}
	} else if(selector.sequence !== undefined) {
		result.push(parent);
		for(var i = 0; i < selector.sequence.length; i++) {
			var temp = new Array();
			for(var j = 0; j < result.length; j++) {
				var newElements = elements(selector.sequence[i], result[j]);
				for(var k = 0; k < newElements.length; k++) {
					temp.push(newElements[k]);
				}
			}
			result = temp;
		}
		if(result.length == 1 && result[0] === document) {
			result = new Array();
		}
	} else if(selector.union !== undefined) {
		if(selector.union.length > 0) {
			var newElements = elements(selector.union[0], parent);
			for(var i = 0; i < newElements.length; i++) {
				result.push(newElements[i]);
			}
			for(var i = 1; i < selector.union.length; i++) {
				var newElements = elements(selector.union[i], parent);
				var temp = new Array();
				for(var j = 0; j < result.length; j++) {
					for(var k = 0; k < newElements.length; k++) {
						if(result[j] === newElements[k]) {
							temp.push(result[j]);
							break;
						}
					}
				}
				result = temp;
			}
		}
	} else if(selector.type !== undefined) {
		switch(selector.type) {
			case SelectorType.ID:
				if(parent.getElementById !== undefined) {
					result.push(parent.getElementById(selector.value));
				}
				break;
			case SelectorType.TAG:
				if(parent.getElementsByTagName !== undefined) {
					var temp = parent.getElementsByTagName(selector.value);
					for(var i = 0; i < temp.length; i++) {
						result.push(temp[i]);
					}
				}
				break;
			case SelectorType.CLASS:
				if(parent.getElementsByClassName !== undefined) {
					var temp = parent.getElementsByClassName(selector.value);
					for(var i = 0; i < temp.length; i++) {
						result.push(temp[i]);
					}
				}
				break;
		}
		if(selector.attribute !== undefined) {
			temp = new Array();
			for(var i = 0; i < result.length; i++) {
				if(result[i].getAttribute(selector.attribute) !== null) {
					temp.push(result[i]);
				}
			}
			result = temp;
		}
		if(selector.parent !== undefined && selector.parent) {
			temp = new Array();
			for(var i = 0; i < result.length; i++) {
				temp.push(result[i].parentNode);
			}
			result = temp;
		} else if(selector.index !== undefined && result.length > 0) {
			temp = new Array();
			switch(selector.index) {
				case SelectorIndexType.FIRST:
					temp.push(result[0]);
					break;
				case SelectorIndexType.LAST:
					temp.push(result[result.length - 1]);
					break;
			}
			result = temp;
		}
	}
	return result;
}