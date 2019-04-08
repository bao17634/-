axios.defaults.baseURL = '/yqwl';
axios.defaults.headers.common['Authorization'] = '11111222';
// axios.defaults.headers.post['content-Type'] = 'appliction/x-www-form-urlencoded';

axios.interceptors.response.use(function (response) {
    if (response.status === 200) {
        response = response.data;
    }
    return response
}, function (error) {
    // Do something with response error
    return Promise.reject(error)
})

Vue.prototype.$http = axios
