<template>

<div class="d-flex justify-center">
<!-- top -->
            <div class="top" :justify="dynamicJustify">
                <div class="profileImg">
                    <v-list-item-avatar size="130">
                                <img src="https://smallstartour.com/wp-content/uploads/2020/05/SA_page3_top1080x700-compressor.jpg" alt="picture">
                    </v-list-item-avatar>
                </div>
                <v-card
                    class="mx-auto"
                    flat
                    :width="width"
                >
                    <v-card-title>
                        26 | 여성
                    </v-card-title>
                    <v-card-subtitle>
                        닉네임
                    </v-card-subtitle>
                    <div class="detail_left">
                        <v-spacer></v-spacer>
                        <div class="badge">
                            <div class="badgeImg">
                                <v-list-item-avatar size="50">
                                <img
                                    src="https://cdn4.iconfinder.com/data/icons/badges-9/66/31-512.png"
                                    alt="picture">
                                </v-list-item-avatar>
                            </div>
                        </div>

                    </div>

                    <v-card-actions>
                        <vs-button
                            uploade
                            success
                            border
                            :active="active == 2"
                            @click="active = 2"
                            class="button"
                        >
                            <i class="bx bxs-magic-wand"> 커밋 30</i> 
                        </vs-button>
                        <Follower/>
                        
                    <v-spacer></v-spacer>
                    <ProfileEdit/>
                    <v-btn
                        icon
                        @click="show = !show"
                    >
                        <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                    </v-btn>
                    </v-card-actions>

                    <v-expand-transition>
                    <div v-show="show">

                        <v-card-text>
                        I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape.
                        </v-card-text>
                        
                    </div>
                    </v-expand-transition>
                </v-card>
                
            </div>
</div>

</template>
<script>
import Follower from '../../common/dialog/Follower'
import ProfileEdit from '../../common/dialog/ProfileEdit'
export default {
    components: {
          Follower,
          ProfileEdit,
    },
    data: () => ({
        valueOne: '', 
        valueTwo: '',
        show: false,
        }),
    computed:{
        width () {
            switch (this.$vuetify.breakpoint.name) {
            case 'xs': return '200px'
            case 'sm': return '200px';
            case 'md': return '500px';
            case 'lg': return '600px';
            case 'xl': return '900px';
            }
            return 700
        },
    }
}
</script>


<style scoped>
        .badgeImg{
            width: 35%;
            text-align: center;
            margin-top:-70%;
        }
        .detail{
            display: flex;
            justify-content:space-between;
        }
        .detail_left{
            display: flex;
        }
        .numbers{
            font-weight: bold;
            color:grey
        }
        .top {
            display: flex;
        }
        .nickname{
            font-size: 20px;
            font-weight: bold;
            color:black;
        }
        .agengender{
            font-weight: bold;
            color:grey;
        }
        .button{
            margin-right: 2%;
        }
</style>