<%--<div id="user${user.id}">
    <% def dynamic = ((!session.voted[user.name]) && !(session.user.name.equals(user.name)))%>
    <richui:rating dynamic="${dynamic.toString()}" id="${user.id}" units="5"
        rating="${rating}" updateId="user${user.id}" controller="user" action="rate"  />
    <p class="static">
        ${session.user.name} y ${user.name} so ${(session.user.name.equals(user.name)).toString()}
        final: ${((!session.voted[user.name]) && !(session.user.name.equals(user.name))).toString()}
        "${dynamic.toString()}"Rating ${java.text.NumberFormat.instance.format(user.rating)}
        based on ${user.totalVotes} vote<g:if test="${user.totalVotes != 1}">s</g:if>
    </p>
    <g:if test="${!dynamic}">
        <div style="color: green;" id="vote${user.id}">Thanks for voting!</div>
    </g:if>
</div>--%>
<richui:rating dynamic="true" units="5" rating="${rating}" noAjax="true" inputId=${params.rating} } />