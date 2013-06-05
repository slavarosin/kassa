<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:formatDate value="${updated}" pattern="dd.MM.yyyy" var="updated"/>
<fmt:message key="header.rates.updated">
    <fmt:param value="${updated}"/>
</fmt:message>
<table width="50%" border="1">
	<tr>
		<th width="20%"><fmt:message key="header.currencyFrom"/></th>
		<th width="20%"><fmt:message key="header.currencyTo"/></th>
		<th width="20%"><fmt:message key="header.value"/></th>
		<%--xth><fmt:message key="header.action"/></th --%>
	</tr>
	<c:forEach items="${rates}" var="rate" varStatus="status">
	    <tr>
	        <td>${rate.curr1.name}</td>
	        <td>${rate.curr2.name}</td>
	        <td>${rate.rate}</td>
	        <%--td valign="middle">
	                <form method="post">
	                    <input name="currency1" value="${rate.currency1}" type="hidden"/>
	                    <input name="currency2" value="${rate.currency2}" type="hidden"/>
                        <input name="rate" value="${rate.rate}"/>
                    </form>
            </td>
	        <td>&nbsp;<a href="javascript:document.forms[${status.index}].submit();"><fmt:message key="button.update"/></a>
	        </td --%>
	    </tr>
	</c:forEach>
</table>

