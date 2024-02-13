import request from '@/utils/request'

// 查询危险品识别信息管理列表
export function listDangerinfo(query) {
  return request({
    url: '/dangerinfo/dangerinfo/list',
    method: 'get',
    params: query
  })
}

// 查询危险品识别信息管理详细
export function getDangerinfo(dangerId) {
  return request({
    url: '/dangerinfo/dangerinfo/' + dangerId,
    method: 'get'
  })
}

// 新增危险品识别信息管理
export function addDangerinfo(data) {
  return request({
    url: '/dangerinfo/dangerinfo',
    method: 'post',
    data: data
  })
}

// 修改危险品识别信息管理
export function updateDangerinfo(data) {
  return request({
    url: '/dangerinfo/dangerinfo',
    method: 'put',
    data: data
  })
}

// 删除危险品识别信息管理
export function delDangerinfo(dangerId) {
  return request({
    url: '/dangerinfo/dangerinfo/' + dangerId,
    method: 'delete'
  })
}
