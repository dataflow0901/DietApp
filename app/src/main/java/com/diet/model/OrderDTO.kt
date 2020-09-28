package com.diet.model

import java.lang.reflect.Constructor
import java.util.*

class OrderDTO {

    var userId:String? = null
    var name:String? = null
    var userCellNo:String? = null
    var email:String? = null

    var productNo:Int? = null
    var productCode:String? = null
    var productName:String? = null
    var companyNo:Int? = null
    var companyCode:String? = null
    var companyName:String? = null

    var salesStandNo:Int? = null
    var salesStandCode:String? = null
    var salesStandName:String? = null

    var cartNo:Int? = null
    var zzimNo:Int? = null
    var qty:Int? = null
    var unit:String? = null
    var price:Int? = null
    var ranking:Int? = null
    var gpa:Int? = null
    var review:Int? = null
    var originalAmount:Int? = null
    var discountAmount:Int? = null
    var purchaseAmount:Int? = null
    var deliveryCost:Int? = null
    var paymentNo:Int? = null
    var paymentCode:String? = null
    var paymentName:String? = null
    var paymentDate:String? = null
    var paymentTotalAmount:Int? = null
    var paidRealAmount:Int? = null
    var paidCouponAmount:Int? = null
    var paidPointAmount:Int? = null

    var deliveryName:String? = null
    var deliveryUserCellNo:String? = null
    var deliveryAddress:String? = null
    var pointTotal:Int? = null
    var pointRemaining:Int? = null
    var pointUsed:Int? = null
    var pointRemainingPredict:Int? = null


}