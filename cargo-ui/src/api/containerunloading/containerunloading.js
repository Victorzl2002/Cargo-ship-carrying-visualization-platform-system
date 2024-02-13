import request from '@/utils/request'

// 查询集装箱卸载管理列表
export function listContainerunloading(query) {
  return request({
    url: '/containerunloading/containerunloading/list',
    method: 'get',
    params: query
  })
}

// 查询集装箱卸载管理详细
export function getContainerunloading(unloadingId) {
  return request({
    url: '/containerunloading/containerunloading/' + unloadingId,
    method: 'get'
  })
}

// 新增集装箱卸载管理
export function addContainerunloading(data) {
  return request({
    url: '/containerunloading/containerunloading',
    method: 'post',
    data: data
  })
}

// 修改集装箱卸载管理
export function updateContainerunloading(data) {
  return request({
    url: '/containerunloading/containerunloading',
    method: 'put',
    data: data
  })
}

// 删除集装箱卸载管理
export function delContainerunloading(unloadingId) {
  return request({
    url: '/containerunloading/containerunloading/' + unloadingId,
    method: 'delete'
  })
}
