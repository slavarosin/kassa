<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty User}">
	<fmt:message key="menu.current.user"/>&nbsp;<b>${User.login}</b>
</c:if>
<c:if test="${User.role > 0}">
	<table width="100%" class="adminmenu" border="1">
		<tr>
		    <c:choose>
			     <c:when test="${menu eq 'manage/users'}">
			         <td class="selected"><a href="<c:url value="/do/manage/users"/>"><fmt:message key="menu.manage.users"/></a></td>
			     </c:when>
			     <c:otherwise>
			         <td><a href="<c:url value="/do/manage/users"/>"><fmt:message key="menu.manage.users"/></a></td>
			     </c:otherwise>
			</c:choose>
			<c:choose>
                 <c:when test="${menu eq 'manage/kassa'}">
                     <td class="selected"><a href="<c:url value="/do/manage/kassa"/>"><fmt:message key="menu.manage.kassa"/></a></td>
                 </c:when>
                 <c:otherwise>
                     <td><a href="<c:url value="/do/manage/kassa"/>"><fmt:message key="menu.manage.kassa"/></a></td>
                 </c:otherwise>
            </c:choose>
            <c:choose>
                 <c:when test="${menu eq 'manage/currencies'}">
                     <td class="selected"><a href="<c:url value="/do/manage/currencies"/>"><fmt:message key="menu.manage.currencies"/></a></td>
                 </c:when>
                 <c:otherwise>
                     <td><a href="<c:url value="/do/manage/currencies"/>"><fmt:message key="menu.manage.currencies"/></a></td>
                 </c:otherwise>
            </c:choose>
            <c:choose>
                 <c:when test="${menu eq 'manage/rates'}">
                     <td class="selected"><a href="<c:url value="/do/manage/rates"/>"><fmt:message key="menu.manage.rates"/></a></td>
                 </c:when>
                 <c:otherwise>
                     <td><a href="<c:url value="/do/manage/rates"/>"><fmt:message key="menu.manage.rates"/></a></td>
                 </c:otherwise>
            </c:choose>
            <c:choose>
                 <c:when test="${menu eq 'manage/balance'}">
                     <td class="selected"><a href="<c:url value="/do/manage/balance"/>"><fmt:message key="menu.manage.balance"/></a></td>
                 </c:when>
                 <c:otherwise>
                     <td><a href="<c:url value="/do/manage/balance"/>"><fmt:message key="menu.manage.balance"/></a></td>
                 </c:otherwise>
            </c:choose>
             <c:choose>
                 <c:when test="${menu eq 'manage/comments'}">
                     <td class="selected"><a href="<c:url value="/do/manage/comments"/>"><fmt:message key="menu.manage.comments"/></a></td>
                 </c:when>
                 <c:otherwise>
                     <td><a href="<c:url value="/do/manage/comments"/>"><fmt:message key="menu.manage.comments"/></a></td>
                 </c:otherwise>
            </c:choose>
             <c:choose>
                 <c:when test="${menu eq 'manage/companies'}">
                     <td class="selected"><a href="<c:url value="/do/manage/companies"/>"><fmt:message key="menu.manage.companies"/></a></td>
                 </c:when>
                 <c:otherwise>
                     <td><a href="<c:url value="/do/manage/companies"/>"><fmt:message key="menu.manage.companies"/></a></td>
                 </c:otherwise>
            </c:choose>
             <c:choose>
                 <c:when test="${menu eq 'manage/trades'}">
                     <td class="selected"><a href="<c:url value="/do/manage/trades"/>"><fmt:message key="menu.manage.trades"/></a></td>
                 </c:when>
                 <c:otherwise>
                     <td><a href="<c:url value="/do/manage/trades"/>"><fmt:message key="menu.manage.trades"/></a></td>
                 </c:otherwise>
            </c:choose>
             <c:choose>
                 <c:when test="${menu eq 'manage/milestones'}">
                     <td class="selected"><a href="<c:url value="/do/manage/milestones"/>"><fmt:message key="menu.manage.milestones"/></a></td>
                 </c:when>
                 <c:otherwise>
                     <td><a href="<c:url value="/do/manage/milestones"/>"><fmt:message key="menu.manage.milestones"/></a></td>
                 </c:otherwise>
            </c:choose>
		</tr>
	</table>
