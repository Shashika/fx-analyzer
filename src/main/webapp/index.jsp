<html>
<head>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
</head>
<body>
<div class="container">
    <div class="col-md-12 col-md-offset-3" style="margin-top: 2%;">
        <h4 style="font-weight: bold">FX Analyzer</h4>
    </div>

    <div class="col-md-12 col-md-offset-2">
        <div class="form-group" style="margin-left: 2%">
            <h5>Choose File to Upload in Server</h5>
        </div>
    </div>

    <div class="col-md-12 col-md-offset-2">

        <div class="col-md-4">

            <form action="upload" method="post" enctype="multipart/form-data">

                <div class="form-group">
                    <input class="form-control" type="file" name="file" />
                </div>

                <div class="form-group">
                    <input class="bg-primary" type="submit" value="upload" />
                </div>

            </form>

        </div>
    </div>
</div>
</body>
</html>
