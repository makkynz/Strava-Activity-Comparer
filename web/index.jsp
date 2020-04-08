<%@ page import="stravacustom.utils.ConfigHelper" %><%--
  Created by IntelliJ IDEA.
  User: antho
  Date: 5/01/2020
  Time: 4:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Global site tag (gtag.js) - Google Analytics -->
    <script async src="https://www.googletagmanager.com/gtag/js?id=UA-51742975-3"></script>
    <script>
        window.dataLayer = window.dataLayer || [];
        function gtag(){dataLayer.push(arguments);}
        gtag('js', new Date());

        gtag('config', 'UA-51742975-3');
    </script>


    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href='https://d3nn82uaxijpm6.cloudfront.net/apple-touch-icon-180x180.png?v=dLlWydWlG8' rel='apple-touch-icon'
          sizes='180x180'>
    <link href='https://d3nn82uaxijpm6.cloudfront.net/apple-touch-icon-152x152.png?v=dLlWydWlG8' rel='apple-touch-icon'
          sizes='152x152'>
    <link href='https://d3nn82uaxijpm6.cloudfront.net/apple-touch-icon-144x144.png?v=dLlWydWlG8' rel='apple-touch-icon'
          sizes='144x144'>
    <link href='https://d3nn82uaxijpm6.cloudfront.net/apple-touch-icon-120x120.png?v=dLlWydWlG8' rel='apple-touch-icon'
          sizes='120x120'>
    <link href='https://d3nn82uaxijpm6.cloudfront.net/apple-touch-icon-114x114.png?v=dLlWydWlG8' rel='apple-touch-icon'
          sizes='114x114'>
    <link href='https://d3nn82uaxijpm6.cloudfront.net/apple-touch-icon-76x76.png?v=dLlWydWlG8' rel='apple-touch-icon'
          sizes='76x76'>
    <link href='https://d3nn82uaxijpm6.cloudfront.net/apple-touch-icon-72x72.png?v=dLlWydWlG8' rel='apple-touch-icon'
          sizes='72x72'>
    <link href='https://d3nn82uaxijpm6.cloudfront.net/apple-touch-icon-60x60.png?v=dLlWydWlG8' rel='apple-touch-icon'
          sizes='60x60'>
    <link href='https://d3nn82uaxijpm6.cloudfront.net/apple-touch-icon-57x57.png?v=dLlWydWlG8' rel='apple-touch-icon'
          sizes='57x57'>
    <link href='https://d3nn82uaxijpm6.cloudfront.net/favicon-32x32.png?v=dLlWydWlG8' rel='icon' sizes='32x32'
          type='image/png'>
    <link href='https://d3nn82uaxijpm6.cloudfront.net/icon-strava-chrome-192.png?v=dLlWydWlG8' rel='icon'
          sizes='192x192' type='image/png'>
    <link href='https://d3nn82uaxijpm6.cloudfront.net/favicon-96x96.png?v=dLlWydWlG8' rel='icon' sizes='96x96'
          type='image/png'>
    <link href='https://d3nn82uaxijpm6.cloudfront.net/favicon-16x16.png?v=dLlWydWlG8' rel='icon' sizes='16x16'
          type='image/png'>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>BF Run Club</title>
    <style>

        .card-text b {
            font-weight: 200;
        }

        .athletes, .activities {
            max-width: 1000px;
        }

        .activities {
            margin-top: 20px;
        }

        .alert-danger {
            display: none;
        }

    </style>
</head>
<body style="margin:20px">
<h1>BF Run Club Lock Down Challenge</h1>
<p>
    Strava activity since Covid 19 lock down in NZ (26/03/2020)
    <img src="api_logo_pwrdBy_strava_horiz_light.svg" style="width:110px;"/>


</p>
<div class="alert alert-danger" role="alert">
    Sorry, we can't get your activities unless you authorise to connect to Strava.
</div>
<p>
    <button class="btn btn-primary btnGetActivities" style="background-color: #fc5c19;
    border-color: #fc5c19;">Get My Activities
    </button>
</p>
<div class="athletes card-deck"></div>
<div class="activities table-responsive">


</div>

<script>

    let stravaClientId = "<%= ConfigHelper.get("strava.clientId") %>"
</script>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.1/moment.min.js"></script>
<script src="main.js"></script>
</body>
</html>



