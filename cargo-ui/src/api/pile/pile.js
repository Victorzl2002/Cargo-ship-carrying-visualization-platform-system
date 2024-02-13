import request from '@/utils/request'

// 查询货堆管理列表
export function listPile(query) {
  return request({
    url: '/pile/pile/list',
    method: 'get',
    params: query
  })
}

// 查询货堆管理详细
export function getPile(pileId) {
  return request({
    url: '/pile/pile/' + pileId,
    method: 'get'
  })
}

// 新增货堆管理
export function addPile(data) {
  return request({
    url: '/pile/pile',
    method: 'post',
    data: data
  })
}

// 修改货堆管理
export function updatePile(data) {
  return request({
    url: '/pile/pile',
    method: 'put',
    data: data
  })
}

// 删除货堆管理
export function delPile(pileId) {
  return request({
    url: '/pile/pile/' + pileId,
    method: 'delete'
  })
}
