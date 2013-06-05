<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form method="post">
	<table width="100%" border="1">
		<tr>
			<th><fmt:message key="header.milestone"/></th>
			<th><fmt:message key="header.user"/></th>
		</tr>
		<tr>
	        <td align="center">
	            <select name="milestone">
	                <option value="">-</option>
	                    <c:forEach items="${milestones}" var="m">
	                        <c:choose>
                                <c:when test="${empty m.date}">
                                    <fmt:message key="milestones.current" var="date"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:formatDate var="date" value="${m.date}" pattern="dd.MM.yy"/>
                                </c:otherwise>
	                        </c:choose>
	                        <c:choose>
		                        <c:when test="${m.id eq milestone}">
	                                <option value="${m.id}" selected="selected">${m.id} (${date})</option>
		                        </c:when>
		                        <c:otherwise>
                                    <option value="${m.id}">${m.id} (${date})</option>
                                </c:otherwise>
	                        </c:choose>
	                    </c:forEach>
	            </select>
	        </td>
	        <td align="center">
                <select name="user">
                        <option value="">-</option>
                        <c:forEach items="${users}" var="u">
                            <c:choose>
                                <c:when test="${u.id eq user}">
                                    <option value="${u.id}" selected="selected">${u.login}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${u.id}">${u.login}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                </select>
            </td>
	    </tr>
	</table>
	<br>
	<div align="right">
        <input type="submit" value="<fmt:message key="button.show"/>"/>
	</div>
</form>
<c:if test="${not empty items}">
    <div align="center">
    	<c:set var="sum" value="0"/>
        <table width="100%" border="1" style="font-size: 8pt;">
	      <tr>
		        <th><fmt:message key="header.trades.amount"/></th>
		        <th><fmt:message key="header.trades.price"/></th>
		        <th><fmt:message key="header.trades.currency"/></th>
		        <th><fmt:message key="header.trades.milestone"/></th>
		        <th><fmt:message key="header.trades.company"/></th>
		        <th><fmt:message key="header.date"/></th>
		        <th><fmt:message key="header.user"/></th>
		        <th><fmt:message key="header.trades.trackingID"/></th>
		        <th><fmt:message key="header.trades.received"/></th>
		    </tr>
	        <c:forEach items="${items}" var="trade">
	       	  <c:set value="${sum + trade.amount * trade.price}" var="sum"></c:set>
	          <tr>
		            <td>${trade.amount}</td>
		            <td>${trade.price}</td>
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
		            <td>
                        <c:choose>
                            <c:when test="${not empty trade.trackingID}">${trade.trackingID}</c:when>
                            <c:otherwise>-</c:otherwise>
                        </c:choose>
		            </td>
		            <td><fmt:message key="value.${trade.received}"/></td>
		        </tr>
	        </c:forEach>
	    </table>
    </div>
</c:if>

<c:if test="${not empty sum}">
	<div align="center" style="font-size: 30pt;">
		<fmt:message key="label.sum"/>
	    <b><fmt:formatNumber maxFractionDigits="2" value="${sum}"/>&nbsp;£</b>
	</div>
<br>
<div align="center" style="font-size: 15pt; color: gray;" id="otherCurrencies">
    <fmt:message key="label.approxEqual"/><br>
    <c:forEach items="${otherCurrencies}" var="oc">
    	<c:if test="${oc.key.sign != '£'}">
        <fmt:formatNumber maxFractionDigits="2" value="${oc.value}"/>&nbsp;${oc.key.sign}<br>
        </c:if>
    </c:forEach>
</div>
</c:if>