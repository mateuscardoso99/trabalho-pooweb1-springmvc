<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="pt-br">
    <c:import url="./components/header.jsp" />
    <body>
        <c:import url="./components/menu.jsp" />

        <div class="container">

            <!-- <div class="row mb-3">
                <c:if test="${not empty sessionScope.error}">
                    <div class="alert alert-danger" role="alert">
                        ${sessionScope.error}
                    </div>
                    <c:set var="error" scope="session" value=""></c:set>
                </c:if>

                <c:if test="${not empty sessionScope.success}">
                    <div class="alert alert-success" role="alert">
                        ${sessionScope.success}
                    </div>
                    <c:set var="success" scope="session" value=""></c:set>
                </c:if>
            </div> -->

            <div class="row mb-3">
                <form:form action="${pageContext.request.contextPath}/user/perfil/update" method="post" modelAttribute="user">
                    <div class="mb-3">
                        <form:label path="nome" class="form-label">Nome</form:label>
                        <form:input type="text" path="nome" class="form-control" value="${sessionScope.usuario.nome}"/>
                        <!-- <c:if test="${not empty sessionScope.validationErrors}">
                            <c:forEach items="${sessionScope.validationErrors.nome}" var="error">
                                <span class="text-danger">
                                    ${error}
                                </span>
                            </c:forEach>
                        </c:if> -->
                    </div>
                    <div class="mb-3">
                        <form:label path="email" class="form-label">Email</form:label>
                        <form:input type="text" path="email" class="form-control" value="${sessionScope.usuario.email}"/>
                        <!-- <c:if test="${not empty sessionScope.validationError}">
                            <c:forEach items="${sessionScope.validationErrors.email}" var="error">
                                <span class="text-danger">
                                    ${error}
                                </span>
                            </c:forEach>
                        </c:if> -->
                    </div>
                    <div class="mb-3">
                        <form:label path="senha" class="form-label">Nova senha</form:label>
                        <form:input type="password" path="senha" class="form-control"/>
                        <!-- <c:if test="${not empty sessionScope.validationErrors}">
                            <c:forEach items="${sessionScope.validationErrors.senha}" var="error">
                                <span class="text-danger">
                                    ${error}
                                </span>
                            </c:forEach>
                        </c:if> -->
                    </div>
                    <!-- <c:set var="validationErrors" scope="session" value=""></c:set> -->
                    <button type="submit" class="btn btn-success">Salvar</button>
                </form:form>
            </div>

            <div class="row mt-5">
                <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal">
                    APAGAR CONTA
                </button>
            </div>

            <!--APAGAR CONTA-->
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">Apagar conta</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            Deseja apagar a conta, todos os seus dados serão excluídos
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                            <form action="${pageContext.request.contextPath}/user/apagar-conta" method="post">
                                <button type="submit" class="btn btn-danger">Apagar conta</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <c:import url="./components/footer.jsp" />
    </body>
</html>