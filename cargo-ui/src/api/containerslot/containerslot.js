import request from '@/utils/request'

// 查询箱位管理列表
export function listContainerslot(query) {
  return request({
    url: '/containerslot/containerslot/list',
    method: 'get',
    params: query
  })
}

// 查询箱位管理详细
export function getContainerslot(slotId) {
  return request({
    url: '/containerslot/containerslot/' + slotId,
    method: 'get'
  })
}

// 新增箱位管理
export function addContainerslot(data) {
  return request({
    url: '/containerslot/containerslot',
    method: 'post',
    data: data
  })
}

// 修改箱位管理
export function updateContainerslot(data) {
  return request({
    url: '/containerslot/containerslot',
    method: 'put',
    data: data
  })
}

// 删除箱位管理
export function delContainerslot(slotId) {
  return request({
    url: '/containerslot/containerslot/' + slotId,
    method: 'delete'
  })
}
