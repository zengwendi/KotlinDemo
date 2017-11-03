package com.weiclicai.kotlindemo

/**
 * Created by zengwendi on 2017/11/2.
 */
data class User(var id: Int = 0, var name: String? = null,
                var role: String? = null, var avatar: String? = null, var mobile: String? = null,
                var company: String? = null, var token: String? = null, var city: String? = null,
                var province: String? = null, var code: String? = null, var rate: Double = 0.toDouble(),
                var company_abbr: String? = null, var api_city_id: Int = 0, var business_license_photo: String? = null,
                var identity_card_front: String? = null, var identity_card_back: String? = null, var id_card_no: String? = null,
                var message: Long = 0, var balance: String? = null, var city_id: String? = null, var focus_count: String? = null,
                var sub_account_count: String? = null, var total_cf: String? = null, var total_credit: String? = null,
                var business_license_no: String? = null, var status: String? = null, var car_dealer_user_id: Int = 0, var car_dealer_type: String? = null)