<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="<c:url value='/js/stats.js'/>"></script>

<form method="post">
	<table width="100%" border="1">
		<tr>
			<th><fmt:message key="header.type"/></th>
			<th><fmt:message key="header.user"/></th>
			<th><fmt:message key="header.creditor"/></th>
			<th><fmt:message key="header.comments"/></th>
			<th><fmt:message key="header.time"/></th>
		</tr>
		<tr>
	        <td align="center">
	            <select name="type">
                    <c:forEach items="${types}" var="t">
                        <c:choose>
	                        <c:when test="${t eq type}">
                                <option value="${t}" selected="selected"><fmt:message key="label.type.${t}"/></option>
	                        </c:when>
	                        <c:otherwise>
                                   <option value="${t}"><fmt:message key="label.type.${t}"/></option>
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
            <td align="center">
                <select name="creditor">
                        <option value="">-</option>
                        <c:forEach items="${creditors}" var="cr">
                            <c:choose>
                                <c:when test="${cr eq creditor}">
                                    <option value="${cr}" selected="selected">${cr}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${cr}">${cr}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                </select>
            </td>
            <td align="center">
                <select name="comment">
                        <option value="">-</option>
                        <c:forEach items="${comments}" var="c">
                            <c:choose>
                                <c:when test="${c.text eq comment}">
                                    <option value="${c.text}" selected="selected">${c.text}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${c.text}">${c.text}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                </select>
            </td>
            <td align="center">
                <div style="font-size: 9pt;">
                    <a href="javascript:setDates('${today}','${today}');"><fmt:message key="button.today"/></a>
                    <a href="javascript:setDates('${yesterday}','${yesterday}');"><fmt:message key="button.yesterday"/></a>
                    <a href="javascript:setDates('${thisWeekStart}','${thisWeekEnd}');"><fmt:message key="button.currentweek"/></a>
                    <a href="javascript:setDates('${thisMonthStart}','${thisMonthEnd}');"><fmt:message key="button.currentmonth"/></a>
                    <a href="javascript:setDates('${prevWeekStart}','${prevWeekEnd}');"><fmt:message key="button.prevweek"/></a>
                    <a href="javascript:setDates('${prevMonthStart}','${prevMonthEnd}');"><fmt:message key="button.prevmonth"/></a>
                    <a href="javascript:setDates('${prevMonthStart}','${today}');"><fmt:message key="button.prevmonthtoday"/></a>
                    <a href="javascript:setDates('${thisYearStart}','${thisYearEnd}');"><fmt:message key="button.currentyear"/></a>
                </div>
                <input id="from" name="from" value="${from}"/>
                <input id="to" name="to" value="${to}"/>
            </td>
	    </tr>
	</table>
	<br>
	<div align="right">
		<input type="hidden" value="${orderBy}" name="orderBy" id="orderBy"/>
		<input type="hidden" value="${orderType}" name="orderType" id="orderType"/>
        <input type="submit" value="<fmt:message key="button.show"/>"/>
	</div>
</form>

