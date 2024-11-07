import { myAxios } from './Helper';
export const getUserByEmailId = async (emailId) => {
  try {
    const response = await myAxios.get(`user/emailId/${emailId}`);
    return response.data;
  } catch (error) {
    console.log("error");
    throw error;
  }
};

export const addUser = async (User) => {
  try {
    
    const response = await myAxios.post("user/addUser", User);
    return response.data;
  } catch (error) {
    console.log("error");
    throw error;
  }
};
