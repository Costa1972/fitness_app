import apiClient from "@/utils/apiClient";
import router from "@/router";
import LoginView from "@/views/LoginView.vue";
class AuthService {
    login = async (user: any): Promise<any> => {
        return await apiClient.post("/auth/login", {
            username: user.username,
            password: user.password,
        });
    };
    logout() {
        window.localStorage.removeItem("accessToken");
        router.push("/login");
    }
}
export default new AuthService();
