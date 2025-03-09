import axios from "axios";

const apiUrl = process.env.REACT_APP_API_URL + "/api/v1";


export function get(url, parameters) {
    return axios.get(apiUrl + url, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem("token")
        },
        toJSON() {
            return parameters;
        }
    });
}

export function post(url, parameters) {
    return axios.post(apiUrl + url, parameters, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem("token")
        }
    });
}