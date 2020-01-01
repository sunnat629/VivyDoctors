package dev.sunnat629.vivydoctors.domain.doctors

data class DoctorsListDtoEntity(
    var doctors: List<DoctorsEntity>? = null,
    var lastKey: String? = null
)

data class DoctorsEntity(
    var id: String? = null,
    var name: String? = null,
    var photoId: String? = null,
    var rating: Double? = null,
    var address: String? = null,
    var lat: Double? = null,
    var lng: Double? = null,
    var highlighted: Boolean? = null,
    var reviewCount: Int? = null,
    var specialityIds: List<Int>? = null,
    var source: String? = null,
    var phoneNumber: String? = null,
    var email: String? = null,
    var website: String? = null,
    var openingHours: List<String>? = null,
    var integration: Any? = null,
    var translation: Any? = null
)