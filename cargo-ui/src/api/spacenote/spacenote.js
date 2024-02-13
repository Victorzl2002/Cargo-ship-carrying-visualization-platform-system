import request from '@/utils/request'

// 查询集装箱空间利用率列表
export function listSpacenote(query) {
  return request({
    url: '/spacenote/spacenote/list',
    method: 'get',
    params: query
  })
}

// 查询集装箱空间利用率详细
export function getSpacenote(spaceutilizationrateId) {
  return request({
    url: '/spacenote/spacenote/' + spaceutilizationrateId,
    method: 'get'
  })
}

// 新增集装箱空间利用率
export function addSpacenote(data) {
  return request({
    url: '/spacenote/spacenote',
    method: 'post',
    data: data
  })
}

// 修改集装箱空间利用率
export function updateSpacenote(data) {
  return request({
    url: '/spacenote/spacenote',
    method: 'put',
    data: data
  })
}

// 删除集装箱空间利用率
export function delSpacenote(spaceutilizationrateId) {
  return request({
    url: '/spacenote/spacenote/' + spaceutilizationrateId,
    method: 'delete'
  })
}
