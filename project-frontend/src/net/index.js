import axios from "axios";
import {ElMessage} from "element-plus";

const defaultError = () => ElMessage.error('发生了一些错误，请联系管理员')
const defaultFailure = (message) => ElMessage.warning(message)

const service = axios.create({
    baseURL : "/api"
})

export default service
    


function post(url, data, success, failure = defaultFailure, error = defaultError) {
    axios.post(url, data, {
        headers: {
            'Content-Type': 'application/json'
        },
        withCredentials: true
    }).then(({data}) => {
        if(data.success)
        {
            success(data.message, data.status);
        }else
            failure(data.message, data.status)
    }).catch(error)
}
function postall(url, data, success, failure = defaultFailure, error = defaultError) {
    axios.post(url, data, {
        headers: {
            'Content-Type': 'application/json'
        },
        withCredentials: true
    }).then(({data}) => {
        // 直接将完整的 data 对象传递给 success 回调函数
        if (data.success) {
            success(data);  // 传递完整的 JSON 数据
        } else {
            failure(data);  // 传递完整的 JSON 数据给 failure 回调
        }
    }).catch(error);
}
// postall('http://localhost:8080/user/register', 
//     {
//         username: form.username,
//         realname: form.realname,   // 真实姓名
//         level: form.identity,   // 身份（学生或老师）
//         password: form.password,
//         email: form.email,
//         phone: form.phone
//     },
//         (data) => {
//             // 成功时的处理逻辑，data 是完整的 JSON 响应
//             console.log('成功响应：', data);
//             ElMessage.success(data.message);
//         }, 
//         (data) => {
//             // 失败时的处理逻辑，data 也是完整的 JSON 响应
//             console.log('失败响应：', data);
//             ElMessage.warning(data.message);
//         },
//         (error) => {
//             // 请求错误时的处理逻辑
//             console.error('请求错误:', error);
//             ElMessage.error("请求出现错误，请稍后重试");
//         }
function get(url, success, failure = defaultFailure, error = defaultError) {
    axios.get(url, {
        withCredentials: true
    }).then(({data}) => {
        if(data.success)
            success(data.message, data.status)
        else
            failure(data.message, data.status)
    }).catch(error)
}
function getall(url, successCallback, failure = defaultFailure, error = defaultError) {
    axios.get(url, {
        withCredentials: true
    }).then(({data}) => {
        // 直接将完整的 data 对象传递给 successCallback
        if (data.success) {
            successCallback(data);  // 传递整个后端返回的 JSON 数据
        } else {
            failure(data.message);
        }
    }).catch(error);
}
// getall(`http://localhost:8080/user/check-username?username=${form.username}`,
//     (data) => {
//         // 成功时的处理逻辑
//         console.log(data);  // 打印完整的后端返回的 JSON 数据

//         // 根据 status 字段判断学号是否存在
//         if (data.status === 'exists') {
//             ElMessage.error("学号已存在");
//         } else if (data.status === 'available') {
//             ElMessage.success("学号可用");
//         }
//     },
//     (failureMessage) => {
//         // 失败时的处理逻辑
//         ElMessage.warning(`校验失败：${failureMessage}`);
//     },
//     (error) => {
//         // 网络或其他错误处理
//         console.error("请求错误:", error);
//         ElMessage.error("请求出现错误，请稍后重试");
//     }
// );
// };
export { get, post ,getall,postall}