package com.domainmoney.common.navigation

import dagger.hilt.GeneratesRootInput

@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
@GeneratesRootInput
annotation class HiltNavigationFactory
