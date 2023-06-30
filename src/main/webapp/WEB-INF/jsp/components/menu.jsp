<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <!-- <a class="navbar-brand" href="#">Navbar</a> -->
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <security:authorize access="isAuthenticated()">
            <li class="nav-item">
              <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/user">
                <security:authentication property="principal.username" />
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/user/perfil">Perfil</a>
            </li>
            <li class="nav-item">
              <a class="nav-link btn btn-dark text-light" href="${pageContext.request.contextPath}/logout">Sair</a>
            </li>
          </security:authorize>
          <security:authorize access="!isAuthenticated()">
            <li class="nav-item">
              <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/login">Login</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/register">Criar conta</a>
            </li>
          </security:authorize>
        </ul>
      </div>
    </div>
  </nav>