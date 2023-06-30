<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <c:import url="./components/header.jsp" />
    <body>
        <c:import url="./components/menu.jsp" />
        <div class="container">
            <div class="row mt-3">
                <form:form action="register" method="post" modelAttribute="user">
                    <h2 class="text-center bg-light text-dark">criar conta</h2>
        
                    <div class="mb-3">
                        <!-- <label for="nome" class="form-label">Nome</label>
                        <input type="text" class="form-control" name="nome" id="nome"> -->
                        <form:label path="nome" class="form-label">Nome</form:label>
                        <form:input type="text" path="nome" class="form-control"/>
                    </div>
                    <div class="mb-3">
                        <!-- <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" name="email" id="email"> -->
                        <form:label path="email" class="form-label">Email</form:label>
                        <form:input type="email" path="email" class="form-control"/>
                    </div>
                    <div class="mb-3">
                        <!-- <label for="password" class="form-label">Senha</label>
                        <input type="password" class="form-control" name="password" id="password"> -->
                        <form:label path="senha" class="form-label">Senha</form:label>
                        <form:input type="password" path="senha" class="form-control"/>
                    </div>
                    <button type="submit" class="btn btn-primary">salvar</button>
                </form:form>
            </div>
        </div>
        <c:import url="./components/footer.jsp" />
    </body>
</html>