package vn.asiantech.nhanphant.roompersistencelibrarydemo.data.model

/**
 * AsianTech Inc.
 * Created by nhanphant on 25/12/2017.
 */
data class Address(
        var latitude: Long,
        var longitude: Long
) {
    constructor() : this(0, 0)
}
