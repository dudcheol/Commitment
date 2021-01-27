<template>
<div class="grid-container-article">
    <div class="picture">
        <v-img max-height="300"
        src="https://picsum.photos/200"
        >
        </v-img>
    </div>
    <div class="avatar mt-4">
        <vs-avatar circle>
        <img src="https://picsum.photos/200" alt="">
        </vs-avatar>
    </div>
    <div class="username ml-4 mt-4">Horge Watson</div>
    <div class="created_at ml-4">2hours ago</div>
    <div class="buttons">
        <vs-button icon color="warn">
        <i class='bx bxs-bell-ring' ></i>
        </vs-button>

      <vs-button
        icon
        color="#7d33ff"
        relief

      >
        <i class='bx bxs-paper-plane' ></i>
      </vs-button>

      <vs-button
        icon
        color="rgb(59,222,200)"
        gradient
        :active="active == 6"
        @click="active = 6"
      >
        <i class='bx bxs-purchase-tag' ></i>
      </vs-button>
    </div>
    <div class="article ml-4">열기구타고 두둥실</div>
    <div class="hashtag ml-3">
        <v-chip>Default</v-chip>
        <v-chip>Default</v-chip>
        <v-chip>Default</v-chip>
    </div>
    <div class="follow_btn ">      
      <vs-button>
        Follow
      </vs-button>
    </div>
</div>
</template>

<script>
export default {

}
</script>

<style>
.grid-container-article {
  display: grid;
  grid-template-columns: min-content 1fr 1fr 1fr;
  grid-template-rows: 300px 1fr 1fr 1fr 1fr 1fr;
  gap: 5px 0px;
  grid-template-areas:
    "picture picture picture picture"
    "avatar username . follow_btn"
    "avatar created_at . follow_btn"
    "buttons buttons buttons buttons"
    "article article article article"
    "hashtag hashtag hashtag hashtag";
}

.picture { grid-area: picture;}

.avatar { grid-area: avatar; 
align-self: center;
justify-self: start;

}

.username { grid-area: username; 
align-self: center;
justify-self: start;
}

.created_at { grid-area: created_at; 
align-self: center;
justify-self: start;
}

.buttons { 
grid-area: buttons; 
display: flex;
justify-content: flex-end;

}

.article { grid-area: article; }

.hashtag { grid-area: hashtag; }

.follow_btn { grid-area: follow_btn;
max-height: 50%; 
display: grid;
align-self: center;
justify-self: end;
}

</style>