<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="pt-br">
    <c:import url="../components/header.jsp" />
    <body>
        <c:import url="../components/menu.jsp" />

        <div class="container">
            <c:if test="${not empty errors}">
              <div class="row mb-3">
                <div class="alert alert-danger" role="alert">
                  <c:forEach items="${errors}" var="e">
                    <span class="d-block">${e.defaultMessage}</span>
                  </c:forEach>
                </div>
              </div>
            </c:if>

            <!--
            <c:if test="${not empty sessionScope.error}">
              <div class="row mb-3">
                  <div class="alert alert-danger" role="alert">
                      ${sessionScope.error}
                  </div>
                  <c:set var="error" scope="session" value=""></c:set>
              </div>
            </c:if> -->

            <!-- <c:if test="${not empty sessionScope.validationErrors}">
              <c:if test="${not empty sessionScope.validationErrors.arquivo}">
                <div class="row mb-3">
                  <c:forEach items="${sessionScope.validationErrors.arquivo}" var="error">
                    <div class="alert alert-danger" role="alert">${error}</div>
                  </c:forEach>
                </div>
              </c:if>
            </c:if>

            <c:set var="validationErrors" scope="session" value=""></c:set> -->

            <div class="row m-3">
              <div class="col text-center">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalAddArquivo">
                    novo arquivo
                </button>
              </div>
            </div>

            <div class="row">
                <c:choose>
                  <c:when test="${documentos.size() > 0}">
                    <c:forEach items="${documentos}" var="doc">
                      <div class="col-md-6 text-left mt-5 mb-5">
                        <iframe loading="lazy" title="preview" src="${pageContext.request.contextPath}/user/docs/arquivo/${doc.arquivo}" width="100%" height="250"></iframe>
                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/user/docs/apagar/${doc.id}">Apagar</a>
                        <a class="btn btn-success" href="${pageContext.request.contextPath}/user/docs/download-arquivo/${doc.arquivo}">Baixar</a>
                      </div>
                    </c:forEach>
                  </c:when>
                  <c:otherwise>
                    <p class="text-danger fw-bold text-center">Nenhum documento cadastrado</p>
                  </c:otherwise>
                </c:choose>
            </div>

            <div class="modal fade" id="modalAddArquivo" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Upload arquivo</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    <form:form action="${pageContext.request.contextPath}/user/docs/create" modelAttribute="documento" method="post" enctype="multipart/form-data">
                      <div class="mb-3">
                        <form:label path="arquivo" class="form-label">Foto</form:label>
                        <form:input type="file" path="arquivo" class="form-control"/>
                      </div>
                      <div class="mb-3">
                          <button type="submit" class="btn btn-success">Salvar</button>
                      </div>
                    </form:form>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                  </div>
                </div>
              </div>
            </div>
        </div>

        <c:import url="../components/footer.jsp" />
    </body>
</html>