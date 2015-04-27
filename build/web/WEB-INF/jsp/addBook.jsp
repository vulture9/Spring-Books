<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Damian Grzegorzewski Spring Project</title>
    </head>

    <body>
        <!-- Fixed navbar -->
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a style="padding-top:3px" class="navbar-brand" href="index"><img src="book.png"></a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li ><a href="index"><i class="fa fa-home"></i> Home</a></li>
                    <li ><a href="viewAll"> <i class="fa fa-eye"></i> View All books</a></li> 
                    <li class="active"><a href="addBook"><i class="fa fa-plus-circle"></i> Add book</a></li> 
                    <li><a href="about"><i class="fa fa-user"></i> About</a></li> 
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </nav>

    <div class="container theme-showcase" role="main">

        <!-- Main jumbotron for a primary marketing message or call to action -->
        <div class="jumbotron">
            <h1><i class="fa fa-book"></i> Add a book to the collection</h1>
            <p>add book</p>

            <form:form method="POST" action="saveBook.html">
                <div class="form-group">
                    <form:label for="exampleInputEmail1" path="title">Book title:</form:label>
                    <form:input path="title" type="text" class="form-control" id="book_title" placeholder="Enter title"/>
                </div>
                <div class="form-group">
                    <form:label path="author" for="exampleInputPassword1">Author:</form:label>
                    <form:input path="author" type="text" class="form-control" id="book_author" placeholder="Enter Author"/>
                </div>
                <div class="form-group">
                    <form:label path="isbn" for="exampleInputPassword1">ISBN: </form:label>
                    <form:input path="isbn" type="text" class="form-control" id="book_isbn" placeholder="Enter isbn number"/>
                </div>
                
                <button value="submit" type="submit" class="btn btn-default"><i class="fa fa-plus"></i> Add book</button>
            </form:form>
        </div>  
    </div> 
</body>
</html>
