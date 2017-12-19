(function() {

	/**
	 * Variables
	 */
	var user_id = 'demo_profile_id';

	/**
	 * Initialize
	 */
	function init() {
		// Register event listeners
		$('start-btn').addEventListener('click', retrieve);
	}
	
	function showWarningMessage(msg) {
		var itemList = $('message');
		itemList.innerHTML = '<p class="notice">'
				+ msg + '</p>';
	}

	function showErrorMessage(msg) {
		var itemList = $('message');
		itemList.innerHTML = '<p class="notice">'
				+ msg + '</p>';
	}
	// -----------------------------------
	// Helper Functions
	// -----------------------------------
	/**
	 * A helper function that creates a DOM element <tag options...>
	 * 
	 * @param tag
	 * @param options
	 * @returns
	 */
	function $(tag, options) {
		if (!options) {
			return document.getElementById(tag);
		}

		var element = document.createElement(tag);

		for ( var option in options) {
			if (options.hasOwnProperty(option)) {
				element[option] = options[option];
			}
		}

		return element;
	}

	function hideElement(element) {
		element.style.display = 'none';
	}

	function showElement(element, style) {
		var displayStyle = style ? style : 'block';
		element.style.display = displayStyle;
	}

	/**
	 * AJAX helper
	 * 
	 * @param method -
	 *            GET|POST|PUT|DELETE
	 * @param url -
	 *            API end point
	 * @param callback -
	 *            This the successful callback
	 * @param errorHandler -
	 *            This is the failed callback
	 */
	function ajax(method, url, data, callback, errorHandler) {
		var xhr = new XMLHttpRequest();

		xhr.open(method, url, true);

		xhr.onload = function() {
			switch (xhr.status) {
			case 200:
				callback(xhr.responseText);
				break;
			case 403:
				alert('403 Forbidden error message');
				break;
			case 401:
				errorHandler();
				break;
			}
		};

		xhr.onerror = function() {
			console.error("The request couldn't be completed.");
			errorHandler();
		};

		if (data === null) {
			xhr.send();
		} else {
			xhr.setRequestHeader("Content-Type",
					"application/json;charset=utf-8");
			xhr.send(data);
		}
	}

	// -------------------------------------
	// AJAX call server-side APIs
	// -------------------------------------

	/**
	 * API retrieve musical score API end point: [GET]
	 * /MusicalGeneRank/score?user_id=demo_profile_id
	 */
	function retrieve() {
		console.log('retrieveScore');

		// The request parameters
		var url = './score';
		var params = 'user_id=' + user_id;
		var req = JSON.stringify({});

		// make AJAX call
		ajax('GET', url + '?' + params, req,
		// successful callback
		function(res) {
			var score = JSON.parse(res);
			if (!score || score.length === 0) {
				showWarningMessage('No score available.');
			} else {
				showScore(score);
			}
		},
		// failed callback
		function() {
			showErrorMessage('Cannot get your score');
		});
	}

	// -------------------------------------
	// Create display for the score
	// -------------------------------------

	/**
	 * Show score
	 * 
	 * @param score - An JSON objects
	 */
	function showScore(score) {
		// Clear the current results
		var itemList = $('message');
		itemList.innerHTML = 'Your score is: ' + score.demo_profile_id + ' / 5.';
	}

	init();

})();

