import request from '@/utils/request'

// 查询wxuser列表
export function listUser(query) {
  return request({
    url: '/user/user/list',
    method: 'get',
    params: query
  })
}

// 查询wxuser详细
export function getUser(openId) {
  return request({
    url: '/user/user/' + openId,
    method: 'get'
  })
}

// 新增wxuser
export function addUser(data) {
  return request({
    url: '/user/user',
    method: 'post',
    data: data
  })
}

// 修改wxuser
export function updateUser(data) {
  return request({
    url: '/user/user',
    method: 'put',
    data: data
  })
}

// 删除wxuser
export function delUser(openId) {
  return request({
    url: '/user/user/' + openId,
    method: 'delete'
  })
}
