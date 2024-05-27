import apiClient from "@/utils/apiClient";
import User from "@/types/User";

class UserService {
    // get All Users
    getAllUsers = (): Promise<User[]> => {
        return apiClient.get("/users").then((response) => response.data);
    };
    getUserById = (id: any): Promise<User> => {
        return apiClient.get(`/users/${id}`).then((response) => response.data);
    };

    edit = (id: any, emp: any): Promise<User> => {
        return apiClient
            .put(`/users/${id}`, emp)
            .then((response) => response.data);
    };
    delete = async (id: any): Promise<User> => {
        return await apiClient
            .delete(`/users/${id}`)
            .then((response) => response.data);
    };
    save = (emp: any): Promise<User> => {
        return apiClient.post("/users", emp).then((response) => response.data);
    };
}

export default new UserService();
