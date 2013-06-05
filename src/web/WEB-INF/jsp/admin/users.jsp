<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form method="post">
    <input name="login"/>
    <input type="password"  name="passwd"/>
    <input type="submit" value="<fmt:message key="button.adduser"/>">
</form>
<br><br>
<table width="50%" border="1">
	<tr>
		<th width="40%"><fmt:message key="header.username"/></th>
		<th><fmt:message key="header.action"/></th>
	</tr>
	<c:forEach items="${users}" var="user">
	    <c:if test="${user.role == 0}">
	        <tr>
		        <td>${user.login}</td>
		        <td>&nbsp;<a href="?remove=${user.id}"><fmt:message key="button.remove"/></a></td>
		    </tr>
	    </c:if>
	</c:forEach>
</table>

