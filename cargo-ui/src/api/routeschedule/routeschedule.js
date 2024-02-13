/*
 * @Author: Victorzl
 * @Date: 2023-12-30 16:27:49
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-01-17 18:33:43
 * @Description: 请填写简介
 */
import request from '@/utils/request'

// 查询航运计划列表
export function listRouteschedule(query) {
  return request({
    url: '/routeschedule/routeschedule/list',
    method: 'get',
    params: query
  })
}

export function listRouteschedule1(query) {
  return request({
    url: '/routeschedule/routeschedule/list1',
    method: 'get',
    params: query
  })
}
export function listRouteschedule2(query) {
  return request({
    url: '/routeschedule/routeschedule/list2',
    method: 'get',
    params: query
  })
}

// 查询航运计划详细
export function getRouteschedule(routescheduleId) {
  return request({
    url: '/routeschedule/routeschedule/' + routescheduleId,
    method: 'get'
  })
}

// 新增航运计划
export function addRouteschedule(data) {
  return request({
    url: '/routeschedule/routeschedule',
    method: 'post',
    data: data
  })
}

export function addRouteschedule1(data) {
  return request({
    url: '/routeschedule/routeschedule/add1',
    method: 'post',
    data: data
  })
}

// 修改航运计划
export function updateRouteschedule(data) {
  return request({
    url: '/routeschedule/routeschedule',
    method: 'put',
    data: data
  })
}

// 删除航运计划
export function delRouteschedule(routescheduleId) {
  return request({
    url: '/routeschedule/routeschedule/' + routescheduleId,
    method: 'delete'
  })
}
