<html>
    <body>
        <h2>Edit User</h2>
        <p>${error}</p>
        <form action="/edituser" method="post">
            Firstname:<br/>
            <input type="text" name="firstname"/>
            <br/>
            Username:<br/>
            <input type="text" name="username"/>
            <br><br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>