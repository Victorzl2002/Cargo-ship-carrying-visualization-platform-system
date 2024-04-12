import request from '@/utils/request'

// 查询driver列表
export function listDriver(query) {
  return request({
    url: '/driver/driver/list',
    method: 'get',
    params: query
  })
}

// 查询driver详细
export function getDriver(driverId) {
  return request({
    url: '/driver/driver/' + driverId,
    method: 'get'
  })
}

// 新增driver
export function addDriver(data) {
  return request({
    url: '/driver/driver',
    method: 'post',
    data: data
  })
}

// 修改driver
export function updateDriver(data) {
  return request({
    url: '/driver/driver',
    method: 'put',
    data: data
  })
}

// 删除driver
export function delDriver(driverId) {
  return request({
    url: '/driver/driver/' + driverId,
    method: 'delete'
  })
}
