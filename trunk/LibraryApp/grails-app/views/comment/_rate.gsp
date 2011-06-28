<div id="comment${comment.id}">
    <% def dynamic = !session.voted[comment.description] %>
    <richui:rating dynamic="${dynamic.toString()}" id="${comment.id}" units="5"
        rating="${rating}" updateId="comment${comment.id}" controller="comment" action="rate"  />
<%--    <p class="static">--%>
<%--        Rating ${java.text.NumberFormat.instance.format(commet.score)}--%>
<%--        based on ${user.totalVotes} vote<g:if test="${user.totalVotes != 1}">s</g:if>--%>
<%--    </p>--%>
    <g:if test="${!dynamic}">
        <div style="color: green;" id="vote${comment.id}">Thanks for voting!</div>
    </g:if>
</div>