<template>
  <div class="home">
    <h1>
      Добро пожаловать! <br />
      <br />
      Клиенты
    </h1>
    <div v-for="(user, index) in users" :key="index">
      <user-card :user="user" />
    </div>
    <Footer></Footer>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import UserCard from "@/components/UserCard.vue";
import FooterPage  from "@/components/FooterPage.vue";
import User from "@/types/User";
import UserService from "@/services/UserService";

export default defineComponent({
  name: "HomeView",
  components: { UserCard, FooterPage },

  setup() {
    let users = ref<User[]>([]);

    UserService
        .getAllUsers()
        .then((response) => {
          users.value = response;
        })
        .catch((error) => {
          if (error.response && error.response.status === 401) {
            // Redirect the user to the login page
            window.location.href = "/login";
          }
        });

    return {
      users,
    };
  },
});
</script>

<style scoped>
h1 {
  text-align: center;
  margin-bottom: 80px;
}
</style>
