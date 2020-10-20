<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section id="books">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>List of the Best Books</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th class="text-center">Title</th>
                                <th class="text-center">Description</th>
                                <th class="text-center">Cover</th>
                            </tr>
                        </thead>
                        <tbody>                            
                            <c:forEach var="book" items="${books}" varStatus="status">
                                <tr>
                                    <td>${book.id}</td>
                                    <td class="text-dark">${book.bookname}</td>
                                    <td>${book.description}</td>
                                    <td class="text-center"> <img src="${book.image}" height="200"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>