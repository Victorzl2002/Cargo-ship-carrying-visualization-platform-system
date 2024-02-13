/*
 * @Author: Victorzl
 * @Date: 2023-12-30 20:14:40
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-01-17 18:46:17
 * @Description: 请填写简介
 */
import request from '@/utils/request'

// 查询order列表
export function listOrder(query) {
  return request({
    url: '/order/order/list',
    method: 'get',
    params: query
  })
}

export function listOrder1(query) {
  return request({
    url: '/order/order/list1',
    method: 'get',
    params: query
  })
}
export function listOrder2(query) {
  return request({
    url: '/order/order/list2',
    method: 'get',
    params: query
  })
}

export function listOrder3(query) {
  return request({
    url: '/order/order/list3',
    method: 'get',
    params: query
  })
}
// 查询order详细
export function getOrder(orderId) {
  return request({
    url: '/order/order/' + orderId,
    method: 'get'
  })
}

// 新增order
export function addOrder(data) {
  return request({
    url: '/order/order',
    method: 'post',
    data: data
  })
}

// 修改order
export function updateOrder(data) {
  return request({
    url: '/order/order',
    method: 'put',
    data: data
  })
}

// 删除order
export function delOrder(orderId) {
  return request({
    url: '/order/order/' + orderId,
    method: 'delete'
  })
}
