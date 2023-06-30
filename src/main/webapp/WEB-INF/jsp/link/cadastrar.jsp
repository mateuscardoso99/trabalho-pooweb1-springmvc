<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pt-br">
    <c:import url="../components/header.jsp" />
    <body>
        <c:import url="../components/menu.jsp" />

        <div class="container">

            <div class="row">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                      <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/user/link/show">Link</a></li>
                      <li class="breadcrumb-item active" aria-current="page">Cadastrar</li>
                    </ol>
                  </nav>
            </div>

            <!-- <div class="row mb-3">
                <c:if test="${not empty requestScope.error}">
                    <div class="alert alert-danger" role="alert">
                        ${requestScope.error}
                    </div>
                    <c:set var="error" scope="request" value=""></c:set>
                </c:if>
            </div> -->

            <div class="row">
                <form:form action="${pageContext.request.contextPath}/user/link/create" method="post" modelAttribute="link">
                    <div class="mb-3">
                        <form:label path="url" class="form-label">URL</form:label>
                        <form:input type="text" path="url" class="form-control"/>
                        <!-- <c:if test="${not empty requestScope.validationErrors}">
                            <c:forEach items="${requestScope.validationErrors.url}" var="error">
                                <span class="text-danger d-block">
                                    ${error}
                                </span>
                            </c:forEach>
                        </c:if> -->
                    </div>
                    <div class="mb-3">
                        <form:label path="descricao" class="form-label">Descrição</form:label>
                        <form:textarea path="descricao" class="form-control" cols="10" rows="10"/>
                        <!-- <c:if test="${not empty requestScope.validationErrors}">
                            <c:forEach items="${requestScope.validationErrors.descricao}" var="error">
                                <span class="text-danger">${error}</span>
                            </c:forEach>
                        </c:if> -->
                    </div>
                    <!-- <c:set var="validationErrors" scope="request" value=""></c:set> -->
                    <button type="submit" name="action" class="btn btn-success" value="cadastrar">Salvar link</button>
                </form:form>
            </div>
        </div>

        <c:import url="../components/footer.jsp" />
    </body>
</html>