<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="pt-br">
    <c:import url="../components/header.jsp" />
    <body>
        <c:import url="../components/menu.jsp" />

        <div class="container">
            <!-- <c:if test="${not empty sessionScope.success}">
              <div class="row mb-3">
                <div class="alert alert-success" role="alert">
                  ${sessionScope.success}
                </div>
                <c:set var="success" scope="session" value=""></c:set>
              </div>
            </c:if>

            <c:if test="${not empty sessionScope.error}">
              <div class="row mb-3">
                  <div class="alert alert-danger" role="alert">
                      ${sessionScope.error}
                  </div>
                  <c:set var="error" scope="session" value=""></c:set>
              </div>
            </c:if>

            <c:if test="${not empty sessionScope.validationErrors}">

              <c:if test="${not empty sessionScope.validationErrors.id}">
                <div class="row mb-3">
                  <c:forEach items="${sessionScope.validationErrors.id}" var="error">
                    <div class="alert alert-danger" role="alert">${error}</div>
                  </c:forEach>
                </div>
              </c:if>

              <c:if test="${not empty sessionScope.validationErrors.nome}">
                <div class="row mb-3">
                  <c:forEach items="${sessionScope.validationErrors.nome}" var="error">
                    <div class="alert alert-danger" role="alert">${error}</div>
                  </c:forEach>
                </div>
              </c:if>

              <c:if test="${not empty sessionScope.validationErrors.telefone}">
                <div class="row mb-3">
                  <c:forEach items="${sessionScope.validationErrors.telefone}" var="error">
                    <div class="alert alert-danger" role="alert">${error}</div>
                  </c:forEach>
                </div>
              </c:if>

            </c:if>

            <c:set var="validationErrors" scope="session" value=""></c:set> -->

            <div class="row m-3">
              <div class="col text-center">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/user/contato/create">novo contato</a>
              </div>
            </div>

            <div class="row">
              <c:choose>
                <c:when test="${contatos.size() > 0}">
                  <c:forEach items="${contatos}" var="contato">
                    <div class="col-md-4 mb-3">
                      <div class="card m-auto" style="width: 18rem;">
                        <c:choose>
                          <c:when test="${not empty contato.foto}">
                            <img src="${pageContext.request.contextPath}/fotos_contato?name=${contato.foto}" height="190px" alt="${contato.nome}">
                          </c:when>
                          <c:otherwise>
                            <img src="https://placehold.co/600x400/png" alt="${contato.nome}">
                          </c:otherwise>
                        </c:choose>                    
                        <div class="card-body">
                          <h5 class="card-title">${contato.nome}</h5>
                          <span class="badge text-light bg-primary">${contato.telefone}</span>
                        </div>
                        <div class="card-footer text-muted">
                          <button type="button" class="btn btn-sm btn-warning" onclick="editar('${contato.id}','${contato.nome}','${contato.telefone}')">Editar</button>
                          <button type="button" class="btn btn-sm btn-danger" onclick="apagar('${contato.id}')">Apagar</button>
                        </div>
                      </div>
                    </div>
                  </c:forEach>
                </c:when>
                <c:otherwise>
                  <p class="text-danger fw-bold text-center">Nenhum contato cadastrado</p>
                </c:otherwise>
              </c:choose>
              
            </div>

            <div class="modal fade" id="modalEditar" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Editar contato</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    <form:form action="${pageContext.request.contextPath}/user/contato/update" modelAttribute="contato" method="post">
                      <input type="hidden" name="id" id="idContatoEditar">
                      <div class="mb-3">
                        <form:label path="nome" class="form-label">Nome:</form:label>
                        <form:input type="text" path="nome" class="form-control" id="nome" placeholder="nome"/>
                      </div>
                      <div class="mb-3">
                        <form:label path="telefone" class="form-label">Telefone</form:label>
                        <form:input type="tel" path="telefone" class="form-control"/>
                      </div>
                      <div class="mb-3">
                          <button type="submit" name="action" class="btn btn-success" value="editar">Salvar contato</button>
                      </div>
                    </form:form>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                  </div>
                </div>
              </div>
            </div>

            <div class="modal fade" id="modalApagar" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Apagar contato</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    <div class="row">
                      <p>deseja apagar este contato?</p>
                    </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                    <form action="${pageContext.request.contextPath}/user/contato/apagar" method="post">
                      <input type="hidden" name="idContato" id="idContatoApagar">
                      <button type="submit" class="btn btn-danger" name="action" value="apagar">Apagar contato</button>
                    </form>
                  </div>
                </div>
              </div>
            </div>            
        </div>

        <c:import url="../components/footer.jsp" />
    </body>
</html>

<script type="text/javascript">
    function editar(id,nome,telefone) {
        var myModal = new bootstrap.Modal(document.getElementById('modalEditar'), {});
        document.getElementById("idContatoEditar").value = id;
        document.getElementById("nome").value = nome;
        document.getElementById("telefone").value = telefone;
        myModal.show()
    }

    function apagar(id) {
        var myModal = new bootstrap.Modal(document.getElementById('modalApagar'), {});
        document.getElementById("idContatoApagar").value = id;
        myModal.show()
    }
</script>