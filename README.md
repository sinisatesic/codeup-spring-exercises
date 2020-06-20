Greetings!

 This is a brief explanation of this repo and projects within - it is titled codeup-spring-exercises
 as Codeup is the bootcamp (full-stack web development) I underwent to learn Spring (along with other
 languages/technologies). This particular project is the Fortuna Spring Blog. Fortuna is the name of
 my Codeup cohort, and this application is intended to serve as a site for CRUD-enabled posts built with
 Spring and Thymeleaf via MVC design. 
 
 The consistent navbar you see with available options is a partial - below is its code:
 
 ```
<nav th:fragment="navbar" class="navbar navbar-default">
    <!-- awesome navbar goes here -->
    <ul class="nav navbar-nav">
        <li class="nav-item"><a href="/home">Home</a> </li>
        <li class="nav-item"><a href="/hello/Fortuna">Hello</a></li>
        <li class="nav-item"><a href="/roll-dice">Dice Game</a></li>
        <li class="nav-item"><a href="/posts">Posts</a></li>
        <li class="nav-item"><a href="/posts/create">Create Post</a></li>
        <li class="nav-item">
        <li class="nav-item">
            <form th:action="@{/login}" th:method="post">
                <button class="nav-link" style="margin-top: 2px">Login</button>
            </form>
        </li>
        <li class="nav-item">
            <form th:action="@{/logout}" th:method="post">
                <button class="nav-link" style="margin-top: 2px">Logout</button>
            </form>
        </li>
        <li class="nav-item">
            <a href="/register">
                <button class="nav-link register-button">Register</button>
            </a>
        </li>
    </ul>
</nav>
```
    This was my first Spring project, and was thrilling to create. This project absolutely made me fall
    in love with Jpa; it is a breathe of fresh air regarding MVC design, without a doubt. 
