<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form method="post">
    <input name="text"/>
    <input type="submit" value="<fmt:message key="button.addcomment"/>">
</form>
<br><br>
<table width="50%" border="1">
	<tr>
		<th width="40%"><fmt:message key="header.text"/></th>
		<th><fmt:message key="header.action"/></th>
	</tr>
	<c:forEach items="${comments}" var="comment">
        <tr>
	        <td>${comment.text}</td>
	        <td>&nbsp;<a href="?remove=${comment.id}"><fmt:message key="button.remove"/></a></td>
	    </tr>
	</c:forEach>
</table>

