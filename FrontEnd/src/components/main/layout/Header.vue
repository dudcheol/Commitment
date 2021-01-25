<template>
<v-container class="container">
    <nav>Navbar</nav>
    <main>Main</main>
    <v-card id="sidebar">
    <v-list dense class="yellow">
      <v-subheader>REPORTS</v-subheader>
      <v-list-item-group
        v-model="selectedItem"
      >
        <v-list-item
          v-for="(item, i) in items"
          :key="i"
        >
          <v-list-item-icon>
            <v-icon v-text="item.icon"></v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title v-text="item.text"></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>
    </v-list>
     </v-card>
     <v-card id="content1" >
         <v-img
         class="mx-auto"
         justify="center"
         max-width="200"
        :aspect-ratio="4/3"
         src="https://cdn.vuetifyjs.com/images/cards/docks.jpg">
         <v-card-title>Title</v-card-title>
        </v-img>
        <v-card-subtitle>
          Hello
        </v-card-subtitle>
        <v-card-text>
        <div>It's me</div>
        <div>I've been wondering</div>
        </v-card-text>
     </v-card>
    <div id="content2">Content2</div>
    <div id="content3">Content3</div>
    <footer>Footer</footer>
</v-container>
</template>

<script>
export default {
     data: () => ({
      selectedItem: 1,
      items: [
        { text: 'Real-Time', icon: 'mdi-clock' },
        { text: 'Audience', icon: 'mdi-account' },
        { text: 'Conversions', icon: 'mdi-flag' },
      ],
      messages: [
        {
          from: 'You',
          message: `Sure, I'll see you later.`,
          time: '10:42am',
          color: 'deep-purple lighten-1',
        },
        {
          from: 'John Doe',
          message: 'Yeah, sure. Does 1:00pm work?',
          time: '10:37am',
          color: 'green',
        },
        {
          from: 'You',
          message: 'Did you still want to grab lunch today?',
          time: '9:47am',
          color: 'deep-purple lighten-1',
        },
      ],
    }),

}
</script>

<style>
:root {
  --main-radius: 5px;
  --main-padding: 5px;
}

body {
  font-family: "Inter", sans-serif;
}

.v-card {
    font-size: 12px;
}

.container {
  display: grid;
  height: 100vh;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-rows: 0.2fr 1.5fr 1.2fr 0.8fr;
  grid-template-areas:
    "nav nav nav nav"
    "sidebar main main main"
    "sidebar content1 content2 content3"
    "sidebar footer footer footer";
  grid-gap: 0.2rem;
  font-weight: 800;
  text-transform: uppercase;
  font-size: 12px;
  color:black;
  text-align: center;
}

nav {
  background: red;
  grid-area: nav;
  border-radius: var(--main-radius);
  padding-top: var(--main-padding);
}

main {
  background: orange;
  grid-area: main;
  border-radius: var(--main-radius);
  padding-top: var(--main-padding);
  /* flex-basis: 70% */
}

#sidebar{
  background: yellow;
  grid-area: sidebar;
  border-radius: var(--main-radius);
  padding-top: var(--main-padding);
  /* flex-basis: 30% */
}

#content1 {
  /* background: green; */
  grid-area: content1;
  border-radius: var(--main-radius);
  padding-top: var(--main-padding);
  justify-content: center;  

}

#content2 {
  background: blue;
  grid-area: content2;
  border-radius: var(--main-radius);
  padding-top: var(--main-padding);
}

#content3 {
  background: darkblue;
  grid-area: content3;
  border-radius: var(--main-radius);
  padding-top: var(--main-padding);
}

footer {
  background: violet;
  grid-area: footer;
  border-radius: var(--main-radius);
  padding-top: var(--main-padding);
}

a {
  text-align: center;
  display: block;
  font-family: inherit;
  text-decoration: none;
  font-weight: bold;
  margin: 1rem;
}

@media only screen and (max-width: 550px) {
  .container {
    grid-template-columns: 1fr;
    grid-template-rows: 0.4fr 2.2fr 1.2fr 1.2fr 1.2fr 1fr;
    grid-template-areas:
      "nav"
      "main"
      "content1"
      "content2"
      "content3"
      "footer";
  }
  
  #sidebar {
      display: none;
      background: red;
  }

}
</style>