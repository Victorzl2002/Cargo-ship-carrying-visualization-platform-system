import request from '@/utils/request'

// 查询route列表
export function listRoute(query) {
  return request({
    url: '/route/route/list',
    method: 'get',
    params: query
  })
}

// 查询route详细
export function getRoute(routeId) {
  return request({
    url: '/route/route/' + routeId,
    method: 'get'
  })
}

// 新增route
export function addRoute(data) {
  return request({
    url: '/route/route',
    method: 'post',
    data: data
  })
}

// 修改route
export function updateRoute(data) {
  return request({
    url: '/route/route',
    method: 'put',
    data: data
  })
}

// 删除route
export function delRoute(routeId) {
  return request({
    url: '/route/route/' + routeId,
    method: 'delete'
  })
}
