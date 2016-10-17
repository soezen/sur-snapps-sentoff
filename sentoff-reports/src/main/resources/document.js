var console = (function() {
	
	function log(msg) {
		systemOut.println(msg);
	}
	
	return {
		log: log
	}
})();

var document = {};
document.name = 'document';

document.querySelector = function(selector) {
	console.log("querySelector(" + selector + ")");
	return document.elements[selector];
};
document.querySelectorAll = function(selector) {
	console.log("querySelectorAll(" + selector + ")");
};
document.toString = function() {
	var result = '';
	for (var i in this.elements) {
		var element = document.elements[i];
		result += element.toString();
	}
	return result;
};
document.createElement = function(name) {
	return document.createElementNS(null, name);
};
document.createElementNS = function(ns, name) {
	var element = new document.Element(ns, name, document);
	element.setSelf(element);
	return element;
};

document.Element = function(ns, name, ownerDocument) {
	console.log("createElement(" + name + ")");
	
	var self = null;
	var attributes = {};
	var style = (function() {
		var properties = {};
		
		function setProperty(name, value, priority) {
			console.log('setProperty ' + name + ' ' + value);
			properties[name] = value;
		}
		
		function removeProperty(name) {
			console.log('removeProperty');
			properties[name] = null;
		}
		
		function toString() {
			var result = '';
			for (var i in properties) {
				var property = properties[i];
				result += i + ':' + property + ';';
			}
			return result;
		}
		
		function isEmpty() {
			return Object.keys(properties).length == 0;
		}
		
		return {
			setProperty: setProperty,
			removeProperty: removeProperty,
			isEmpty: isEmpty,
			toString: toString,
			name: 'Element.style'
		} 
	})();
	var children = [];
	
	function appendChild(child) {
		console.log('appendChild(' + child.name + ')');
		children.push(child);
		return child;
	}

	function setAttribute(name, value) {
		console.log('add attribute: ' + name + " " + value);
		attributes[name] = value;
	}
	
	function toString() {
		return '<' + name + getNamespaceString() + getAttributesString() + '>' + getContentString() + '</' + name + '>';
	}
	
	function getNamespaceString() {
		if (ns != null) {
			return ' xmlns="' + ns + '"'; 
		}
		return '';
	}
	
	function getAttributesString() {
		var result = '';
		for (var i in attributes) {
			var attribute = attributes[i];
			result += ' ' + i + '="' + attribute + '"';
		}
		if (!style.isEmpty()) {
			result += ' style="' + style.toString() + '"';
		}
		if (self.height) {
			result += ' height="' + self.height + '"';
		}
		return result;
	}
		
	function getContentString() {
		var result = '';
		for (var i in children) {
			var child = children[i];
			result += child.toString();
		}
		if (self.textContent) {
			result += self.textContent;
		}
		return result;
	}
	
	function querySelector(selector) {
		return { name: 'Element.querySelector'};
	}
	
	function querySelectorAll(selector) {
		return { name: 'Element.querySelectorAll'};
	}
	
	function insertBefore(child, next) {
		var index = indexOf(next);
		children.splice(index, 0, child);
	}
	
	function indexOf(child) {
		for (var i in children) {
			if (children[i] == child) {
				return i;
			}
		}
		return -1;
	}
	
	return {
		setAttribute: setAttribute,
		ownerDocument: ownerDocument,
		appendChild: appendChild,
		children: children,
		toString: toString,
		querySelector: querySelector,
		querySelectorAll: querySelectorAll,
		insertBefore: insertBefore,
		style: style,
		setSelf: function(a) { self = a},
		name: 'Element'
	};
};


document.elements = {
		body: document.createElement('body')
};