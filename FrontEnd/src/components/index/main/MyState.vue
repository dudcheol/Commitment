<template>
  <div class="d-flex justify-center">
    <v-sheet rounded="xl" class="mid-size px-3">
      <v-container class="my-2">
        <v-row>
          <v-col>
            <h4>
              <v-icon class="mr-1" color="primary">mdi-crosshairs-gps</v-icon
              >{{ address ? address : '위치를 찾는 중...' }}
            </h4>
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <div class="d-flex flex-row">
              <div class="flex-grow-0">
                <vs-avatar circle size="40">
                  <img src="https://picsum.photos/200/300" alt="" />
                </vs-avatar>
              </div>
              <div class="flex-grow-1 ml-2">
                <v-chip
                  large
                  style="height:40px; width:100%; cursor:pointer"
                  @click="write = true"
                  color="blue-grey lighten-5"
                  text-color="blue-grey darken-2"
                  :ripple="false"
                  depressed
                >
                  {{ user.nickname }}님, 여기는 어떤 곳인가요?
                </v-chip>
              </div>
            </div>
          </v-col>
        </v-row>
      </v-container>
      <write-dialog :web="write" @close="closeWrite"></write-dialog>
    </v-sheet>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import WriteDialog from '../../common/dialog/WriteDialog.vue';
export default {
  props: ['openWriteDialog'],
  watch: {
    openWriteDialog: {
      immediate: true,
      handler(val) {
        this.write = val;
      },
    },
  },
  data() {
    return {
      write: this.openWriteDialog,
    };
  },
  computed: {
    ...mapGetters({ address: ['getCurrentAddress'], user: ['getUserInfo'] }),
  },
  components: { WriteDialog },
  methods: {
    closeWrite() {
      this.write = false;
      this.$emit('close-write');
    },
  },
};
</script>

<style scoped>
.my_location {
  display: flex;
  align-items: center;
}
.commit_input {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  align-content: center;
}

.buttons {
  display: flex;
  flex-direction: row;
}

.commit_input .vs-input {
  width: 50vh;
}

@media (max-width: 450px) {
  .commit_input .vs-input {
    width: 25vh;
  }
}
</style>
