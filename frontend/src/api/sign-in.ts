import { api } from "@/lib/axios";

export interface SignInBody {
  username: string;
  password: string;
}

const url = "/api/v1/auth";


const signInApi = {
  signIn: async ({ username, password }: SignInBody) => {
    await api.post(`${url}/login`, { username, password });
  },
};

export {signInApi};
