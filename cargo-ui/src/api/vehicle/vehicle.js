import request from '@/utils/request'

// 查询货车管理列表
export function listVehicle(query) {
  return request({
    url: '/vehicle/vehicle/list',
    method: 'get',
    params: query
  })
}

// 查询货车管理详细
export function getVehicle(vehicleId) {
  return request({
    url: '/vehicle/vehicle/' + vehicleId,
    method: 'get'
  })
}

// 新增货车管理
export function addVehicle(data) {
  return request({
    url: '/vehicle/vehicle',
    method: 'post',
    data: data
  })
}

// 修改货车管理
export function updateVehicle(data) {
  return request({
    url: '/vehicle/vehicle',
    method: 'put',
    data: data
  })
}

// 删除货车管理
export function delVehicle(vehicleId) {
  return request({
    url: '/vehicle/vehicle/' + vehicleId,
    method: 'delete'
  })
}
