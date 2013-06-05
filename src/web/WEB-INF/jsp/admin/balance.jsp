<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form method="post">
	<table width="50%" border="1">
	    <tr>
	        <td><fmt:message key="label.initialbalance"/></td>
	        <td>
	           <select name="currency">
                    <c:forEach items="${currencies}" var="currency">
                        <option value="${currency.id}">${currency.name}</option>
                    </c:forEach>
                </select>
	        </td>
	        <td><input name="balance" value="${balance.amount}"/>&nbsp;${balance.currencyFrom.sign}</td>
	        <td>&nbsp;<a href="javascript:document.forms[0].submit();"><fmt:message key="button.update"/></a>
	        </td>
	    </tr>
	</table>
</form>