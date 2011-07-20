<!DOCTYPE html>
<html>
	<head>
		<title><g:layoutTitle default="Home" /></title>
		<link rel="stylesheet" href="${resource(dir:'css',file:'home.css')}" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js?ver=1.4.2"></script>
		<g:javascript src="ClientCore.js" />
		<g:layoutHead />
		</head>
	<body>
		<div class="header">
			<div class="container">
				<div id="logo"><g:link url="/Library">Library</g:link></div>
				<div id="loginContainer">
					<g:if test="${session.user}">
						<span id="logoutButton"><g:link controller="user"  action="logout">Logout</g:link></span>
						<span id="userWelcome">Welcome <g:link controller="user" action="viewProfile" params="[userId:session.user.id]">${session.user.name}</g:link></span>
					</g:if>
					<g:else>
						<a href="#" id="loginButton"><span>Login</span><em></em></a>
						<div style="clear:both"></div>
						<div id="loginBox">                
							<g:form class="loginForm" controller="user" action="login">
								<fieldset id="body">
									<fieldset>
										<label for="email">User</label>
										<g:textField name="userId"></g:textField>
									</fieldset>
									<fieldset>
										<label for="password">Password</label>
										<g:passwordField name="password"></g:passwordField>
									</fieldset>
									<g:submitButton id="login" name="Sing in" />
									<label for="checkbox"><input type="checkbox" id="checkbox" />Remember me</label>
									
								</fieldset>
								
								<span><a href="#">Forgot your password?</a></span>
								<g:link controller="user" action="register">Sign up now!</g:link>
							</g:form>
						</div>
					</g:else>
				</div>
			</div>
		</div>
		<div class="menu-bar">
			<div class="container">
				<ul>
					<li><g:link controller="home"  action="index">Home</g:link></li>
					<li><a href="#">Books</a></li>
					<li><g:link controller="library"  action="gmaps">Librarys</g:link></li>
					<li><a href="#">Console</a></li>
				</ul>
				<div id="searchForm">
		  			<g:form url='[controller: "book", action: "search"]'
		          		id="searchableForm"
		          		name="searchableForm"
		          		method="get">
		    				<richui:autoComplete name="q" value="${params.q}" action="${createLinkTo('dir': 'book/searchAJAX')}" />
		    				<input id="searchButton" type="submit" value="Search" />	
		  			</g:form>
				</div>
			</div>
		</div>
		<div id="content">
			<div class="container">
				<div class="col-s-content">
					<g:layoutBody />
				</div>
				<div class="large-sidebar">
					<g:if test="${session.user}">
							<div class="inner-boundary last-item">
								<div class="inner-border author-item">
									
										<div>
										<span class="text-form">${session.user.name}</span> <br/>
										<span class="text-form">points: 3 Stars</span> <br/>
										<resource:rating />
										<div id="user${session.user.id}">
		    								<% def dynamic = (!session.voted[session.user.name])%>
		    								<richui:rating dynamic="${dynamic.toString()}" id="${session.user.id}" units="5"
		        								rating="${session.user.rating}" updateId="user${session.user.id}" controller="user" action="rate"  />
		    								<p class="static">"Rating ${java.text.NumberFormat.instance.format(session.user.rating)}
		        								based on ${session.user.totalVotes} vote<g:if test="${session.user.totalVotes != 1}">s</g:if>
		    								</p>
		    								<g:if test="${!dynamic}">
		        								<div style="color: green;" id="vote${session.user.id}">Thanks for voting!</div>
		    								</g:if>
										</div>
										<br/>	
										</div>
										<ul class="menu-vert">
											<li><g:link controller="reservation" action="viewMyReservation">My reservation</g:link></li>
											<li><g:link controller="comment" action="viewMyComments">My Comments</g:link></li>
											<li><a href="#">Recommendation for me</a></li>
										</ul>
										
										
										<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
								        <script type="text/javascript">
								
								        var map;
								        var geocoder = new google.maps.Geocoder();
								
								        geocoder.geocode( { address: "${session.user.seeAddress()}" }, function(results, status) {
								            if (status == google.maps.GeocoderStatus.OK && results.length) {
											
								              if (status != google.maps.GeocoderStatus.ZERO_RESULTS) {
								                var latlng = results[0].geometry.location;
												
								                var myOptions = {
								                  zoom: 12,
								                  center: latlng,
								                  mapTypeId: google.maps.MapTypeId.ROADMAP
								                }
												
								                map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
								                
								                var marker = new google.maps.Marker({
								                    position: latlng,
								                    map: map
								                });

<%--								                <g:each in="${libraryList}">--%>
<%--								                --%>
<%--								                var latlng2 = new google.maps.LatLng(60, 105);								                --%>
<%--								                new google.maps.Marker({--%>
<%--								                    position: latlng2,--%>
<%--								                    map: map--%>
<%--								                });--%>
<%--								                </g:each>--%>
							                
								              }
								            } else {
								              alert("Geocode was unsuccessful due to: " + status);
								            }
								          });
							       
								      </script>
		        					<div id="map_canvas" style="width:276px; height:240px"></div>
									
								</div>
							</div>
					</g:if>
				</div>	
			</div>
		</div>
		<div class="footer">
			<div class="container">
				<p>Following</p>
			</div>
		</div>
		
	</body>
</html>