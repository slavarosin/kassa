<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="<c:url value='/js/stats.js'/>"></script>

<form method="post">
    <input type="hidden" name="filter" value="true"/>
    <table width="100%" border="1">
        <tr>
            <th><fmt:message key="header.user"/></th>
            <th><fmt:message key="header.comments"/></th>
            <th><fmt:message key="header.time"/></th>
        </tr>
        <tr>
            <td align="center">
                <select name="user">
                        <option value="">-</option>
                        <c:forEach items="${users}" var="u">
                            <c:choose>
                                <c:when test="${u.id eq user.id}">
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
                <input id="from" name="from" value="<fmt:formatDate value='${dateFrom}' pattern='dd.MM.yyyy'/>"/>
                <input id="to" name="to" value="<fmt:formatDate value='${dateTo}' pattern='dd.MM.yyyy'/>"/>
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
        <table width="100%" border="1" style="font-size: 8pt;">
            <tr>
                <th width="12%"><fmt:message key="label.amount"/></th>
                <th width="5%"><fmt:message key="header.currencyFrom"/></th>
                <th width="5%"><fmt:message key="label.rate"/></th>
                <th width="5%"><fmt:message key="header.currencyTo"/></th>
                <th width="15%"><fmt:message key="label.type"/></th>
                <th width="10%"><fmt:message key="header.date"/></th>
                <th width="8%"><fmt:message key="header.user"/></th>
                <th width="15%"><fmt:message key="label.comments"/></th>
                <th width="10%"><fmt:message key="label.usercomments"/></th>
                <th width="10%"><fmt:message key="label.creditor"/></th>
                <th width="5%"><fmt:message key="header.action"/></th>
            </tr>
        </table>
        <c:forEach items="${items}" var="item" varStatus="status">
            <form method="post" style="margin: 0px; padding: 0px;">
            <input name="id" value="${item.id}" type="hidden"/>
            <table width="100%" border="1" style="font-size: 8pt;">
               <tr>
                   <td align="center"  width="12%">
                       <input style="width: 100px" name="amount" value="<fmt:formatNumber maxFractionDigits="2" groupingUsed="false" value="${item.amount}"/>"/>
                   </td>
                   <td align="center"  width="5%">
                        <select name="currencyFrom">
                            <c:forEach items="${currencies}" var="currency">
                                <c:choose>
                                    <c:when test="${currency.id eq item.currencyFrom.id}">
                                        <option value="${currency.id}" selected="selected">${currency.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${currency.id}">${currency.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                   </td>
                   <td align="center"  width="5%"><input style="width: 50px" name="rate" value="<fmt:formatNumber value="${item.rate}"/>"/></td>
                     <td align="center"  width="5%">
                        <select name="currencyTo">
                            <c:forEach items="${currencies}" var="currency">
                                <c:choose>
                                    <c:when test="${currency.id eq item.currencyTo.id}">
                                        <option value="${currency.id}" selected="selected">${currency.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${currency.id}">${currency.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                   </td>
                   <td align="center" width="15%"><fmt:message key="label.type.${item.type}"/></td>
                   <td align="center" width="10%"><fmt:formatDate value="${item.date}" pattern="dd.MM.yy"/></td>
                   <td align="center" width="8%">
                        <select name="user">
                            <c:forEach items="${users}" var="user">
                                <c:choose>
                                    <c:when test="${user.id eq item.createdBy.id}">
                                        <option value="${user.id}" selected="selected">${user.login}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${user.id}">${user.login}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                   </td>
                   <td align="center" width="15%">
                        <select name="comments">
						        <option value="">-</option>
						        <c:forEach items="${comments}" var="c">
						            <c:choose>
						                <c:when test="${c.text eq item.comments}">
						                    <option value="${c.text}" selected="selected">${c.text}</option>
						                </c:when>
						                <c:otherwise>
						                    <option value="${c.text}">${c.text}</option>
						                </c:otherwise>
						            </c:choose>
						        </c:forEach>
						</select>
                   </td>
                   <td align="center" width="10%">
                       <input style="width: 100px" name="userComments" value="${item.userComments}"/>
                   </td>
                   <td align="center" width="10%">
                   		<select name="standardCreditor">
							<option value="aaa" selected="selected">-</option>
							<c:forEach items="${creditors}" var="creditor">
								<c:choose>
					                <c:when test="${creditor eq item.creditor}">
					                    <option value="${creditor}" selected="selected">${creditor}</option>
					                </c:when>
					                <c:otherwise>
					                    <option value="${creditor}">${creditor}</option>
					                </c:otherwise>
					            </c:choose>
							</c:forEach>
						</select>
						<input name="creditor" value="${item.creditor}"/>
                   </td>
                   <td align="center" width="5%">
						<a href="javascript:document.forms[${status.index + 1}].submit();"><fmt:message key="button.update"/></a>
						&nbsp;
						<a href="?remove=${item.id}"><fmt:message key="button.remove"/></a>
				   </td>
               </tr>
            </table>
            </form>
        </c:forEach>
   </div>
</c:if>