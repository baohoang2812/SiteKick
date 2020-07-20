<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${requestScope.SiteDetail}" var="siteDetail" />
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
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/themify-icons.css">
        <link rel="stylesheet" href="css/nice-select.css">
        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="css/gijgo.css">
        <link rel="stylesheet" href="css/animate.min.css">
        <link rel="stylesheet" href="css/slick.css">
        <link rel="stylesheet" href="css/slicknav.css">
        <link rel="stylesheet" href="css/style.css">
        <!-- <link rel="stylesheet" href="css/responsive.css"> -->
    </head>

    <body>
        <!--[if lte IE 9]>
                <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
            <![endif]-->

        <!-- header-start -->
        <%@include file="header.jsp" %>
        <!-- header-end -->
        <c:if test="${not empty siteDetail}">
            <!-- site_title  -->
            <div class="bradcam_area">
                <div class="bradcam_shap">
                    <img src="img/ilstrator/bradcam_ils.png" alt="">
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col-xl-12">
                            <div class="bradcam_text ">
                                <h3>${siteDetail.Url}</h3>
                                <nav class="brad_cam_lists">
                                    <ul class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="index.jsp.html">Mobile app</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">Design</li>
                                    </ul>
                                </nav>
                                <div class="row">
                                    <div class="col-md-6">
                                        <h5><i class="fa fa-globe"></i><strong> Country: </strong><span>${siteDetail.country}</span></h5>
                                    </div>
                                    <div class="col-md-6">
                                        <h5><i class="fa fa-th-list"></i><strong> Category: </strong><span>${siteDetail.categoryId.name}</span></h5>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <h5><i class="fa fa-trophy"></i><strong> Global Rank: </strong><span>${siteDetail.globalRank}</span></h5>

                                    </div>
                                    <div class="col-md-6">
                                        <h5><i class="fa fa-star"></i><strong> Country Rank: </strong><span>${siteDetail.countryRank}</span></h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /site_title  -->
            <!-- site_detail_area  -->
            <div class="case_details_area">
                <div class="container">
                    <div class="border_bottom">
                        <div class="row ">
                            <div class="col-xl-12">
                                <div class="case_thumb">
                                    <img src="img/case_study/case_banner.png" alt="">
                                </div>
                            </div>
                            <div class="col-xl-9">
                                <div class="details_main_wrap">
                                    <!--technologyGroup-->
                                    <p class="details_info">
                                        “Donec imperdiet congue orci consequat mattis. Donec rutrum porttitor
                                        sollicitudin. Pellentesque id dolor tempor sapien feugiat ultrices nec sed neque.
                                        Fusce ac mattis nulla. Morbi eget ornare dui.
                                    </p>
                                    <!--technology-->
                                    <c:forEach var="tech" items="siteDetail.technologyList" varStatus="counter">
                                        <div class="single_details">
                                            <!--tech Name-->
                                            <span>${tech.name}</span>
                                            <!--tech description-->
                                            <p>${tech.description}</p>
                                            <!--/tech description-->
                                        </div>
                                    </c:forEach>
                                    <!--/technology-->
                                    <!--/technologyGroup-->


                                    <div class="single_details">
                                        <span>Solution</span>
                                        <p>Esteem spirit temper too say adieus who direct esteem. It esteems luckily or picture
                                            placing drawing. Apartments frequently or motionless on reasonable sed do eiusmod
                                            tempor inciunt ut labore et dolore magna liqua.abore et dolore incididunt ut labore
                                            et dolore magna liqua abore et dolore</p>
                                    </div>
                                    <div class="single_details">
                                        <span>Result</span>
                                        <p>Esteem spirit temper too say adieus who direct esteem. It esteems luckily or picture
                                            placing drawing. Apartments frequently or motionless on reasonable sed do eiusmod
                                            tempor inciunt ut labore et dolore magna liqua.abore et dolore incididunt ut labore
                                            et dolore magna liqua abore et dolore</p>
                                    </div>
                                    <div class="single_details mb-60">
                                        <ul>
                                            <li>
                                                <a href="#"> <i class="fa fa-facebook"></i> Facebook </a>
                                            </li>
                                            <li>
                                                <a href="#"> <i class="fa fa-twitter"></i> Twitter </a>
                                            </li>
                                            <li>
                                                <a href="#"> <i class="fa fa-pinterest-p"></i> Pinterest </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /site_detail_area  -->
        </c:if>

        <!-- Related Site  -->
        <div class="case_study_area white_case_study study_page">
            <div class="patrn_1 d-none d-lg-block">
                <img src="img/pattern/patrn_1.png" alt="">
            </div>
            <div class="patrn_2 d-none d-lg-block">
                <img src="img/pattern/patrn.png" alt="">
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-xl-12">
                        <div class="details_title">
                            <h3>Related Case</h3>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-4 col-md-6">
                        <div class="single_study text-center white_single_study">
                            <div class="thumb">
                                <a href="#">
                                    <img src="img/case_study/4.png" alt="">
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
                                <a href="#">
                                    <img src="img/case_study/5.png" alt="">
                                </a>
                            </div>
                            <div class="subheading white_subheading">
                                <h4><a href="#">Custom Website</a></h4>
                                <p>UI/UX, Design</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <div class="single_study text-center white_single_study">
                            <div class="thumb">
                                <a href="#">
                                    <img src="img/case_study/6.png" alt="">
                                </a>
                            </div>
                            <div class="subheading white_subheading">
                                <h4><a href="#">Digital Marketing</a></h4>
                                <p>UI/UX, Design</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--/ Related Site  -->
        <%@include  file="footer.jsp" %>

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