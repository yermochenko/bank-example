handlers.add({
	'type': SelectorType.ID,
	'value': "user-edit-form"
}, HandlerType.ONSUBMIT, function() {
	return validateForm(this, [{
		id: "login-id",
		message: "Поле «Имя пользователя» не может быть пустым и может содержать только большие и маленькие латинские символы, цифры и знак подчёркивания",
		checker: function(value) {
			return checkRegexp(value, "^\\w+$");
		}
	}]);
});