import request from '@/utils/request'

// 查询port列表
export function listPort(query) {
  return request({
    url: '/port/port/list',
    method: 'get',
    params: query
  })
}

// 查询port详细
export function getPort(portId) {
  return request({
    url: '/port/port/' + portId,
    method: 'get'
  })
}

// 新增port
export function addPort(data) {
  return request({
    url: '/port/port',
    method: 'post',
    data: data
  })
}

// 修改port
export function updatePort(data) {
  return request({
    url: '/port/port',
    method: 'put',
    data: data
  })
}

// 删除port
export function delPort(portId) {
  return request({
    url: '/port/port/' + portId,
    method: 'delete'
  })
}
