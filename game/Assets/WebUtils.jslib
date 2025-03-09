mergeInto(LibraryManager.library, {
	OnStarted: function(){
		window.dispatchReactUnityEvent(
			"OnStarted"
		);
	},
	GetLocalStorageValue: function(name) {
		var value = localStorage[UTF8ToString(name)];
		
		var length = lengthBytesUTF8(value) + 1;
		var buffer = _malloc(length);
		stringToUTF8(value, buffer, length);

		return buffer;
	},
}); 