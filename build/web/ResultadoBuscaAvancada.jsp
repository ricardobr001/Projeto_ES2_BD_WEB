<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Buscas IMDb</title>

    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Theme CSS -->
    <link href="css/theme.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

</head>

<body id="page-top" class="index">
<div id="skipnav"><a href="#maincontent">Skip to main content</a></div>

    <!-- Navigation -->
    <nav id="mainNav" class="navbar navbar-default navbar-fixed-top navbar-custom">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand" href="#page-top">Projeto Integrado</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li>
                        <a href="index.jsp">Ir para o início</a>
                    </li>
                    <li>
                        <a href="ranking.jsp">Ranking</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

    <!-- Header -->
    <header>
        <div class="container" id="maincontent" tabindex="-1">
            <div class="row">
                <div class="col-lg-12">
                    <div class="intro-text">
                        <h1 class="name">Resultados</h1>
                        <hr class="star-light">
                    </div>
                </div>
            </div>
        </div>
    </header>
    <%
        ResultadoBusca res = (ResultadoBusca)request.getAttribute("ResultadoBusca");
        String pagina = (String)request.getAttribute("pagina");
        String url = (String)request.getAttribute("url");
        
        if(res == null) {
    %>
        <section id="contact">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h2>Ooops, algo deu errado</h2>
                        <hr class="star-primary">
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2 text-center">
                        <p>Aparentemente houve um erro, por favor tente novamente ou informe um administrador do sistema.</p>
                    </div>
                </div>
            </div>
        </section>
    <%
        } else {
    %>
    <section id="resultados">
        <div class="container">
            <table class="table table-bordered table-striped table-hover table-condensed table-responsive">
                <thead>
                        <tr>
                            <th>Título</th>
                            <th>Ano</th>
                            <th>Idioma</th>
                            <th>Nome do Ator</th>
                            <th>Personagem</th>
                        </tr>
                </thead>
                
                <tbody>
                    <% 
                        for (int i = 0 ; i < res.size() ; i++){
                            out.println("<tr>" + res.returnMovie(i) + res.returnActor(i) + "</tr>");
                        }
                    %>
                </tbody>
            </table>
            <div class="text-center">
                <%
                    int numPagina, proxPagina, antPagina;
                    
                    if (pagina != null) {
                        numPagina = Integer.parseInt(pagina);
                    }
                    else {
                        numPagina = 1;
                    }
                    
                    String aux = "&pg=" + numPagina;
                    url = url.replace(aux, "");

                    out.println("<ul class=\"pager\">");

                    if (numPagina == 1 && res.size() == 30) {
                        out.println("<li class=\"page-item\"><a class=\"page-link\" href=BuscaAvancada?" + url + "&pg=2#resultados>Próximo</a></li>");
                    }
                    else if (res.size() < 30 && numPagina != 1) {
                        antPagina = numPagina-1;
                        out.println("<li class=\"page-item\"><a class=\"page-link\" href=BuscaAvancada?" + url + "&pg=" + antPagina + "#resultados>Anterior</a></li>");
                    }
                    else if (numPagina == 1 && res.size() < 30) {
                        out.println("<li></li>");
                    }
                    else {
                        proxPagina = numPagina+1;
                        antPagina = numPagina-1;
                        out.println("<li class=\"page-item\"><a class=\"page-link\" href=BuscaAvancada?" + url + "&pg=" + antPagina + "#resultados>Anterior</a></li>");
                        out.println("<li class=\"page-item\"><a class=\"page-link\" href=BuscaAvancada?" + url + "&pg=" + proxPagina + "#resultados>Próximo</a></li>");
                    }
                %>
            </div>
        </div>
    </section>
    <%
        }
    %>

    <!-- Footer -->
    <footer class="text-center">
        <div class="footer-above">
            <div class="container">
                <div class="row">
                    <div class="footer-col col-md-6">
                        <h3>Desenvolvido por:</h3>
                            <p class="small">Carlos Alberico Bezerra de Andrade</p>
                            <p class="small">Gustavo Leite Oliveira</p>
                            <p class="small">Isabela Salmeron Boschi</p>
                            <p class="small">Luciane da Silva Lopes</p>
                            <p class="small">Ricardo Mendes Leal Junior</p>
                    </div>
                    <div class="footer-col col-md-6">
                        <h3>Sobre nós</h3>
                        <p>Estudantes de Ciência da Computação na
                          <br><a href="http://www.sorocaba.ufscar.br/ufscar/">UFSCar - Sorocaba</a></p>
                    </div>
                </div>
            </div>
        </div>
    </footer>

    <!-- Scroll to Top Button (Only visible on small and extra-small screen sizes) -->
    <div class="scroll-top page-scroll hidden-sm hidden-xs hidden-lg hidden-md">
        <a class="btn btn-primary" href="#page-top">
            <i class="fa fa-chevron-up"></i>
        </a>
    </div>

    <!-- jQuery -->
    <script src="vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>

    <!-- Contact Form JavaScript -->
    <script src="js/jqBootstrapValidation.js"></script>
    <script src="js/app.js"></script>

    <!-- Theme JavaScript -->
    <script src="js/theme.min.js"></script>

</body>

</html>

