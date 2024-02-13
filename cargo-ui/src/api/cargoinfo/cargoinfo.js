/*
 * @Author: Victorzl
 * @Date: 2023-12-30 16:24:31
 * @LastEditors: Victorzl
 * @LastEditTime: 2024-01-19 10:10:01
 * @Description: 请填写简介
 */
import request from '@/utils/request'

// 查询货物管理列表
export function listCargoinfo(query) {
  return request({
    url: '/cargoinfo/cargoinfo/list',
    method: 'get',
    params: query
  })
}

// 查询货物管理详细
export function getCargoinfo(cargoId) {
  return request({
    url: '/cargoinfo/cargoinfo/' + cargoId,
    method: 'get'
  })
}

// 新增货物管理
export function addCargoinfo(data) {
  return request({
    url: '/cargoinfo/cargoinfo',
    method: 'post',
    data: data
  })
}

// 货物分配
export function allocateCargo() {
  return request({
    url: '/cargoinfo/cargoinfo/allocate',
    method: 'post',
  })
}

// 修改货物管理
export function updateCargoinfo(data) {
  return request({
    url: '/cargoinfo/cargoinfo',
    method: 'put',
    data: data
  })
}

// 删除货物管理
export function delCargoinfo(cargoId) {
  return request({
    url: '/cargoinfo/cargoinfo/' + cargoId,
    method: 'delete'
  })
}
