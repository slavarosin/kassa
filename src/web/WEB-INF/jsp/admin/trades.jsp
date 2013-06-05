<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table width="100%" border="1">
	<tr>
		<th><fmt:message key="header.trades.amount"/></th>
        <th><fmt:message key="header.trades.price"/></th>
        <th><fmt:message key="header.trades.sum"/></th>
        <th><fmt:message key="header.trades.currency"/></th>
        <th><fmt:message key="header.trades.milestone"/></th>
        <th><fmt:message key="header.trades.company"/></th>
        <th><fmt:message key="header.date"/></th>
        <th><fmt:message key="header.user"/></th>
        <th><fmt:message key="header.trades.trackingID"/> / <fmt:message key="header.trades.received"/></th>
	</tr>
	<c:forEach items="${trades}" var="trade" varStatus="status">
	    <tr>
	        <td>${trade.amount}</td>
	        <td>${trade.price}</td>
	        <td>${trade.price * trade.amount}</td>
	        <td>£</td>
	        <td>${trade.milestone.id}</td>
	        <td>
	        	<c:choose>
	        		<c:when test="${not empty trade.company}">${trade.company}</c:when>
	        		<c:otherwise>-</c:otherwise>
	        	</c:choose>
	        </td>
	        <td><fmt:formatDate value="${trade.date}" pattern="dd.MM.yyyy"/></td>
            <td>${trade.createdBy.login}</td>
	        <td valign="middle">
               <form method="post">
                    <input name="id" value="${trade.id}" type="hidden"/>
                    <input name="trackingID" value="${trade.trackingID}" style="width: 50%"/>
		            <c:if test="${trade.received}">
		                <input name="received" type="checkbox" checked="checked" />
		            </c:if>
		            <c:if test="${not trade.received}">
		                <input name="received" type="checkbox"/>
		            </c:if>
		            &nbsp;<a href="javascript:document.forms[${status.index}].submit();"><fmt:message key="button.update"/></a>
	            </form>
            </td>
	    </tr>
	</c:forEach>
</table>

