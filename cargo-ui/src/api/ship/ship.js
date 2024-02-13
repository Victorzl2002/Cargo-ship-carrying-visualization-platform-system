/*
 * @Author: Victorzl
 * @Date: 2023-12-30 16:30:30
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-01-16 12:19:30
 * @Description: 请填写简介
 */
import request from '@/utils/request'

// 查询船舶管理列表
export function listShip(query) {
  return request({
    url: '/ship/ship/list',
    method: 'get',
    params: query
  })
}

export function listShip1(query) {
  return request({
    url: '/ship/ship/list1',
    method: 'get',
    params: query
  })
}

// 查询船舶管理详细
export function getShip(shipId) {
  return request({
    url: '/ship/ship/' + shipId,
    method: 'get'
  })
}

// 新增船舶管理
export function addShip(data) {
  return request({
    url: '/ship/ship',
    method: 'post',
    data: data
  })
}

// 修改船舶管理
export function updateShip(data) {
  return request({
    url: '/ship/ship',
    method: 'put',
    data: data
  })
}

// 删除船舶管理
export function delShip(shipId) {
  return request({
    url: '/ship/ship/' + shipId,
    method: 'delete'
  })
}
