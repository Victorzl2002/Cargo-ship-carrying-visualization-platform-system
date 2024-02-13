import request from '@/utils/request'

// 查询集装箱装配列表
export function listContainerassembly(query) {
  return request({
    url: '/containerassembly/containerassembly/list',
    method: 'get',
    params: query
  })
}

// 查询集装箱装配详细
export function getContainerassembly(assemblyId) {
  return request({
    url: '/containerassembly/containerassembly/' + assemblyId,
    method: 'get'
  })
}

// 新增集装箱装配
export function addContainerassembly(data) {
  return request({
    url: '/containerassembly/containerassembly',
    method: 'post',
    data: data
  })
}

// 修改集装箱装配
export function updateContainerassembly(data) {
  return request({
    url: '/containerassembly/containerassembly',
    method: 'put',
    data: data
  })
}

// 删除集装箱装配
export function delContainerassembly(assemblyId) {
  return request({
    url: '/containerassembly/containerassembly/' + assemblyId,
    method: 'delete'
  })
}
