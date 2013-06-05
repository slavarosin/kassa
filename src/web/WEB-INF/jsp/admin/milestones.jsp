<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table width="50%" border="1">
	<tr>
		<th width="30%"><fmt:message key="header.milestone"/></th>
		<th width="40%"><fmt:message key="header.date"/></th>
	</tr>
	<c:forEach items="${milestones}" var="milestone">
	    <c:if test="${empty milestone.date}">
	        <tr bgcolor="yellow">
		        <td>${milestone.id}</td>
		        <td>-</td>
		    </tr>
	    </c:if>
	    <c:if test="${not empty milestone.date}">
            <tr>
                <td>${milestone.id}</td>
                <td>${milestone.date}</td>
            </tr>
        </c:if>
	</c:forEach>
</table>
<br><br>
<a href="?addmilestone=true"><fmt:message key="button.addmilestone"/></a>