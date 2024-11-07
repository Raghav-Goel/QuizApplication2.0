import axios from 'axios';
export const Base_url = "http://localhost:8080/";
export const myAxios = axios.create({
  baseURL: Base_url,
});
