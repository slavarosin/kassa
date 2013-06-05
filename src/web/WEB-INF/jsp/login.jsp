<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form:form commandName="user">
	<div>
		<table width="50%">
			<tr>
				<td valign="middle" width="10%"><fmt:message key="label.username"/></td>
				<td width="10%">
					<form:input htmlEscape="true" path="login"/>
				</td>
			</tr>
			<tr>
				<td valign="middle"><fmt:message key="label.password"/></td>
				<td>
					<form:password path="passwd"/>
				</td>
			</tr>
		</table>
	</div>
	<br>
	<div class="actions">
		<input type="submit" value="<fmt:message key="button.login"/>" name="save" id="save"/>
	</div>
</form:form>
