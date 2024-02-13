import request from '@/utils/request'

// 查询用户反馈列表
export function listFeedbook(query) {
  return request({
    url: '/feedbook/feedbook/list',
    method: 'get',
    params: query
  })
}

// 查询用户反馈详细
export function getFeedbook(feedId) {
  return request({
    url: '/feedbook/feedbook/' + feedId,
    method: 'get'
  })
}

// 新增用户反馈
export function addFeedbook(data) {
  return request({
    url: '/feedbook/feedbook',
    method: 'post',
    data: data
  })
}

// 修改用户反馈
export function updateFeedbook(data) {
  return request({
    url: '/feedbook/feedbook',
    method: 'put',
    data: data
  })
}

// 删除用户反馈
export function delFeedbook(feedId) {
  return request({
    url: '/feedbook/feedbook/' + feedId,
    method: 'delete'
  })
}
