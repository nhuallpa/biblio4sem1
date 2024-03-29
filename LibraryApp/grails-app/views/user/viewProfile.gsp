<html>
<head>
	<meta name="layout" content="home" />
	<title>Profile: ${userProfile?.name}</title>
	<resource:tabView skin="custom" />
	<script type="text/javascript">
		$('#edit-tags-profile-button').live('click', function(){
			$('#fondo-popup').css('display', 'block');
			$('#edit-tags-profile').css('display','block');
			var tags = "";
			$('.tag-list li span').each(function(){
				if (tags == "") {
					tags = $(this).html();
				} else {
					tags = tags + ", " + $(this).html();	
				}
			});
			$('#user-tags-area').html(tags);
		});
	
	</script>
</head>
<body>
	<div id="profile-details">
		<img src="<g:createLinkTo dir="images/User/" file="photo.png" />"></img>
		<div class="resumen-derecho">
			<h3 id="name">${userProfile?.name}</h3>
			<div class="user-details">
				<span class="text">email: ${userProfile?.email }</span>
				|
				<span class="text">Phone: ${userProfile?.phone }</span>
				
				<br/><br/>	
				<span class="text">Your score: ${userProfile?.score }</span>
				<g:if test="${userProfile?.score >= 100}">
     				<g:link controller="award" action="awards" params="[type:'user']">Exchange!</g:link>
   				</g:if>
				
			</div>	
			<br/>
			
			<br/>		
			<br/>			
			<h4>Favourite Genres</h4><a id="edit-tags-profile-button" href="#">edit</a>
			<ul class="tag-list">
				<g:each var="tag" in="${userProfile?.tags}">
					<li><span>${tag}</span></li>
				</g:each>
			</ul>
			
		</div>
	</div>
	<g:if test="${flash.message}">
     		<div class="message">${flash.message}</div>
   	</g:if>
	<richui:tabView id="tabView"> 
		<richui:tabLabels> 
			<richui:tabLabel selected="true" title="Comments" /> 
			<richui:tabLabel title="Reservation" /> 
			<richui:tabLabel title="Delivered" />
			<richui:tabLabel title="Recommendation" /> 
			<richui:tabLabel title="My Awards" /> 
		</richui:tabLabels>
	
		<richui:tabContents> 
			<richui:tabContent> 
				<ul class="item-list">
					<g:each var="commentDone" in="${userProfile?.commentsDone}">
						<li class="book">
						  <g:link controller="book" action="viewDetails" params="[bookId:commentDone.book.id]">
						  		<img  src="<g:createLinkTo dir="images/Book/${commentDone.book.name}" file="cover.jpg" />"/>
						  </g:link>
						  <div class="resumen-derecho">
							  	<g:link controller="book" action="viewDetails" params="[bookId:commentDone.book.id]">${commentDone.book.name}</g:link>
								<h4>"${commentDone.description}"</h4>
								<p>Comment Score: ${commentDone.score}</p>
								 <span class="link-item"><g:link controller="comment" action="deleteComment" params="[commentId:commentDone.id,bookId:commentDone.book.id]">Delete</g:link></span>
						  </div>
					    </li>
					</g:each>
				</ul>
			</richui:tabContent>
			<richui:tabContent> 
				<ul class="item-list">
					<g:each var="reservation" in="${userProfile?.reservations}">
						<g:if test="${reservation.isReserved()}">
							<li class="book">
							  <g:link controller="book" action="viewDetails" params="[bookId:reservation.bookCopy.bookMaster.id]">
							  	<img  src="<g:createLinkTo dir="images/Book/${reservation.nameOfBook()}" file="cover.jpg" />"/>
							  </g:link>
								<span class="text"><g:link controller="book" action="viewDetails" params="[bookId:reservation.bookCopy.bookMaster.id]">${reservation.nameOfBook()}</g:link></span>
								<p><span class="text">Reservation Date : ${reservation.reservationDate.format('dd/MM/yyyy')}</span></p>
								<p><span class="text">Expiration Date : ${reservation.reservationExpirationDate.format('dd/MM/yyyy')}</span></p>
								<p><span class="text">State: ${reservation.stateOfBook()}</span></p>
								<span class="link-item"><g:link controller="reservation" action="cancelReserve" params="[bookId:reservation.bookCopy.bookMaster.id]">Cancel</g:link></span>
								<span class="link-item"><g:link controller="reservation" action="deliverBook" params="[reservationId:reservation.id]">Deliver</g:link></span>
						    </li>
						</g:if>
					</g:each>
				</ul>
			</richui:tabContent>
			<richui:tabContent> 
				<ul class="item-list">
					<g:each var="reservation" in="${userProfile?.reservations}">
						<g:if test="${reservation.isDelivered()}">
							<li class="book">
							  <g:link controller="book" action="viewDetails" params="[bookId:reservation.bookCopy.bookMaster.id]">
							  	<img  src="<g:createLinkTo dir="images/Book/${reservation.nameOfBook()}" file="cover.jpg" />"/>
							  </g:link>
								<span class="text"><g:link controller="book" action="viewDetails" params="[bookId:reservation.bookCopy.bookMaster.id]">${reservation.nameOfBook()}</g:link></span>
								<p><span class="text">Delivered Date : ${reservation.deliverDate.format('dd/MM/yyyy')}</span></p>
								<p><span class="text">Expiration Date : ${reservation.deliverExpirationDate.format('dd/MM/yyyy')}</span></p>
								<span class="text">State: ${reservation.stateOfBook()}</span><br/><br/>
								<span class="link-item"><g:link controller="reservation" action="returnBook" params="[bookCopyId:reservation.bookCopy.id]">Return</g:link></span>
						    </li>
						</g:if>
					</g:each>
				</ul>
			</richui:tabContent>
			<richui:tabContent> 
				<ul id="resultados-recommendation-list" class="columns">
			  		<g:each in="${booksRecommended}" status="i" var="book">
			  			<li class="libro">
						    <div class="imagen"><img  src="<g:createLinkTo dir="images/Book/${book.name}" file="cover.jpg"  />" alt="images/Book/${book.name}" title="images/Book/${book.name}" width="122px" height="180px"/></div>
						    <ul class="detalles">
						        <li class="titulo">Title ${book.name}</li>
						        <li class="autor">Author: ${book.author}</li>
						        <li class="votos"><span class="icon icon-votos"></span>${book.rating}</li>
						        <li class="comentarios"><span class="icon icon-comments"></span>${book.totalVotes}</li>
						        <li class="verDetalles"><a href="${createLink(controller:'book', action:'viewDetails', params:[bookId:book.id])}">see details</a></li>
						    </ul>                
						</li>
			  		</g:each>
				 </ul>
			</richui:tabContent> 
			<richui:tabContent> 
				<ul id="item-list">
			  		<g:each in="${userProfile?.myAwards}" status="i" var="award">
			  			<li class="libro">
						    <div class="imagen"><img  src="<g:createLinkTo dir="images/award/${award.category}" file="award.jpg"  />" title="Image" width="122px" height="180px"/></div>
						    <ul class="detalles">
						        <li class="titulo">${award.detail}</li>
								<li class="autor">Scores: ${award.score}</li>
								<li class="info"><a href="${createLink(controller:'award', action:'info', params:[userId:userProfile?.id, award_id: award.id])}">Info</a></li>
						    </ul>                
						</li>
			  		</g:each>
				 </ul>
			</richui:tabContent>			
		</richui:tabContents> 
	</richui:tabView>
	
</body>
</html>