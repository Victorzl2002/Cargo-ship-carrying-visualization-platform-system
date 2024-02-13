import request from '@/utils/request'

// 查询shipcompany列表
export function listShipcompany(query) {
  return request({
    url: '/shipcompany/shipcompany/list',
    method: 'get',
    params: query
  })
}

// 查询shipcompany详细
export function getShipcompany(companyId) {
  return request({
    url: '/shipcompany/shipcompany/' + companyId,
    method: 'get'
  })
}

// 新增shipcompany
export function addShipcompany(data) {
  return request({
    url: '/shipcompany/shipcompany',
    method: 'post',
    data: data
  })
}

// 修改shipcompany
export function updateShipcompany(data) {
  return request({
    url: '/shipcompany/shipcompany',
    method: 'put',
    data: data
  })
}

// 删除shipcompany
export function delShipcompany(companyId) {
  return request({
    url: '/shipcompany/shipcompany/' + companyId,
    method: 'delete'
  })
}
