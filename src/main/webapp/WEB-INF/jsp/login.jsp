<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <c:import url="./components/header.jsp" />
    <body>
        <c:import url="./components/menu.jsp" />
        <div class="container">
            <c:if test="${not empty sessionScope.error}">
                <div class="alert alert-danger" role="alert">
                    ${sessionScope.error}
                </div>
                <c:set var="error" scope="session" value=""></c:set>
            </c:if>

            <div class="row mt-3">
                <form:form action="login" method="post" modelAttribute="login">
                    <div class="mb-3">
                        <form:label path="email" class="form-label">Email</form:label>
                        <form:input type="text" path="email" class="form-control"/>
                    </div>
                    <div class="mb-3">
                        <form:label path="senha" class="form-label">Email</form:label>
                        <form:input type="password" path="senha" class="form-control"/>
                    </div>
                    <button type="submit" class="btn btn-primary">Entrar</button>
                </form:form>
            </div>
        </div>
        <c:import url="./components/footer.jsp" />
    </body>
</html>