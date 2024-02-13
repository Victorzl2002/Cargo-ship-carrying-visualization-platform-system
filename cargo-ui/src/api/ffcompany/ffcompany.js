import request from '@/utils/request'

// 查询货代公司管理列表
export function listFfcompany(query) {
  return request({
    url: '/ffcompany/ffcompany/list',
    method: 'get',
    params: query
  })
}

// 查询货代公司管理详细
export function getFfcompany(ffcompanyId) {
  return request({
    url: '/ffcompany/ffcompany/' + ffcompanyId,
    method: 'get'
  })
}

// 新增货代公司管理
export function addFfcompany(data) {
  return request({
    url: '/ffcompany/ffcompany',
    method: 'post',
    data: data
  })
}

// 修改货代公司管理
export function updateFfcompany(data) {
  return request({
    url: '/ffcompany/ffcompany',
    method: 'put',
    data: data
  })
}

// 删除货代公司管理
export function delFfcompany(ffcompanyId) {
  return request({
    url: '/ffcompany/ffcompany/' + ffcompanyId,
    method: 'delete'
  })
}
