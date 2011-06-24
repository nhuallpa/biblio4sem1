<div id="user${user.id}">
    <% def dynamic = !session.voted[user.name] %>
    <richui:rating dynamic="${dynamic.toString()}" id="${user.id}" units="5"
        rating="${rating}" updateId="user${user.id}" controller="user" action="rate"  />
    <p class="static">
        Rating ${java.text.NumberFormat.instance.format(user.rating)}
        based on ${user.totalVotes} vote<g:if test="${user.totalVotes != 1}">s</g:if>
    </p>
    <g:if test="${!dynamic}">
        <div style="color: green;" id="vote${user.id}">Thanks for voting!</div>
    </g:if>
</div>