package com.nocountry.movie_no_country.feature_home.domain.usecase

import com.nocountry.movie_no_country.feature_home.domain.CommentRepository

class GetCommentsUseCase(private val commentRepository: CommentRepository) {

    suspend operator fun invoke()=commentRepository.getComments()
}