<%@attribute name="title" required="true" type="java.lang.String" %>

<!DOCTYPE html>

<html lang="en" ng-app="pizzaApp">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>${title}</title>
        <link rel="stylesheet" media="screen" href='/stylesheets/foundation/foundation.min.css'/>
        <link rel="stylesheet" media="screen" href='/stylesheets/main.css'/>
        <link rel="shortcut icon" type="imagepng" href='/images/favicon.png'/>
        <script src='/javascripts/foundation/vendor/modernizr.js' type="text/javascript"></script>
    </head>
    <body>
        <jsp:doBody />

        <script src='/javascripts/foundation/vendor/jquery.js' type="text/javascript"></script>
        <script src='/javascripts/foundation/foundation.min.js' type="text/javascript"></script>
        <script src='/javascripts/angular.js' type="text/javascript"></script>
        <script src='/javascripts/app.js' type="text/javascript"></script>
</body>
</html>