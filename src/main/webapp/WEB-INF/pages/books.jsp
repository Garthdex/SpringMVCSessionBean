<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/content/js/ajax.js"/>" type="text/javascript"></script>
    <title>Books Page</title>
</head>
<body>
<a href="../../index.jsp">Back to main menu</a>

<br/>
<br/>

<h1>Add a book by ajax</h1>

<div class = "showAjax"></div>
<div class = "addBookFromResponse"></div>
<c:url var="addActionAjax" value="/books/add/ajax"/>
<form:form id="newBookForm" action="${addActionAjax}" commandName="book" modelAttribute="book">
    <table>
        <tbody>
        <tr>
            <td>
                <form:label path="bookTitle">
                    <spring:message text="Title"/>
                </form:label>
            </td>
            <td><form:input path="bookTitle"/></td>
        </tr>
        <tr>
            <td>
                <form:label path="bookAuthor">
                    <spring:message text="Author"/>
                </form:label>
            </td>
            <td><form:input path="bookAuthor"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty book.bookTitle}">
                    <input type="submit"
                           value="<spring:message text="Edit Book Ajax"/>"/>
                </c:if>
                <c:if test="${empty book.bookTitle}">
                    <input type="submit"
                           value="<spring:message text="Add Book Ajax"/>"/>
                </c:if>
            </td>
        </tr>
        </tbody>
    </table>
</form:form>

<h1>Booklist from ajax</h1>
<div class="fromAjax">

</div>

___________________________________________________________________________________________________________
<c:url var="addAction" value="/books/add"/>
<h1>Add a book</h1>
<form:form action="${addAction}" commandName="book" modelAttribute="book">
    <table>
        <c:if test="${!empty book.bookTitle}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="id"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="bookTitle">
                    <spring:message text="Title"/>
                </form:label>
            </td>
            <td>
                <form:input path="bookTitle"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="bookAuthor">
                    <spring:message text="Author"/>
                </form:label>
            </td>
            <td>
                <form:input path="bookAuthor"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty book.bookTitle}">
                    <input type="submit"
                           value="<spring:message text="Edit Book"/>"/>
                </c:if>
                <c:if test="${empty book.bookTitle}">
                    <input type="submit"
                           value="<spring:message text="Add Book"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>

<h1>Booklist</h1>

<c:if test="${!empty listBooks}">
    <table>
        <tr>
            <th width="80">ID</th>
            <th width="120">Title</th>
            <th width="120">Author</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listBooks}" var="book">
            <tr>
                <td>${book.id}</td>
                <td>${book.bookTitle}</td>
                <td>${book.bookAuthor}</td>
                <td><a href="<c:url value='books/edit/${book.id}'/>">Edit</a></td>
                <td><a href="<c:url value='books/remove/${book.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
