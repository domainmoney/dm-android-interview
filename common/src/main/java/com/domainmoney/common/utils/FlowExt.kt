package com.domainmoney.common.utils

import kotlinx.coroutines.flow.MutableStateFlow

inline fun <T> MutableStateFlow<T>.update(block: (T) -> (T)) {
    compareAndSet(value, block(value))
}
