<template>
  <div class="mt-4">
    <v-expansion-panels>
      <v-expansion-panel>
        <v-expansion-panel-header>
          전체완료
          <template v-slot:actions>
            <v-icon color="primary">
              $expand
            </v-icon>
          </template>
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          Lorem ipsum dolor sit amet,
        </v-expansion-panel-content>
      </v-expansion-panel>

      <v-expansion-panel>
        <v-expansion-panel-header disable-icon-rotate>
          호엥
          <template v-slot:actions>
            <v-icon color="teal">
              mdi-check
            </v-icon>
          </template>
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          Lorem ipsum dolor sit amet,
        </v-expansion-panel-content>
      </v-expansion-panel>

      <v-expansion-panel>
        <v-expansion-panel-header disable-icon-rotate>
          커밋만
          <template v-slot:actions>
            <v-icon color="error">
              mdi-alert-circle
            </v-icon>
          </template>
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          Lorem ipsum dolor
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </div>
</template>

<script>
export default {};
</script>

<style scoped></style>