<c:if test="${not empty items}">
    <div align="center">
        <table width="100%" border="1" style="font-size: 8pt;">
	        <tr>
	            <th>
                    <a href="#" onclick="order('amount', 'asc');"><img src="<c:url value='/images/order_asc.gif'/>" border="0"/></a>
                    &nbsp;&nbsp;<fmt:message key="label.amount"/>&nbsp;&nbsp;
                    <a href="#" onclick="order('amount', 'desc');"><img src="<c:url value='/images/order_desc.gif'/>" border="0"/></a>
                </th>
                <th>
                    <a href="#" onclick="order('currencyFrom', 'asc');"><img src="<c:url value='/images/order_asc.gif'/>" border="0"/></a>
                    &nbsp;&nbsp;<fmt:message key="label.currency"/>&nbsp;&nbsp;
                    <a href="#" onclick="order('currencyFrom', 'desc');"><img src="<c:url value='/images/order_desc.gif'/>" border="0"/></a>
                </th>
                <th>
                    <a href="#" onclick="order('type', 'asc');"><img src="<c:url value='/images/order_asc.gif'/>" border="0"/></a>
                    &nbsp;&nbsp;<fmt:message key="label.type"/>&nbsp;&nbsp;
                    <a href="#" onclick="order('type', 'desc');"><img src="<c:url value='/images/order_desc.gif'/>" border="0"/></a>
                </th>
                <th>
                    <a href="#" onclick="order('date', 'asc');"><img src="<c:url value='/images/order_asc.gif'/>" border="0"/></a>
                    &nbsp;&nbsp;<fmt:message key="header.date"/>&nbsp;&nbsp;
                    <a href="#" onclick="order('date', 'desc');"><img src="<c:url value='/images/order_desc.gif'/>" border="0"/></a>
                </th>
                <th>
                    <a href="#" onclick="order('createdBy', 'asc');"><img src="<c:url value='/images/order_asc.gif'/>" border="0"/></a>
                    &nbsp;&nbsp;<fmt:message key="header.user"/>&nbsp;&nbsp;
                    <a href="#" onclick="order('createdBy', 'desc');"><img src="<c:url value='/images/order_desc.gif'/>" border="0"/></a>
                </th>
                <th>
                    <a href="#" onclick="order('comments', 'asc');"><img src="<c:url value='/images/order_asc.gif'/>" border="0"/></a>
                    &nbsp;&nbsp;<fmt:message key="label.comments"/>&nbsp;&nbsp;
                    <a href="#" onclick="order('comments', 'desc');"><img src="<c:url value='/images/order_desc.gif'/>" border="0"/></a>
                </th>
                <th>
                    <a href="#" onclick="order('userComments', 'asc');"><img src="<c:url value='/images/order_asc.gif'/>" border="0"/></a>
                    &nbsp;&nbsp;<fmt:message key="label.usercomments"/>&nbsp;&nbsp;
                    <a href="#" onclick="order('userComments', 'desc');"><img src="<c:url value='/images/order_desc.gif'/>" border="0"/></a>
                </th>
                <th>
                    <a href="#" onclick="order('creditor', 'asc');"><img src="<c:url value='/images/order_asc.gif'/>" border="0"/></a>
                    &nbsp;&nbsp;<fmt:message key="label.creditor"/>&nbsp;&nbsp;
                    <a href="#" onclick="order('creditor', 'desc');"><img src="<c:url value='/images/order_desc.gif'/>" border="0"/></a>
                </th>
	        </tr>
	        <c:forEach items="${items}" var="item">
	            <tr>
		            <td align="center">
                        <fmt:formatNumber maxFractionDigits="2" groupingUsed="true" value="${item.amount}"/>
                        <c:if test="${item.currencyFrom ne item.currencyTo}">
                            -> <fmt:formatNumber maxFractionDigits="2" groupingUsed="true" value="${item.amount * item.rate}"/>
                        </c:if>
                    </td>
		            <td align="center">
                        ${item.currencyFrom.name}
                        <c:if test="${item.currencyFrom ne item.currencyTo}">
                            -> ${item.currencyTo.name} (${item.rate})
                        </c:if>
		            </td>
		            <td align="center"><fmt:message key="label.type.${item.type}"/></td>
		            <td align="center"><fmt:formatDate value="${item.date}" pattern="dd.MM.yy"/></td>
		            <td align="center">${item.createdBy.login}</td>
		            <td align="center">
                        <c:choose>
                            <c:when test="${not empty item.comments}">${item.comments}</c:when>
                            <c:otherwise>-</c:otherwise>
                        </c:choose>
		            </td>
		            <td align="center">
                        <c:choose>
                            <c:when test="${not empty item.userComments}">${item.userComments}</c:when>
                            <c:otherwise>-</c:otherwise>
                        </c:choose>
		            </td>
		            <td align="center">
                        <c:choose>
                            <c:when test="${not empty item.creditor}">${item.creditor}</c:when>
                            <c:otherwise>-</c:otherwise>
                        </c:choose>
                    </td>
		        </tr>
	        </c:forEach>
	    </table>
    </div>
</c:if>
<c:if test="${not empty sum}">
    <div align="center" style="font-size: 30pt;">
	    <fmt:message key="label.stats"/>
	    <br>
	    <c:forEach items="${sum}" var="sumElement" varStatus="status">
	        <b><fmt:formatNumber groupingUsed="true" maxFractionDigits="2" value="${sumElement.value}"/>&nbsp;${sumElement.key.sign}</b>
	        <c:if test="${not status.last}"><br></c:if>
	    </c:forEach>
	</div>
	<br>
	<div align="center" style="font-size: 15pt; color: gray;" id="otherCurrencies">
	    <fmt:message key="label.approxEqual"/><br>
	    <c:forEach items="${otherCurrencies}" var="oc">
	        <fmt:formatNumber groupingUsed="true" maxFractionDigits="2" value="${oc.value}"/>&nbsp;${oc.key.sign}<br>
	    </c:forEach>
	</div>
</c:if>