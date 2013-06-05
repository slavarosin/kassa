<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="<c:url value='/js/additem.js'/>"></script>

<c:set var="convert" value="${currencies[0].sign} -&gt; ${currencies[0].sign}"/>

<form method="post">
	<table width="100%" border="1">
	    <tr>
	        <th><fmt:message key="label.amount"/></th>
	        <th><fmt:message key="label.currency"/></th>
	        <th><input type="checkbox" onchange="enableExchange(this.checked);"/><fmt:message key="label.exchange"/></th>
	        <th><fmt:message key="label.type"/></th>
	        <th><fmt:message key="header.date"/></th>
	        <th><fmt:message key="header.user"/></th>
	        <th><fmt:message key="label.company"/></th>
	        <th><fmt:message key="label.comments"/></th>
	        <c:if test="${(type eq 'CREDIT') or (type eq 'CREDITBACK')}">
                <th><fmt:message key="label.creditor"/></th>
	        </c:if>
	    </tr>
	     <tr>
            <td align="center"><input name="amount"/></td>
            <td align="center">
                <select id="currencyFrom" name="currencyFrom" onchange="updateCurrencyRate('${type.in}');">
                    <c:forEach items="${currencies}" var="currency">
                        <option value="${currency.id}">${currency.name}</option>
                    </c:forEach>
                </select>
            </td>
            <td align="center">
                <div id="convertLabel" style="display: none;">${convert}</div>
                <input name="rate" id="rate" disabled="disabled"/>
                <select id="currencyTo" name="currencyTo" onchange="updateCurrencyRate('${type.in}');" disabled="disabled">
                    <c:forEach items="${currencies}" var="currency">
                        <option value="${currency.id}">${currency.name}</option>
                    </c:forEach>
                </select>
                </td>
            <td align="center"><fmt:message key="label.type.${type}"/></td>
            <td align="center"><input name="date" value="${now}"/></td>
            <td align="center">${User.login}</td>
             <td align="center">
                <select name="company">
                    <!-- option value="">-</option-->
                    <c:forEach items="${companies}" var="company">
                        <c:choose>
                            <c:when test="${company.name eq company}">
                                <option value="${company.name}" selected="selected">${company.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${company.name}">${company.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
            <td align="center">
                <select name="standardComment">
                    <option value="">-</option>
                    <c:forEach items="${comments}" var="comment">
                        <c:choose>
                            <c:when test="${comment.text eq standardComment}">
                                <option value="${comment.text}" selected="selected">${comment.text}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${comment.text}">${comment.text}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <input name="comments"/>
            </td>
            <c:if test="${(type eq 'CREDIT') or (type eq 'CREDITBACK')}">
                <td align="center">
                      <select name="standardCreditor">
	                    <option value="" selected="selected">-</option>
	                    <c:forEach items="${creditors}" var="creditor">
	                        <option value="${text}">${creditor}</option>
	                    </c:forEach>
	                </select>
                    <input name="creditor"/>
                </td>
            </c:if>
        </tr>
	</table>
	<br>
	<div align="right">
	   <input type="submit" value="<fmt:message key="button.enter"/>"/>
	</div>

	<script type="text/javascript">updateRate('1')</script>

	<c:forEach items="${rates}" var="rate">
        <input type="hidden" id="${rate.currency1}_${rate.currency2}" value="${rate.rate}"/>
    </c:forEach>

    <c:forEach items="${currencies}" var="currency">
        <input type="hidden" id="sign${currency.id}" value="${currency.sign}"/>
    </c:forEach>
</form>