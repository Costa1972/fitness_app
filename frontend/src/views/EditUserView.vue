<template>
  <div class="user-edit-form">
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="username">Ник</label>
        <input type="text" id="username" v-model="editedUser.username" required />
      </div>
      <div class="form-group">
        <label for="first_name">Имя</label>
        <input type="text" id="first_name" v-model="editedUser.first_name" required />
      </div>
      <div class="form-group">
        <label for="last_name">Фамилия</label>
        <input type="last_name" id="email" v-model="editedUser.last_name" required />
      </div>
      <div class="form-group">
        <label for="email">email</label>
        <input type="tel" id="phone" v-model="editedUser.email" required />
      </div>
      <div class="form-group">
        <label for="position">Телефон</label>
        <input
            type="text"
            id="phone"
            v-model="editedUser.phone"
            required
        />
      </div>
      <div class="form-group">
        <label for="bio">Bio</label>
        <textarea id="bio" v-model="editedUser.age" required></textarea>
      </div>
      <div class="form-group">
        <label for="name">Name</label>
        <input type="text" id="name" v-model="editedUser.sex" required />
      </div>
      <div class="form-actions">
        <button type="submit">Save Changes</button>
        <button type="button" @click="cancelEdit">Cancel</button>
      </div>
    </form>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import User from "@/types/User";
import UserService from "@/services/UserService";
import { useRoute, useRouter } from "vue-router";

export default defineComponent({
  name: "EmployeeEditForm",
  setup() {
    const editedUser = ref<User>();
    console.log(editedUser);
    const route = useRoute();
    const router = useRouter();
    let id = route.params.id;
    UserService.getEmployeeById(id).then((response) => {
      editedUser.value = response;
    });

    const submitForm = () => {
      UserService.edit(id, editedUser.value).then(() => {
        router.push({ name: "home" });
      });
      window.location.reload;
    };
    const cancelEdit = () => {
      router.push({ name: "home" });
    };

    return {
      editedUser,
      cancelEdit,
      submitForm,
    };
  },
});
</script>

<style scoped>
.user-edit-form {
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  padding: 2rem;
}

.form-group {
  margin-bottom: 1rem;
}

label {
  display: block;
  font-size: 1.2rem;
  margin-bottom: 0.5rem;
}

input,
textarea {
  border-radius: 3px;
  border: 1px solid #ccc;
  padding: 0.5rem;
  font-size: 1.2rem;
  width: 100%;
  box-sizing: border-box;
}

button {
  background-color: #007aff;
  color: #fff;
  border: none;
  border-radius: 3px;
  padding: 0.5rem 1rem;
  font-size: 1.2rem;
  cursor: pointer;
  margin-right: 1rem;
}

button:hover {
  background-color: #0062cc;
}

button[type="submit"] {
  background-color: #2894a7;
}

button[type="submit"]:hover {
  background-color: #216188;
}

button[type="button"] {
  background-color: #dc3545;
}

button[type="button"]:hover {
  background-color: #c82333;
}

.form-actions {
  margin-top: 1rem;
  display: flex;
  justify-content: flex-end;
}
</style>
