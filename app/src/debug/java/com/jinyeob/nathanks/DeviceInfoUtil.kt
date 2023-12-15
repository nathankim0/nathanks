package com.jinyeob.nathanks

import android.os.Build

object DeviceInfoUtil {
    /**
     * device 제조사 가져오기
     * @return
     */
    val manufacturer: String
        get() = Build.MANUFACTURER

    /**
     * device 브랜드 가져오기
     * @return
     */
    val deviceBrand: String
        get() = Build.BRAND

    /**
     * device 모델명 가져오기
     * @return
     */
    val deviceModel: String
        get() = Build.MODEL

    /**
     * device Android OS 버전 가져오기
     * @return
     */
    val deviceOs: String
        get() = Build.VERSION.RELEASE

    /**
     * device SDK 버전 가져오기
     * @return
     */
    val deviceSdk: Int
        get() = Build.VERSION.SDK_INT
}
