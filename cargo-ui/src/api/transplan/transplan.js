/*
 * @Author: Victorzl
 * @Date: 2024-04-07 13:43:58
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-05-12 19:07:59
 * @Description: 请填写简介
 */
import request from '@/utils/request'

// 查询transplan列表
export function listTransplan(query) {
  return request({
    url: '/transplan/transplan/list',
    method: 'get',
    params: query
  })
}

// 查询transplan详细
export function getTransplan(planId) {
  return request({
    url: '/transplan/transplan/' + planId,
    method: 'get'
  })
}

// 新增transplan
export function addTransplan(data) {
  return request({
    url: '/transplan/transplan',
    method: 'post',
    data: data
  })
}

// 任务分配
export function allocateTransplan(planId) {
  return request({
    url: '/transplan/transplan/allocate/' + planId,
    method: 'get',
  })
}

// 修改transplan
export function updateTransplan(data) {
  return request({
    url: '/transplan/transplan',
    method: 'put',
    data: data
  })
}

// 删除transplan
export function delTransplan(planId) {
  return request({
    url: '/transplan/transplan/' + planId,
    method: 'delete'
  })
}
