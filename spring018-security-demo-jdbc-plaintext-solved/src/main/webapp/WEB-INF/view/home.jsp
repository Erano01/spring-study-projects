<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	
	Welcome
	asd
	
	
	<hr>
	
	<!-- add a link to point to /leaders... this is for the managers -->
	<security:authorize access="hasRole('MANAGER')">
		<p>
		<a href="${pageContext.request.contextPath }/leaders">LeaderShip meeting</a>
		(Only for manager peeps)
		</p>
		<hr>
	</security:authorize>
	
	
	<!--add a link to point to /systems ... this is for the admins -->
	
	<security:authorize access="hasRole('ADMIN')">
		<p>
		<a href="${pageContext.request.contextPath }/systems">Admins meeting</a>
		(Only for manager peeps)
		</p>	
		<hr>	
	</security:authorize>
	
	
	<form:form action="${pageContext.request.contextPath}/logout" 
	method="POST">
	
		<input type="submit" value="Logout"/> 
	
	</form:form>
	
	<hr>
	
	<p>
	User: <security:authentication property="principal.username"/>
	<br><br><!-- roller aşağıda authorities = roles -->
	Role(s):<security:authentication property="principal.authorities"/>	
	</p>
	
	
	
</body>
</html>