<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div align="center" style="font-size: 30pt;">
    <fmt:message key="label.kassa"/>
    <br>
    <c:choose>
	    <c:when test="${not empty kassa}">
	    	<c:forEach items="${kassa}" var="kassaElement" varStatus="status">
		        <b><fmt:formatNumber maxFractionDigits="2" value="${kassaElement.value}"/>&nbsp;${kassaElement.key.sign}</b>
		        <c:if test="${not status.last}"><br></c:if>
		    </c:forEach>
	    </c:when>
	    <c:otherwise>
			<fmt:message key="label.kassa.empty"/>    
	    </c:otherwise>
    </c:choose>
</div>
<br>
<div align="center" style="font-size: 15pt; color: gray;" id="otherCurrencies">
    <fmt:message key="label.approxEqual"/><br>
    <c:forEach items="${otherCurrencies}" var="oc">
        <fmt:formatNumber maxFractionDigits="2" value="${oc.value}"/>&nbsp;${oc.key.sign}<br>
    </c:forEach>
</div>