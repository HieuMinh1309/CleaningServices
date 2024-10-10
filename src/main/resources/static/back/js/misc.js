(function($) {
	'use strict';
	document.addEventListener('DOMContentLoaded', function() {
		// Your existing code here
		var body = $('body');
		var contentWrapper = $('.content-wrapper');
		var scroller = $('.container-scroller');
		var footer = $('.footer');
		var sidebar = $('.sidebar');

		//Add active class to nav-link based on url dynamically
		//Active class can be hard coded directly in html file also as required
		var current = location.pathname.split("/").slice(-1)[0].replace(/^\/|\/$/g, '');
		function addActiveClass(element) {
			if (current === "") {
				//for root url
				if (element.attr('href').indexOf("index.html") !== -1) {
					element.parents('.nav-item').last().addClass('active');
					if (element.parents('.sub-menu').length) {
						element.closest('.collapse').addClass('show');
						element.addClass('active');
					}
				}
			} else {
				//for other url
				if (element.attr('href').indexOf(current) !== -1) {
					element.parents('.nav-item').last().addClass('active');
					if (element.parents('.sub-menu').length) {
						element.closest('.collapse').addClass('show');
						element.addClass('active');
					}
					if (element.parents('.submenu-item').length) {
						element.addClass('active');
					}
				}
			}
		}
	});

	// Toggle Sidebar items
	$('[data-toggle="expansionPanel"]').on('click', function() {
		// close other items
		$('.mdc-expansion-panel').not($('#' + $(this).attr("data-target"))).hide(300);
		$('.mdc-expansion-panel').not($('#' + $(this).attr("data-target"))).prev('[data-toggle="expansionPanel"]').removeClass("expanded");
		// Open toggle menu
		$('#' + $(this).attr("data-target")).slideToggle(300, function() {
			$('#' + $(this).attr("data-target")).toggleClass('expanded');
		});
	});


	// Add expanded class to mdc-drawer-link after expanded
	$('.mdc-drawer-item .mdc-expansion-panel').each(function() {
		$(this).prev('[data-toggle="expansionPanel"]').on('click', function() {
			$(this).toggleClass('expanded');
		})
	});
})(jQuery);