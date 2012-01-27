<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/themes/jquery.ui.theme.min.css" />" />
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.min.css" />" />
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/custom.css" />" />

<script type="text/javascript" src="<c:url value="/resources/javascript/jquery/jquery-1.7.1-core-min.js" /> "></script>
<script type="text/javascript" src="<c:url value="/resources/javascript/jquery/jquery-ui-1.8.16-min.js" /> "></script>
<script type="text/javascript" src="<c:url value="/resources/javascript/json-min.js" /> "></script>
<script type="text/javascript" src="<c:url value="/resources/javascript/custom.js" /> "></script>
<title></title>
</head>
<body>
   <div align="center">
		<form:form modelAttribute="account" action="saveUser" method="post">
			<fieldset>
				<%-- show all errors in one tag.. - specify element to put them in - uses span by default
				<form:errors path="*" cssClass="errorblock" element="div" /> --%>
		    	<p>
					<form:label id="person.firstNameLabel" for="person.firstName" path="person.firstName">
						<spring:message code="label.firstName" />
					</form:label>
					<form:input id="person.firstName" path="person.firstName" autocomplete="on" size="50" style="font-size: 20px" />
					<br/>
					<form:errors path="person.firstName" cssClass="error" />
				</p>
				<p>
					<form:label id="person.middleNameLabel" for="person.middleName" path="person.middleName">
						<spring:message code="label.middleName" />
					</form:label>
					<form:input id="person.middleName" path="person.middleName" autocomplete="on" size="50" style="font-size: 20px" />
					<br/>
					<form:errors path="person.middleName" cssClass="error" />
				</p>
				<p>
					<form:label id="person.lastNameLabel" for="person.lastName" path="person.lastName">
						<spring:message code="label.lastName" />
					</form:label>
					<form:input id="person.lastName" path="person.lastName" autocomplete="on" size="50" style="font-size: 20px" />
					<br/>
					<form:errors path="person.lastName" cssClass="error" />	
				</p>
				<p>
					<form:label id="login.usernameLabel" for="login.username" path="login.username">
						<spring:message code="label.username" />
					</form:label>
					<form:input id="login.username" path="login.username" autocomplete="on" size="50" style="font-size: 20px" />
					<br/>
					<form:errors path="login.username" cssClass="error" />	
				</p>
				<p>
					<form:label id="login.passwordLabel" for="login.password" path="login.password">
						<spring:message code="label.password" />
					</form:label>
					<form:password id="login.password" path="login.password" autocomplete="on" size="50" style="font-size: 20px" />
					<br/>
					<form:errors path="login.password" cssClass="error" />	
					<form:errors path="login" cssClass="error" />
				</p>
				<p>
					<form:label id="login.passwordVerifyLabel" for="login.passwordVerify" path="login.passwordVerify">
						<spring:message code="label.passwordVerify" />
					</form:label>
					<form:password id="login.passwordVerify" path="login.passwordVerify" autocomplete="on" size="50" style="font-size: 20px" />
					<br/>
					<form:errors path="login.passwordVerify" cssClass="error" />
					<form:errors path="login" cssClass="error" />	
				</p>
				<input id="createUser" value="Create User" type="submit" />
			    <br/>
			    <a id="index" href="<c:url value="/" />"><spring:message code="label.home"/></a>
			    <br/>
			</fieldset>
		</form:form>
	</div>	
</body>
</html>