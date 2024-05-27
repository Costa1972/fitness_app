
<template>
  <div class="user-details">
    <div class="user-header">
      <div class="user-avatar">
        <img src="../assets/lofi2.jpeg" alt="User Avatar" />
      </div>
      <div class="user-info">
        <h1>{{ user?.username }}</h1>
        <h2>{{ user?.last_name }}</h2>
        <div class="user-contact">
          <div class="user-contact-item">
            <i class="fas fa-envelope"></i>
            <p>{{ user?.email }}</p>
          </div>
          <div class="user-contact-item">
            <i class="fas fa-phone"></i>
            <p>{{ user?.phone }}</p>
          </div>
        </div>
      </div>
    </div>
    <div class="user-details-body">
      <div class="user-details-section">
        <h2>About {{ user?.username }}</h2>
        <p>{{ user?.bio }}</p>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import userService from "@/services/UserService";
import User from "@/types/User";
import { defineComponent, ref } from "vue";
import { useRoute } from "vue-router";
import user from "@/types/User";

export default defineComponent({
  setup() {
    // question difference between useRouter and useRoute
    const route = useRoute();
    const id: string | string[] = route.params.id;
    let user = ref<User>();

    userService
        .getUserById(id)
        .then((Response) => (user.value = Response));

    return {
      user,
    };
  },
});
</script>

<style>
.employee-details {
  margin: 2rem auto;
  max-width: 800px;
  padding: 2rem;
  background-color: #f2f2f2;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  font-family: Arial, sans-serif;
  color: #333;
}

.employee-header {
  display: flex;
  align-items: center;
  margin-bottom: 2rem;
}

.employee-avatar {
  width: 150px;
  height: 150px;
  border-radius: 50%;
  overflow: hidden;
  margin-right: 2rem;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

.employee-avatar img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.employee-info {
  display: flex;
  flex-direction: column;
}

.employee-info h1 {
  font-size: 2.5rem;
  margin: 0;
}

.employee-info h2 {
  font-size: 1.5rem;
  margin: 0;
  color: #666;
}

.employee-contact {
  display: flex;
  margin-top: 1rem;
}

.employee-contact-item {
  display: flex;
  align-items: center;
  margin-right: 1rem;
}

.employee-contact-item i {
  font-size: 1.2rem;
}

.employee-contact-item p {
  margin: 0 0 0 0.5rem;
}

.employee-details-body {
  display: flex;
  flex-direction: column;
}

.employee-details-section {
  margin-bottom: 2rem;
}

.employee-details-section h2 {
  font-size: 1.8rem;
  margin-bottom: 1rem;
}

.employee-details-section p {
  line-height: 1.5;
}

.employee-skills {
  list-style: none;
  padding: 0;
  margin: 0;
}
.employee-skills li {
  display: inline-block;
  background-color: #ccc;
  color: #333;
  padding: 0.5rem 1rem;
  border-radius: 3px;
  margin-right: 0.5rem;
  margin-bottom: 0.5rem;
}

.employee-projects {
  list-style: none;
  padding: 0;
  margin: 0;
}

.employee-projects h3 {
  font-size: 1.5rem;
  margin-bottom: 0.5rem;
}

.project-tech-stack {
  margin-top: 0.5rem;
}

.project-tech-stack ul {
  list-style: none;
  padding: 0;
  margin: 0;
}
.project-tech-stack li {
  display: inline-block;
  background-color: #ccc;
  color: #333;
  padding: 0.5rem 1rem;
  border-radius: 3px;
  margin-right: 0.5rem;
  margin-bottom: 0.5rem;
}
</style>