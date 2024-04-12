import request from '@/utils/request'

// 查询terminalmanage列表
export function listTerminalmanage(query) {
  return request({
    url: '/terminalmanage/terminalmanage/list',
    method: 'get',
    params: query
  })
}

// 查询terminalmanage详细
export function getTerminalmanage(terminalId) {
  return request({
    url: '/terminalmanage/terminalmanage/' + terminalId,
    method: 'get'
  })
}

// 新增terminalmanage
export function addTerminalmanage(data) {
  return request({
    url: '/terminalmanage/terminalmanage',
    method: 'post',
    data: data
  })
}

// 修改terminalmanage
export function updateTerminalmanage(data) {
  return request({
    url: '/terminalmanage/terminalmanage',
    method: 'put',
    data: data
  })
}

// 删除terminalmanage
export function delTerminalmanage(terminalId) {
  return request({
    url: '/terminalmanage/terminalmanage/' + terminalId,
    method: 'delete'
  })
}
