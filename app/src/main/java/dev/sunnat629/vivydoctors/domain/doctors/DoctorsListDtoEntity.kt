package dev.sunnat629.vivydoctors.domain.doctors

data class DoctorsListDtoEntity(
    var doctors: List<DoctorsEntity>?,
    var lastKey: String?
)

data class DoctorsEntity(
    var id: String?,
    var name: String?,
    var photoId: String?,
    var rating: Double?,
    var address: String?,
    var lat: Double?,
    var lng: Double?,
    var highlighted: Boolean?,
    var reviewCount: Int?,
    var specialityIds: List<Int>?,
    var source: String?,
    var phoneNumber: String?,
    var email: String?,
    var website: String?,
    var openingHours: List<String>?,
    var integration: Any?,
    var translation: Any?
)