import request from '@/utils/request'

// 查询监测信息反馈列表
export function listMornite(query) {
  return request({
    url: '/envirinfo/mornite/list',
    method: 'get',
    params: query
  })
}

// 查询监测信息反馈详细
export function getMornite(morniteId) {
  return request({
    url: '/envirinfo/mornite/' + morniteId,
    method: 'get'
  })
}

// 新增监测信息反馈
export function addMornite(data) {
  return request({
    url: '/envirinfo/mornite',
    method: 'post',
    data: data
  })
}

// 修改监测信息反馈
export function updateMornite(data) {
  return request({
    url: '/envirinfo/mornite',
    method: 'put',
    data: data
  })
}

// 删除监测信息反馈
export function delMornite(morniteId) {
  return request({
    url: '/envirinfo/mornite/' + morniteId,
    method: 'delete'
  })
}
