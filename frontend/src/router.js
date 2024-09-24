
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import MaterialMaterialManager from "./components/listers/MaterialMaterialCards"
import MaterialMaterialDetail from "./components/listers/MaterialMaterialDetail"

import AdvertisementAdvertisementManager from "./components/listers/AdvertisementAdvertisementCards"
import AdvertisementAdvertisementDetail from "./components/listers/AdvertisementAdvertisementDetail"

import InventoryInventoryManager from "./components/listers/InventoryInventoryCards"
import InventoryInventoryDetail from "./components/listers/InventoryInventoryDetail"

import DeliveryDeliveryManager from "./components/listers/DeliveryDeliveryCards"
import DeliveryDeliveryDetail from "./components/listers/DeliveryDeliveryDetail"



export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/materials/materials',
                name: 'MaterialMaterialManager',
                component: MaterialMaterialManager
            },
            {
                path: '/materials/materials/:id',
                name: 'MaterialMaterialDetail',
                component: MaterialMaterialDetail
            },

            {
                path: '/advertisements/advertisements',
                name: 'AdvertisementAdvertisementManager',
                component: AdvertisementAdvertisementManager
            },
            {
                path: '/advertisements/advertisements/:id',
                name: 'AdvertisementAdvertisementDetail',
                component: AdvertisementAdvertisementDetail
            },

            {
                path: '/inventories/inventories',
                name: 'InventoryInventoryManager',
                component: InventoryInventoryManager
            },
            {
                path: '/inventories/inventories/:id',
                name: 'InventoryInventoryDetail',
                component: InventoryInventoryDetail
            },

            {
                path: '/deliveries/deliveries',
                name: 'DeliveryDeliveryManager',
                component: DeliveryDeliveryManager
            },
            {
                path: '/deliveries/deliveries/:id',
                name: 'DeliveryDeliveryDetail',
                component: DeliveryDeliveryDetail
            },




    ]
})
