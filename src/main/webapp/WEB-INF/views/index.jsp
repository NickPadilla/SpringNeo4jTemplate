<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<html>
<head>
    <title></title>
</head>
<body>
	<div align="center">
	    <br/>
	    <br/>
	    <br/>
	    <a id="toHome" href="<c:url value="addUser" />"><spring:message code="label.addUser"/></a>
	    <br/>
	    <br/>
	    <a id="toUsers" href="<c:url value="listUsers" />"><spring:message code="label.showUsers"/></a>
	</div>
</body>
</html>