<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <c:import url="../components/header.jsp" />
    <body>
        <c:import url="../components/menu.jsp" />

        <div class="container">
            <div class="row">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                      <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/user/contato/show">Contato</a></li>
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
                <form:form action="${pageContext.request.contextPath}/user/contato/create" method="post" modelAttribute="contato" enctype="multipart/form-data">
                    <div class="mb-3">
                        <form:label path="nome" class="form-label">Nome</form:label>
                        <form:input type="text" path="nome" class="form-control"/>
                        <form:errors path="nome" cssClass="text-danger"/></td>
                    </div>
                    <div class="mb-3">
                        <form:label path="telefone" class="form-label">Telefone</form:label>
                        <form:input type="tel" path="telefone" class="form-control"/>
                        <form:errors path="telefone" cssClass="text-danger"/></td>
                    </div>
                    <div class="mb-3">
                        <form:label path="foto" class="form-label">Foto</form:label>
                        <form:input type="file" path="foto" class="form-control"/>
                    </div>
                    <button type="submit" name="action" class="btn btn-success" value="cadastrar">Salvar contato</button>
                </form:form>
            </div>
        </div>

        <c:import url="../components/footer.jsp" />
    </body>
</html>