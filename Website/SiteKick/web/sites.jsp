<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="totalPageCount" value="${requestScope.TotalPageCount}"/>
<c:set var="siteList" value="${requestScope.SiteList}" />
<c:set var="pageIndex" value="${requestScope.PageIndex}" />

<!doctype html>
<html class="no-js" lang="zxx">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>SITE KICK</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- <link rel="manifest" href="site.webmanifest"> -->
        <!-- Place favicon.ico in the root directory -->

        <!-- CSS here -->
        <%@include file="commonCss.jsp" %>
        <link rel="stylesheet" href="css/sites.css">
        <!-- <link rel="stylesheet" href="css/responsive.css"> -->
    </head>

    <body>
        <!--[if lte IE 9]>
                <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
            <![endif]-->

        <!-- header-start -->
        <%@include file="header.jsp" %>
        <!-- header-end -->

        <!-- bradcam_area  -->
        <div class="bradcam_area">
            <div class="bradcam_shap">
                <img src="img/ilstrator/bradcam_ils.png" alt="">
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-xl-12">
                        <div class="bradcam_text text-center">
                            <h3>Sites</h3>
                            <nav class="brad_cam_lists">
                                <ul class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Sites</li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /bradcam_area  -->

        <!-- case_study_area  -->
        <div class="case_study_area white_case_study">
            <div class="patrn_1 d-none d-lg-block">
                <img src="img/pattern/patrn_1.png" alt="">
            </div>
            <div class="patrn_2 d-none d-lg-block">
                <img src="img/pattern/patrn.png" alt="">
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 col-md-6">
                        <div class="single_study text-center white_single_study">
                            <div class="thumb">
                                <a href="Case_details.html">
                                    <img src="img/case_study/1.png" alt="">
                                </a>
                            </div>
                            <div class="subheading white_subheading">
                                <h4><a href="#">Product Design</a></h4>
                                <p>UI/UX, Design</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <div class="single_study text-center white_single_study">
                            <div class="thumb">
                                <a href="Case_details.html">
                                    <img src="img/case_study/2.png" alt="">
                                </a>
                            </div>
                            <div class="subheading white_subheading">
                                <h4><a href="Case_details.html">Custom Website</a></h4>
                                <p>UI/UX, Design</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <div class="single_study text-center white_single_study">
                            <div class="thumb">
                                <a href="Case_details.html">
                                    <img src="img/case_study/3.png" alt="">
                                </a>
                            </div>
                            <div class="subheading white_subheading">
                                <h4><a href="Case_details.html">Digital Marketing</a></h4>
                                <p>UI/UX, Design</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <div class="single_study text-center white_single_study">
                            <div class="thumb">
                                <a href="Case_details.html">
                                    <img src="img/case_study/4.png" alt="">
                                </a>
                            </div>
                            <div class="subheading white_subheading">
                                <h4><a href="Case_details.html">Product Design</a></h4>
                                <p>UI/UX, Design</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <div class="single_study text-center white_single_study">
                            <div class="thumb">
                                <a href="Case_details.html">
                                    <img src="img/case_study/5.png" alt="">
                                </a>
                            </div>
                            <div class="subheading white_subheading">
                                <h4><a href="Case_details.html">Custom Website</a></h4>
                                <p>UI/UX, Design</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <div class="single_study text-center white_single_study">
                            <div class="thumb">
                                <a href="Case_details.html">
                                    <img src="img/case_study/6.png" alt="">
                                </a>
                            </div>
                            <div class="subheading white_subheading">
                                <h4><a href="Case_details.html">Digital Marketing</a></h4>
                                <p>UI/UX, Design</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xl-12">
                        <nav class="blog-pagination justify-content-center d-flex">
                            <ul class="pagination">
                                <c:if test="${not empty totalPageCount}">
                                    <li class="page-item">
                                        <c:set var="lastIndex" value="${pageIndex == 1 ? totalPageCount : pageIndex - 1}"/>
                                        <a href="/SiteKick/SiteProcessController?pageIndex=${lastIndex}" class="page-link" aria-label="Previous">
                                            <i class="ti-angle-left"></i>
                                        </a>
                                    </li>
                                    <c:forEach begin="1" end="${totalPageCount}" varStatus="index">
                                        <c:if test="${pageIndex == index.count}">
                                            <li class="page-item active">
                                                <a href="/SiteKick/SiteProcessController?pageIndex=${index.count}" class="page-link">${index.count}</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${pageIndex ne index.count}">
                                            <li class="page-item">
                                                <a href="/SiteKick/SiteProcessController?pageIndex=${index.count}" class="page-link">${index.count}</a>
                                            </li>
                                        </c:if>
                                    </c:forEach> 
                                    <li class="page-item">
                                        <c:set var="nextIndex" value="${pageIndex == totalPageCount ? 1 : pageIndex + 1}"/>
                                        <a href="/SiteKick/SiteProcessController?pageIndex=${nextIndex}" class="page-link" aria-label="Next">
                                            <i class="ti-angle-right"></i>
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
        <!--/ case_study_area  -->

        <!-- testimonial_area  -->
        <div class="testimonial_area ">
            <div class="container">
                <div class="row">
                    <div class="col-xl-12">
                        <div class="testmonial_active owl-carousel">
                            <div class="single_carousel">
                                <div class="single_testmonial text-center">
                                    <div class="quote">
                                        <img src="img/testmonial/quote.svg" alt="">
                                    </div>
                                    <p>Donec imperdiet congue orci consequat mattis. Donec rutrum porttitor <br> 
                                        sollicitudin. Pellentesque id dolor tempor sapien feugiat ultrices nec sed neque.  <br>
                                        Fusce ac mattis nulla. Morbi eget ornare dui. </p>
                                    <div class="testmonial_author">
                                        <div class="thumb">
                                            <img src="img/testmonial/thumb.png" alt="">
                                        </div>
                                        <h3>Robert Thomson</h3>
                                        <span>Business Owner</span>
                                    </div>
                                </div>
                            </div>
                            <div class="single_carousel">
                                <div class="single_testmonial text-center">
                                    <div class="quote">
                                        <img src="img/testmonial/quote.svg" alt="">
                                    </div>
                                    <p>Donec imperdiet congue orci consequat mattis. Donec rutrum porttitor <br> 
                                        sollicitudin. Pellentesque id dolor tempor sapien feugiat ultrices nec sed neque.  <br>
                                        Fusce ac mattis nulla. Morbi eget ornare dui. </p>
                                    <div class="testmonial_author">
                                        <div class="thumb">
                                            <img src="img/testmonial/thumb.png" alt="">
                                        </div>
                                        <h3>Robert Thomson</h3>
                                        <span>Business Owner</span>
                                    </div>
                                </div>
                            </div>
                            <div class="single_carousel">
                                <div class="single_testmonial text-center">
                                    <div class="quote">
                                        <img src="img/testmonial/quote.svg" alt="">
                                    </div>
                                    <p>Donec imperdiet congue orci consequat mattis. Donec rutrum porttitor <br> 
                                        sollicitudin. Pellentesque id dolor tempor sapien feugiat ultrices nec sed neque.  <br>
                                        Fusce ac mattis nulla. Morbi eget ornare dui. </p>
                                    <div class="testmonial_author">
                                        <div class="thumb">
                                            <img src="img/testmonial/thumb.png" alt="">
                                        </div>
                                        <h3>Robert Thomson</h3>
                                        <span>Business Owner</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /testimonial_area  -->

        <%@include file="footer.jsp" %>

        <!-- JS here -->
        <script src="js/vendor/modernizr-3.5.0.min.js"></script>
        <script src="js/vendor/jquery-1.12.4.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/isotope.pkgd.min.js"></script>
        <script src="js/ajax-form.js"></script>
        <script src="js/waypoints.min.js"></script>
        <script src="js/jquery.counterup.min.js"></script>
        <script src="js/imagesloaded.pkgd.min.js"></script>
        <script src="js/scrollIt.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/wow.min.js"></script>
        <script src="js/nice-select.min.js"></script>
        <script src="js/jquery.slicknav.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/plugins.js"></script>
        <script src="js/gijgo.min.js"></script>

        <!--contact js-->
        <script src="js/contact.js"></script>
        <script src="js/jquery.ajaxchimp.min.js"></script>
        <script src="js/jquery.form.js"></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="js/mail-script.js"></script>

        <script src="js/main.js"></script>
    </body>

</html>