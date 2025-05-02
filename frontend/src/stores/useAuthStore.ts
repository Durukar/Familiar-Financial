import { signInApi } from '@/api/sign-in'
import {create} from 'zustand'

type AuthState = {
  user: string | null
  login: (username: string, password: string) => void
  logout: () => void
}

export const useAuthStore = create<AuthState>((set) => (
  {
    user: null,
    login: async (username: string, password: string) => {
      try {
        await signInApi.signIn({username, password})
      } catch (error) {
        console.error(`Failed to login ${error}`)
      }
    },
    logout: () => set({ user: null})
  }
))