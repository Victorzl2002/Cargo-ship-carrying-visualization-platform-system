import request from '@/utils/request'

// 查询危险品知识库列表
export function listDangerknowledge(query) {
  return request({
    url: '/dangerknowledge/dangerknowledge/list',
    method: 'get',
    params: query
  })
}

// 查询危险品知识库详细
export function getDangerknowledge(dangerId) {
  return request({
    url: '/dangerknowledge/dangerknowledge/' + dangerId,
    method: 'get'
  })
}

// 新增危险品知识库
export function addDangerknowledge(data) {
  return request({
    url: '/dangerknowledge/dangerknowledge',
    method: 'post',
    data: data
  })
}

// 修改危险品知识库
export function updateDangerknowledge(data) {
  return request({
    url: '/dangerknowledge/dangerknowledge',
    method: 'put',
    data: data
  })
}

// 删除危险品知识库
export function delDangerknowledge(dangerId) {
  return request({
    url: '/dangerknowledge/dangerknowledge/' + dangerId,
    method: 'delete'
  })
}
