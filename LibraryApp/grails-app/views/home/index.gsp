<html>
	<head>
		<meta name="layout" content="home" />
		<resource:autoComplete skin="default" />
		<title>Library Home</title>
	<style type="text/css">
		#introduction {
			width: 800px;
			margin: 0 auto;
		}
		#introduction p {
			padding-top: 20px;
		}
		#technology-logos {
			margin: 0 auto;
			width: 250px;
		}
		#features-list, #features-list li span {
			padding-left: 20px;
		}
		
		#features-list li{
			padding-top: 10px;
		}
	</style>
	</head>
	<body>
		
		<div id="introduction">
			<h3>Welcome to Map of Books</h3>
			<p>
			Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
			Nulla nec turpis non nisl sodales ultricies. 
			Nam bibendum dictum felis, vel mollis nulla tincidunt nec. 
			Nam libero neque, aliquet sed semper ornare, interdum vel velit. 
			 
			</p>
			
			<p>
			In in justo et erat facilisis sagittis non adipiscing tortor. 
			Phasellus tempor enim et dolor pulvinar tincidunt. 
			Nam semper imperdiet mauris. Aliquam sit amet leo commodo arcu 
			convallis vestibulum pellentesque sed eros. Donec eu dui tellus. 
			Praesent in odio quis elit cursus vestibulum et interdum neque. 
			Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
			Praesent eleifend vulputate lectus sed porta. 
			Quisque facilisis mollis bibendum. 
			Sed consectetur nisl ut tellus lacinia pretium. 
			</p>
			<p>Features</p>
			<ul id="features-list">
				<li><span class="icon-tilde">Find the books nearest</span></li>
				<li><span class="icon-tilde">Make reservation online</li></span>
				<li><span class="icon-tilde">Categorize Books</li></span>
				<li><span class="icon-tilde">Have a profile</li></span>
				<li><span class="icon-tilde">Comment and score differents books</li></span>
				<li><span class="icon-tilde">See comments by other users</li></span>
				<li><span class="icon-tilde">Win Awards</li></span>
				<li><span class="icon-tilde">Access by your device</li></span>
			</ul>
			<p>Technology</p>
			<div id="technology-logos">
				<img src="<g:createLinkTo dir="images" file="grails_cup.jpg"  />" alt="Grails" title="logo_grails" height="100"></img>
				<img src="<g:createLinkTo dir="images" file="android.jpg"  />" alt="Grails" title="logo_android" height="100"></img>
			</div>
		</div>
	</body>
</html>