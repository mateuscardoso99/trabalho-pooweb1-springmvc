<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">
    <c:import url="./components/header.jsp" />
    <body>
        <c:import url="./components/menu.jsp" />

        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <div class="card text-white bg-danger mb-3" style="max-width: 18rem; cursor: pointer;" onclick="redirect('${pageContext.request.contextPath}/user/contato/show')">
                        <div class="card-header">Contatos</div>
                        <div class="card-body">
                            <h5 class="card-title">Contatos</h5>
                            <p class="card-text">Cadastro de contatos</p>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card text-white bg-danger mb-3" style="max-width: 18rem; cursor: pointer;" onclick="redirect('${pageContext.request.contextPath}/user/link/show')">
                        <div class="card-header">Links</div>
                        <div class="card-body">
                            <h5 class="card-title">Links</h5>
                            <p class="card-text">Cadastro de páginas web favoritas</p>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card text-white bg-danger mb-3" style="max-width: 18rem; cursor: pointer;" onclick="redirect('${pageContext.request.contextPath}/user/docs/show')">
                        <div class="card-header">Documentos</div>
                        <div class="card-body">
                            <h5 class="card-title">Documentos</h5>
                            <p class="card-text">Salvar documentos</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <c:import url="./components/footer.jsp" />
        <script>
            const redirect = (url) => window.location.href = url
        </script>
    </body>
</html>