</c:if>

<table width="100%" class="menu" border="1">
	<tr>
	   <c:choose>
            <c:when test="${menu eq 'kassa'}">
                <td class="selected"><a href="<c:url value="/do/kassa"/>"><fmt:message key="menu.kassa"/></a></td>
            </c:when>
            <c:otherwise>
                <td><a href="<c:url value="/do/kassa"/>"><fmt:message key="menu.kassa"/></a></td>
            </c:otherwise>
	   </c:choose>
	   <c:choose>
            <c:when test="${menu eq 'addincome'}">
                <td class="selected"><a href="<c:url value="/do/add?type=income"/>"><fmt:message key="menu.add.income"/></a></td>
            </c:when>
            <c:otherwise>
                <td><a href="<c:url value="/do/add?type=income"/>"><fmt:message key="menu.add.income"/></a></td>
            </c:otherwise>
       </c:choose>
       <c:choose>
            <c:when test="${menu eq 'addoutcome'}">
                <td class="selected"><a href="<c:url value="/do/add?type=outcome"/>"><fmt:message key="menu.add.outcome"/></a></td>
            </c:when>
            <c:otherwise>
                <td><a href="<c:url value="/do/add?type=outcome"/>"><fmt:message key="menu.add.outcome"/></a></td>
            </c:otherwise>
       </c:choose>
       <c:choose>
            <c:when test="${menu eq 'addcredit'}">
                <td class="selected"><a href="<c:url value="/do/add?type=credit"/>"><fmt:message key="menu.add.credit"/></a></td>
            </c:when>
            <c:otherwise>
                <td><a href="<c:url value="/do/add?type=credit"/>"><fmt:message key="menu.add.credit"/></a></td>
            </c:otherwise>
       </c:choose>
       <c:choose>
            <c:when test="${menu eq 'addcreditback'}">
                <td class="selected"><a href="<c:url value="/do/add?type=creditback"/>"><fmt:message key="menu.add.creditback"/></a></td>
            </c:when>
            <c:otherwise>
                <td><a href="<c:url value="/do/add?type=creditback"/>"><fmt:message key="menu.add.creditback"/></a></td>
            </c:otherwise>
       </c:choose>
       <c:choose>
            <c:when test="${menu eq 'adddividend'}">
                <td class="selected"><a href="<c:url value="/do/add?type=dividend"/>"><fmt:message key="menu.add.dividend"/></a></td>
            </c:when>
            <c:otherwise>
                <td><a href="<c:url value="/do/add?type=dividend"/>"><fmt:message key="menu.add.dividend"/></a></td>
            </c:otherwise>
       </c:choose>
       <c:choose>
            <c:when test="${menu eq 'addtrade'}">
                <td class="selected"><a href="<c:url value="/do/addtrade"/>"><fmt:message key="menu.add.trade"/></a></td>
            </c:when>
            <c:otherwise>
                <td><a href="<c:url value="/do/addtrade"/>"><fmt:message key="menu.add.trade"/></a></td>
            </c:otherwise>
       </c:choose>
       <c:choose>
            <c:when test="${menu eq 'stats'}">
                <td class="selected"><a href="<c:url value="/do/stats"/>"><fmt:message key="menu.stats"/></a></td>
            </c:when>
            <c:otherwise>
                <td><a href="<c:url value="/do/stats"/>"><fmt:message key="menu.stats"/></a></td>
            </c:otherwise>
       </c:choose>
       <c:choose>
            <c:when test="${menu eq 'tradestats'}">
                <td class="selected"><a href="<c:url value="/do/tradestats"/>"><fmt:message key="menu.stats.trade"/></a></td>
            </c:when>
            <c:otherwise>
                <td><a href="<c:url value="/do/tradestats"/>"><fmt:message key="menu.stats.trade"/></a></td>
            </c:otherwise>
       </c:choose>
	   <td><a href="<c:url value="/do/logout"/>"><fmt:message key="menu.logout"/></a></td>
	</tr>
</table>
