<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form method="post">
    <fmt:message key="header.currency"/>:<input name="name"/>
    <fmt:message key="header.sign"/>:<input name="sign"/>
    <fmt:message key="header.code"/>:<input name="code"/>
    <input type="submit" value="<fmt:message key="button.addcurrency"/>">
</form>
<br><br>
<table width="50%" border="1">
	<tr>
		<th width="40%"><fmt:message key="header.currency"/></th>
		<th width="20%"><fmt:message key="header.sign"/></th>
		<th width="20%"><fmt:message key="header.code"/></th>
		<th width="20%"><fmt:message key="header.action"/></th>
	</tr>
	<c:forEach items="${currencies}" var="currency">
        <tr>
	        <td>${currency.name}</td>
	        <td>${currency.sign}</td>
	        <td>${currency.code}</td>
	        <td>
	        	<a href="?remove=${currency.id}"><fmt:message key="button.remove"/></a>
	        	<c:if test="${not currency.def}">
		        	<br>
		        	<a href="?first=${currency.id}"><fmt:message key="button.makedefault"/></a>
		        </c:if>
	        </td>
	    </tr>
	</c:forEach>
</table>

