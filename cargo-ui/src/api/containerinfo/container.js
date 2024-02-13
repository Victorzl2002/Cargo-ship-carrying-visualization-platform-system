/*
 * @Author: Victorzl
 * @Date: 2024-01-18 11:23:49
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-01-18 16:55:16
 * @Description: 请填写简介
 */
import request from '@/utils/request'

// 查询集装箱管理列表
export function listContainer(query) {
  return request({
    url: '/container/container/list',
    method: 'get',
    params: query
  })
}

// 查询集装箱管理列表
export function listContainer1(query) {
  return request({
    url: '/container/container/list1',
    method: 'get',
    params: query
  })
}

export function listContainer2(query) {
  return request({
    url: '/container/container/list2',
    method: 'get',
    params: query
  })
}

// 查询集装箱管理详细
export function getContainer(containerId) {
  return request({
    url: '/container/container/' + containerId,
    method: 'get'
  })
}

// 新增集装箱管理
export function addContainer(data) {
  return request({
    url: '/container/container',
    method: 'post',
    data: data
  })
}

// 新增集装箱管理
export function addContainer1(data) {
  return request({
    url: '/container/container/port',
    method: 'post',
    data: data
  })
}

// 新增集装箱管理
export function addContainer2(data) {
  return request({
    url: '/container/container/ship',
    method: 'post',
    data: data
  })
}

// 修改集装箱管理
export function updateContainer(data) {
  return request({
    url: '/container/container',
    method: 'put',
    data: data
  })
}

// 删除集装箱管理
export function delContainer(containerId) {
  return request({
    url: '/container/container/' + containerId,
    method: 'delete'
  })
}
