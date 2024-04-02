import axios from 'axios';

// export function retrieveHelloWorldBean() {
//  return axios.get('http://localhost:8080/hello-world-bean')
// }

const apiClient = axios.create(
    {
        baseURL : 'http://localhost:8080'
    }
)

export const retrieveHelloWorldBean 
    = () => apiClient.get('/hello-world-bean')

// Response to preflight request doesn't pass access control check => Authentication header
export const retrieveHelloWorldPathVariable
    = (username) => apiClient.get(`/hello-world/path-variable/${username}`, {
        headers: {
            Authorization: 'Basic aW4yOG1pbnV0ZXM6ZHVtbXk='
        }
    })

export const executeBasicAuthenticationService
    = (token) => apiClient.get(`/basicauth`, {
        headers: {
            Authorization: token
        }
    })
    