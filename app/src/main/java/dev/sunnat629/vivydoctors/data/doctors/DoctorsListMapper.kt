package dev.sunnat629.vivydoctors.data.doctors

import dev.sunnat629.vivydoctors.data.utils.Mapper
import dev.sunnat629.vivydoctors.domain.doctors.doctorList.DoctorsEntity
import dev.sunnat629.vivydoctors.domain.doctors.doctorList.DoctorsListDtoEntity
import javax.inject.Inject

class DoctorsListDtoMapper @Inject constructor(
    private val mapper: DoctorsMapper
) : Mapper<DoctorsListDtoEntity, DoctorsListDto> {
    override fun mapFromEntity(type: DoctorsListDtoEntity): DoctorsListDto {
        return DoctorsListDto(
            lastKey = type.lastKey,
            doctors = type.doctors?.map {
                mapper.mapFromEntity(it)
            }
        )
    }

    override fun mapToEntity(type: DoctorsListDto): DoctorsListDtoEntity {
        return DoctorsListDtoEntity(
            lastKey = type.lastKey,
            doctors = type.doctors?.map {
                mapper.mapToEntity(it)
            }
        )
    }
}

class DoctorsMapper @Inject constructor() : Mapper<DoctorsEntity, DoctorsModel> {
    override fun mapFromEntity(type: DoctorsEntity): DoctorsModel {
        return DoctorsModel(
            id = type.id,
            address = type.address,
            name = type.name,
            email = type.email,
            highlighted = type.highlighted,
            integration = type.integration,
            lat = type.lat,
            lng = type.lng,
            openingHours = type.openingHours,
            phoneNumber = type.phoneNumber,
            photoId = type.photoId,
            rating = type.rating,
            reviewCount = type.reviewCount,
            source = type.source,
            specialityIds = type.specialityIds,
            translation = type.translation,
            website = type.website
        )
    }

    override fun mapToEntity(type: DoctorsModel): DoctorsEntity {
        return DoctorsEntity(
            id = type.id,
            address = type.address,
            name = type.name,
            email = type.email,
            highlighted = type.highlighted,
            integration = type.integration,
            lat = type.lat,
            lng = type.lng,
            openingHours = type.openingHours,
            phoneNumber = type.phoneNumber,
            photoId = type.photoId,
            rating = type.rating,
            reviewCount = type.reviewCount,
            source = type.source,
            specialityIds = type.specialityIds,
            translation = type.translation,
            website = type.website
        )
    }
}