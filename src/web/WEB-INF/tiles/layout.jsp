<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
	<head>
		<title><fmt:message key="app.title"/></title>
		<link href="<c:url value="/styles/style.css"/>" type="text/css" rel="stylesheet">
	</head>
	<body>
		<table width="100%">
			<tr>
				<td align="right">
					<tiles:insertDefinition name="menu"/>
				</td>
			</tr>
			<tr>
				<td width="100%" valign="top">
					<!-- Content Start -->
					<tiles:insertAttribute name="content"/>
					<!-- Content End -->
				</td>
			</tr>
			<tr>
                <td class="version" align="right">
                    <fmt:formatDate value='${Version.date}' pattern="dd.MM.yy HH:mm" var="versionDate"/>
                    <fmt:message key="app.version">
                        <fmt:param value="${Version.number}"/>
                        <fmt:param value="${versionDate}"/>
                    </fmt:message>
                </td>
            </tr>
		</table>
	</body>
</html>
