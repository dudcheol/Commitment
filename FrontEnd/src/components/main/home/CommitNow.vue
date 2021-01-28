<template>

<v-list>
    <v-list-item >
        <v-list-item-avatar width="5px">
            <vs-avatar>
                <i class='bx bxs-backpack' ></i>
            </vs-avatar>
        </v-list-item-avatar>
        <v-list-item-content>
        <v-list-item-title>
            오늘의 하루
        </v-list-item-title>
        <v-list-item-subtitle>
            마라탕존맛
        </v-list-item-subtitle>
    </v-list-item-content>

    </v-list-item>
        <v-list-item>
        <v-list-item-avatar width="5px">
            <vs-avatar>
                <i class='bx bxs-hot' ></i>
            </vs-avatar>
        </v-list-item-avatar>
        <v-list-item-content>
        <v-list-item-title>
            우왕 여기는
        </v-list-item-title>
        <v-list-item-subtitle>
            매우핫한장소네
        </v-list-item-subtitle>
        </v-list-item-content>
    </v-list-item>

    <v-list-item>
        <v-list-item-avatar width="5px">
            <vs-avatar>
                <i class='bx bxs-wine' ></i>
            </vs-avatar>
        </v-list-item-avatar>
        <v-list-item-content>
        <v-list-item-title>
            꺄르륵
        </v-list-item-title>
        <v-list-item-subtitle>
            뀨?꺄?
        </v-list-item-subtitle>
        </v-list-item-content>
    </v-list-item>
</v-list>

</template>

<script>
export default {
    name: "CommitNow",
    data: () => ({

})
}
</script>

<style>

.message {
    display: flex;
    width: min-content;
}

.vs-avatar-content {
    width: 30px;
    height: 30px;
}
/* .v-list-item__title {
    font-size: 30px;
} */
</style>