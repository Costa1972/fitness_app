<template>
  <div class="user-edit-form">
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="name">Ник</label>
        <input
            type="text"
            id="name"
            v-model="userToBeSaved.username"
            required
        />
      </div>
      <div class="form-group">
        <label for="name">Имя</label>
        <input
            type="text"
            id="name"
            v-model="userToBeSaved.first_name"
            required
        />
      </div>
      <div class="form-group">
        <label for="name">Фамилия</label>
        <input
            type="text"
            id="name"
            v-model="userToBeSaved.last_name"
            required
        />
      </div>
      <div class="form-group">
        <label for="email">Email</label>
        <input
            type="email"
            id="email"
            v-model="userToBeSaved.email"
            required
        />
      </div>
      <div class="form-group">
        <label for="phone">Phone</label>
        <input
            type="tel"
            id="phone"
            v-model="userToBeSaved.phone"
            required
        />
      </div>
      <div class="form-group">
        <label for="name">Возраст</label>
        <input

            type="text"
            id="name"
            v-model="userToBeSaved.age"
            required
        />
      </div>
      <div class="form-group">
        <label for="name">Пол</label>
        <input
            type="text"
            id="name"
            v-model="userToBeSaved.sex"
            required
        />
      </div>
      <div class="form-actions">
        <button type="submit">Save User</button>
        <button type="button" @click="cancelEdit">Cancel</button>
      </div>
    </form>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import User from "@/types/User";
import UserService from "@/services/UserService";
import { useRouter } from "vue-router";

export default defineComponent({
  name: "AddUserView",
  setup() {
    const userToBeSaved = ref<User>({
      id: 0,
      username: "",
      first_name: "",
      last_name: "",
      phone: "",
      email: "",
      age: 0,
      sex: "",
      bio: ""
    });
    const router = useRouter();

    const submitForm = () => {
      UserService.save(userToBeSaved.value).then(() => {
        router.push({ name: "home" });
      });
      window.location.reload;
    };
    const cancelEdit = () => {
      router.push({ name: "home" });
    };

    return {
      userToBeSaved,
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
