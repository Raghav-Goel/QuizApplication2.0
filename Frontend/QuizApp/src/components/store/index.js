import {createStore} from "redux";
const values={
    isLoggedIn:null,
}
const counterReducer=(store=values,action)=>{
    if(action.type==='true'){
        console.log("Hello")
        return {isLoggedIn:"true"};
    }
    console.log('Inside store');
    return store;
}

const quizStore=createStore(counterReducer);
export default quizStore;