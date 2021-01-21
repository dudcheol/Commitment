<template>
  <div class="container">
    <nav>Navbar</nav>
    <main>Main</main>
    <div id="sidebar">Sidebar</div>
    <div id="content1">Content1</div>
    <div id="content2">Content2</div>
    <div id="content3">Content3</div>
    <footer>Footer</footer>
  </div>
</template>

<script>
export default {

}
</script>

<style>
.container {
    display: grid;
    height: 100vh;
    grid-template-columns: 1fr 1fr 1fr 1fr;
    grid-template-rows: 0.2fr 1.5fr 1.2fr 0.8fr;
    grid-template-areas: "nav nav nav nav"
    "sidebar main main main"
    "sidebar content1 content2 content3"
    "sidebar footer footer footer footer";
    grid-gap: 0.2rem;
}

nav { 
    background-color: red;
    grid-area: nav;

}

main {
    background-color: yellow;
    grid-area: main;
}

#sidebar {
    background-color: orange;
    grid-area: sidebar;
}

#content1 {
    background-color: green;
    grid-area: content1;
}

#content2 {
    background-color: blue;
    grid-area: content2;
}

#content3 {
    background-color: darkblue;
    grid-area: content3;
}

footer {
    background-color: purple;
    grid-area: footer;
}

@media only screen and (max-width: 550px) {
    .container {
        grid-template-columns: 1fr;
        grid-template-rows: 0.4fr 0.4fr 2.2fr 1.2fr 1.2fr 1fr;
        grid-template-areas:
        "nav"
        "sidebar"
        "main"
        "content1"
        "content2"
        "content3"
        "footer";
    }
}

</style>