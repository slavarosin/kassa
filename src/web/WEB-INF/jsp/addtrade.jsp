<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form method="post">
	<table width="100%" border="1">
	    <tr>
	        <th><fmt:message key="header.trades.amount"/></th>
	        <th><fmt:message key="header.trades.price"/></th>
	        <th><fmt:message key="header.trades.currency"/></th>
	        <th><fmt:message key="header.trades.milestone"/></th>
	        <th><fmt:message key="header.trades.trackingID"/></th>
	        <th><fmt:message key="header.trades.company"/></th>
	        <th><fmt:message key="header.trades.received"/></th>
	        <th><fmt:message key="header.date"/></th>
	        <th><fmt:message key="header.user"/></th>
	    </tr>
	     <tr>
            <td align="center"><input name="amount"/></td>
            <td align="center"><input name="price" value="${price}"/></td>
            <td align="center">£</td>
            <td align="center">${milestone}</td>
            <td align="center"><input name="trackingID"/></td>
            <td align="center"><input name="company"/></td>
            <td align="center"><input name="received" type="checkbox"/></td>
            <td align="center"><input name="date" value="${now}"/></td>
            <td align="center">${User.login}</td>
        </tr>
	</table>
	<br>
	<div align="right">
	   <input type="submit" value="<fmt:message key="button.enter"/>"/>
	</div>
</form>