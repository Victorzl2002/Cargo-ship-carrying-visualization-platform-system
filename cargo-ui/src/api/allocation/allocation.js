import request from '@/utils/request'

// 查询货物分配列表
export function listAllocation(query) {
  return request({
    url: '/allocation/allocation/list',
    method: 'get',
    params: query
  })
}

// 查询货物分配详细
export function getAllocation(allocationId) {
  return request({
    url: '/allocation/allocation/' + allocationId,
    method: 'get'
  })
}

// 新增货物分配
export function addAllocation(data) {
  return request({
    url: '/allocation/allocation',
    method: 'post',
    data: data
  })
}

// 修改货物分配
export function updateAllocation(data) {
  return request({
    url: '/allocation/allocation',
    method: 'put',
    data: data
  })
}

// 删除货物分配
export function delAllocation(allocationId) {
  return request({
    url: '/allocation/allocation/' + allocationId,
    method: 'delete'
  })
}
