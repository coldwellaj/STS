/*
	Dimension by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
*/

/****************************************************
When called will reset the form that is passed by ID
******************************************************/
Element.prototype.remove = function() {
	this.parentElement.removeChild(this);
}
NodeList.prototype.remove = HTMLCollection.prototype.remove = function() {
	for(var i = this.length - 1; i >= 0; i--) {
		if(this[i] && this[i].parentElement) {
			this[i].parentElement.removeChild(this[i]);
		}
	}
}

var clients = [];


function resetForm($form) {
    $form.find('input:text, input:password, input:file, textarea').val('');
    $form.find('input:radio, input:checkbox')
         .removeAttr('checked').removeAttr('selected');
}
$('#SageTickets').on('click',function(){
	getSageTickets();
});
$('#userTickets').on('click',function(){
	var lastCheck = sessionStorage.getItem('CheckTickets');
	var time = new Date();
	if(lastCheck == null){
		sessionStorage.setItem('CheckTickets', time.getTime());
	}else if(lastCheck + 180000 > time.getTime()){
		window.location.href = "#myTickets";
	}

	getTickets();
});
(function($) {
	skel.breakpoints({
		xlarge:		'(max-width: 1680px)',
		large:		'(max-width: 1280px)',
		medium:		'(max-width: 980px)',
		small:		'(max-width: 736px)',
		xsmall:		'(max-width: 480px)',
		xxsmall:	'(max-width: 360px)'
	});
	$(window).on("beforeunload", function() { 
    	 sessionStorage.clear();
	});
	$(function() {
		var	$window = $(window),
			$body = $('body'),
			$wrapper = $('#wrapper'),
			$header = $('#header'),
			$footer = $('#footer'),
			$main = $('#main'),
			$main_articles = $main.children('article');

		// Disable animations/transitions until the page has loaded.
			$body.addClass('is-loading');

			$window.on('load', function() {
				window.setTimeout(function() {
					$body.removeClass('is-loading');
				}, 100);
			});

		// Fix: Placeholder polyfill.
			$('form').placeholder();
			$('#login').on('submit', function (e){
				e.preventDefault();
				$("body").addClass("loading");
				var f = $('form[name=login]').serialize();
				var url = "http://sagests.us.to:8080/STS/LoginForm?" + f + "&format=json&callback=?";
				$.getJSON(url, function(data){
					$("body").addClass("loaded");
						$("body").removeClass("loading");
						$("body").removeClass("loaded");
					if(data[0].Login==true){
						window.location="#";
						sessionStorage.setItem('FirstName', '');
						sessionStorage.setItem('LastName',data[0].LastName);
						sessionStorage.setItem('UserID', data[0].UserId);
						sessionStorage.setItem('status','loggedIn');
					
							$('#refresh').on('click',function(){
								var first = sessionStorage.getItem("FirstName");
								var last = sessionStorage.getItem("LastName");
								
								$('body').toggleClass('loading');
								if(sessionStorage.getItem('Sage') == 'true'){
									getSageTickets();
								}else{
									getTickets();
								}
							});
						$('#footnote').css('display', 'none');	
						$('#StandardMenu').css('display','none');
						
						if(data[1]!=undefined && data[1].Sage == true){
							sessionStorage.setItem('Sage', "true");
							$('#SageMenu').css('display','');
							$('#wrapper').css('display','none');
							$('#wrapper').css('display','');
							
							siteConfigs();
							
						}else{
							$('#UserMenu').css('display','');
						}

					}else if(data[0].locked==true){
						alert("To Many Attempts account has been locked for 10 minutes.");
						
					}else if(data[0].ipLocked==true){
						alert("To Many Attempts your IP address has been blocked for 10 minutes");
					}
						else{
						alert("UserName or Password incorrect");	
					}
				})
				$('#refresh').on('click',function(){
								var first = sessionStorage.getItem("FirstName");
								var last = sessionStorage.getItem("LastName");
								
								$('body').toggleClass('loading');
								if(sessionStorage.getItem('Sage') == 'true'){
									getSageTickets();
								}else{
									getTickets();
								}
							});
			});
			
		/*******************************
		* catch new ticket submissions *
		********************************/
			$('#New_Ticket').on('submit', function (e) {
         		e.preventDefault();
				  var f = $('form[name=New_Ticket]').serialize();
				  var today = new Date();
				  var dd = today.getDate();
				  var mm = today.getMonth()+1; //January is 0!
				  
				  var yyyy = today.getFullYear();
				  if(dd<10){
					  dd='0'+dd;
				  } 
				  if(mm<10){
					  mm='0'+mm;
				  } 
				  var now = dd+'/'+mm+'/'+yyyy;
				  var dd = today.getDay() + 2
				  var twoday = (dd)+'/'+mm+'/'+yyyy;
				  /* priority=&start_date=&end_date=&current_user=&department=&details=&client_f_name=&client_l_name=&client_email=&client_phone= */
				var url = "http://sagests.us.to:8080/STS/TicketForm?" + f + "&current_user=coldwellaj@gmail.com&start_date="+now+"&end_date="+twoday+"&format=json&callback=?";
				$('body').addClass("loading");
				$.getJSON(url, function(data){
					if(data[0].Submission == true){
						window.location = "#ticketCreated";	
						resetForm($('#New_Ticket'));
						if(sessionStorage.getItem('status') == "loggedIn"){
							getTickets();
						}else{
							$('body').addClass("loaded");
							$('body').removeClass('loading')
							$('body').removeClass('loaded');
						}					
					}else{
						$('body').addClass("loaded");
						$('body').removeClass('loading')
						$('body').removeClass('loaded');
						resetForm($('#New_Ticket'));
						alert("Ticket not submitted correctly. If you continue to have this issue, please call the support desk");
					}
				})
				
        	});
			$('#New_Time_Entry').on('submit', function(e){
				e.preventDefault();
					if(sessionStorage.getItem('status') != "loggedIn"){
						window.location.href="#";
					}else{
					$("body").addClass("loading");
					var f = $('form[name=New_Time_Entry]').serialize();
					var num = $('#timeEntryTitle').html();
					num = num.substr(17,31);
					var url = "http://10.200.200.187/sage_app/addTime.php?"+f+"&First="+sessionStorage.getItem('FirstName')+"&Last="+sessionStorage.getItem('LastName')+"&Number="+num+"&format=json&callback=?";
					$.getJSON(url, function(data){
						if(data.Submission){
							alert ('Time Entry Added');
							var first = sessionStorage.getItem('FirstName');
							var last = sessionStorage.getItem('LastName');
							getSageTickets();
							siteConfigs();
							window.location.href="#myTickets";
							resetForm($('#New_Time_Entry'));
						}else{
							alert('Time entry submission error');
							$('body').addClass("loaded");
							$('body').removeClass('loading')
							$('body').removeClass('loaded');
						}

						
					})
				}
			});
			$('#New_User').on('submit',function(e){
				e.preventDefault();
				if(sessionStorage.getItem('status') != "loggedIn"){
					window.location.href="#";
				}else{
					var f = $('form[name=New_User]').serialize();
					
					var url = "http://10.200.200.187/sage_app/New_User.php?" + f + "&format=json&callback=?";
					$('body').addClass('loading');
					$.getJSON(url, function(data){
						$('body').removeClass('loading');
						$('body').addClass('loaded');
						if(data[0].ATCreated == true){
							var created = document.createElement('h2');
							created.innerHTML=data[0].SQLCreated;
							$('#User_Created').append(created);
							window.location.href = "#User_Created";

						}
					})
				}
			});
		// Fix: Flexbox min-height bug on IE.
			if (skel.vars.IEVersion < 12) {

				var flexboxFixTimeoutId;

				$window.on('resize.flexbox-fix', function() {

					clearTimeout(flexboxFixTimeoutId);

					flexboxFixTimeoutId = setTimeout(function() {

						if ($wrapper.prop('scrollHeight') > $window.height())
							$wrapper.css('height', 'auto');
						else
							$wrapper.css('height', '100vh');

					}, 250);

				}).triggerHandler('resize.flexbox-fix');

			}

		// Nav.
			var $nav = $header.children('nav'),
				$nav_li = $nav.find('li');

			// Add "middle" alignment classes if we're dealing with an even number of items.
				if ($nav_li.length % 2 == 0) {

					$nav.addClass('use-middle');
					$nav_li.eq( ($nav_li.length / 2) ).addClass('is-middle');

				}

		// Main.
			var	delay = 325,
				locked = false;

			// Methods.
				$main._show = function(id, initial) {

					var $article = $main_articles.filter('#' + id);

					// No such article? Bail.
						if ($article.length == 0)
							return;

					// Handle lock.

						// Already locked? Speed through "show" steps w/o delays.
							if (locked || (typeof initial != 'undefined' && initial === true)) {

								// Mark as switching.
									$body.addClass('is-switching');

								// Mark as visible.
									$body.addClass('is-article-visible');

								// Deactivate all articles (just in case one's already active).
									$main_articles.removeClass('active');

								// Hide header, footer.
									$header.hide();
									$footer.hide();

								// Show main, article.
									$main.show();
									$article.show();

								// Activate article.
									$article.addClass('active');

								// Unlock.
									locked = false;

								// Unmark as switching.
									setTimeout(function() {
										$body.removeClass('is-switching');
									}, (initial ? 1000 : 0));

								return;

							}

						// Lock.
							locked = true;

					// Article already visible? Just swap articles.
						if ($body.hasClass('is-article-visible')) {

							// Deactivate current article.
								var $currentArticle = $main_articles.filter('.active');

								$currentArticle.removeClass('active');

							// Show article.
								setTimeout(function() {

									// Hide current article.
										$currentArticle.hide();

									// Show article.
										$article.show();

									// Activate article.
										setTimeout(function() {

											$article.addClass('active');

											// Window stuff.
												$window
													.scrollTop(0)
													.triggerHandler('resize.flexbox-fix');

											// Unlock.
												setTimeout(function() {
													locked = false;
												}, delay);

										}, 25);

								}, delay);

						}

					// Otherwise, handle as normal.
						else {

							// Mark as visible.
								$body
									.addClass('is-article-visible');

							// Show article.
								setTimeout(function() {

									// Hide header, footer.
										$header.hide();
										$footer.hide();

									// Show main, article.
										$main.show();
										$article.show();

									// Activate article.
										setTimeout(function() {

											$article.addClass('active');

											// Window stuff.
												$window
													.scrollTop(0)
													.triggerHandler('resize.flexbox-fix');

											// Unlock.
												setTimeout(function() {
													locked = false;
												}, delay);

										}, 25);

								}, delay);

						}

				};

				$main._hide = function(addState) {

					var $article = $main_articles.filter('.active');

					// Article not visible? Bail.
						if (!$body.hasClass('is-article-visible'))
							return;

					// Add state?
						if (typeof addState != 'undefined'
						&&	addState === true)
							history.pushState(null, null, '#');

					// Handle lock.

						// Already locked? Speed through "hide" steps w/o delays.
							if (locked) {

								// Mark as switching.
									$body.addClass('is-switching');

								// Deactivate article.
									$article.removeClass('active');

								// Hide article, main.
									$article.hide();
									$main.hide();

								// Show footer, header.
									$footer.show();
									$header.show();

								// Unmark as visible.
									$body.removeClass('is-article-visible');

								// Unlock.
									locked = false;

								// Unmark as switching.
									$body.removeClass('is-switching');

								// Window stuff.
									$window
										.scrollTop(0)
										.triggerHandler('resize.flexbox-fix');

								return;

							}

						// Lock.
							locked = true;

					// Deactivate article.
						$article.removeClass('active');

					// Hide article.
						setTimeout(function() {

							// Hide article, main.
								$article.hide();
								$main.hide();

							// Show footer, header.
								$footer.show();
								$header.show();

							// Unmark as visible.
								setTimeout(function() {

									$body.removeClass('is-article-visible');

									// Window stuff.
										$window
											.scrollTop(0)
											.triggerHandler('resize.flexbox-fix');

									// Unlock.
										setTimeout(function() {
											locked = false;
										}, delay);

								}, 25);

						}, delay);


				};

			// Articles.
				$main_articles.each(function() {

					var $this = $(this);

					// Close.
						$('<div class="close">Close</div>')
							.appendTo($this)
							.on('click', function() {
								location.hash = '';
							});

					// Prevent clicks from inside article from bubbling.
						$this.on('click', function(event) {
							event.stopPropagation();
						});

				});

			// Events.
				$body.on('click', function(event) {

					// Article visible? Hide.
						if ($body.hasClass('is-article-visible'))
							$main._hide(true);

				});

				$window.on('keyup', function(event) {

					switch (event.keyCode) {

						case 27:

							// Article visible? Hide.
								if ($body.hasClass('is-article-visible'))
									$main._hide(true);

							break;

						default:
							break;

					}

				});

				$window.on('hashchange', function(event) {

					// Empty hash?
						if (location.hash == ''
						||	location.hash == '#') {

							// Prevent default.
								event.preventDefault();
								event.stopPropagation();

							// Hide.
								$main._hide();

						}

					// Otherwise, check for a matching article.
						else if ($main_articles.filter(location.hash).length > 0) {

							// Prevent default.
								event.preventDefault();
								event.stopPropagation();

							// Show article.
								$main._show(location.hash.substr(1));

						}

				});

			// Scroll restoration.
			// This prevents the page from scrolling back to the top on a hashchange.
				if ('scrollRestoration' in history)
					history.scrollRestoration = 'manual';
				else {

					var	oldScrollPos = 0,
						scrollPos = 0,
						$htmlbody = $('html,body');

					$window
						.on('scroll', function() {

							oldScrollPos = scrollPos;
							scrollPos = $htmlbody.scrollTop();

						})
						.on('hashchange', function() {
							$window.scrollTop(oldScrollPos);
						});

				}

			// Initialize.

				// Hide main, articles.
					$main.hide();
					$main_articles.hide();

				// Initial article.
					if (location.hash != ''
					&&	location.hash != '#')
						$window.on('load', function() {
							$main._show(location.hash.substr(1), true);
						});

	});

})(jQuery);
/*This function will retrieve the tickets from the database. These tickets are for end users*/
function getTickets(){ 
	$('body').addClass('loading');
//variable for the data from the database       
var data;
//These are the arrays that will be converted into json for storage as session variables. 
//they will contain the ticket data
var times = new Array();
var numbers = new Array();
var dateWorked = new Array();
var userID = sessionStorage.getItem('UserID');
console.log(userID);
var z = 0;
var url = "http://sagests.us.to:8080/STS/TicketList?user="+userID+"&format=json&callback=?";
sessionStorage.setItem('TicketNumber', 'null');//create empty session variable.
$('#MyTickets').remove();//clear the article to make way for the new information
var wrapper = document.createElement('div');
wrapper.setAttribute('class','table-wrapper');
wrapper.setAttribute('id','MyTickets');
$('#myTickets').append(wrapper);
	$.getJSON(url, function(data){
		//Create the table and the table headers
		$('body').addClass('loaded');
		$('body').removeClass('loading');
		$('body').removeClass('loaded');			
		var table = document.createElement("table");
		var thead = document.createElement("thead");
		var row = document.createElement("tr");
		var col = document.createElement("td");
		var col2 = document.createElement("td");
		var col3 = document.createElement("td");
		var noTickets = false; 
		/* if(data[0].TicketNumber.toUpperCase() === 'NO OPEN TICKETS'){
			col.innerHTML="No Open Tickets";
			noTickets=true;
		}else */{
			col.innerHTML="Ticket Number";
			col2.innerHTML="Due Date";
			col3.innerHTML="Description";
		}
		row.appendChild(col);
		row.appendChild(col2);
		row.appendChild(col3);
		thead.appendChild(row);
		table.appendChild(thead);
		//loop throught the tickets and add them to the table
		if(!noTickets){
			for(var i=0;i<data.length;i++){
				var row = document.createElement("tr");
				var col = document.createElement("td");
				var col2 = document.createElement("td");
				var col3 = document.createElement("td");			
				//only enter this part of the tickets if it is a new ticket
				//to add the correct time entries to the correct tickets
				if(data[i].TicketNumber !== sessionStorage.getItem('TicketNumber')){
					sessionStorage.setItem('TicketNumber', data[i].TicketNumber);		
					numbers[i]=data[i].TicketNumber;
					col.innerHTML = data[i].TicketID; 
					col2.innerHTML = data[i].StartDate; 
					col3.innerHTML = data[i].Detail;
					//listen for the click of the row to see the time entries
					 row.addEventListener('click',function(){
						var arg = this.firstChild.innerHTML;
						console.log(arg);
						editTicket(arg);
					});
					row.appendChild(col);
					row.appendChild(col2);
					row.appendChild(col3);
					table.appendChild(row);
					times[i]=data[i].TimeEntry;
					numbers[i]=data[i].TicketNumber;
					dateWorked[i]=data[i].DateWorked;
					z++;
					//if the ticket is not unique then just add the ticket data
				}else{
					numbers[i]=data[i].TicketNumber
					times[i]=data[i].TimeEntry
					dateWorked[i]=data[i].DateWorked;
				}
			}
		//time entries object to be stored as session variable
		/* var timeEntries = {TicketNumber:numbers, time:times, date:dateWorked};
		sessionStorage.setItem('TimeEntries',JSON.stringify(timeEntries)); */
	}
		$('#MyTickets').append(table);
		$('body').css({'counter-reset':'ticket-count '+ z});
		return;
	})
}
function editTicket(id){
var url = "http://sagests.us.to:8080/STS/TicketView?ticketId=" + id + "&format=json&callback=?";
$.getJSON(url, function(data){
	console.log(data);
	$('#EditTicketDescription').val(data[0].Detail);
	$('#edit_start_date').val(data[0].StartDate);
	$('#edit_end_date').val(data[0].EndDate);
	$('#EditPriority').val(data[0].Priority);
	$('#EditTicketTitle').val(data[0].Department);
	$('#edit_resource').val(data[0].Resource);
	sessionStorage.setItem("ClientID", data[0].ClientID);
	sessionStorage.setItem("TicketID", data[0].TicketID);
	window.location.href="#editTicket";
});
}
$('#close_ticket').click(function(){
	var ticketID = sessionStorage.getItem("TicketID");
	var url = "http://sagests.us.to:8080/STS/TicketStatus?ticketid=" + ticketID + "&ticketstatus=4&callback=?";
	$.getJSON(url, function(data){
		alert("Ticket Closed");
	});
});
$('#Assign_Tech').submit(function(e){
	$("body").addClass('loading');
	e.preventDefault()
	var assign = $(this).serialize();
	var url = "http://sagests.us.to:8080/STS/AssignTicket?" + assign + "&format=json&callback=?";
	$.getJSON(url, function(data){
		$("body").addClass("loaded").removeClass("loading");
        if(data[0].Submission){
            alert("Tech Assigned");
            window.location.href="#";
        }else{
            alert("Ticket Update Failed " + data[0].Submission);
        }
	});
});
$('#Edit_Ticket').submit(function(e){
	e.preventDefault();
	var update = $(this).serialize();
	var clientID = sessionStorage.getItem("ClientID");
	var ticketID = sessionStorage.getItem("TicketID");
	var url = "http://sagests.us.to:8080/STS/TicketUpdate?"+update+"&client_id="+clientID+"&id="+ticketID+"&callback=?";
	$.getJSON(url, function(data){
		if(data[0].Submission){
			alert("Ticket Updated");
			window.location.href="#";
		}else{
			alert("Ticket Update Failed " + data[0].Submission);
		}
	});
});
function getNewTickets(){
	$("body").addClass("loading");
	var url = "http://sagests.us.to:8080/STS/NewTicketList?callback=?"
	$.getJSON(url, function(data){
		for(var i=0; i < data.length; i++){
			var ticket = document.createElement('option');
			ticket.innerHTML=data[i].TicketID;
			ticket.value=data[i].TicketID;
			$('#ticketId').append(ticket);
		}
	});
	window.location="#techAssign"
	$("body").addClass("loaded");
	$("body").removeClass("loading");

}
//retrieve tickets for a sage user
function getSageTickets(){
	$("body").addClass("loading");

	var userID = sessionStorage.getItem('UserID');
		for(var i=0; i <= clients.length;i++){
			var client = document.createElement('option');
			client.innerHTML=clients[i];
			client.value=clients[i];
			$('#Account').append(client);
		}
	var data;
	var times = new Array();
	var numbers = new Array();
	var dateWorked = new Array();
	var z = 0;
	sessionStorage.setItem('TicketNumber', 'null');
	$('#MyTickets').remove();
	var wrapper = document.createElement('div');
	wrapper.setAttribute('class','table-wrapper');
	wrapper.setAttribute('id','MyTickets');
	$('#myTickets').append(wrapper);
	
	var url = "http://sagests.us.to:8080/STS/TicketList?user="+userID+"&format=json&callback=?";
		$.getJSON(url, function(data){
			$('body').addClass('loaded');
			$('body').removeClass('loading');
			$('body').removeClass('loaded');			
			var table = document.createElement("table");
			var thead = document.createElement("thead");
			var tbody= document.createElement('tbody');
			var row = document.createElement("tr");
			var col = document.createElement("td");
			var col2 = document.createElement("td");
			var col3 = document.createElement("td");
			col.innerHTML="Ticket Number";
			col2.innerHTML="Ticket Title";
			col3.innerHTML="Description";
			row.appendChild(col);
			row.appendChild(col2);
			row.appendChild(col3);
			thead.appendChild(row);
			table.appendChild(thead);
			
			for(var i=0;i<data.length;i++){
				var row = document.createElement("tr");
				var col = document.createElement("td");
				var col2 = document.createElement("td");
				var col3 = document.createElement("td");			
				if(data[i].TicketNumber !== sessionStorage.getItem('TicketNumber')){
					sessionStorage.setItem('TicketNumber', data[i].TicketNumber);		
					numbers[i]=data[i].TicketNumber;
					col.innerHTML = data[i].TicketNumber;
					col2.innerHTML = data[i].TicketTitle;
					col3.innerHTML = data[i].Description;
					/* row.addEventListener('click',function(){
						var arg = this.innerHTML;
						arg = arg.substr(4,14);
						addTime(arg);
					}); */
					row.appendChild(col);
					row.appendChild(col2);
					row.appendChild(col3);
					tbody.appendChild(row);
					
					times[i]=data[i].TimeEntry;
					numbers[i]=data[i].TicketNumber;
					dateWorked[i]=data[i].DateWorked;

					z++;
				}else{
					numbers[i]=data[i].TicketNumber
					times[i]=data[i].TimeEntry
					dateWorked[i]=data[i].DateWorked;
				}
			}
			table.appendChild(tbody);
			var timeEntries = {TicketNumber:numbers, time:times, date:dateWorked};
			sessionStorage.setItem('TimeEntries',JSON.stringify(timeEntries));
			$('#MyTickets').append(table);
			$('body').css({'counter-reset':'ticket-count '+ z});
		})
		
		return;
}
//controls adding a time entry to a ticket. As well as the time already worked
function addTime(number){
	$('#work').remove();
	var work = document.createElement('div');
	work.setAttribute('class','table-wrapper');
	work.setAttribute('id', 'work');
	work.style.marginTop= '0%';
	var title = document.createElement('h2');
	title.innerHTML=number;
	work.appendChild(title);
	$('#timeEntries').append(work);
	var timeEntries = JSON.parse(sessionStorage.getItem('TimeEntries'));
	var table = document.createElement("table");
	var thead = document.createElement("thead");
	var row = document.createElement("tr");
	var col = document.createElement("td");
	var col2 = document.createElement('td');
	var flag = false;
	col.innerHTML="Date Performed (Y-M-D)";
	col2.innerHTML="<br><br>Work done";
	row.appendChild(col);
	row.appendChild(col2);
	thead.appendChild(row);
	table.appendChild(thead);
	for(var i=0; i<timeEntries.time.length;i++){
		if(timeEntries.TicketNumber[i] == number && timeEntries.time[i]!= null){
			var row = document.createElement("tr");
			var col = document.createElement("td");
			var col2 = document.createElement('td');
			col.innerHTML=timeEntries.date[i];
			col2.innerHTML=timeEntries.time[i];
			row.appendChild(col);
			row.appendChild(col2);
			table.appendChild(row);
			flag = true;
		}
	}
	if(flag){
		$('#work').append(table);
	}else{
		var workDiv = document.createElement('div');
		var workMessage = document.createElement('h2');
		workMessage.innerHTML= "No work logged for this ticket yet";
		workDiv.appendChild(workMessage);
		$('#work').append(workDiv);

	}
	if(sessionStorage.getItem('Sage')=='true'){
		var button = document.createElement("button");
		button.setAttribute('value','Add Time');
		button.innerHTML="New Time Entry"
		button.addEventListener('click', function(){
			addTimeEntry(number);
		})
		$('#work').append(button);
	}
	window.location.href="#timeEntries";
	return;
}
function addTimeEntry(number){
	window.location.href="#TimeEntry";
	$('#timeEntryTitle').html("Add Time for:<br>" + number);
	return;
}
function siteConfigs(){
	for(var i=0; i <= clients.length;i++){
			var client = document.createElement('option');
			client.innerHTML=clients[i];
			client.value=clients[i];
			$('#clientList').append(client);
		}
		var wrapper = document.createElement('div');
		wrapper.setAttribute('class','table-wrapper');
		wrapper.setAttribute('id', 'scTable');
		$('#siteConfigs').append(wrapper);
		$('#clientList').change(function(){
			$('body').addClass('loading');
			$('#scTable').empty();
			var client = '';
			var url = 'http://10.200.200.187/sage_app/account_site_configs.php?account=' + this.value + "&format=json&callback=?";
			$.getJSON(url, function(data){
				$("body").addClass("loaded");
				$("body").removeClass("loading");
				$("body").removeClass("loaded");
				for(var i=0; i < data.length; i++){
					if(data[i].indexOf('AccountName') >= 0){
						var table = document.createElement("table");						
						var thead = document.createElement("thead");
						var row = document.createElement("tr");
						var col = document.createElement("td");
						var col2 = document.createElement('td');
						var head1 = document.createElement('h1');
						client = data[i].substr(data[i].indexOf('=')+1, data[i].length);					
						head1.innerHTML = client;
						col.innerHTML = "Section";
						col2.innerHTML = "Info";
						row.appendChild(col);
						row.appendChild(col2);
						thead.appendChild(row);
						table.appendChild(thead);
						$('#scTable').append(head1);
					}else{
						var row = document.createElement("tr");
						var col = document.createElement("td");
						var col2 = document.createElement('td');
						col.innerHTML = data[i];
						i++;
						col2.innerHTML = data[i];
						row.appendChild(col);
						row.appendChild(col2);
						table.appendChild(row);
					}
				}
				$('#scTable').append(table);
			});
		return;
		});
	}
	$('#reset_password').submit(function(e){
		e.preventDefault();
		if(sessionStorage.getItem('status')==null){
			window.location.href="#";
			alert('Must login to reset password');
			$('body').removeClass('loading');
			return;
		}
		$('body').addClass('loading');
		
		var info = $(this).serialize();
		var url = 'http://10.200.200.187/sage_app/password_reset.php?'+info+'&Unlock=true&format=json&callback=?';
		$.getJSON(url, function(data){
			$('body').removeClass('loading');
			if(data['reset']==true){
				alert("Password Reset");
				window.location.href="#";
			}else if(data['reason'] == 'Bad Password'){
				alert('The Password did not meet the complexity or history requirements');
			}else{
				alert('That user account does not exist');
			}
		});

	});