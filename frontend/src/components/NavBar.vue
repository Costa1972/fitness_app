<template>
  <nav class="navbar">
    <div class="navbar-left">
      <img src="@/assets/logo.svg" alt="Logo" class="navbar-logo" />
    </div>
    <div class="navbar-right">
      <router-link to="/" class="navbar-link">Домой</router-link> |
      <router-link to="/about" class="navbar-link">О нас</router-link>
      <router-link to="/add/user" class="navbar-link"
      >Добавить клиента</router-link
      >
      <router-link to="/login" class="navbar-link">Войти</router-link>
    </div>
  </nav>
</template>

<style>
.navbar {
  background: linear-gradient(to right,black, gray);
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.25);
}

.navbar-logo {
  height: 50px;
}

.navbar-link {
  color: white;
  text-decoration: none;
  margin-left: 20px;
  font-size: 18px;
}

.navbar-link:hover {
  text-decoration: underline;
}
</style>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "NavBar",

  components: {},

  setup(props, ctx) {
    return {};
  },
});
</script>
