<template>

<div>
    <vs-card>
    <template #title>
      <h3>헬로우</h3>
    </template>
    <template #img>
      <img src="https://cdn.fastly.picmonkey.com/contentful/h6goo9gw1hh6/2sNZtFAWOdP1lmQ33VwRN3/24e953b920a9cd0ff2e1d587742a2472/1-intro-photo-final.jpg?w=800&q=70" alt="">
    </template>
    <template #text>
      <p>
        잋츠미
      </p>
    <div class="profile_buttons">
        <vs-button circle relief >
            <i class='bx bx-user'></i>
        </vs-button>
        <vs-button relief>
             <i class='bx bx-stats'></i>
        </vs-button>
        <vs-button relief>
            <i class='bx bx-badge'></i>
        </vs-button>
    </div>
    </template>
    <template #interactions>
    </template>
  </vs-card>

</div>

</template>

<script>
export default {
    data:()=>({

    })
}
</script>

<style>
.profile {
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.profile_buttons {
    display: flex;
}
</style>