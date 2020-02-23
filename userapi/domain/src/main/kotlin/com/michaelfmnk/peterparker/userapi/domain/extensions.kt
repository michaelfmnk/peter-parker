package com.michaelfmnk.peterparker.userapi.domain

import java.time.Duration
import java.util.*

infix fun Date.plus(duration: Duration) = Date(time + duration.seconds * 1000)