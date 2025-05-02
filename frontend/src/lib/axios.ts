import { getUrl } from '@/utils/get-hostname'
import axios from 'axios'

const api = axios.create({
  baseURL: `${getUrl()}`,
  withCredentials: true
})

export {api}